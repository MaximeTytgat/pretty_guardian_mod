package com.doudou.prettyguardian.event;

import com.doudou.prettyguardian.PrettyGuardian;
import com.doudou.prettyguardian.entity.ModEntities;
import com.doudou.prettyguardian.entity.custom.ButterflyEntity;
import com.doudou.prettyguardian.entity.custom.CelestialRabbitEntity;
import com.doudou.prettyguardian.entity.custom.FairyEntity;
import com.doudou.prettyguardian.entity.custom.StrawberryCowEntity;
import com.doudou.prettyguardian.particle.ModParticles;
import com.doudou.prettyguardian.particle.custom.PinkCritParticles;
import com.doudou.prettyguardian.particle.custom.StarLightParticles;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PrettyGuardian.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
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
        event.put(ModEntities.FAIRY.get(), FairyEntity.createAttributes().build());
    }
}