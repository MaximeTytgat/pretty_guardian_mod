package com.max.prettyguardian.blocks.custom.furniture;

import net.minecraft.core.BlockPos;
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
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class JapLampBlock extends LanternBlock {
    private static final VoxelShape AABB = Block.box(4, 0, 4, 12, 15, 12);
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

    public JapLampBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(
                this.stateDefinition.any()
                        .setValue(HANGING, Boolean.FALSE)
                        .setValue(POWERED, Boolean.FALSE)
                        .setValue(LIT, Boolean.FALSE)
                        .setValue(WATERLOGGED, Boolean.FALSE)
        );
    }

    @Override
    public @NotNull VoxelShape getShape(
            @NotNull BlockState blockState,
            @NotNull BlockGetter blockGetter,
            @NotNull BlockPos blockPos,
            @NotNull CollisionContext collisionContext
    ) {
        return AABB;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        BlockState blockstate = this.defaultBlockState();
        FluidState fluidstate = blockPlaceContext.getLevel().getFluidState(blockPlaceContext.getClickedPos());

        if (blockPlaceContext.getLevel().hasNeighborSignal(blockPlaceContext.getClickedPos())) {
            blockstate = blockstate.setValue(LIT, Boolean.TRUE).setValue(POWERED, Boolean.TRUE);
        }

        return blockstate.setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER).setValue(HANGING, Boolean.FALSE);
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
        blockBlockStateBuilder.add(LIT, POWERED, HANGING, WATERLOGGED);
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
