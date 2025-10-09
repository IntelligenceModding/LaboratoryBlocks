package de.artemis.laboratoryblocks;

import de.artemis.laboratoryblocks.common.registration.Registration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(LaboratoryBlocks.MOD_ID)
public class LaboratoryBlocks {

    public static final String MOD_ID = "laboratoryblocks";

    public LaboratoryBlocks() {
        MinecraftForge.EVENT_BUS.register(this);
        Registration.register();
    }

    private void commonSetup(final FMLCommonSetupEvent event)  {

    }
}