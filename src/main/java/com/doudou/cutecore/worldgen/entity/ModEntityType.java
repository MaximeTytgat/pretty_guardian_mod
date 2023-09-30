package com.doudou.cutecore.worldgen.entity;

import com.doudou.cutecore.CuteCore;
import com.doudou.cutecore.worldgen.entity.projectile.CuteArrow;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityType {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, CuteCore.MODID);


    public static final RegistryObject<EntityType<CuteArrow>> CUTE_ARROW =
            ENTITY_TYPES.register("cute_arrow", () -> EntityType.Builder.of((EntityType.EntityFactory<CuteArrow>) CuteArrow::new, MobCategory.MISC)
            .sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).build("cute_arrow"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
