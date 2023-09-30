package com.doudou.cutecore.datagen;

import com.doudou.cutecore.CuteCore;
import com.doudou.cutecore.blocks.CuteCoreBlock;
import com.doudou.cutecore.item.CuteCoreItem;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;
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
        super(output, CuteCore.MODID, existingFileHelper);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(CuteCore.MODID, "item/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleItemForBlock(RegistryObject<Block> block) {
        return withExistingParent(block.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(CuteCore.MODID, "item/" + block.getId().getPath()));
    }

    private  ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(CuteCore.MODID, "item/" + item.getId().getPath()));
    }

    @Override
    protected void registerModels() {
        simpleItem(CuteCoreItem.STRAWBERRY_SEEDS);
        simpleItemForBlock(CuteCoreBlock.THREE_STRAWBERRY_CAKE);
        simpleItemForBlock(CuteCoreBlock.THREE_STRAWBERRY_CHOCO_CAKE);
        simpleItem(CuteCoreItem.STRAWBERRY);
        simpleItem(CuteCoreItem.CHOCOLATE_HEART);
//        simpleItem(CuteCoreItem.RAW_PINK_SAPPHIRE);
        simpleItem(CuteCoreItem.PINK_SAPPHIRE);
        simpleItem(CuteCoreItem.CUTE_ARROW);

        handheldItem(CuteCoreItem.PINK_SAPPHIRE_AXE);
        handheldItem(CuteCoreItem.PINK_SAPPHIRE_PICKAXE);
        handheldItem(CuteCoreItem.PINK_SAPPHIRE_SHOVEL);
        handheldItem(CuteCoreItem.PINK_SAPPHIRE_HOE);
        handheldItem(CuteCoreItem.PINK_SAPPHIRE_SWORD);

        trimmedArmorItem(CuteCoreItem.PINK_SAPPHIRE_HELMET);
        trimmedArmorItem(CuteCoreItem.PINK_SAPPHIRE_CHESTPLATE);
        trimmedArmorItem(CuteCoreItem.PINK_SAPPHIRE_LEGGINGS);
        trimmedArmorItem(CuteCoreItem.PINK_SAPPHIRE_BOOTS);

        handheldItem(CuteCoreItem.RUBY_AXE);
        handheldItem(CuteCoreItem.RUBY_PICKAXE);
        handheldItem(CuteCoreItem.RUBY_SHOVEL);
        handheldItem(CuteCoreItem.RUBY_HOE);
        handheldItem(CuteCoreItem.RUBY_SWORD);

//        trimmedArmorItem(CuteCoreItem.RUBY_HELMET);
//        trimmedArmorItem(CuteCoreItem.RUBY_CHESTPLATE);
//        trimmedArmorItem(CuteCoreItem.RUBY_LEGGINGS);
//        trimmedArmorItem(CuteCoreItem.RUBY_BOOTS);
    }

    private void trimmedArmorItem(RegistryObject<Item> itemRegistryObject) {
        final String MOD_ID = CuteCore.MODID;

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
}
