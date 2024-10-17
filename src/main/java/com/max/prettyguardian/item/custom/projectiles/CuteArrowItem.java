package com.max.prettyguardian.item.custom.projectiles;


import com.max.prettyguardian.worldgen.entity.ModEntityType;
import com.max.prettyguardian.worldgen.entity.projectile.CuteArrowEntity;
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

    public CuteArrowEntity createArrow(Level world, ItemStack itemStack, LivingEntity shooter) {
        CuteArrowEntity arrow = new CuteArrowEntity(ModEntityType.HEART_ARROW.get(), shooter, world);
        arrow.setBaseDamage(this.damage);
        return arrow;
    }

    public boolean isInfinite(ItemStack stack, ItemStack bow, net.minecraft.world.entity.player.Player player) {
        int enchant = net.minecraft.world.item.enchantment.EnchantmentHelper.getItemEnchantmentLevel(net.minecraft.world.item.enchantment.Enchantments.INFINITY_ARROWS, bow);
        return enchant > 0 && this.getClass() == CuteArrowItem.class;
    }
}
