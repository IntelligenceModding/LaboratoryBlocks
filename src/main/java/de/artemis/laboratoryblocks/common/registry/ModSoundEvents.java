package de.artemis.laboratoryblocks.common.registry;

import de.artemis.laboratoryblocks.LaboratoryBlocks;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class ModSoundEvents {
    public static void register() {
    }

    private static RegistrySupplier<SoundEvent> register(String name) {
        ResourceLocation id = new ResourceLocation(LaboratoryBlocks.MOD_ID, name);
        SoundEvent soundEvent = Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(id));
        return new RegistrySupplier<>(id, soundEvent);
    }

    public static final RegistrySupplier<SoundEvent> LABORATORY_BLOCK_BREAK = register("laboratory_block_break");
    public static final RegistrySupplier<SoundEvent> LABORATORY_BLOCK_FALL = register("laboratory_block_fall");
    public static final RegistrySupplier<SoundEvent> LABORATORY_BLOCK_HIT = register("laboratory_block_hit");
    public static final RegistrySupplier<SoundEvent> LABORATORY_BLOCK_PLACE = register("laboratory_block_place");
    public static final RegistrySupplier<SoundEvent> LABORATORY_BLOCK_STEP = register("laboratory_block_step");
    public static final RegistrySupplier<SoundEvent> CONFIGURATION_TOOL_USE = register("configuration_tool_use");
}
