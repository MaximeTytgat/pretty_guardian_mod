package com.doudou.prettyguardian.event;

import com.doudou.prettyguardian.PrettyGuardian;
import com.doudou.prettyguardian.entity.client.butterfly.ButterflyModel;
import com.doudou.prettyguardian.entity.client.celestialrabbit.CelestialRabbitModel;
import com.doudou.prettyguardian.entity.client.ModModelLayers;
import com.doudou.prettyguardian.entity.client.fairy.FairyModel;
import net.minecraft.client.model.CowModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PrettyGuardian.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.STRAWBERRY_COW_LAYER, CowModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.CELESTIAL_RABBIT_LAYER, CelestialRabbitModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.BUTTERFLY_LAYER, ButterflyModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.FAIRY_LAYER, FairyModel::createBodyLayer);
    }
}