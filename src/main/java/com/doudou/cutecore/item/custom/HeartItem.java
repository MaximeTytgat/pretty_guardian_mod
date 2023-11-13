package com.doudou.cutecore.item.custom;


import com.doudou.cutecore.worldgen.entity.ModEntityType;
import com.doudou.cutecore.worldgen.entity.projectile.HeartEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class HeartItem extends Item {

    public final float damage;

    public HeartItem(Properties p_40512_, float damage) {
        super(p_40512_);
        this.damage = damage;
    }

    public HeartEntity createArrow(Level world, ItemStack itemStack, LivingEntity shooter) {
        HeartEntity arrow = new HeartEntity(ModEntityType.HEART.get(), shooter, world);
        return arrow;
    }

    public boolean isInfinite(ItemStack stack, ItemStack bow, net.minecraft.world.entity.player.Player player) {
        int enchant = net.minecraft.world.item.enchantment.EnchantmentHelper.getItemEnchantmentLevel(net.minecraft.world.item.enchantment.Enchantments.INFINITY_ARROWS, bow);
        return enchant <= 0 ? false : this.getClass() == HeartItem.class;
    }
}
