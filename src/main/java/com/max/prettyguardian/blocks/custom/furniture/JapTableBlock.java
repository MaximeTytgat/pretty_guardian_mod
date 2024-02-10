package com.max.prettyguardian.blocks.custom.furniture;

import com.max.prettyguardian.blocks.PrettyGuardianBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class JapTableBlock extends Block {
    public static final BooleanProperty NORTH_ATTACHED = BlockStateProperties.NORTH;
    public static final BooleanProperty EAST_ATTACHED = BlockStateProperties.EAST;
    public static final BooleanProperty SOUTH_ATTACHED = BlockStateProperties.SOUTH;
    public static final BooleanProperty WEST_ATTACHED = BlockStateProperties.WEST;
    public static final VoxelShape LEG_NORTH_WEST = Block.box(1, 0, 1, 3, 14, 3);
    public static final VoxelShape LEG_NORTH_EAST = Block.box(13, 0, 1, 15, 14, 3);
    public static final VoxelShape LEG_SOUTH_WEST = Block.box(1, 0, 13, 3, 14, 15);
    public static final VoxelShape LEG_SOUTH_EAST = Block.box(13, 0, 13, 15, 14, 15);
    public static final VoxelShape TABLE_TOP = Block.box(0, 14, 0, 16, 16, 16);
    public static final VoxelShape BASE_TABLE = Shapes.or(TABLE_TOP, LEG_NORTH_WEST, LEG_NORTH_EAST, LEG_SOUTH_WEST, LEG_SOUTH_EAST);
    public static final VoxelShape TABLE_CONNECTED_NORTH = Shapes.or(TABLE_TOP, LEG_SOUTH_WEST, LEG_SOUTH_EAST);
    public static final VoxelShape TABLE_CONNECTED_EAST = Shapes.or(TABLE_TOP, LEG_NORTH_WEST, LEG_SOUTH_WEST);
    public static final VoxelShape TABLE_CONNECTED_SOUTH = Shapes.or(TABLE_TOP, LEG_NORTH_WEST, LEG_NORTH_EAST);
    public static final VoxelShape TABLE_CONNECTED_WEST = Shapes.or(TABLE_TOP, LEG_NORTH_EAST, LEG_SOUTH_EAST);
    public static final VoxelShape TABLE_CONNECTED_NORTH_EAST = Shapes.or(TABLE_TOP, LEG_SOUTH_WEST);
    public static final VoxelShape TABLE_CONNECTED_NORTH_WEST = Shapes.or(TABLE_TOP, LEG_SOUTH_EAST);
    public static final VoxelShape TABLE_CONNECTED_SOUTH_EAST = Shapes.or(TABLE_TOP, LEG_NORTH_WEST);
    public static final VoxelShape TABLE_CONNECTED_SOUTH_WEST = Shapes.or(TABLE_TOP, LEG_NORTH_EAST);



    public JapTableBlock(Properties properties) {
        super(properties);
        this.defaultBlockState()
                .setValue(NORTH_ATTACHED, false)
                .setValue(EAST_ATTACHED, false)
                .setValue(SOUTH_ATTACHED, false)
                .setValue(WEST_ATTACHED, false);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        if (state.getValue(NORTH_ATTACHED) && state.getValue(EAST_ATTACHED) && state.getValue(SOUTH_ATTACHED) && state.getValue(WEST_ATTACHED)) {
            return TABLE_TOP;
        } else if (state.getValue(NORTH_ATTACHED) && state.getValue(EAST_ATTACHED) && state.getValue(SOUTH_ATTACHED)) {
            return TABLE_TOP;
        } else if (state.getValue(NORTH_ATTACHED) && state.getValue(EAST_ATTACHED) && state.getValue(WEST_ATTACHED)) {
            return TABLE_TOP;
        } else if (state.getValue(NORTH_ATTACHED) && state.getValue(SOUTH_ATTACHED) && state.getValue(WEST_ATTACHED)) {
            return TABLE_TOP;
        } else if (state.getValue(EAST_ATTACHED) && state.getValue(SOUTH_ATTACHED) && state.getValue(WEST_ATTACHED)) {
            return TABLE_TOP;
        } else if (state.getValue(NORTH_ATTACHED) && state.getValue(EAST_ATTACHED)) {
            return TABLE_CONNECTED_NORTH_EAST;
        } else if (state.getValue(NORTH_ATTACHED) && state.getValue(SOUTH_ATTACHED)) {
            return TABLE_TOP;
        } else if (state.getValue(NORTH_ATTACHED) && state.getValue(WEST_ATTACHED)) {
            return TABLE_CONNECTED_NORTH_WEST;
        } else if (state.getValue(EAST_ATTACHED) && state.getValue(SOUTH_ATTACHED)) {
            return TABLE_CONNECTED_SOUTH_EAST;
        } else if (state.getValue(EAST_ATTACHED) && state.getValue(WEST_ATTACHED)) {
            return TABLE_TOP;
        } else if (state.getValue(SOUTH_ATTACHED) && state.getValue(WEST_ATTACHED)) {
            return TABLE_CONNECTED_SOUTH_WEST;
        } else if (state.getValue(NORTH_ATTACHED)) {
            return TABLE_CONNECTED_NORTH;
        } else if (state.getValue(EAST_ATTACHED)) {
            return TABLE_CONNECTED_EAST;
        } else if (state.getValue(SOUTH_ATTACHED)) {
            return TABLE_CONNECTED_SOUTH;
        } else if (state.getValue(WEST_ATTACHED)) {
            return TABLE_CONNECTED_WEST;
        } else {
            return BASE_TABLE;
        }
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState state = this.defaultBlockState();
        return getConnections(state, context.getLevel(), context.getClickedPos());
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos currentPos, BlockPos neighborPos) {
        return getConnections(state, level, currentPos);
    }

    public BlockState getConnections(BlockState state, LevelAccessor level, BlockPos pos) {
        boolean n = validConnection(level.getBlockState(pos.north()));
        boolean e = validConnection(level.getBlockState(pos.east()));
        boolean s = validConnection(level.getBlockState(pos.south()));
        boolean w = validConnection(level.getBlockState(pos.west()));

        return state
                .setValue(NORTH_ATTACHED, n)
                .setValue(EAST_ATTACHED, e)
                .setValue(SOUTH_ATTACHED, s)
                .setValue(WEST_ATTACHED, w);
    }

    public boolean validConnection(BlockState state) {
        return state.is(PrettyGuardianBlock.TABLE_JAPANESE_OAK.get());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(NORTH_ATTACHED, EAST_ATTACHED, SOUTH_ATTACHED, WEST_ATTACHED);
    }
}
