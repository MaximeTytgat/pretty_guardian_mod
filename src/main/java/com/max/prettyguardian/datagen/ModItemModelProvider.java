package com.max.prettyguardian.datagen;

import com.max.prettyguardian.PrettyGuardian;
import com.max.prettyguardian.blocks.PrettyGuardianBlock;
import com.max.prettyguardian.item.PrettyGuardianItem;
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
        super(output, PrettyGuardian.MOD_ID, existingFileHelper);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(PrettyGuardian.MOD_ID, "item/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleItemForBlock(RegistryObject<Block> block) {
        return withExistingParent(block.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(PrettyGuardian.MOD_ID, "item/" + block.getId().getPath()));
    }

    private  ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(PrettyGuardian.MOD_ID, "item/" + item.getId().getPath()));
    }

    @Override
    protected void registerModels() {
        simpleItem(PrettyGuardianItem.STRAWBERRY_SEEDS);
        simpleItem(PrettyGuardianItem.VANILLA_SEEDS);
        simpleItem(PrettyGuardianItem.MINT_SEEDS);
        simpleItemForBlock(PrettyGuardianBlock.CHOCOLATE_CAKE);
        simpleItemForBlock(PrettyGuardianBlock.CREAM_CAKE);
        simpleItemForBlock(PrettyGuardianBlock.RHUM_CAKE);
        simpleItemForBlock(PrettyGuardianBlock.STRAWBERRY_CAKE);
        simpleItemForBlock(PrettyGuardianBlock.BERRY_STRAWBERRY_CAKE);
        simpleItemForBlock(PrettyGuardianBlock.VELVET_CAKE);
        simpleItemForBlock(PrettyGuardianBlock.CREAM_STRAWBERRY_CAKE);
        simpleItemForBlock(PrettyGuardianBlock.STRAWBERRY_CHOCO_CAKE);
        simpleItemForBlock(PrettyGuardianBlock.THREE_VELVET_CAKE);
        simpleItemForBlock(PrettyGuardianBlock.THREE_CHOCO_CAKE);
        simpleItemForBlock(PrettyGuardianBlock.THREE_STRAWBERRY_CAKE);
        simpleItemForBlock(PrettyGuardianBlock.THREE_STRAWBERRY_CHOCO_CAKE);
        simpleItemForBlock(PrettyGuardianBlock.APPLE_PIE);
        simpleItemForBlock(PrettyGuardianBlock.CHOCOLATE_PIE);
        simpleItemForBlock(PrettyGuardianBlock.MAGIC_PIE);
        simpleItemForBlock(PrettyGuardianBlock.LEMON_PIE);
        simpleItemForBlock(PrettyGuardianBlock.STRAWBERRY_PIE);

        simpleItem(PrettyGuardianItem.STRAWBERRY);
        simpleItem(PrettyGuardianItem.VANILLA);
        simpleItem(PrettyGuardianItem.MINT);
        simpleItem(PrettyGuardianItem.PISTACHIO);
        simpleItem(PrettyGuardianItem.LEMON);
        simpleItem(PrettyGuardianItem.BOBA);
        simpleItem(PrettyGuardianItem.RAW_BOBA);
        simpleItem(PrettyGuardianItem.AGARAGAR);
        simpleItem(PrettyGuardianItem.CREAM);
        simpleItem(PrettyGuardianItem.RAW_SQUID);
        simpleItem(PrettyGuardianItem.SQUID_STICK);
        simpleItem(PrettyGuardianItem.SQUID_COOKED);

        simpleItem(PrettyGuardianItem.CHOCOLATE_STRAWBERRY);
        simpleItem(PrettyGuardianItem.SECRET_DONUT);
        simpleItem(PrettyGuardianItem.DONUT);
        simpleItem(PrettyGuardianItem.STRAWBERRY_DONUT);
        simpleItem(PrettyGuardianItem.CHOCOLATE_DONUT);
        simpleItem(PrettyGuardianItem.SECRET_MOCHI_DONUT);
        simpleItem(PrettyGuardianItem.MOCHI_DONUT);
        simpleItem(PrettyGuardianItem.STRAWBERRY_MOCHI_DONUT);
        simpleItem(PrettyGuardianItem.CHOCOLATE_MOCHI_DONUT);
        simpleItem(PrettyGuardianItem.CARAMEL);
        simpleItem(PrettyGuardianItem.CHOCOLATE);
        simpleItem(PrettyGuardianItem.STRAWBERRY_MILK_BUCKET);
        simpleItem(PrettyGuardianItem.CHOCOLATE_MILK_BUCKET);
        simpleItem(PrettyGuardianItem.VANILLA_MILK_BUCKET);
        simpleItem(PrettyGuardianItem.STRAWBERRY_MILK_CARTON);
        simpleItem(PrettyGuardianItem.CHOCOLATE_MILK_CARTON);
        simpleItem(PrettyGuardianItem.VANILLA_MILK_CARTON);
        simpleItem(PrettyGuardianItem.PISTACHIO_ICE_CREAM);
        simpleItem(PrettyGuardianItem.VANILLA_ICE_CREAM);
        simpleItem(PrettyGuardianItem.CHOCOLATE_ICE_CREAM);
        simpleItem(PrettyGuardianItem.STRAWBERRY_ICE_CREAM);
        simpleItem(PrettyGuardianItem.POKKY_ICE_CREAM);
        simpleItem(PrettyGuardianItem.APPLE_MOJITO);
        simpleItem(PrettyGuardianItem.MINT_MOJITO);
        simpleItem(PrettyGuardianItem.STRAWBERRY_MOJITO);
        simpleItem(PrettyGuardianItem.BUBBLETEA_MELON);
        simpleItem(PrettyGuardianItem.BUBBLETEA_STRAWBERRY);
        simpleItem(PrettyGuardianItem.BUBBLETEA_CARAMEL);
        simpleItem(PrettyGuardianItem.WAFFLE);
        simpleItem(PrettyGuardianItem.FISH_WAFFLE);
        simpleItem(PrettyGuardianItem.CHOCOLATE_WAFFLE);
        simpleItem(PrettyGuardianItem.ICE_CREAM_WAFFLE_VANILLA);
        simpleItem(PrettyGuardianItem.ICE_CREAM_WAFFLE_CHOCOLATE);
        simpleItem(PrettyGuardianItem.ICE_CREAM_WAFFLE_STRAWBERRY);
        simpleItem(PrettyGuardianItem.ICE_CREAM_WAFFLE_PISTACHIO);
        simpleItem(PrettyGuardianItem.CHOCOLATE_POCKY);
        simpleItem(PrettyGuardianItem.STRAWBERRY_POCKY);
        simpleItem(PrettyGuardianItem.TAKOYAKI);
        simpleItem(PrettyGuardianItem.DANGO_CARAMEL);
        simpleItem(PrettyGuardianItem.TRICOLOR_DANGO);
        simpleItem(PrettyGuardianItem.POPSICLE);
        simpleItem(PrettyGuardianItem.CREAM_POPSICLE);
        simpleItem(PrettyGuardianItem.CHOCOLATE_POPSICLE);
        simpleItem(PrettyGuardianItem.STRAWBERRY_POPSICLE);
        simpleItem(PrettyGuardianItem.CARAMEL_PUDDING);
        simpleItem(PrettyGuardianItem.CHOCOLATE_PUDDING);
        simpleItem(PrettyGuardianItem.STRAWBERRY_PUDDING);
        simpleItem(PrettyGuardianItem.PISTACHIO_PUDDING);
        simpleItem(PrettyGuardianItem.MARSHMALLOW);
        simpleItem(PrettyGuardianItem.MARSHMALLOW_STICK);
        simpleItem(PrettyGuardianItem.ROASTED_MARSHMALLOW_STICK);
        simpleItem(PrettyGuardianItem.SMORE);
        simpleItem(PrettyGuardianItem.MARSHMELLOW_STRAWBERRY_BURGER);
        simpleItem(PrettyGuardianItem.CANDY_APPLE);
        simpleItem(PrettyGuardianItem.CHOCOLATE_CROISSANT);
        simpleItem(PrettyGuardianItem.VANILLA_CROISSANT);
        simpleItem(PrettyGuardianItem.STRAWBERRY_CROISSANT);
        simpleItem(PrettyGuardianItem.PISTACHIO_CROISSANT);
        simpleItem(PrettyGuardianItem.RHUM_BOTTLE);
        simpleItem(PrettyGuardianItem.JUICE_GLASS);
        simpleItem(PrettyGuardianItem.ICE_CREAM_CUP);


//        simpleItem(PrettyGuardianItem.RAW_PINK_SAPPHIRE);
        simpleItem(PrettyGuardianItem.PINK_SAPPHIRE);
        simpleItem(PrettyGuardianItem.HEART_ARROW);

        simpleItem(PrettyGuardianItem.CUTIE_MOON_ROD);
        simpleItem(PrettyGuardianItem.ETERNAL_TIARE);
        simpleItem(PrettyGuardianItem.MOON_KALEIDOSCOPE);
        simpleItem(PrettyGuardianItem.MOON_STICK);
        simpleItem(PrettyGuardianItem.MOON_STICK_PEARL);
        simpleItem(PrettyGuardianItem.SPIRAL_HEART_MOON_ROD);

        simpleItem(PrettyGuardianItem.PLUTONS_KEY);
//        simpleItem(PrettyGuardianItem.NEPTUNES_MIRROR);
//        simpleItem(PrettyGuardianItem.SPACE_SWORD);

        handheldItem(PrettyGuardianItem.PINK_SAPPHIRE_AXE);
        handheldItem(PrettyGuardianItem.PINK_SAPPHIRE_PICKAXE);
        handheldItem(PrettyGuardianItem.PINK_SAPPHIRE_SHOVEL);
        handheldItem(PrettyGuardianItem.PINK_SAPPHIRE_HOE);
        handheldItem(PrettyGuardianItem.PINK_SAPPHIRE_SWORD);

        trimmedArmorItem(PrettyGuardianItem.PINK_SAPPHIRE_HELMET);
        trimmedArmorItem(PrettyGuardianItem.PINK_SAPPHIRE_CHESTPLATE);
        trimmedArmorItem(PrettyGuardianItem.PINK_SAPPHIRE_LEGGINGS);
        trimmedArmorItem(PrettyGuardianItem.PINK_SAPPHIRE_BOOTS);

        simpleItem(PrettyGuardianItem.RUBY);
        simpleItem(PrettyGuardianItem.RAW_RUBY);
        handheldItem(PrettyGuardianItem.RUBY_AXE);
        handheldItem(PrettyGuardianItem.RUBY_PICKAXE);
        handheldItem(PrettyGuardianItem.RUBY_SHOVEL);
        handheldItem(PrettyGuardianItem.RUBY_HOE);
        handheldItem(PrettyGuardianItem.RUBY_SWORD);

        simpleItem(PrettyGuardianItem.SAILORMOON_OST_MUSIC_DISC);
        simpleItem(PrettyGuardianItem.SAILORMOON_MOONPRIDE_MUSIC_DISC);
        simpleItem(PrettyGuardianItem.LOFI_MUSIC_DISC);
        simpleItem(PrettyGuardianItem.TAVERN_MUSIC_DISC);
        simpleItem(PrettyGuardianItem.JAPANESE_FLUTE_MUSIC_DISC);

        simpleItem(PrettyGuardianItem.BUTTERFLY_NET);

        trimmedArmorItem(PrettyGuardianItem.RUBY_HELMET);
        trimmedArmorItem(PrettyGuardianItem.RUBY_CHESTPLATE);
        trimmedArmorItem(PrettyGuardianItem.RUBY_LEGGINGS);
        trimmedArmorItem(PrettyGuardianItem.RUBY_BOOTS);

        saplingItem(PrettyGuardianBlock.PISTACHIO_SAPLING);
        saplingItem(PrettyGuardianBlock.LEMON_SAPLING);
        saplingItem(PrettyGuardianBlock.BOBA_SAPLING);

        simpleBlockItemBlockTexture(PrettyGuardianBlock.STRAWBERRY_CROP_FLOWER);

        withExistingParent(PrettyGuardianItem.STRAWBERRY_COW_EGG.getId().getPath(),
                mcLoc("item/template_spawn_egg"));
        withExistingParent(PrettyGuardianItem.CELESTIAL_RABBIT_EGG.getId().getPath(),
                mcLoc("item/template_spawn_egg"));
        withExistingParent(PrettyGuardianItem.FAIRY_EGG.getId().getPath(),
                mcLoc("item/template_spawn_egg"));

        simpleItem(PrettyGuardianItem.FAIRY_DUST);
        simpleItem(PrettyGuardianItem.GIFT_BOX);
    }


    private ItemModelBuilder saplingItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(PrettyGuardian.MOD_ID,"block/" + item.getId().getPath()));
    }

    private void trimmedArmorItem(RegistryObject<Item> itemRegistryObject) {
        final String MOD_ID = PrettyGuardian.MOD_ID;

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
                new ResourceLocation(PrettyGuardian.MOD_ID,"block/" + item.getId().getPath()));
    }
}
