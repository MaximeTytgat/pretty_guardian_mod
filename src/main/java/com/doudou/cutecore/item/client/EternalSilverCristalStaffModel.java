package com.doudou.cutecore.item.client;

import com.doudou.cutecore.CuteCore;
import com.doudou.cutecore.item.custom.tool.EternalSilverCristalStaffitem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EternalSilverCristalStaffModel extends GeoModel<EternalSilverCristalStaffitem> {
    @Override
    public ResourceLocation getModelResource(EternalSilverCristalStaffitem animatable) {
        return new ResourceLocation(CuteCore.MOD_ID, "geo/eternal_silver_cristal_staff.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EternalSilverCristalStaffitem animatable) {
        return new ResourceLocation(CuteCore.MOD_ID, "textures/item/eternal_silver_cristal_staff.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EternalSilverCristalStaffitem animatable) {
        return new ResourceLocation(CuteCore.MOD_ID, "animations/eternal_silver_cristal_staff.animation.json");
    }
}
