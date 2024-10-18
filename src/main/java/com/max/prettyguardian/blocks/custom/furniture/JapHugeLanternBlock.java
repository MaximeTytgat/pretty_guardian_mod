package com.max.prettyguardian.blocks.custom.furniture;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
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
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class JapHugeLanternBlock extends LanternBlock {
    private static final VoxelShape AABB = Shapes.or(
            Block.box(6.500000000000005, 11, 8, 9.500000000000005, 13, 8),
            Block.box(8, 12, 6.500000000000002, 8, 15, 9.500000000000002),
            Block.box(6.500000000000005, 14, 8, 9.500000000000005, 16, 8),
            Block.box(3, 9, 3, 13, 11, 13),
            Block.box(1, 8, 1, 15, 9, 15),
            Block.box(0, 0, 0, 16, 8, 16)
    );
    private static final VoxelShape MIDDLE_AABB = Shapes.or(
            Block.box(0, 13, 0, 16, 16, 16),
            Block.box(1, 12, 1, 15, 13, 15),
            Block.box(4, 11, 4, 12, 12, 12),
            Block.box(3, 10, 3, 13, 11, 13),
            Block.box(2, 3, 2, 14, 10, 14),
            Block.box(3, 2, 3, 13, 3, 13),
            Block.box(5, 1, 5, 11, 2, 11),
            Block.box(4, 0, 4, 12, 1, 12)
    );
    private static final VoxelShape LOWER_AABB = Shapes.or(
            Block.box(3, 10, 3, 13, 16, 13),
            Block.box(4, 9, 4, 12, 10, 12),
            Block.box(5, 8, 5, 11, 9, 11)
    );
    public static final EnumProperty<TripleBlockHalf> HALF = EnumProperty.create("half", TripleBlockHalf.class);
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

    public JapHugeLanternBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(
                this.stateDefinition.any()
                    .setValue(HALF, TripleBlockHalf.UPPER)
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
        BlockPos middlePos = blockPos.below();
        level.setBlock(
                middlePos,
                blockState
                    .setValue(HALF, TripleBlockHalf.MIDDLE)
                    .setValue(POWERED, Boolean.FALSE)
                    .setValue(HANGING, Boolean.TRUE)
                    .setValue(LIT, Boolean.FALSE)
                    .setValue(WATERLOGGED, Boolean.FALSE),
                3
        );
        level.setBlock(
                middlePos.below(),
                blockState
                    .setValue(HALF, TripleBlockHalf.LOWER)
                    .setValue(POWERED, Boolean.FALSE)
                    .setValue(HANGING, Boolean.TRUE)
                    .setValue(LIT, Boolean.FALSE)
                    .setValue(WATERLOGGED, Boolean.FALSE),
                3
        );
    }

    @Override
    public @NotNull VoxelShape getShape(
            BlockState blockState,
            @NotNull BlockGetter blockGetter,
            @NotNull BlockPos blockPos,
            @NotNull CollisionContext collisionContext
    ) {
        return switch (blockState.getValue(HALF)) {
            case LOWER -> LOWER_AABB;
            case MIDDLE -> MIDDLE_AABB;
            default -> AABB;
        };
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext clickedPos) {
        FluidState fluidstate = clickedPos.getLevel().getFluidState(clickedPos.getClickedPos());

        for(Direction direction : clickedPos.getNearestLookingDirections()) {
            if (direction == Direction.UP) {
                BlockPos belowPos = clickedPos.getClickedPos().below();
                BlockPos belowPos2 = belowPos.below();

                if (
                        clickedPos.getLevel().getBlockState(belowPos).canBeReplaced(clickedPos) &&
                        clickedPos.getLevel().getBlockState(belowPos2).canBeReplaced(clickedPos)
                ) {
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
    public @NotNull BlockState updateShape(
            @NotNull BlockState currentState,
            @NotNull Direction facing,
            BlockState facingState,
            @NotNull LevelAccessor level,
            @NotNull BlockPos currentPos,
            @NotNull BlockPos facingPos
    ) {
        Block facingBlock = facingState.getBlock();

        boolean isFacingBlock = facingBlock == this;
        if (isFacingBlock) return super.updateShape(currentState, facing, facingState, level, currentPos, facingPos);

        TripleBlockHalf half = currentState.getValue(HALF);
        if (
                (half == TripleBlockHalf.LOWER && facing == Direction.UP) ||
                (half == TripleBlockHalf.MIDDLE && (facing == Direction.UP || facing == Direction.DOWN)) ||
                (half == TripleBlockHalf.UPPER && facing == Direction.DOWN)
        ){
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
            TripleBlockHalf half = blockState.getValue(HALF);
            BlockPos blockToDestroy = switch (half) {
                case LOWER -> blockPos.above();
                case UPPER -> blockPos.below();
                default -> blockPos;
            };

            level.destroyBlock(blockToDestroy, false);
        }

        return super.playerWillDestroy(level, blockPos, blockState, player);
    }

    @Override
    public void neighborChanged(
            @NotNull BlockState blockState,
            Level level,
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
            @NotNull BlockState blockState,
            @NotNull Level level,
            @NotNull BlockPos blockPos,
            @NotNull Player player,
            @NotNull BlockHitResult blockHitResult
    ) {
        switch (blockState.getValue(HALF)) {
            case LOWER -> {
                changeCycle(level, blockPos.above());
                changeCycle(level, blockPos.above().above());
            }
            case MIDDLE -> {
                changeCycle(level, blockPos.above());
                changeCycle(level, blockPos.below());
            }
            case UPPER -> {
                changeCycle(level, blockPos.below());
                changeCycle(level, blockPos.below().below());
            }
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

    public enum TripleBlockHalf implements StringRepresentable {
        UPPER,
        MIDDLE,
        LOWER;

        TripleBlockHalf() {
        }

        @Override
        public String toString() {
            return this.getSerializedName();
        }

        public @NotNull String getSerializedName() {
            return this.name().toLowerCase();
        }
    }

}
