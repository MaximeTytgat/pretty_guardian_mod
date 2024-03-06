package com.max.prettyguardian.entity.client.celestialrabbit;

import com.max.prettyguardian.PrettyGuardian;
import com.max.prettyguardian.entity.custom.CelestialRabbitEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class CelestialRabbitCollarLayer<T extends CelestialRabbitEntity, M extends CelestialRabbitModel<T>> extends RenderLayer<T, M> {
    private static final RenderType CELESTIAL_RABBIT_COLLAR_LOCATION = RenderType.entityCutoutNoCull(new ResourceLocation(PrettyGuardian.MOD_ID, "textures/entity/rabbit/celestial/celestial_rabbit_collar.png"));

    public CelestialRabbitCollarLayer(RenderLayerParent<T, M> layerParent) {
        super(layerParent);
    }

    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int p_117722_, CelestialRabbitEntity celestialRabbitEntity, float p_117724_, float p_117725_, float p_117726_, float p_117727_, float p_117728_, float p_117729_) {
        if (celestialRabbitEntity.isTame() && !celestialRabbitEntity.isInvisible()) {
            VertexConsumer vertexconsumer = multiBufferSource.getBuffer(this.renderType());
            this.getParentModel().renderToBuffer(poseStack, vertexconsumer, p_117722_, LivingEntityRenderer.getOverlayCoords(celestialRabbitEntity, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);
        }
    }

    public RenderType renderType() {
        return CELESTIAL_RABBIT_COLLAR_LOCATION;
    }
}