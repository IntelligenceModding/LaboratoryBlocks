package de.artemis.laboratoryblocks.common.item;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.RecordItem;

public class LaboratoryMusicDiscItem extends RecordItem {
    private final int durationTicks;

    public LaboratoryMusicDiscItem(int comparatorOutput, SoundEvent sound, Properties properties, int durationTicks) {
        super(comparatorOutput, sound, properties, (durationTicks + 19) / 20);
        this.durationTicks = durationTicks;
    }

    @Override
    public int getLengthInTicks() {
        return durationTicks;
    }
}
