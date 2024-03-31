package com.max.prettyguardian.entity.client.celestialrabbit;

import com.max.prettyguardian.PrettyGuardian;
import com.max.prettyguardian.entity.custom.CelestialRabbitEntity;
import com.max.prettyguardian.item.PrettyGuardianItem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class CelestialRabbitFlameLayer<T extends CelestialRabbitEntity, M extends CelestialRabbitModel<T>> extends RenderLayer<T, M> {
//    private static final ResourceLocation ANIMATED_TEXTURE = new ResourceLocation(PrettyGuardian.MOD_ID, "textures/entity/rabbit/celestial/celestial_rabbit_flame.png");
    private static final ResourceLocation FLAME_1 = new ResourceLocation(PrettyGuardian.MOD_ID, "textures/entity/rabbit/celestial/flame_01.png");
    private static final ResourceLocation FLAME_2 = new ResourceLocation(PrettyGuardian.MOD_ID, "textures/entity/rabbit/celestial/flame_02.png");
    private static final ResourceLocation FLAME_3 = new ResourceLocation(PrettyGuardian.MOD_ID, "textures/entity/rabbit/celestial/flame_03.png");
    private static final ResourceLocation FLAME_4 = new ResourceLocation(PrettyGuardian.MOD_ID, "textures/entity/rabbit/celestial/flame_04.png");
    private static final ResourceLocation FLAME_5 = new ResourceLocation(PrettyGuardian.MOD_ID, "textures/entity/rabbit/celestial/flame_05.png");
    private static final ResourceLocation FLAME_6 = new ResourceLocation(PrettyGuardian.MOD_ID, "textures/entity/rabbit/celestial/flame_06.png");
    private static final ResourceLocation FLAME_7 = new ResourceLocation(PrettyGuardian.MOD_ID, "textures/entity/rabbit/celestial/flame_07.png");
    private static final ResourceLocation FLAME_8 = new ResourceLocation(PrettyGuardian.MOD_ID, "textures/entity/rabbit/celestial/flame_08.png");

    private static final ResourceLocation COLOR_FLAME_1 = new ResourceLocation(PrettyGuardian.MOD_ID, "textures/entity/rabbit/celestial/color_flame_01.png");
    private static final ResourceLocation COLOR_FLAME_2 = new ResourceLocation(PrettyGuardian.MOD_ID, "textures/entity/rabbit/celestial/color_flame_02.png");
    private static final ResourceLocation COLOR_FLAME_3 = new ResourceLocation(PrettyGuardian.MOD_ID, "textures/entity/rabbit/celestial/color_flame_03.png");
    private static final ResourceLocation COLOR_FLAME_4 = new ResourceLocation(PrettyGuardian.MOD_ID, "textures/entity/rabbit/celestial/color_flame_04.png");
    private static final ResourceLocation COLOR_FLAME_5 = new ResourceLocation(PrettyGuardian.MOD_ID, "textures/entity/rabbit/celestial/color_flame_05.png");
    private static final ResourceLocation COLOR_FLAME_6 = new ResourceLocation(PrettyGuardian.MOD_ID, "textures/entity/rabbit/celestial/color_flame_06.png");
    private static final ResourceLocation COLOR_FLAME_7 = new ResourceLocation(PrettyGuardian.MOD_ID, "textures/entity/rabbit/celestial/color_flame_07.png");
    private static final ResourceLocation COLOR_FLAME_8 = new ResourceLocation(PrettyGuardian.MOD_ID, "textures/entity/rabbit/celestial/color_flame_08.png");

    public CelestialRabbitFlameLayer(RenderLayerParent<T, M> renderLayerParent) {
        super(renderLayerParent);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, T celestialRabbitEntity, float p_117260_, float p_117261_, float p_117262_, float p_117263_, float p_117264_, float p_117265_) {
        if (!celestialRabbitEntity.isBaby() && !celestialRabbitEntity.isInvisible() && celestialRabbitEntity.level().isClientSide) {
            int animationTicks = celestialRabbitEntity.tickCount;

            ResourceLocation flameTexture = switch (animationTicks % 16) {
                case 2, 3 -> /*celestialRabbitEntity.isTame() ? COLOR_FLAME_2 :*/ FLAME_2;
                case 4, 5 -> /*celestialRabbitEntity.isTame() ? COLOR_FLAME_3 :*/ FLAME_3;
                case 6, 7 -> /*celestialRabbitEntity.isTame() ? COLOR_FLAME_4 :*/ FLAME_4;
                case 8, 9 -> /*celestialRabbitEntity.isTame() ? COLOR_FLAME_5 :*/ FLAME_5;
                case 10, 11 -> /*celestialRabbitEntity.isTame() ? COLOR_FLAME_6 :*/ FLAME_6;
                case 12, 13 -> /*celestialRabbitEntity.isTame() ? COLOR_FLAME_7 :*/ FLAME_7;
                case 14, 15 -> /*celestialRabbitEntity.isTame() ? COLOR_FLAME_8 :*/ FLAME_8;
                default -> /*celestialRabbitEntity.isTame() ? COLOR_FLAME_1 :*/ FLAME_1;
            };

            poseStack.pushPose();
            poseStack.popPose();

//            if (celestialRabbitEntity.isTame()) {
//                float[] afloat = celestialRabbitEntity.getCollarColor().getTextureDiffuseColors();
////                renderColoredCutoutModel(this.getParentModel(), flameTexture, poseStack, multiBufferSource, 15728640, celestialRabbitEntity, afloat[0], afloat[1], afloat[2]);
//                VertexConsumer vertexConsumer = multiBufferSource.getBuffer(RenderType.entityTranslucentEmissive(flameTexture));
//                this.getParentModel().renderToBuffer(poseStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY, afloat[0], afloat[1], afloat[2], 0.8F);
//            } else {
                VertexConsumer vertexConsumer = multiBufferSource.getBuffer(RenderType.entityTranslucentEmissive(flameTexture));
                this.getParentModel().renderToBuffer(poseStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 0.8F);
//            }
        }
    }
}
