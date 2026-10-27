package de.artemis.laboratoryblocks.common.datagen;

import de.artemis.laboratoryblocks.LaboratoryBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.data.event.GatherDataRegistryEntriesEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = LaboratoryBlocks.MOD_ID)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherRegistries(GatherDataRegistryEntriesEvent event) {
        event.lootTable(new LootTableProvider.SubProviderEntry(ModBlockLootTableProvider::new, LootContextParamSets.BLOCK))
                .recipe(ModRecipeProvider::new);
    }

    @SubscribeEvent
    public static void gatherClientData(GatherDataEvent.Client event) {
        addProviders(event.getGenerator(), event.getGenerator().getPackOutput(), event.getWorldLookupProvider());
    }

    @SubscribeEvent
    public static void gatherServerData(GatherDataEvent.Server event) {
        addProviders(event.getGenerator(), event.getGenerator().getPackOutput(), event.getWorldLookupProvider());
    }

    private static void addProviders(
            DataGenerator generator,
            PackOutput packOutput,
            CompletableFuture<HolderLookup.Provider> lookupProvider
    ) {
        BlockTagsProvider blockTagsProvider = new ModBlockTagProvider(packOutput, lookupProvider);
        generator.addProvider(true, blockTagsProvider);

        generator.addProvider(true, new ModModelProvider(packOutput));
        generator.addProvider(true, new ModFusionModelProvider(packOutput));
        generator.addProvider(true, new ModFusionTextureMetadataProvider(packOutput));
        generator.addProvider(true, new ModLanguageProvider(packOutput, "en_us"));
    }
}
