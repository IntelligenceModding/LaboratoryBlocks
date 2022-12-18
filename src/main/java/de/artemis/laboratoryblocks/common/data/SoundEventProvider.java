package de.artemis.laboratoryblocks.common.data;

import de.artemis.laboratoryblocks.LaboratoryBlocks;
import de.artemis.laboratoryblocks.common.registration.ModSoundEvents;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinitionsProvider;

public class SoundEventProvider extends SoundDefinitionsProvider {
    protected SoundEventProvider(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, LaboratoryBlocks.MODID, helper);
    }

    @Override
    public void registerSounds() {
        sound(ModSoundEvents.LABORATORY_BLOCK_BREAK.get().getLocation().toString());
        sound(ModSoundEvents.LABORATORY_BLOCK_FALL.get().getLocation().toString());
        sound(ModSoundEvents.LABORATORY_BLOCK_HIT.get().getLocation().toString());
        sound(ModSoundEvents.LABORATORY_BLOCK_PLACE.get().getLocation().toString());
        sound(ModSoundEvents.LABORATORY_BLOCK_STEP.get().getLocation().toString());
    }
}
