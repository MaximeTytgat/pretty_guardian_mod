package com.doudou.cutecore.item.client;

import com.doudou.cutecore.CuteCore;
import com.doudou.cutecore.item.custom.PinkSapphireArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class PinkSapphireArmorModel extends GeoModel<PinkSapphireArmorItem> {
    @Override
    public ResourceLocation getModelResource(PinkSapphireArmorItem animatable) {
        return new ResourceLocation(CuteCore.MOD_ID, "geo/pink_sapphire_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(PinkSapphireArmorItem animatable) {
        return new ResourceLocation(CuteCore.MOD_ID, "textures/armor/pink_sapphire_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(PinkSapphireArmorItem animatable) {
        return new ResourceLocation(CuteCore.MOD_ID, "animations/pink_sapphire_armor.animation.json");
    }
}
