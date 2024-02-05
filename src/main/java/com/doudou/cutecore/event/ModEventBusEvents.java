package com.doudou.cutecore.event;

import com.doudou.cutecore.CuteCore;
import com.doudou.cutecore.entity.ModEntities;
import com.doudou.cutecore.entity.custom.ButterflyEntity;
import com.doudou.cutecore.entity.custom.CelestialRabbitEntity;
import com.doudou.cutecore.entity.custom.StrawberryCowEntity;
import com.doudou.cutecore.particle.ModParticles;
import com.doudou.cutecore.particle.custom.PinkCritParticles;
import com.doudou.cutecore.particle.custom.StarLightParticles;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CuteCore.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerParticleFactories(final RegisterParticleProvidersEvent event) {
        Minecraft.getInstance().particleEngine.register(ModParticles.PINK_CRIT_PARTICLES.get(),
                PinkCritParticles.Provider::new);

        Minecraft.getInstance().particleEngine.register(ModParticles.STAR_LIGHT_PARTICLES.get(),
                StarLightParticles.Provider::new);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.STRAWBERRY_COW.get(), StrawberryCowEntity.createAttributes().build());
        event.put(ModEntities.CELESTIAL_RABBIT.get(), CelestialRabbitEntity.createAttributes().build());
        event.put(ModEntities.BUTTERFLY.get(), ButterflyEntity.createAttributes().build());
        event.put(ModEntities.FAIRY.get(), ButterflyEntity.createAttributes().build());
    }
}