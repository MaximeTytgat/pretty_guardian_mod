package com.doudou.cutecore.client.renderer.entity;


import com.doudou.cutecore.CuteCore;
import com.doudou.cutecore.worldgen.entity.projectile.CuteArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;


public class CuteArrowRenderer extends ArrowRenderer<CuteArrowEntity> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(CuteCore.MOD_ID, "textures/entity/projectiles/heart_arrow.png");

    public CuteArrowRenderer(EntityRendererProvider.Context manager) {
        super(manager);
    }

    public ResourceLocation getTextureLocation(CuteArrowEntity arrow) {
        return TEXTURE;
    }
}