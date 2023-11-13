package com.doudou.cutecore.blocks;

import com.doudou.cutecore.CuteCore;
import com.doudou.cutecore.item.CuteCoreItem;
import com.doudou.cutecore.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Random;

public class StrawberryCropBlock extends CropBlock {
    public static final int MAX_AGE = 4;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_4;
    public StrawberryCropBlock(Properties p_52247_) {
        super(p_52247_);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return CuteCoreItem.STRAWBERRY_SEEDS.get();
    }

    @Override
    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }


    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if (blockState.getValue(AGE) == MAX_AGE) {
            Random random = new Random();
            int bonus_seed = random.nextInt(5) + 1;

            int rd = random.nextInt(2);

            CuteCore.LOGGER.info("test: " + rd);

            level.setBlockAndUpdate(blockPos, blockState.setValue(AGE, 0));
            level.addDestroyBlockEffect(blockPos, blockState);

            Block.popResource(level, blockPos, new ItemStack(CuteCoreItem.STRAWBERRY.get()));
            if (bonus_seed == 1) {
                Block.popResource(level, blockPos, new ItemStack(CuteCoreItem.STRAWBERRY_SEEDS.get()));
            }

            return InteractionResult.SUCCESS;
        }
        return super.use(blockState, level, blockPos, player, interactionHand, blockHitResult);
    }
}
