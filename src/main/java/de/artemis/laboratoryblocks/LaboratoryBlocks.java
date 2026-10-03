package de.artemis.laboratoryblocks;

import de.artemis.laboratoryblocks.common.registry.ModBlocks;
import de.artemis.laboratoryblocks.common.registry.ModCreativeModeTabs;
import de.artemis.laboratoryblocks.common.registry.ModItems;
import de.artemis.laboratoryblocks.common.registry.ModParticles;
import de.artemis.laboratoryblocks.common.registry.ModSoundEvents;
import net.fabricmc.api.ModInitializer;

public class LaboratoryBlocks implements ModInitializer {
    public static final String MOD_ID = "laboratoryblocks";

    @Override
    public void onInitialize() {
        ModSoundEvents.register();
        ModItems.register();
        ModBlocks.register();
        ModParticles.register();
        ModCreativeModeTabs.register();
    }
}
