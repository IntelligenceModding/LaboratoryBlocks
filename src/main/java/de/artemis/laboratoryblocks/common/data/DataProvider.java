package de.artemis.laboratoryblocks.common.data;

import de.artemis.laboratoryblocks.LaboratoryBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = LaboratoryBlocks.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataProvider {

    @SubscribeEvent
    public static void onDataGen(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        generator.addProvider(new ModelAndBlockStateProvider(generator, existingFileHelper));
        generator.addProvider(new ItemModelProvider(generator, existingFileHelper));
        generator.addProvider(new LanguageProvider(generator, "en_us"));
        generator.addProvider(new BlockLootTablesProvider(generator));
        generator.addProvider(new RecipesProvider(generator));
        generator.addProvider(new SoundEventProvider(generator, existingFileHelper));
        generator.addProvider(new TagsProvider.BlockTagsProvider(generator, existingFileHelper));
        generator.addProvider(new TagsProvider.ItemTagsProvider(generator, existingFileHelper));

    }

    public static String getRegistryName(Item item) {
        return item.builtInRegistryHolder().key().location().toString();
    }

    public static String getRegistryName(Block block) {
        return block.builtInRegistryHolder().key().location().toString();
    }

    public static String getRawRegistryName(Item item) {
        return item.builtInRegistryHolder().key().location().getPath().toString();
    }

    public static String getRawRegistryName(Block block) {
        return block.builtInRegistryHolder().key().location().getPath().toString();
    }
}
