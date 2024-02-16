package com.max.prettyguardian.datagen;

import com.max.prettyguardian.PrettyGuardian;
import com.max.prettyguardian.blocks.PrettyGuardianBlock;
import com.max.prettyguardian.item.PrettyGuardianItem;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {

    private static final List<ItemLike> CAKE_SMELTABLES = List.of(
            PrettyGuardianBlock.THREE_STRAWBERRY_CHOCO_CAKE.get(),
            PrettyGuardianBlock.THREE_STRAWBERRY_CAKE.get()
    );

    public ModRecipeProvider(PackOutput p_248933_) {
        super(p_248933_);
    }



    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        SimpleCookingRecipeBuilder.generic(
                Ingredient.of(Items.SUGAR), RecipeCategory.MISC, PrettyGuardianItem.CARAMEL.get(), 0.35F, 200, RecipeSerializer.SMELTING_RECIPE)
                .group("caramel_from_sugar")
                .unlockedBy(getHasName(Items.SUGAR), has(Items.SUGAR))
                .save(pWriter);

        BlockIngotRecipes(pWriter,
                PrettyGuardianBlock.PINK_SAPPHIRE_BLOCK.get(),
                PrettyGuardianItem.PINK_SAPPHIRE.get()
        );

        toolRecipes(pWriter,
                PrettyGuardianItem.PINK_SAPPHIRE_SWORD.get(),
                PrettyGuardianItem.PINK_SAPPHIRE_PICKAXE.get(),
                PrettyGuardianItem.PINK_SAPPHIRE_AXE.get(),
                PrettyGuardianItem.PINK_SAPPHIRE_SHOVEL.get(),
                PrettyGuardianItem.PINK_SAPPHIRE_HOE.get(),
                PrettyGuardianItem.PINK_SAPPHIRE.get()
        );

        armorRecipes(pWriter,
                PrettyGuardianItem.PINK_SAPPHIRE_HELMET.get(),
                PrettyGuardianItem.PINK_SAPPHIRE_CHESTPLATE.get(),
                PrettyGuardianItem.PINK_SAPPHIRE_LEGGINGS.get(),
                PrettyGuardianItem.PINK_SAPPHIRE_BOOTS.get(),
                PrettyGuardianItem.PINK_SAPPHIRE.get()
        );

        BlockIngotRecipes(pWriter,
                PrettyGuardianBlock.RUBY_BLOCK.get(),
                PrettyGuardianItem.RUBY.get()
        );

        toolRecipes(pWriter,
                PrettyGuardianItem.RUBY_SWORD.get(),
                PrettyGuardianItem.RUBY_PICKAXE.get(),
                PrettyGuardianItem.RUBY_AXE.get(),
                PrettyGuardianItem.RUBY_SHOVEL.get(),
                PrettyGuardianItem.RUBY_HOE.get(),
                PrettyGuardianItem.RUBY.get()
        );

        armorRecipes(pWriter,
                PrettyGuardianItem.RUBY_HELMET.get(),
                PrettyGuardianItem.RUBY_CHESTPLATE.get(),
                PrettyGuardianItem.RUBY_LEGGINGS.get(),
                PrettyGuardianItem.RUBY_BOOTS.get(),
                PrettyGuardianItem.RUBY.get()
        );

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, PrettyGuardianItem.CHOCOLATE.get())
                .pattern("AB")
                .pattern("BA")
                .pattern("AB")
                .define('A', Items.COCOA_BEANS)
                .define('B', Items.SUGAR)
                .unlockedBy(getHasName(Items.COCOA_BEANS), has(Items.COCOA_BEANS))
                .save(pWriter);

        BlockIngotRecipes(pWriter,
                PrettyGuardianBlock.CHOCOLATE_BLOCK.get(),
                PrettyGuardianItem.CHOCOLATE.get()
        );

        simpleShapeless(pWriter, RecipeCategory.FOOD, PrettyGuardianItem.AGARAGAR.get(), 2, Items.KELP, 1);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, PrettyGuardianItem.CREAM.get(), 6)
                .pattern("AAA")
                .pattern("BBB")
                .define('A', Items.SUGAR)
                .define('B', Items.MILK_BUCKET)
                .unlockedBy(getHasName(Items.SUGAR), has(Items.SUGAR))
                .unlockedBy(getHasName(Items.MILK_BUCKET), has(Items.MILK_BUCKET))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, PrettyGuardianItem.MARSHMALLOW.get(), 4)
                .pattern("ABA")
                .pattern("BCB")
                .pattern("ABA")
                .define('A', PrettyGuardianItem.AGARAGAR.get())
                .define('B', Items.SUGAR)
                .define('C', Items.WATER_BUCKET)
                .unlockedBy(getHasName(PrettyGuardianItem.AGARAGAR.get()), has(PrettyGuardianItem.AGARAGAR.get()))
                .save(pWriter);

        doubleSimpleShapeless(pWriter, RecipeCategory.FOOD, PrettyGuardianItem.MARSHMALLOW_STICK.get(), 1,
                PrettyGuardianItem.MARSHMALLOW.get(), Items.STICK);

        SimpleCookingRecipeBuilder.generic(
                Ingredient.of(PrettyGuardianItem.MARSHMALLOW_STICK.get()), RecipeCategory.FOOD, PrettyGuardianItem.ROASTED_MARSHMALLOW_STICK.get(), 0.35F, 200, RecipeSerializer.CAMPFIRE_COOKING_RECIPE)
                .group("roasted_marshmallow_stick")
                .unlockedBy(getHasName(PrettyGuardianItem.MARSHMALLOW_STICK.get()), has(PrettyGuardianItem.MARSHMALLOW_STICK.get()))
                .save(pWriter);

        BlockIngotRecipes(pWriter,
                PrettyGuardianBlock.MARSHMELLO_BLOCK.get(),
                PrettyGuardianItem.MARSHMALLOW.get()
        );

        SimpleCookingRecipeBuilder.generic(
                Ingredient.of(PrettyGuardianBlock.MARSHMELLO_BLOCK.get()), RecipeCategory.FOOD, PrettyGuardianBlock.ROASTED_MARSHMELLO_BLOCK.get(), 0.35F, 200, RecipeSerializer.SMELTING_RECIPE)
                .group("roasted_marshmello_block")
                .unlockedBy(getHasName(PrettyGuardianBlock.MARSHMELLO_BLOCK.get()), has(PrettyGuardianBlock.MARSHMELLO_BLOCK.get()))
                .save(pWriter);


        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, PrettyGuardianItem.DONUT.get(), 4)
                .pattern("ABA")
                .pattern("BCB")
                .pattern("ABA")
                .define('A', Items.SUGAR)
                .define('B', Items.WHEAT)
                .define('C', Items.EGG)
                .unlockedBy(getHasName(Items.SUGAR), has(Items.SUGAR))
                .unlockedBy(getHasName(Items.WHEAT), has(Items.WHEAT))
                .unlockedBy(getHasName(Items.EGG), has(Items.EGG))
                .save(pWriter);

        doubleSimpleShapeless(pWriter, RecipeCategory.FOOD, PrettyGuardianItem.CHOCOLATE_DONUT.get(), 1,
                PrettyGuardianItem.DONUT.get(), PrettyGuardianItem.CHOCOLATE.get());
        doubleSimpleShapeless(pWriter, RecipeCategory.FOOD, PrettyGuardianItem.STRAWBERRY_DONUT.get(), 1,
                PrettyGuardianItem.DONUT.get(), PrettyGuardianItem.STRAWBERRY.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, PrettyGuardianItem.MOCHI_DONUT.get())
                .pattern("ABA")
                .pattern("BCB")
                .pattern("ABA")
                .define('A', PrettyGuardianItem.AGARAGAR.get())
                .define('B', Items.WHEAT)
                .define('C', Items.EGG)
                .unlockedBy(getHasName(PrettyGuardianItem.AGARAGAR.get()), has(PrettyGuardianItem.AGARAGAR.get()))
                .save(pWriter);

        doubleSimpleShapeless(pWriter, RecipeCategory.FOOD, PrettyGuardianItem.CHOCOLATE_MOCHI_DONUT.get(), 1,
                PrettyGuardianItem.MOCHI_DONUT.get(), PrettyGuardianItem.CHOCOLATE.get());
        doubleSimpleShapeless(pWriter, RecipeCategory.FOOD, PrettyGuardianItem.STRAWBERRY_MOCHI_DONUT.get(), 1,
                PrettyGuardianItem.MOCHI_DONUT.get(), PrettyGuardianItem.STRAWBERRY.get());
        doubleSimpleShapeless(pWriter, RecipeCategory.FOOD, PrettyGuardianItem.CHOCOLATE_STRAWBERRY.get(), 1,
                PrettyGuardianItem.CHOCOLATE.get(), PrettyGuardianItem.STRAWBERRY.get());
        doubleSimpleShapeless(pWriter, RecipeCategory.FOOD, PrettyGuardianItem.CHOCOLATE_MILK_BUCKET.get(), 1,
                PrettyGuardianItem.CHOCOLATE.get(), Items.MILK_BUCKET);
        doubleSimpleShapeless(pWriter, RecipeCategory.FOOD, PrettyGuardianItem.STRAWBERRY_MILK_BUCKET.get(), 3,
                PrettyGuardianItem.STRAWBERRY.get(), Items.MILK_BUCKET);
        doubleSimpleShapeless(pWriter, RecipeCategory.FOOD, PrettyGuardianItem.VANILLA_MILK_BUCKET.get(), 3,
                PrettyGuardianItem.VANILLA.get(), Items.MILK_BUCKET);

        simpleShapeless(pWriter, RecipeCategory.FOOD, PrettyGuardianItem.VANILLA_MILK_CARTON.get(), 3, PrettyGuardianItem.VANILLA_MILK_BUCKET.get());
        simpleShapeless(pWriter, RecipeCategory.FOOD, PrettyGuardianItem.STRAWBERRY_MILK_CARTON.get(), 3, PrettyGuardianItem.STRAWBERRY_MILK_BUCKET.get());
        simpleShapeless(pWriter, RecipeCategory.FOOD, PrettyGuardianItem.CHOCOLATE_MILK_CARTON.get(), 3, PrettyGuardianItem.CHOCOLATE_MILK_BUCKET.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PrettyGuardianItem.ICE_CREAM_CUP.get(), 3)
                .pattern("AAA")
                .pattern(" A ")
                .define('A', Items.GLASS_PANE)
                .unlockedBy(getHasName(Items.GLASS_PANE), has(Items.GLASS_PANE))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PrettyGuardianItem.JUICE_GLASS.get(), 6)
                .pattern("AA")
                .pattern("AA")
                .pattern("AA")
                .define('A', Items.GLASS_PANE)
                .unlockedBy(getHasName(Items.GLASS_PANE), has(Items.GLASS_PANE))
                .save(pWriter);

        mojinRecipes(pWriter, PrettyGuardianItem.APPLE_MOJITO.get(), Items.APPLE);
        mojinRecipes(pWriter, PrettyGuardianItem.STRAWBERRY_MOJITO.get(), PrettyGuardianItem.STRAWBERRY.get());
        mojinRecipes(pWriter, PrettyGuardianItem.MINT_MOJITO.get(), PrettyGuardianItem.MINT.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, PrettyGuardianItem.RHUM_BOTTLE.get())
                .pattern(" A ")
                .pattern("ABA")
                .pattern(" C ")
                .define('A', Items.SUGAR_CANE)
                .define('B', Items.WATER_BUCKET)
                .define('C', Items.GLASS_BOTTLE)
                .unlockedBy(getHasName(Items.SUGAR_CANE), has(Items.SUGAR_CANE))
                .save(pWriter);

        iceCreamRecipes(pWriter, PrettyGuardianItem.STRAWBERRY_ICE_CREAM.get(), PrettyGuardianItem.STRAWBERRY.get());
        iceCreamRecipes(pWriter, PrettyGuardianItem.VANILLA_ICE_CREAM.get(), PrettyGuardianItem.VANILLA.get());
        iceCreamRecipes(pWriter, PrettyGuardianItem.PISTACHIO_ICE_CREAM.get(), PrettyGuardianItem.PISTACHIO.get());
        iceCreamRecipes(pWriter, PrettyGuardianItem.CHOCOLATE_ICE_CREAM.get(), PrettyGuardianItem.CHOCOLATE.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, PrettyGuardianItem.POKKY_ICE_CREAM.get())
                .pattern("ABC")
                .pattern("DEF")
                .pattern("GGG")
                .define('A', PrettyGuardianItem.WAFFLE.get())
                .define('B', PrettyGuardianItem.CREAM.get())
                .define('C', PrettyGuardianItem.CHOCOLATE_POCKY.get())
                .define('D', PrettyGuardianItem.CHOCOLATE.get())
                .define('E', PrettyGuardianItem.VANILLA_ICE_CREAM.get())
                .define('F', PrettyGuardianItem.STRAWBERRY.get())
                .define('G', Items.SNOWBALL)
                .unlockedBy(getHasName(PrettyGuardianItem.VANILLA_ICE_CREAM.get()), has(PrettyGuardianItem.VANILLA_ICE_CREAM.get()))
                .save(pWriter);


        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, PrettyGuardianItem.WAFFLE.get(), 3)
                .pattern("ABA")
                .pattern("CDC")
                .pattern("EEE")
                .define('A', Items.SUGAR)
                .define('B', PrettyGuardianItem.VANILLA.get())
                .define('C', Items.EGG)
                .define('D', Items.MILK_BUCKET)
                .define('E', Items.WHEAT)
                .unlockedBy(getHasName(Items.WHEAT), has(Items.WHEAT))
                .unlockedBy(getHasName(Items.SUGAR), has(Items.SUGAR))
                .unlockedBy(getHasName(Items.MILK_BUCKET), has(Items.MILK_BUCKET))
                .unlockedBy(getHasName(Items.EGG), has(Items.EGG))
                .save(pWriter);

        doubleSimpleShapeless(pWriter, RecipeCategory.FOOD, PrettyGuardianItem.CHOCOLATE_WAFFLE.get(), 1,
                PrettyGuardianItem.WAFFLE.get(), PrettyGuardianItem.CHOCOLATE.get());

        doubleSimpleShapeless(pWriter, RecipeCategory.FOOD, PrettyGuardianItem.CHOCOLATE_POCKY.get(), 4,
                PrettyGuardianItem.CHOCOLATE.get(), Items.WHEAT);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, PrettyGuardianItem.STRAWBERRY_POCKY.get(), 4)
                .pattern("A")
                .pattern("B")
                .pattern("C")
                .define('A', PrettyGuardianItem.CHOCOLATE.get())
                .define('B', PrettyGuardianItem.STRAWBERRY.get())
                .define('C', Items.WHEAT)
                .unlockedBy(getHasName(PrettyGuardianItem.CHOCOLATE.get()), has(PrettyGuardianItem.CHOCOLATE.get()))
                .unlockedBy(getHasName(PrettyGuardianItem.STRAWBERRY.get()), has(PrettyGuardianItem.STRAWBERRY.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, PrettyGuardianItem.DANGO_CARAMEL.get(), 3)
                .pattern("ABA")
                .pattern("CCC")
                .pattern("DDD")
                .define('A', PrettyGuardianItem.VANILLA.get())
                .define('B', PrettyGuardianItem.CARAMEL.get())
                .define('C', PrettyGuardianItem.AGARAGAR.get())
                .define('D', Items.STICK)
                .unlockedBy(getHasName(PrettyGuardianItem.VANILLA.get()), has(PrettyGuardianItem.VANILLA.get()))
                .unlockedBy(getHasName(PrettyGuardianItem.CARAMEL.get()), has(PrettyGuardianItem.CARAMEL.get()))
                .unlockedBy(getHasName(PrettyGuardianItem.AGARAGAR.get()), has(PrettyGuardianItem.AGARAGAR.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, PrettyGuardianItem.TRICOLOR_DANGO.get(), 3)
                .pattern("ABC")
                .pattern("DDD")
                .pattern("EEE")
                .define('A', PrettyGuardianItem.VANILLA.get())
                .define('B', PrettyGuardianItem.STRAWBERRY.get())
                .define('C', Items.MELON_SLICE)
                .define('D', PrettyGuardianItem.AGARAGAR.get())
                .define('E', Items.STICK)
                .unlockedBy(getHasName(PrettyGuardianItem.VANILLA.get()), has(PrettyGuardianItem.VANILLA.get()))
                .unlockedBy(getHasName(PrettyGuardianItem.STRAWBERRY.get()), has(PrettyGuardianItem.STRAWBERRY.get()))
                .unlockedBy(getHasName(PrettyGuardianItem.AGARAGAR.get()), has(PrettyGuardianItem.AGARAGAR.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, PrettyGuardianItem.POPSICLE.get(), 1)
                .pattern("  A")
                .pattern(" B ")
                .pattern("C  ")
                .define('A', Items.SNOWBALL)
                .define('B', Items.SUGAR)
                .define('C', Items.STICK)
                .unlockedBy(getHasName(Items.SNOWBALL), has(Items.SNOWBALL))
                .unlockedBy(getHasName(Items.SUGAR), has(Items.SUGAR))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, PrettyGuardianItem.CREAM_POPSICLE.get(), 1)
                .pattern("  A")
                .pattern(" B ")
                .pattern("C  ")
                .define('A', PrettyGuardianItem.VANILLA.get())
                .define('B', PrettyGuardianItem.CREAM.get())
                .define('C', Items.STICK)
                .unlockedBy(getHasName(Items.SNOWBALL), has(Items.SNOWBALL))
                .unlockedBy(getHasName(PrettyGuardianItem.CREAM.get()), has(PrettyGuardianItem.CREAM.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, PrettyGuardianItem.CHOCOLATE_POPSICLE.get(), 1)
                .pattern("  A")
                .pattern(" B ")
                .pattern("C  ")
                .define('A', PrettyGuardianItem.CHOCOLATE.get())
                .define('B', PrettyGuardianItem.CREAM.get())
                .define('C', Items.STICK)
                .unlockedBy(getHasName(Items.SNOWBALL), has(Items.SNOWBALL))
                .unlockedBy(getHasName(PrettyGuardianItem.CHOCOLATE.get()), has(PrettyGuardianItem.CHOCOLATE.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, PrettyGuardianItem.STRAWBERRY_POPSICLE.get(), 1)
                .pattern("  A")
                .pattern(" B ")
                .pattern("C  ")
                .define('A', PrettyGuardianItem.STRAWBERRY.get())
                .define('B', PrettyGuardianItem.CREAM.get())
                .define('C', Items.STICK)
                .unlockedBy(getHasName(Items.SNOWBALL), has(Items.SNOWBALL))
                .unlockedBy(getHasName(PrettyGuardianItem.STRAWBERRY.get()), has(PrettyGuardianItem.STRAWBERRY.get()))
                .save(pWriter);

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

    protected static void simpleShapeless(Consumer<FinishedRecipe> finishedRecipeConsumer, RecipeCategory category, ItemLike result, int resultCount, ItemLike ingredient) {
        simpleShapeless(finishedRecipeConsumer, category, result, resultCount, ingredient, 1);
    }
    protected static void simpleShapeless(Consumer<FinishedRecipe> finishedRecipeConsumer, RecipeCategory category, ItemLike result, int resultCount, ItemLike ingredient, int ingredientCount) {
        ShapelessRecipeBuilder.shapeless(category, result, resultCount)
                .requires(ingredient, ingredientCount)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(finishedRecipeConsumer);
    }

    protected static void doubleSimpleShapeless(Consumer<FinishedRecipe> finishedRecipeConsumer, RecipeCategory category, ItemLike result, int resultCount, ItemLike ingredient1, ItemLike ingredient2) {
        doubleSimpleShapeless(finishedRecipeConsumer, category, result, resultCount, ingredient1, 1, ingredient2, 1);
    }

    protected static void doubleSimpleShapeless(Consumer<FinishedRecipe> finishedRecipeConsumer, RecipeCategory category, ItemLike result, int resultCount, ItemLike ingredient1, int ingredient1Count, ItemLike ingredient2, int ingredient2Count) {
        ShapelessRecipeBuilder.shapeless(category, result, resultCount)
                .requires(ingredient1, ingredient1Count)
                .requires(ingredient2, ingredient2Count)
                .unlockedBy(getHasName(ingredient1), has(ingredient1))
                .unlockedBy(getHasName(ingredient2), has(ingredient2))
                .save(finishedRecipeConsumer);
    }

    protected static void mojinRecipes(Consumer<FinishedRecipe> finishedRecipeConsumer, ItemLike mojin, ItemLike flavor) {
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, mojin)
                .pattern("ABC")
                .pattern("DED")
                .pattern("FFF")
                .define('A', PrettyGuardianItem.MINT.get())
                .define('B', PrettyGuardianItem.LEMON.get())
                .define('C', flavor)
                .define('D', Items.SUGAR)
                .define('E', Items.WATER_BUCKET)
                .define('F', PrettyGuardianItem.JUICE_GLASS.get())
                .unlockedBy(getHasName(PrettyGuardianItem.JUICE_GLASS.get()), has(PrettyGuardianItem.JUICE_GLASS.get()))
                .save(finishedRecipeConsumer);
    }

    protected static void iceCreamRecipes(Consumer<FinishedRecipe> finishedRecipeConsumer, ItemLike iceCream, ItemLike flavor) {
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, iceCream, 3)
                .pattern("ABC")
                .pattern("DEF")
                .define('A', Items.MILK_BUCKET)
                .define('B', Items.SNOWBALL)
                .define('C', Items.EGG)
                .define('D', Items.SUGAR)
                .define('E', PrettyGuardianItem.ICE_CREAM_CUP.get())
                .define('F', flavor)
                .unlockedBy(getHasName(PrettyGuardianItem.ICE_CREAM_CUP.get()), has(PrettyGuardianItem.ICE_CREAM_CUP.get()))
                .save(finishedRecipeConsumer);
    }


    protected static void BlockIngotRecipes(Consumer<FinishedRecipe> finishedRecipeConsumer, ItemLike block, ItemLike ingot) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, block)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ingot)
                .unlockedBy(getHasName(ingot), has(ingot))
                .save(finishedRecipeConsumer, new ResourceLocation(PrettyGuardian.MOD_ID, block.asItem() + "_from_" + ingot.asItem() + "_item"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ingot, 9)
                .requires(block)
                .unlockedBy(getHasName(block), has(block))
                .save(finishedRecipeConsumer, new ResourceLocation(PrettyGuardian.MOD_ID,ingot.asItem() + "_item_from_" + block.asItem()));
    }

    protected static void armorRecipes(Consumer<FinishedRecipe> finishedRecipeConsumer, Item helmet, Item chestplate, Item leggings, Item boots, ItemLike material) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, helmet)
                .pattern("AAA")
                .pattern("A A")
                .define('A', material)
                .unlockedBy(getHasName(material), has(material))
                .save(finishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, chestplate)
                .pattern("A A")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', material)
                .unlockedBy(getHasName(material), has(material))
                .save(finishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, leggings)
                .pattern("AAA")
                .pattern("A A")
                .pattern("A A")
                .define('A', material)
                .unlockedBy(getHasName(material), has(material))
                .save(finishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, boots)
                .pattern("A A")
                .pattern("A A")
                .define('A', material)
                .unlockedBy(getHasName(material), has(material))
                .save(finishedRecipeConsumer);
    }

    protected static void toolRecipes(Consumer<FinishedRecipe> finishedRecipeConsumer, Item sword, Item pickaxe, Item axe, Item shovel, Item hoe, ItemLike material) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, sword)
                .pattern(" A ")
                .pattern(" A ")
                .pattern(" B ")
                .define('A', material)
                .define('B', Items.STICK)
                .unlockedBy(getHasName(material), has(material))
                .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                .save(finishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, pickaxe)
                .pattern("AAA")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', material)
                .define('B', Items.STICK)
                .unlockedBy(getHasName(material), has(material))
                .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                .save(finishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, axe)
                .pattern("AA ")
                .pattern("AB ")
                .pattern(" B ")
                .define('A', material)
                .define('B', Items.STICK)
                .unlockedBy(getHasName(material), has(material))
                .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                .save(finishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, shovel)
                .pattern(" A ")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', material)
                .define('B', Items.STICK)
                .unlockedBy(getHasName(material), has(material))
                .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                .save(finishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, hoe)
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
