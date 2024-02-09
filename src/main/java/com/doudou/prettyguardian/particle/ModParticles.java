package com.doudou.prettyguardian.particle;

import com.doudou.prettyguardian.PrettyGuardian;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModParticles {
    public static DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, PrettyGuardian.MOD_ID);

    public static final RegistryObject<SimpleParticleType> PINK_CRIT_PARTICLES = PARTICLE_TYPES.register("pink_crit_particle", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> STAR_LIGHT_PARTICLES = PARTICLE_TYPES.register("star_light_particles", () -> new SimpleParticleType(true));

    public static void register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }
}
