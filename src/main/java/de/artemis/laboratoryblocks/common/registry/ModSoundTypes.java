package de.artemis.laboratoryblocks.common.registry;

import net.minecraft.world.level.block.SoundType;

public class ModSoundTypes {

    public static final SoundType LABORATORY_BLOCK = new SoundType(1.0F, 1.0F,
            ModSoundEvents.LABORATORY_BLOCK_BREAK.get(),
            ModSoundEvents.LABORATORY_BLOCK_STEP.get(),
            ModSoundEvents.LABORATORY_BLOCK_PLACE.get(),
            ModSoundEvents.LABORATORY_BLOCK_HIT.get(),
            ModSoundEvents.LABORATORY_BLOCK_FALL.get());
}
