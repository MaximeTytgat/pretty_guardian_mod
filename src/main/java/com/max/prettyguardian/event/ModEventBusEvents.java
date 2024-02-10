package com.max.prettyguardian.event;

import com.max.prettyguardian.PrettyGuardian;
import com.max.prettyguardian.entity.ModEntities;
import com.max.prettyguardian.entity.custom.ButterflyEntity;
import com.max.prettyguardian.entity.custom.CelestialRabbitEntity;
import com.max.prettyguardian.entity.custom.FairyEntity;
import com.max.prettyguardian.entity.custom.StrawberryCowEntity;
import com.max.prettyguardian.particle.ModParticles;
import com.max.prettyguardian.particle.custom.PinkCritParticles;
import com.max.prettyguardian.particle.custom.StarLightParticles;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PrettyGuardian.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.STRAWBERRY_COW.get(), StrawberryCowEntity.createAttributes().build());
        event.put(ModEntities.CELESTIAL_RABBIT.get(), CelestialRabbitEntity.createAttributes().build());
        event.put(ModEntities.BUTTERFLY.get(), ButterflyEntity.createAttributes().build());
        event.put(ModEntities.FAIRY.get(), FairyEntity.createAttributes().build());
    }
}