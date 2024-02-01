package com.doudou.cutecore.blocks;

import com.doudou.cutecore.CuteCore;
import com.doudou.cutecore.blocks.custom.SeaShell;
import com.doudou.cutecore.blocks.custom.crop.CropLeavesBlock;
import com.doudou.cutecore.blocks.custom.crop.MintCropBlock;
import com.doudou.cutecore.blocks.custom.PicnicBasketBlock;
import com.doudou.cutecore.blocks.custom.crop.StrawberryCropBlock;
import com.doudou.cutecore.blocks.custom.crop.VanillaCropBlock;
import com.doudou.cutecore.blocks.custom.food.BaseCake;
import com.doudou.cutecore.blocks.custom.food.BasePie;
import com.doudou.cutecore.blocks.custom.food.BaseThreeCake;
import com.doudou.cutecore.blocks.custom.furniture.JapDoor;
import com.doudou.cutecore.blocks.custom.furniture.JapLampBlock;
import com.doudou.cutecore.blocks.custom.furniture.JapLanternBlock;
import com.doudou.cutecore.blocks.custom.furniture.ShojiBlossom;
import com.doudou.cutecore.blocks.custom.plush.CowPlushBlock;
import com.doudou.cutecore.blocks.custom.plush.PlushiesBlock;
import com.doudou.cutecore.item.CuteCoreItem;
import com.doudou.cutecore.worldgen.tree.LemonTreeGrower;
import com.doudou.cutecore.worldgen.tree.PistachioTreeGrower;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class CuteCoreBlock {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, CuteCore.MOD_ID);

    public static final RegistryObject<Block> CHOCOLATE_CAKE = registryBlock("chocolate_cake", () -> new BaseCake(cakeProperties()));
    public static final RegistryObject<Block> CREAM_CAKE = registryBlock("cream_cake", () -> new BaseCake(cakeProperties()));
    public static final RegistryObject<Block> RHUM_CAKE = registryBlock("rhum_cake", () -> new BaseCake(cakeProperties()));
    public static final RegistryObject<Block> STRAWBERRY_CAKE = registryBlock("strawberry_cake", () -> new BaseCake(cakeProperties()));
    public static final RegistryObject<Block> BERRY_STRAWBERRY_CAKE = registryBlock("berry_strawberry_cake", () -> new BaseCake(cakeProperties()));
    public static final RegistryObject<Block> VELVET_CAKE = registryBlock("velvet_cake", () -> new BaseCake(cakeProperties()));
    public static final RegistryObject<Block> CREAM_STRAWBERRY_CAKE = registryBlock("cream_strawberry_cake", () -> new BaseCake(cakeProperties()));
    public static final RegistryObject<Block> STRAWBERRY_CHOCO_CAKE = registryBlock("strawberry_choco_cake", () -> new BaseCake(cakeProperties()));
    public static final RegistryObject<Block> THREE_STRAWBERRY_CAKE = registryBlock("three_strawberry_cake", () -> new BaseThreeCake(cakeProperties()));
    public static final RegistryObject<Block> THREE_STRAWBERRY_CHOCO_CAKE = registryBlock("three_strawberry_choco_cake", () -> new BaseThreeCake(cakeProperties()));
    public static final RegistryObject<Block> THREE_CHOCO_CAKE = registryBlock("three_choco_cake", () -> new BaseThreeCake(cakeProperties()));
    public static final RegistryObject<Block> THREE_VELVET_CAKE = registryBlock("three_velvet_cake", () -> new BaseThreeCake(cakeProperties()));
    public static final RegistryObject<Block> APPLE_PIE = registryBlock("apple_pie", () -> new BasePie(cakeProperties()));
    public static final RegistryObject<Block> CHOCOLATE_PIE = registryBlock("chocolate_pie", () -> new BasePie(cakeProperties()));
    public static final RegistryObject<Block> MAGIC_PIE = registryBlock("magic_pie", () -> new BasePie(cakeProperties(), true));
    public static final RegistryObject<Block> LEMON_PIE = registryBlock("lemon_pie", () -> new BasePie(cakeProperties()));
    public static final RegistryObject<Block> STRAWBERRY_PIE = registryBlock("strawberry_pie", () -> new BasePie(cakeProperties()));

    public static final RegistryObject<Block> STRAWBERRY_CROP = BLOCKS.register("strawberry_crop", () -> new StrawberryCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().noCollission()));
    public static final RegistryObject<Block> MINT_CROP = BLOCKS.register("mint_crop", () -> new MintCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().noCollission()));
    public static final RegistryObject<Block> VANILLA_CROP = BLOCKS.register("vanilla_crop", () -> new VanillaCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().noCollission()));
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
    public static final RegistryObject<Block> PISTACHIO_LEAVES_CROP = registryBlock("pistachio_leaves_crop", () -> new CropLeavesBlock(CuteCoreItem.PISTACHIO, BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));
    public static final RegistryObject<Block> PISTACHIO_SAPLING = registryBlock("pistachio_sapling", () -> new SaplingBlock(new PistachioTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));
    public static final RegistryObject<Block> LEMON_LEAVES_CROP = registryBlock("lemon_leaves_crop", () -> new CropLeavesBlock(CuteCoreItem.LEMON, BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));
    public static final RegistryObject<Block> LEMON_SAPLING = registryBlock("lemon_sapling", () -> new SaplingBlock(new LemonTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));
    public static final RegistryObject<Block> STRAWBERRY_CROP_FLOWER = registryBlock("strawberry_crop_flower", () -> new FlowerBlock(() -> MobEffects.LUCK, 5,  BlockBehaviour.Properties.copy(Blocks.ALLIUM).noOcclusion().noCollission().noLootTable()));
    public static final RegistryObject<Block> MYMELODY_PLUSH = registryBlock("mymelody_plush", () -> new PlushiesBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));
    public static final RegistryObject<Block> KUROMI_PLUSH = registryBlock("kuromi_plush", () -> new PlushiesBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));
    public static final RegistryObject<Block> CAVALIER_PLUSH = registryBlock("cavalier_plush", () -> new PlushiesBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));
    public static final RegistryObject<Block> TEDDYBEAR_PLUSH = registryBlock("teddybear_plush", () -> new PlushiesBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));
    public static final RegistryObject<Block> RABBIT_PLUSH = registryBlock("rabbit_plush", () -> new PlushiesBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));
    public static final RegistryObject<Block> COW_PLUSH = registryBlock("cow_plush", () -> new CowPlushBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));

    public static final RegistryObject<Block> SHOJI_BLOSSOM = registryBlock("shoji_blossom", () -> new ShojiBlossom(BlockBehaviour.Properties.copy(Blocks.CHERRY_TRAPDOOR).sound(SoundType.WOOL).strength(0.2F, 0.2F)));
    public static final RegistryObject<Block> SHOJI_BLOSSOM_BOTTOM = registryBlock("shoji_blossom_bottom", () -> new ShojiBlossom(BlockBehaviour.Properties.copy(Blocks.CHERRY_TRAPDOOR).sound(SoundType.WOOL).strength(0.2F, 0.2F)));
    public static final RegistryObject<Block> SHOJI_BLOSSOM_SMALL = registryBlock("shoji_blossom_small", () -> new ShojiBlossom(BlockBehaviour.Properties.copy(Blocks.CHERRY_TRAPDOOR).sound(SoundType.WOOL).strength(0.2F, 0.2F)));
    public static final RegistryObject<Block> SHOJI_BLOSSOM_SMALL_BOTTOM = registryBlock("shoji_blossom_small_bottom", () -> new ShojiBlossom(BlockBehaviour.Properties.copy(Blocks.CHERRY_TRAPDOOR).sound(SoundType.WOOL).strength(0.2F, 0.2F)));

    public static final RegistryObject<Block> SHOJI_CHERRY = registryBlock("shoji_cherry", () -> new ShojiBlossom(BlockBehaviour.Properties.copy(Blocks.CHERRY_TRAPDOOR).sound(SoundType.WOOL).strength(0.2F, 0.2F)));
    public static final RegistryObject<Block> SHOJI_CHERRY_BOTTOM = registryBlock("shoji_cherry_bottom", () -> new ShojiBlossom(BlockBehaviour.Properties.copy(Blocks.CHERRY_TRAPDOOR).sound(SoundType.WOOL).strength(0.2F, 0.2F)));
    public static final RegistryObject<Block> SHOJI_CHERRY_SMALL = registryBlock("shoji_cherry_small", () -> new ShojiBlossom(BlockBehaviour.Properties.copy(Blocks.CHERRY_TRAPDOOR).sound(SoundType.WOOL).strength(0.2F, 0.2F)));
    public static final RegistryObject<Block> SHOJI_CHERRY_SMALL_BOTTOM = registryBlock("shoji_cherry_small_bottom", () -> new ShojiBlossom(BlockBehaviour.Properties.copy(Blocks.CHERRY_TRAPDOOR).sound(SoundType.WOOL).strength(0.2F, 0.2F)));

    public static final RegistryObject<Block> SHOJI_BIRCH = registryBlock("shoji_birch", () -> new ShojiBlossom(BlockBehaviour.Properties.copy(Blocks.BIRCH_TRAPDOOR).sound(SoundType.WOOL).strength(0.2F, 0.2F)));
    public static final RegistryObject<Block> SHOJI_BIRCH_BOTTOM = registryBlock("shoji_birch_bottom", () -> new ShojiBlossom(BlockBehaviour.Properties.copy(Blocks.BIRCH_TRAPDOOR).sound(SoundType.WOOL).strength(0.2F, 0.2F)));
    public static final RegistryObject<Block> SHOJI_BIRCH_SMALL = registryBlock("shoji_birch_small", () -> new ShojiBlossom(BlockBehaviour.Properties.copy(Blocks.BIRCH_TRAPDOOR).sound(SoundType.WOOL).strength(0.2F, 0.2F)));
    public static final RegistryObject<Block> SHOJI_BIRCH_SMALL_BOTTOM = registryBlock("shoji_birch_small_bottom", () -> new ShojiBlossom(BlockBehaviour.Properties.copy(Blocks.BIRCH_TRAPDOOR).sound(SoundType.WOOL).strength(0.2F, 0.2F)));

    public static final RegistryObject<Block> LANTERN_JAPANESE = registryBlock("lantern_japanese", () -> new JapLanternBlock(BlockBehaviour.Properties.copy(Blocks.REDSTONE_LAMP).sound(SoundType.WOOL).strength(0.2F, 0.2F)));
    public static final RegistryObject<Block> LANTERN_JAPANESE_SAKURA = registryBlock("lantern_japanese_sakura", () -> new JapLanternBlock(BlockBehaviour.Properties.copy(Blocks.REDSTONE_LAMP).sound(SoundType.WOOL).strength(0.2F, 0.2F)));
    public static final RegistryObject<Block> LANTERN_JAPANESE_FESTIVAL = registryBlock("lantern_japanese_festival", () -> new JapLanternBlock(BlockBehaviour.Properties.copy(Blocks.REDSTONE_LAMP).sound(SoundType.WOOL).strength(0.2F, 0.2F)));

    public static final RegistryObject<Block> LAMP_JAPANESE_OAK = registryBlock("lamp_japanese_oak", () -> new JapLampBlock(BlockBehaviour.Properties.copy(Blocks.REDSTONE_LAMP).sound(SoundType.WOOD).strength(0.2F, 0.2F)));
    public static final RegistryObject<Block> LAMP_JAPANESE_SPRUCE = registryBlock("lamp_japanese_spruce", () -> new JapLampBlock(BlockBehaviour.Properties.copy(Blocks.REDSTONE_LAMP).sound(SoundType.WOOD).strength(0.2F, 0.2F)));
    public static final RegistryObject<Block> LAMP_JAPANESE_MANGROVE = registryBlock("lamp_japanese_mangrove", () -> new JapLampBlock(BlockBehaviour.Properties.copy(Blocks.REDSTONE_LAMP).sound(SoundType.WOOD).strength(0.2F, 0.2F)));
    public static final RegistryObject<Block> LAMP_JAPANESE_CHERRY = registryBlock("lamp_japanese_cherry", () -> new JapLampBlock(BlockBehaviour.Properties.copy(Blocks.REDSTONE_LAMP).sound(SoundType.WOOD).strength(0.2F, 0.2F)));
    public static final RegistryObject<Block> LAMP_JAPANESE_BIRCH = registryBlock("lamp_japanese_birch", () -> new JapLampBlock(BlockBehaviour.Properties.copy(Blocks.REDSTONE_LAMP).sound(SoundType.WOOD).strength(0.2F, 0.2F)));
    public static final RegistryObject<Block> LAMP_JAPANESE_JUNGLE = registryBlock("lamp_japanese_jungle", () -> new JapLampBlock(BlockBehaviour.Properties.copy(Blocks.REDSTONE_LAMP).sound(SoundType.WOOD).strength(0.2F, 0.2F)));
    public static final RegistryObject<Block> LAMP_JAPANESE_ACACIA = registryBlock("lamp_japanese_acacia", () -> new JapLampBlock(BlockBehaviour.Properties.copy(Blocks.REDSTONE_LAMP).sound(SoundType.WOOD).strength(0.2F, 0.2F)));
    public static final RegistryObject<Block> LAMP_JAPANESE_DARK_OAK = registryBlock("lamp_japanese_dark_oak", () -> new JapLampBlock(BlockBehaviour.Properties.copy(Blocks.REDSTONE_LAMP).sound(SoundType.WOOD).strength(0.2F, 0.2F)));

    public static final RegistryObject<Block> DOOR_SHOJI_BLOSSOM = registryBlock("door_shoji_blossom", () -> new JapDoor(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(3.0F).noOcclusion().ignitedByLava(), BlockSetType.CHERRY));
    public static final RegistryObject<Block> DOOR_SHOJI_BLOSSOM_SMALL = registryBlock("door_shoji_blossom_small", () -> new JapDoor(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(3.0F).noOcclusion().ignitedByLava(), BlockSetType.CHERRY));
    public static final RegistryObject<Block> DOOR_SHOJI_BIRCH = registryBlock("door_shoji_birch", () -> new JapDoor(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(3.0F).noOcclusion().ignitedByLava(), BlockSetType.CHERRY));
    public static final RegistryObject<Block> DOOR_SHOJI_BIRCH_SMALL = registryBlock("door_shoji_birch_small", () -> new JapDoor(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(3.0F).noOcclusion().ignitedByLava(), BlockSetType.CHERRY));
    public static final RegistryObject<Block> DOOR_SHOJI_CHERRY = registryBlock("door_shoji_cherry", () -> new JapDoor(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(3.0F).noOcclusion().ignitedByLava(), BlockSetType.CHERRY));
    public static final RegistryObject<Block> DOOR_SHOJI_CHERRY_SMALL = registryBlock("door_shoji_cherry_small", () -> new JapDoor(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(3.0F).noOcclusion().ignitedByLava(), BlockSetType.CHERRY));
    public static final RegistryObject<Block> SEA_SHELL = registryBlock("sea_shell", () -> new SeaShell(BlockBehaviour.Properties.copy(Blocks.TUBE_CORAL_BLOCK).sound(SoundType.WOOL).strength(0.2F, 0.2F)));

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
