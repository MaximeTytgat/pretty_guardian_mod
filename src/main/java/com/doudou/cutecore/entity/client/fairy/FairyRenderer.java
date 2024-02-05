package com.doudou.cutecore.entity.client.fairy;

import com.doudou.cutecore.CuteCore;
import com.doudou.cutecore.entity.client.ModModelLayers;
import com.doudou.cutecore.entity.custom.FairyEntity;
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
            p_242076_.put(fairy$variant, new ResourceLocation(CuteCore.MOD_ID, String.format(Locale.ROOT, "textures/entity/fairy/fairy_%s.png", fairy$variant.getName())));
        }
    });

    private static final Map<FairyEntity.Variant, ResourceLocation> DUST_TEXTURE_BY_TYPE = Util.make(Maps.newHashMap(), (p_242076_) -> {
        for(FairyEntity.Variant fairy$variant : FairyEntity.Variant.values()) {
            p_242076_.put(fairy$variant, new ResourceLocation(CuteCore.MOD_ID, String.format(Locale.ROOT, "textures/entity/fairy/fairy_%s_dust.png", fairy$variant.getName())));
        }
    });

    public FairyRenderer(EntityRendererProvider.Context context) {
        super(context, new FairyModel<>(context.bakeLayer(ModModelLayers.FAIRY_LAYER)), 0.25f);
    }

    @Override
    public ResourceLocation getTextureLocation(FairyEntity fairyEntity) {
        return fairyEntity.hasDust() ?  DUST_TEXTURE_BY_TYPE.get(fairyEntity.getVariant()) : TEXTURE_BY_TYPE.get(fairyEntity.getVariant());
    }

    @Override
    public void render(FairyEntity fairyEntity, float p_115456_, float p_115457_, PoseStack poseStack, MultiBufferSource multiBufferSource, int p_115460_) {
        if (fairyEntity.isBaby()) {
            poseStack.scale(0.6f, 0.6f, 0.6f);
        }

        super.render(fairyEntity, p_115456_, p_115457_, poseStack, multiBufferSource, p_115460_);
    }
}
