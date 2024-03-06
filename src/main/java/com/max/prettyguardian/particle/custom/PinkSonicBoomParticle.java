package com.max.prettyguardian.particle.custom;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.HugeExplosionParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PinkSonicBoomParticle extends HugeExplosionParticle {
    protected PinkSonicBoomParticle(ClientLevel clientLevel, double p_234029_, double p_234030_, double p_234031_, double p_234032_, SpriteSet p_234033_) {
        super(clientLevel, p_234029_, p_234030_, p_234031_, p_234032_, p_234033_);
        this.lifetime = 16;
        this.quadSize = 1F;
        this.setSpriteFromAge(p_234033_);
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet p_234036_) {
            this.sprites = p_234036_;
        }

        public Particle createParticle(SimpleParticleType p_234047_, ClientLevel p_234048_, double p_234049_, double p_234050_, double p_234051_, double p_234052_, double p_234053_, double p_234054_) {
            return new PinkSonicBoomParticle(p_234048_, p_234049_, p_234050_, p_234051_, p_234052_, this.sprites);
        }
    }
}
