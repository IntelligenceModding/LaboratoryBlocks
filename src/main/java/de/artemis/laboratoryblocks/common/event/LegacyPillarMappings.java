package de.artemis.laboratoryblocks.common.event;

import de.artemis.laboratoryblocks.LaboratoryBlocks;
import de.artemis.laboratoryblocks.common.registry.ModBlocks;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.MissingMappingsEvent;

/** Migrate saved gray pillars to their 1.0 dark replacements. */
@Mod.EventBusSubscriber(modid = LaboratoryBlocks.MOD_ID)
public final class LegacyPillarMappings {
    @SubscribeEvent
    public static void remap(MissingMappingsEvent event) {
        for (var mapping : event.getMappings(ForgeRegistries.Keys.BLOCKS, LaboratoryBlocks.MOD_ID)) {
            switch (mapping.getKey().getPath()) {
                case "gray_laboratory_pillar" -> mapping.remap(ModBlocks.DARK_LABORATORY_PILLAR.get());
                case "glowing_gray_laboratory_pillar" -> mapping.remap(ModBlocks.GLOWING_DARK_LABORATORY_PILLAR.get());
            }
        }
        for (var mapping : event.getMappings(ForgeRegistries.Keys.ITEMS, LaboratoryBlocks.MOD_ID)) {
            switch (mapping.getKey().getPath()) {
                case "gray_laboratory_pillar" -> mapping.remap(ModBlocks.DARK_LABORATORY_PILLAR.get().asItem());
                case "glowing_gray_laboratory_pillar" -> mapping.remap(ModBlocks.GLOWING_DARK_LABORATORY_PILLAR.get().asItem());
            }
        }
    }
    private LegacyPillarMappings() {}
}
