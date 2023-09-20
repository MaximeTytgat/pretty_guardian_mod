package com.doudou.cutecore.blocks;

import com.doudou.cutecore.util.CakeBuilder;
import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
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
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class BaseCake extends Block  {
    public static int MAX_BITES = 6;
    public static final IntegerProperty BITES = IntegerProperty.create("bites", 0, MAX_BITES);
    private final int biteCount;
    private CakeBuilder builder;
    protected static final VoxelShape[] BIG_SHAPE_BY_BITE = new VoxelShape[]{ Stream.of( Block.box(1, 0, 1, 15, 8, 15), Block.box(2, 8, 2, 14, 15, 14), Block.box(3, 15, 3, 13, 21, 13) ).reduce(Shapes::or).get(), Stream.of( Block.box(1, 0, 1, 15, 8, 15), Block.box(2, 8, 2, 14, 15, 14), Block.box(5, 15, 3, 13, 21, 13) ).reduce(Shapes::or).get(), Stream.of( Block.box(1, 0, 1, 15, 8, 15), Block.box(2, 8, 2, 14, 15, 14), Block.box(7, 15, 3, 13, 21, 13) ).reduce(Shapes::or).get(), Stream.of( Block.box(1, 0, 1, 15, 8, 15), Block.box(2, 8, 2, 14, 15, 14), Block.box(9, 15, 3, 13, 21, 13) ).reduce(Shapes::or).get(), Stream.of( Block.box(1, 0, 1, 15, 8, 15), Block.box(2, 8, 2, 14, 15, 14), Block.box(11, 15, 3, 13, 21, 13) ).reduce(Shapes::or).get(), Stream.of( Block.box(1, 0, 1, 15, 8, 15), Block.box(2, 8, 2, 14, 15, 14), Block.box(13, 15, 3, 13, 21, 13) ).reduce(Shapes::or).get(), Stream.of( Block.box(1, 0, 1, 15, 8, 15), Block.box(4, 8, 2, 14, 15, 14) ).reduce(Shapes::or).get(), Stream.of( Block.box(1, 0, 1, 15, 8, 15),  Block.box(6, 8, 2, 14, 15, 14) ).reduce(Shapes::or).get(), Stream.of( Block.box(1, 0, 1, 15, 8, 15),  Block.box(8, 8, 2, 14, 15, 14) ).reduce(Shapes::or).get(), Stream.of( Block.box(1, 0, 1, 15, 8, 15),  Block.box(10, 8, 2, 14, 15, 14) ).reduce(Shapes::or).get(), Block.box(1, 0, 1, 15, 8, 15), Block.box(3, 0, 1, 15, 8, 15), Block.box(5, 0, 1, 15, 8, 15), Block.box(7, 0, 1, 15, 8, 15), Block.box(9, 0, 1, 15, 8, 15), Block.box(11, 0, 1, 15, 8, 15),
    };

    protected static  VoxelShape[] SHAPE_BY_BITE = new VoxelShape[]{Block.box(1.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D), Block.box(3.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D), Block.box(5.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D), Block.box(7.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D), Block.box(9.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D), Block.box(11.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D), Block.box(13.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D)};
    private static final Logger LOGGER = LogUtils.getLogger();

    private static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;


    protected BaseCake(Properties properties, int biteCount) {
        super(properties);
        this.biteCount = biteCount;
        if (getBiteCount() > 0) {
            registerDefaultState(stateDefinition.any().setValue(getBites(), 0));
        }
    }
    public BaseCake(CakeBuilder builder, int biteCount) {
        this(builder.getCakeProperties(), biteCount);
        this.builder = builder;


        this.registerDefaultState(this.stateDefinition.any().setValue(BITES, Integer.valueOf(0)));
    }

    public BaseCake(CakeBuilder builder) {
        this(builder.getCakeProperties(), 6);
    }


//    public BaseCake() {
//        super(BlockBehaviour.Properties.of().sound(SoundType.WOOL));
//        if (bigCake) {
//            MAX_BITES = 15;
//            SHAPE_BY_BITE = BIG_SHAPE_BY_BITE;
//        }
//        this.registerDefaultState(this.stateDefinition.any().setValue(BITES, Integer.valueOf(0)));
//    }
//    public BaseCake(Boolean bigCake) {
//        super(BlockBehaviour.Properties.of().sound(SoundType.WOOL));
//        if (bigCake) {
//            MAX_BITES = 15;
//            SHAPE_BY_BITE = BIG_SHAPE_BY_BITE;
//        }
//        this.registerDefaultState(this.stateDefinition.any().setValue(BITES, Integer.valueOf(0)));
////    }
//
//
//    public BaseCake(CakeBuilder cakeBuilder) {
//        super(cakeBuilder.getCakeProperties());
//    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        ItemStack itemStack = player.getItemInHand(interactionHand);
        Item item = itemStack.getItem();
        if (itemStack.is(ItemTags.CANDLES) && blockState.getValue(BITES) == 0) {
            Block block = Block.byItem(item);
            if (block instanceof CandleBlock) {
//                if (!player.isCreative()) {
//                    itemStack.shrink(1);
//                }
//                level.playSound(null, blockPos, SoundEvents.CAKE_ADD_CANDLE, SoundSource.BLOCKS, 1.0F, 1.0F);
//                level.setBlockAndUpdate(blockPos, CandleCakeBlock.byCandle(block));
//                level.gameEvent(player, GameEvent.BLOCK_CHANGE, blockPos);
//                player.awardStat(Stats.ITEM_USED.get(item));
//                return InteractionResult.SUCCESS;
            }
        }

        if (level.isClientSide) {
            if (eat(level, blockPos, blockState, player).consumesAction()) {
                return InteractionResult.SUCCESS;
            }

            if (itemStack.isEmpty()) {
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
                LOGGER.info(String.valueOf(i));
                LOGGER.info(String.valueOf(SHAPE_BY_BITE[blockState.getValue(BITES)]));
                levelAccessor.setBlock(blockPos, blockState.setValue(BITES, i + 1), 3);
            } else {
                levelAccessor.removeBlock(blockPos, false);
                levelAccessor.gameEvent(player, GameEvent.BLOCK_DESTROY, blockPos);
            }

            return InteractionResult.SUCCESS;
        }
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState2, LevelAccessor levelAccessor, BlockPos blockPos1, BlockPos blockPos2) {
        return direction == Direction.DOWN && !blockState.canSurvive(levelAccessor, blockPos1) ? Blocks.AIR.defaultBlockState() : super.updateShape(blockState, direction, blockState2, levelAccessor, blockPos1, blockPos2);
    }

    @Override
    public boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        return levelReader.getBlockState(blockPos.below()).isSolid();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateDefinitionBuilder) {
        stateDefinitionBuilder.add(BITES);
    }

    @Override
    public int getAnalogOutputSignal(BlockState blockState, Level level, BlockPos blockPos) {
        return getOutputSignal(blockState.getValue(BITES));
    }

    public static int getOutputSignal(int bites) {
        return (7 - bites) * 2;
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState blockState) {
        return true;
    }

    @Override
    public boolean isPathfindable(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, PathComputationType pathComputationType) {
        return false;
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return SHAPE_BY_BITE[blockState.getValue(BITES)];
    }

    public int getBiteCount() {
        return biteCount;
    }

    @Nullable
    public IntegerProperty getBites() {
        return BITES;
    }
}
