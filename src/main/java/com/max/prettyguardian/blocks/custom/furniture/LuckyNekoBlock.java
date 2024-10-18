package com.max.prettyguardian.blocks.custom.furniture;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class LuckyNekoBlock extends Block {
    public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
    public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;
    private static final VoxelShape SHAPE_NORTH_LOWER = Shapes.or(Block.box(1.5000000000000004, 0.001, 3, 14.499999999999998, 16, 16), Block.box(-0.9999999999999996, 0, 1, 4.000000000000002, 8, 15), Block.box(11.999999999999996, 0, 1, 17.000000000000014, 8, 15));
    private static final VoxelShape SHAPE_EAST_LOWER = Shapes.or(Block.box(0, 0.001, 1.5, 13, 16, 14.499999999999998), Block.box(1, 0, -1, 15, 8, 4.000000000000002), Block.box(1, 0, 11.999999999999996, 15, 8, 17.000000000000014));
    private static final VoxelShape SHAPE_SOUTH_LOWER = Shapes.or(Block.box(1.5000000000000018, 0.001, 0, 14.5, 16, 13), Block.box(11.999999999999998, 0, 1, 17, 8, 15), Block.box(-1.0000000000000142, 0, 1, 4.0000000000000036, 8, 15));
    private static final VoxelShape SHAPE_WEST_LOWER = Shapes.or(Block.box(3, 0.001, 1.5000000000000018, 16, 16, 14.5), Block.box(1, 0, 11.999999999999998, 15, 8, 17), Block.box(1, 0, -1.0000000000000142, 15, 8, 4.0000000000000036));
    private static final VoxelShape SHAPE_NORTH_UPPER = Shapes.or(Block.box(1.5, 0, 3, 14.499999999999998, 11, 16), Block.box(11.499999999999998, 8, 10, 17.500000000000007, 14, 12), Block.box(-1.5, 8, 10, 4.499999999999998, 14, 12), Block.box(4.5, 0.9999999999999858, 0, 11.5, 4.999999999999986, 3));
    private static final VoxelShape SHAPE_EAST_UPPER = Shapes.or(Block.box(0, 0, 1.5, 13, 11, 14.499999999999998), Block.box(4, 8, 11.499999999999998, 6, 14, 17.500000000000007), Block.box(4, 8, -1.5, 6, 14, 4.499999999999998), Block.box(13, 0.9999999999999858, 4.5, 16, 4.999999999999986, 11.5));
    private static final VoxelShape SHAPE_SOUTH_UPPER = Shapes.or(Block.box(1.5000000000000018, 0, 0, 14.5, 11, 13), Block.box(-1.500000000000007, 8, 4, 4.500000000000002, 14, 6), Block.box(11.500000000000002, 8, 4, 17.5, 14, 6), Block.box(4.5, 0.9999999999999858, 13, 11.5, 4.999999999999986, 16));
    private static final VoxelShape SHAPE_WEST_UPPER = Shapes.or(Block.box(3, 0, 1.5000000000000018, 16, 11, 14.5), Block.box(10, 8, -1.500000000000007, 12, 14, 4.500000000000002), Block.box(10, 8, 11.500000000000002, 12, 14, 17.5), Block.box(0, 0.9999999999999858, 4.5, 3, 4.999999999999986, 11.5));

    public LuckyNekoBlock(Properties properties) {
        super(properties);
        this.defaultBlockState()
                .setValue(FACING, Direction.NORTH)
                .setValue(HALF, DoubleBlockHalf.LOWER);
    }

    @Override
    public @NotNull VoxelShape getShape(
            BlockState blockState,
            @NotNull BlockGetter blockGetter,
            @NotNull BlockPos blockPos,
            @NotNull CollisionContext collisionContext
    ) {
        DoubleBlockHalf half = blockState.getValue(HALF);
        return switch (blockState.getValue(FACING)) {
            case SOUTH -> half == DoubleBlockHalf.UPPER ? SHAPE_SOUTH_UPPER : SHAPE_SOUTH_LOWER;
            case EAST -> half == DoubleBlockHalf.UPPER ? SHAPE_EAST_UPPER : SHAPE_EAST_LOWER;
            case WEST -> half == DoubleBlockHalf.UPPER ? SHAPE_WEST_UPPER : SHAPE_WEST_LOWER;
            default -> half == DoubleBlockHalf.UPPER ? SHAPE_NORTH_UPPER : SHAPE_NORTH_LOWER;
        };
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
        } else if (currentState.getValue(HALF) == DoubleBlockHalf.UPPER) {
            if (facing == Direction.DOWN && facingBlock != this) {
                return Blocks.AIR.defaultBlockState();
            }
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
                case LOWER -> blockPos;
                case UPPER -> blockPos.below();
            };

            level.destroyBlock(blockToDestroy, false);
        }
        return super.playerWillDestroy(level, blockPos, blockState, player);
    }


    @Override
    public void setPlacedBy(
            Level level,
            BlockPos blockPos,
            BlockState blockState,
            @Nullable LivingEntity livingEntity,
            @NotNull ItemStack itemStack
    ) {
        level.setBlock(
                blockPos.above(),
                this.defaultBlockState()
                        .setValue(FACING, blockState.getValue(FACING))
                        .setValue(HALF, DoubleBlockHalf.UPPER),
                3
        );
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        if (context.getLevel().getBlockState(context.getClickedPos().above()).canBeReplaced(context)) {
            return this.defaultBlockState()
                    .setValue(FACING, context.getHorizontalDirection().getOpposite())
                    .setValue(HALF, DoubleBlockHalf.LOWER);
        } else {
            return null;
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, HALF);
    }
}
