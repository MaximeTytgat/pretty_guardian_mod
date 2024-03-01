package com.max.prettyguardian.blocks.custom.furniture;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class JapScreenBlock extends Block {
    public static final EnumProperty<DoubleBlockHalf> HALF;
    public static final EnumProperty<ParaventPart> PART;
    public static final EnumProperty<Direction> FACING;
    private static final VoxelShape SHAPE = Block.box(0, 0, 7, 16, 16, 9);
    private static final VoxelShape SHAPE_EAST = Block.box(7, 0, 0, 9, 16, 16);
    public JapScreenBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(HALF, DoubleBlockHalf.LOWER).setValue(PART, ParaventPart.RIGHT).setValue(FACING, Direction.NORTH));
    }

    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return p_60555_.getValue(FACING) == Direction.EAST || p_60555_.getValue(FACING) == Direction.WEST ? SHAPE_EAST : SHAPE;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos pos = context.getClickedPos();
        Direction direction = context.getHorizontalDirection();
        BlockPos pos1 = pos.relative(direction.getClockWise());
        Level level = context.getLevel();
        if (
                pos.getY() < level.getMaxBuildHeight() - 1
                && level.getBlockState(pos.above()).canBeReplaced(context)
                && level.getBlockState(pos1).canBeReplaced(context)
                && level.getBlockState(pos1.above()).canBeReplaced(context)
        ) {

            return this.defaultBlockState().setValue(FACING, direction).setValue(HALF, DoubleBlockHalf.LOWER).setValue(PART, ParaventPart.LEFT);
        }

        return null;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos blockPos, BlockState blockState, @Nullable LivingEntity livingEntity, ItemStack itemStack) {
        super.setPlacedBy(level, blockPos, blockState, livingEntity, itemStack);
        if (!level.isClientSide) {
            level.setBlock(blockPos.above(), blockState.setValue(HALF, DoubleBlockHalf.UPPER).setValue(PART, ParaventPart.LEFT), 3);
            BlockPos blockPos1 = blockPos.relative(blockState.getValue(FACING).getClockWise());
            level.setBlock(blockPos1, blockState.setValue(HALF, DoubleBlockHalf.LOWER).setValue(PART, ParaventPart.RIGHT), 3);
            level.setBlock(blockPos1.above(), blockState.setValue(HALF, DoubleBlockHalf.UPPER).setValue(PART, ParaventPart.RIGHT), 3);
        }
    }

    @Override
    public BlockState updateShape(BlockState currentState, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        Block facingBlock = facingState.getBlock();

        if (currentState.getValue(HALF) == DoubleBlockHalf.LOWER && currentState.getValue(PART) == ParaventPart.LEFT) {
            if (facing == Direction.UP && facingBlock != this) {
                return Blocks.AIR.defaultBlockState();
            } else if (facing == currentState.getValue(FACING).getClockWise() && facingBlock != this) {
                return Blocks.AIR.defaultBlockState();
            }
        } else if (currentState.getValue(HALF) == DoubleBlockHalf.UPPER && currentState.getValue(PART) == ParaventPart.LEFT) {
            if (facing == Direction.DOWN && facingBlock != this) {
                return Blocks.AIR.defaultBlockState();
            } else if (facing == currentState.getValue(FACING).getClockWise() && facingBlock != this) {
                return Blocks.AIR.defaultBlockState();
            }
        } else if (currentState.getValue(HALF) == DoubleBlockHalf.LOWER && currentState.getValue(PART) == ParaventPart.RIGHT) {
            if (facing == Direction.UP && facingBlock != this) {
                return Blocks.AIR.defaultBlockState();
            } else if (facing == currentState.getValue(FACING).getCounterClockWise() && facingBlock != this) {
                return Blocks.AIR.defaultBlockState();
            }
        } else if (currentState.getValue(HALF) == DoubleBlockHalf.UPPER && currentState.getValue(PART) == ParaventPart.RIGHT) {
            if (facing == Direction.DOWN && facingBlock != this) {
                return Blocks.AIR.defaultBlockState();
            } else if (facing == currentState.getValue(FACING).getCounterClockWise() && facingBlock != this) {
                return Blocks.AIR.defaultBlockState();
            }
        }

        return super.updateShape(currentState, facing, facingState, level, currentPos, facingPos);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(PART, HALF, FACING);
    }

    @Override
    public void playerWillDestroy(Level level, BlockPos blockPos, BlockState blockState, Player player) {
        // prevent creative drops
        if (player.isCreative()) {
            DoubleBlockHalf half = blockState.getValue(HALF);
            BlockPos blockToDestroy = switch (half) {
                case LOWER -> blockPos;
                case UPPER -> blockPos.below();
            };

            ParaventPart part = blockState.getValue(PART);
            blockToDestroy = switch (part) {
                case LEFT -> blockToDestroy;
                case RIGHT -> blockToDestroy.relative(blockState.getValue(FACING).getCounterClockWise());
            };

            level.destroyBlock(blockToDestroy, false);
        }

        super.playerWillDestroy(level, blockPos, blockState, player);
    }

    public enum ParaventPart implements StringRepresentable {
        RIGHT("right"),
        LEFT("left");

        private final String name;

        private ParaventPart(String name) {
            this.name = name;
        }

        public String toString() {
            return this.name;
        }

        public String getSerializedName() {
            return this.name;
        }
    }

    static {
        FACING = DirectionalBlock.FACING;
        PART = EnumProperty.create("part", ParaventPart.class);
        HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
    }
}
