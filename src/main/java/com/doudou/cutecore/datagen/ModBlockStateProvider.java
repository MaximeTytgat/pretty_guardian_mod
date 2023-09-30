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
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, CuteCore.MODID, exFileHelper);
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
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
    }


    public void makeStrawberryCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> strawberryStates(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] strawberryStates(BlockState state, CropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((StrawberryCropBlock) block).getAgeProperty()),
                new ResourceLocation(CuteCore.MODID, "block/" + textureName + state.getValue(((StrawberryCropBlock) block).getAgeProperty()))).renderType("cutout"));

        return models;
    }

}
