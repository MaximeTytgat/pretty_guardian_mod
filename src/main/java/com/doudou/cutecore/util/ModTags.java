package com.doudou.cutecore.util;

import com.doudou.cutecore.CuteCore;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> CAKE = tag("cake");

        public static final TagKey<Block> NEEDS_PINK_SAPPHIRE_TOOL = tag("needs_pink_sapphire_tool");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(CuteCore.MODID, name));
        }
    }
    public static class Items {


        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(CuteCore.MODID, name));
        }
    }
}
