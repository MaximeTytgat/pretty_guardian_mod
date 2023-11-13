package com.doudou.cutecore.item;

import com.doudou.cutecore.CuteCore;
import com.doudou.cutecore.blocks.CuteCoreBlock;
import com.doudou.cutecore.item.custom.*;
import com.doudou.cutecore.sound.ModSounds;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CuteCoreItem {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, CuteCore.MOD_ID);

    public static final RegistryObject<Item> STYLE_CAKE = ITEMS.register("style_cake", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STRAWBERRY_SEEDS = ITEMS.register("strawberry_seeds", () -> new ItemNameBlockItem(CuteCoreBlock.STRAWBERRY_CROP.get(), new Item.Properties()));
    public static final RegistryObject<Item> STRAWBERRY = ITEMS.register("strawberry", () -> new Item(new Item.Properties().food(ModFoods.STRAWBERRY)));
    public static final RegistryObject<Item> CHOCOLATE_STRAWBERRY = ITEMS.register("chocolate_strawberry", () -> new Item(new Item.Properties().food(ModFoods.STRAWBERRY)));
    public static final RegistryObject<Item> CHOCOLATE_HEART = ITEMS.register("chocolate_heart", () -> new Item(new Item.Properties().food(ModFoods.CHOCOLATE_HEART)));

    public static final RegistryObject<Item> VANILLA_MILK_CARTON = ITEMS.register("vanilla_milk_carton", () -> new MilkBucketItem(new Item.Properties().food(ModFoods.MILK_CARTON).stacksTo(16)));
    public static final RegistryObject<Item> PINK_SAPPHIRE = ITEMS.register("pink_sapphire", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_PINK_SAPPHIRE = ITEMS.register("raw_pink_sapphire", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PINK_SAPPHIRE_SWORD = ITEMS.register("pink_sapphire_sword", () -> new SwordItem(ModToolTiers.PINK_SAPPHIRE, 4, 2,  new Item.Properties()));
    public static final RegistryObject<Item> PINK_SAPPHIRE_PICKAXE = ITEMS.register("pink_sapphire_pickaxe", () -> new PickaxeItem(ModToolTiers.PINK_SAPPHIRE, 4, 2,  new Item.Properties()));
    public static final RegistryObject<Item> PINK_SAPPHIRE_AXE = ITEMS.register("pink_sapphire_axe", () -> new AxeItem(ModToolTiers.PINK_SAPPHIRE, 4, 2,  new Item.Properties()));
    public static final RegistryObject<Item> PINK_SAPPHIRE_SHOVEL = ITEMS.register("pink_sapphire_shovel", () -> new ShovelItem(ModToolTiers.PINK_SAPPHIRE, 4, 2,  new Item.Properties()));
    public static final RegistryObject<Item> PINK_SAPPHIRE_HOE = ITEMS.register("pink_sapphire_hoe", () -> new HoeItem(ModToolTiers.PINK_SAPPHIRE, 4, 2,  new Item.Properties()));

    public static final RegistryObject<Item> PINK_SAPPHIRE_HELMET = ITEMS.register("pink_sapphire_helmet", () -> new PinkSapphireArmorItem(ModArmorMaterials.PINK_SAPPHIRE, ArmorItem.Type.HELMET,  new Item.Properties()));
    public static final RegistryObject<Item> PINK_SAPPHIRE_CHESTPLATE = ITEMS.register("pink_sapphire_chestplate", () -> new PinkSapphireArmorItem(ModArmorMaterials.PINK_SAPPHIRE, ArmorItem.Type.CHESTPLATE,  new Item.Properties()));
    public static final RegistryObject<Item> PINK_SAPPHIRE_LEGGINGS = ITEMS.register("pink_sapphire_leggings", () -> new PinkSapphireArmorItem(ModArmorMaterials.PINK_SAPPHIRE, ArmorItem.Type.LEGGINGS,  new Item.Properties()));
    public static final RegistryObject<Item> PINK_SAPPHIRE_BOOTS = ITEMS.register("pink_sapphire_boots", () -> new PinkSapphireArmorItem(ModArmorMaterials.PINK_SAPPHIRE, ArmorItem.Type.BOOTS,  new Item.Properties()));

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


    public static final RegistryObject<Item> HEART_ARROW = ITEMS.register("heart_arrow", () -> new CuteArrowItem(new Item.Properties(), 5.9F));
    public static final RegistryObject<Item> CUTE_HEART = ITEMS.register("cute_heart", () -> new HeartItem(new Item.Properties(), 5.9F));
    public static final RegistryObject<Item> CUPIDON_BOW = ITEMS.register("cupidon_bow", () -> new CuteBowItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> CUTE_WAND = ITEMS.register("cute_wand", () -> new CuteWandItem(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> SAILORMOON_OST_MUSIC_DISC = ITEMS.register("sailormoon_ost_music_disc", () -> new RecordItem(4, ModSounds.SAILORMOON_OST.get(), new Item.Properties().stacksTo(1), 10380));
    public static final RegistryObject<Item> SAILORMOON_MOONPRIDE_MUSIC_DISC = ITEMS.register("sailormoon_moonpride_music_disc", () -> new RecordItem(4, ModSounds.SAILORMOON_MOONPRIDE.get(), new Item.Properties().stacksTo(1), 1780));
    public static final RegistryObject<Item> LOFI_MUSIC_DISC = ITEMS.register("lofi_music_disc", () -> new RecordItem(4, ModSounds.LOFI.get(), new Item.Properties().stacksTo(1), 9700));
    public static final RegistryObject<Item> TAVERN_MUSIC_DISC = ITEMS.register("tavern_music_disc", () -> new RecordItem(4, ModSounds.TAVERN.get(), new Item.Properties().stacksTo(1), 16000));
    public static final RegistryObject<Item> JAPANESE_FLUTE_MUSIC_DISC = ITEMS.register("japanese_flute_music_disc", () -> new RecordItem(4, ModSounds.JAPANESE_FLUTE.get(), new Item.Properties().stacksTo(1), 999999999));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}

