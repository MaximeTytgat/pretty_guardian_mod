package com.doudou.cutecore.blocks.custom.furniture;

import com.doudou.cutecore.blocks.CuteCoreBlock;
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
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class JapTableBlock extends Block {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
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


    public JapTableBlock(Properties properties) {
        super(properties);
        this.defaultBlockState()
                .setValue(FACING, Direction.NORTH)
                .setValue(NORTH_ATTACHED, false)
                .setValue(EAST_ATTACHED, false)
                .setValue(SOUTH_ATTACHED, false)
                .setValue(WEST_ATTACHED, false);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        switch (state.getValue(FACING)) {
            case NORTH:
                return state.getValue(NORTH_ATTACHED) ? TABLE_CONNECTED_NORTH : BASE_TABLE;
            case EAST:
                return state.getValue(EAST_ATTACHED) ? TABLE_CONNECTED_EAST : BASE_TABLE;
            case SOUTH:
                return state.getValue(SOUTH_ATTACHED) ? TABLE_CONNECTED_SOUTH : BASE_TABLE;
            case WEST:
                return state.getValue(WEST_ATTACHED) ? TABLE_CONNECTED_WEST : BASE_TABLE;
            default:
                return BASE_TABLE;
        }
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState state = this.defaultBlockState()
                .setValue(FACING, context.getHorizontalDirection().getOpposite());
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
        return state.is(CuteCoreBlock.TABLE_JAPANESE_OAK.get());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, NORTH_ATTACHED, EAST_ATTACHED, SOUTH_ATTACHED, WEST_ATTACHED);
    }
}
