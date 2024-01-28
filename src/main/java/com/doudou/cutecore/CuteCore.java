package com.doudou.cutecore;

import com.doudou.cutecore.blocks.CuteCoreBlock;
import com.doudou.cutecore.blocks.entity.ModBlockEntities;
import com.doudou.cutecore.entity.ModEntities;
import com.doudou.cutecore.entity.client.celestialrabbit.CelestialRabbitRenderer;
import com.doudou.cutecore.entity.client.strawberryCow.StrawberryCowRenderer;
import com.doudou.cutecore.item.CuteCoreItem;
import com.doudou.cutecore.loot.ModLootModifiers;
import com.doudou.cutecore.particle.ModParticles;
import com.doudou.cutecore.screen.ModMenuTypes;
import com.doudou.cutecore.screen.PicnicBasketScreen;
import com.doudou.cutecore.sound.ModSounds;
import com.doudou.cutecore.util.ModItemProperties;
import com.doudou.cutecore.worldgen.entity.ModEntityType;
import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(CuteCore.MOD_ID)
public class CuteCore
{
    public static final String MOD_ID = "cutecore";
    public static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "cutecore" namespace
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
    // Create a Deferred Register to hold Items which will all be registered under the "cutecore" namespace
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "cutecore" namespace
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);

    // Creates a new Block with the id "cutecore:example_block", combining the namespace and path
//    public static final RegistryObject<Block> EXAMPLE_BLOCK = BLOCKS.register("example_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE)));
//    // Creates a new BlockItem with the id "cutecore:example_block", combining the namespace and path
//    public static final RegistryObject<Item> EXAMPLE_BLOCK_ITEM = ITEMS.register("example_block", () -> new BlockItem(EXAMPLE_BLOCK.get(), new Item.Properties()));
//
//    // Creates a new food item with the id "cutecore:example_id", nutrition 1 and saturation 2
//    public static final RegistryObject<Item> EXAMPLE_ITEM = ITEMS.register("example_item", () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
//            .alwaysEat().nutrition(1).saturationMod(2f).build())));

    // Creates a creative tab with the id "cutecore:example_tab" for the example item, that is placed after the combat tab
    public static final RegistryObject<CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("example_tab", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> CuteCoreItem.CHOCOLATE.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(CuteCoreBlock.CREAM_CAKE.get());
                output.accept(CuteCoreBlock.RHUM_CAKE.get());
                output.accept(CuteCoreBlock.STRAWBERRY_CAKE.get());
                output.accept(CuteCoreBlock.BERRY_STRAWBERRY_CAKE.get());
                output.accept(CuteCoreBlock.VELVET_CAKE.get());
                output.accept(CuteCoreBlock.CREAM_STRAWBERRY_CAKE.get());
                output.accept(CuteCoreBlock.THREE_CHOCO_CAKE.get());
                output.accept(CuteCoreBlock.THREE_STRAWBERRY_CAKE.get());
                output.accept(CuteCoreBlock.THREE_STRAWBERRY_CHOCO_CAKE.get());
                output.accept(CuteCoreBlock.THREE_VELVET_CAKE.get());
                output.accept(CuteCoreBlock.STRAWBERRY_CHOCO_CAKE.get());
                output.accept(CuteCoreBlock.CHOCOLATE_CAKE.get());
                output.accept(CuteCoreBlock.APPLE_PIE.get());
                output.accept(CuteCoreBlock.CHOCOLATE_PIE.get());
                output.accept(CuteCoreBlock.MAGIC_PIE.get());
                output.accept(CuteCoreBlock.LEMON_PIE.get());
                output.accept(CuteCoreBlock.STRAWBERRY_PIE.get());
                output.accept(CuteCoreItem.STRAWBERRY_SEEDS.get());
                output.accept(CuteCoreItem.STRAWBERRY.get());
                output.accept(CuteCoreItem.VANILLA_SEEDS.get());
                output.accept(CuteCoreItem.VANILLA.get());
                output.accept(CuteCoreItem.PISTACHIO.get());
                output.accept(CuteCoreItem.LEMON.get());


                output.accept(CuteCoreItem.MINT_SEEDS.get());
                output.accept(CuteCoreItem.MINT.get());
                output.accept(CuteCoreItem.CHOCOLATE_STRAWBERRY.get());
                output.accept(CuteCoreItem.SECRET_DONUT.get());
                output.accept(CuteCoreItem.DONUT.get());
                output.accept(CuteCoreItem.STRAWBERRY_DONUT.get());
                output.accept(CuteCoreItem.CHOCOLATE_DONUT.get());
                output.accept(CuteCoreItem.SECRET_MOCHI_DONUT.get());
                output.accept(CuteCoreItem.MOCHI_DONUT.get());
                output.accept(CuteCoreItem.STRAWBERRY_MOCHI_DONUT.get());
                output.accept(CuteCoreItem.CHOCOLATE_MOCHI_DONUT.get());
                output.accept(CuteCoreItem.CARAMEL.get());
                output.accept(CuteCoreItem.CHOCOLATE.get());
                output.accept(CuteCoreItem.STRAWBERRY_MILK_BUCKET.get());
                output.accept(CuteCoreItem.CHOCOLATE_MILK_BUCKET.get());
                output.accept(CuteCoreItem.VANILLA_MILK_BUCKET.get());
                output.accept(CuteCoreItem.STRAWBERRY_MILK_CARTON.get());
                output.accept(CuteCoreItem.CHOCOLATE_MILK_CARTON.get());
                output.accept(CuteCoreItem.VANILLA_MILK_CARTON.get());
                output.accept(CuteCoreItem.PISTACHIO_ICE_CREAM.get());
                output.accept(CuteCoreItem.VANILLA_ICE_CREAM.get());
                output.accept(CuteCoreItem.CHOCOLATE_ICE_CREAM.get());
                output.accept(CuteCoreItem.STRAWBERRY_ICE_CREAM.get());
                output.accept(CuteCoreItem.POKKY_ICE_CREAM.get());
                output.accept(CuteCoreItem.APPLE_MOJITO.get());
                output.accept(CuteCoreItem.MINT_MOJITO.get());
                output.accept(CuteCoreItem.STRAWBERRY_MOJITO.get());
                output.accept(CuteCoreItem.BUBBLE_TEA.get());
                output.accept(CuteCoreItem.WAFFLE.get());
                output.accept(CuteCoreItem.CHOCOLATE_WAFFLE.get());
                output.accept(CuteCoreItem.CHOCOLATE_POCKY.get());
                output.accept(CuteCoreItem.STRAWBERRY_POCKY.get());
                output.accept(CuteCoreItem.DANGO_CARAMEL.get());
                output.accept(CuteCoreItem.TRICOLOR_DANGO.get());
                output.accept(CuteCoreItem.POPSICLE.get());
                output.accept(CuteCoreItem.VANILLA_POPSICLE.get());
                output.accept(CuteCoreItem.CHOCOLATE_POPSICLE.get());
                output.accept(CuteCoreItem.STRAWBERRY_POPSICLE.get());
                output.accept(CuteCoreItem.CARAMEL_PUDDING.get());
                output.accept(CuteCoreItem.CHOCOLATE_PUDDING.get());
                output.accept(CuteCoreItem.STRAWBERRY_PUDDING.get());
                output.accept(CuteCoreItem.PISTACHIO_PUDDING.get());
                output.accept(CuteCoreItem.MARSHMALLOW.get());
                output.accept(CuteCoreItem.MARSHMALLOW_STICK.get());
                output.accept(CuteCoreItem.ROASTED_MARSHMALLOW_STICK.get());
                output.accept(CuteCoreItem.SMORE.get());
                output.accept(CuteCoreItem.MARSHMELLOW_STRAWBERRY_BURGER.get());
                output.accept(CuteCoreItem.CANDY_APPLE.get());
                output.accept(CuteCoreItem.CHOCOLATE_CROISSANT.get());
                output.accept(CuteCoreItem.VANILLA_CROISSANT.get());
                output.accept(CuteCoreItem.STRAWBERRY_CROISSANT.get());
                output.accept(CuteCoreItem.PISTACHIO_CROISSANT.get());
                output.accept(CuteCoreItem.RHUM_BOTTLE.get());

                output.accept(CuteCoreItem.RAW_PINK_SAPPHIRE.get());
                output.accept(CuteCoreItem.PINK_SAPPHIRE.get());
                output.accept(CuteCoreBlock.PINK_SAPPHIRE_BLOCK.get());
                output.accept(CuteCoreBlock.PINK_SAPPHIRE_ORE.get());
                output.accept(CuteCoreBlock.DEEPSLATE_PINK_SAPPHIRE_ORE.get());

                output.accept(CuteCoreItem.PINK_SAPPHIRE_SWORD.get());
                output.accept(CuteCoreItem.PINK_SAPPHIRE_PICKAXE.get());
                output.accept(CuteCoreItem.PINK_SAPPHIRE_AXE.get());
                output.accept(CuteCoreItem.PINK_SAPPHIRE_SHOVEL.get());
                output.accept(CuteCoreItem.PINK_SAPPHIRE_HOE.get());

                output.accept(CuteCoreItem.PINK_SAPPHIRE_HELMET.get());
                output.accept(CuteCoreItem.PINK_SAPPHIRE_CHESTPLATE.get());
                output.accept(CuteCoreItem.PINK_SAPPHIRE_LEGGINGS.get());
                output.accept(CuteCoreItem.PINK_SAPPHIRE_BOOTS.get());

//                output.accept(CuteCoreItem.RAW_RUBY.get());
                output.accept(CuteCoreItem.RUBY.get());
                output.accept(CuteCoreBlock.RUBY_BLOCK.get());
                output.accept(CuteCoreBlock.RUBY_ORE.get());
                output.accept(CuteCoreBlock.DEEPSLATE_RUBY_ORE.get());

                output.accept(CuteCoreItem.RUBY_SWORD.get());
                output.accept(CuteCoreItem.RUBY_PICKAXE.get());
                output.accept(CuteCoreItem.RUBY_AXE.get());
                output.accept(CuteCoreItem.RUBY_SHOVEL.get());
                output.accept(CuteCoreItem.RUBY_HOE.get());

                output.accept(CuteCoreItem.RUBY_HELMET.get());
                output.accept(CuteCoreItem.RUBY_CHESTPLATE.get());
                output.accept(CuteCoreItem.RUBY_LEGGINGS.get());
                output.accept(CuteCoreItem.RUBY_BOOTS.get());

                output.accept(CuteCoreItem.ETERNAL_SILVER_CISTAL_STAFF.get());

                output.accept(CuteCoreItem.CUPIDON_BOW.get());
                output.accept(CuteCoreItem.HEART_ARROW.get());

                output.accept(CuteCoreItem.CUTIE_MOON_ROD.get());
                output.accept(CuteCoreItem.ETERNAL_TIARE.get());
                output.accept(CuteCoreItem.MOON_KALEIDOSCOPE.get());
                output.accept(CuteCoreItem.MOON_STICK.get());
                output.accept(CuteCoreItem.MOON_STICK_PEARL.get());
                output.accept(CuteCoreItem.SPIRAL_HEART_MOON_ROD.get());

                output.accept(CuteCoreItem.PLUTONS_KEY.get());
                output.accept(CuteCoreItem.NEPTUNES_MIRROR.get());

                output.accept(CuteCoreBlock.PICNIC_BASKET.get());
                output.accept(CuteCoreItem.SAILORMOON_OST_MUSIC_DISC.get());
                output.accept(CuteCoreItem.SAILORMOON_MOONPRIDE_MUSIC_DISC.get());
                output.accept(CuteCoreItem.LOFI_MUSIC_DISC.get());
                output.accept(CuteCoreItem.TAVERN_MUSIC_DISC.get());
                output.accept(CuteCoreItem.JAPANESE_FLUTE_MUSIC_DISC.get());

                output.accept(CuteCoreBlock.CHOCOLATE_BLOCK.get());
                output.accept(CuteCoreBlock.MARSHMELLO_BLOCK.get());
                output.accept(CuteCoreBlock.ROASTED_MARSHMELLO_BLOCK.get());

                output.accept(CuteCoreBlock.PISTACHIO_LEAVES_CROP.get());
                output.accept(CuteCoreBlock.PISTACHIO_SAPLING.get());

                output.accept(CuteCoreBlock.LEMON_LEAVES_CROP.get());
                output.accept(CuteCoreBlock.LEMON_SAPLING.get());

                output.accept(CuteCoreItem.STRAWBERRY_COW_EGG.get());
                output.accept(CuteCoreItem.CELESTIAL_RABBIT_EGG.get());
                output.accept(CuteCoreItem.BUTTERFLY_NET.get());

                output.accept(CuteCoreBlock.MYMELODY_PLUSH.get());
                output.accept(CuteCoreBlock.KUROMI_PLUSH.get());
                output.accept(CuteCoreBlock.CAVALIER_PLUSH.get());
                output.accept(CuteCoreBlock.TEDDYBEAR_PLUSH.get());
                output.accept(CuteCoreBlock.RABBIT_PLUSH.get());
                output.accept(CuteCoreBlock.COW_PLUSH.get());

                output.accept(CuteCoreBlock.SHOJI_BLOSSOM.get());
                output.accept(CuteCoreBlock.SHOJI_BLOSSOM_BOTTOM.get());
                output.accept(CuteCoreBlock.SHOJI_BLOSSOM_SMALL.get());
                output.accept(CuteCoreBlock.SHOJI_BLOSSOM_SMALL_BOTTOM.get());

                output.accept(CuteCoreBlock.SHOJI_CHERRY.get());
                output.accept(CuteCoreBlock.SHOJI_CHERRY_BOTTOM.get());
                output.accept(CuteCoreBlock.SHOJI_CHERRY_SMALL.get());
                output.accept(CuteCoreBlock.SHOJI_CHERRY_SMALL_BOTTOM.get());

                output.accept(CuteCoreBlock.SHOJI_BIRCH.get());
                output.accept(CuteCoreBlock.SHOJI_BIRCH_BOTTOM.get());
                output.accept(CuteCoreBlock.SHOJI_BIRCH_SMALL.get());
                output.accept(CuteCoreBlock.SHOJI_BIRCH_SMALL_BOTTOM.get());

                output.accept(CuteCoreBlock.LANTERN_JAPANESE.get());
                output.accept(CuteCoreBlock.LANTERN_JAPANESE_SAKURA.get());
                output.accept(CuteCoreBlock.LANTERN_JAPANESE_FESTIVAL.get());

                output.accept(CuteCoreBlock.LAMP_JAPANESE_OAK.get());
                output.accept(CuteCoreBlock.LAMP_JAPANESE_BIRCH.get());
                output.accept(CuteCoreBlock.LAMP_JAPANESE_SPRUCE.get());
                output.accept(CuteCoreBlock.LAMP_JAPANESE_JUNGLE.get());
                output.accept(CuteCoreBlock.LAMP_JAPANESE_ACACIA.get());
                output.accept(CuteCoreBlock.LAMP_JAPANESE_DARK_OAK.get());
                output.accept(CuteCoreBlock.LAMP_JAPANESE_MANGROVE.get());
                output.accept(CuteCoreBlock.LAMP_JAPANESE_CHERRY.get());

            }).build());

    public CuteCore()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        CuteCoreBlock.init();
        modEventBus.register(this);
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        CuteCoreItem.register(modEventBus);
        CuteCoreBlock.register(modEventBus);
        ModLootModifiers.register(modEventBus);
        ModEntityType.register(modEventBus);
        ModParticles.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModSounds.register(modEventBus);
        ModEntities.register(modEventBus);

        // Register the Deferred Register to the mod event bus so blocks get registered
        BLOCKS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        ITEMS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so tabs get registered
        CREATIVE_MODE_TABS.register(modEventBus);



        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
//        modEventBus.addListener(this::addCreative);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");

        if (Config.logDirtBlock)
            LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));

        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
    }

    // Add the example block item to the building blocks tab
//    private void addCreative(BuildCreativeModeTabContentsEvent event)
//    {
//        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS)
//            event.accept(EXAMPLE_BLOCK_ITEM);
//    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
//    @SubscribeEvent
//    public void onServerStarting(ServerStartingEvent event)
//    {
//        // Do something when the server starts
//        LOGGER.info("HELLO from server starting");
//    }
//
    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            ModItemProperties.addCustomProperties();

            MenuScreens.register(ModMenuTypes.PICNIC_BASKET_MENU.get(), PicnicBasketScreen::new);
            EntityRenderers.register(ModEntities.STRAWBERRY_COW.get(), StrawberryCowRenderer::new);
            EntityRenderers.register(ModEntities.CELESTIAL_RABBIT.get(), CelestialRabbitRenderer::new);
        }
    }
}
