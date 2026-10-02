package de.artemis.laboratoryblocks.common.datagen;

import de.artemis.laboratoryblocks.LaboratoryBlocks;
import com.mojang.datafixers.util.Pair;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.LootTableProvider;
import net.minecraft.loot.LootParameterSet;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.ValidationTracker;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Collections;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = LaboratoryBlocks.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();

        if (event.includeServer()) {
            generator.addProvider(new LootTableProvider(generator) {
                @Override
                protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> getTables() {
                    return Collections.singletonList(Pair.of(ModBlockLootTableProvider::new, LootParameterSets.BLOCK));
                }

                @Override
                protected void validate(Map<ResourceLocation, LootTable> map, ValidationTracker context) {
                }
            });
            generator.addProvider(new ModRecipeProvider(generator));

            BlockTagsProvider blockTagsProvider = new ModBlockTagProvider(generator, event.getExistingFileHelper());
            generator.addProvider(blockTagsProvider);
        }

        if (event.includeClient()) {
            generator.addProvider(new ModModelProvider(generator));
            generator.addProvider(new ModFusionModelProvider(generator));
            generator.addProvider(new ModFusionTextureMetadataProvider(generator));
            generator.addProvider(new ModLanguageProvider(generator, "en_us"));
        }
    }
}
