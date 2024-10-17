package com.max.prettyguardian.blocks.custom.food;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Stream;

public class BaseThreeCake extends Block  {

    public static final IntegerProperty BITES = IntegerProperty.create("bites", 0, 15);
    public static final int MAX_BITES = 15;
    protected static final VoxelShape[] SHAPE_BY_BITE = new VoxelShape[]{ Stream.of( Block.box(1, 0, 1, 15, 8, 15), Block.box(2, 8, 2, 14, 15, 14), Block.box(3, 15, 3, 13, 21, 13) ).reduce(Shapes::or).get(), Stream.of( Block.box(1, 0, 1, 15, 8, 15), Block.box(2, 8, 2, 14, 15, 14), Block.box(5, 15, 3, 13, 21, 13) ).reduce(Shapes::or).get(), Stream.of( Block.box(1, 0, 1, 15, 8, 15), Block.box(2, 8, 2, 14, 15, 14), Block.box(7, 15, 3, 13, 21, 13) ).reduce(Shapes::or).get(), Stream.of( Block.box(1, 0, 1, 15, 8, 15), Block.box(2, 8, 2, 14, 15, 14), Block.box(9, 15, 3, 13, 21, 13) ).reduce(Shapes::or).get(), Stream.of( Block.box(1, 0, 1, 15, 8, 15), Block.box(2, 8, 2, 14, 15, 14), Block.box(11, 15, 3, 13, 21, 13) ).reduce(Shapes::or).get(), Stream.of( Block.box(1, 0, 1, 15, 8, 15), Block.box(2, 8, 2, 14, 15, 14), Block.box(13, 15, 3, 13, 21, 13) ).reduce(Shapes::or).get(), Stream.of( Block.box(1, 0, 1, 15, 8, 15), Block.box(4, 8, 2, 14, 15, 14) ).reduce(Shapes::or).get(), Stream.of( Block.box(1, 0, 1, 15, 8, 15),  Block.box(6, 8, 2, 14, 15, 14) ).reduce(Shapes::or).get(), Stream.of( Block.box(1, 0, 1, 15, 8, 15),  Block.box(8, 8, 2, 14, 15, 14) ).reduce(Shapes::or).get(), Stream.of( Block.box(1, 0, 1, 15, 8, 15),  Block.box(10, 8, 2, 14, 15, 14) ).reduce(Shapes::or).get(), Block.box(1, 0, 1, 15, 8, 15), Block.box(3, 0, 1, 15, 8, 15), Block.box(5, 0, 1, 15, 8, 15), Block.box(7, 0, 1, 15, 8, 15), Block.box(9, 0, 1, 15, 8, 15), Block.box(11, 0, 1, 15, 8, 15),};

    public BaseThreeCake(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(BITES, 0));
    }

    @Override
    public @NotNull VoxelShape getShape(
            BlockState state,
            @NotNull BlockGetter getter,
            @NotNull BlockPos pos,
            @NotNull CollisionContext context
    ) {
        return SHAPE_BY_BITE[state.getValue(BITES)];
    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(
            @NotNull BlockState blockState,
            @NotNull Level level,
            @NotNull BlockPos blockPos,
            @NotNull Player player,
            @NotNull BlockHitResult blockHitResult
    ) {
        if (level.isClientSide) {
            if (eat(level, blockPos, blockState, player).consumesAction()) {
                return InteractionResult.SUCCESS;
            }

            if (player.getItemInHand(InteractionHand.MAIN_HAND).isEmpty()) {
                return InteractionResult.CONSUME;
            }
        }

        return eat(level, blockPos, blockState, player);
    }

    @Override
    protected @NotNull ItemInteractionResult useItemOn(
            ItemStack itemStack,
            @NotNull BlockState blockState,
            @NotNull Level level,
            @NotNull BlockPos blockPos,
            @NotNull Player player,
            @NotNull InteractionHand interactionHand,
            @NotNull BlockHitResult blockHitResult
    ) {
        Item item = itemStack.getItem();
        if (itemStack.is(ItemTags.CANDLES) && blockState.getValue(BITES) == 0) {
            Block block = Block.byItem(item);
            if (block instanceof CandleBlock candleBlock) {
                if (!player.isCreative()) {
                    itemStack.shrink(1);
                }

                level.playSound(null, blockPos, SoundEvents.CAKE_ADD_CANDLE, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.setBlockAndUpdate(blockPos, CandleCakeBlock.byCandle(candleBlock));
                level.gameEvent(player, GameEvent.BLOCK_CHANGE, blockPos);
                player.awardStat(Stats.ITEM_USED.get(item));
                return ItemInteractionResult.SUCCESS;
            }
        }

        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    protected static InteractionResult eat(LevelAccessor accessor, BlockPos blockPos, BlockState blockState, Player player) {
        if (!player.canEat(false)) {
            return InteractionResult.PASS;
        } else {
            player.awardStat(Stats.EAT_CAKE_SLICE);
            player.getFoodData().eat(2, 0.1F);
            int i = blockState.getValue(BITES);
            accessor.gameEvent(player, GameEvent.EAT, blockPos);
            if (i < MAX_BITES) {
                accessor.setBlock(blockPos, blockState.setValue(BITES, i + 1), 3);
            } else {
                accessor.removeBlock(blockPos, false);
                accessor.gameEvent(player, GameEvent.BLOCK_DESTROY, blockPos);
            }

            return InteractionResult.SUCCESS;
        }
    }

    @Override
    public @NotNull BlockState updateShape(
            @NotNull BlockState state,
            @NotNull Direction dir,
            @NotNull BlockState state2,
            @NotNull LevelAccessor accessor,
            @NotNull BlockPos pos1,
            @NotNull BlockPos pos2
    ) {
        return dir == Direction.DOWN && !state.canSurvive(accessor, pos1) ?
                Blocks.AIR.defaultBlockState() :
                super.updateShape(state, dir, state2, accessor, pos1, pos2);
    }

    @Override
    public boolean canSurvive(@NotNull BlockState state, LevelReader reader, BlockPos pos) {
        return !reader.getBlockState(pos.below()).isAir();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder builder) {
        builder.add(BITES);
    }

    @Override
    public int getAnalogOutputSignal(BlockState state, @NotNull Level level, @NotNull BlockPos pos) {
        return getOutputSignal(state.getValue(BITES));
    }

    public static int getOutputSignal(int i) {
        return (7 - i) * 2;
    }

    @Override
    public boolean hasAnalogOutputSignal(@NotNull BlockState state) {
        return true;
    }
}
