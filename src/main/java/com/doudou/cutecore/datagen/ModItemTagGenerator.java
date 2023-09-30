package com.doudou.cutecore.datagen;

import com.doudou.cutecore.CuteCore;
import com.doudou.cutecore.item.CuteCoreItem;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_, CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, CuteCore.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider holderLookupProvider) {
        this.tag(ItemTags.TRIMMABLE_ARMOR)
                .add(
                        CuteCoreItem.PINK_SAPPHIRE_HELMET.get(),
                        CuteCoreItem.PINK_SAPPHIRE_CHESTPLATE.get(),
                        CuteCoreItem.PINK_SAPPHIRE_LEGGINGS.get(),
                        CuteCoreItem.PINK_SAPPHIRE_BOOTS.get()
//                        CuteCoreItem.RUBY_HELMET.get(),
//                        CuteCoreItem.RUBY_CHESTPLATE.get(),
//                        CuteCoreItem.RUBY_LEGGINGS.get(),
//                        CuteCoreItem.RUBY_BOOTS.get()
                );

        this.tag(ItemTags.ARROWS)
                .add(CuteCoreItem.CUTE_ARROW.get());
    }
}
