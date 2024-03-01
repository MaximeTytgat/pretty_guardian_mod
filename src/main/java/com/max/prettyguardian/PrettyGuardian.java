package com.max.prettyguardian;

import com.max.prettyguardian.blocks.PrettyGuardianBlock;
import com.max.prettyguardian.blocks.entity.ModBlockEntities;
import com.max.prettyguardian.client.gui.sreens.GemPolishingStationScreen;
import com.max.prettyguardian.client.gui.sreens.GiftBoxScreen;
import com.max.prettyguardian.client.gui.sreens.MoonAltarScreen;
import com.max.prettyguardian.effect.ModEffects;
import com.max.prettyguardian.enchantment.ModEnchantments;
import com.max.prettyguardian.entity.ModEntities;
import com.max.prettyguardian.entity.client.butterfly.ButterflyRenderer;
import com.max.prettyguardian.entity.client.celestialrabbit.CelestialRabbitRenderer;
import com.max.prettyguardian.entity.client.fairy.FairyRenderer;
import com.max.prettyguardian.entity.client.strawberryCow.StrawberryCowRenderer;
import com.max.prettyguardian.event.custom.JapChairEvent;
import com.max.prettyguardian.item.PrettyGuardianItem;
import com.max.prettyguardian.loot.ModLootModifiers;
import com.max.prettyguardian.particle.ModParticles;
import com.max.prettyguardian.client.gui.sreens.inventory.ModMenuTypes;
import com.max.prettyguardian.client.gui.sreens.PicnicBasketScreen;
import com.max.prettyguardian.potion.ModPotions;
import com.max.prettyguardian.sound.ModSounds;
import com.max.prettyguardian.util.BetterBrewingRecipe;
import com.max.prettyguardian.util.ModItemProperties;
import com.max.prettyguardian.world.entity.ai.poi.ModPoiTypes;
import com.max.prettyguardian.worldgen.entity.ModEntityType;
import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(PrettyGuardian.MOD_ID)
public class PrettyGuardian
{
    public static final String MOD_ID = "prettyguardian";
    public static final Logger LOGGER = LogUtils.getLogger();
    public PrettyGuardian()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        PrettyGuardianBlock.init();
        modEventBus.register(this);
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        PrettyGuardianItem.register(modEventBus);
        PrettyGuardianBlock.register(modEventBus);
        ModLootModifiers.register(modEventBus);
        ModEntityType.register(modEventBus);
        ModParticles.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModSounds.register(modEventBus);
        ModEntities.register(modEventBus);
        ModEnchantments.register(modEventBus);
        ModEffects.register(modEventBus);
        CreativeTab.register(modEventBus);
        ModPoiTypes.register(modEventBus);
        ModPotions.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

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

//    // You can use SubscribeEvent and let the Event Bus discover methods to call
//    @SubscribeEvent
//    public void onServerStarting(ServerStartingEvent event)
//    {
//        // Do something when the server starts
//        LOGGER.info("HELLO from server starting");
//    }

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
            MenuScreens.register(ModMenuTypes.GEM_POLISHING_MENU.get(), GemPolishingStationScreen::new);
            MenuScreens.register(ModMenuTypes.STAFF_MAGIC_TABLE_MENU.get(), MoonAltarScreen::new);
            MenuScreens.register(ModMenuTypes.GIFT_BOX_MENU.get(), GiftBoxScreen::new);
            EntityRenderers.register(ModEntities.STRAWBERRY_COW.get(), StrawberryCowRenderer::new);
            EntityRenderers.register(ModEntities.CELESTIAL_RABBIT.get(), CelestialRabbitRenderer::new);
            EntityRenderers.register(ModEntities.BUTTERFLY.get(), ButterflyRenderer::new);
            EntityRenderers.register(ModEntities.FAIRY.get(), FairyRenderer::new);
            EntityRenderers.register(ModEntityType.SEAT_JAP_CHAIR.get(), JapChairEvent.SeatJapChairRenderer::new);

            BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(Potions.WATER, PrettyGuardianItem.FAIRY_DUST.get(), ModPotions.LOVE_POTION.get()));
        }
    }
}
