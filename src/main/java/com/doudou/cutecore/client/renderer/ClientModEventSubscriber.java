package com.doudou.cutecore.client.renderer;

import com.doudou.cutecore.CuteCore;
import com.doudou.cutecore.client.renderer.entity.CuteArrowRenderer;
import com.doudou.cutecore.worldgen.entity.ModEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CuteCore.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEventSubscriber {

    @SubscribeEvent
    public static void onClientSetupEvent(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntityType.CUTE_ARROW.get(), CuteArrowRenderer::new);
    }
}
