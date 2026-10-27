package de.artemis.laboratoryblocks.common.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.advancements.Advancement;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.data.recipes.RecipeProvider;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class DataGenerators implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(ModBlockLootTableProvider::new);
        pack.addProvider((FabricPackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) -> new FabricRecipeProvider(output, lookupProvider) {
            @Override
            protected @NotNull RecipeProvider createRecipeProvider(HolderLookup.@NotNull Provider provider, @NotNull BootstrapContext<Recipe<?>> recipes, @NotNull BootstrapContext<Advancement> advancements) {
                return new ModRecipeProvider(provider, recipes, advancements);
            }

            @Override
            public @NotNull String getName() {
                return "Laboratory Blocks Recipes";
            }
        });
        pack.addProvider(ModBlockTagProvider::new);
        pack.addProvider(ModItemTagProvider::new);
        pack.addProvider((FabricPackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) -> new ModModelProvider(output));
        pack.addProvider((FabricPackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) -> new ModFusionModelProvider(output));
        pack.addProvider((FabricPackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) -> new ModFusionTextureMetadataProvider(output));
        pack.addProvider(ModLanguageProvider::new);
    }
}
