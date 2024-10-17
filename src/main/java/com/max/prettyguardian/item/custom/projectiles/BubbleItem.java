package com.max.prettyguardian.item.custom.projectiles;


import com.max.prettyguardian.worldgen.entity.ModEntityType;
import com.max.prettyguardian.worldgen.entity.projectile.BubbleEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

public class BubbleItem extends Item {

    public final float damage;

    public BubbleItem(Properties properties, float damage) {
        super(properties);
        this.damage = damage;
    }

    public BubbleEntity createArrow(Level world, ItemStack itemStack, LivingEntity shooter, float damage) {
        BubbleEntity arrow = new BubbleEntity(ModEntityType.BUBBLE.get(), shooter, world, damage);
        return arrow;
    }

    public boolean isInfinite(ItemStack stack, ItemStack bow, Player player) {
        int enchant = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, bow);
        return enchant > 0 && this.getClass() == BubbleItem.class;
    }
}
