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
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class JapBonzaiBlock extends Block {
    public static final EnumProperty<Direction> FACING = DirectionalBlock.FACING;
    public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;

    public static final VoxelShape SHAPE_NORTH_LOWER = Shapes.or(Block.box(1, 0, 1, 15, 10, 15), Block.box(6, 10, 6, 10, 16, 10), Block.box(7, 14, 1, 9, 16, 6), Block.box(7, 15, 10, 9, 16, 15));
    public static final VoxelShape SHAPE_EAST_LOWER = Shapes.or(Block.box(1, 0, 1, 15, 10, 15), Block.box(6, 10, 6, 10, 16, 10), Block.box(10, 14, 7, 15, 16, 9), Block.box(1, 15, 7, 6, 16, 9));
    public static final VoxelShape SHAPE_SOUTH_LOWER = Shapes.or(Block.box(1, 0, 1, 15, 10, 15), Block.box(6, 10, 6, 10, 16, 10), Block.box(7, 14, 10, 9, 16, 15), Block.box(7, 15, 1, 9, 16, 6));
    public static final VoxelShape SHAPE_WEST_LOWER = Shapes.or(Block.box(1, 0, 1, 15, 10, 15), Block.box(6, 10, 6, 10, 16, 10), Block.box(1, 14, 7, 6, 16, 9), Block.box(10, 15, 7, 15, 16, 9));
    public static final VoxelShape SHAPE_NORTH_UPPER = Shapes.or(Block.box(6, 0, 6, 10, 3, 10), Block.box(7, 0, 1, 9, 2, 3), Block.box(4, 2, -2, 12, 7, 6), Block.box(-3, 5, 3, 7, 10, 13), Block.box(4, 3, 10, 12, 8, 18), Block.box(7, 8, 2, 19, 13, 14), Block.box(7, 1, 13, 9, 3, 15), Block.box(12, 1, 7, 14, 8, 9), Block.box(10, 1, 7, 12, 3, 9), Block.box(3, 0, 7, 6, 2, 9), Block.box(1, 0, 7, 3, 5, 9));
    public static final VoxelShape SHAPE_EAST_UPPER = Shapes.or(Block.box(6, 0, 6, 10, 3, 10), Block.box(13, 0, 7, 15, 2, 9), Block.box(10, 2, 4, 18, 7, 12), Block.box(3, 5, -3, 13, 10, 7), Block.box(-2, 3, 4, 6, 8, 12), Block.box(2, 8, 7, 14, 13, 19), Block.box(1, 1, 7, 3, 3, 9), Block.box(7, 1, 12, 9, 8, 14), Block.box(7, 1, 10, 9, 3, 12), Block.box(7, 0, 3, 9, 2, 6), Block.box(7, 0, 1, 9, 5, 3));
    public static final VoxelShape SHAPE_SOUTH_UPPER = Shapes.or(Block.box(6, 0, 6, 10, 3, 10), Block.box(7, 0, 13, 9, 2, 15), Block.box(4, 2, 10, 12, 7, 18), Block.box(9, 5, 3, 19, 10, 13), Block.box(4, 3, -2, 12, 8, 6), Block.box(-3, 8, 2, 9, 13, 14), Block.box(7, 1, 1, 9, 3, 3), Block.box(2, 1, 7, 4, 8, 9), Block.box(4, 1, 7, 6, 3, 9), Block.box(10, 0, 7, 13, 2, 9), Block.box(13, 0, 7, 15, 5, 9));
    public static final VoxelShape SHAPE_WEST_UPPER = Shapes.or(Block.box(6, 0, 6, 10, 3, 10), Block.box(1, 0, 7, 3, 2, 9), Block.box(-2, 2, 4, 6, 7, 12), Block.box(3, 5, 9, 13, 10, 19), Block.box(10, 3, 4, 18, 8, 12), Block.box(2, 8, -3, 14, 13, 9), Block.box(13, 1, 7, 15, 3, 9), Block.box(7, 1, 2, 9, 8, 4), Block.box(7, 1, 4, 9, 3, 6), Block.box(7, 0, 10, 9, 2, 13), Block.box(7, 0, 13, 9, 5, 15));

    public JapBonzaiBlock(Properties properties) {
        super(properties);
        this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(HALF, DoubleBlockHalf.LOWER);
    }
    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        DoubleBlockHalf half = blockState.getValue(HALF);
        return switch (blockState.getValue(FACING)) {
            case NORTH -> half == DoubleBlockHalf.UPPER ? SHAPE_NORTH_UPPER : SHAPE_NORTH_LOWER;
            case SOUTH -> half == DoubleBlockHalf.UPPER ? SHAPE_SOUTH_UPPER : SHAPE_SOUTH_LOWER;
            case EAST -> half == DoubleBlockHalf.UPPER ? SHAPE_EAST_UPPER : SHAPE_EAST_LOWER;
            case WEST -> half == DoubleBlockHalf.UPPER ? SHAPE_WEST_UPPER : SHAPE_WEST_LOWER;
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
    public BlockState playerWillDestroy(Level level, BlockPos blockPos, BlockState blockState, Player player) {
        // prevent creative drops
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
    public void setPlacedBy(Level p_49847_, BlockPos p_49848_, BlockState p_49849_, @Nullable LivingEntity p_49850_, ItemStack p_49851_) {
        p_49847_.setBlock(p_49848_.above(), this.defaultBlockState().setValue(FACING, p_49849_.getValue(FACING)).setValue(HALF, DoubleBlockHalf.UPPER), 3);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        if (context.getLevel().getBlockState(context.getClickedPos().above()).canBeReplaced(context)) {
            return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(HALF, DoubleBlockHalf.LOWER);
        } else {
            return null;
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, HALF);
    }
}
