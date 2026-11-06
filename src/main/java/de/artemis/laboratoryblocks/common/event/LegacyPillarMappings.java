package de.artemis.laboratoryblocks.common.event;
import de.artemis.laboratoryblocks.LaboratoryBlocks;
import de.artemis.laboratoryblocks.common.registry.ModBlocks;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/** Migrate saved gray pillars and items to the 1.0 dark replacements. */
@Mod.EventBusSubscriber(modid=LaboratoryBlocks.MOD_ID,bus=Mod.EventBusSubscriber.Bus.MOD)
public final class LegacyPillarMappings {
    @SubscribeEvent
    public static void blocks(RegistryEvent.MissingMappings<Block> event) {
        for (var mapping : event.getMappings(LaboratoryBlocks.MOD_ID)) {
            switch (mapping.key.getPath()) {
                case "gray_laboratory_pillar" -> mapping.remap(ModBlocks.DARK_LABORATORY_PILLAR.get());
                case "glowing_gray_laboratory_pillar" -> mapping.remap(ModBlocks.GLOWING_DARK_LABORATORY_PILLAR.get());
            }
        }
    }
    @SubscribeEvent
    public static void items(RegistryEvent.MissingMappings<Item> event) {
        for (var mapping : event.getMappings(LaboratoryBlocks.MOD_ID)) {
            switch (mapping.key.getPath()) {
                case "gray_laboratory_pillar" -> mapping.remap(ModBlocks.DARK_LABORATORY_PILLAR.get().asItem());
                case "glowing_gray_laboratory_pillar" -> mapping.remap(ModBlocks.GLOWING_DARK_LABORATORY_PILLAR.get().asItem());
            }
        }
    }
    private LegacyPillarMappings() {}
}
