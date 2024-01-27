package com.doudou.cutecore.entity.client.strawberryCow;

import com.doudou.cutecore.CuteCore;
import com.doudou.cutecore.blocks.CuteCoreBlock;
import com.doudou.cutecore.blocks.custom.crop.StrawberryCropBlock;
import com.doudou.cutecore.item.CuteCoreItem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.MushroomCowMushroomLayer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.MushroomCow;
import net.minecraft.world.level.block.state.BlockState;

public class StrawberryCowStrawberryLayer<T extends MushroomCow> extends MushroomCowMushroomLayer<T> {
    private final BlockRenderDispatcher blockRenderer;
    public StrawberryCowStrawberryLayer(RenderLayerParent renderLayerParent, BlockRenderDispatcher blockRenderDispatcher) {
        super(renderLayerParent, blockRenderDispatcher);
        this.blockRenderer = blockRenderDispatcher;
    }

    @Override
    public void render(PoseStack p_117256_, MultiBufferSource p_117257_, int p_117258_, T p_117259_, float p_117260_, float p_117261_, float p_117262_, float p_117263_, float p_117264_, float p_117265_) {
        if (!p_117259_.isBaby()) {
            Minecraft minecraft = Minecraft.getInstance();
            boolean flag = minecraft.shouldEntityAppearGlowing(p_117259_) && p_117259_.isInvisible();
            if (!p_117259_.isInvisible() || flag) {
                BlockState blockstate = CuteCoreBlock.STRAWBERRY_CROP_FLOWER.get().defaultBlockState();
                int i = LivingEntityRenderer.getOverlayCoords(p_117259_, 0.0F);
                BakedModel bakedmodel = this.blockRenderer.getBlockModel(blockstate);
                p_117256_.pushPose();
                p_117256_.translate(0.2F, -0.35F, 0.5F);
                p_117256_.mulPose(Axis.YP.rotationDegrees(-48.0F));
                p_117256_.scale(-0.7F, -0.7F, 0.7F);
                p_117256_.translate(-0.5F, -0.7F, -0.5F);
                this.renderMushroomBlock(p_117256_, p_117257_, p_117258_, flag, blockstate, i, bakedmodel);
                p_117256_.popPose();
                p_117256_.pushPose();
                p_117256_.translate(0.2F, -0.35F, 0.5F);
                p_117256_.mulPose(Axis.YP.rotationDegrees(42.0F));
                p_117256_.translate(0.1F, 0.0F, -0.6F);
                p_117256_.mulPose(Axis.YP.rotationDegrees(-48.0F));
                p_117256_.scale(-0.7F, -0.7F, 0.7F);
                p_117256_.translate(-0.5F, -0.7F, -0.5F);
                this.renderMushroomBlock(p_117256_, p_117257_, p_117258_, flag, blockstate, i, bakedmodel);
                p_117256_.popPose();
                p_117256_.pushPose();
                this.getParentModel().getHead().translateAndRotate(p_117256_);
                p_117256_.translate(0.0F, -0.7F, -0.2F);
                p_117256_.mulPose(Axis.YP.rotationDegrees(-78.0F));
                p_117256_.scale(-0.7F, -0.7F, 0.7F);
                p_117256_.translate(-0.5F, -0.7F, -0.5F);
                this.renderMushroomBlock(p_117256_, p_117257_, p_117258_, flag, blockstate, i, bakedmodel);
                p_117256_.popPose();
            }
        }
    }

    private void renderMushroomBlock(PoseStack p_234853_, MultiBufferSource p_234854_, int p_234855_, boolean p_234856_, BlockState p_234857_, int p_234858_, BakedModel p_234859_) {
        if (p_234856_) {
            this.blockRenderer.getModelRenderer().renderModel(p_234853_.last(), p_234854_.getBuffer(RenderType.outline(TextureAtlas.LOCATION_BLOCKS)), p_234857_, p_234859_, 0.0F, 0.0F, 0.0F, p_234855_, p_234858_);
        } else {
            this.blockRenderer.renderSingleBlock(p_234857_, p_234853_, p_234854_, p_234855_, p_234858_);
        }

    }
}
