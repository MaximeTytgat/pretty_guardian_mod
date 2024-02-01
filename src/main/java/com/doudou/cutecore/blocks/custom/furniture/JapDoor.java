package com.doudou.cutecore.blocks.custom.furniture;

import com.doudou.cutecore.CuteCore;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class JapDoor extends DoorBlock {
    protected static final VoxelShape SOUTH_AABB = Block.box(0, 0, 7, 16, 16, 9);
    protected static final VoxelShape SOUTH_AABB_OPEN = Block.box(13, 0, 7, 29, 16, 9);
    protected static final VoxelShape NORTH_AABB = Block.box(0, 0, 7, 16, 16, 9);
    protected static final VoxelShape NORTH_AABB_OPEN = Block.box(-13, 0, 7, 3, 16, 9);
    protected static final VoxelShape WEST_AABB = Block.box(7, 0, 0, 9, 16, 16);
    protected static final VoxelShape WEST_AABB_OPEN = Block.box(7, 0, -13, 9, 16, 3);
    protected static final VoxelShape EAST_AABB = Block.box(7, 0, 0, 9, 16, 16);
    protected static final VoxelShape EAST_AABB_OPEN = Block.box(7, 0, 13, 9, 16, 29);

    public JapDoor(Properties properties, BlockSetType type) {
        super(properties, type);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        Direction direction = blockState.getValue(FACING);
        boolean flag = !blockState.getValue(OPEN);
        boolean flag1 = blockState.getValue(HINGE) == DoorHingeSide.RIGHT;
        switch (direction) {
            case EAST:
            default:
                return flag ? EAST_AABB : (flag1 ? EAST_AABB_OPEN : WEST_AABB_OPEN);
            case SOUTH:
                return flag ? SOUTH_AABB : (flag1 ? NORTH_AABB_OPEN : SOUTH_AABB_OPEN);
            case WEST:
                return flag ? WEST_AABB : (flag1 ? WEST_AABB_OPEN : EAST_AABB_OPEN);
            case NORTH:
                return flag ? NORTH_AABB : (flag1 ? SOUTH_AABB_OPEN : NORTH_AABB_OPEN);
        }
    }
}
