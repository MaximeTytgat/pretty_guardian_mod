package com.doudou.cutecore.client.renderer;

import com.doudou.cutecore.CuteCore;
import com.doudou.cutecore.client.renderer.entity.CuteArrowRenderer;
import com.doudou.cutecore.client.renderer.entity.HeartRenderer;
import com.doudou.cutecore.worldgen.entity.ModEntityType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
@Mod.EventBusSubscriber(modid = CuteCore.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEventSubscriber {
    @SubscribeEvent
    public static void doSetup(EntityRenderersEvent.RegisterRenderers event) {
        EntityRenderers.register(ModEntityType.HEART_ARROW.get(), CuteArrowRenderer::new);
        EntityRenderers.register(ModEntityType.HEART.get(), HeartRenderer::new);
    }
}