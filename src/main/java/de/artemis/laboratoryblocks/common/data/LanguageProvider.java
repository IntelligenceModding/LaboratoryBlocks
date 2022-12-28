package de.artemis.laboratoryblocks.common.data;

import de.artemis.laboratoryblocks.LaboratoryBlocks;
import de.artemis.laboratoryblocks.common.registration.ModBlocks;
import net.minecraft.data.DataGenerator;

public class LanguageProvider extends net.minecraftforge.common.data.LanguageProvider {
    public LanguageProvider(DataGenerator gen, String locale) {
        super(gen, LaboratoryBlocks.MOD_ID, locale);
    }

    @Override
    protected void addTranslations() {
        add("itemGroup.laboratoryblocks", "Artemis' Laboratory Blocks");

        add(ModBlocks.LABORATORY_BLOCK.get(), "Laboratory Block");
        add(ModBlocks.ENLIGHTED_LABORATORY_BLOCK.get(), "Enlighted Laboratory Block");
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
        add(ModBlocks.LABORATORY_GLASS.get(), "Laboratory Glass");
        add(ModBlocks.ENLIGHTED_LABORATORY_GLASS.get(), "Enlighted LaboratoryGlass");
    }
}
