package com.max.prettyguardian.blocks.custom.furniture;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class JapScreenBlock extends Block {
    public static final EnumProperty<DoubleBlockHalf> HALF;
    public static final EnumProperty<ParaventPart> PART;
    public static final EnumProperty<Direction> FACING;
    private static final VoxelShape SHAPE = Block.box(0, 0, 7, 16, 16, 9);
    private static final VoxelShape SHAPE_EAST = Block.box(7, 0, 0, 9, 16, 16);
    public JapScreenBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(HALF, DoubleBlockHalf.LOWER).setValue(PART, ParaventPart.RIGHT).setValue(FACING, Direction.NORTH));
    }

    @Override
    public @NotNull VoxelShape getShape(
            BlockState blockState,
            @NotNull BlockGetter blockGetter,
            @NotNull BlockPos blockPos,
            @NotNull CollisionContext collisionContext
    ) {
        return blockState.getValue(FACING) == Direction.EAST || blockState.getValue(FACING) == Direction.WEST ? SHAPE_EAST : SHAPE;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos pos = context.getClickedPos();
        Direction direction = context.getHorizontalDirection();
        BlockPos pos1 = pos.relative(direction.getClockWise());
        Level level = context.getLevel();
        if (
                pos.getY() < level.getMaxBuildHeight() - 1
                && level.getBlockState(pos.above()).canBeReplaced(context)
                && level.getBlockState(pos1).canBeReplaced(context)
                && level.getBlockState(pos1.above()).canBeReplaced(context)
        ) {
            return this.defaultBlockState()
                    .setValue(FACING, direction)
                    .setValue(HALF, DoubleBlockHalf.LOWER)
                    .setValue(PART, ParaventPart.LEFT);
        }

        return null;
    }

    @Override
    public void setPlacedBy(
            @NotNull Level level,
            @NotNull BlockPos blockPos,
            @NotNull BlockState blockState,
            @Nullable LivingEntity livingEntity,
            @NotNull ItemStack itemStack
    ) {
        super.setPlacedBy(level, blockPos, blockState, livingEntity, itemStack);
        if (!level.isClientSide) {
            level.setBlock(
                    blockPos.above(),
                    blockState
                            .setValue(HALF, DoubleBlockHalf.UPPER)
                            .setValue(PART, ParaventPart.LEFT),
                    3
            );
            BlockPos blockPos1 = blockPos.relative(blockState.getValue(FACING).getClockWise());
            level.setBlock(
                    blockPos1,
                    blockState
                            .setValue(HALF, DoubleBlockHalf.LOWER)
                            .setValue(PART, ParaventPart.RIGHT),
                    3
            );
            level.setBlock(
                    blockPos1.above(),
                    blockState
                            .setValue(HALF, DoubleBlockHalf.UPPER)
                            .setValue(PART, ParaventPart.RIGHT),
                    3
            );
        }
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

        if (facingBlock == this) super.updateShape(currentState, facing, facingState, level, currentPos, facingPos);

        DoubleBlockHalf half = currentState.getValue(HALF);
        ParaventPart part = currentState.getValue(PART);
        Direction direction = currentState.getValue(FACING);
        Direction facingDirection = part == ParaventPart.LEFT ? direction.getClockWise() : direction.getCounterClockWise();
        if (
                (half == DoubleBlockHalf.LOWER && (facing == Direction.UP || facing == facingDirection))
                || (half == DoubleBlockHalf.UPPER && (facing == Direction.DOWN || facing == facingDirection))
        ) {
            return Blocks.AIR.defaultBlockState();
        }

        return super.updateShape(currentState, facing, facingState, level, currentPos, facingPos);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(PART, HALF, FACING);
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
                case LOWER -> blockPos;
                case UPPER -> blockPos.below();
            };

            ParaventPart part = blockState.getValue(PART);
            blockToDestroy = switch (part) {
                case LEFT -> blockToDestroy;
                case RIGHT -> blockToDestroy.relative(blockState.getValue(FACING).getCounterClockWise());
            };

            level.destroyBlock(blockToDestroy, false);
        }

        return super.playerWillDestroy(level, blockPos, blockState, player);
    }

    public enum ParaventPart implements StringRepresentable {
        RIGHT("right"),
        LEFT("left");

        private final String name;

        ParaventPart(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return this.name;
        }

        public @NotNull String getSerializedName() {
            return this.name;
        }
    }

    static {
        FACING = DirectionalBlock.FACING;
        PART = EnumProperty.create("part", ParaventPart.class);
        HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
    }
}
