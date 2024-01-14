package de.artemis.laboratoryblocks.common.data;

import de.artemis.laboratoryblocks.LaboratoryBlocks;
import de.artemis.laboratoryblocks.common.registration.ModBlocks;
import de.artemis.laboratoryblocks.common.registration.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;

public class LanguageProvider extends net.minecraftforge.common.data.LanguageProvider {
    public LanguageProvider(PackOutput packOutput, String locale) {
        super(packOutput, LaboratoryBlocks.MOD_ID, locale);
    }

    @Override
    protected void addTranslations() {
        add("itemGroup.laboratoryblocks", "Artemis' Laboratory Blocks");
        add("keybind.laboratoryblocks.category", "Artemis' Laboratory Blocks");

        add("keybind.laboratoryblocks.alternative_configuration_tool_action", "Alternative Configuration Tool Action");

        add(ModItems.STARCH.get(), "Starch");
        add(ModItems.COMPRESSED_STARCH.get(), "Compressed Starch");
        add(ModItems.PLA_SHEETS.get(), "PLA Sheets");
        add(ModItems.IRON_SCREW.get(), "Iron Screw");
        add(ModItems.GLOWSTONE_PARTICLES.get(), "Glowstone Particles");
        add(ModItems.REDSTONE_PARTICLES.get(), "Redstone Particles");
        add(ModItems.CONFIGURATION_TOOL.get(), "Configuration Tool");

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
        add(ModBlocks.CHERRY_LABORATORY_FLOOR.get(), "Cherry Laboratory Floor");
        add(ModBlocks.ENLIGHTED_CHERRY_LABORATORY_FLOOR.get(), "Enlighted Cherry Laboratory Floor");
        add(ModBlocks.CHERRY_LABORATORY_TILES.get(), "Cherry Laboratory Tiles");
        add(ModBlocks.ENLIGHTED_CHERRY_LABORATORY_TILES.get(), "Enlighted Cherry Laboratory Tiles");
        add(ModBlocks.BAMBOO_LABORATORY_TILES.get(), "Bamboo Laboratory Tiles");
        add(ModBlocks.ENLIGHTED_BAMBOO_LABORATORY_TILES.get(), "Enlighted Bamboo Laboratory Tiles");
        add(ModBlocks.BAMBOO_LABORATORY_FLOOR.get(), "Bamboo Laboratory Floor");
        add(ModBlocks.ENLIGHTED_BAMBOO_LABORATORY_FLOOR.get(), "Enlighted Bamboo Laboratory Floor");
        add(ModBlocks.LABORATORY_GLASS.get(), "Laboratory Glass");
        add(ModBlocks.ENLIGHTED_LABORATORY_GLASS.get(), "Enlighted Laboratory Glass");
        add(ModBlocks.LABORATORY_VENT.get(), "Laboratory Vent");
        add(ModBlocks.ENLIGHTED_LABORATORY_VENT.get(), "Enlighted Laboratory Vent");
        add(ModBlocks.LEFT_FACED_BLUE_SIGNALING_LABORATORY_BLOCK.get(), "Left-Faced Blue Signaling Laboratory Block");
        add(ModBlocks.ENLIGHTED_LEFT_FACED_BLUE_SIGNALING_LABORATORY_BLOCK.get(), "Enlighted Left-Faced Blue Signaling Laboratory Block");
        add(ModBlocks.RIGHT_FACED_BLUE_SIGNALING_LABORATORY_BLOCK.get(), "Right-Faced Blue Signaling Laboratory Block");
        add(ModBlocks.ENLIGHTED_RIGHT_FACED_BLUE_SIGNALING_LABORATORY_BLOCK.get(), "Enlighted Right-Faced Blue Signaling Laboratory Block");
        add(ModBlocks.LEFT_FACED_GREEN_SIGNALING_LABORATORY_BLOCK.get(), "Left-Faced Green Signaling Laboratory Block");
        add(ModBlocks.ENLIGHTED_LEFT_FACED_GREEN_SIGNALING_LABORATORY_BLOCK.get(), "Enlighted Left-Faced Green Signaling Laboratory Block");
        add(ModBlocks.RIGHT_FACED_GREEN_SIGNALING_LABORATORY_BLOCK.get(), "Right-Faced Green Signaling Laboratory Block");
        add(ModBlocks.ENLIGHTED_RIGHT_FACED_GREEN_SIGNALING_LABORATORY_BLOCK.get(), "Enlighted Right-Faced Green Signaling Laboratory Block");
        add(ModBlocks.LEFT_FACED_RED_SIGNALING_LABORATORY_BLOCK.get(), "Left-Faced Red Signaling Laboratory Block");
        add(ModBlocks.ENLIGHTED_LEFT_FACED_RED_SIGNALING_LABORATORY_BLOCK.get(), "Enlighted Left-Faced Red Signaling Laboratory Block");
        add(ModBlocks.RIGHT_FACED_RED_SIGNALING_LABORATORY_BLOCK.get(), "Right-Faced Red Signaling Laboratory Block");
        add(ModBlocks.ENLIGHTED_RIGHT_FACED_RED_SIGNALING_LABORATORY_BLOCK.get(), "Enlighted Right-Faced Red Signaling Laboratory Block");
        add(ModBlocks.LABORATORY_FAN.get(), "Laboratory Fan");
        add(ModBlocks.ENLIGHTED_LABORATORY_FAN.get(), "Enlighted Laboratory Fan");
        add(ModBlocks.SCREWED_LABORATORY_BLOCK.get(), "Screwed Laboratory Block");
        add(ModBlocks.ENLIGHTED_SCREWED_LABORATORY_BLOCK.get(), "Enlighted Screwed Laboratory Block");
        add(ModBlocks.CLEAR_LABORATORY_SCREEN.get(), "Clear Laboratory Screen");
        add(ModBlocks.ENLIGHTED_CLEAR_LABORATORY_SCREEN.get(), "Enlighted Clear Laboratory Screen");
        add(ModBlocks.LABORATORY_PILLAR.get(), "Laboratory Pillar");
        add(ModBlocks.ENLIGHTED_LABORATORY_PILLAR.get(), "Enlighted Laboratory Pillar");
        add(ModBlocks.GRAY_LABORATORY_PILLAR.get(), "Gray Laboratory Pillar");
        add(ModBlocks.ENLIGHTED_GRAY_LABORATORY_PILLAR.get(), "Enlighted Gray Laboratory Pillar");
        add(ModBlocks.LABORATORY_FAN_REDSTONE_CONTROLLED.get(), "Laboratory Fan (Redstone Controlled)");
        add(ModBlocks.ENLIGHTED_LABORATORY_FAN_REDSTONE_CONTROLLED.get(), "Enlighted Laboratory Fan (Redstone Controlled)");
    }
}
