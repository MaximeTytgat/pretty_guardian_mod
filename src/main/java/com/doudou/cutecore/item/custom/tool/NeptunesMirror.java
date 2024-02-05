package com.doudou.cutecore.item.custom.tool;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;

public class NeptunesMirror extends Item {

    public NeptunesMirror(Properties properties) {
        super(properties.rarity(Rarity.EPIC));
    }

    public boolean isFoil(ItemStack itemStack) {
        return true;
    }
}
