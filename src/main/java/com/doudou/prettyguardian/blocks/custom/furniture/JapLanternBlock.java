package com.doudou.prettyguardian.blocks.custom.furniture;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CandleCakeBlock;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.RedstoneTorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.stream.Stream;

public class JapLanternBlock extends LanternBlock {

    private static final VoxelShape AABB = Stream.of(
            Block.box(5, 11, 5, 11, 12, 11),
            Block.box(4, 10, 4, 12, 11, 12),
            Block.box(3, 2, 3, 13, 10, 13),
            Block.box(4, 1, 4, 12, 2, 12),
            Block.box(5, 0, 5, 11, 1, 11)
    ).reduce(Shapes::or).get();

    private static final VoxelShape HANGING_AABB = Stream.of(
            Block.box(5, 11, 5, 11, 12, 11),
            Block.box(4, 10, 4, 12, 11, 12),
            Block.box(3, 2, 3, 13, 10, 13),
            Block.box(4, 1, 4, 12, 2, 12),
            Block.box(5, 0, 5, 11, 1, 11),
            Stream.of(
                Block.box(0, 15, 9.8, 0, 17, 12.8),
                Block.box(-1.5, 13, 11.25, 1.5, 16, 11.25),
                Block.box(0, 12, 9.8, 0, 14, 12.8)
            ).reduce(Shapes::or).get()
    ).reduce(Shapes::or).get();

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
