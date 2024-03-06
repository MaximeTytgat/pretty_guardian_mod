package com.max.prettyguardian.entityOnShoulder;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerEntityOnShoulderProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<PlayerEntityOnShoulder> PLAYER_ENTITY_ON_SHOULDER_CAPABILITY = CapabilityManager.get(new CapabilityToken<PlayerEntityOnShoulder>() {});
    private PlayerEntityOnShoulder playerEntityOnShoulder = null;
    private final LazyOptional<PlayerEntityOnShoulder> lazyOptional = LazyOptional.of(this::createPlayerEntityOnShoulder);

    private PlayerEntityOnShoulder createPlayerEntityOnShoulder() {
        if (playerEntityOnShoulder == null) {
            this.playerEntityOnShoulder = new PlayerEntityOnShoulder();
        }

        return this.playerEntityOnShoulder;
    }

    public PlayerEntityOnShoulder getPlayerEntityOnShoulder() {
        return createPlayerEntityOnShoulder();
    }


    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction direction) {
        if (capability == PLAYER_ENTITY_ON_SHOULDER_CAPABILITY) {
            return lazyOptional.cast();
        }

        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        createPlayerEntityOnShoulder().saveNBTData(tag);

        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag compoundTag) {
        createPlayerEntityOnShoulder().loadNBTData(compoundTag);
    }
}
