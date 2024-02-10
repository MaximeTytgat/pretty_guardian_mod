package com.max.prettyguardian.item.client;

import com.max.prettyguardian.PrettyGuardian;
import com.max.prettyguardian.item.custom.PinkSapphireArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class PinkSapphireArmorModel extends GeoModel<PinkSapphireArmorItem> {
    @Override
    public ResourceLocation getModelResource(PinkSapphireArmorItem animatable) {
        return new ResourceLocation(PrettyGuardian.MOD_ID, "geo/pink_sapphire_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(PinkSapphireArmorItem animatable) {
        return new ResourceLocation(PrettyGuardian.MOD_ID, "textures/armor/pink_sapphire_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(PinkSapphireArmorItem animatable) {
        return new ResourceLocation(PrettyGuardian.MOD_ID, "animations/pink_sapphire_armor.animation.json");
    }
}
