package de.artemis.laboratoryblocks.common.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class DataGenerators implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(ModBlockLootTableProvider::new);
        pack.addProvider(ModRecipeProvider::new);
        pack.addProvider(ModBlockTagProvider::new);
        pack.addProvider((FabricDataOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) -> new ModModelProvider(output));
        pack.addProvider((FabricDataOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) -> new ModFusionModelProvider(output));
        pack.addProvider((FabricDataOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) -> new ModFusionTextureMetadataProvider(output));
        pack.addProvider(ModLanguageProvider::new);
    }
}
