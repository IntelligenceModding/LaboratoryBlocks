package de.artemis.laboratoryblocks.common.data;

import de.artemis.laboratoryblocks.LaboratoryBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = LaboratoryBlocks.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataProvider {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(true, new LanguageProvider(generator.getPackOutput(), "en_us"));
        generator.addProvider(event.includeServer(), new LootTableProvider(packOutput, Collections.emptySet(), List.of(new LootTableProvider.SubProviderEntry(BlockLootTablesProvider::new, LootContextParamSets.BLOCK)), lookupProvider));
        generator.addProvider(event.includeServer(), new RecipesProvider(packOutput, lookupProvider));
        generator.addProvider(event.includeClient(), new ModelAndBlockStateProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), new ItemModelProvider(packOutput, existingFileHelper));

        generator.addProvider(true, new TagsProvider.BlockTagsProvider(generator.getPackOutput(), lookupProvider, existingFileHelper));
        generator.addProvider(true, new TagsProvider.ItemTagsProvider(generator.getPackOutput(), lookupProvider, existingFileHelper));

        generator.addProvider(true, new FusionModelProvider(generator.getPackOutput()));

    }

    @SuppressWarnings("deprecation")
    public static String getRegistryName(Item item) {
        return item.builtInRegistryHolder().key().location().toString();
    }

    @SuppressWarnings("deprecation")
    public static String getRegistryName(Block block) {
        return block.builtInRegistryHolder().key().location().toString();
    }

    @SuppressWarnings("deprecation")
    public static String getRawRegistryName(Item item) {
        return item.builtInRegistryHolder().key().location().getPath().toString();
    }

    @SuppressWarnings("deprecation")
    public static String getRawRegistryName(Block block) {
        return block.builtInRegistryHolder().key().location().getPath().toString();
    }


}
