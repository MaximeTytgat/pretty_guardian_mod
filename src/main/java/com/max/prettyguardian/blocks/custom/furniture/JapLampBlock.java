package com.max.prettyguardian.blocks.custom.furniture;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
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

import javax.annotation.Nullable;

public class JapLampBlock extends LanternBlock {

    private static final VoxelShape AABB = Block.box(4, 0, 4, 12, 15, 12);

    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;


    public JapLampBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(HANGING, Boolean.valueOf(false)).setValue(POWERED, Boolean.valueOf(false)).setValue(LIT, Boolean.valueOf(false)).setValue(WATERLOGGED, Boolean.valueOf(false)));
    }

    @Override
    public VoxelShape getShape(BlockState p_153474_, BlockGetter p_153475_, BlockPos p_153476_, CollisionContext p_153477_) {
        return AABB;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext p_57533_) {
        BlockState blockstate = this.defaultBlockState();
        FluidState fluidstate = p_57533_.getLevel().getFluidState(p_57533_.getClickedPos());

        if (p_57533_.getLevel().hasNeighborSignal(p_57533_.getClickedPos())) {
            blockstate = blockstate.setValue(LIT, Boolean.valueOf(true)).setValue(POWERED, Boolean.valueOf(true));
        }

        return blockstate.setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER)).setValue(HANGING, Boolean.valueOf(false));
    }

    public void neighborChanged(BlockState blockState, Level level, BlockPos blockPos, Block block, BlockPos blockPos1, boolean b) {
        if (!level.isClientSide) {
            boolean flag = level.hasNeighborSignal(blockPos);
            if (flag != blockState.getValue(POWERED)) {
                if (blockState.getValue(LIT) != flag) {
                    blockState = blockState.setValue(LIT, Boolean.valueOf(flag));
                }

                level.setBlock(blockPos, blockState.setValue(POWERED, Boolean.valueOf(flag)), 2);
                if (blockState.getValue(WATERLOGGED)) {
                    level.scheduleTick(blockPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
                }
            }

        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_153490_) {
        p_153490_.add(LIT, POWERED, HANGING, WATERLOGGED);
    }

    public InteractionResult use(BlockState p_57540_, Level p_57541_, BlockPos p_57542_, Player p_57543_, InteractionHand p_57544_, BlockHitResult p_57545_) {
        p_57540_ = p_57540_.cycle(LIT);
        p_57541_.setBlock(p_57542_, p_57540_, 2);
        if (p_57540_.getValue(WATERLOGGED)) {
            p_57541_.scheduleTick(p_57542_, Fluids.WATER, Fluids.WATER.getTickDelay(p_57541_));
        }
        return InteractionResult.sidedSuccess(p_57541_.isClientSide);
    }
}
