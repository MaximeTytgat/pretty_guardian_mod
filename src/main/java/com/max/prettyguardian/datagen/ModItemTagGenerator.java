package com.max.prettyguardian.datagen;

import com.max.prettyguardian.PrettyGuardian;
import com.max.prettyguardian.item.PrettyGuardianItem;
import com.max.prettyguardian.util.ModTags;
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
        super(p_275343_, p_275729_, p_275322_, PrettyGuardian.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider holderLookupProvider) {
        this.tag(ItemTags.TRIMMABLE_ARMOR)
                .add(
                        PrettyGuardianItem.PINK_SAPPHIRE_HELMET.get(),
                        PrettyGuardianItem.PINK_SAPPHIRE_CHESTPLATE.get(),
                        PrettyGuardianItem.PINK_SAPPHIRE_LEGGINGS.get(),
                        PrettyGuardianItem.PINK_SAPPHIRE_BOOTS.get(),
                        PrettyGuardianItem.RUBY_HELMET.get(),
                        PrettyGuardianItem.RUBY_CHESTPLATE.get(),
                        PrettyGuardianItem.RUBY_LEGGINGS.get(),
                        PrettyGuardianItem.RUBY_BOOTS.get()
                );

//        this.tag(ItemTags.ARROWS)
//                .add(PrettyGuardianItem.HEART_ARROW.get());

        this.tag(ModTags.Items.HEART_ARROWS).add(PrettyGuardianItem.HEART_ARROW.get());

        this.tag(ItemTags.MUSIC_DISCS)
                .add(
                        PrettyGuardianItem.SAILORMOON_OST_MUSIC_DISC.get(),
                        PrettyGuardianItem.SAILORMOON_MOONPRIDE_MUSIC_DISC.get(),
                        PrettyGuardianItem.LOFI_MUSIC_DISC.get(),
                        PrettyGuardianItem.TAVERN_MUSIC_DISC.get(),
                        PrettyGuardianItem.JAPANESE_FLUTE_MUSIC_DISC.get()
                );

        this.tag(ItemTags.CREEPER_DROP_MUSIC_DISCS)
                .add(
                        PrettyGuardianItem.SAILORMOON_OST_MUSIC_DISC.get(),
                        PrettyGuardianItem.SAILORMOON_MOONPRIDE_MUSIC_DISC.get(),
                        PrettyGuardianItem.LOFI_MUSIC_DISC.get(),
                        PrettyGuardianItem.TAVERN_MUSIC_DISC.get(),
                        PrettyGuardianItem.JAPANESE_FLUTE_MUSIC_DISC.get()
                );
    }
}