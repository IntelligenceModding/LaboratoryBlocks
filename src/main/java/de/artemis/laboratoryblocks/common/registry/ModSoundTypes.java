package de.artemis.laboratoryblocks.common.registry;

import net.neoforged.neoforge.common.util.DeferredSoundType;

public class ModSoundTypes {

    public static final DeferredSoundType LABORATORY_BLOCK = new DeferredSoundType(1.0F, 1.0F,
            ModSoundEvents.LABORATORY_BLOCK_BREAK,
            ModSoundEvents.LABORATORY_BLOCK_STEP,
            ModSoundEvents.LABORATORY_BLOCK_PLACE,
            ModSoundEvents.LABORATORY_BLOCK_HIT,
            ModSoundEvents.LABORATORY_BLOCK_FALL);
}