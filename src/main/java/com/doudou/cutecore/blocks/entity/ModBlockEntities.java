package com.doudou.cutecore.blocks.entity;

import com.doudou.cutecore.CuteCore;
import com.doudou.cutecore.blocks.CuteCoreBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, CuteCore.MOD_ID);

    public static final RegistryObject<BlockEntityType<PicnicBasketBlockEntity>> PICNIC_BASKET_BE =
            BLOCK_ENTITIES.register("picnic_basket_be", () ->
                    BlockEntityType.Builder.of(PicnicBasketBlockEntity::new,
                            CuteCoreBlock.PICNIC_BASKET.get()).build(null));


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
