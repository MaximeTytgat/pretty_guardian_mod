package com.max.prettyguardian.blocks.custom.furniture;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class JapLanternBlock extends LanternBlock {
    private static final VoxelShape AABB = Shapes.or(
            Block.box(5.000000000000002, 11, 4.999999999999995, 11.000000000000004, 12, 11),
            Block.box(4.000000000000002, 10, 3.9999999999999947, 12, 11, 12.000000000000004),
            Block.box(3.0000000000000018, 2, 2.9999999999999956, 13.000000000000002, 10, 13.000000000000012),
            Block.box(4.000000000000002, 1, 3.9999999999999947, 12, 2, 12.000000000000004),
            Block.box(5.000000000000002, 0, 4.999999999999995, 11.000000000000004, 1, 11)
    );
    private static final VoxelShape HANGING_AABB = Shapes.or(
            Block.box(8.000000000000002, 15, 6.486291501015234, 8.000000000000002, 17, 9.486291501015234),
            Block.box(6.500000000000001, 13, 7.9362915010152335, 9.500000000000002, 16, 7.9362915010152335),
            Block.box(8.000000000000002, 12, 6.486291501015234, 8.000000000000002, 14, 9.486291501015234),
            Block.box(5.000000000000002, 11, 4.999999999999995, 11.000000000000004, 12, 11),
            Block.box(4.000000000000002, 10, 3.9999999999999947, 12, 11, 12.000000000000004),
            Block.box(3.0000000000000018, 2, 2.9999999999999956, 13.000000000000002, 10, 13.000000000000012),
            Block.box(4.000000000000002, 1, 3.9999999999999947, 12, 2, 12.000000000000004),
            Block.box(5.000000000000002, 0, 4.999999999999995, 11.000000000000004, 1, 11)
    );
    public static final BooleanProperty HANGING = BlockStateProperties.HANGING;
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

    public JapLanternBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(
                this.stateDefinition.any()
                        .setValue(POWERED, Boolean.FALSE)
                        .setValue(HANGING, Boolean.FALSE)
                        .setValue(LIT, Boolean.FALSE)
                        .setValue(WATERLOGGED, Boolean.FALSE)
        );
    }

    @Override
    public @NotNull VoxelShape getShape(
            BlockState blockState,
            @NotNull BlockGetter blockGetter,
            @NotNull BlockPos blockPos,
            @NotNull CollisionContext collisionContext
    ) {
        return Boolean.TRUE.equals(blockState.getValue(HANGING)) ? HANGING_AABB : AABB;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        FluidState fluidstate = blockPlaceContext.getLevel().getFluidState(blockPlaceContext.getClickedPos());

        for(Direction direction : blockPlaceContext.getNearestLookingDirections()) {
            if (direction.getAxis() == Direction.Axis.Y) {
                BlockState blockstate = this.defaultBlockState().setValue(HANGING, direction == Direction.UP);

                if (blockPlaceContext.getLevel().hasNeighborSignal(blockPlaceContext.getClickedPos())) {
                    blockstate = blockstate.setValue(LIT, Boolean.TRUE).setValue(POWERED, Boolean.TRUE);
                }

                if (blockstate.canSurvive(blockPlaceContext.getLevel(), blockPlaceContext.getClickedPos())) {
                    return blockstate.setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
                }
            }
        }

        return super.getStateForPlacement(blockPlaceContext);
    }



    @Override
    public void neighborChanged(
            @NotNull BlockState blockState,
            Level level,
            @NotNull BlockPos blockPos,
            @NotNull Block block,
            @NotNull BlockPos blockPos1,
            boolean b
    ) {
        if (!level.isClientSide) {
            boolean flag = level.hasNeighborSignal(blockPos);
            if (flag != Boolean.TRUE.equals(blockState.getValue(POWERED))) {
                if (Boolean.TRUE.equals(blockState.getValue(LIT)) != flag) {
                    blockState = blockState.setValue(LIT, flag);
                }

                level.setBlock(blockPos, blockState.setValue(POWERED, flag), 2);
                if (Boolean.TRUE.equals(blockState.getValue(WATERLOGGED))) {
                    level.scheduleTick(blockPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
                }
            }

        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> blockBlockStateBuilder) {
        blockBlockStateBuilder.add(HANGING, LIT, POWERED, WATERLOGGED);
    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(
            @NotNull BlockState blockState,
            Level level,
            @NotNull BlockPos blockPos,
            @NotNull Player player,
            @NotNull BlockHitResult blockHitResult
    ) {
        blockState = blockState.cycle(LIT);
        level.setBlock(blockPos, blockState, 2);
        if (Boolean.TRUE.equals(blockState.getValue(WATERLOGGED))) {
            level.scheduleTick(blockPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        return super.useWithoutItem(blockState, level, blockPos, player, blockHitResult);
    }
}
