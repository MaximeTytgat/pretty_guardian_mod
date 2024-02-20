package com.max.prettyguardian.item.custom.tool;

import com.max.prettyguardian.enchantment.ModEnchantments;
import it.unimi.dsi.fastutil.objects.Reference2IntMap;
import it.unimi.dsi.fastutil.objects.Reference2IntOpenHashMap;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.enchantment.Enchantment;

import java.util.Map;

public class SpaceSwordItem extends SwordItem {
    public SpaceSwordItem(Tier tier, int damage, float speed, Properties properties) {
        super(tier, damage, speed, properties.rarity(Rarity.EPIC));
    }

    @Override
    public Map<Enchantment, Integer> getAllEnchantments(ItemStack stack) {
        Reference2IntMap<Enchantment> map = new Reference2IntOpenHashMap<>();
        map.put(ModEnchantments.SLOW.get(), 1);
        return map;
    }

    @Override
    public boolean isFoil(ItemStack itemStack) {
        return true;
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
