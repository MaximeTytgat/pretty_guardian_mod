package com.max.prettyguardian.blocks.custom.plush;

import com.max.prettyguardian.blocks.PrettyGuardianBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

public class RandomPlushBoxBlock extends Block {
    public RandomPlushBoxBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void onRemove(BlockState blockState, Level level, BlockPos blockPos, BlockState blockState1, boolean p_60519_) {
        Block[] plushBlocks = {
                PrettyGuardianBlock.MYMELODY_PLUSH.get(),
                PrettyGuardianBlock.KUROMI_PLUSH.get(),
                PrettyGuardianBlock.CAVALIER_PLUSH.get(),
                PrettyGuardianBlock.TEDDYBEAR_PLUSH.get(),
                PrettyGuardianBlock.RABBIT_PLUSH.get(),
                PrettyGuardianBlock.COW_PLUSH.get(),
                PrettyGuardianBlock.CINNAMOROLL_PLUSH.get(),
                PrettyGuardianBlock.BABYYODA_PLUSH.get()
        };

        Random random = new Random();
        int randomIndex = random.nextInt(plushBlocks.length);
        Block randomPlushBlock = plushBlocks[randomIndex];
        SimpleContainer inventory = new SimpleContainer(1);
        inventory.setItem(0, new ItemStack(randomPlushBlock.asItem()));
        Containers.dropContents(level, blockPos, inventory);

        super.onRemove(blockState, level, blockPos, blockState1, p_60519_);
    }
}
