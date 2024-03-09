package com.max.prettyguardian.entity.client.fairy;

import com.max.prettyguardian.PrettyGuardian;
import com.max.prettyguardian.entity.client.ModModelLayers;
import com.max.prettyguardian.entity.custom.FairyEntity;
import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import java.util.Locale;
import java.util.Map;

public class FairyRenderer extends MobRenderer<FairyEntity, FairyModel<FairyEntity>> {

    private static final Map<FairyEntity.Variant, ResourceLocation> TEXTURE_BY_TYPE = Util.make(Maps.newHashMap(), (p_242076_) -> {
        for(FairyEntity.Variant fairy$variant : FairyEntity.Variant.values()) {
            p_242076_.put(fairy$variant, new ResourceLocation(PrettyGuardian.MOD_ID, String.format(Locale.ROOT, "textures/entity/fairy/fairy_%s.png", fairy$variant.getName())));
        }
    });

    public FairyRenderer(EntityRendererProvider.Context context) {
        super(context, new FairyModel<>(context.bakeLayer(ModModelLayers.FAIRY_LAYER)), 0.25f);
        this.addLayer(new FairyGlowLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(FairyEntity fairyEntity) {
        return TEXTURE_BY_TYPE.get(fairyEntity.getVariant());
    }

    @Override
    public void render(FairyEntity fairyEntity, float p_115456_, float p_115457_, PoseStack poseStack, MultiBufferSource multiBufferSource, int p_115460_) {
        if (fairyEntity.isBaby()) {
            poseStack.scale(0.6f, 0.6f, 0.6f);
        }

        super.render(fairyEntity, p_115456_, p_115457_, poseStack, multiBufferSource, p_115460_);
    }
}
