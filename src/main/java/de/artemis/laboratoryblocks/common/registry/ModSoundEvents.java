package de.artemis.laboratoryblocks.common.registry;

import de.artemis.laboratoryblocks.LaboratoryBlocks;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(Registries.SOUND_EVENT, LaboratoryBlocks.MOD_ID);

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }

    private static DeferredHolder<SoundEvent, SoundEvent> register(String resourceLocation) {
        return SOUND_EVENTS.register(resourceLocation, () -> SoundEvent.createVariableRangeEvent(Identifier.tryBuild(LaboratoryBlocks.MOD_ID, resourceLocation)));
    }

    public static final DeferredHolder<SoundEvent, SoundEvent> LABORATORY_BLOCK_BREAK = register("laboratory_block_break");
    public static final DeferredHolder<SoundEvent, SoundEvent> LABORATORY_BLOCK_FALL = register("laboratory_block_fall");
    public static final DeferredHolder<SoundEvent, SoundEvent> LABORATORY_BLOCK_HIT = register("laboratory_block_hit");
    public static final DeferredHolder<SoundEvent, SoundEvent> LABORATORY_BLOCK_PLACE = register("laboratory_block_place");
    public static final DeferredHolder<SoundEvent, SoundEvent> LABORATORY_BLOCK_STEP = register("laboratory_block_step");
    public static final DeferredHolder<SoundEvent, SoundEvent> CONFIGURATION_TOOL_USE = register("configuration_tool_use");

}