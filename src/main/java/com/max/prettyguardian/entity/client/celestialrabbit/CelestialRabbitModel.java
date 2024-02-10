package com.max.prettyguardian.entity.client.celestialrabbit;

import com.max.prettyguardian.PrettyGuardian;
import com.max.prettyguardian.entity.animations.ModAnimationDefinitions;
import com.max.prettyguardian.entity.custom.CelestialRabbitEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;


public class CelestialRabbitModel<T extends Entity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(PrettyGuardian.MOD_ID, "celestial_rabbit"), "main");
	private final ModelPart celestial_rabbit;
	private final ModelPart head;
	public final ModelPart flame;

	public CelestialRabbitModel(ModelPart root) {
		this.celestial_rabbit = root.getChild("root");
		this.head = this.celestial_rabbit.getChild("body").getChild("head");
		this.flame = this.head.getChild("flame");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(28, 0).addBox(-3.0F, -3.0F, -6.0F, 6.0F, 6.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 17).addBox(-4.0F, -3.0F, -6.0F, 8.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -6.0F));

		PartDefinition nose = head.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(16, 30).addBox(-2.0F, -0.955F, -2.0F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, -6.0F));

		PartDefinition small_ear_left = head.addOrReplaceChild("small_ear_left", CubeListBuilder.create().texOffs(24, 49).addBox(-1.0F, -2.0F, -0.5F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -3.0F, -3.5F));

		PartDefinition small_ear_right = head.addOrReplaceChild("small_ear_right", CubeListBuilder.create().texOffs(24, 49).mirror().addBox(-1.0F, -2.0F, -0.5F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-3.0F, -3.0F, -3.5F));

		PartDefinition ear_left = head.addOrReplaceChild("ear_left", CubeListBuilder.create().texOffs(12, 39).addBox(-0.005F, -1.5F, -2.0F, 2.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -1.5F, -3.0F));

		PartDefinition ear_left2 = ear_left.addOrReplaceChild("ear_left2", CubeListBuilder.create().texOffs(44, 29).addBox(-1.0F, 0.0F, -2.25F, 2.0F, 7.0F, 4.5F, new CubeDeformation(0.0F))
				.texOffs(0, 30).addBox(-1.5F, 2.0F, -2.75F, 3.0F, 1.0F, 5.5F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 3.5F, 0.0F));

		PartDefinition ear_right = head.addOrReplaceChild("ear_right", CubeListBuilder.create().texOffs(12, 39).mirror().addBox(-1.995F, -1.5F, -2.0F, 2.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-4.0F, -1.5F, -3.0F));

		PartDefinition ear_right2 = ear_right.addOrReplaceChild("ear_right2", CubeListBuilder.create().texOffs(44, 29).mirror().addBox(-1.0F, 0.0F, -2.25F, 2.0F, 7.0F, 4.5F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(0, 30).addBox(-1.5F, 2.0F, -2.75F, 3.0F, 1.0F, 5.5F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 3.5F, 0.0F));

		PartDefinition flame = head.addOrReplaceChild("flame", CubeListBuilder.create().texOffs(54, 48).addBox(-2.5F, -5.0F, 0.0F, 5.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(54, 43).addBox(0.0F, -5.0F, -2.5F, 0.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, -2.5F));

		PartDefinition legs = body.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, -0.5F));

		PartDefinition leg_front = legs.addOrReplaceChild("leg_front", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, -3.5F));

		PartDefinition leg2 = leg_front.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(0, 49).addBox(-0.005F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 0.0F, 0.0F));

		PartDefinition leg3 = leg_front.addOrReplaceChild("leg3", CubeListBuilder.create().texOffs(36, 39).addBox(-1.995F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 0.0F, 0.0F));

		PartDefinition leg_back = legs.addOrReplaceChild("leg_back", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, 3.5F));

		PartDefinition leg0 = leg_back.addOrReplaceChild("leg0", CubeListBuilder.create().texOffs(16, 49).addBox(-0.005F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 0.0F, 0.0F));

		PartDefinition leg1 = leg_back.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(8, 49).addBox(-1.995F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 0.0F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(48, 17).addBox(-2.0F, 0.0F, -1.75F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 4.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition tail2 = tail.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, 0.0F, -3.5F, 7.0F, 9.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, 0.25F));

		PartDefinition tail3 = tail2.addOrReplaceChild("tail3", CubeListBuilder.create().texOffs(28, 17).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 8.0F, 0.0F));

		PartDefinition tail4 = tail3.addOrReplaceChild("tail4", CubeListBuilder.create().texOffs(24, 39).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);

		this.animateWalk(ModAnimationDefinitions.CELESTIAL_RABBIT_WALK, limbSwing, limbSwingAmount, 1f, 2.5F);
		this.animate(((CelestialRabbitEntity) entity).idleAnimationState, ModAnimationDefinitions.CELESTIAL_RABBIT_IDLE, ageInTicks, 1F);
		this.animate(((CelestialRabbitEntity) entity).sitAnimationState, ModAnimationDefinitions.CELESTIAL_RABBIT_SIT, ageInTicks, 1F);
	}

	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

		this.head.zRot = pNetHeadYaw * ((float)Math.PI / 180F);
//		this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
	}


	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		celestial_rabbit.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return celestial_rabbit;
	}
}