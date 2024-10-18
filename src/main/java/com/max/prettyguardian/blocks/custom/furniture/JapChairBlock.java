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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class JapChairBlock extends Block {
    public static final EnumProperty<Direction> FACING = DirectionalBlock.FACING;
    private static final VoxelShape SHAPE_NORTH = Shapes.or(
            Block.box(2, 8, 2, 4, 19, 4),
            Block.box(5, 17, 2.5, 7, 18, 3.5),
            Block.box(7, 16.5, 2.5, 9, 17.5, 3.5),
            Block.box(9, 17, 2.5, 11, 18, 3.5),
            Block.box(4, 14, 2.5, 5, 17, 3.5),
            Block.box(11, 14, 2.5, 12, 17, 3.5),
            Block.box(5, 13, 2.5, 6, 14, 3.5),
            Block.box(10, 13, 2.5, 11, 14, 3.5),
            Block.box(6, 12, 2.5, 7, 13, 3.5),
            Block.box(9, 12, 2.5, 10, 13, 3.5),
            Block.box(7, 11.5, 2.5, 9, 12.5, 3.5),
            Block.box(12, 8, 2, 14, 19, 4),
            Block.box(3, 8, 3, 13, 9.5, 13),
            Block.box(2, 7, 2, 14, 8, 14),
            Block.box(3, 6, 3, 13, 7, 13),
            Block.box(12, 0, 12, 14, 7, 14),
            Block.box(12, 0, 2, 14, 7, 4),
            Block.box(2, 0, 12, 4, 7, 14),
            Block.box(2, 0, 2, 4, 7, 4)
    );
    private static final VoxelShape SHAPE_EAST = Shapes.or(
            Block.box(2, 8, 12, 4, 19, 14),
            Block.box(2.5, 17, 9, 3.5, 18, 11),
            Block.box(2.5, 16.5, 7, 3.5, 17.5, 9),
            Block.box(2.5, 17, 5, 3.5, 18, 7),
            Block.box(2.5, 14, 11, 3.5, 17, 12),
            Block.box(2.5, 14, 4, 3.5, 17, 5),
            Block.box(2.5, 13, 10, 3.5, 14, 11),
            Block.box(2.5, 13, 5, 3.5, 14, 6),
            Block.box(2.5, 12, 9, 3.5, 13, 10),
            Block.box(2.5, 12, 6, 3.5, 13, 7),
            Block.box(2.5, 11.5, 7, 3.5, 12.5, 9),
            Block.box(2, 8, 2, 4, 19, 4),
            Block.box(3, 8, 3, 13, 9.5, 13),
            Block.box(2, 7, 2, 14, 8, 14),
            Block.box(3, 6, 3, 13, 7, 13),
            Block.box(12, 0, 2, 14, 7, 4),
            Block.box(2, 0, 2, 4, 7, 4),
            Block.box(12, 0, 12, 14, 7, 14),
            Block.box(2, 0, 12, 4, 7, 14)
    );
    private static final VoxelShape SHAPE_WEST = Shapes.or(
            Block.box(12, 8, 2, 14, 19, 4),
            Block.box(12.5, 17, 5, 13.5, 18, 7),
            Block.box(12.5, 16.5, 7, 13.5, 17.5, 9),
            Block.box(12.5, 17, 9, 13.5, 18, 11),
            Block.box(12.5, 14, 4, 13.5, 17, 5),
            Block.box(12.5, 14, 11, 13.5, 17, 12),
            Block.box(12.5, 13, 5, 13.5, 14, 6),
            Block.box(12.5, 13, 10, 13.5, 14, 11),
            Block.box(12.5, 12, 6, 13.5, 13, 7),
            Block.box(12.5, 12, 9, 13.5, 13, 10),
            Block.box(12.5, 11.5, 7, 13.5, 12.5, 9),
            Block.box(12, 8, 12, 14, 19, 14),
            Block.box(3, 8, 3, 13, 9.5, 13),
            Block.box(2, 7, 2, 14, 8, 14),
            Block.box(3, 6, 3, 13, 7, 13),
            Block.box(2, 0, 12, 4, 7, 14),
            Block.box(12, 0, 12, 14, 7, 14),
            Block.box(2, 0, 2, 4, 7, 4),
            Block.box(12, 0, 2, 14, 7, 4)
    );
    private static final VoxelShape SHAPE_SOUTH = Shapes.or(
            Block.box(12, 8, 12, 14, 19, 14),
            Block.box(9, 17, 12.5, 11, 18, 13.5),
            Block.box(7, 16.5, 12.5, 9, 17.5, 13.5),
            Block.box(5, 17, 12.5, 7, 18, 13.5),
            Block.box(11, 14, 12.5, 12, 17, 13.5),
            Block.box(4, 14, 12.5, 5, 17, 13.5),
            Block.box(10, 13, 12.5, 11, 14, 13.5),
            Block.box(5, 13, 12.5, 6, 14, 13.5),
            Block.box(9, 12, 12.5, 10, 13, 13.5),
            Block.box(6, 12, 12.5, 7, 13, 13.5),
            Block.box(7, 11.5, 12.5, 9, 12.5, 13.5),
            Block.box(2, 8, 12, 4, 19, 14),
            Block.box(3, 8, 3, 13, 9.5, 13),
            Block.box(2, 7, 2, 14, 8, 14),
            Block.box(3, 6, 3, 13, 7, 13),
            Block.box(2, 0, 2, 4, 7, 4),
            Block.box(2, 0, 12, 4, 7, 14),
            Block.box(12, 0, 2, 14, 7, 4),
            Block.box(12, 0, 12, 14, 7, 14)
    );

    public JapChairBlock(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull VoxelShape getShape(
            BlockState blockState,
            @NotNull BlockGetter blockGetter,
            @NotNull BlockPos blockPos,
            @NotNull CollisionContext collisionContext
    ) {
        return switch (blockState.getValue(FACING)) {
            case NORTH -> SHAPE_SOUTH;
            case EAST -> SHAPE_EAST;
            case WEST -> SHAPE_WEST;
            default -> SHAPE_NORTH;
        };
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
