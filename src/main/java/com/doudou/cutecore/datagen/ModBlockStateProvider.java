package com.doudou.cutecore.datagen;

import com.doudou.cutecore.CuteCore;
import com.doudou.cutecore.blocks.CuteCoreBlock;
import com.doudou.cutecore.blocks.StrawberryCropBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
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
        simpleBlockWithItem(block, models().cubeTop(name(block), blockTexture(block), new ResourceLocation(CuteCore.MOD_ID, "block/marshmello_block_top")));
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

        blockWithItem(CuteCoreBlock.PINK_SAPPHIRE_BLOCK);
        blockWithItem(CuteCoreBlock.PINK_SAPPHIRE_ORE);
        blockWithItem(CuteCoreBlock.DEEPSLATE_PINK_SAPPHIRE_ORE);

        blockWithItem(CuteCoreBlock.RUBY_BLOCK);
        blockWithItem(CuteCoreBlock.RUBY_ORE);
        blockWithItem(CuteCoreBlock.DEEPSLATE_RUBY_ORE);

        blockWithItem(CuteCoreBlock.CHOCOLATE_BLOCK);
        topblockWithItem(CuteCoreBlock.MARSHMELLO_BLOCK.get());
        BottomTopblockWithItem(CuteCoreBlock.ROASTED_MARSHMELLO_BLOCK.get());


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

}
