package com.max.prettyguardian.item;

import com.max.prettyguardian.PrettyGuardian;
import com.max.prettyguardian.util.ModTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class ModToolTiers {
    public static final Tier SPACE_SWORD = TierSortingRegistry.registerTier(
            new ForgeTier(2, 2500, 5f, 2f, 25,
                    ModTags.Items.SPACE_SWORD, () -> Ingredient.of(PrettyGuardianItem.PINK_SAPPHIRE.get())),
            new ResourceLocation(PrettyGuardian.MOD_ID, "space_sword"), List.of(Tiers.NETHERITE), List.of()
    );

    public static final Tier PINK_SAPPHIRE = TierSortingRegistry.registerTier(
            new ForgeTier(5, 1500, 8.0F, 3.0F, 25,
                    ModTags.Blocks.NEEDS_PINK_SAPPHIRE_TOOL, () -> Ingredient.of(PrettyGuardianItem.PINK_SAPPHIRE.get())),
            new ResourceLocation(PrettyGuardian.MOD_ID, "pink_sapphire"), List.of(Tiers.NETHERITE), List.of()
    );

    public static final Tier RUBY = TierSortingRegistry.registerTier(
            new ForgeTier(6, 3000, 9.0F, 4.0F, 65,
                    ModTags.Blocks.NEEDS_PINK_SAPPHIRE_TOOL, () -> Ingredient.of(PrettyGuardianItem.PINK_SAPPHIRE.get())),
            new ResourceLocation(PrettyGuardian.MOD_ID, "ruby"), List.of(Tiers.NETHERITE), List.of()
    );
}
