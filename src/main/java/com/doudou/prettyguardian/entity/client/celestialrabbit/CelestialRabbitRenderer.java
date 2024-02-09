package com.doudou.prettyguardian.entity.client.celestialrabbit;

import com.doudou.prettyguardian.PrettyGuardian;
import com.doudou.prettyguardian.entity.client.ModModelLayers;
import com.doudou.prettyguardian.entity.custom.CelestialRabbitEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class CelestialRabbitRenderer extends MobRenderer<CelestialRabbitEntity, CelestialRabbitModel<CelestialRabbitEntity>> {
    private static final ResourceLocation CELESTIAL_RABBIT_LOCATION = new ResourceLocation(PrettyGuardian.MOD_ID, "textures/entity/rabbit/celestial/celestial_rabbit.png");
    private static final ResourceLocation CELESTIAL_RABBIT_ANGRY_LOCATION = new ResourceLocation(PrettyGuardian.MOD_ID, "textures/entity/rabbit/celestial/celestial_rabbit_angry.png");

    public CelestialRabbitRenderer(EntityRendererProvider.Context context) {
        super(context, new CelestialRabbitModel<>(context.bakeLayer(ModModelLayers.CELESTIAL_RABBIT_LAYER)), 0.5f);
        this.addLayer(new CelestialRabbitGlowLayer<>(this));
        this.addLayer(new CelestialRabbitCollarLayer<>(this));
        this.addLayer(new CelestialRabbitCollarPearlLayer<>(this));
//        this.addLayer(new CelestialRabbitFlameLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(CelestialRabbitEntity celestialRabbitEntity) {
        return celestialRabbitEntity.isAngry() ? CELESTIAL_RABBIT_ANGRY_LOCATION : CELESTIAL_RABBIT_LOCATION;
    }

    @Override
    public void render(CelestialRabbitEntity p_115455_, float p_115456_, float p_115457_, PoseStack p_115458_, MultiBufferSource p_115459_, int p_115460_) {
        if ( p_115455_.isBaby() ) {
            p_115458_.scale(0.6f, 0.6f, 0.6f);
        }


        super.render(p_115455_, p_115456_, p_115457_, p_115458_, p_115459_, p_115460_);
    }
}
