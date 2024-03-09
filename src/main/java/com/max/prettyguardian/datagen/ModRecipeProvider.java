package com.max.prettyguardian.datagen;

import com.max.prettyguardian.PrettyGuardian;
import com.max.prettyguardian.blocks.PrettyGuardianBlock;
import com.max.prettyguardian.item.PrettyGuardianItem;
import com.max.prettyguardian.util.ModTags;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {

    public ModRecipeProvider(PackOutput packOutput) {
        super(packOutput);
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

        simpleBubbleTea(pWriter, PrettyGuardianItem.BUBBLETEA_MELON.get(), Items.MELON_SLICE);
        simpleBubbleTea(pWriter, PrettyGuardianItem.BUBBLETEA_CARAMEL.get(), PrettyGuardianItem.CARAMEL.get());
        simpleBubbleTea(pWriter, PrettyGuardianItem.BUBBLETEA_STRAWBERRY.get(), PrettyGuardianItem.STRAWBERRY.get());

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

        waffleIceCreamRecipes(pWriter, PrettyGuardianItem.ICE_CREAM_WAFFLE_STRAWBERRY.get(), PrettyGuardianItem.STRAWBERRY.get());
        waffleIceCreamRecipes(pWriter, PrettyGuardianItem.ICE_CREAM_WAFFLE_VANILLA.get(), PrettyGuardianItem.VANILLA.get());
        waffleIceCreamRecipes(pWriter, PrettyGuardianItem.ICE_CREAM_WAFFLE_PISTACHIO.get(), PrettyGuardianItem.PISTACHIO.get());
        waffleIceCreamRecipes(pWriter, PrettyGuardianItem.ICE_CREAM_WAFFLE_CHOCOLATE.get(), PrettyGuardianItem.CHOCOLATE.get());

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
                .unlockedBy(getHasName(PrettyGuardianItem.CHOCOLATE_POCKY.get()), has(PrettyGuardianItem.CHOCOLATE_POCKY.get()))
                .unlockedBy(getHasName(PrettyGuardianItem.WAFFLE.get()), has(PrettyGuardianItem.WAFFLE.get()))
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

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, PrettyGuardianItem.FISH_WAFFLE.get(), 3)
                .pattern("ABA")
                .pattern("CDC")
                .pattern("EEE")
                .define('A', Items.SUGAR)
                .define('B', Items.SWEET_BERRIES)
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

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, PrettyGuardianItem.TAKOYAKI.get(), 3)
                .pattern(" A ")
                .pattern("BCB")
                .pattern("DED")
                .define('A', Items.KELP)
                .define('B', Items.EGG)
                .define('C', PrettyGuardianItem.SQUID_COOKED.get())
                .define('D', Items.WHEAT)
                .define('E', PrettyGuardianItem.AGARAGAR.get())
                .unlockedBy(getHasName(Items.KELP), has(Items.KELP))
                .unlockedBy(getHasName(Items.EGG), has(Items.EGG))
                .unlockedBy(getHasName(PrettyGuardianItem.SQUID_COOKED.get()), has(PrettyGuardianItem.SQUID_COOKED.get()))
                .unlockedBy(getHasName(Items.WHEAT), has(Items.WHEAT))
                .unlockedBy(getHasName(PrettyGuardianItem.AGARAGAR.get()), has(PrettyGuardianItem.AGARAGAR.get()))
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

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, PrettyGuardianBlock.RHUM_CAKE.get())
                .pattern("ABA")
                .pattern("CDC")
                .pattern("EEE")
                .define('A', PrettyGuardianItem.CARAMEL.get())
                .define('B', PrettyGuardianItem.RHUM_BOTTLE.get())
                .define('C', Items.EGG)
                .define('D', Items.MILK_BUCKET)
                .define('E', Items.WHEAT)
                .unlockedBy(getHasName(PrettyGuardianItem.CARAMEL.get()), has(PrettyGuardianItem.CARAMEL.get()))
                .unlockedBy(getHasName(PrettyGuardianItem.RHUM_BOTTLE.get()), has(PrettyGuardianItem.RHUM_BOTTLE.get()))
                .save(pWriter);

        simpleCake(pWriter, PrettyGuardianBlock.STRAWBERRY_CAKE.get(), PrettyGuardianItem.STRAWBERRY.get(), PrettyGuardianItem.CREAM.get());
        simpleCake(pWriter, PrettyGuardianBlock.VELVET_CAKE.get(), Items.RED_DYE, PrettyGuardianItem.CHOCOLATE.get());
        simpleCake(pWriter, PrettyGuardianBlock.CREAM_CAKE.get(), PrettyGuardianItem.VANILLA.get(), PrettyGuardianItem.CREAM.get());
        simpleCake(pWriter, PrettyGuardianBlock.CHOCOLATE_CAKE.get(), PrettyGuardianItem.CHOCOLATE.get(), PrettyGuardianItem.CREAM.get());
        simpleCake(pWriter, PrettyGuardianBlock.BERRY_STRAWBERRY_CAKE.get(), PrettyGuardianItem.STRAWBERRY.get(), Items.SWEET_BERRIES);
        simpleCake(pWriter, PrettyGuardianBlock.CREAM_STRAWBERRY_CAKE.get(), PrettyGuardianItem.CREAM.get(), PrettyGuardianItem.STRAWBERRY.get());
        simpleCake(pWriter, PrettyGuardianBlock.STRAWBERRY_CHOCO_CAKE.get(), PrettyGuardianItem.STRAWBERRY.get(), PrettyGuardianItem.CHOCOLATE.get());

        simpleThreeCake(pWriter, PrettyGuardianBlock.THREE_VELVET_CAKE.get(), PrettyGuardianBlock.VELVET_CAKE.get());
        simpleThreeCake(pWriter, PrettyGuardianBlock.THREE_STRAWBERRY_CAKE.get(), PrettyGuardianBlock.CREAM_STRAWBERRY_CAKE.get());
        simpleThreeCake(pWriter, PrettyGuardianBlock.THREE_CHOCO_CAKE.get(), PrettyGuardianBlock.CHOCOLATE_CAKE.get());
        simpleThreeCake(pWriter, PrettyGuardianBlock.THREE_STRAWBERRY_CHOCO_CAKE.get(), PrettyGuardianBlock.STRAWBERRY_CHOCO_CAKE.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, PrettyGuardianItem.MARSHMELLOW_STRAWBERRY_BURGER.get(), 2)
                .pattern("ABA")
                .pattern(" C ")
                .define('A', PrettyGuardianItem.STRAWBERRY.get())
                .define('B', PrettyGuardianItem.CREAM.get())
                .define('C', Items.BREAD)
                .unlockedBy(getHasName(PrettyGuardianItem.STRAWBERRY.get()), has(PrettyGuardianItem.STRAWBERRY.get()))
                .unlockedBy(getHasName(PrettyGuardianItem.CREAM.get()), has(PrettyGuardianItem.CREAM.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, PrettyGuardianItem.SMORE.get(), 4)
                .pattern("AA")
                .pattern("BC")
                .pattern("AA")
                .define('A', Items.WHEAT)
                .define('B', PrettyGuardianItem.CHOCOLATE.get())
                .define('C', PrettyGuardianItem.MARSHMALLOW.get())
                .unlockedBy(getHasName(PrettyGuardianItem.CHOCOLATE.get()), has(PrettyGuardianItem.CHOCOLATE.get()))
                .unlockedBy(getHasName(PrettyGuardianItem.MARSHMALLOW.get()), has(PrettyGuardianItem.MARSHMALLOW.get()))
                .save(pWriter);

        simplePudding(pWriter, PrettyGuardianItem.CARAMEL_PUDDING.get(), PrettyGuardianItem.CARAMEL.get());
        simplePudding(pWriter, PrettyGuardianItem.CHOCOLATE_PUDDING.get(), PrettyGuardianItem.CHOCOLATE.get());
        simplePudding(pWriter, PrettyGuardianItem.STRAWBERRY_PUDDING.get(), PrettyGuardianItem.STRAWBERRY.get());
        simplePudding(pWriter, PrettyGuardianItem.PISTACHIO_PUDDING.get(), PrettyGuardianItem.PISTACHIO.get());

        simpleCroissant(pWriter, PrettyGuardianItem.CHOCOLATE_CROISSANT.get(), PrettyGuardianItem.CHOCOLATE.get());
        simpleCroissant(pWriter, PrettyGuardianItem.STRAWBERRY_CROISSANT.get(), PrettyGuardianItem.STRAWBERRY.get());
        simpleCroissant(pWriter, PrettyGuardianItem.VANILLA_CROISSANT.get(), PrettyGuardianItem.VANILLA.get());
        simpleCroissant(pWriter, PrettyGuardianItem.PISTACHIO_CROISSANT.get(), PrettyGuardianItem.PISTACHIO.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, PrettyGuardianItem.CANDY_APPLE.get(), 1)
                .pattern("  A")
                .pattern(" B ")
                .pattern("C  ")
                .define('A', PrettyGuardianItem.CARAMEL.get())
                .define('B', Items.APPLE)
                .define('C', Items.STICK)
                .unlockedBy(getHasName(PrettyGuardianItem.CARAMEL.get()), has(PrettyGuardianItem.CARAMEL.get()))
                .unlockedBy(getHasName(Items.APPLE), has(Items.APPLE))
                .save(pWriter);

        simplePie(pWriter, PrettyGuardianBlock.STRAWBERRY_PIE.get(), PrettyGuardianItem.STRAWBERRY.get());
        simplePie(pWriter, PrettyGuardianBlock.CHOCOLATE_PIE.get(), PrettyGuardianItem.CHOCOLATE.get());
        simplePie(pWriter, PrettyGuardianBlock.APPLE_PIE.get(), Items.APPLE);
        simplePie(pWriter, PrettyGuardianBlock.LEMON_PIE.get(), PrettyGuardianItem.LEMON.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, PrettyGuardianBlock.MAGIC_PIE.get(), 1)
                .pattern("ABA")
                .pattern("CDC")
                .pattern("FFF")
                .define('A', Items.SUGAR)
                .define('B', PrettyGuardianItem.FAIRY_DUST.get())
                .define('C', Items.SWEET_BERRIES)
                .define('D', PrettyGuardianItem.STRAWBERRY.get())
                .define('F', Items.WHEAT)
                .unlockedBy(getHasName(PrettyGuardianItem.FAIRY_DUST.get()), has(PrettyGuardianItem.FAIRY_DUST.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PrettyGuardianItem.BUTTERFLY_NET.get(), 1)
                .pattern(" AA")
                .pattern("BAA")
                .define('A', Items.STRING)
                .define('B', Items.STICK)
                .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                .unlockedBy(getHasName(Items.STRING), has(Items.STRING))
                .save(pWriter);


        simpleScreen(pWriter, PrettyGuardianBlock.SCREEN_JAPANESE_BIRCH.get(), Blocks.BIRCH_PLANKS);
        simpleScreen(pWriter, PrettyGuardianBlock.SCREEN_JAPANESE_CHERRY_LOG.get(), Blocks.CHERRY_LOG);
        simpleScreen(pWriter, PrettyGuardianBlock.SCREEN_JAPANESE_CHERRY_PLANK.get(), Blocks.CHERRY_PLANKS);

        simpleLamp(pWriter, PrettyGuardianBlock.LAMP_JAPANESE_BIRCH.get(), Blocks.BIRCH_LOG);
        simpleLamp(pWriter, PrettyGuardianBlock.LAMP_JAPANESE_CHERRY.get(), Blocks.CHERRY_LOG);
        simpleLamp(pWriter, PrettyGuardianBlock.LAMP_JAPANESE_JUNGLE.get(), Blocks.JUNGLE_LOG);
        simpleLamp(pWriter, PrettyGuardianBlock.LAMP_JAPANESE_OAK.get(), Blocks.OAK_LOG);
        simpleLamp(pWriter, PrettyGuardianBlock.LAMP_JAPANESE_SPRUCE.get(), Blocks.SPRUCE_LOG);
        simpleLamp(pWriter, PrettyGuardianBlock.LAMP_JAPANESE_ACACIA.get(), Blocks.ACACIA_LOG);
        simpleLamp(pWriter, PrettyGuardianBlock.LAMP_JAPANESE_DARK_OAK.get(), Blocks.DARK_OAK_LOG);
        simpleLamp(pWriter, PrettyGuardianBlock.LAMP_JAPANESE_MANGROVE.get(), Blocks.MANGROVE_LOG);

        simpleLantern(pWriter, PrettyGuardianBlock.LANTERN_JAPANESE.get(), Items.PAPER);
        simpleLantern(pWriter, PrettyGuardianBlock.LANTERN_JAPANESE_SAKURA.get(), Items.PINK_DYE);
        simpleLantern(pWriter, PrettyGuardianBlock.LANTERN_JAPANESE_FESTIVAL.get(), Items.RED_DYE);
        doubleSimpleShapelessOneUnlockedBy(pWriter, RecipeCategory.MISC, PrettyGuardianBlock.LANTERN_JAPANESE_BIG.get(), 1, PrettyGuardianBlock.LANTERN_JAPANESE.get(), PrettyGuardianBlock.LANTERN_JAPANESE.get());
        doubleSimpleShapelessOneUnlockedBy(pWriter, RecipeCategory.MISC, PrettyGuardianBlock.LANTERN_JAPANESE_SAKURA_BIG.get(), 1, PrettyGuardianBlock.LANTERN_JAPANESE_SAKURA.get(), PrettyGuardianBlock.LANTERN_JAPANESE_SAKURA.get());
        doubleSimpleShapelessOneUnlockedBy(pWriter, RecipeCategory.MISC, PrettyGuardianBlock.LANTERN_JAPANESE_FESTIVAL_BIG.get(), 1, PrettyGuardianBlock.LANTERN_JAPANESE_FESTIVAL.get(), PrettyGuardianBlock.LANTERN_JAPANESE_FESTIVAL.get());
        simpleThreeCake(pWriter, PrettyGuardianBlock.LANTERN_HUGE_JAPANESE.get(), PrettyGuardianBlock.LANTERN_JAPANESE.get());
        simpleThreeCake(pWriter, PrettyGuardianBlock.LANTERN_SAKURA_HUGE_JAPANESE.get(), PrettyGuardianBlock.LANTERN_JAPANESE_SAKURA.get());
        simpleThreeCake(pWriter, PrettyGuardianBlock.LANTERN_FESTIVAL_HUGE_JAPANESE.get(), PrettyGuardianBlock.LANTERN_JAPANESE_FESTIVAL.get());

        simpleDoor(pWriter, PrettyGuardianBlock.DOOR_SHOJI_BIRCH.get(), Blocks.BIRCH_PLANKS);
        simpleDoor2(pWriter, PrettyGuardianBlock.DOOR_SHOJI_BIRCH_SMALL.get(), Blocks.BIRCH_PLANKS);
        simpleDoor(pWriter, PrettyGuardianBlock.DOOR_SHOJI_BLOSSOM.get(), Blocks.CHERRY_PLANKS);
        simpleDoor2(pWriter, PrettyGuardianBlock.DOOR_SHOJI_BLOSSOM_SMALL.get(), Blocks.CHERRY_PLANKS);
        simpleDoor(pWriter, PrettyGuardianBlock.DOOR_SHOJI_CHERRY.get(), Blocks.CHERRY_LOG);
        simpleDoor2(pWriter, PrettyGuardianBlock.DOOR_SHOJI_CHERRY_SMALL.get(), Blocks.CHERRY_LOG);

        simpleShoji(pWriter, PrettyGuardianBlock.SHOJI_BIRCH.get(), Blocks.BIRCH_PLANKS);
        simpleShoji2(pWriter, PrettyGuardianBlock.SHOJI_BIRCH_SMALL.get(), Blocks.BIRCH_PLANKS);
        simpleShojiBase(pWriter, PrettyGuardianBlock.SHOJI_BIRCH_BOTTOM.get(), Blocks.BIRCH_PLANKS);
        simpleShojiBase2(pWriter, PrettyGuardianBlock.SHOJI_BIRCH_SMALL_BOTTOM.get(), Blocks.BIRCH_PLANKS);
        simpleShoji(pWriter, PrettyGuardianBlock.SHOJI_BLOSSOM.get(), Blocks.CHERRY_PLANKS);
        simpleShoji2(pWriter, PrettyGuardianBlock.SHOJI_BLOSSOM_SMALL.get(), Blocks.CHERRY_PLANKS);
        simpleShojiBase(pWriter, PrettyGuardianBlock.SHOJI_BLOSSOM_BOTTOM.get(), Blocks.CHERRY_PLANKS);
        simpleShojiBase2(pWriter, PrettyGuardianBlock.SHOJI_BLOSSOM_SMALL_BOTTOM.get(), Blocks.CHERRY_PLANKS);
        simpleShoji(pWriter, PrettyGuardianBlock.SHOJI_CHERRY.get(), Blocks.CHERRY_LOG);
        simpleShoji2(pWriter, PrettyGuardianBlock.SHOJI_CHERRY_SMALL.get(), Blocks.CHERRY_LOG);
        simpleShojiBase(pWriter, PrettyGuardianBlock.SHOJI_CHERRY_BOTTOM.get(), Blocks.CHERRY_LOG);
        simpleShojiBase2(pWriter, PrettyGuardianBlock.SHOJI_CHERRY_SMALL_BOTTOM.get(), Blocks.CHERRY_LOG);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PrettyGuardianBlock.PICNIC_BASKET.get(), 1)
                .pattern(" A ")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', Blocks.BAMBOO)
                .define('B', Items.PINK_WOOL)
                .unlockedBy(getHasName(Blocks.OAK_PLANKS), has(Blocks.OAK_PLANKS))
                .unlockedBy(getHasName(Items.CHEST), has(Items.CHEST))
                .save(pWriter);

        simpleTable(pWriter, PrettyGuardianBlock.TABLE_JAPANESE_BIRCH.get(), Blocks.STRIPPED_BIRCH_LOG, Blocks.BIRCH_SLAB);
        simpleTable(pWriter, PrettyGuardianBlock.TABLE_JAPANESE_CHERRY_PLANK.get(), Blocks.STRIPPED_CHERRY_LOG, Blocks.CHERRY_SLAB);
        simpleTable(pWriter, PrettyGuardianBlock.TABLE_JAPANESE_CHERRY_LOG.get(), Blocks.CHERRY_LOG, Blocks.CHERRY_WOOD);
        simpleTable(pWriter, PrettyGuardianBlock.TABLE_JAPANESE_OAK.get(), Blocks.STRIPPED_OAK_LOG, Blocks.OAK_SLAB);
        simpleTable(pWriter, PrettyGuardianBlock.TABLE_JAPANESE_SPRUCE.get(), Blocks.STRIPPED_SPRUCE_LOG, Blocks.SPRUCE_SLAB);

        simpleChair(pWriter, PrettyGuardianBlock.CHAIR_JAPANESE_BIRCH.get(), Blocks.STRIPPED_BIRCH_LOG, Blocks.BIRCH_SLAB);
        simpleChair(pWriter, PrettyGuardianBlock.CHAIR_JAPANESE_CHERRY_PLANK.get(), Blocks.STRIPPED_CHERRY_LOG, Blocks.CHERRY_SLAB);
        simpleChair(pWriter, PrettyGuardianBlock.CHAIR_JAPANESE_CHERRY_LOG.get(), Blocks.CHERRY_LOG, Blocks.CHERRY_WOOD);
        simpleChair(pWriter, PrettyGuardianBlock.CHAIR_JAPANESE_OAK.get(), Blocks.STRIPPED_OAK_LOG, Blocks.OAK_SLAB);
        simpleChair(pWriter, PrettyGuardianBlock.CHAIR_JAPANESE_SPRUCE.get(), Blocks.STRIPPED_SPRUCE_LOG, Blocks.SPRUCE_SLAB);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, PrettyGuardianBlock.BONZAI_CHERRY.get(), 1)
                .pattern(" A ")
                .pattern("ABA")
                .pattern(" C ")
                .define('A', Blocks.CHERRY_LEAVES)
                .define('B', Blocks.CHERRY_SAPLING)
                .define('C', Blocks.FLOWER_POT)
                .unlockedBy(getHasName(Blocks.CHERRY_LEAVES), has(Blocks.CHERRY_LEAVES))
                .unlockedBy(getHasName(Blocks.CHERRY_SAPLING), has(Blocks.CHERRY_SAPLING))
                .unlockedBy(getHasName(Blocks.FLOWER_POT), has(Blocks.FLOWER_POT))
                .save(pWriter);

        simpleFruitSapling(pWriter, PrettyGuardianBlock.BOBA_SAPLING.get(), PrettyGuardianItem.BOBA.get());
        simpleFruitSapling(pWriter, PrettyGuardianBlock.LEMON_SAPLING.get(), PrettyGuardianItem.LEMON.get());
        simpleFruitSapling(pWriter, PrettyGuardianBlock.PISTACHIO_SAPLING.get(), PrettyGuardianItem.PISTACHIO.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PrettyGuardianBlock.RANDOM_PLUSH_BOX.get(), 1)
                .pattern("ABA")
                .pattern("CDC")
                .pattern("ABA")
                .define('A', Items.STICK)
                .define('B', Items.PINK_WOOL)
                .define('C', Items.WHITE_WOOL)
                .define('D', Items.STRING)
                .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                .unlockedBy(getHasName(Items.PINK_WOOL), has(Items.PINK_WOOL))
                .unlockedBy(getHasName(Items.WHITE_WOOL), has(Items.WHITE_WOOL))
                .unlockedBy(getHasName(Items.STRING), has(Items.STRING))
                .save(pWriter);

        SimpleCookingRecipeBuilder.generic(Ingredient.of(PrettyGuardianItem.RAW_BOBA.get()), RecipeCategory.FOOD, PrettyGuardianItem.BOBA.get(), 0.35F, 200, RecipeSerializer.SMELTING_RECIPE)
                .group("roasted_marshmallow_stick")
                .unlockedBy(getHasName(PrettyGuardianItem.RAW_BOBA.get()), has(PrettyGuardianItem.RAW_BOBA.get()))
                .save(pWriter);

        doubleSimpleShapeless(pWriter, RecipeCategory.FOOD, PrettyGuardianItem.SQUID_STICK.get(), 1, PrettyGuardianItem.BOBA.get(), Items.STICK);

        SimpleCookingRecipeBuilder.generic(Ingredient.of(PrettyGuardianItem.SQUID_STICK.get()), RecipeCategory.FOOD, PrettyGuardianItem.SQUID_COOKED.get(), 0.35F, 200, RecipeSerializer.SMELTING_RECIPE)
                .group("roasted_marshmallow_stick")
                .unlockedBy(getHasName(PrettyGuardianItem.SQUID_STICK.get()), has(PrettyGuardianItem.SQUID_STICK.get()))
                .unlockedBy(getHasName(PrettyGuardianItem.RAW_SQUID.get()), has(PrettyGuardianItem.RAW_SQUID.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PrettyGuardianItem.GIFT_BOX.get(), 1)
                .pattern("ABA")
                .pattern("ACA")
                .pattern("AAA")
                .define('A', Items.PAPER)
                .define('B', Items.STRING)
                .define('C', Items.PINK_DYE)
                .unlockedBy(getHasName(Items.PAPER), has(Items.PAPER))
                .unlockedBy(getHasName(Items.STRING), has(Items.STRING))
                .unlockedBy(getHasName(Items.PINK_DYE), has(Items.PINK_DYE))
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

    protected static void simpleTable(Consumer<FinishedRecipe> finishedRecipeConsumer, ItemLike table, ItemLike log, ItemLike slab) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, table, 1)
                .pattern("AAA")
                .pattern("B B")
                .pattern("B B")
                .define('A', slab)
                .define('B', log)
                .unlockedBy(getHasName(log), has(log))
                .unlockedBy(getHasName(slab), has(slab))
                .save(finishedRecipeConsumer);
    }

    protected static void simpleChair(Consumer<FinishedRecipe> finishedRecipeConsumer, ItemLike chair, ItemLike log, ItemLike slab) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, chair, 1)
                .pattern("A  ")
                .pattern("ABA")
                .pattern("C C")
                .define('A', slab)
                .define('B', ItemTags.WOOL)
                .define('C', log)
                .unlockedBy(getHasName(log), has(log))
                .unlockedBy(getHasName(slab), has(slab))
                .save(finishedRecipeConsumer);
    }

    protected static void simpleFruitSapling(Consumer<FinishedRecipe> finishedRecipeConsumer, ItemLike sapling, ItemLike fruit) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, sapling, 1)
                .requires(fruit)
                .requires(fruit)
                .requires(ItemTags.SAPLINGS)
                .unlockedBy(getHasName(fruit), has(fruit))
                .save(finishedRecipeConsumer);
    }

    protected static void simpleShoji(Consumer<FinishedRecipe> finishedRecipeConsumer, ItemLike shoji, ItemLike wood) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, shoji, 3)
                .pattern("ABA")
                .pattern("BAB")
                .pattern("ABA")
                .define('A', wood)
                .define('B', Items.PAPER)
                .unlockedBy(getHasName(wood), has(wood))
                .unlockedBy(getHasName(Items.PAPER), has(Items.PAPER))
                .save(finishedRecipeConsumer);
    }
    protected static void simpleShoji2(Consumer<FinishedRecipe> finishedRecipeConsumer, ItemLike shoji, ItemLike wood) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, shoji, 3)
                .pattern("BAB")
                .pattern("ABA")
                .pattern("BAB")
                .define('A', wood)
                .define('B', Items.PAPER)
                .unlockedBy(getHasName(wood), has(wood))
                .unlockedBy(getHasName(Items.PAPER), has(Items.PAPER))
                .save(finishedRecipeConsumer);
    }

    protected static void simpleShojiBase(Consumer<FinishedRecipe> finishedRecipeConsumer, ItemLike shoji, ItemLike wood) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, shoji, 3)
                .pattern("ABA")
                .pattern("BAB")
                .pattern("AAA")
                .define('A', wood)
                .define('B', Items.PAPER)
                .unlockedBy(getHasName(wood), has(wood))
                .unlockedBy(getHasName(Items.PAPER), has(Items.PAPER))
                .save(finishedRecipeConsumer);
    }
    protected static void simpleShojiBase2(Consumer<FinishedRecipe> finishedRecipeConsumer, ItemLike shoji, ItemLike wood) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, shoji, 3)
                .pattern("BAB")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', wood)
                .define('B', Items.PAPER)
                .unlockedBy(getHasName(wood), has(wood))
                .unlockedBy(getHasName(Items.PAPER), has(Items.PAPER))
                .save(finishedRecipeConsumer);
    }

    protected static void simpleDoor(Consumer<FinishedRecipe> finishedRecipeConsumer, ItemLike door, ItemLike wood) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, door, 3)
                .pattern("BA")
                .pattern("AB")
                .pattern("BA")
                .define('A', Items.PAPER)
                .define('B', wood)
                .unlockedBy(getHasName(wood), has(wood))
                .save(finishedRecipeConsumer);
    }

    protected static void simpleDoor2(Consumer<FinishedRecipe> finishedRecipeConsumer, ItemLike door, ItemLike wood) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, door, 3)
                .pattern("AB")
                .pattern("BA")
                .pattern("AB")
                .define('A', Items.PAPER)
                .define('B', wood)
                .unlockedBy(getHasName(wood), has(wood))
                .save(finishedRecipeConsumer);
    }

    protected static void simpleLantern(Consumer<FinishedRecipe> finishedRecipeConsumer, ItemLike lantern, ItemLike ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, lantern, 1)
                .pattern("ABA")
                .pattern("ACA")
                .pattern("ABA")
                .define('A', Items.PAPER)
                .define('B', ingredient)
                .define('C', Items.TORCH)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(finishedRecipeConsumer);
    }

    protected static void simpleLamp(Consumer<FinishedRecipe> finishedRecipeConsumer, ItemLike lamp, ItemLike wood) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, lamp, 1)
                .pattern("A A")
                .pattern("BCB")
                .pattern("D D")
                .define('A', Items.PAPER)
                .define('B', Items.STICK)
                .define('C', Items.TORCH)
                .define('D', wood)
                .unlockedBy(getHasName(wood), has(wood))
                .save(finishedRecipeConsumer);
    }

    protected static void simpleScreen(Consumer<FinishedRecipe> finishedRecipeConsumer, ItemLike screen, ItemLike wood) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, screen, 1)
                .pattern("ABA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', wood)
                .define('B', Items.PAPER)
                .unlockedBy(getHasName(wood), has(wood))
                .unlockedBy(getHasName(Items.PAPER), has(Items.PAPER))
                .save(finishedRecipeConsumer);
    }

    protected static void simplePie(Consumer<FinishedRecipe> finishedRecipeConsumer, ItemLike result, ItemLike ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, result)
                .pattern("ABC")
                .pattern("DDD")
                .define('A', Items.SUGAR)
                .define('B', ingredient)
                .define('C', Items.EGG)
                .define('D', Items.WHEAT)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(finishedRecipeConsumer);
    }

    protected static void simpleCroissant(Consumer<FinishedRecipe> finishedRecipeConsumer, ItemLike result, ItemLike ingredient1) {
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, result, 3)
                .pattern(" AB")
                .pattern("CDE")
                .pattern("CC ")
                .define('A', Items.SUGAR)
                .define('B', ingredient1)
                .define('C', Items.WHEAT)
                .define('D', PrettyGuardianItem.CREAM.get())
                .define('E', Items.EGG)
                .unlockedBy(getHasName(ingredient1), has(ingredient1))
                .save(finishedRecipeConsumer);
    }

    protected static void simplePudding(Consumer<FinishedRecipe> finishedRecipeConsumer, ItemLike result, ItemLike ingredient1) {
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, result)
                .pattern("ABA")
                .pattern("CDC")
                .pattern("ABA")
                .define('A', PrettyGuardianItem.AGARAGAR.get())
                .define('B', Items.SUGAR)
                .define('C', ingredient1)
                .define('D', Items.WATER_BUCKET)
                .unlockedBy(getHasName(ingredient1), has(ingredient1))
                .save(finishedRecipeConsumer);
    }

    protected static void simpleCake(Consumer<FinishedRecipe> finishedRecipeConsumer, ItemLike result, ItemLike ingredient1, ItemLike ingredient2) {
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, result)
                .pattern("ABA")
                .pattern("CDC")
                .pattern("EEE")
                .define('A', ingredient1)
                .define('B', ingredient2)
                .define('C', Items.SUGAR)
                .define('D', Items.MILK_BUCKET)
                .define('E', Items.WHEAT)
                .unlockedBy(getHasName(ingredient1), has(ingredient1))
                .unlockedBy(getHasName(ingredient2), has(ingredient2))
                .save(finishedRecipeConsumer);
    }

    protected static void simpleThreeCake(Consumer<FinishedRecipe> finishedRecipeConsumer, ItemLike result, ItemLike cake) {
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, result)
                .pattern("A")
                .pattern("A")
                .pattern("A")
                .define('A', cake)
                .unlockedBy(getHasName(cake), has(cake))
                .save(finishedRecipeConsumer);
    }

    protected static void simpleShapeless(Consumer<FinishedRecipe> finishedRecipeConsumer, RecipeCategory category, ItemLike result, int resultCount, ItemLike ingredient1) {
        simpleShapeless(finishedRecipeConsumer, category, result, resultCount, ingredient1, 1);
    }
    protected static void simpleShapeless(Consumer<FinishedRecipe> finishedRecipeConsumer, RecipeCategory category, ItemLike result, int resultCount, ItemLike ingredient1, int ingredientCount) {
        ShapelessRecipeBuilder.shapeless(category, result, resultCount)
                .requires(ingredient1, ingredientCount)
                .unlockedBy(getHasName(ingredient1), has(ingredient1))
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

    protected static void doubleSimpleShapelessOneUnlockedBy(Consumer<FinishedRecipe> finishedRecipeConsumer, RecipeCategory category, ItemLike result, int resultCount, ItemLike ingredient1, ItemLike ingredient2) {
        doubleSimpleShapelessOneUnlockedBy(finishedRecipeConsumer, category, result, resultCount, ingredient1, 1, ingredient2, 1);
    }

    protected static void doubleSimpleShapelessOneUnlockedBy(Consumer<FinishedRecipe> finishedRecipeConsumer, RecipeCategory category, ItemLike result, int resultCount, ItemLike ingredient1, int ingredient1Count, ItemLike ingredient2, int ingredient2Count) {
        ShapelessRecipeBuilder.shapeless(category, result, resultCount)
                .requires(ingredient1, ingredient1Count)
                .requires(ingredient2, ingredient2Count)
                .unlockedBy(getHasName(ingredient1), has(ingredient1))
                .save(finishedRecipeConsumer);
    }

    protected static void mojinRecipes(Consumer<FinishedRecipe> finishedRecipeConsumer, ItemLike mojin, ItemLike flavor) {
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, mojin)
                .pattern("ABC")
                .pattern("DED")
                .pattern(" F ")
                .define('A', PrettyGuardianItem.MINT.get())
                .define('B', PrettyGuardianItem.LEMON.get())
                .define('C', flavor)
                .define('D', Items.SUGAR)
                .define('E', Items.WATER_BUCKET)
                .define('F', PrettyGuardianItem.JUICE_GLASS.get())
                .unlockedBy(getHasName(PrettyGuardianItem.JUICE_GLASS.get()), has(PrettyGuardianItem.JUICE_GLASS.get()))
                .save(finishedRecipeConsumer);
    }

    protected static void simpleBubbleTea(Consumer<FinishedRecipe> finishedRecipeConsumer, ItemLike bubbleTea, ItemLike flavor) {
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, bubbleTea)
                .pattern("ABA")
                .pattern("CCC")
                .pattern(" D ")
                .define('A', PrettyGuardianItem.BOBA.get())
                .define('B', Items.WATER_BUCKET)
                .define('C', flavor)
                .define('D', PrettyGuardianItem.JUICE_GLASS.get())
                .unlockedBy(getHasName(PrettyGuardianItem.JUICE_GLASS.get()), has(PrettyGuardianItem.JUICE_GLASS.get()))
                .unlockedBy(getHasName(PrettyGuardianItem.BOBA.get()), has(PrettyGuardianItem.BOBA.get()))
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

    protected static void waffleIceCreamRecipes(Consumer<FinishedRecipe> finishedRecipeConsumer, ItemLike waffleIceCream, ItemLike flavor) {
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, waffleIceCream, 3)
                .pattern("ABC")
                .pattern("DEF")
                .define('A', Items.MILK_BUCKET)
                .define('B', Items.SNOWBALL)
                .define('C', Items.EGG)
                .define('D', Items.SUGAR)
                .define('E', PrettyGuardianItem.WAFFLE.get())
                .define('F', flavor)
                .unlockedBy(getHasName(PrettyGuardianItem.WAFFLE.get()), has(PrettyGuardianItem.WAFFLE.get()))
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
