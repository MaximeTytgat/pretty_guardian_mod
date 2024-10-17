package com.max.prettyguardian.item.custom.projectiles;


import com.max.prettyguardian.worldgen.entity.ModEntityType;
import com.max.prettyguardian.worldgen.entity.projectile.StarLightEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class StarLightItem extends Item {

    public final float damage;

    public StarLightItem(Properties p_40512_, float damage) {
        super(p_40512_);
        this.damage = damage;
    }

    public StarLightEntity createArrow(Level world, ItemStack itemStack, LivingEntity shooter, float damage) {
        StarLightEntity arrow = new StarLightEntity(ModEntityType.STAR_LIGHT.get(), shooter, world, damage);
        return arrow;
    }

    public boolean isInfinite(ItemStack stack, ItemStack bow, net.minecraft.world.entity.player.Player player) {
        int enchant = net.minecraft.world.item.enchantment.EnchantmentHelper.getItemEnchantmentLevel(net.minecraft.world.item.enchantment.Enchantments.INFINITY_ARROWS, bow);
        return enchant > 0 && this.getClass() == StarLightItem.class;
    }
}
