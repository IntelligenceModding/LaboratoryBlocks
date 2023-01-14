package de.artemis.laboratoryblocks.common.data;

import de.artemis.laboratoryblocks.LaboratoryBlocks;
import de.artemis.laboratoryblocks.common.registration.ModBlocks;
import de.artemis.laboratoryblocks.common.registration.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemModelProvider extends net.minecraftforge.client.model.generators.ItemModelProvider {
    public ItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, LaboratoryBlocks.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.STARCH.get());
        simpleItem(ModItems.COMPRESSED_STARCH.get());
        simpleItem(ModItems.PLA_SHEETS.get());
        simpleBlock(ModBlocks.PLA_BLOCK.get());
        cubeAll(ForgeRegistries.BLOCKS.getKey(ModBlocks.ENLIGHTED_PLA_BLOCK.get()).toString(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_pla_block"));
        simpleBlock(ModBlocks.PLA_TILES.get());
        cubeAll(ForgeRegistries.BLOCKS.getKey(ModBlocks.ENLIGHTED_PLA_TILES.get()).toString(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_pla_tiles"));

        carpet(ForgeRegistries.BLOCKS.getKey(ModBlocks.PLA_FLOORING.get()).toString(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/pla_block"));
        carpet(ForgeRegistries.BLOCKS.getKey(ModBlocks.ENLIGHTED_PLA_FLOORING.get()).toString(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_pla_block"));
        carpet(ForgeRegistries.BLOCKS.getKey(ModBlocks.TILED_PLA_FLOORING.get()).toString(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/pla_tiles"));
        carpet(ForgeRegistries.BLOCKS.getKey(ModBlocks.ENLIGHTED_TILED_PLA_FLOORING.get()).toString(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_pla_tiles"));

        simpleBlock(ModBlocks.LABORATORY_BLOCK.get());
        cubeAll(ForgeRegistries.BLOCKS.getKey(ModBlocks.ENLIGHTED_LABORATORY_BLOCK.get()).toString(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_laboratory_block"));
        simpleBlock(ModBlocks.LABORATORY_TILES.get());
        cubeAll(ForgeRegistries.BLOCKS.getKey(ModBlocks.ENLIGHTED_LABORATORY_TILES.get()).toString(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_laboratory_tiles"));
        simpleBlock(ModBlocks.GRAY_LABORATORY_TILES.get());
        cubeAll(ForgeRegistries.BLOCKS.getKey(ModBlocks.ENLIGHTED_GRAY_LABORATORY_TILES.get()).toString(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_gray_laboratory_tiles"));
        simpleBlock(ModBlocks.MIXED_LABORATORY_TILES.get());
        cubeAll(ForgeRegistries.BLOCKS.getKey(ModBlocks.ENLIGHTED_MIXED_LABORATORY_TILES.get()).toString(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_mixed_laboratory_tiles"));

        simpleBlock(ModBlocks.OAK_LABORATORY_FLOOR.get());
        cubeAll(ForgeRegistries.BLOCKS.getKey(ModBlocks.ENLIGHTED_OAK_LABORATORY_FLOOR.get()).toString(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_oak_laboratory_floor"));
        simpleBlock(ModBlocks.SPRUCE_LABORATORY_FLOOR.get());
        cubeAll(ForgeRegistries.BLOCKS.getKey(ModBlocks.ENLIGHTED_SPRUCE_LABORATORY_FLOOR.get()).toString(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_spruce_laboratory_floor"));
        simpleBlock(ModBlocks.BIRCH_LABORATORY_FLOOR.get());
        cubeAll(ForgeRegistries.BLOCKS.getKey(ModBlocks.ENLIGHTED_BIRCH_LABORATORY_FLOOR.get()).toString(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_birch_laboratory_floor"));
        simpleBlock(ModBlocks.DARK_OAK_LABORATORY_FLOOR.get());
        cubeAll(ForgeRegistries.BLOCKS.getKey(ModBlocks.ENLIGHTED_DARK_OAK_LABORATORY_FLOOR.get()).toString(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_dark_oak_laboratory_floor"));
        simpleBlock(ModBlocks.JUNGLE_LABORATORY_FLOOR.get());
        cubeAll(ForgeRegistries.BLOCKS.getKey(ModBlocks.ENLIGHTED_JUNGLE_LABORATORY_FLOOR.get()).toString(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_jungle_laboratory_floor"));
        simpleBlock(ModBlocks.ACACIA_LABORATORY_FLOOR.get());
        cubeAll(ForgeRegistries.BLOCKS.getKey(ModBlocks.ENLIGHTED_ACACIA_LABORATORY_FLOOR.get()).toString(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_acacia_laboratory_floor"));
        simpleBlock(ModBlocks.MANGROVE_LABORATORY_FLOOR.get());
        cubeAll(ForgeRegistries.BLOCKS.getKey(ModBlocks.ENLIGHTED_MANGROVE_LABORATORY_FLOOR.get()).toString(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_mangrove_laboratory_floor"));
        simpleBlock(ModBlocks.CRIMSON_LABORATORY_FLOOR.get());
        cubeAll(ForgeRegistries.BLOCKS.getKey(ModBlocks.ENLIGHTED_CRIMSON_LABORATORY_FLOOR.get()).toString(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_crimson_laboratory_floor"));
        simpleBlock(ModBlocks.WARPED_LABORATORY_FLOOR.get());
        cubeAll(ForgeRegistries.BLOCKS.getKey(ModBlocks.ENLIGHTED_WARPED_LABORATORY_FLOOR.get()).toString(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_warped_laboratory_floor"));

        simpleBlock(ModBlocks.OAK_LABORATORY_TILES.get());
        cubeAll(ForgeRegistries.BLOCKS.getKey(ModBlocks.ENLIGHTED_OAK_LABORATORY_TILES.get()).toString(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_oak_laboratory_tiles"));
        simpleBlock(ModBlocks.SPRUCE_LABORATORY_TILES.get());
        cubeAll(ForgeRegistries.BLOCKS.getKey(ModBlocks.ENLIGHTED_SPRUCE_LABORATORY_TILES.get()).toString(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_spruce_laboratory_tiles"));
        simpleBlock(ModBlocks.BIRCH_LABORATORY_TILES.get());
        cubeAll(ForgeRegistries.BLOCKS.getKey(ModBlocks.ENLIGHTED_BIRCH_LABORATORY_TILES.get()).toString(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_birch_laboratory_tiles"));
        simpleBlock(ModBlocks.DARK_OAK_LABORATORY_TILES.get());
        cubeAll(ForgeRegistries.BLOCKS.getKey(ModBlocks.ENLIGHTED_DARK_OAK_LABORATORY_TILES.get()).toString(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_dark_oak_laboratory_tiles"));
        simpleBlock(ModBlocks.JUNGLE_LABORATORY_TILES.get());
        cubeAll(ForgeRegistries.BLOCKS.getKey(ModBlocks.ENLIGHTED_JUNGLE_LABORATORY_TILES.get()).toString(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_jungle_laboratory_tiles"));
        simpleBlock(ModBlocks.ACACIA_LABORATORY_TILES.get());
        cubeAll(ForgeRegistries.BLOCKS.getKey(ModBlocks.ENLIGHTED_ACACIA_LABORATORY_TILES.get()).toString(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_acacia_laboratory_tiles"));
        simpleBlock(ModBlocks.MANGROVE_LABORATORY_TILES.get());
        cubeAll(ForgeRegistries.BLOCKS.getKey(ModBlocks.ENLIGHTED_MANGROVE_LABORATORY_TILES.get()).toString(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_mangrove_laboratory_tiles"));
        simpleBlock(ModBlocks.CRIMSON_LABORATORY_TILES.get());
        cubeAll(ForgeRegistries.BLOCKS.getKey(ModBlocks.ENLIGHTED_CRIMSON_LABORATORY_TILES.get()).toString(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_crimson_laboratory_tiles"));
        simpleBlock(ModBlocks.WARPED_LABORATORY_TILES.get());
        cubeAll(ForgeRegistries.BLOCKS.getKey(ModBlocks.ENLIGHTED_WARPED_LABORATORY_TILES.get()).toString(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_warped_laboratory_tiles"));

        simpleBlock(ModBlocks.LABORATORY_GLASS.get());
        cubeAll(ForgeRegistries.BLOCKS.getKey(ModBlocks.ENLIGHTED_LABORATORY_GLASS.get()).toString(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_laboratory_glass"));
    }

    private void simpleItem(Item item) {
        withExistingParent(DataProvider.getRegistryName(item), "item/generated").texture("layer0", new ResourceLocation(this.modid, "item/" +
                DataProvider.getRawRegistryName(item)));
    }

    private void simpleBlock(Block block) {
        withExistingParent(DataProvider.getRegistryName(block), new ResourceLocation(this.modid, "block/" + DataProvider.getRawRegistryName(block)));
    }
}
