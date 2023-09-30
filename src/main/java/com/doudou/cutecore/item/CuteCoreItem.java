package com.doudou.cutecore.item;

import com.doudou.cutecore.CuteCore;
import com.doudou.cutecore.blocks.CuteCoreBlock;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CuteCoreItem {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, CuteCore.MODID);

    public static final RegistryObject<Item> STYLE_CAKE = ITEMS.register("style_cake", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STRAWBERRY_SEEDS = ITEMS.register("strawberry_seeds", () -> new ItemNameBlockItem(CuteCoreBlock.STRAWBERRY_CROP.get(), new Item.Properties()));
    public static final RegistryObject<Item> STRAWBERRY = ITEMS.register("strawberry", () -> new Item(new Item.Properties().food(ModFoods.STRAWBERRY)));
    public static final RegistryObject<Item> CHOCOLATE_HEART = ITEMS.register("chocolate_heart", () -> new Item(new Item.Properties().food(ModFoods.CHOCOLATE_HEART)));
    public static final RegistryObject<Item> PINK_SAPPHIRE = ITEMS.register("pink_sapphire", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_PINK_SAPPHIRE = ITEMS.register("raw_pink_sapphire", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PINK_SAPPHIRE_SWORD = ITEMS.register("pink_sapphire_sword", () -> new SwordItem(ModToolTiers.PINK_SAPPHIRE, 4, 2,  new Item.Properties()));
    public static final RegistryObject<Item> PINK_SAPPHIRE_PICKAXE = ITEMS.register("pink_sapphire_pickaxe", () -> new PickaxeItem(ModToolTiers.PINK_SAPPHIRE, 4, 2,  new Item.Properties()));
    public static final RegistryObject<Item> PINK_SAPPHIRE_AXE = ITEMS.register("pink_sapphire_axe", () -> new AxeItem(ModToolTiers.PINK_SAPPHIRE, 4, 2,  new Item.Properties()));
    public static final RegistryObject<Item> PINK_SAPPHIRE_SHOVEL = ITEMS.register("pink_sapphire_shovel", () -> new ShovelItem(ModToolTiers.PINK_SAPPHIRE, 4, 2,  new Item.Properties()));
    public static final RegistryObject<Item> PINK_SAPPHIRE_HOE = ITEMS.register("pink_sapphire_hoe", () -> new HoeItem(ModToolTiers.PINK_SAPPHIRE, 4, 2,  new Item.Properties()));

    public static final RegistryObject<Item> PINK_SAPPHIRE_HELMET = ITEMS.register("pink_sapphire_helmet", () -> new ArmorItem(ModArmorMaterials.PINK_SAPPHIRE, ArmorItem.Type.HELMET,  new Item.Properties()));
    public static final RegistryObject<Item> PINK_SAPPHIRE_CHESTPLATE = ITEMS.register("pink_sapphire_chestplate", () -> new ArmorItem(ModArmorMaterials.PINK_SAPPHIRE, ArmorItem.Type.CHESTPLATE,  new Item.Properties()));
    public static final RegistryObject<Item> PINK_SAPPHIRE_LEGGINGS = ITEMS.register("pink_sapphire_leggings", () -> new ArmorItem(ModArmorMaterials.PINK_SAPPHIRE, ArmorItem.Type.LEGGINGS,  new Item.Properties()));
    public static final RegistryObject<Item> PINK_SAPPHIRE_BOOTS = ITEMS.register("pink_sapphire_boots", () -> new ArmorItem(ModArmorMaterials.PINK_SAPPHIRE, ArmorItem.Type.BOOTS,  new Item.Properties()));

    public static final RegistryObject<Item> RUBY = ITEMS.register("ruby", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RUBY_SWORD = ITEMS.register("ruby_sword", () -> new SwordItem(ModToolTiers.RUBY, 4, 2,  new Item.Properties()));
    public static final RegistryObject<Item> RUBY_PICKAXE = ITEMS.register("ruby_pickaxe", () -> new PickaxeItem(ModToolTiers.RUBY, 4, 2,  new Item.Properties()));
    public static final RegistryObject<Item> RUBY_AXE = ITEMS.register("ruby_axe", () -> new AxeItem(ModToolTiers.RUBY, 4, 2,  new Item.Properties()));
    public static final RegistryObject<Item> RUBY_SHOVEL = ITEMS.register("ruby_shovel", () -> new ShovelItem(ModToolTiers.RUBY, 4, 2,  new Item.Properties()));
    public static final RegistryObject<Item> RUBY_HOE = ITEMS.register("ruby_hoe", () -> new HoeItem(ModToolTiers.RUBY, 4, 2,  new Item.Properties()));

    public static final RegistryObject<Item> RUBY_HELMET = ITEMS.register("ruby_helmet", () -> new ArmorItem(ModArmorMaterials.RUBY, ArmorItem.Type.HELMET,  new Item.Properties()));
    public static final RegistryObject<Item> RUBY_CHESTPLATE = ITEMS.register("ruby_chestplate", () -> new ArmorItem(ModArmorMaterials.RUBY, ArmorItem.Type.CHESTPLATE,  new Item.Properties()));
    public static final RegistryObject<Item> RUBY_LEGGINGS = ITEMS.register("ruby_leggings", () -> new ArmorItem(ModArmorMaterials.RUBY, ArmorItem.Type.LEGGINGS,  new Item.Properties()));
    public static final RegistryObject<Item> RUBY_BOOTS = ITEMS.register("ruby_boots", () -> new ArmorItem(ModArmorMaterials.RUBY, ArmorItem.Type.BOOTS,  new Item.Properties()));


    public static final RegistryObject<Item> CUTE_ARROW = ITEMS.register("cute_arrow", () -> new CuteArrowItem(new Item.Properties(), 999.9F));
    public static final RegistryObject<Item> CUTE_BOW = ITEMS.register("cute_bow", () -> new CuteBowItem(new Item.Properties()));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
