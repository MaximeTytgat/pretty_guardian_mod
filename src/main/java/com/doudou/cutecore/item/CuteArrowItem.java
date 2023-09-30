package com.doudou.cutecore.item;


import com.doudou.cutecore.worldgen.entity.ModEntityType;
import com.doudou.cutecore.worldgen.entity.projectile.CuteArrow;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class CuteArrowItem extends Item {

    public final float damage;

    public CuteArrowItem(Properties p_40512_, float damage) {
        super(p_40512_);
        this.damage = damage;
    }

    public CuteAbstractArrow createArrow(Level p_40513_, ItemStack p_40514_, LivingEntity p_40515_) {
        CuteArrow arrow = new CuteArrow(ModEntityType.CUTE_ARROW.get(), p_40513_);
        arrow.setBaseDamage(this.damage);
        return arrow;
    }

    public boolean isInfinite(ItemStack stack, ItemStack bow, net.minecraft.world.entity.player.Player player) {
        int enchant = net.minecraft.world.item.enchantment.EnchantmentHelper.getItemEnchantmentLevel(net.minecraft.world.item.enchantment.Enchantments.INFINITY_ARROWS, bow);
        return enchant <= 0 ? false : this.getClass() == CuteArrowItem.class;
    }
}
