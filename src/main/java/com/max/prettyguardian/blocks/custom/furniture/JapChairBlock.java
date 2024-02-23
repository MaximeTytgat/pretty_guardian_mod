package com.max.prettyguardian.blocks.custom.furniture;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class JapChairBlock extends Block {
    public static final EnumProperty<Direction> FACING = DirectionalBlock.FACING;

    private static final VoxelShape SHAPE_NORTH = Shapes.or(
            Block.box(2, 0, 2, 4, 6, 4),
            Block.box(2, 0, 12, 4, 6, 14),
            Block.box(12, 0, 2, 14, 6, 4),
            Block.box(12, 0, 12, 14, 6, 14),
            Block.box(2, 6, 2, 14, 7, 14),
            Block.box(3, 7, 3, 13, 8, 13),
            Block.box(3, 5, 3, 13, 6, 13),
            Block.box(12, 7, 2, 14, 16, 4),
            Block.box(4, 12, 2.5, 12, 15, 3.5),
            Block.box(2, 7, 2, 4, 16, 4),
            Block.box(2, 7, 13, 3, 11, 14),
            Block.box(2, 11, 4, 3, 12, 14),
            Block.box(13, 7, 13, 14, 11, 14),
            Block.box(13, 11, 4, 14, 12, 14)
    );

    private static final VoxelShape SHAPE_EAST = Shapes.or(
            Block.box(2, 0, 12, 4, 6, 14),
            Block.box(12, 0, 12, 14, 6, 14),
            Block.box(2, 0, 2, 4, 6, 4),
            Block.box(12, 0, 2, 14, 6, 4),
            Block.box(2, 6, 2, 14, 7, 14),
            Block.box(3, 7, 3, 13, 8, 13),
            Block.box(3, 5, 3, 13, 6, 13),
            Block.box(2, 7, 2, 4, 16, 4),
            Block.box(2.5, 12, 4, 3.5, 15, 12),
            Block.box(2, 7, 12, 4, 16, 14),
            Block.box(13, 7, 13, 14, 11, 14),
            Block.box(4, 11, 13, 14, 12, 14),
            Block.box(13, 7, 2, 14, 11, 3),
            Block.box(4, 11, 2, 14, 12, 3)
    );

    private static final VoxelShape SHAPE_WEST = Shapes.or(
            Block.box(12, 0, 2, 14, 6, 4),
            Block.box(2, 0, 2, 4, 6, 4),
            Block.box(12, 0, 12, 14, 6, 14),
            Block.box(2, 0, 12, 4, 6, 14),
            Block.box(2, 6, 2, 14, 7, 14),
            Block.box(3, 7, 3, 13, 8, 13),
            Block.box(3, 5, 3, 13, 6, 13),
            Block.box(12, 7, 12, 14, 16, 14),
            Block.box(12.5, 12, 4, 13.5, 15, 12),
            Block.box(12, 7, 2, 14, 16, 4),
            Block.box(2, 7, 2, 3, 11, 3),
            Block.box(2, 11, 2, 12, 12, 3),
            Block.box(2, 7, 13, 3, 11, 14),
            Block.box(2, 11, 13, 12, 12, 14)
    );

    private static final VoxelShape SHAPE_SOUTH = Shapes.or(
            Block.box(12, 0, 12, 14, 6, 14),
            Block.box(12, 0, 2, 14, 6, 4),
            Block.box(2, 0, 12, 4, 6, 14),
            Block.box(2, 0, 2, 4, 6, 4),
            Block.box(2, 6, 2, 14, 7, 14),
            Block.box(3, 7, 3, 13, 8, 13),
            Block.box(3, 5, 3, 13, 6, 13),
            Block.box(2, 7, 12, 4, 16, 14),
            Block.box(4, 12, 12.5, 12, 15, 13.5),
            Block.box(12, 7, 12, 14, 16, 14),
            Block.box(13, 7, 2, 14, 11, 3),
            Block.box(13, 11, 2, 14, 12, 12),
            Block.box(2, 7, 2, 3, 11, 3),
            Block.box(2, 11, 2, 3, 12, 12)
    );

    public JapChairBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        switch (p_60555_.getValue(FACING)) {
            case NORTH:
                return SHAPE_SOUTH;
            case SOUTH:
                return SHAPE_NORTH;
            case EAST:
                return SHAPE_EAST;
            case WEST:
                return SHAPE_WEST;
            default:
                return SHAPE_NORTH;
        }
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

}
