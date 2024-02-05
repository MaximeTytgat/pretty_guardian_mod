package com.doudou.cutecore.entity.client.butterfly;

import com.doudou.cutecore.CuteCore;
import com.doudou.cutecore.entity.client.ModModelLayers;
import com.doudou.cutecore.entity.custom.ButterflyEntity;
import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.axolotl.Axolotl;

import java.util.Locale;
import java.util.Map;

public class ButterflyRenderer extends MobRenderer<ButterflyEntity, ButterflyModel<ButterflyEntity>> {
    private static final Map<ButterflyEntity.Variant, ResourceLocation> TEXTURE_BY_TYPE = Util.make(Maps.newHashMap(), (p_242076_) -> {
        for(ButterflyEntity.Variant butterfly$variant : ButterflyEntity.Variant.values()) {
            p_242076_.put(butterfly$variant, new ResourceLocation(CuteCore.MOD_ID, String.format(Locale.ROOT, "textures/entity/butterfly/%s_butterfly.png", butterfly$variant.getName())));
        }

    });

    public ButterflyRenderer(EntityRendererProvider.Context context) {
        super(context, new ButterflyModel<>(context.bakeLayer(ModModelLayers.BUTTERFLY_LAYER)), 0.25f);
    }

    @Override
    public ResourceLocation getTextureLocation(ButterflyEntity butterflyEntity) {
        return TEXTURE_BY_TYPE.get(butterflyEntity.getVariant());
    }

    @Override
    public void render(ButterflyEntity butterflyEntity, float p_115456_, float p_115457_, PoseStack poseStack, MultiBufferSource multiBufferSource, int p_115460_) {
        if (butterflyEntity.isBaby()) {
            poseStack.scale(0.6f, 0.6f, 0.6f);
        }

        super.render(butterflyEntity, p_115456_, p_115457_, poseStack, multiBufferSource, p_115460_);
    }
}
