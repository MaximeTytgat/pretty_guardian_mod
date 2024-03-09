package com.max.prettyguardian.entity.client.fairy;

import com.google.common.collect.Maps;
import com.max.prettyguardian.PrettyGuardian;
import com.max.prettyguardian.entity.custom.FairyEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

import java.util.Locale;
import java.util.Map;

public class FairyGlowLayer<T extends FairyEntity, M extends FairyModel<T>> extends RenderLayer<T, M> {
    private static final Map<FairyEntity.Variant, RenderType> RENDER_BY_TYPE = Util.make(Maps.newHashMap(), (p_242076_) -> {
        for(FairyEntity.Variant fairy$variant : FairyEntity.Variant.values()) {
            p_242076_.put(fairy$variant, RenderType.entityTranslucentEmissive(new ResourceLocation(PrettyGuardian.MOD_ID, String.format(Locale.ROOT, "textures/entity/fairy/fairy_%s.png", fairy$variant.getName()))));
        }
    });
    public FairyGlowLayer(RenderLayerParent<T, M> p_116981_) {
        super(p_116981_);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int p_117258_, T fairyEntity, float p_117260_, float p_117261_, float p_117262_, float p_117263_, float p_117264_, float p_117265_) {
        VertexConsumer vertexconsumer = multiBufferSource.getBuffer(this.renderType(fairyEntity));
        if (fairyEntity.hasDust()) {
            this.getParentModel().renderToBuffer(poseStack, vertexconsumer, 15728640, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        }
    }

    public RenderType renderType(FairyEntity fairyEntity) {
        return RENDER_BY_TYPE.get(fairyEntity.getVariant());
    }
}
