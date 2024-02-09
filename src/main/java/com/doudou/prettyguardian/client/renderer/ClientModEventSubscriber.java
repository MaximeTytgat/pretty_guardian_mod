package com.doudou.prettyguardian.client.renderer;

import com.doudou.prettyguardian.PrettyGuardian;
import com.doudou.prettyguardian.client.renderer.entity.CuteArrowRenderer;
import com.doudou.prettyguardian.client.renderer.entity.HeartRenderer;
import com.doudou.prettyguardian.client.renderer.entity.StarLightRenderer;
import com.doudou.prettyguardian.worldgen.entity.ModEntityType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
@Mod.EventBusSubscriber(modid = PrettyGuardian.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEventSubscriber {
    @SubscribeEvent
    public static void doSetup(EntityRenderersEvent.RegisterRenderers event) {
        EntityRenderers.register(ModEntityType.HEART_ARROW.get(), CuteArrowRenderer::new);
        EntityRenderers.register(ModEntityType.HEART.get(), HeartRenderer::new);
        EntityRenderers.register(ModEntityType.STAR_LIGHT.get(), StarLightRenderer::new);
    }
}