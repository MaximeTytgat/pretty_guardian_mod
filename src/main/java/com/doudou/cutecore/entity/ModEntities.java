package com.doudou.cutecore.entity;

import com.doudou.cutecore.CuteCore;
import com.doudou.cutecore.entity.custom.ButterflyEntity;
import com.doudou.cutecore.entity.custom.CelestialRabbitEntity;
import com.doudou.cutecore.entity.custom.StrawberryCowEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.animal.MushroomCow;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, CuteCore.MOD_ID);

    public static final RegistryObject<EntityType<StrawberryCowEntity>> STRAWBERRY_COW =
            ENTITY_TYPES.register("strawberry_cow", () -> EntityType.Builder.of(StrawberryCowEntity::new, MobCategory.CREATURE)
                    .sized(0.9F, 1.4F).build("strawberry_cow"));
    public static final RegistryObject<EntityType<CelestialRabbitEntity>> CELESTIAL_RABBIT =
            ENTITY_TYPES.register("celestial_rabbit", () -> EntityType.Builder.of(CelestialRabbitEntity::new, MobCategory.CREATURE)
                    .sized(0.5F, 0.8F).build("celestial_rabbit"));
    public static final RegistryObject<EntityType<ButterflyEntity>> BUTTERFLY =
            ENTITY_TYPES.register("butterfly", () -> EntityType.Builder.of(ButterflyEntity::new, MobCategory.CREATURE)
                    .sized(0.5F, 0.35F).build("butterfly"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
