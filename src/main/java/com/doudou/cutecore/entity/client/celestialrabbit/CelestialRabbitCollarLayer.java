package com.doudou.cutecore.entity.client.celestialrabbit;

import com.doudou.cutecore.CuteCore;
import com.doudou.cutecore.entity.custom.CelestialRabbitEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.WolfModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Wolf;

public class CelestialRabbitCollarLayer<T extends CelestialRabbitEntity, M extends CelestialRabbitModel<T>> extends RenderLayer<T, M> {
    private static final RenderType CELESTIAL_RABBIT_COLLAR_LOCATION = RenderType.entityCutout(new ResourceLocation(CuteCore.MOD_ID, "textures/entity/rabbit/celestial/celestial_rabbit_collar.png"));

    public CelestialRabbitCollarLayer(RenderLayerParent<T, M> p_117707_) {
        super(p_117707_);
    }

    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int p_117722_, CelestialRabbitEntity celestialRabbitEntity, float p_117724_, float p_117725_, float p_117726_, float p_117727_, float p_117728_, float p_117729_) {
        if (celestialRabbitEntity.isTame() && !celestialRabbitEntity.isInvisible()) {
            VertexConsumer vertexconsumer = multiBufferSource.getBuffer(this.renderType());
            this.getParentModel().renderToBuffer(poseStack, vertexconsumer, 15728640, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        }
    }

    public RenderType renderType() {
        return CELESTIAL_RABBIT_COLLAR_LOCATION;
    }
}