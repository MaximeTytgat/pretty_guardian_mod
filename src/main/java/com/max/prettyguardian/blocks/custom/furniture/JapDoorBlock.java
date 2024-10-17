package com.max.prettyguardian.blocks.custom.furniture;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class JapDoorBlock extends DoorBlock {
    protected static final VoxelShape SOUTH_AABB = Block.box(0, 0, 7, 16, 16, 9);
    protected static final VoxelShape SOUTH_AABB_OPEN = Block.box(13, 0, 7, 29, 16, 9);
    protected static final VoxelShape NORTH_AABB = Block.box(0, 0, 7, 16, 16, 9);
    protected static final VoxelShape NORTH_AABB_OPEN = Block.box(-13, 0, 7, 3, 16, 9);
    protected static final VoxelShape WEST_AABB = Block.box(7, 0, 0, 9, 16, 16);
    protected static final VoxelShape WEST_AABB_OPEN = Block.box(7, 0, -13, 9, 16, 3);
    protected static final VoxelShape EAST_AABB = Block.box(7, 0, 0, 9, 16, 16);
    protected static final VoxelShape EAST_AABB_OPEN = Block.box(7, 0, 13, 9, 16, 29);

    public JapDoorBlock(Properties properties, BlockSetType type) {
        super(type, properties);
    }

    @Override
    public @NotNull VoxelShape getShape(
            BlockState blockState,
            @NotNull BlockGetter blockGetter,
            @NotNull BlockPos blockPos,
            @NotNull CollisionContext collisionContext
    ) {
        Direction direction = blockState.getValue(FACING);
        boolean open = blockState.getValue(OPEN);
        boolean hingeRight = blockState.getValue(HINGE) == DoorHingeSide.RIGHT;
        VoxelShape hingeShape = getHingeShape(direction, hingeRight);
        return switch (direction) {
            case SOUTH -> open ? hingeShape: SOUTH_AABB;
            case WEST -> open ? hingeShape: WEST_AABB;
            case NORTH -> open ? hingeShape : NORTH_AABB;
            default -> open ? hingeShape: EAST_AABB;
        };
    }

    private static VoxelShape getHingeShape(Direction direction, boolean hingeRight) {
        return switch (direction) {
            case SOUTH -> hingeRight ? NORTH_AABB_OPEN : SOUTH_AABB_OPEN;
            case WEST -> hingeRight ? WEST_AABB_OPEN : EAST_AABB_OPEN;
            case NORTH -> hingeRight ? SOUTH_AABB_OPEN : NORTH_AABB_OPEN;
            default -> hingeRight ? EAST_AABB_OPEN : WEST_AABB_OPEN;
        };
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

}
