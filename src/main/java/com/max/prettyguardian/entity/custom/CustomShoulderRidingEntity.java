package com.max.prettyguardian.entity.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.level.Level;

public abstract class CustomShoulderRidingEntity extends TamableAnimal {
    private static final int RIDE_COOLDOWN = 5000;
    private int rideCooldownCounter;

    protected CustomShoulderRidingEntity(EntityType<? extends CustomShoulderRidingEntity> entityType, Level level) {
        super(entityType, level);
    }

    public boolean setEntityOnShoulder(ServerPlayer p_29896_) {
        CompoundTag $$1 = new CompoundTag();
        $$1.putString("id", this.getEncodeId());
        this.saveWithoutId($$1);
        if (p_29896_.setEntityOnShoulder($$1)) {
            this.discard();
            return true;
        } else {
            return false;
        }
    }

    public void tick() {
        ++this.rideCooldownCounter;
        super.tick();
    }

    public boolean canSitOnShoulder() {
        return this.rideCooldownCounter > 5000;
    }
}
