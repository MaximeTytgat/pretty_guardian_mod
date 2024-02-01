package com.doudou.cutecore.datagen;

import com.doudou.cutecore.CuteCore;
import com.doudou.cutecore.blocks.CuteCoreBlock;
import com.doudou.cutecore.item.CuteCoreItem;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.LinkedHashMap;

public class ModItemModelProvider extends ItemModelProvider {
    private static LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();
    static {
        trimMaterials.put(TrimMaterials.QUARTZ, 0.1F);
        trimMaterials.put(TrimMaterials.IRON, 0.2F);
        trimMaterials.put(TrimMaterials.NETHERITE, 0.3F);
        trimMaterials.put(TrimMaterials.REDSTONE, 0.4F);
        trimMaterials.put(TrimMaterials.COPPER, 0.5F);
        trimMaterials.put(TrimMaterials.GOLD, 0.6F);
        trimMaterials.put(TrimMaterials.EMERALD, 0.7F);
        trimMaterials.put(TrimMaterials.DIAMOND, 0.8F);
        trimMaterials.put(TrimMaterials.LAPIS, 0.9F);
        trimMaterials.put(TrimMaterials.AMETHYST, 1.0F);
    }


    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, CuteCore.MOD_ID, existingFileHelper);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(CuteCore.MOD_ID, "item/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleItemForBlock(RegistryObject<Block> block) {
        return withExistingParent(block.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(CuteCore.MOD_ID, "item/" + block.getId().getPath()));
    }

    private  ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(CuteCore.MOD_ID, "item/" + item.getId().getPath()));
    }

    @Override
    protected void registerModels() {
        simpleItem(CuteCoreItem.STRAWBERRY_SEEDS);
        simpleItem(CuteCoreItem.VANILLA_SEEDS);
        simpleItem(CuteCoreItem.MINT_SEEDS);
        simpleItemForBlock(CuteCoreBlock.CHOCOLATE_CAKE);
        simpleItemForBlock(CuteCoreBlock.CREAM_CAKE);
        simpleItemForBlock(CuteCoreBlock.RHUM_CAKE);
        simpleItemForBlock(CuteCoreBlock.STRAWBERRY_CAKE);
        simpleItemForBlock(CuteCoreBlock.BERRY_STRAWBERRY_CAKE);
        simpleItemForBlock(CuteCoreBlock.VELVET_CAKE);
        simpleItemForBlock(CuteCoreBlock.CREAM_STRAWBERRY_CAKE);
        simpleItemForBlock(CuteCoreBlock.STRAWBERRY_CHOCO_CAKE);
        simpleItemForBlock(CuteCoreBlock.THREE_VELVET_CAKE);
        simpleItemForBlock(CuteCoreBlock.THREE_CHOCO_CAKE);
        simpleItemForBlock(CuteCoreBlock.THREE_STRAWBERRY_CAKE);
        simpleItemForBlock(CuteCoreBlock.THREE_STRAWBERRY_CHOCO_CAKE);
        simpleItemForBlock(CuteCoreBlock.APPLE_PIE);
        simpleItemForBlock(CuteCoreBlock.CHOCOLATE_PIE);
        simpleItemForBlock(CuteCoreBlock.MAGIC_PIE);
        simpleItemForBlock(CuteCoreBlock.LEMON_PIE);
        simpleItemForBlock(CuteCoreBlock.STRAWBERRY_PIE);

        simpleItem(CuteCoreItem.STRAWBERRY);
        simpleItem(CuteCoreItem.VANILLA);
        simpleItem(CuteCoreItem.MINT);
        simpleItem(CuteCoreItem.PISTACHIO);
        simpleItem(CuteCoreItem.LEMON);

        simpleItem(CuteCoreItem.CHOCOLATE_STRAWBERRY);
        simpleItem(CuteCoreItem.SECRET_DONUT);
        simpleItem(CuteCoreItem.DONUT);
        simpleItem(CuteCoreItem.STRAWBERRY_DONUT);
        simpleItem(CuteCoreItem.CHOCOLATE_DONUT);
        simpleItem(CuteCoreItem.SECRET_MOCHI_DONUT);
        simpleItem(CuteCoreItem.MOCHI_DONUT);
        simpleItem(CuteCoreItem.STRAWBERRY_MOCHI_DONUT);
        simpleItem(CuteCoreItem.CHOCOLATE_MOCHI_DONUT);
        simpleItem(CuteCoreItem.CARAMEL);
        simpleItem(CuteCoreItem.CHOCOLATE);
        simpleItem(CuteCoreItem.STRAWBERRY_MILK_BUCKET);
        simpleItem(CuteCoreItem.CHOCOLATE_MILK_BUCKET);
        simpleItem(CuteCoreItem.VANILLA_MILK_BUCKET);
        simpleItem(CuteCoreItem.STRAWBERRY_MILK_CARTON);
        simpleItem(CuteCoreItem.CHOCOLATE_MILK_CARTON);
        simpleItem(CuteCoreItem.VANILLA_MILK_CARTON);
        simpleItem(CuteCoreItem.PISTACHIO_ICE_CREAM);
        simpleItem(CuteCoreItem.VANILLA_ICE_CREAM);
        simpleItem(CuteCoreItem.CHOCOLATE_ICE_CREAM);
        simpleItem(CuteCoreItem.STRAWBERRY_ICE_CREAM);
        simpleItem(CuteCoreItem.POKKY_ICE_CREAM);
        simpleItem(CuteCoreItem.APPLE_MOJITO);
        simpleItem(CuteCoreItem.MINT_MOJITO);
        simpleItem(CuteCoreItem.STRAWBERRY_MOJITO);
        simpleItem(CuteCoreItem.BUBBLE_TEA);
        simpleItem(CuteCoreItem.WAFFLE);
        simpleItem(CuteCoreItem.CHOCOLATE_WAFFLE);
        simpleItem(CuteCoreItem.CHOCOLATE_POCKY);
        simpleItem(CuteCoreItem.STRAWBERRY_POCKY);
        simpleItem(CuteCoreItem.DANGO_CARAMEL);
        simpleItem(CuteCoreItem.TRICOLOR_DANGO);
        simpleItem(CuteCoreItem.POPSICLE);
        simpleItem(CuteCoreItem.VANILLA_POPSICLE);
        simpleItem(CuteCoreItem.CHOCOLATE_POPSICLE);
        simpleItem(CuteCoreItem.STRAWBERRY_POPSICLE);
        simpleItem(CuteCoreItem.CARAMEL_PUDDING);
        simpleItem(CuteCoreItem.CHOCOLATE_PUDDING);
        simpleItem(CuteCoreItem.STRAWBERRY_PUDDING);
        simpleItem(CuteCoreItem.PISTACHIO_PUDDING);
        simpleItem(CuteCoreItem.MARSHMALLOW);
        simpleItem(CuteCoreItem.MARSHMALLOW_STICK);
        simpleItem(CuteCoreItem.ROASTED_MARSHMALLOW_STICK);
        simpleItem(CuteCoreItem.SMORE);
        simpleItem(CuteCoreItem.MARSHMELLOW_STRAWBERRY_BURGER);
        simpleItem(CuteCoreItem.CANDY_APPLE);
        simpleItem(CuteCoreItem.CHOCOLATE_CROISSANT);
        simpleItem(CuteCoreItem.VANILLA_CROISSANT);
        simpleItem(CuteCoreItem.STRAWBERRY_CROISSANT);
        simpleItem(CuteCoreItem.PISTACHIO_CROISSANT);
        simpleItem(CuteCoreItem.RHUM_BOTTLE);
        simpleItem(CuteCoreItem.JUICE_GLASS);
        simpleItem(CuteCoreItem.ICE_CREAM_CUP);


//        simpleItem(CuteCoreItem.RAW_PINK_SAPPHIRE);
        simpleItem(CuteCoreItem.PINK_SAPPHIRE);
        simpleItem(CuteCoreItem.HEART_ARROW);

        simpleItem(CuteCoreItem.CUTIE_MOON_ROD);
        simpleItem(CuteCoreItem.ETERNAL_TIARE);
        simpleItem(CuteCoreItem.MOON_KALEIDOSCOPE);
        simpleItem(CuteCoreItem.MOON_STICK);
        simpleItem(CuteCoreItem.MOON_STICK_PEARL);
        simpleItem(CuteCoreItem.SPIRAL_HEART_MOON_ROD);
        simpleItem(CuteCoreItem.PLUTONS_KEY);
        simpleItem(CuteCoreItem.NEPTUNES_MIRROR);

        handheldItem(CuteCoreItem.PINK_SAPPHIRE_AXE);
        handheldItem(CuteCoreItem.PINK_SAPPHIRE_PICKAXE);
        handheldItem(CuteCoreItem.PINK_SAPPHIRE_SHOVEL);
        handheldItem(CuteCoreItem.PINK_SAPPHIRE_HOE);
        handheldItem(CuteCoreItem.PINK_SAPPHIRE_SWORD);

        trimmedArmorItem(CuteCoreItem.PINK_SAPPHIRE_HELMET);
        trimmedArmorItem(CuteCoreItem.PINK_SAPPHIRE_CHESTPLATE);
        trimmedArmorItem(CuteCoreItem.PINK_SAPPHIRE_LEGGINGS);
        trimmedArmorItem(CuteCoreItem.PINK_SAPPHIRE_BOOTS);

        simpleItem(CuteCoreItem.RUBY);
        handheldItem(CuteCoreItem.RUBY_AXE);
        handheldItem(CuteCoreItem.RUBY_PICKAXE);
        handheldItem(CuteCoreItem.RUBY_SHOVEL);
        handheldItem(CuteCoreItem.RUBY_HOE);
        handheldItem(CuteCoreItem.RUBY_SWORD);

        simpleItem(CuteCoreItem.SAILORMOON_OST_MUSIC_DISC);
        simpleItem(CuteCoreItem.SAILORMOON_MOONPRIDE_MUSIC_DISC);
        simpleItem(CuteCoreItem.LOFI_MUSIC_DISC);
        simpleItem(CuteCoreItem.TAVERN_MUSIC_DISC);
        simpleItem(CuteCoreItem.JAPANESE_FLUTE_MUSIC_DISC);

        simpleItem(CuteCoreItem.BUTTERFLY_NET);

        trimmedArmorItem(CuteCoreItem.RUBY_HELMET);
        trimmedArmorItem(CuteCoreItem.RUBY_CHESTPLATE);
        trimmedArmorItem(CuteCoreItem.RUBY_LEGGINGS);
        trimmedArmorItem(CuteCoreItem.RUBY_BOOTS);

        saplingItem(CuteCoreBlock.PISTACHIO_SAPLING);
        saplingItem(CuteCoreBlock.LEMON_SAPLING);

        simpleBlockItemBlockTexture(CuteCoreBlock.STRAWBERRY_CROP_FLOWER);

        withExistingParent(CuteCoreItem.STRAWBERRY_COW_EGG.getId().getPath(),
                mcLoc("item/template_spawn_egg"));
        withExistingParent(CuteCoreItem.CELESTIAL_RABBIT_EGG.getId().getPath(),
                mcLoc("item/template_spawn_egg"));

        simpleItem(CuteCoreItem.FAIRY_DUST);
    }


    private ItemModelBuilder saplingItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(CuteCore.MOD_ID,"block/" + item.getId().getPath()));
    }

    private void trimmedArmorItem(RegistryObject<Item> itemRegistryObject) {
        final String MOD_ID = CuteCore.MOD_ID;

        if(itemRegistryObject.get() instanceof ArmorItem armorItem) {
            trimMaterials.entrySet().forEach(entry -> {

                ResourceKey<TrimMaterial> trimMaterial = entry.getKey();
                float trimValue = entry.getValue();

                String armorType = switch (armorItem.getEquipmentSlot()) {
                    case HEAD -> "helmet";
                    case CHEST -> "chestplate";
                    case LEGS -> "leggings";
                    case FEET -> "boots";
                    default -> "";
                };

                String armorItemPath = "item/" + armorItem;
                String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
                String currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
                ResourceLocation armorItemResLoc = new ResourceLocation(MOD_ID, armorItemPath);
                ResourceLocation trimResLoc = new ResourceLocation(trimPath); // minecraft namespace
                ResourceLocation trimNameResLoc = new ResourceLocation(MOD_ID, currentTrimName);

                // This is used for making the ExistingFileHelper acknowledge that this texture exist, so this will
                // avoid an IllegalArgumentException
                existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

                // Trimmed armorItem files
                getBuilder(currentTrimName)
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", armorItemResLoc)
                        .texture("layer1", trimResLoc);

                // Non-trimmed armorItem file (normal variant)
                this.withExistingParent(itemRegistryObject.getId().getPath(),
                                mcLoc("item/generated"))
                        .override()
                        .model(new ModelFile.UncheckedModelFile(trimNameResLoc))
                        .predicate(mcLoc("trim_type"), trimValue).end()
                        .texture("layer0",
                                new ResourceLocation(MOD_ID,
                                        "item/" + itemRegistryObject.getId().getPath()));
            });
        }
    }

    private ItemModelBuilder simpleBlockItemBlockTexture(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(CuteCore.MOD_ID,"block/" + item.getId().getPath()));
    }
}
