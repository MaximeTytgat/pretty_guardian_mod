package com.max.prettyguardian.datagen;

import com.max.prettyguardian.PrettyGuardian;
import com.max.prettyguardian.item.PrettyGuardianItem;
import com.max.prettyguardian.loot.AddItemModifier;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;

public class ModGlobalLootModifiersProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifiersProvider(PackOutput output, String modid) {
        super(output, PrettyGuardian.MOD_ID);
    }

    @Override
    protected void start() {
        add("raw_squid_from_squid", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/squid")).build()
        }, PrettyGuardianItem.RAW_SQUID.get()));

        add("ruby_template_upgrade_from_ancient_city", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/ancient_city")).build(),
                LootItemRandomChanceCondition.randomChance(0.6f).build()
        }, PrettyGuardianItem.RUBY_TEMPLATE_UPGRADE.get()));
    }
}
