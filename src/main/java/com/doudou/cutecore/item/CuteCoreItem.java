package com.doudou.cutecore.item;

import com.doudou.cutecore.CuteCore;
import com.doudou.cutecore.blocks.CuteCoreBlock;
import com.doudou.cutecore.entity.ModEntities;
import com.doudou.cutecore.item.custom.*;
import com.doudou.cutecore.item.custom.projectiles.HeartItem;
import com.doudou.cutecore.item.custom.projectiles.StarLightItem;
import com.doudou.cutecore.sound.ModSounds;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CuteCoreItem {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, CuteCore.MOD_ID);

    public static final RegistryObject<Item> STRAWBERRY_SEEDS = ITEMS.register("strawberry_seeds", () -> new ItemNameBlockItem(CuteCoreBlock.STRAWBERRY_CROP.get(), new Item.Properties()));
    public static final RegistryObject<Item> STRAWBERRY = ITEMS.register("strawberry", () -> new Item(new Item.Properties().food(ModFoods.STRAWBERRY)));
    public static final RegistryObject<Item> MINT_SEEDS = ITEMS.register("mint_seeds", () -> new ItemNameBlockItem(CuteCoreBlock.MINT_CROP.get(), new Item.Properties()));
    public static final RegistryObject<Item> MINT = ITEMS.register("mint", () -> new Item(new Item.Properties().food(ModFoods.STRAWBERRY)));
    public static final RegistryObject<Item> VANILLA_SEEDS = ITEMS.register("vanilla_seeds", () -> new ItemNameBlockItem(CuteCoreBlock.VANILLA_CROP.get(), new Item.Properties()));
    public static final RegistryObject<Item> VANILLA = ITEMS.register("vanilla", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PISTACHIO = ITEMS.register("pistachio", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LEMON = ITEMS.register("lemon", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CHOCOLATE_STRAWBERRY = ITEMS.register("chocolate_strawberry", () -> new Item(new Item.Properties().food(ModFoods.STRAWBERRY)));
    public static final RegistryObject<Item> SECRET_DONUT = ITEMS.register("secret_donut", () -> new Item(new Item.Properties().food(ModFoods.DONUT)));
    public static final RegistryObject<Item> DONUT = ITEMS.register("donut", () -> new Item(new Item.Properties().food(ModFoods.DONUT)));
    public static final RegistryObject<Item> STRAWBERRY_DONUT = ITEMS.register("strawberry_donut", () -> new Item(new Item.Properties().food(ModFoods.DONUT)));
    public static final RegistryObject<Item> CHOCOLATE_DONUT = ITEMS.register("chocolate_donut", () -> new Item(new Item.Properties().food(ModFoods.DONUT)));
    public static final RegistryObject<Item> SECRET_MOCHI_DONUT = ITEMS.register("secret_mochi_donut", () -> new Item(new Item.Properties().food(ModFoods.MOCHI_DONUT)));
    public static final RegistryObject<Item> MOCHI_DONUT = ITEMS.register("mochi_donut", () -> new Item(new Item.Properties().food(ModFoods.MOCHI_DONUT)));
    public static final RegistryObject<Item> STRAWBERRY_MOCHI_DONUT = ITEMS.register("strawberry_mochi_donut", () -> new Item(new Item.Properties().food(ModFoods.MOCHI_DONUT)));
    public static final RegistryObject<Item> CHOCOLATE_MOCHI_DONUT = ITEMS.register("chocolate_mochi_donut", () -> new Item(new Item.Properties().food(ModFoods.MOCHI_DONUT)));
    public static final RegistryObject<Item> CARAMEL = ITEMS.register("caramel", () -> new Item(new Item.Properties().food(ModFoods.CARAMEL)));
    public static final RegistryObject<Item> CHOCOLATE = ITEMS.register("chocolate", () -> new Item(new Item.Properties().food(ModFoods.CHOCOLATE)));
    public static final RegistryObject<Item> STRAWBERRY_MILK_BUCKET = ITEMS.register("strawberry_milk_bucket", () -> new MilkBucketItem(new Item.Properties().food(ModFoods.MILK).stacksTo(1)));
    public static final RegistryObject<Item> CHOCOLATE_MILK_BUCKET = ITEMS.register("chocolate_milk_bucket", () -> new MilkBucketItem(new Item.Properties().food(ModFoods.MILK).stacksTo(1)));
    public static final RegistryObject<Item> VANILLA_MILK_BUCKET = ITEMS.register("vanilla_milk_bucket", () -> new MilkBucketItem(new Item.Properties().food(ModFoods.MILK).stacksTo(1)));
    public static final RegistryObject<Item> STRAWBERRY_MILK_CARTON = ITEMS.register("strawberry_milk_carton", () -> new Item(new Item.Properties().food(ModFoods.MILK_CARTON)));
    public static final RegistryObject<Item> CHOCOLATE_MILK_CARTON = ITEMS.register("chocolate_milk_carton", () -> new Item(new Item.Properties().food(ModFoods.MILK_CARTON)));
    public static final RegistryObject<Item> VANILLA_MILK_CARTON = ITEMS.register("vanilla_milk_carton", () -> new Item(new Item.Properties().food(ModFoods.MILK_CARTON)));
    public static final RegistryObject<Item> PISTACHIO_ICE_CREAM = ITEMS.register("pistachio_ice_cream", () -> new ReusableFoodContainerItem(new Item.Properties().food(ModFoods.ICE_CREAM), 32, CuteCoreItem.ICE_CREAM_CUP));
    public static final RegistryObject<Item> VANILLA_ICE_CREAM = ITEMS.register("vanilla_ice_cream", () -> new ReusableFoodContainerItem(new Item.Properties().food(ModFoods.ICE_CREAM), 32, CuteCoreItem.ICE_CREAM_CUP));
    public static final RegistryObject<Item> CHOCOLATE_ICE_CREAM = ITEMS.register("chocolate_ice_cream", () -> new ReusableFoodContainerItem(new Item.Properties().food(ModFoods.ICE_CREAM), 32, CuteCoreItem.ICE_CREAM_CUP));
    public static final RegistryObject<Item> STRAWBERRY_ICE_CREAM = ITEMS.register("strawberry_ice_cream", () -> new ReusableFoodContainerItem(new Item.Properties().food(ModFoods.ICE_CREAM), 32, CuteCoreItem.ICE_CREAM_CUP));
    public static final RegistryObject<Item> POKKY_ICE_CREAM = ITEMS.register("pokky_ice_cream", () -> new ReusableFoodContainerItem(new Item.Properties().food(ModFoods.POKKY_ICE_CREAM), 32, CuteCoreItem.ICE_CREAM_CUP));
    public static final RegistryObject<Item> APPLE_MOJITO = ITEMS.register("apple_mojito", () -> new ReusableFoodContainerItem(new Item.Properties().food(ModFoods.MOJITO), 32, CuteCoreItem.JUICE_GLASS));
    public static final RegistryObject<Item> MINT_MOJITO = ITEMS.register("mint_mojito", () -> new ReusableFoodContainerItem(new Item.Properties().food(ModFoods.MOJITO), 32, CuteCoreItem.JUICE_GLASS));
    public static final RegistryObject<Item> STRAWBERRY_MOJITO = ITEMS.register("strawberry_mojito", () -> new ReusableFoodContainerItem(new Item.Properties().food(ModFoods.MOJITO), 32, CuteCoreItem.JUICE_GLASS));
    public static final RegistryObject<Item> BUBBLE_TEA = ITEMS.register("bubble_tea", () -> new ReusableFoodContainerItem(new Item.Properties().food(ModFoods.BUBBLE_TEA), 32, CuteCoreItem.JUICE_GLASS));
    public static final RegistryObject<Item> WAFFLE = ITEMS.register("waffle", () -> new Item(new Item.Properties().food(ModFoods.WAFFLE)));
    public static final RegistryObject<Item> CHOCOLATE_WAFFLE = ITEMS.register("chocolate_waffle", () -> new Item(new Item.Properties().food(ModFoods.CHOCOLATE_WAFFLE)));
    public static final RegistryObject<Item> CHOCOLATE_POCKY = ITEMS.register("chocolate_pocky", () -> new Item(new Item.Properties().food(ModFoods.POCKY)));
    public static final RegistryObject<Item> STRAWBERRY_POCKY = ITEMS.register("strawberry_pocky", () -> new Item(new Item.Properties().food(ModFoods.POCKY)));
    public static final RegistryObject<Item> DANGO_CARAMEL = ITEMS.register("dango_caramel", () -> new Item(new Item.Properties().food(ModFoods.DANGO)));
    public static final RegistryObject<Item> TRICOLOR_DANGO = ITEMS.register("tricolor_dango", () -> new Item(new Item.Properties().food(ModFoods.DANGO)));
    public static final RegistryObject<Item> POPSICLE = ITEMS.register("popsicle", () -> new Item(new Item.Properties().food(ModFoods.POPSICLE)));
    public static final RegistryObject<Item> VANILLA_POPSICLE = ITEMS.register("vanilla_popsicle", () -> new Item(new Item.Properties().food(ModFoods.POPSICLE)));
    public static final RegistryObject<Item> CHOCOLATE_POPSICLE = ITEMS.register("chocolate_popsicle", () -> new Item(new Item.Properties().food(ModFoods.POPSICLE)));
    public static final RegistryObject<Item> STRAWBERRY_POPSICLE = ITEMS.register("strawberry_popsicle", () -> new Item(new Item.Properties().food(ModFoods.POPSICLE)));
    public static final RegistryObject<Item> CARAMEL_PUDDING = ITEMS.register("caramel_pudding", () -> new Item(new Item.Properties().food(ModFoods.PUDDING)));
    public static final RegistryObject<Item> CHOCOLATE_PUDDING = ITEMS.register("chocolate_pudding", () -> new Item(new Item.Properties().food(ModFoods.PUDDING)));
    public static final RegistryObject<Item> STRAWBERRY_PUDDING = ITEMS.register("strawberry_pudding", () -> new Item(new Item.Properties().food(ModFoods.PUDDING)));
    public static final RegistryObject<Item> PISTACHIO_PUDDING = ITEMS.register("pistachio_pudding", () -> new Item(new Item.Properties().food(ModFoods.PUDDING)));
    public static final RegistryObject<Item> MARSHMALLOW = ITEMS.register("marshmallow", () -> new Item(new Item.Properties().food(ModFoods.MARSHMELLOW)));
    public static final RegistryObject<Item> MARSHMALLOW_STICK = ITEMS.register("marshmallow_stick", () -> new Item(new Item.Properties().food(ModFoods.MARSHMELLOW)));
    public static final RegistryObject<Item> ROASTED_MARSHMALLOW_STICK = ITEMS.register("roasted_marshmallow_stick", () -> new Item(new Item.Properties().food(ModFoods.ROASTED_MARSHMELLOW)));
    public static final RegistryObject<Item> SMORE = ITEMS.register("smore", () -> new Item(new Item.Properties().food(ModFoods.SMORE)));
    public static final RegistryObject<Item> MARSHMELLOW_STRAWBERRY_BURGER = ITEMS.register("marshmellow_strawberry_burger", () -> new Item(new Item.Properties().food(ModFoods.MARSHMELLOW_STRAWBERRY_BURGER)));
    public static final RegistryObject<Item> CANDY_APPLE = ITEMS.register("candy_apple", () -> new Item(new Item.Properties().food(ModFoods.CANDY_APPLE)));
    public static final RegistryObject<Item> CHOCOLATE_CROISSANT = ITEMS.register("chocolate_croissant", () -> new Item(new Item.Properties().food(ModFoods.CROISSANT)));
    public static final RegistryObject<Item> VANILLA_CROISSANT = ITEMS.register("vanilla_croissant", () -> new Item(new Item.Properties().food(ModFoods.CROISSANT)));
    public static final RegistryObject<Item> STRAWBERRY_CROISSANT = ITEMS.register("strawberry_croissant", () -> new Item(new Item.Properties().food(ModFoods.CROISSANT)));
    public static final RegistryObject<Item> PISTACHIO_CROISSANT = ITEMS.register("pistachio_croissant", () -> new Item(new Item.Properties().food(ModFoods.CROISSANT)));
    public static final RegistryObject<Item> RHUM_BOTTLE = ITEMS.register("rhum_bottle", () -> new Item(new Item.Properties().food(ModFoods.RHUM)));
    public static final RegistryObject<Item> JUICE_GLASS = ITEMS.register("juice_glass", () -> new Item(new Item.Properties().food(ModFoods.RHUM)));
    public static final RegistryObject<Item> ICE_CREAM_CUP = ITEMS.register("ice_cream_cup", () -> new Item(new Item.Properties().food(ModFoods.RHUM)));


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

    public static final RegistryObject<Item> RAW_RUBY = ITEMS.register("raw_ruby", () -> new Item(new Item.Properties()));
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
    public static final RegistryObject<Item> STAR_LIGHT = ITEMS.register("star_light", () -> new StarLightItem(new Item.Properties(), 5.9F));
    public static final RegistryObject<Item> ETERNAL_SILVER_CISTAL_STAFF = ITEMS.register("eternal_silver_cristal_staff", () -> new EternalSilverCristalStaffitem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> CUPIDON_BOW = ITEMS.register("cupidon_bow", () -> new CuteBowItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> CUTIE_MOON_ROD = ITEMS.register("cutie_moon_rod", () -> new CuteWandItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> ETERNAL_TIARE = ITEMS.register("eternal_tiare", () -> new CuteWandItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> MOON_KALEIDOSCOPE = ITEMS.register("moon_kaleidoscope", () -> new CuteWandItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> MOON_STICK = ITEMS.register("moon_stick", () -> new CuteWandItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> MOON_STICK_PEARL = ITEMS.register("moon_stick_pearl", () -> new CuteWandItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> SPIRAL_HEART_MOON_ROD = ITEMS.register("spiral_heart_moon_rod", () -> new CuteWandItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> PLUTONS_KEY = ITEMS.register("plutons_key", () -> new PlutonsKey(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> NEPTUNES_MIRROR = ITEMS.register("neptunes_mirror", () -> new NeptunesMirror(new Item.Properties().stacksTo(1)));
//    public static final RegistryObject<Item> NEPTUNES_MIRROR = ITEMS.register("neptunes_mirror", () -> new NeptunesMirror(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> STRAWBERRY_COW_EGG = ITEMS.register("strawberry_cow_egg", () -> new ForgeSpawnEggItem(ModEntities.STRAWBERRY_COW, 0xfcd6df, 0xf798be, new Item.Properties()));
    public static final RegistryObject<Item> CELESTIAL_RABBIT_EGG = ITEMS.register("celestial_rabbit_egg", () -> new ForgeSpawnEggItem(ModEntities.CELESTIAL_RABBIT, 0xf564df, 0xf548be, new Item.Properties()));

    public static final RegistryObject<Item> BUTTERFLY_NET = ITEMS.register("butterfly_net", () -> new ButterflyNet(new Item.Properties()));


    public static final RegistryObject<Item> SAILORMOON_OST_MUSIC_DISC = ITEMS.register("sailormoon_ost_music_disc", () -> new RecordItem(4, ModSounds.SAILORMOON_OST.get(), new Item.Properties().stacksTo(1), 10380));
    public static final RegistryObject<Item> SAILORMOON_MOONPRIDE_MUSIC_DISC = ITEMS.register("sailormoon_moonpride_music_disc", () -> new RecordItem(4, ModSounds.SAILORMOON_MOONPRIDE.get(), new Item.Properties().stacksTo(1), 1780));
    public static final RegistryObject<Item> LOFI_MUSIC_DISC = ITEMS.register("lofi_music_disc", () -> new RecordItem(4, ModSounds.LOFI.get(), new Item.Properties().stacksTo(1), 9700));
    public static final RegistryObject<Item> TAVERN_MUSIC_DISC = ITEMS.register("tavern_music_disc", () -> new RecordItem(4, ModSounds.TAVERN.get(), new Item.Properties().stacksTo(1), 16000));
    public static final RegistryObject<Item> JAPANESE_FLUTE_MUSIC_DISC = ITEMS.register("japanese_flute_music_disc", () -> new RecordItem(4, ModSounds.JAPANESE_FLUTE.get(), new Item.Properties().stacksTo(1), 999999999));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}

