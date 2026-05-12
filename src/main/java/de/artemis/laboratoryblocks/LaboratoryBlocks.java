package de.artemis.laboratoryblocks;

import de.artemis.laboratoryblocks.common.registry.ModBlocks;
import de.artemis.laboratoryblocks.common.registry.ModCreativeModeTabs;
import de.artemis.laboratoryblocks.common.registry.ModItems;
import de.artemis.laboratoryblocks.common.registry.ModParticles;
import de.artemis.laboratoryblocks.common.registry.ModSoundEvents;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(LaboratoryBlocks.MOD_ID)
public class LaboratoryBlocks {
    public static final String MOD_ID = "laboratoryblocks";

    public LaboratoryBlocks(IEventBus modEventBus) {
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModCreativeModeTabs.register(modEventBus);
        ModParticles.register(modEventBus);
        ModSoundEvents.register(modEventBus);
    }
}