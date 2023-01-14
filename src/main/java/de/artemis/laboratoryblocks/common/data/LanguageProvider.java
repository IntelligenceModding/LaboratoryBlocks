package de.artemis.laboratoryblocks.common.data;

import de.artemis.laboratoryblocks.LaboratoryBlocks;
import de.artemis.laboratoryblocks.common.registration.ModBlocks;
import de.artemis.laboratoryblocks.common.registration.ModItems;
import net.minecraft.data.DataGenerator;

public class LanguageProvider extends net.minecraftforge.common.data.LanguageProvider {
    public LanguageProvider(DataGenerator gen, String locale) {
        super(gen, LaboratoryBlocks.MOD_ID, locale);
    }

    @Override
    protected void addTranslations() {
        add("itemGroup.laboratoryblocks", "Artemis' Laboratory Blocks");

        add(ModItems.STARCH.get(), "Starch");
        add(ModItems.COMPRESSED_STARCH.get(), "Compressed Starch");
        add(ModItems.PLA_SHEETS.get(), "PLA Sheets");
        add(ModBlocks.PLA_BLOCK.get(), "PLA Block");
        add(ModBlocks.ENLIGHTED_PLA_BLOCK.get(), "Enlighted PLA Block");
        add(ModBlocks.PLA_TILES.get(), "PLA Tiles");
        add(ModBlocks.ENLIGHTED_PLA_TILES.get(), "Enlighted PLA Tiles");

        add(ModBlocks.PLA_FLOORING.get(), "PLA Flooring");
        add(ModBlocks.ENLIGHTED_PLA_FLOORING.get(), "Enlighted PLA Flooring");
        add(ModBlocks.TILED_PLA_FLOORING.get(), "Tiled PLA Flooring");
        add(ModBlocks.ENLIGHTED_TILED_PLA_FLOORING.get(), "Enlighted Tiled PLA Flooring");

        add(ModBlocks.LABORATORY_BLOCK.get(), "Laboratory Block");
        add(ModBlocks.ENLIGHTED_LABORATORY_BLOCK.get(), "Enlighted Laboratory Block");
        add(ModBlocks.LABORATORY_TILES.get(), "Laboratory Tiles");
        add(ModBlocks.ENLIGHTED_LABORATORY_TILES.get(), "Enlighted Laboratory Tiles");
        add(ModBlocks.GRAY_LABORATORY_TILES.get(), "Gray Laboratory Tiles");
        add(ModBlocks.ENLIGHTED_GRAY_LABORATORY_TILES.get(), "Enlighted Gray Laboratory Tiles");
        add(ModBlocks.MIXED_LABORATORY_TILES.get(), "Mixed Laboratory Tiles");
        add(ModBlocks.ENLIGHTED_MIXED_LABORATORY_TILES.get(), "Enlighted Mixed Laboratory Tiles");

        add(ModBlocks.OAK_LABORATORY_FLOOR.get(), "Oak Laboratory Floor");
        add(ModBlocks.ENLIGHTED_OAK_LABORATORY_FLOOR.get(), "Enlighted Oak Laboratory Floor");
        add(ModBlocks.SPRUCE_LABORATORY_FLOOR.get(), "Spruce Laboratory Floor");
        add(ModBlocks.ENLIGHTED_SPRUCE_LABORATORY_FLOOR.get(), "Enlighted Spruce Laboratory Floor");
        add(ModBlocks.BIRCH_LABORATORY_FLOOR.get(), "Birch Laboratory Floor");
        add(ModBlocks.ENLIGHTED_BIRCH_LABORATORY_FLOOR.get(), "Enlighted Birch Laboratory Floor");
        add(ModBlocks.DARK_OAK_LABORATORY_FLOOR.get(), "Dark Oak Laboratory Floor");
        add(ModBlocks.ENLIGHTED_DARK_OAK_LABORATORY_FLOOR.get(), "Enlighted Dark Oak Laboratory Floor");
        add(ModBlocks.JUNGLE_LABORATORY_FLOOR.get(), "Jungle Laboratory Floor");
        add(ModBlocks.ENLIGHTED_JUNGLE_LABORATORY_FLOOR.get(), "Enlighted Jungle Laboratory Floor");
        add(ModBlocks.ACACIA_LABORATORY_FLOOR.get(), "Acacia Laboratory Floor");
        add(ModBlocks.ENLIGHTED_ACACIA_LABORATORY_FLOOR.get(), "Enlighted Acacia Laboratory Floor");
        add(ModBlocks.MANGROVE_LABORATORY_FLOOR.get(), "Mangrove Laboratory Floor");
        add(ModBlocks.ENLIGHTED_MANGROVE_LABORATORY_FLOOR.get(), "Enlighted Mangrove Laboratory Floor");
        add(ModBlocks.CRIMSON_LABORATORY_FLOOR.get(), "Crimson Laboratory Floor");
        add(ModBlocks.ENLIGHTED_CRIMSON_LABORATORY_FLOOR.get(), "Enlighted Crimson Laboratory Floor");
        add(ModBlocks.WARPED_LABORATORY_FLOOR.get(), "Warped Laboratory Floor");
        add(ModBlocks.ENLIGHTED_WARPED_LABORATORY_FLOOR.get(), "Enlighted Warped Laboratory Floor");

        add(ModBlocks.OAK_LABORATORY_TILES.get(), "Oak Laboratory Tiles");
        add(ModBlocks.ENLIGHTED_OAK_LABORATORY_TILES.get(), "Enlighted Oak Laboratory Tiles");
        add(ModBlocks.SPRUCE_LABORATORY_TILES.get(), "Spruce Laboratory Tiles");
        add(ModBlocks.ENLIGHTED_SPRUCE_LABORATORY_TILES.get(), "Enlighted Spruce Laboratory Tiles");
        add(ModBlocks.BIRCH_LABORATORY_TILES.get(), "Birch Laboratory Tiles");
        add(ModBlocks.ENLIGHTED_BIRCH_LABORATORY_TILES.get(), "Enlighted Birch Laboratory Tiles");
        add(ModBlocks.DARK_OAK_LABORATORY_TILES.get(), "Dark Oak Laboratory Tiles");
        add(ModBlocks.ENLIGHTED_DARK_OAK_LABORATORY_TILES.get(), "Enlighted Dark Oak Laboratory Tiles");
        add(ModBlocks.JUNGLE_LABORATORY_TILES.get(), "Jungle Laboratory Tiles");
        add(ModBlocks.ENLIGHTED_JUNGLE_LABORATORY_TILES.get(), "Enlighted Jungle Laboratory Tiles");
        add(ModBlocks.ACACIA_LABORATORY_TILES.get(), "Acacia Laboratory Tiles");
        add(ModBlocks.ENLIGHTED_ACACIA_LABORATORY_TILES.get(), "Enlighted Acacia Laboratory Tiles");
        add(ModBlocks.MANGROVE_LABORATORY_TILES.get(), "Mangrove Laboratory Tiles");
        add(ModBlocks.ENLIGHTED_MANGROVE_LABORATORY_TILES.get(), "Enlighted Mangrove Laboratory Tiles");
        add(ModBlocks.CRIMSON_LABORATORY_TILES.get(), "Crimson Laboratory Tiles");
        add(ModBlocks.ENLIGHTED_CRIMSON_LABORATORY_TILES.get(), "Enlighted Crimson Laboratory Tiles");
        add(ModBlocks.WARPED_LABORATORY_TILES.get(), "Warped Laboratory Tiles");
        add(ModBlocks.ENLIGHTED_WARPED_LABORATORY_TILES.get(), "Enlighted Warped Laboratory Tiles");

        add(ModBlocks.LABORATORY_GLASS.get(), "Laboratory Glass");
        add(ModBlocks.ENLIGHTED_LABORATORY_GLASS.get(), "Enlighted Laboratory Glass");
    }
}
