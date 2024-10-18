package com.max.prettyguardian.blocks.custom.furniture;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class JapDoubleLanternBlock extends LanternBlock {
    private static final VoxelShape AABB = Shapes.or(
            Block.box(3, 9, 3, 13, 11, 13),
            Block.box(1, 8, 1, 15, 9, 15),
            Block.box(0, 0, 0, 16, 8, 16)
    );
    private static final VoxelShape LOWER_AABB = Shapes.or(
            Block.box(0, 13, 0, 16, 16, 16),
            Block.box(1, 12, 1, 15, 13, 15),
            Block.box(3, 10, 3, 13, 12, 13)
    );
    public static final EnumProperty<DoubleBlockHalf> HALF = EnumProperty.create("half", DoubleBlockHalf.class);
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

    public JapDoubleLanternBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(
                this.stateDefinition.any()
                    .setValue(HALF, DoubleBlockHalf.UPPER)
                    .setValue(POWERED, Boolean.FALSE)
                    .setValue(HANGING, Boolean.FALSE)
                    .setValue(LIT, Boolean.FALSE)
                    .setValue(WATERLOGGED, Boolean.FALSE)
        );
    }

    @Override
    public void setPlacedBy(
            Level level,
            BlockPos blockPos,
            BlockState blockState,
            LivingEntity livingEntity,
            @NotNull ItemStack itemStack
    ) {
        level.setBlock(
                blockPos.below(),
                blockState
                    .setValue(HALF, DoubleBlockHalf.LOWER)
                    .setValue(POWERED, Boolean.FALSE)
                    .setValue(HANGING, Boolean.TRUE)
                    .setValue(LIT, Boolean.FALSE)
                    .setValue(WATERLOGGED, Boolean.FALSE),
                3);
    }

    @Override
    public @NotNull VoxelShape getShape(
            BlockState blockState,
            @NotNull BlockGetter blockGetter,
            @NotNull BlockPos blockPos,
            @NotNull CollisionContext collisionContext
    ) {
        return blockState.getValue(HALF) == DoubleBlockHalf.LOWER ? LOWER_AABB : AABB;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        Level level = blockPlaceContext.getLevel();
        BlockPos clickedPos = blockPlaceContext.getClickedPos();
        FluidState fluidstate = level.getFluidState(clickedPos);

        for(Direction direction : blockPlaceContext.getNearestLookingDirections()) {
            BlockPos belowPos = clickedPos.below();
            if (direction != Direction.UP || !level.getBlockState(belowPos).canBeReplaced(blockPlaceContext)) continue;

            BlockState blockstate = this.defaultBlockState().setValue(HANGING, Boolean.TRUE);

            if (level.hasNeighborSignal(clickedPos)) {
                blockstate = blockstate.setValue(LIT, Boolean.TRUE).setValue(POWERED, Boolean.TRUE);
            }

            if (blockstate.canSurvive(level, clickedPos)) {
                return blockstate.setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
            }
        }

        return super.getStateForPlacement(blockPlaceContext);
    }

    @Override
    public @NotNull BlockState updateShape(
            BlockState currentState,
            @NotNull Direction facing,
            BlockState facingState,
            @NotNull LevelAccessor level,
            @NotNull BlockPos currentPos,
            @NotNull BlockPos facingPos
    ) {
        Block facingBlock = facingState.getBlock();

        if (currentState.getValue(HALF) == DoubleBlockHalf.LOWER) {
            if (facing == Direction.UP && facingBlock != this) {
                return Blocks.AIR.defaultBlockState();
            }
        } else if (
                currentState.getValue(HALF) == DoubleBlockHalf.UPPER
                        && facing == Direction.DOWN
                        && facingBlock != this
        ) {
            return Blocks.AIR.defaultBlockState();
        }

        return super.updateShape(currentState, facing, facingState, level, currentPos, facingPos);
    }

    @Override
    public @NotNull BlockState playerWillDestroy(
            @NotNull Level level,
            @NotNull BlockPos blockPos,
            @NotNull BlockState blockState,
            Player player
    ) {
        if (player.isCreative()) {
            DoubleBlockHalf half = blockState.getValue(HALF);
            BlockPos blockToDestroy = switch (half) {
                case LOWER -> blockPos.above();
                case UPPER -> blockPos;
            };

            level.destroyBlock(blockToDestroy, false);
        }

        return super.playerWillDestroy(level, blockPos, blockState, player);
    }

    @Override
    public void neighborChanged(
            @NotNull BlockState blockState, Level level,
            @NotNull BlockPos blockPos,
            @NotNull Block block,
            @NotNull BlockPos blockPos1,
            boolean b
    ) {
        if (!level.isClientSide) {
            boolean flag = level.hasNeighborSignal(blockPos);
            if (flag != Boolean.TRUE.equals(blockState.getValue(POWERED))) {
                if (Boolean.TRUE.equals(blockState.getValue(LIT)) != flag) {
                    blockState = blockState.setValue(LIT, flag);
                }

                level.setBlock(blockPos, blockState.setValue(POWERED, flag), 2);
                if (Boolean.TRUE.equals(blockState.getValue(WATERLOGGED))) {
                    level.scheduleTick(blockPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
                }
            }

        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> blockBlockStateBuilder) {
        blockBlockStateBuilder.add(HALF, LIT, POWERED, HANGING, WATERLOGGED);
    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(
            BlockState blockState,
            @NotNull Level level,
            @NotNull BlockPos blockPos,
            @NotNull Player player,
            @NotNull BlockHitResult blockHitResult
    ) {
        if (blockState.getValue(HALF) == DoubleBlockHalf.LOWER) {
            changeCycle(level, blockPos.above());
        } else {
            changeCycle(level, blockPos.below());
        }

        changeCycle(level, blockPos);
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    private static void changeCycle(Level level, BlockPos blockPos) {
        BlockState blockState = level.getBlockState(blockPos);
        blockState = blockState.cycle(LIT);
        level.setBlock(blockPos, blockState, 2);
        if (Boolean.TRUE.equals(blockState.getValue(WATERLOGGED))) {
            level.scheduleTick(blockPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }
    }
}
