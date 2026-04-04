package de.artemis.laboratoryblocks;

import de.artemis.laboratoryblocks.common.block.ModBlocks;
import de.artemis.laboratoryblocks.common.item.ModItems;
import de.artemis.laboratoryblocks.common.particle.ModParticles;
import de.artemis.laboratoryblocks.common.util.ModSoundEvents;
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
