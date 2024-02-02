package com.doudou.cutecore.datagen;

import com.doudou.cutecore.CuteCore;
import com.doudou.cutecore.blocks.custom.crop.MintCropBlock;
import com.doudou.cutecore.blocks.custom.food.BaseCake;
import com.doudou.cutecore.blocks.custom.food.BaseThreeCake;
import com.doudou.cutecore.blocks.CuteCoreBlock;
import com.doudou.cutecore.blocks.custom.crop.StrawberryCropBlock;
import com.doudou.cutecore.blocks.custom.crop.VanillaCropBlock;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, CuteCore.MOD_ID, exFileHelper);
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void BottomTopblockWithItem(Block block) {
        simpleBlockWithItem(block, models().cubeBottomTop(
                name(block),
                blockTexture(block),
                new ResourceLocation(CuteCore.MOD_ID, "block/" + name(block) + "_bottom"),
                new ResourceLocation(CuteCore.MOD_ID, "block/" + name(block) + "_top")
        ));
    }

    private void topblockWithItem(Block block) {
        simpleBlockWithItem(block, models().cubeTop(name(block), blockTexture(block), new ResourceLocation(CuteCore.MOD_ID, "block/" + name(block) + "_top")));
    }

    private ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }

    private String name(Block block) {
        return key(block).getPath();
    }

    @Override
    protected void registerStatesAndModels() {
        makeStrawberryCrop((CropBlock) CuteCoreBlock.STRAWBERRY_CROP.get(), "strawberry_stage", "strawberry_stage");
        makeMintCrop((CropBlock) CuteCoreBlock.MINT_CROP.get(), "mint_stage", "mint_stage");

        blockWithItem(CuteCoreBlock.PINK_SAPPHIRE_BLOCK);
        blockWithItem(CuteCoreBlock.PINK_SAPPHIRE_ORE);
        blockWithItem(CuteCoreBlock.DEEPSLATE_PINK_SAPPHIRE_ORE);

        blockWithItem(CuteCoreBlock.RUBY_BLOCK);
        blockWithItem(CuteCoreBlock.RUBY_ORE);
        blockWithItem(CuteCoreBlock.DEEPSLATE_RUBY_ORE);

        blockWithItem(CuteCoreBlock.CHOCOLATE_BLOCK);
        topblockWithItem(CuteCoreBlock.MARSHMELLO_BLOCK.get());
        BottomTopblockWithItem(CuteCoreBlock.ROASTED_MARSHMELLO_BLOCK.get());

        simpleCake(CuteCoreBlock.CHOCOLATE_CAKE.get());
        simpleCake(CuteCoreBlock.CREAM_CAKE.get());
        simpleCake(CuteCoreBlock.RHUM_CAKE.get());
        simpleCake(CuteCoreBlock.STRAWBERRY_CAKE.get());
        simpleCake(CuteCoreBlock.BERRY_STRAWBERRY_CAKE.get());
        simpleCake(CuteCoreBlock.VELVET_CAKE.get());
        simpleCake(CuteCoreBlock.CREAM_STRAWBERRY_CAKE.get());
        simpleCake(CuteCoreBlock.STRAWBERRY_CHOCO_CAKE.get());

        simpleThreeCake(CuteCoreBlock.THREE_VELVET_CAKE.get());
        simpleThreeCake(CuteCoreBlock.THREE_CHOCO_CAKE.get());
        simpleThreeCake(CuteCoreBlock.THREE_STRAWBERRY_CAKE.get());
        simpleThreeCake(CuteCoreBlock.THREE_STRAWBERRY_CHOCO_CAKE.get());

        saplingBlock(CuteCoreBlock.PISTACHIO_SAPLING);

        simpleBlockWithItem(CuteCoreBlock.STRAWBERRY_CROP_FLOWER.get(), models().cross(blockTexture(CuteCoreBlock.STRAWBERRY_CROP_FLOWER.get()).getPath(),
                blockTexture(CuteCoreBlock.STRAWBERRY_CROP_FLOWER.get())).renderType("cutout"));

        simpleBlockWithItem(CuteCoreBlock.GEM_POLISHING_STATION.get(), new ModelFile.UncheckedModelFile(modLoc("block/gem_polishing_station")));
    }




    private void saplingBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    public void makeStrawberryCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> strawberryStates(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] strawberryStates(BlockState state, CropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((StrawberryCropBlock) block).getAgeProperty()),
                new ResourceLocation(CuteCore.MOD_ID, "block/" + textureName + state.getValue(((StrawberryCropBlock) block).getAgeProperty()))).renderType("cutout"));

        return models;
    }


    public void makeMintCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> mintStates(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] mintStates(BlockState state, CropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((MintCropBlock) block).getAgeProperty()),
                new ResourceLocation(CuteCore.MOD_ID, "block/" + textureName + state.getValue(((MintCropBlock) block).getAgeProperty()))).renderType("cutout"));

        return models;
    }

    public void makeVanillaCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> vanillaStates(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] vanillaStates(BlockState state, CropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().singleTexture(
                modelName + state.getValue(((VanillaCropBlock) block).getAgeProperty()),
                new ResourceLocation(CuteCore.MOD_ID, "block/crop_cross_double"),
                "cross",
                new ResourceLocation(CuteCore.MOD_ID, "block/" + textureName + state.getValue(((VanillaCropBlock) block).getAgeProperty()))
        ).renderType("cutout"));

        return models;
    }




    private void simpleCake(Block block) {
        getVariantBuilder(block)
                .partialState().with(BaseCake.BITES, 0).addModels(new ConfiguredModel(models().getBuilder(name(block))
                        .texture("texture", blockTexture(block))
                        .texture("particle", blockTexture(block)).element()
                        .from(1, 0, 1).to(15, 7, 15)
                        .face(Direction.NORTH).uvs(8.5F, 1F, 15.5F, 5F).texture("#texture").end()
                        .face(Direction.EAST).uvs(8.5F, 1F, 15.5F, 5F).texture("#texture").end()
                        .face(Direction.SOUTH).uvs(8.5F, 1F, 15.5F, 5F).texture("#texture").end()
                        .face(Direction.WEST).uvs(8.5F, 1F, 15.5F, 5F).texture("#texture").end()
                        .face(Direction.UP).uvs(0.5F, 0.5F, 7.5F, 7.5F).texture("#texture").end()
                        .face(Direction.DOWN).uvs(0.5F, 8.5F, 7.5F, 15.5F).texture("#texture").end()
                        .end()))
                .partialState().with(BaseCake.BITES, 1).addModels(new ConfiguredModel(models().getBuilder(name(block) + "_slice1")
                        .texture("texture", blockTexture(block))
                        .texture("particle", blockTexture(block)).element()
                        .from(3, 0, 1).to(15, 7, 15)
                        .face(Direction.NORTH).uvs(8.5F, 1F, 14.5F, 5F).texture("#texture").end()
                        .face(Direction.EAST).uvs(8.5F, 1F, 15.5F, 5F).texture("#texture").end()
                        .face(Direction.SOUTH).uvs(9.5F, 1F, 15.5F, 5F).texture("#texture").end()
                        .face(Direction.WEST).uvs(8.5F, 11F, 15.5F, 15F).texture("#texture").end()
                        .face(Direction.UP).uvs(1.5F, 0.5F, 7.5F, 7.5F).texture("#texture").end()
                        .face(Direction.DOWN).uvs(0.5F, 8.5F, 7.5F, 15.5F).texture("#texture").end()
                        .end()))
                .partialState().with(BaseCake.BITES, 2).addModels(new ConfiguredModel(models().getBuilder(name(block) + "_slice2")
                        .texture("texture", blockTexture(block))
                        .texture("particle", blockTexture(block))
                        .element()
                        .from(5, 0, 1).to(15, 7, 15)
                        .face(Direction.NORTH).uvs(8.5F, 1F, 13.5F, 5F).texture("#texture").end()
                        .face(Direction.EAST).uvs(8.5F, 1F, 15.5F, 5F).texture("#texture").end()
                        .face(Direction.SOUTH).uvs(10.5F, 1F, 15.5F, 5F).texture("#texture").end()
                        .face(Direction.WEST).uvs(8.5F, 6F, 15.5F, 10F).texture("#texture").end()
                        .face(Direction.UP).uvs(2.5F, 0.5F, 7.5F, 7.5F).texture("#texture").end()
                        .face(Direction.DOWN).uvs(2.5F, 8.5F, 7.5F, 15.5F).texture("#texture").end()
                        .end()))
                .partialState().with(BaseCake.BITES, 3).addModels(new ConfiguredModel(models().getBuilder(name(block) + "_slice3")
                        .texture("texture", blockTexture(block))
                        .texture("particle", blockTexture(block))
                        .element()
                        .from(7, 0, 1).to(15, 7, 15)
                        .face(Direction.NORTH).uvs(8.5F, 1F, 12.5F, 5F).texture("#texture").end()
                        .face(Direction.EAST).uvs(8.5F, 1F, 15.5F, 5F).texture("#texture").end()
                        .face(Direction.SOUTH).uvs(11.5F, 1F, 15.5F, 5F).texture("#texture").end()
                        .face(Direction.WEST).uvs(8.5F, 11F, 15.5F, 15F).texture("#texture").end()
                        .face(Direction.UP).uvs(3.5F, 0.5F, 7.5F, 7.5F).texture("#texture").end()
                        .face(Direction.DOWN).uvs(3.5F, 8.5F, 7.5F, 15.5F).texture("#texture").end()
                        .end()))
                .partialState().with(BaseCake.BITES, 4).addModels(new ConfiguredModel(models().getBuilder(name(block) + "_slice4")
                        .texture("texture", blockTexture(block))
                        .texture("particle", blockTexture(block))
                        .element()
                        .from(9, 0, 1).to(15, 7, 15)
                        .face(Direction.NORTH).uvs(8.5F, 1F, 11.5F, 5F).texture("#texture").end()
                        .face(Direction.EAST).uvs(8.5F, 1F, 15.5F, 5F).texture("#texture").end()
                        .face(Direction.SOUTH).uvs(12.5F, 1F, 15.5F, 5F).texture("#texture").end()
                        .face(Direction.WEST).uvs(8.5F, 11F, 15.5F, 15F).texture("#texture").end()
                        .face(Direction.UP).uvs(4.5F, 0.5F, 7.5F, 7.5F).texture("#texture").end()
                        .face(Direction.DOWN).uvs(4.5F, 8.5F, 7.5F, 15.5F).texture("#texture").end()
                        .end()))
                .partialState().with(BaseCake.BITES, 5).addModels(new ConfiguredModel(models().getBuilder(name(block) + "_slice5")
                        .texture("texture", blockTexture(block))
                        .texture("particle", blockTexture(block))
                        .element()
                        .from(11, 0, 1).to(15, 7, 15)
                        .face(Direction.NORTH).uvs(8.5F, 1F, 10.5F, 5F).texture("#texture").end()
                        .face(Direction.EAST).uvs(8.5F, 1F, 15.5F, 5F).texture("#texture").end()
                        .face(Direction.SOUTH).uvs(13.5F, 1F, 15.5F, 5F).texture("#texture").end()
                        .face(Direction.WEST).uvs(8.5F, 6F, 15.5F, 10F).texture("#texture").end()
                        .face(Direction.UP).uvs(5.5F, 0.5F, 7.5F, 7.5F).texture("#texture").end()
                        .face(Direction.DOWN).uvs(5.5F, 8.5F, 7.5F, 15.5F).texture("#texture").end()
                        .end()))
                .partialState().with(BaseCake.BITES, 6).addModels(new ConfiguredModel(models().getBuilder(name(block) + "_slice6")
                        .texture("texture", blockTexture(block))
                        .texture("particle", blockTexture(block))
                        .element()
                        .from(13, 0, 1).to(15, 7, 15)
                        .face(Direction.NORTH).uvs(8.5F, 1F, 9.5F, 5F).texture("#texture").end()
                        .face(Direction.EAST).uvs(8.5F, 1F, 15.5F, 5F).texture("#texture").end()
                        .face(Direction.SOUTH).uvs(14.5F, 1F, 15.5F, 5F).texture("#texture").end()
                        .face(Direction.WEST).uvs(8.5F, 11F, 15.5F, 15F).texture("#texture").end()
                        .face(Direction.UP).uvs(6.5F, 0.5F, 7.5F, 7.5F).texture("#texture").end()
                        .face(Direction.DOWN).uvs(6.5F, 8.5F, 7.5F, 15.5F).texture("#texture").end()
                        .end()));
    }

    private void simpleThreeCake(Block block) {
        getVariantBuilder(block)
                .partialState().with(BaseThreeCake.BITES, 0).addModels(new ConfiguredModel(models().getBuilder(name(block))
                        .texture("texture", blockTexture(block))
                        .texture("particle", blockTexture(block))
                        .element()
                        .from(1, 0, 1).to(15, 8, 15)
                        .face(Direction.NORTH).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture("#texture").end()
                        .face(Direction.EAST).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture("#texture").end()
                        .face(Direction.SOUTH).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture("#texture").end()
                        .face(Direction.WEST).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture("#texture").end()
                        .face(Direction.UP).uvs(5.84615F, 11.69231F, 10.15385F, 16F).texture("#texture").end()
                        .face(Direction.DOWN).uvs(0.92308F, 11.69231F, 5.23077F, 16F).texture("#texture").end()
                        .end()
                        .element()
                        .from(2, 8, 2).to(14, 15, 14)
                        .face(Direction.NORTH).uvs(1.23077F, 6.76923F, 4.92308F, 8.92308F).texture("#texture").end()
                        .face(Direction.EAST).uvs(1.23077F, 6.76923F, 4.92308F, 8.92308F).texture("#texture").end()
                        .face(Direction.SOUTH).uvs(1.23077F, 6.76923F, 4.92308F, 8.92308F).texture("#texture").end()
                        .face(Direction.WEST).uvs(1.23077F, 6.76923F, 4.92308F, 8.92308F).texture("#texture").end()
                        .face(Direction.UP).uvs(11.07692F, 12F, 14.76923F, 15.69231F).texture("#texture").end()
                        .face(Direction.DOWN).uvs(5.84615F, 11.69231F, 10.15385F, 16F).texture("#texture").end()
                        .end()
                        .element()
                        .from(3, 15, 3).to(13, 21, 13)
                        .face(Direction.NORTH).uvs(1.53846F, 4.92308F, 4.61538F, 6.76923F).texture("#texture").end()
                        .face(Direction.EAST).uvs(1.53846F, 4.92308F, 4.61538F, 6.76923F).texture("#texture").end()
                        .face(Direction.SOUTH).uvs(1.53846F, 4.92308F, 4.61538F, 6.76923F).texture("#texture").end()
                        .face(Direction.WEST).uvs(1.53846F, 4.92308F, 4.61538F, 6.76923F).texture("#texture").end()
                        .face(Direction.UP).uvs(0.92308F, 0.30769F, 5.17949F, 4.56411F).texture("#texture").end()
                        .face(Direction.DOWN).uvs(5.84615F, 11.69231F, 10.15385F, 16F).texture("#texture").end()
                        .end()))
                .partialState().with(BaseThreeCake.BITES, 1).addModels(new ConfiguredModel(models().getBuilder(name(block) + "_slice1")
                        .texture("texture", blockTexture(block))
                        .texture("particle", blockTexture(block))
                        .element()
                        .from(1, 0, 1).to(15, 8, 15)
                        .face(Direction.NORTH).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture("#texture").end()
                        .face(Direction.EAST).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture("#texture").end()
                        .face(Direction.SOUTH).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture("#texture").end()
                        .face(Direction.WEST).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture("#texture").end()
                        .face(Direction.UP).uvs(5.84615F, 11.69231F, 10.15385F, 16F).texture("#texture").end()
                        .face(Direction.DOWN).uvs(0.92308F, 11.69231F, 5.23077F, 16F).texture("#texture").end()
                        .end()
                        .element()
                        .from(2, 8, 2).to(14, 15, 14)
                        .face(Direction.NORTH).uvs(1.23077F, 6.76923F, 4.92308F, 8.92308F).texture("#texture").end()
                        .face(Direction.EAST).uvs(1.23077F, 6.76923F, 4.92308F, 8.92308F).texture("#texture").end()
                        .face(Direction.SOUTH).uvs(1.23077F, 6.76923F, 4.92308F, 8.92308F).texture("#texture").end()
                        .face(Direction.WEST).uvs(1.23077F, 6.76923F, 4.92308F, 8.92308F).texture("#texture").end()
                        .face(Direction.UP).uvs(11.07692F, 12F, 14.76923F, 15.69231F).texture("#texture").end()
                        .face(Direction.DOWN).uvs(5.84615F, 11.69231F, 10.15385F, 16F).texture("#texture").end()
                        .end()
                        .element()
                        .from(5, 15, 3).to(13, 21, 13)
                        .face(Direction.NORTH).uvs(1.53846F, 4.92308F, 4F, 6.76923F).texture("#texture").end()
                        .face(Direction.EAST).uvs(1.53846F, 4.92308F, 4.61538F, 6.76923F).texture("#texture").end()
                        .face(Direction.SOUTH).uvs(2.15385F, 4.92308F, 4.61538F, 6.76923F).texture("#texture").end()
                        .face(Direction.WEST).uvs(6.46154F, 4.92308F, 9.53846F, 6.76923F).texture("#texture").end()
                        .face(Direction.UP).uvs(1.84615F, 0.30769F, 5.17949F, 4.56411F).texture("#texture").end()
                        .face(Direction.DOWN).uvs(5.84615F, 11.69231F, 10.15385F, 16F).texture("#texture").end()
                        .end()))
                .partialState().with(BaseThreeCake.BITES, 2).addModels(new ConfiguredModel(models().getBuilder(name(block) + "_slice2")
                        .texture("texture", blockTexture(block))
                        .texture("particle", blockTexture(block))
                        .element()
                        .from(1, 0, 1).to(15, 8, 15)
                        .face(Direction.NORTH).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture("#texture").end()
                        .face(Direction.EAST).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture("#texture").end()
                        .face(Direction.SOUTH).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture("#texture").end()
                        .face(Direction.WEST).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture("#texture").end()
                        .face(Direction.UP).uvs(5.84615F, 11.69231F, 10.15385F, 16F).texture("#texture").end()
                        .face(Direction.DOWN).uvs(0.92308F, 11.69231F, 5.23077F, 16F).texture("#texture").end()
                        .end()
                        .element()
                        .from(2, 8, 2).to(14, 15, 14)
                        .face(Direction.NORTH).uvs(1.23077F, 6.76923F, 4.92308F, 8.92308F).texture("#texture").end()
                        .face(Direction.EAST).uvs(1.23077F, 6.76923F, 4.92308F, 8.92308F).texture("#texture").end()
                        .face(Direction.SOUTH).uvs(1.23077F, 6.76923F, 4.92308F, 8.92308F).texture("#texture").end()
                        .face(Direction.WEST).uvs(1.23077F, 6.76923F, 4.92308F, 8.92308F).texture("#texture").end()
                        .face(Direction.UP).uvs(11.07692F, 12F, 14.76923F, 15.69231F).texture("#texture").end()
                        .face(Direction.DOWN).uvs(5.84615F, 11.69231F, 10.15385F, 16F).texture("#texture").end()
                        .end()
                        .element()
                        .from(7, 15, 3).to(13, 21, 13)
                        .face(Direction.NORTH).uvs(1.53846F, 4.92308F, 3.38462F, 6.76923F).texture("#texture").end()
                        .face(Direction.EAST).uvs(1.53846F, 4.92308F, 4.61538F, 6.76923F).texture("#texture").end()
                        .face(Direction.SOUTH).uvs(2.76923F, 4.92308F, 4.61538F, 6.76923F).texture("#texture").end()
                        .face(Direction.WEST).uvs(6.46154F, 4.92308F, 9.53846F, 6.76923F).texture("#texture").end()
                        .face(Direction.UP).uvs(2.76923F, 0.30769F, 5.17949F, 4.56411F).texture("#texture").end()
                        .face(Direction.DOWN).uvs(5.84615F, 11.69231F, 10.15385F, 16F).texture("#texture").end()
                        .end()))
                .partialState().with(BaseThreeCake.BITES, 3).addModels(new ConfiguredModel(models().getBuilder(name(block) + "_slice3")
                        .texture("texture", blockTexture(block))
                        .texture("particle", blockTexture(block))
                        .element()
                        .from(1, 0, 1).to(15, 8, 15)
                        .face(Direction.NORTH).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture("#texture").end()
                        .face(Direction.EAST).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture("#texture").end()
                        .face(Direction.SOUTH).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture("#texture").end()
                        .face(Direction.WEST).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture("#texture").end()
                        .face(Direction.UP).uvs(5.84615F, 11.69231F, 10.15385F, 16F).texture("#texture").end()
                        .face(Direction.DOWN).uvs(0.92308F, 11.69231F, 5.23077F, 16F).texture("#texture").end()
                        .end()
                        .element()
                        .from(2, 8, 2).to(14, 15, 14)
                        .face(Direction.NORTH).uvs(1.23077F, 6.76923F, 4.92308F, 8.92308F).texture("#texture").end()
                        .face(Direction.EAST).uvs(1.23077F, 6.76923F, 4.92308F, 8.92308F).texture("#texture").end()
                        .face(Direction.SOUTH).uvs(1.23077F, 6.76923F, 4.92308F, 8.92308F).texture("#texture").end()
                        .face(Direction.WEST).uvs(1.23077F, 6.76923F, 4.92308F, 8.92308F).texture("#texture").end()
                        .face(Direction.UP).uvs(11.07692F, 12F, 14.76923F, 15.69231F).texture("#texture").end()
                        .face(Direction.DOWN).uvs(5.84615F, 11.69231F, 10.15385F, 16F).texture("#texture").end()
                        .end()
                        .element()
                        .from(9, 15, 3).to(13, 21, 13)
                        .face(Direction.NORTH).uvs(1.53846F, 4.92308F, 2.76923F, 6.76923F).texture("#texture").end()
                        .face(Direction.EAST).uvs(1.53846F, 4.92308F, 4.61538F, 6.76923F).texture("#texture").end()
                        .face(Direction.SOUTH).uvs(3.38462F, 4.92308F, 4.61538F, 6.76923F).texture("#texture").end()
                        .face(Direction.WEST).uvs(11.38462F, 4.92308F, 14.46154F, 6.76923F).texture("#texture").end()
                        .face(Direction.UP).uvs(3.38462F, 0.30769F, 5.17949F, 4.56411F).texture("#texture").end()
                        .face(Direction.DOWN).uvs(5.84615F, 11.69231F, 10.15385F, 16F).texture("#texture").end()
                        .end()))
                .partialState().with(BaseThreeCake.BITES, 4).addModels(new ConfiguredModel(models().getBuilder(name(block) + "_slice4")
                        .texture("texture", blockTexture(block))
                        .texture("particle", blockTexture(block))
                        .element()
                        .from(1, 0, 1).to(15, 8, 15)
                        .face(Direction.NORTH).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture("#texture").end()
                        .face(Direction.EAST).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture("#texture").end()
                        .face(Direction.SOUTH).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture("#texture").end()
                        .face(Direction.WEST).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture("#texture").end()
                        .face(Direction.UP).uvs(5.84615F, 11.69231F, 10.15385F, 16F).texture("#texture").end()
                        .face(Direction.DOWN).uvs(0.92308F, 11.69231F, 5.23077F, 16F).texture("#texture").end()
                        .end()
                        .element()
                        .from(2, 8, 2).to(14, 15, 14)
                        .face(Direction.NORTH).uvs(1.23077F, 6.76923F, 4.92308F, 8.92308F).texture("#texture").end()
                        .face(Direction.EAST).uvs(1.23077F, 6.76923F, 4.92308F, 8.92308F).texture("#texture").end()
                        .face(Direction.SOUTH).uvs(1.23077F, 6.76923F, 4.92308F, 8.92308F).texture("#texture").end()
                        .face(Direction.WEST).uvs(1.23077F, 6.76923F, 4.92308F, 8.92308F).texture("#texture").end()
                        .face(Direction.UP).uvs(11.07692F, 12F, 14.76923F, 15.69231F).texture("#texture").end()
                        .face(Direction.DOWN).uvs(5.84615F, 11.69231F, 10.15385F, 16F).texture("#texture").end()
                        .end()
                        .element()
                        .from(11, 15, 3).to(13, 21, 13)
                        .face(Direction.NORTH).uvs(1.53846F, 4.92308F, 2.15385F, 6.76923F).texture("#texture").end()
                        .face(Direction.EAST).uvs(1.53846F, 4.92308F, 4.61538F, 6.76923F).texture("#texture").end()
                        .face(Direction.SOUTH).uvs(4F, 4.92308F, 4.61538F, 6.76923F).texture("#texture").end()
                        .face(Direction.WEST).uvs(6.46154F, 4.92308F, 9.53846F, 6.76923F).texture("#texture").end()
                        .face(Direction.UP).uvs(4.30769F, 0.30769F, 5.17949F, 4.56411F).texture("#texture").end()
                        .face(Direction.DOWN).uvs(5.84615F, 11.69231F, 10.15385F, 16F).texture("#texture").end()
                        .end()))
                .partialState().with(BaseThreeCake.BITES, 5).addModels(new ConfiguredModel(models().getBuilder(name(block) + "_slice5")
                        .texture("texture", blockTexture(block))
                        .texture("particle", blockTexture(block))
                        .element()
                        .from(1, 0, 1).to(15, 8, 15)
                        .face(Direction.NORTH).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture("#texture").end()
                        .face(Direction.EAST).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture("#texture").end()
                        .face(Direction.SOUTH).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture("#texture").end()
                        .face(Direction.WEST).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture("#texture").end()
                        .face(Direction.UP).uvs(5.84615F, 11.69231F, 10.15385F, 16F).texture("#texture").end()
                        .face(Direction.DOWN).uvs(0.92308F, 11.69231F, 5.23077F, 16F).texture("#texture").end()
                        .end()
                        .element()
                        .from(2, 8, 2).to(14, 15, 14)
                        .face(Direction.NORTH).uvs(1.23077F, 6.76923F, 4.92308F, 8.92308F).texture("#texture").end()
                        .face(Direction.EAST).uvs(1.23077F, 6.76923F, 4.92308F, 8.92308F).texture("#texture").end()
                        .face(Direction.SOUTH).uvs(1.23077F, 6.76923F, 4.92308F, 8.92308F).texture("#texture").end()
                        .face(Direction.WEST).uvs(1.23077F, 6.76923F, 4.92308F, 8.92308F).texture("#texture").end()
                        .face(Direction.UP).uvs(11.07692F, 12F, 14.76923F, 15.69231F).texture("#texture").end()
                        .face(Direction.DOWN).uvs(5.84615F, 11.69231F, 10.15385F, 16F).texture("#texture").end()
                        .end()))
                .partialState().with(BaseThreeCake.BITES, 6).addModels(new ConfiguredModel(models().getBuilder(name(block) + "_slice6")
                        .texture("texture", blockTexture(block))
                        .texture("particle", blockTexture(block))
                        .element()
                        .from(1, 0, 1).to(15, 8, 15)
                        .face(Direction.NORTH).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture("#texture").end()
                        .face(Direction.EAST).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture("#texture").end()
                        .face(Direction.SOUTH).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture("#texture").end()
                        .face(Direction.WEST).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture("#texture").end()
                        .face(Direction.UP).uvs(5.84615F, 11.69231F, 10.15385F, 16F).texture("#texture").end()
                        .face(Direction.DOWN).uvs(0.92308F, 11.69231F, 5.23077F, 16F).texture("#texture").end()
                        .end()
                        .element()
                        .from(4, 8, 2).to(14, 15, 14)
                        .face(Direction.NORTH).uvs(1.23077F, 6.76923F, 4.30769F, 8.92308F).texture("#texture").end()
                        .face(Direction.EAST).uvs(1.23077F, 6.76923F, 4.92308F, 8.92308F).texture("#texture").end()
                        .face(Direction.SOUTH).uvs(1.84615F, 6.76923F, 4.92308F, 8.92308F).texture("#texture").end()
                        .face(Direction.WEST).uvs(6.15385F, 2.76923F, 9.84615F, 4.92308F).texture("#texture").end()
                        .face(Direction.UP).uvs(11.69231F, 12F, 14.76923F, 15.69231F).texture("#texture").end()
                        .face(Direction.DOWN).uvs(5.84615F, 11.69231F, 10.15385F, 16F).texture("#texture").end()
                        .end()))
                .partialState().with(BaseThreeCake.BITES, 7).addModels(new ConfiguredModel(models().getBuilder(name(block) + "_slice7")
                        .texture("texture", blockTexture(block))
                        .texture("particle", blockTexture(block))
                        .element()
                        .from(1, 0, 1).to(15, 8, 15)
                        .face(Direction.NORTH).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture("#texture").end()
                        .face(Direction.EAST).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture("#texture").end()
                        .face(Direction.SOUTH).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture("#texture").end()
                        .face(Direction.WEST).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture("#texture").end()
                        .face(Direction.UP).uvs(5.84615F, 11.69231F, 10.15385F, 16F).texture("#texture").end()
                        .face(Direction.DOWN).uvs(0.92308F, 11.69231F, 5.23077F, 16F).texture("#texture").end()
                        .end()
                        .element()
                        .from(6, 8, 2).to(14, 15, 14)
                        .face(Direction.NORTH).uvs(1.23077F, 6.76923F, 3.69231F, 8.92308F).texture("#texture").end()
                        .face(Direction.EAST).uvs(1.23077F, 6.76923F, 4.92308F, 8.92308F).texture("#texture").end()
                        .face(Direction.SOUTH).uvs(2.46154F, 6.76923F, 4.92308F, 8.92308F).texture("#texture").end()
                        .face(Direction.WEST).uvs(6.15385F, 2.76923F, 9.84615F, 4.92308F).texture("#texture").end()
                        .face(Direction.UP).uvs(12.30769F, 12F, 14.76923F, 15.69231F).texture("#texture").end()
                        .face(Direction.DOWN).uvs(5.84615F, 11.69231F, 10.15385F, 16F).texture("#texture").end()
                        .end()))
                .partialState().with(BaseThreeCake.BITES, 8).addModels(new ConfiguredModel(models().getBuilder(name(block) + "_slice8")
                        .texture("texture", blockTexture(block))
                        .texture("particle", blockTexture(block))
                        .element()
                        .from(1, 0, 1).to(15, 8, 15)
                        .face(Direction.NORTH).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture("#texture").end()
                        .face(Direction.EAST).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture("#texture").end()
                        .face(Direction.SOUTH).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture("#texture").end()
                        .face(Direction.WEST).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture("#texture").end()
                        .face(Direction.UP).uvs(5.84615F, 11.69231F, 10.15385F, 16F).texture("#texture").end()
                        .face(Direction.DOWN).uvs(0.92308F, 11.69231F, 5.23077F, 16F).texture("#texture").end()
                        .end()
                        .element()
                        .from(8, 8, 2).to(14, 15, 14)
                        .face(Direction.NORTH).uvs(1.23077F, 6.76923F, 3.07692F, 8.92308F).texture("#texture").end()
                        .face(Direction.EAST).uvs(1.23077F, 6.76923F, 4.92308F, 8.92308F).texture("#texture").end()
                        .face(Direction.SOUTH).uvs(3.07692F, 6.76923F, 4.92308F, 8.92308F).texture("#texture").end()
                        .face(Direction.WEST).uvs(6.15385F, 2.76923F, 9.84615F, 4.92308F).texture("#texture").end()
                        .face(Direction.UP).uvs(12.92308F, 12F, 14.76923F, 15.69231F).texture("#texture").end()
                        .face(Direction.DOWN).uvs(5.84615F, 11.69231F, 10.15385F, 16F).texture("#texture").end()
                        .end()))
                .partialState().with(BaseThreeCake.BITES, 9).addModels(new ConfiguredModel(models().getBuilder(name(block) + "_slice9")
                        .texture("texture", blockTexture(block))
                        .texture("particle", blockTexture(block))
                        .element()
                        .from(1, 0, 1).to(15, 8, 15)
                        .face(Direction.NORTH).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture("#texture").end()
                        .face(Direction.EAST).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture("#texture").end()
                        .face(Direction.SOUTH).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture("#texture").end()
                        .face(Direction.WEST).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture("#texture").end()
                        .face(Direction.UP).uvs(5.84615F, 11.69231F, 10.15385F, 16F).texture("#texture").end()
                        .face(Direction.DOWN).uvs(0.92308F, 11.69231F, 5.23077F, 16F).texture("#texture").end()
                        .end()
                        .element()
                        .from(10, 8, 2).to(14, 15, 14)
                        .face(Direction.NORTH).uvs(1.23077F, 6.76923F, 2.46154F, 8.92308F).texture("#texture").end()
                        .face(Direction.EAST).uvs(1.23077F, 6.76923F, 4.92308F, 8.92308F).texture("#texture").end()
                        .face(Direction.SOUTH).uvs(3.69231F, 6.76923F, 4.92308F, 8.92308F).texture("#texture").end()
                        .face(Direction.WEST).uvs(6.15385F, 2.76923F, 9.84615F, 4.92308F).texture("#texture").end()
                        .face(Direction.UP).uvs(13.53846F, 12F, 14.76923F, 15.69231F).texture("#texture").end()
                        .face(Direction.DOWN).uvs(5.84615F, 11.69231F, 10.15385F, 16F).texture("#texture").end()
                        .end()))
                .partialState().with(BaseThreeCake.BITES, 10).addModels(new ConfiguredModel(models().getBuilder(name(block) + "_slice10")
                        .texture("texture", blockTexture(block))
                        .texture("particle", blockTexture(block))
                        .element()
                        .from(1, 0, 1).to(15, 8, 15)
                        .face(Direction.NORTH).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture("#texture").end()
                        .face(Direction.EAST).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture("#texture").end()
                        .face(Direction.SOUTH).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture("#texture").end()
                        .face(Direction.WEST).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture("#texture").end()
                        .face(Direction.UP).uvs(5.84615F, 11.69231F, 10.15385F, 16F).texture("#texture").end()
                        .face(Direction.DOWN).uvs(0.92308F, 11.69231F, 5.23077F, 16F).texture("#texture").end()
                        .end()))
                .partialState().with(BaseThreeCake.BITES, 11).addModels(new ConfiguredModel(models().getBuilder(name(block) + "_slice11")
                        .texture("texture", blockTexture(block))
                        .texture("particle", blockTexture(block))
                        .element()
                        .from(3, 0, 1).to(15, 8, 15)
                        .face(Direction.NORTH).uvs(0.92308F, 8.92308F, 4.61538F, 11.38462F).texture("#texture").end()
                        .face(Direction.EAST).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture("#texture").end()
                        .face(Direction.SOUTH).uvs(1.53846F, 8.92308F, 5.23077F, 11.38462F).texture("#texture").end()
                        .face(Direction.WEST).uvs(10.76923F, 2.46154F, 15.07692F, 4.92308F).texture("#texture").end()
                        .face(Direction.UP).uvs(6.46154F, 11.69231F, 10.15385F, 16F).texture("#texture").end()
                        .face(Direction.DOWN).uvs(1.53846F, 11.69231F, 5.23077F, 16F).texture("#texture").end()
                        .end()))
                .partialState().with(BaseThreeCake.BITES, 12).addModels(new ConfiguredModel(models().getBuilder(name(block) + "_slice12")
                        .texture("texture", blockTexture(block))
                        .texture("particle", blockTexture(block))
                        .element()
                        .from(5, 0, 1).to(15, 8, 15)
                        .face(Direction.NORTH).uvs(0.92308F, 8.92308F, 4F, 11.38462F).texture("#texture").end()
                        .face(Direction.EAST).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture("#texture").end()
                        .face(Direction.SOUTH).uvs(2.15385F, 8.92308F, 5.23077F, 11.38462F).texture("#texture").end()
                        .face(Direction.WEST).uvs(10.76923F, 2.46154F, 15.07692F, 4.92308F).texture("#texture").end()
                        .face(Direction.UP).uvs(7.07692F, 11.69231F, 10.15385F, 16F).texture("#texture").end()
                        .face(Direction.DOWN).uvs(2.15385F, 11.69231F, 5.23077F, 16F).texture("#texture").end()
                        .end()))
                .partialState().with(BaseThreeCake.BITES, 13).addModels(new ConfiguredModel(models().getBuilder(name(block) + "_slice13")
                        .texture("texture", blockTexture(block))
                        .texture("particle", blockTexture(block))
                        .element()
                        .from(7, 0, 1).to(15, 8, 15)
                        .face(Direction.NORTH).uvs(0.92308F, 8.92308F, 3.38462F, 11.38462F).texture("#texture").end()
                        .face(Direction.EAST).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture("#texture").end()
                        .face(Direction.SOUTH).uvs(2.76923F, 8.92308F, 5.23077F, 11.38462F).texture("#texture").end()
                        .face(Direction.WEST).uvs(10.76923F, 2.46154F, 15.07692F, 4.92308F).texture("#texture").end()
                        .face(Direction.UP).uvs(7.69231F, 11.69231F, 10.15385F, 16F).texture("#texture").end()
                        .face(Direction.DOWN).uvs(2.76923F, 11.69231F, 5.23077F, 16F).texture("#texture").end()
                        .end()))
                .partialState().with(BaseThreeCake.BITES, 14).addModels(new ConfiguredModel(models().getBuilder(name(block) + "_slice14")
                        .texture("texture", blockTexture(block))
                        .texture("particle", blockTexture(block))
                        .element()
                        .from(9, 0, 1).to(15, 8, 15)
                        .face(Direction.NORTH).uvs(0.92308F, 8.92308F, 2.76923F, 11.38462F).texture("#texture").end()
                        .face(Direction.EAST).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture("#texture").end()
                        .face(Direction.SOUTH).uvs(3.38462F, 8.92308F, 5.23077F, 11.38462F).texture("#texture").end()
                        .face(Direction.WEST).uvs(10.76923F, 2.46154F, 15.07692F, 4.92308F).texture("#texture").end()
                        .face(Direction.UP).uvs(8.30769F, 11.69231F, 10.15385F, 16F).texture("#texture").end()
                        .face(Direction.DOWN).uvs(3.38462F, 11.69231F, 5.23077F, 16F).texture("#texture").end()
                        .end()))
                .partialState().with(BaseThreeCake.BITES, 15).addModels(new ConfiguredModel(models().getBuilder(name(block) + "_slice15")
                        .texture("texture", blockTexture(block))
                        .texture("particle", blockTexture(block))
                        .element()
                        .from(12, 0, 1).to(15, 8, 15)
                        .face(Direction.NORTH).uvs(0.92308F, 8.92308F, 1.84615F, 11.38462F).texture("#texture").end()
                        .face(Direction.EAST).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture("#texture").end()
                        .face(Direction.SOUTH).uvs(4.30769F, 8.92308F, 5.23077F, 11.38462F).texture("#texture").end()
                        .face(Direction.WEST).uvs(10.76923F, 2.46154F, 15.07692F, 4.92308F).texture("#texture").end()
                        .face(Direction.UP).uvs(9.23077F, 11.69231F, 10.15385F, 16F).texture("#texture").end()
                        .face(Direction.DOWN).uvs(4.30769F, 11.69231F, 5.23077F, 16F).texture("#texture").end()
                        .end()));
    }
}
