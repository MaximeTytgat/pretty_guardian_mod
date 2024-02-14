package com.max.prettyguardian.effect;

import com.max.prettyguardian.particle.ModParticles;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

public class LoveEffect extends MobEffect {
    protected LoveEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }

    @Override
    public void applyEffectTick(LivingEntity livingEntity, int pAmplifier) {
        Random random = new Random();

        if (random.nextInt() > 4) {

            Random random1 = new Random();
            float f = random1.nextFloat() * 0.5F - 0.25F;

            Vec3 look = livingEntity.getLookAngle();
            double angleRadians = Math.atan2(look.z, look.x);
            double angleDegrees = Math.toDegrees(angleRadians);

            // random number between 0 and 360
            angleDegrees += random1.nextInt(360);

            float distance = 0.5F;
            double offsetX = Math.cos(Math.toRadians(angleDegrees)) * distance;
            double offsetZ = Math.sin(Math.toRadians(angleDegrees)) * distance;

            livingEntity.level().addParticle(ModParticles.PINK_HEART_PARTICLES.get(), livingEntity.getX() + offsetX, livingEntity.getY() + 2 + f, livingEntity.getZ() + offsetZ, 0.0D, 0.0D, 0.0D);
        }
        super.applyEffectTick(livingEntity, pAmplifier);
    }

    @Override
    public boolean isDurationEffectTick(int p_19455_, int p_19456_) {
        return true;
    }
}
