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
    public void fillItemCategory(net.minecraft.world.item.CreativeModeTab tab, net.minecraft.core.NonNullList<net.minecraft.world.item.ItemStack> items) {
        super.fillItemCategory(tab, items);
        if (tab == net.minecraft.world.item.CreativeModeTab.TAB_MISC) items.add(new net.minecraft.world.item.ItemStack(this));
    }

    @Override
    public int getLengthInTicks() {
        return durationTicks;
    }
}
