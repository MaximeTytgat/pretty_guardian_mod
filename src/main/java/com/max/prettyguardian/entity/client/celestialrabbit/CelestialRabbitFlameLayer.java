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

public class CelestialRabbitFlameLayer<T extends CelestialRabbitEntity, M extends CelestialRabbitModel<T>> extends RenderLayer<T, M> {
//    private static final ResourceLocation ANIMATED_TEXTURE = new ResourceLocation(PrettyGuardian.MOD_ID, "textures/entity/rabbit/celestial/celestial_rabbit_flame.png");
    private static final ResourceLocation ANIMATED_TEXTURE = new ResourceLocation(PrettyGuardian.MOD_ID, "textures/entity/rabbit/celestial/flame.png");

    public CelestialRabbitFlameLayer(RenderLayerParent<T, M> renderLayerParent) {
        super(renderLayerParent);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, T celestialRabbitEntity, float p_117260_, float p_117261_, float p_117262_, float p_117263_, float p_117264_, float p_117265_) {
        if (!celestialRabbitEntity.isBaby()) {
            int animationTicks = celestialRabbitEntity.tickCount;
            int frameIndex = (animationTicks / 8) % 8; // Changez 8 selon le nombre de frames dans votre animation

            int minU = (int) (frameIndex / 8.0f);
            int maxU = (int) ((frameIndex + 1) / 8.0f);

            int minV = 0;
            int maxV = 1;

            VertexConsumer vertexConsumer = multiBufferSource.getBuffer(RenderType.entityCutout(ANIMATED_TEXTURE));

            poseStack.pushPose();
            poseStack.popPose();

            this.getParentModel().renderToBuffer(poseStack, vertexConsumer, 15728640, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        }
    }
}
