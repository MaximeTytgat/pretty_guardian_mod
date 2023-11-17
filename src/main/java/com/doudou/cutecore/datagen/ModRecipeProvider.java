package com.doudou.cutecore.datagen;

import com.doudou.cutecore.CuteCore;
import com.doudou.cutecore.blocks.CuteCoreBlock;
import com.doudou.cutecore.item.CuteCoreItem;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {

    private static final List<ItemLike> CAKE_SMELTABLES = List.of(
            CuteCoreBlock.THREE_STRAWBERRY_CHOCO_CAKE.get(),
            CuteCoreBlock.THREE_STRAWBERRY_CAKE.get()
    );

    public ModRecipeProvider(PackOutput p_248933_) {
        super(p_248933_);
    }



    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
//        oreSmelting(pWriter, CAKE_SMELTABLES, RecipeCategory.MISC, CuteCoreBlock.THREE_STRAWBERRY_CAKE.get().asItem(), 0.35F, 200, "cake");
//        oreBlasting(pWriter, CAKE_SMELTABLES, RecipeCategory.MISC, CuteCoreBlock.THREE_STRAWBERRY_CAKE.get().asItem(), 0.35F, 200, "cake");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CuteCoreItem.CHOCOLATE.get())
                .pattern("AB")
                .pattern("BA")
                .pattern("AB")
                .define('A', Items.COCOA_BEANS)
                .define('B', Items.SUGAR)
                .unlockedBy(getHasName(Items.COCOA_BEANS), has(Items.COCOA_BEANS))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CuteCoreBlock.THREE_STRAWBERRY_CAKE.get())
                .pattern("AAA")
                .pattern("BCB")
                .pattern("DDD")
                .define('A', CuteCoreItem.STRAWBERRY.get())
                .define('B', Items.SUGAR)
                .define('C', Items.EGG)
                .define('D', Items.WHEAT)
                .unlockedBy(getHasName(Items.EGG), has(Items.EGG))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CuteCoreBlock.THREE_STRAWBERRY_CHOCO_CAKE.get())
                .pattern("AAA")
                .pattern("DBD")
                .pattern("CCC")
                .define('A', CuteCoreItem.STRAWBERRY.get())
                .define('B', Items.EGG)
                .define('C', Items.WHEAT)
                .define('D', CuteCoreItem.CHOCOLATE.get())
                .unlockedBy(getHasName(Items.EGG), has(Items.EGG))
                .save(pWriter);


        BlockIngotRecipes(pWriter,
                CuteCoreBlock.PINK_SAPPHIRE_BLOCK.get(),
                CuteCoreItem.PINK_SAPPHIRE.get()
        );

        toolRecipes(pWriter,
                CuteCoreItem.PINK_SAPPHIRE_SWORD.get(),
                CuteCoreItem.PINK_SAPPHIRE_PICKAXE.get(),
                CuteCoreItem.PINK_SAPPHIRE_AXE.get(),
                CuteCoreItem.PINK_SAPPHIRE_SHOVEL.get(),
                CuteCoreItem.PINK_SAPPHIRE_HOE.get(),
                CuteCoreItem.PINK_SAPPHIRE.get()
        );

        armorRecipes(pWriter,
                CuteCoreItem.PINK_SAPPHIRE_HELMET.get(),
                CuteCoreItem.PINK_SAPPHIRE_CHESTPLATE.get(),
                CuteCoreItem.PINK_SAPPHIRE_LEGGINGS.get(),
                CuteCoreItem.PINK_SAPPHIRE_BOOTS.get(),
                CuteCoreItem.PINK_SAPPHIRE.get()
        );

        BlockIngotRecipes(pWriter,
                CuteCoreBlock.RUBY_BLOCK.get(),
                CuteCoreItem.RUBY.get()
        );

        toolRecipes(pWriter,
                CuteCoreItem.RUBY_SWORD.get(),
                CuteCoreItem.RUBY_PICKAXE.get(),
                CuteCoreItem.RUBY_AXE.get(),
                CuteCoreItem.RUBY_SHOVEL.get(),
                CuteCoreItem.RUBY_HOE.get(),
                CuteCoreItem.RUBY.get()
        );

//        armorRecipes(pWriter,
//                CuteCoreItem.RUBY_HELMET.get(),
//                CuteCoreItem.RUBY_CHESTPLATE.get(),
//                CuteCoreItem.RUBY_LEGGINGS.get(),
//                CuteCoreItem.RUBY_BOOTS.get(),
//                CuteCoreItem.RUBY.get()
//        );



    }

    protected static void oreSmelting(Consumer<FinishedRecipe> p_250654_, List<ItemLike> p_250172_, RecipeCategory p_250588_, ItemLike p_251868_, float p_250789_, int p_252144_, String p_251687_) {
        oreCooking(p_250654_, RecipeSerializer.SMELTING_RECIPE, p_250172_, p_250588_, p_251868_, p_250789_, p_252144_, p_251687_, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> p_248775_, List<ItemLike> p_251504_, RecipeCategory p_248846_, ItemLike p_249735_, float p_248783_, int p_250303_, String p_251984_) {
        oreCooking(p_248775_, RecipeSerializer.BLASTING_RECIPE, p_251504_, p_248846_, p_249735_, p_248783_, p_250303_, p_251984_, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> finishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> recipeSerializer, List<ItemLike> itemLikeList, RecipeCategory recipeCategory, ItemLike itemLike, float p_251871_, int p_251316_, String p_251450_, String p_249236_) {
        for(ItemLike itemlike : itemLikeList) {
            SimpleCookingRecipeBuilder.generic(
                    Ingredient.of(itemlike), recipeCategory, itemLike, p_251871_, p_251316_, recipeSerializer)
                    .group(p_251450_)
                    .unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(finishedRecipeConsumer);
        }

    }

    protected static void BlockIngotRecipes(Consumer<FinishedRecipe> finishedRecipeConsumer, ItemLike block, ItemLike ingot) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, block)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ingot)
                .unlockedBy(getHasName(ingot), has(ingot))
                .save(finishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ingot, 9)
                .requires(block)
                .unlockedBy(getHasName(ingot), has(block))
                .save(finishedRecipeConsumer);
    }

    protected static void armorRecipes(Consumer<FinishedRecipe> finishedRecipeConsumer, Item helmet, Item chestplate, Item leggings, Item boots, ItemLike material) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, helmet)
                .pattern("AAA")
                .pattern("A A")
                .define('A', material)
                .unlockedBy(getHasName(material), has(material))
                .save(finishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, chestplate)
                .pattern("A A")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', material)
                .unlockedBy(getHasName(material), has(material))
                .save(finishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, leggings)
                .pattern("AAA")
                .pattern("A A")
                .pattern("A A")
                .define('A', material)
                .unlockedBy(getHasName(material), has(material))
                .save(finishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, boots)
                .pattern("A A")
                .pattern("A A")
                .define('A', material)
                .unlockedBy(getHasName(material), has(material))
                .save(finishedRecipeConsumer);
    }

    protected static void toolRecipes(Consumer<FinishedRecipe> finishedRecipeConsumer, Item sword, Item pickaxe, Item axe, Item shovel, Item hoe, ItemLike material) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, sword)
                .pattern(" A ")
                .pattern(" A ")
                .pattern(" B ")
                .define('A', material)
                .define('B', Items.STICK)
                .unlockedBy(getHasName(material), has(material))
                .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                .save(finishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, pickaxe)
                .pattern("AAA")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', material)
                .define('B', Items.STICK)
                .unlockedBy(getHasName(material), has(material))
                .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                .save(finishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, axe)
                .pattern("AA ")
                .pattern("AB ")
                .pattern(" B ")
                .define('A', material)
                .define('B', Items.STICK)
                .unlockedBy(getHasName(material), has(material))
                .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                .save(finishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, shovel)
                .pattern(" A ")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', material)
                .define('B', Items.STICK)
                .unlockedBy(getHasName(material), has(material))
                .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                .save(finishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, hoe)
                .pattern(" AA")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', material)
                .define('B', Items.STICK)
                .unlockedBy(getHasName(material), has(material))
                .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                .save(finishedRecipeConsumer);
    }

}
