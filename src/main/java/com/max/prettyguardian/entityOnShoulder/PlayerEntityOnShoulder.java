package com.max.prettyguardian.entityOnShoulder;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.DyeColor;

public class PlayerEntityOnShoulder {
    private EntityType<?> entityType;
    private DyeColor collarColor;
    private Component name;

    public PlayerEntityOnShoulder() {}

    public void setEntityOnShoulder(EntityType<?> entityType, DyeColor collarColor, Component name) {
        this.entityType = entityType;
        this.collarColor = collarColor;
        this.name = name;
    }
    public EntityType<?> getEntityType() {
        return entityType;
    }

    public DyeColor getCollarColor() {
        return collarColor;
    }
    public Component getName() {
        return name;
    }

    public void copyFrom(PlayerEntityOnShoulder source) {
        entityType = source.entityType;
        collarColor = source.collarColor;
        name = source.name;
    }

    public void saveNBTData(CompoundTag tag) {
        if (entityType != null) tag.putString("entity_on_shoulder", EntityType.getKey(entityType).toString());
        if (collarColor != null) tag.putString("collar_color", collarColor.getName());
        if (name != null) tag.putString("name", Component.Serializer.toJson(name));
    }

    public void loadNBTData(CompoundTag tag) {
        entityType = EntityType.byString(tag.getString("entity_on_shoulder")).orElse(null);
        collarColor = DyeColor.byName(tag.getString("collar_color"), null);
        if (tag.contains("name")) name = Component.Serializer.fromJson(tag.getString("name"));
    }
}
