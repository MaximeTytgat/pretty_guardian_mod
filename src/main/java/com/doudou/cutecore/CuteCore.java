package com.doudou.cutecore;

import com.doudou.cutecore.blocks.CuteCoreBlock;
import com.doudou.cutecore.blocks.entity.ModBlockEntities;
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
            .icon(() -> CuteCoreItem.CHOCOLATE_HEART.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(CuteCoreItem.STYLE_CAKE.get());
                output.accept(CuteCoreBlock.THREE_STRAWBERRY_CAKE.get());
                output.accept(CuteCoreBlock.THREE_STRAWBERRY_CHOCO_CAKE.get());
                output.accept(CuteCoreBlock.CHOCOLATE_PIE.get());
                output.accept(CuteCoreItem.STRAWBERRY_SEEDS.get());
                output.accept(CuteCoreItem.STRAWBERRY.get());
                output.accept(CuteCoreItem.CHOCOLATE_STRAWBERRY.get());
                output.accept(CuteCoreItem.CHOCOLATE_HEART.get());
                output.accept(CuteCoreBlock.PINK_SAPPHIRE_BLOCK.get());
                output.accept(CuteCoreBlock.PINK_SAPPHIRE_ORE.get());
                output.accept(CuteCoreBlock.DEEPSLATE_PINK_SAPPHIRE_ORE.get());
                output.accept(CuteCoreItem.RAW_PINK_SAPPHIRE.get());
                output.accept(CuteCoreItem.PINK_SAPPHIRE.get());

                output.accept(CuteCoreItem.PINK_SAPPHIRE_SWORD.get());
                output.accept(CuteCoreItem.PINK_SAPPHIRE_PICKAXE.get());
                output.accept(CuteCoreItem.PINK_SAPPHIRE_AXE.get());
                output.accept(CuteCoreItem.PINK_SAPPHIRE_SHOVEL.get());
                output.accept(CuteCoreItem.PINK_SAPPHIRE_HOE.get());

                output.accept(CuteCoreItem.PINK_SAPPHIRE_HELMET.get());
                output.accept(CuteCoreItem.PINK_SAPPHIRE_CHESTPLATE.get());
                output.accept(CuteCoreItem.PINK_SAPPHIRE_LEGGINGS.get());
                output.accept(CuteCoreItem.PINK_SAPPHIRE_BOOTS.get());

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

                output.accept(CuteCoreItem.CUPIDON_BOW.get());
                output.accept(CuteCoreItem.HEART_ARROW.get());
                output.accept(CuteCoreItem.CUTE_WAND.get());

                output.accept(CuteCoreBlock.PICNIC_BASKET.get());
                output.accept(CuteCoreItem.SAILORMOON_OST_MUSIC_DISC.get());
                output.accept(CuteCoreItem.SAILORMOON_MOONPRIDE_MUSIC_DISC.get());
                output.accept(CuteCoreItem.LOFI_MUSIC_DISC.get());
                output.accept(CuteCoreItem.TAVERN_MUSIC_DISC.get());
                output.accept(CuteCoreItem.JAPANESE_FLUTE_MUSIC_DISC.get());

                output.accept(CuteCoreBlock.CHOCOLATE_BLOCK.get());
                output.accept(CuteCoreBlock.MARSHMELLO_BLOCK.get());
                output.accept(CuteCoreBlock.ROASTED_MARSHMELLO_BLOCK.get());
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
        }
    }
}
