package com.max.prettyguardian.entity.client.celestialrabbit;

import com.max.prettyguardian.PrettyGuardian;
import com.max.prettyguardian.client.ClientPlayerEntityOnShoulderData;
import com.max.prettyguardian.entity.ModEntities;
import com.max.prettyguardian.entity.client.ModModelLayers;
import com.max.prettyguardian.entity.custom.CelestialRabbitEntity;
import com.max.prettyguardian.entityOnShoulder.PlayerEntityOnShoulderProvider;
import com.max.prettyguardian.particle.ModParticles;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ParrotModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

import java.util.Random;


public class CelestialRabbitOnShoulderLayer<T extends Player> extends RenderLayer<T, PlayerModel<T>> {
    private static final ResourceLocation CELESTIAL_RABBIT_LOCATION = new ResourceLocation(PrettyGuardian.MOD_ID, "textures/entity/rabbit/celestial/celestial_rabbit.png");
    private final CelestialRabbitModel model;

    public CelestialRabbitOnShoulderLayer(RenderLayerParent<T, PlayerModel<T>> parent/*, EntityModelSet modelSet*/) {
        super(parent);
        this.model = new CelestialRabbitModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModModelLayers.CELESTIAL_RABBIT_LAYER));
    }

    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, T player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        this.render(poseStack, bufferSource, light, player, limbSwing, limbSwingAmount, netHeadYaw, headPitch, true);
//        this.render(poseStack, bufferSource, light, player, limbSwing, limbSwingAmount, netHeadYaw, headPitch, false);
    }

    private void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, T player, float limbSwing, float limbSwingAmount, float netHeadYaw, float headPitch, boolean leftShoulder) {
        if(ClientPlayerEntityOnShoulderData.getHasEntityOnShoulder()) {

            CelestialRabbitEntity pet = ModEntities.CELESTIAL_RABBIT.get().create(Minecraft.getInstance().level);
            poseStack.pushPose();
            poseStack.translate(leftShoulder ? 0.4F : -0.4F, player.isCrouching() ? -1.3F : -1.5F, 0.0F);
            poseStack.scale(0.75f, 0.75f, 0.75f);
            poseStack.translate(0, -1.5, 0);

            VertexConsumer vertexConsumer = bufferSource.getBuffer(this.model.renderType(CELESTIAL_RABBIT_LOCATION)/*.renderType(ParrotRenderer.getVariantTexture(variant))*/);
            this.model.renderOnShoulder(poseStack, vertexConsumer, light, OverlayTexture.NO_OVERLAY, limbSwing, limbSwingAmount, netHeadYaw, headPitch, player.tickCount);


            poseStack.popPose();
        }
    }
}