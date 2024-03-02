package com.max.prettyguardian.blocks.custom.furniture;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
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

import javax.annotation.Nullable;

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


    public static final EnumProperty<DoubleBlockHalf> HALF = EnumProperty.create("half", DoubleBlockHalf.class);;
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;


    public JapDoubleLanternBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(HALF, DoubleBlockHalf.UPPER).setValue(POWERED, Boolean.valueOf(false)).setValue(HANGING, Boolean.valueOf(false)).setValue(LIT, Boolean.valueOf(false)).setValue(WATERLOGGED, Boolean.valueOf(false)));
    }

    public void setPlacedBy(Level level, BlockPos blockPos, BlockState blockState, LivingEntity livingEntity, ItemStack itemStack) {
        level.setBlock(blockPos.below(), blockState.setValue(HALF, DoubleBlockHalf.LOWER).setValue(POWERED, Boolean.valueOf(false)).setValue(HANGING, Boolean.valueOf(true)).setValue(LIT, Boolean.valueOf(false)).setValue(WATERLOGGED, Boolean.valueOf(false)), 3);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return blockState.getValue(HALF) == DoubleBlockHalf.LOWER ? LOWER_AABB : AABB;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext clickedPos) {
        FluidState fluidstate = clickedPos.getLevel().getFluidState(clickedPos.getClickedPos());

        for(Direction direction : clickedPos.getNearestLookingDirections()) {
            if (direction == Direction.UP) {
                BlockPos belowPos = clickedPos.getClickedPos().below();
                BlockPos belowPos2 = belowPos.below();

                if (clickedPos.getLevel().getBlockState(belowPos).canBeReplaced(clickedPos) && clickedPos.getLevel().getBlockState(belowPos2).canBeReplaced(clickedPos)) {
                    BlockState blockstate = this.defaultBlockState().setValue(HANGING, Boolean.TRUE);

                    if (clickedPos.getLevel().hasNeighborSignal(clickedPos.getClickedPos())) {
                        blockstate = blockstate.setValue(LIT, Boolean.TRUE).setValue(POWERED, Boolean.TRUE);
                    }

                    if (blockstate.canSurvive(clickedPos.getLevel(), clickedPos.getClickedPos())) {
                        return blockstate.setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
                    }
                }
            }
        }

        return null;
    }

    @Override
    public BlockState updateShape(BlockState currentState, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        Block facingBlock = facingState.getBlock();

        if (currentState.getValue(HALF) == DoubleBlockHalf.LOWER) {
            if (facing == Direction.UP && facingBlock != this) {
                return Blocks.AIR.defaultBlockState();
            }
        } else if (currentState.getValue(HALF) == DoubleBlockHalf.UPPER) {
            if (facing == Direction.DOWN && facingBlock != this) {
                return Blocks.AIR.defaultBlockState();
            }
        }

        return super.updateShape(currentState, facing, facingState, level, currentPos, facingPos);
    }

    @Override
    public void playerWillDestroy(Level level, BlockPos blockPos, BlockState blockState, Player player) {
        // prevent creative drops
        if (player.isCreative()) {
            DoubleBlockHalf half = blockState.getValue(HALF);
            BlockPos blockToDestroy = switch (half) {
                case LOWER -> blockPos.above();
                case UPPER -> blockPos;
            };

            level.destroyBlock(blockToDestroy, false);
        }

        super.playerWillDestroy(level, blockPos, blockState, player);
    }

    public void neighborChanged(BlockState blockState, Level level, BlockPos blockPos, Block block, BlockPos blockPos1, boolean b) {
        if (!level.isClientSide) {
            boolean flag = level.hasNeighborSignal(blockPos);
            if (flag != blockState.getValue(POWERED)) {
                if (blockState.getValue(LIT) != flag) {
                    blockState = blockState.setValue(LIT, Boolean.valueOf(flag));
                }

                level.setBlock(blockPos, blockState.setValue(POWERED, Boolean.valueOf(flag)), 2);
                if (blockState.getValue(WATERLOGGED)) {
                    level.scheduleTick(blockPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
                }
            }

        }
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_153490_) {
        p_153490_.add(HALF, LIT, POWERED, HANGING, WATERLOGGED);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {

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
        if (blockState.getValue(WATERLOGGED)) {
            level.scheduleTick(blockPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }
    }
}
