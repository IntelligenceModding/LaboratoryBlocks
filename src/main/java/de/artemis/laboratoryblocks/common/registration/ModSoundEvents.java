package de.artemis.laboratoryblocks.common.registration;

import de.artemis.laboratoryblocks.LaboratoryBlocks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.registries.RegistryObject;

public class ModSoundEvents {

    public static void register() {
    }

    private static RegistryObject<SoundEvent> register(String resourceLocation) {
        return Registration.SOUND_EVENTS.register(resourceLocation,  () -> new SoundEvent(new ResourceLocation(LaboratoryBlocks.MODID, resourceLocation)));
    }

    public static final RegistryObject<SoundEvent> LABORATORY_BLOCK_BREAK = register("laboratory_block_break");
    public static final RegistryObject<SoundEvent> LABORATORY_BLOCK_FALL = register("laboratory_block_fall");
    public static final RegistryObject<SoundEvent> LABORATORY_BLOCK_HIT = register("laboratory_block_hit");
    public static final RegistryObject<SoundEvent> LABORATORY_BLOCK_PLACE = register("laboratory_block_place");
    public static final RegistryObject<SoundEvent> LABORATORY_BLOCK_STEP = register("laboratory_block_step");

}
