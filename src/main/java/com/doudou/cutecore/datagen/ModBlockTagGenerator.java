package com.doudou.cutecore.datagen;

import com.doudou.cutecore.CuteCore;
import com.doudou.cutecore.blocks.CuteCoreBlock;
import com.doudou.cutecore.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, CuteCore.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider p_256380_) {
        this.tag(ModTags.Blocks.CAKE).add(
                CuteCoreBlock.CHOCOLATE_CAKE.get(),
                CuteCoreBlock.THREE_CHOCO_CAKE.get(),
                CuteCoreBlock.THREE_STRAWBERRY_CAKE.get(),
                CuteCoreBlock.THREE_STRAWBERRY_CHOCO_CAKE.get()
        );


        this.tag(ModTags.Blocks.NEEDS_PINK_SAPPHIRE_TOOL).add(
                CuteCoreBlock.PINK_SAPPHIRE_BLOCK.get()
        );

    }
}
