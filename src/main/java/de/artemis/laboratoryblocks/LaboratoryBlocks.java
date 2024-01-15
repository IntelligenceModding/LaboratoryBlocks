package de.artemis.laboratoryblocks;

import com.mojang.logging.LogUtils;
import de.artemis.laboratoryblocks.common.registration.Registration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

@Mod(LaboratoryBlocks.MOD_ID)
public class LaboratoryBlocks {

    public static final String MOD_ID = "laboratoryblocks";

    public LaboratoryBlocks() {
        MinecraftForge.EVENT_BUS.register(this);
        Registration.register();
    }
}