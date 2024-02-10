package com.max.prettyguardian.entity.client.celestialrabbit;

import com.max.prettyguardian.PrettyGuardian;
import com.max.prettyguardian.entity.custom.CelestialRabbitEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;

public class CelestialRabbitCollarPearlLayer<T extends CelestialRabbitEntity, M extends CelestialRabbitModel<T>> extends RenderLayer<T, M> {
    private static final ResourceLocation CELESTIAL_RABBIT_COLLAR_LOCATION = new ResourceLocation(PrettyGuardian.MOD_ID,"textures/entity/rabbit/celestial/celestial_rabbit_pearl.png");

    public CelestialRabbitCollarPearlLayer(RenderLayerParent<T, M> p_117707_) {
        super(p_117707_);
    }

    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int p_117722_, T celestialRabbitEntity, float p_117724_, float p_117725_, float p_117726_, float p_117727_, float p_117728_, float p_117729_) {
        if (celestialRabbitEntity.isTame() && !celestialRabbitEntity.isInvisible()) {
            float[] afloat = celestialRabbitEntity.getCollarColor().getTextureDiffuseColors();
            renderColoredCutoutModel(this.getParentModel(), CELESTIAL_RABBIT_COLLAR_LOCATION, poseStack, multiBufferSource, p_117722_, celestialRabbitEntity, afloat[0], afloat[1], afloat[2]);
        }
    }
}