package com.max.prettyguardian.util;

import com.max.prettyguardian.PrettyGuardian;
import com.max.prettyguardian.item.PrettyGuardianItem;
import net.mehvahdjukaar.moonlight.api.platform.ForgeHelper;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class ModItemProperties {

        public static void addCustomProperties() {
            makeBow(PrettyGuardianItem.CUPIDON_BOW.get());

            ItemProperties.register(PrettyGuardianItem.NEPTUNES_MIRROR.get(), new ResourceLocation(PrettyGuardian.MOD_ID, "using"),
                    (stack, world, entity, s) -> entity != null && entity.isUsingItem() && ForgeHelper.areStacksEqual(stack, entity.getUseItem(), true) ? 1.0F : 0.0F);
        }


        private static void makeBow(Item item) {
            ItemProperties.register(item, new ResourceLocation("pull"), (p_174635_, p_174636_, p_174637_, p_174638_) -> {
                if (p_174637_ == null) {
                    return 0.0F;
                } else {
                    return p_174637_.getUseItem() != p_174635_ ? 0.0F : (float)(p_174635_.getUseDuration() - p_174637_.getUseItemRemainingTicks()) / 20.0F;
                }
            });
            ItemProperties.register(item, new ResourceLocation("pulling"), (p_174630_, p_174631_, p_174632_, p_174633_) -> {
                return p_174632_ != null && p_174632_.isUsingItem() && p_174632_.getUseItem() == p_174630_ ? 1.0F : 0.0F;
            });
        }


}
