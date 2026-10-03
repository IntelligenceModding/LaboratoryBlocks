package de.artemis.laboratoryblocks.common.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class DataGenerators implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        fabricDataGenerator.addProvider(ModBlockLootTableProvider::new);
        fabricDataGenerator.addProvider(ModRecipeProvider::new);
        fabricDataGenerator.addProvider(ModBlockTagProvider::new);
        fabricDataGenerator.addProvider(ModModelProvider::new);
        fabricDataGenerator.addProvider(ModFusionModelProvider::new);
        fabricDataGenerator.addProvider(ModFusionTextureMetadataProvider::new);
        fabricDataGenerator.addProvider(ModLanguageProvider::new);
    }
}
