package com.max.prettyguardian.worldgen;

import com.max.prettyguardian.PrettyGuardian;
import com.max.prettyguardian.blocks.PrettyGuardianBlock;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> PINK_SAPPHIRE_ORE_PLACED_KEY = registerKey("pink_sapphire_ore_placed");
    public static final ResourceKey<PlacedFeature> RUBY_ORE_PLACED_KEY = registerKey("ruby_ore_placed");
    public static final ResourceKey<PlacedFeature> PISTACHIO_PLACED_KEY = registerKey("pistachio_placed");
    public static final ResourceKey<PlacedFeature> LEMON_PLACED_KEY = registerKey("lemon_placed");
    public static final ResourceKey<PlacedFeature> SEA_SHELL_PLACED_KEY = registerKey("sea_shell_placed");

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);


        register(context, PINK_SAPPHIRE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_PINK_SAPPHIRE_ORE_KEY),
                ModOrePlacement.commonOrePlacement(12, // vein size
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80))));

        register(context, RUBY_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_RUBY_ORE_KEY),
                ModOrePlacement.commonOrePlacement(12, // vein size
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80))));

        register(context, PISTACHIO_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.PISTACHIO_KEY),
                VegetationPlacements.treePlacement(RarityFilter.onAverageOnceEvery(20),
                        PrettyGuardianBlock.PISTACHIO_SAPLING.get()));

        register(context, LEMON_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.LEMON_KEY),
                VegetationPlacements.treePlacement(RarityFilter.onAverageOnceEvery(20),
                        PrettyGuardianBlock.LEMON_SAPLING.get()));

        register(context, SEA_SHELL_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.SEA_SHELL_KEY),
                    List.of(new PlacementModifier[]{RarityFilter.onAverageOnceEvery(20), PlacementUtils.HEIGHTMAP_WORLD_SURFACE , BiomeFilter.biome()}));

    }



    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(PrettyGuardian.MOD_ID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}