package com.max.prettyguardian.worldgen.entity.projectile;


import com.max.prettyguardian.effect.ModEffects;
import com.max.prettyguardian.item.PrettyGuardianItem;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class CuteArrowEntity extends AbstractArrow {

    private final Level level;
    private boolean hited = false;
    public CuteArrowEntity(EntityType<CuteArrowEntity> entityType, Level world) {
        super(entityType, world);
        this.level = world;
    }

    public CuteArrowEntity(EntityType<CuteArrowEntity> entityType, double x, double y, double z, Level world) {
        super(entityType, x, y, z, world);
        this.level = world;
    }

    public CuteArrowEntity(EntityType<CuteArrowEntity> entityType, LivingEntity shooter, Level world) {
        super(entityType, shooter, world);
        this.level = world;
    }
    @Override
    protected @NotNull ItemStack getPickupItem() {
        return new ItemStack(PrettyGuardianItem.HEART_ARROW.get());
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        if (entityHitResult.getEntity() == this.getOwner()) {
            return;
        }
        this.hited = true;
        super.onHitEntity(entityHitResult);

        if (entityHitResult.getEntity() instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) entityHitResult.getEntity();
            livingEntity.addEffect(new MobEffectInstance(ModEffects.LOVE.get(), 100, 1));
        }
        // this, x, y, z, explosionStrength, setsFires, breakMode
    }

    @Override
    protected void onHitBlock(BlockHitResult entityHitResult) {
        this.hited = true;
        super.onHitBlock(entityHitResult);
    }

    @Override
    protected void tickDespawn() {

    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.hited) {
            Vec3 vec3 = this.getDeltaMovement();
            double d5 = vec3.x;
            double d6 = vec3.y;
            double d1 = vec3.z;

            Random random = new Random();
            double randx = -0.5 + (random.nextDouble() * 1);
            double randy = -0.5 + (random.nextDouble() * 1);
            double randz = -0.5 + (random.nextDouble() * 1);

            this.level().addParticle(ParticleTypes.HEART,
                    randx + this.getX() + d5,
                    randy + this.getY() + d6,
                    randz + this.getZ() + d1,
                    -d5, -d6, -d1);
        }
    }
}
