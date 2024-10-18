package com.max.prettyguardian.blocks.custom.furniture;

import com.max.prettyguardian.util.ModTags;
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
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.ArrayList;

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

    public JapTableBlock(Properties properties) {
        super(properties);
        this.defaultBlockState()
                .setValue(NORTH_ATTACHED, false)
                .setValue(EAST_ATTACHED, false)
                .setValue(SOUTH_ATTACHED, false)
                .setValue(WEST_ATTACHED, false);
    }

    @Override
    public @NotNull VoxelShape getShape(
            BlockState state,
            @NotNull BlockGetter blockGetter,
            @NotNull BlockPos blockPos,
            @NotNull CollisionContext collisionContext
    ) {
        boolean north = state.getValue(NORTH_ATTACHED);
        boolean east = state.getValue(EAST_ATTACHED);
        boolean south = state.getValue(SOUTH_ATTACHED);
        boolean west = state.getValue(WEST_ATTACHED);

        int connections = (north ? 1 : 0) + (east ? 1 : 0) + (south ? 1 : 0) + (west ? 1 : 0);
        if (connections > 2 || north && south || east && west) return TABLE_TOP;

        if (connections > 1) {
            return getShapeForTwoConnections(north, east, south, west);
        } else if (connections == 1) {
            return getShapeForOneConnection(north, east, south, west);
        } else {
            return Shapes.or(TABLE_TOP, LEG_NORTH_WEST, LEG_NORTH_EAST, LEG_SOUTH_WEST, LEG_SOUTH_EAST);
        }
    }

    private static VoxelShape getShapeForTwoConnections(boolean north, boolean east, boolean south, boolean west) {
        ArrayList<VoxelShape> shapes = new ArrayList<>();
        if (north && east) shapes.add(Shapes.or(TABLE_TOP, LEG_SOUTH_WEST));
        if (north && west) shapes.add(Shapes.or(TABLE_TOP, LEG_SOUTH_EAST));
        if (east && south) shapes.add(Shapes.or(TABLE_TOP, LEG_NORTH_WEST));
        if (south && west) shapes.add(Shapes.or(TABLE_TOP, LEG_NORTH_EAST));

        return Shapes.or(TABLE_TOP, shapes.toArray(new VoxelShape[0]));
    }

    private static VoxelShape getShapeForOneConnection(boolean north, boolean east, boolean south, boolean west) {
        ArrayList<VoxelShape> shapes = new ArrayList<>();
        if (north) shapes.add(Shapes.or(TABLE_TOP, LEG_SOUTH_WEST, LEG_SOUTH_EAST));
        if (east) shapes.add(Shapes.or(TABLE_TOP, LEG_NORTH_WEST, LEG_SOUTH_WEST));
        if (south) shapes.add(Shapes.or(TABLE_TOP, LEG_NORTH_WEST, LEG_NORTH_EAST));
        if (west) shapes.add(Shapes.or(TABLE_TOP, LEG_NORTH_EAST, LEG_SOUTH_EAST));

        return Shapes.or(TABLE_TOP, shapes.toArray(new VoxelShape[0]));
    }
    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState state = this.defaultBlockState();
        return getConnections(state, context.getLevel(), context.getClickedPos());
    }

    @Override
    public @NotNull BlockState updateShape(
            @NotNull BlockState state,
            @NotNull Direction direction,
            @NotNull BlockState neighborState,
            @NotNull LevelAccessor level,
            @NotNull BlockPos currentPos,
            @NotNull BlockPos neighborPos
    ) {
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
        return state.getTags().anyMatch(tag -> tag.toString().equals(ModTags.Blocks.JAP_TABLE.toString()));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(NORTH_ATTACHED, EAST_ATTACHED, SOUTH_ATTACHED, WEST_ATTACHED);
    }
}
