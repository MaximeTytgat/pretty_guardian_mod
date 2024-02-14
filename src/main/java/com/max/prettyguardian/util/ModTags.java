package com.max.prettyguardian.util;

import com.max.prettyguardian.PrettyGuardian;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> CAKE = tag("cake");
        public static final TagKey<Block> JAP_TABLE = tag("jap_table");

        public static final TagKey<Block> SPACE_SWORD = tag("space_sword");
        public static final TagKey<Block> NEEDS_PINK_SAPPHIRE_TOOL = tag("needs_pink_sapphire_tool");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(PrettyGuardian.MOD_ID, name));
        }
    }
    public static class Items {

        public static final TagKey<Item> HEART_ARROWS = tag("heart_arrows");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(PrettyGuardian.MOD_ID, name));
        }
    }
}
