package com.max.prettyguardian.item.custom.tool;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class SpaceSwordItem extends SwordItem {
    public SpaceSwordItem(Tier tier, int damage, float speed, Properties properties) {
        super(tier, damage, speed, properties.rarity(Rarity.EPIC));
    }

    

    @Override
    public boolean isEnchantable(ItemStack itemStack) {
        return false;
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return false;
    }
}
