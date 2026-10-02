package de.artemis.laboratoryblocks.common.registry;

import de.artemis.laboratoryblocks.LaboratoryBlocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.RegistryObject;

public class ModSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, LaboratoryBlocks.MOD_ID);

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }

    private static RegistryObject<SoundEvent> register(String resourceLocation) {
        return SOUND_EVENTS.register(resourceLocation, () -> new SoundEvent(new ResourceLocation(LaboratoryBlocks.MOD_ID, resourceLocation)));
    }

    public static final RegistryObject<SoundEvent> LABORATORY_BLOCK_BREAK = register("laboratory_block_break");
    public static final RegistryObject<SoundEvent> LABORATORY_BLOCK_FALL = register("laboratory_block_fall");
    public static final RegistryObject<SoundEvent> LABORATORY_BLOCK_HIT = register("laboratory_block_hit");
    public static final RegistryObject<SoundEvent> LABORATORY_BLOCK_PLACE = register("laboratory_block_place");
    public static final RegistryObject<SoundEvent> LABORATORY_BLOCK_STEP = register("laboratory_block_step");
    public static final RegistryObject<SoundEvent> CONFIGURATION_TOOL_USE = register("configuration_tool_use");
}
