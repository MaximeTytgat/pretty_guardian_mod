package com.max.prettyguardian.blocks.custom.food;

import com.max.prettyguardian.particle.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public class BasePie extends Block {
    public Boolean CRITICAL;
    public static final int MAX_BITES = 3;
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final IntegerProperty BITES = IntegerProperty.create("bites", 0, 3);
    protected static  VoxelShape[] SHAPE_BY_BITE_NORTH = new VoxelShape[]{
            Block.box(2, 0, 2, 14, 4.5, 14),
            Shapes.or(
                    Block.box(2, 0, 2, 14, 4.5, 8),
                    Block.box(2, 0, 8, 8, 4.5, 14)
            ),
            Block.box(2, 0, 2, 14, 4.5, 8),
            Block.box(8, 0, 2, 14, 4.5, 8)
    };
    protected static  VoxelShape[] SHAPE_BY_BITE_EAST = new VoxelShape[]{
            Block.box(2, 0, 2, 14, 4.5, 14),
            Shapes.or(
                    Block.box(2, 0, 2, 14, 4.5, 8),
                    Block.box(8, 0, 8, 14, 4.5, 14)
            ),
            Block.box(8, 0, 2, 14, 4.5, 14),
            Block.box(8, 0, 8, 14, 4.5, 14)
    };
    protected static  VoxelShape[] SHAPE_BY_BITE_SOUTH = new VoxelShape[]{
            Block.box(2, 0, 2, 14, 4.5, 14),
            Shapes.or(
                    Block.box(8, 0, 2, 14, 4.5, 14),
                    Block.box(2, 0, 8, 8, 4.5, 14)
            ),
            Block.box(2, 0, 8, 14, 4.5, 14),
            Block.box(2, 0, 8, 8, 4.5, 14)
    };
    protected static  VoxelShape[] SHAPE_BY_BITE_WEST = new VoxelShape[]{
            Block.box(2, 0, 2, 14, 4.5, 14),
            Shapes.or(
                    Block.box(2, 0, 8, 14, 4.5, 14),
                    Block.box(2, 0, 2, 8, 4.5, 8)
            ),
            Block.box(2, 0, 2, 8, 4.5, 14),
            Block.box(2, 0, 2, 8, 4.5, 8)
    };

    public BasePie(Properties properties, boolean critical) {
        super(properties);
        this.CRITICAL = critical;
    }

    public BasePie(Properties properties) {
        this(properties, false);
    }


    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, @NotNull CollisionContext context) {
        Direction direction = blockState.getValue(FACING);

        return switch (direction) {
            case NORTH -> SHAPE_BY_BITE_NORTH[blockState.getValue(BITES)];
            case EAST -> SHAPE_BY_BITE_EAST[blockState.getValue(BITES)];
            case SOUTH -> SHAPE_BY_BITE_SOUTH[blockState.getValue(BITES)];
            case WEST -> SHAPE_BY_BITE_WEST[blockState.getValue(BITES)];
            default -> SHAPE_BY_BITE_NORTH[blockState.getValue(BITES)];
        };
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection()).setValue(BITES, 0);
    }


    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        ItemStack itemstack = player.getItemInHand(interactionHand);
        Item item = itemstack.getItem();
        if (itemstack.is(ItemTags.CANDLES) && blockState.getValue(BITES) == 0) {
            Block block = Block.byItem(item);
            if (block instanceof CandleBlock) {
                if (!player.isCreative()) {
                    itemstack.shrink(1);
                }

                level.playSound((Player)null, blockPos, SoundEvents.CAKE_ADD_CANDLE, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.setBlockAndUpdate(blockPos, CandleCakeBlock.byCandle(block));
                level.gameEvent(player, GameEvent.BLOCK_CHANGE, blockPos);
                player.awardStat(Stats.ITEM_USED.get(item));
                return InteractionResult.SUCCESS;
            }
        }

        if (level.isClientSide) {
            if (eat(level, blockPos, blockState, player).consumesAction()) {
                return InteractionResult.SUCCESS;
            }

            if (itemstack.isEmpty()) {
                return InteractionResult.CONSUME;
            }
        }

        return eat(level, blockPos, blockState, player);
    }

    protected static InteractionResult eat(LevelAccessor levelAccessor, BlockPos blockPos, BlockState blockState, Player player) {
        if (!player.canEat(false)) {
            return InteractionResult.PASS;
        } else {
            player.awardStat(Stats.EAT_CAKE_SLICE);
            player.getFoodData().eat(2, 0.1F);
            int i = blockState.getValue(BITES);
            levelAccessor.gameEvent(player, GameEvent.EAT, blockPos);
            if (i < MAX_BITES) {
                levelAccessor.setBlock(blockPos, blockState.setValue(BITES, Integer.valueOf(i + 1)), 3);
            } else {
                levelAccessor.removeBlock(blockPos, false);
                levelAccessor.gameEvent(player, GameEvent.BLOCK_DESTROY, blockPos);
            }

            return InteractionResult.SUCCESS;
        }
    }

    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState1, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos blockPos1) {
        return direction == Direction.DOWN && !blockState.canSurvive(levelAccessor, blockPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(blockState, direction, blockState1, levelAccessor, blockPos, blockPos1);
    }

    public boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        return !levelReader.getBlockState(blockPos.below()).isAir();
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BITES, FACING);
    }

    public int getAnalogOutputSignal(BlockState blockState, Level level, BlockPos blockPos) {
        return getOutputSignal(blockState.getValue(BITES));
    }

    public static int getOutputSignal(int p_152747_) {
        return (7 - p_152747_) * 2;
    }

    public boolean hasAnalogOutputSignal(BlockState blockState) {
        return true;
    }

    public boolean isPathfindable(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, PathComputationType pathComputationType) {
        return false;
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource rand) {
        float chance = 0.4F;

        if (rand.nextFloat() > chance && this.CRITICAL && blockState.getValue(BITES) < 2) {
            level.addParticle(ModParticles.PINK_CRIT_PARTICLES.get(),
                    blockPos.getX() + (0.15 + (0.85 - 0.15) * rand.nextDouble()),
                    blockPos.getY() + 0.1,
                    blockPos.getZ() + (0.15 + (0.85 - 0.15) * rand.nextDouble()),
                    0, 0.08d, 0);
        }

        super.animateTick(blockState, level, blockPos, rand);
    }
}
