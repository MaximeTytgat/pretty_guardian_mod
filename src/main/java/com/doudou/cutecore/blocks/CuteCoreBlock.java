package com.doudou.cutecore.blocks;

import com.doudou.cutecore.CuteCore;
import com.doudou.cutecore.blocks.custom.PicnicBasketBlock;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class CuteCoreBlock {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, CuteCore.MOD_ID);
    public static final RegistryObject<Block> THREE_STRAWBERRY_CAKE = registryBlock("three_strawberry_cake", () -> new BaseThreeCake(cakeProperties()));
    public static final RegistryObject<Block> THREE_STRAWBERRY_CHOCO_CAKE = registryBlock("three_strawberry_choco_cake", () -> new BaseThreeCake(cakeProperties()));
    public static final RegistryObject<Block> CHOCOLATE_PIE = registryBlock("chocolate_pie", () -> new BasePie(cakeProperties()));

    public static final RegistryObject<Block> STRAWBERRY_CROP = BLOCKS.register("strawberry_crop", () -> new StrawberryCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().noCollission()));
    public static final RegistryObject<Block> PINK_SAPPHIRE_BLOCK = registryBlock("pink_sapphire_block", () -> new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
    public static final RegistryObject<Block> PINK_SAPPHIRE_ORE = registryBlock("pink_sapphire_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(2.0F).requiresCorrectToolForDrops(), UniformInt.of(3, 6)));
    public static final RegistryObject<Block> DEEPSLATE_PINK_SAPPHIRE_ORE = registryBlock("deepslate_pink_sapphire_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)));
    public static final RegistryObject<Block> RUBY_BLOCK = registryBlock("ruby_block", () -> new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
    public static final RegistryObject<Block> RUBY_ORE = registryBlock("ruby_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(2.0F).requiresCorrectToolForDrops(), UniformInt.of(3, 6)));
    public static final RegistryObject<Block> DEEPSLATE_RUBY_ORE = registryBlock("deepslate_ruby_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)));
    public static final RegistryObject<Block> PICNIC_BASKET = registryBlock("picnic_basket", () -> new PicnicBasketBlock(BlockBehaviour.Properties.copy(Blocks.CHEST)));
    public static final RegistryObject<Block> CHOCOLATE_BLOCK = registryBlock("chocolate_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.ACACIA_WOOD).sound(SoundType.STONE).strength(0.2F, 0.2F)));
    public static final RegistryObject<Block> MARSHMELLO_BLOCK = registryBlock("marshmello_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.ACACIA_WOOD).sound(SoundType.WOOL).strength(0.2F, 0.2F)));
    public static final RegistryObject<Block> ROASTED_MARSHMELLO_BLOCK = registryBlock("roasted_marshmello_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.ACACIA_WOOD).sound(SoundType.WOOL).strength(0.2F, 0.2F)));
    public static final RegistryObject<Block> CHOCOLATE_CAKE = registryBlock("chocolate_cake", () -> new BaseCake(BlockBehaviour.Properties.copy(Blocks.CAKE)));

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return CuteCore.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static <T extends Block> RegistryObject<Block> registryBlock(String name, Supplier<T> block) {
        RegistryObject<Block> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

    public static BlockBehaviour.Properties cakeProperties() {
        return BlockBehaviour.Properties.copy(Blocks.CAKE).noLootTable();
    }


    public static void init() {
    }
}
