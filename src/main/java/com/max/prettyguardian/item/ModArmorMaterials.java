package com.max.prettyguardian.item;

import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class ModArmorMaterials {
    public static final Holder<ArmorMaterial> PINK_SAPPHIRE;
    public static final Holder<ArmorMaterial> RUBY;

    private static Holder<ArmorMaterial> register(String name, EnumMap<ArmorItem.Type, Integer> integer, int p_331490_, Supplier<Ingredient> ingredientSupplier, List<ArmorMaterial.Layer> layers) {
        EnumMap<ArmorItem.Type, Integer> enumMap = new EnumMap<>(ArmorItem.Type.class);
        ArmorItem.Type[] values = ArmorItem.Type.values();

        for (ArmorItem.Type type : values) {
            enumMap.put(type, integer.get(type));
        }

        return Registry.registerForHolder(BuiltInRegistries.ARMOR_MATERIAL, new ResourceLocation(name), new ArmorMaterial(enumMap, p_331490_, SoundEvents.ARMOR_EQUIP_GOLD, ingredientSupplier, layers, (float) 0.0, (float) 0.0));
    }

    static {
        PINK_SAPPHIRE = register("pink_sapphire", Util.make(new EnumMap<>(ArmorItem.Type.class), (enumMap) -> {
            enumMap.put(ArmorItem.Type.BOOTS, 4);
            enumMap.put(ArmorItem.Type.LEGGINGS, 9);
            enumMap.put(ArmorItem.Type.CHESTPLATE, 6);
            enumMap.put(ArmorItem.Type.HELMET, 4);
            enumMap.put(ArmorItem.Type.BODY, 15);
        }), 15, () -> {
            return Ingredient.of(PrettyGuardianItem.PINK_SAPPHIRE.get());
        }, List.of(new ArmorMaterial.Layer(new ResourceLocation("pink_sapphire"), "", true), new ArmorMaterial.Layer(new ResourceLocation("pink_sapphire"), "_overlay", false)));

        RUBY = register("ruby", Util.make(new EnumMap<>(ArmorItem.Type.class), (enumMap) -> {
            enumMap.put(ArmorItem.Type.BOOTS, 5);
            enumMap.put(ArmorItem.Type.LEGGINGS, 10);
            enumMap.put(ArmorItem.Type.CHESTPLATE, 7);
            enumMap.put(ArmorItem.Type.HELMET, 5);
            enumMap.put(ArmorItem.Type.BODY, 17);
        }), 17, () -> {
            return Ingredient.of(PrettyGuardianItem.RUBY.get());
        }, List.of(new ArmorMaterial.Layer(new ResourceLocation("ruby"), "", true), new ArmorMaterial.Layer(new ResourceLocation("ruby"), "_overlay", false)));
    }
}
