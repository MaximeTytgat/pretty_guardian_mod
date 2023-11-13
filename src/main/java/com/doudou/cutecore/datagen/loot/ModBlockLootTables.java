package com.doudou.cutecore.datagen.loot;

import com.doudou.cutecore.blocks.CuteCoreBlock;
import com.doudou.cutecore.blocks.StrawberryCropBlock;
import com.doudou.cutecore.item.CuteCoreItem;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {


    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(CuteCoreBlock.STRAWBERRY_CROP.get());
        this.dropSelf(CuteCoreBlock.PINK_SAPPHIRE_BLOCK.get());
        this.dropSelf(CuteCoreBlock.RUBY_BLOCK.get());
        this.dropSelf(CuteCoreBlock.PICNIC_BASKET.get());
        this.dropSelf(CuteCoreBlock.CHOCOLATE_BLOCK.get());
        this.dropSelf(CuteCoreBlock.MARSHMELLO_BLOCK.get());
        this.dropSelf(CuteCoreBlock.ROASTED_MARSHMELLO_BLOCK.get());
        this.createLapisOreDrops(CuteCoreBlock.THREE_STRAWBERRY_CAKE.get());


        this.add(CuteCoreBlock.PINK_SAPPHIRE_ORE.get(), createOreDrop(CuteCoreBlock.PINK_SAPPHIRE_ORE.get(), CuteCoreItem.PINK_SAPPHIRE.get()));
        this.add(CuteCoreBlock.DEEPSLATE_PINK_SAPPHIRE_ORE.get(), createOreDrop(CuteCoreBlock.DEEPSLATE_PINK_SAPPHIRE_ORE.get(), CuteCoreItem.PINK_SAPPHIRE.get()));

        this.add(CuteCoreBlock.RUBY_ORE.get(), createOreDrop(CuteCoreBlock.RUBY_ORE.get(), CuteCoreItem.RUBY.get()));
        this.add(CuteCoreBlock.DEEPSLATE_RUBY_ORE.get(), createOreDrop(CuteCoreBlock.DEEPSLATE_RUBY_ORE.get(), CuteCoreItem.RUBY.get()));

        LootItemCondition.Builder lootitemcondition$builder = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(CuteCoreBlock.STRAWBERRY_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(StrawberryCropBlock.AGE, 4));

        this.add(CuteCoreBlock.STRAWBERRY_CROP.get(), createCropDrops(CuteCoreBlock.STRAWBERRY_CROP.get(), CuteCoreItem.STRAWBERRY.get(),
                CuteCoreItem.STRAWBERRY_SEEDS.get(), lootitemcondition$builder));
    }

    @Override
    protected LootTable.Builder createLapisOreDrops(Block p_251511_) {
        return createSilkTouchDispatchTable(p_251511_,
                this.applyExplosionDecay(p_251511_,
                        LootItem.lootTableItem(CuteCoreItem.STRAWBERRY_SEEDS.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 9.0F)))
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return CuteCoreBlock.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
