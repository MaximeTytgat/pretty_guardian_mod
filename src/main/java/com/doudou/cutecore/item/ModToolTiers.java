package com.doudou.cutecore.item;

import com.doudou.cutecore.CuteCore;
import com.doudou.cutecore.util.ModTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class ModToolTiers {
    public static final Tier PINK_SAPPHIRE = TierSortingRegistry.registerTier(
            new ForgeTier(5, 1500, 5f, 4f, 25,
                    ModTags.Blocks.NEEDS_PINK_SAPPHIRE_TOOL, () -> Ingredient.of(CuteCoreItem.PINK_SAPPHIRE.get())),
            new ResourceLocation(CuteCore.MOD_ID, "pink_sapphire"), List.of(Tiers.NETHERITE), List.of()
    );

    public static final Tier RUBY = TierSortingRegistry.registerTier(
            new ForgeTier(6, 3000, 7f, 8f, 65,
                    ModTags.Blocks.NEEDS_PINK_SAPPHIRE_TOOL, () -> Ingredient.of(CuteCoreItem.PINK_SAPPHIRE.get())),
            new ResourceLocation(CuteCore.MOD_ID, "ruby"), List.of(Tiers.NETHERITE), List.of()
    );
}
