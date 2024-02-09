package com.doudou.prettyguardian.datagen;

import com.doudou.prettyguardian.PrettyGuardian;
import com.doudou.prettyguardian.blocks.PrettyGuardianBlock;
import com.doudou.prettyguardian.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, PrettyGuardian.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider p_256380_) {
        this.tag(ModTags.Blocks.CAKE).add(
                PrettyGuardianBlock.CHOCOLATE_CAKE.get(),
                PrettyGuardianBlock.THREE_CHOCO_CAKE.get(),
                PrettyGuardianBlock.THREE_STRAWBERRY_CAKE.get(),
                PrettyGuardianBlock.THREE_STRAWBERRY_CHOCO_CAKE.get()
        );


        this.tag(ModTags.Blocks.NEEDS_PINK_SAPPHIRE_TOOL).add(
                PrettyGuardianBlock.PINK_SAPPHIRE_BLOCK.get()
        );

    }
}
