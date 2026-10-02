package de.artemis.laboratoryblocks;

import de.artemis.laboratoryblocks.common.registry.ModBlocks;
import de.artemis.laboratoryblocks.common.registry.ModCreativeModeTabs;
import de.artemis.laboratoryblocks.common.registry.ModItems;
import de.artemis.laboratoryblocks.common.registry.ModParticles;
import de.artemis.laboratoryblocks.common.registry.ModSoundEvents;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(LaboratoryBlocks.MOD_ID)
public class LaboratoryBlocks {
    public static final String MOD_ID = "laboratoryblocks";

    public LaboratoryBlocks(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModCreativeModeTabs.register(modEventBus);
        ModParticles.register(modEventBus);
        ModSoundEvents.register(modEventBus);
    }
}
