package com.max.prettyguardian.blocks.custom.furniture;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.stream.Stream;

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
        this.registerDefaultState(this.stateDefinition.any().setValue(POWERED, Boolean.valueOf(false)).setValue(HANGING, Boolean.valueOf(false)).setValue(LIT, Boolean.valueOf(false)).setValue(WATERLOGGED, Boolean.valueOf(false)));
    }

    @Override
    public VoxelShape getShape(BlockState p_153474_, BlockGetter p_153475_, BlockPos p_153476_, CollisionContext p_153477_) {
        return p_153474_.getValue(HANGING) ? HANGING_AABB : AABB;
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext p_153467_) {
        FluidState fluidstate = p_153467_.getLevel().getFluidState(p_153467_.getClickedPos());

        for(Direction direction : p_153467_.getNearestLookingDirections()) {
            if (direction.getAxis() == Direction.Axis.Y) {
                BlockState blockstate = this.defaultBlockState().setValue(HANGING, Boolean.valueOf(direction == Direction.UP));

                if (p_153467_.getLevel().hasNeighborSignal(p_153467_.getClickedPos())) {
                    blockstate = blockstate.setValue(LIT, Boolean.valueOf(true)).setValue(POWERED, Boolean.valueOf(true));
                }

                if (blockstate.canSurvive(p_153467_.getLevel(), p_153467_.getClickedPos())) {
                    return blockstate.setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
                }
            }
        }

        return null;
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

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_153490_) {
        p_153490_.add(HANGING, LIT, POWERED, WATERLOGGED);
    }

    @Override
    public InteractionResult use(BlockState p_57540_, Level p_57541_, BlockPos p_57542_, Player p_57543_, InteractionHand p_57544_, BlockHitResult p_57545_) {
        p_57540_ = p_57540_.cycle(LIT);
        p_57541_.setBlock(p_57542_, p_57540_, 2);
        if (p_57540_.getValue(WATERLOGGED)) {
            p_57541_.scheduleTick(p_57542_, Fluids.WATER, Fluids.WATER.getTickDelay(p_57541_));
        }
        return InteractionResult.sidedSuccess(p_57541_.isClientSide);
    }
}
