package com.max.prettyguardian.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {

    public static final FoodProperties STRAWBERRY = (new FoodProperties.Builder())
            .nutrition(2).saturationMod(0.3F).build();
    public static final FoodProperties CHOCOLATE_HEART = (new FoodProperties.Builder())
            .nutrition(2).saturationMod(0.3F).build();
    public static final FoodProperties CHOCOLATE = (new FoodProperties.Builder())
            .nutrition(1).saturationMod(0.35F).build();
    public static final FoodProperties MILK_CARTON = (new FoodProperties.Builder())
            .nutrition(1).saturationMod(0.25F).build();
    public static final FoodProperties MILK = (new FoodProperties.Builder())
            .nutrition(1).saturationMod(0.5F).build();
    public static final FoodProperties ICE_CREAM = (new FoodProperties.Builder())
            .nutrition(6).saturationMod(0.25F).build();
    public static final FoodProperties MOJITO = (new FoodProperties.Builder())
            .nutrition(6).saturationMod(0.25F).build();
    public static final FoodProperties BUBBLE_TEA = (new FoodProperties.Builder())
            .nutrition(6).saturationMod(0.25F)
            .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 300, 0), 1F).build();
    public static final FoodProperties POPSICLE = (new FoodProperties.Builder())
            .nutrition(6).saturationMod(0.25F).build();
    public static final FoodProperties POKKY_ICE_CREAM = (new FoodProperties.Builder())
            .nutrition(10).saturationMod(0.75F).build();
    public static final FoodProperties PUDDING = (new FoodProperties.Builder())
            .nutrition(6).saturationMod(0.35F).build();
    public static final FoodProperties CROISSANT = (new FoodProperties.Builder())
            .nutrition(6).saturationMod(0.35F).build();
    public static final FoodProperties MARSHMELLOW_STRAWBERRY_BURGER = (new FoodProperties.Builder())
            .nutrition(8).saturationMod(0.75F).build();
    public static final FoodProperties SMORE = (new FoodProperties.Builder())
            .nutrition(8).saturationMod(0.75F).build();
    public static final FoodProperties DONUT = (new FoodProperties.Builder())
            .nutrition(0).saturationMod(0F).build();
    public static final FoodProperties MOCHI_DONUT = (new FoodProperties.Builder())
            .nutrition(0).saturationMod(0F).build();
    public static final FoodProperties RHUM = (new FoodProperties.Builder())
            .nutrition(1).saturationMod(0.1F).build();
    public static final FoodProperties CANDY_APPLE = (new FoodProperties.Builder())
            .nutrition(4).saturationMod(0.5F).build();
    public static final FoodProperties MARSHMELLOW = (new FoodProperties.Builder())
            .nutrition(4).saturationMod(0.5F).build();
    public static final FoodProperties ROASTED_MARSHMELLOW = (new FoodProperties.Builder())
            .nutrition(4).saturationMod(0.5F).build();
    public static final FoodProperties DANGO = (new FoodProperties.Builder())
            .nutrition(4).saturationMod(0.5F).build();
    public static final FoodProperties POCKY = (new FoodProperties.Builder())
            .nutrition(4).saturationMod(0.5F).build();
    public static final FoodProperties WAFFLE = (new FoodProperties.Builder())
            .nutrition(4).saturationMod(0.5F).build();
    public static final FoodProperties CHOCOLATE_WAFFLE = (new FoodProperties.Builder())
            .nutrition(4).saturationMod(0.5F).build();
    public static final FoodProperties CARAMEL = (new FoodProperties.Builder())
            .nutrition(4).saturationMod(0.5F).build();
    public static final FoodProperties SQUID = (new FoodProperties.Builder())
            .nutrition(2).saturationMod(0.3F)
            .effect(new MobEffectInstance(MobEffects.HUNGER, 350, 0), 0.2F).meat().build();

    public static final FoodProperties SQUID_COOKED = (new FoodProperties.Builder())
            .nutrition(6).saturationMod(0.75F).meat().build();
    public static final FoodProperties ICE_CREAM_WAFFLE = (new FoodProperties.Builder())
            .nutrition(8).saturationMod(0.60F).meat().build();

    public static final FoodProperties TAKOYAKI = (new FoodProperties.Builder())
            .nutrition(4).saturationMod(0.40F).meat().build();
}
