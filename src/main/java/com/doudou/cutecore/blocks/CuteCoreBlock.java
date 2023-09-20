package com.doudou.cutecore.blocks;

import com.doudou.cutecore.CuteCore;
import com.doudou.cutecore.util.CakeBuilder;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class CuteCoreBlock {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, CuteCore.MODID);


    private static  <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return CuteCore.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static <T extends Block> RegistryObject<T> registryBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    public static final Supplier<BaseCake> THREE_STRAWBERRY_CAKE = new CakeBuilder("three_strawberry_cake").build();

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

    public static BlockBehaviour.Properties cakeProperties() {
        return BlockBehaviour.Properties.copy(Blocks.CAKE);
    }


    public static void init() {
    }
}
