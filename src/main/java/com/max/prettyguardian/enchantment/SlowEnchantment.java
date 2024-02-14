package com.max.prettyguardian.enchantment;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class SlowEnchantment extends Enchantment {
    protected SlowEnchantment(Rarity rarity, EnchantmentCategory enchantmentCategory, EquipmentSlot... equipmentSlots) {
        super(rarity, enchantmentCategory, equipmentSlots);
    }

    @Override
    public void doPostAttack(LivingEntity attacker, Entity target, int pLevel) {
        if (!attacker.level().isClientSide) {
            if (target instanceof LivingEntity livingEntity) {
                livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 150, 4));
            }
        }
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }
}
