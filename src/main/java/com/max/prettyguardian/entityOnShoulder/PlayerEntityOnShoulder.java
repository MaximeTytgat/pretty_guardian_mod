package com.max.prettyguardian.entityOnShoulder;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.DyeColor;

public class PlayerEntityOnShoulder {

    private String id = null;
    private EntityType<?> entityType = null;
    private DyeColor collarColor = null;
    private Component name = null;
    private boolean isInSittingPose = false;

    public void setEntityOnShoulder(String id, EntityType<?> entityType, DyeColor collarColor, Component name, boolean isInSittingPose) {
        this.entityType = entityType;
        this.collarColor = collarColor;
        this.name = name;
        this.isInSittingPose = isInSittingPose;
    }

    public void letGoEntity() {
        entityType = null;
        collarColor = null;
        name = null;
        isInSittingPose = false;
    }

    public String getId() {
        return id;
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
    public boolean isInSittingPose() {
        return isInSittingPose;
    }

    public void copyFrom(PlayerEntityOnShoulder source) {
        entityType = source.entityType;
        collarColor = source.collarColor;
        name = source.name;
        isInSittingPose = source.isInSittingPose;
    }

    public void saveNBTData(CompoundTag tag) {
        if (entityType != null) tag.putString("entity_on_shoulder", EntityType.getKey(entityType).toString());
        if (collarColor != null) tag.putString("collar_color", collarColor.getName());
        if (name != null) tag.putString("name", Component.Serializer.toJson(name));
        tag.putBoolean("is_in_sitting_pose", isInSittingPose);
    }

    public void loadNBTData(CompoundTag tag) {
        if (tag.contains("entity_on_shoulder")) entityType = EntityType.byString(tag.getString("entity_on_shoulder")).orElse(null);
        if (tag.contains("collar_color")) collarColor = DyeColor.byName(tag.getString("collar_color"), null);
        if (tag.contains("name")) name = Component.Serializer.fromJson(tag.getString("name"));
        if (tag.contains("is_in_sitting_pose")) isInSittingPose = tag.getBoolean("is_in_sitting_pose");
    }
}
