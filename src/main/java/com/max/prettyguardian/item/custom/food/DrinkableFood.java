package com.max.prettyguardian.item.custom.food;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;

public class DrinkableFood extends Item {
    public DrinkableFood(Properties properties) {
        super(properties);
    }

    public UseAnim getUseAnimation(ItemStack itemStack) {
        return UseAnim.DRINK;
    }
}
