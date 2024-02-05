package com.doudou.cutecore.datagen.loot;

import com.doudou.cutecore.CuteCore;
import com.doudou.cutecore.blocks.CuteCoreBlock;
import com.doudou.cutecore.blocks.custom.crop.StrawberryCropBlock;
import com.doudou.cutecore.item.CuteCoreItem;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PotatoBlock;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
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
        this.dropSelf(CuteCoreBlock.CHOCOLATE_BLOCK.get());
        this.dropSelf(CuteCoreBlock.MARSHMELLO_BLOCK.get());
        this.dropSelf(CuteCoreBlock.ROASTED_MARSHMELLO_BLOCK.get());
        this.dropSelf(CuteCoreBlock.PISTACHIO_SAPLING.get());
        this.dropSelf(CuteCoreBlock.LEMON_SAPLING.get());
        this.dropSelf(CuteCoreBlock.MYMELODY_PLUSH.get());
        this.dropSelf(CuteCoreBlock.KUROMI_PLUSH.get());
        this.dropSelf(CuteCoreBlock.CAVALIER_PLUSH.get());
        this.dropSelf(CuteCoreBlock.TEDDYBEAR_PLUSH.get());
        this.dropSelf(CuteCoreBlock.RABBIT_PLUSH.get());
        this.dropSelf(CuteCoreBlock.COW_PLUSH.get());

        this.dropSelf(CuteCoreBlock.SHOJI_BLOSSOM.get());
        this.dropSelf(CuteCoreBlock.SHOJI_BLOSSOM_BOTTOM.get());
        this.dropSelf(CuteCoreBlock.SHOJI_BLOSSOM_SMALL.get());
        this.dropSelf(CuteCoreBlock.SHOJI_BLOSSOM_SMALL_BOTTOM.get());

        this.dropSelf(CuteCoreBlock.SHOJI_CHERRY.get());
        this.dropSelf(CuteCoreBlock.SHOJI_CHERRY_BOTTOM.get());
        this.dropSelf(CuteCoreBlock.SHOJI_CHERRY_SMALL.get());
        this.dropSelf(CuteCoreBlock.SHOJI_CHERRY_SMALL_BOTTOM.get());

        this.dropSelf(CuteCoreBlock.SHOJI_BIRCH.get());
        this.dropSelf(CuteCoreBlock.SHOJI_BIRCH_BOTTOM.get());
        this.dropSelf(CuteCoreBlock.SHOJI_BIRCH_SMALL.get());
        this.dropSelf(CuteCoreBlock.SHOJI_BIRCH_SMALL_BOTTOM.get());

        this.dropSelf(CuteCoreBlock.LANTERN_JAPANESE.get());
        this.dropSelf(CuteCoreBlock.LANTERN_JAPANESE_SAKURA.get());
        this.dropSelf(CuteCoreBlock.LANTERN_JAPANESE_FESTIVAL.get());

        this.dropSelf(CuteCoreBlock.LAMP_JAPANESE_OAK.get());
        this.dropSelf(CuteCoreBlock.LAMP_JAPANESE_BIRCH.get());
        this.dropSelf(CuteCoreBlock.LAMP_JAPANESE_SPRUCE.get());
        this.dropSelf(CuteCoreBlock.LAMP_JAPANESE_JUNGLE.get());
        this.dropSelf(CuteCoreBlock.LAMP_JAPANESE_ACACIA.get());
        this.dropSelf(CuteCoreBlock.LAMP_JAPANESE_DARK_OAK.get());
        this.dropSelf(CuteCoreBlock.LAMP_JAPANESE_MANGROVE.get());
        this.dropSelf(CuteCoreBlock.LAMP_JAPANESE_CHERRY.get());

        this.dropSelf(CuteCoreBlock.DOOR_SHOJI_BLOSSOM.get());
        this.dropSelf(CuteCoreBlock.DOOR_SHOJI_BLOSSOM_SMALL.get());
        this.dropSelf(CuteCoreBlock.DOOR_SHOJI_BIRCH.get());
        this.dropSelf(CuteCoreBlock.DOOR_SHOJI_BIRCH_SMALL.get());
        this.dropSelf(CuteCoreBlock.DOOR_SHOJI_CHERRY.get());
        this.dropSelf(CuteCoreBlock.DOOR_SHOJI_CHERRY_SMALL.get());

        this.dropSelf(CuteCoreBlock.TABLE_JAPANESE_OAK.get());

        this.dropSelf(CuteCoreBlock.SEA_SHELL.get());

        this.dropSelf(CuteCoreBlock.GEM_POLISHING_STATION.get());


        this.add(CuteCoreBlock.PISTACHIO_LEAVES_CROP.get(), block -> createLeavesDrops(block, CuteCoreBlock.PISTACHIO_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        this.add(CuteCoreBlock.LEMON_LEAVES_CROP.get(), block -> createLeavesDrops(block, CuteCoreBlock.LEMON_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));


        this.createLapisOreDrops(CuteCoreBlock.THREE_STRAWBERRY_CAKE.get());

        
        this.add(CuteCoreBlock.PINK_SAPPHIRE_ORE.get(), createOreDrop(CuteCoreBlock.PINK_SAPPHIRE_ORE.get(), CuteCoreItem.PINK_SAPPHIRE.get()));
        this.add(CuteCoreBlock.DEEPSLATE_PINK_SAPPHIRE_ORE.get(), createOreDrop(CuteCoreBlock.DEEPSLATE_PINK_SAPPHIRE_ORE.get(), CuteCoreItem.PINK_SAPPHIRE.get()));

        this.add(CuteCoreBlock.RUBY_ORE.get(), createOreDrop(CuteCoreBlock.RUBY_ORE.get(), CuteCoreItem.RAW_RUBY.get()));
        this.add(CuteCoreBlock.DEEPSLATE_RUBY_ORE.get(), createOreDrop(CuteCoreBlock.DEEPSLATE_RUBY_ORE.get(), CuteCoreItem.RAW_RUBY.get()));

        LootItemCondition.Builder lootitemcondition$builder = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(CuteCoreBlock.STRAWBERRY_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(StrawberryCropBlock.AGE, 4));

        this.add(CuteCoreBlock.STRAWBERRY_CROP.get(),
                this.applyExplosionDecay(CuteCoreBlock.STRAWBERRY_CROP.get(),
                        LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(CuteCoreItem.STRAWBERRY_SEEDS.get())))
                                .withPool(LootPool.lootPool().when(lootitemcondition$builder).add(LootItem.lootTableItem(CuteCoreItem.STRAWBERRY.get())
                                        .apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 2))))
                                .withPool(LootPool.lootPool().when(lootitemcondition$builder).add(LootItem.lootTableItem(CuteCoreItem.STRAWBERRY_SEEDS.get())
                                        .when(LootItemRandomChanceCondition.randomChance(0.1F))))));

        LootItemCondition.Builder lootitemcondition$builder2 = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(CuteCoreBlock.MINT_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(StrawberryCropBlock.AGE, 4));

        this.add(CuteCoreBlock.MINT_CROP.get(), createCropDrops(CuteCoreBlock.MINT_CROP.get(), CuteCoreItem.MINT.get(),
                CuteCoreItem.MINT_SEEDS.get(), lootitemcondition$builder2));

        LootItemCondition.Builder lootitemcondition$builder3 = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(CuteCoreBlock.VANILLA_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(StrawberryCropBlock.AGE, 7));

        this.add(CuteCoreBlock.VANILLA_CROP.get(), createCropDrops(CuteCoreBlock.VANILLA_CROP.get(), CuteCoreItem.VANILLA.get(),
                CuteCoreItem.VANILLA_SEEDS.get(), lootitemcondition$builder3));

        this.add(CuteCoreBlock.PICNIC_BASKET.get(), this::createShulkerBoxDrop);
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
