package com.doudou.prettyguardian.worldgen.entity;

import com.doudou.prettyguardian.PrettyGuardian;
import com.doudou.prettyguardian.worldgen.entity.projectile.CuteArrowEntity;
import com.doudou.prettyguardian.worldgen.entity.projectile.HeartEntity;
import com.doudou.prettyguardian.worldgen.entity.projectile.StarLightEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityType {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, PrettyGuardian.MOD_ID);


    public static final RegistryObject<EntityType<CuteArrowEntity>> HEART_ARROW =
            ENTITY_TYPES.register("heart_arrow", () -> EntityType.Builder.of((EntityType.EntityFactory<CuteArrowEntity>) CuteArrowEntity::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).build("heart_arrow"));

    public static final RegistryObject<EntityType<HeartEntity>> HEART =
            ENTITY_TYPES.register("heart", () -> EntityType.Builder.of((EntityType.EntityFactory<HeartEntity>) HeartEntity::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).build("heart"));

    public static final RegistryObject<EntityType<StarLightEntity>> STAR_LIGHT =
            ENTITY_TYPES.register("star_light", () -> EntityType.Builder.of((EntityType.EntityFactory<StarLightEntity>) StarLightEntity::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).build("star_light"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
