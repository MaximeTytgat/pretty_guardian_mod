package com.doudou.prettyguardian.worldgen.entity.projectile;


import com.doudou.prettyguardian.particle.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

public class StarLightEntity extends Projectile {
    public double xPower;
    public double yPower;
    public double zPower;
    private final float baseDamage;


    public StarLightEntity(EntityType<StarLightEntity> entityType, Level world) {
        super(entityType, world);
        this.baseDamage = 8.0F;
    }

    public StarLightEntity(EntityType<StarLightEntity> entityType, LivingEntity shooter, Level world, float damage) {
        super(entityType, world);
        this.baseDamage = damage;
    }

    protected void defineSynchedData() {
    }

    public boolean shouldRenderAtSqrDistance(double p_36837_) {
        double d0 = this.getBoundingBox().getSize() * 4.0D;
        if (Double.isNaN(d0)) {
            d0 = 4.0D;
        }

        d0 *= 64.0D;
        return p_36837_ < d0 * d0;
    }

    protected float getEyeHeight(Pose pose, EntityDimensions entityDimensions) {
        return 0.01F;
    }

    public void tick() {
        Entity entity = this.getOwner();
        if (this.level().isClientSide || (entity == null || !entity.isRemoved()) && this.level().hasChunkAt(this.blockPosition())) {
            super.tick();

            HitResult hitresult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
            if (hitresult.getType() != HitResult.Type.MISS && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, hitresult)) {
                this.onHit(hitresult);
            }

            this.checkInsideBlocks();

            Vec3 vec3 = this.getDeltaMovement();
            if (this.xRotO == 0.0F && this.yRotO == 0.0F) {
                double d0 = vec3.horizontalDistance();
                this.setYRot((float)(Mth.atan2(vec3.x, vec3.z) * (double)(180F / (float)Math.PI)));
                this.setXRot((float)(Mth.atan2(vec3.y, d0) * (double)(180F / (float)Math.PI)));
                this.yRotO = this.getYRot();
                this.xRotO = this.getXRot();
            }


            double d5 = vec3.x;
            double d6 = vec3.y;
            double d1 = vec3.z;
            double d7 = this.getX() + d5;
            double d2 = this.getY() + d6;
            double d3 = this.getZ() + d1;
            double d4 = vec3.horizontalDistance();
            this.setYRot((float)(Mth.atan2(-d5, -d1) * (double)(180F / (float)Math.PI)));

            this.setXRot((float)(Mth.atan2(d6, d4) * (double)(180F / (float)Math.PI)));
            this.setXRot(lerpRotation(this.xRotO, this.getXRot()));
            this.setYRot(lerpRotation(this.yRotO, this.getYRot()));
            float f = 0.99F;
            float f1 = 0.05F;

            this.setDeltaMovement(vec3.scale((double)f));
            Vec3 vec34 = this.getDeltaMovement();
            this.setDeltaMovement(vec34.x, vec34.y - (double)0.02F, vec34.z);



            this.setPos(d7, d2, d3);
            this.checkInsideBlocks();

            Random random = new Random();
            double randx = -0.5 + (random.nextDouble() * 1);
            double randy = -0.5 + (random.nextDouble() * 1);
            double randz = -0.5 + (random.nextDouble() * 1);

            this.level().addParticle(ModParticles.STAR_LIGHT_PARTICLES.get(),
                    (randx + this.getX() + d5),
                    (randy + this.getY() + d6),
                    (randz + this.getZ() + d1),
                    -d5/3, -d6/3, -d1/3);
        } else {
            this.discard();
        }
        this.baseTick();
    }

    @Override
    protected void onHit(HitResult hitresult) {
        HitResult.Type hitresult$type = hitresult.getType();
        if (hitresult$type == HitResult.Type.ENTITY) {
            this.onHitEntity((EntityHitResult)hitresult);
            this.level().gameEvent(GameEvent.PROJECTILE_LAND, hitresult.getLocation(), GameEvent.Context.of(this, (BlockState)null));
        } else if (hitresult$type == HitResult.Type.BLOCK) {
            BlockHitResult blockhitresult = (BlockHitResult)hitresult;
            this.onHitBlock(blockhitresult);
            BlockPos blockpos = blockhitresult.getBlockPos();
            this.level().gameEvent(GameEvent.PROJECTILE_LAND, blockpos, GameEvent.Context.of(this, this.level().getBlockState(blockpos)));
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        Entity entity = entityHitResult.getEntity();
        Entity entity1 = this.getOwner();
        LivingEntity livingentity = entity1 instanceof LivingEntity ? (LivingEntity)entity1 : null;
        entity.hurt(this.damageSources().mobProjectile(this, livingentity), this.baseDamage);

        this.discard();
    }

    @Override
    protected void onHitBlock(BlockHitResult p_37258_) {
        this.discard();
    }

    protected boolean canHitEntity(Entity p_36842_) {
        return super.canHitEntity(p_36842_) && !p_36842_.noPhysics;
    }

    public void addAdditionalSaveData(CompoundTag p_36848_) {
        super.addAdditionalSaveData(p_36848_);
        p_36848_.put("power", this.newDoubleList(new double[]{this.xPower, this.yPower, this.zPower}));
    }

    public void readAdditionalSaveData(CompoundTag p_36844_) {
        super.readAdditionalSaveData(p_36844_);
        if (p_36844_.contains("power", 9)) {
            ListTag listtag = p_36844_.getList("power", 6);
            if (listtag.size() == 3) {
                this.xPower = listtag.getDouble(0);
                this.yPower = listtag.getDouble(1);
                this.zPower = listtag.getDouble(2);
            }
        }

    }

    public boolean isPickable() {
        return true;
    }

    public float getPickRadius() {
        return 1.0F;
    }

    public boolean hurt(DamageSource damageSource, float p_36840_) {
        if (this.isInvulnerableTo(damageSource)) {
            return false;
        } else {
            this.markHurt();
            Entity entity = damageSource.getEntity();
            if (entity != null) {
                if (!this.level().isClientSide) {
                    Vec3 vec3 = entity.getLookAngle();
                    this.setDeltaMovement(vec3);
                    this.xPower = vec3.x * 0.1D;
                    this.yPower = vec3.y * 0.1D;
                    this.zPower = vec3.z * 0.1D;
                    this.setOwner(entity);
                }

                return true;
            } else {
                return false;
            }
        }
    }

    public float getLightLevelDependentMagicValue() {
        return 0.6F;
    }

    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        Entity entity = this.getOwner();
        int i = entity == null ? 0 : entity.getId();
        return new ClientboundAddEntityPacket(this.getId(), this.getUUID(), this.getX(), this.getY(), this.getZ(), this.getXRot(), this.getYRot(), this.getType(), i, new Vec3(this.xPower, this.yPower, this.zPower), 0.0D);
    }

    public void recreateFromPacket(ClientboundAddEntityPacket p_150128_) {
        super.recreateFromPacket(p_150128_);
        double d0 = p_150128_.getXa();
        double d1 = p_150128_.getYa();
        double d2 = p_150128_.getZa();
        double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
        if (d3 != 0.0D) {
            this.xPower = d0 / d3 * 0.1D;
            this.yPower = d1 / d3 * 0.1D;
            this.zPower = d2 / d3 * 0.1D;
        }

    }
}