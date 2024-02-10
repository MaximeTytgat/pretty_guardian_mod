package com.max.prettyguardian.entity.client.celestialrabbit;

import com.max.prettyguardian.PrettyGuardian;
import com.max.prettyguardian.entity.custom.CelestialRabbitEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class CelestialRabbitGlowLayer<T extends CelestialRabbitEntity, M extends CelestialRabbitModel<T>> extends RenderLayer<T, M> {
    private static final RenderType RABBIT_LIGHT_TEXTURE = RenderType.entityTranslucentEmissive(new ResourceLocation(PrettyGuardian.MOD_ID, "textures/entity/rabbit/celestial/celestial_rabbit_glow.png"));
    public CelestialRabbitGlowLayer(RenderLayerParent<T, M> p_116981_) {
        super(p_116981_);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int p_117258_, T p_117259_, float p_117260_, float p_117261_, float p_117262_, float p_117263_, float p_117264_, float p_117265_) {
        VertexConsumer vertexconsumer = multiBufferSource.getBuffer(this.renderType());
        this.getParentModel().renderToBuffer(poseStack, vertexconsumer, 15728640, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    }

    public RenderType renderType() {
        return RABBIT_LIGHT_TEXTURE;
    }
}
