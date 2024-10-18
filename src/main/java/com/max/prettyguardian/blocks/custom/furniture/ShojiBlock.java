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
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ShojiBlock extends Block {
    public static final EnumProperty<Direction> FACING = DirectionalBlock.FACING;
    private static final VoxelShape SHAPE_NORTH = Block.box(0, 0, 7, 16, 16, 9);
    private static final VoxelShape SHAPE_SOUTH = Block.box(0, 0, 7, 16, 16, 9);
    private static final VoxelShape SHAPE_EAST = Block.box(7, 0, 0, 9, 16, 16);
    private static final VoxelShape SHAPE_WEST = Block.box(7, 0, 0, 9, 16, 16);

    public ShojiBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public @NotNull VoxelShape getShape(
            BlockState state,
            @NotNull BlockGetter world,
            @NotNull BlockPos pos,
            @NotNull CollisionContext context
    ) {
        Direction direction = state.getValue(FACING);
        return switch (direction) {
            case SOUTH -> SHAPE_SOUTH;
            case EAST -> SHAPE_EAST;
            case WEST -> SHAPE_WEST;
            default -> SHAPE_NORTH;
        };
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState()
                .setValue(FACING, context.getHorizontalDirection().getOpposite());
    }
}
