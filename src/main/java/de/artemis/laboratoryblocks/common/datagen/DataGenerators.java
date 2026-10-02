package de.artemis.laboratoryblocks.common.datagen;

import de.artemis.laboratoryblocks.LaboratoryBlocks;
import com.mojang.datafixers.util.Pair;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

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

        generator.addProvider(event.includeServer(), new LootTableProvider(generator) {
            @Override
            protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables() {
                return List.of(Pair.of(ModBlockLootTableProvider::new, LootContextParamSets.BLOCK));
            }

            @Override
            protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext context) {
            }
        });
        generator.addProvider(event.includeServer(), new ModRecipeProvider(generator));

        BlockTagsProvider blockTagsProvider = new ModBlockTagProvider(generator, event.getExistingFileHelper());
        generator.addProvider(event.includeServer(), blockTagsProvider);

        generator.addProvider(event.includeClient(), new ModModelProvider(generator));
        generator.addProvider(event.includeClient(), new ModFusionModelProvider(generator));
        generator.addProvider(event.includeClient(), new ModFusionTextureMetadataProvider(generator));
        generator.addProvider(event.includeClient(), new ModLanguageProvider(generator, "en_us"));
    }
}
