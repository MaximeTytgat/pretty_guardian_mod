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
import org.jetbrains.annotations.Nullable;

public class JapScrollBlock extends Block {
    public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
    public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;

    private static final VoxelShape SHAPE_NORTH_LOWER = Block.box(1, 1, -1.7763568394002505e-15, 15, 16, 0.9999999999999982);
    private static final VoxelShape SHAPE_EAST_LOWER = Block.box(15.000000000000002, 1, 1, 16, 16, 15);
    private static final VoxelShape SHAPE_SOUTH_LOWER = Block.box(1, 1, 15.000000000000002, 15, 16, 16);
    private static final VoxelShape SHAPE_WEST_LOWER = Block.box(0, 1, 1, 0.9999999999999982, 16, 15);
    private static final VoxelShape SHAPE_NORTH_UPPER = Shapes.or(Block.box(0.0009999999999998899, 10, -0.6, 15.998999999999999, 12, 1.4000000000000004), Block.box(1, 0, -1.8041124150158794e-15, 15, 10, 0.9999999999999983));
    private static final VoxelShape SHAPE_EAST_UPPER = Shapes.or(Block.box(14.6, 10, 0.0009999999999994458, 16.6, 12, 15.998999999999999), Block.box(15.000000000000002, 0, 1, 16, 10, 15));
    private static final VoxelShape SHAPE_SOUTH_UPPER = Shapes.or(Block.box(0.0010000000000012221, 10, 14.6, 15.999, 12, 16.6), Block.box(1, 0, 15.000000000000002, 15, 10, 16));
    private static final VoxelShape SHAPE_WEST_UPPER = Shapes.or(Block.box(-0.6000000000000014, 10, 0.0010000000000012221, 1.4000000000000004, 12, 15.999), Block.box(0, 0, 1, 0.9999999999999982, 10, 15));
    public JapScrollBlock(Properties properties) {
        super(properties);
        this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(HALF, DoubleBlockHalf.UPPER);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        DoubleBlockHalf half = blockState.getValue(HALF);
        return switch (blockState.getValue(FACING)) {
            case NORTH -> half == DoubleBlockHalf.UPPER ? SHAPE_SOUTH_UPPER : SHAPE_SOUTH_LOWER;
            case SOUTH -> half == DoubleBlockHalf.UPPER ? SHAPE_NORTH_UPPER : SHAPE_NORTH_LOWER;
            case EAST -> half == DoubleBlockHalf.UPPER ? SHAPE_WEST_UPPER : SHAPE_WEST_LOWER;
            case WEST -> half == DoubleBlockHalf.UPPER ? SHAPE_EAST_UPPER : SHAPE_EAST_LOWER;
            default -> half == DoubleBlockHalf.UPPER ? SHAPE_NORTH_UPPER : SHAPE_NORTH_LOWER;
        };
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
                case LOWER -> blockPos;
                case UPPER -> blockPos.below();
            };

            level.destroyBlock(blockToDestroy, false);
        }

        super.playerWillDestroy(level, blockPos, blockState, player);
    }


    @Override
    public void setPlacedBy(Level level, BlockPos blockPos, BlockState blockState, @Nullable LivingEntity livingEntity, ItemStack itemStack) {
        level.setBlock(blockPos.below(), this.defaultBlockState().setValue(FACING, blockState.getValue(FACING)).setValue(HALF, DoubleBlockHalf.LOWER), 3);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        if (context.getLevel().getBlockState(context.getClickedPos().below()).canBeReplaced(context)) {
            return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(HALF, DoubleBlockHalf.UPPER);
        } else {
            return null;
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, HALF);
    }
}
