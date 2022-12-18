package de.artemis.laboratoryblocks.common.registration;

import de.artemis.laboratoryblocks.LaboratoryBlocks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.RegistryObject;

public class ModSoundEvents {

    public static void register() {
    }

    private static RegistryObject<SoundEvent> register(String resourceLocation) {
        return Registration.SOUND_EVENTS.register(resourceLocation,  () -> new SoundEvent(new ResourceLocation(LaboratoryBlocks.MODID, resourceLocation)));
    }

    public static final RegistryObject<SoundEvent> LABORATORY_BLOCK_BREAK = register("block.laboratory_block.break");
    public static final RegistryObject<SoundEvent> LABORATORY_BLOCK_FALL = register("block.laboratory_block.fall");
    public static final RegistryObject<SoundEvent> LABORATORY_BLOCK_HIT = register("block.laboratory_block.hit");
    public static final RegistryObject<SoundEvent> LABORATORY_BLOCK_PLACE = register("block.laboratory_block.place");
    public static final RegistryObject<SoundEvent> LABORATORY_BLOCK_STEP = register("block.laboratory_block.step");

}
