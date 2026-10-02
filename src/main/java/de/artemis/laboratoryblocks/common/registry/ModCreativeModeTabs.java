package de.artemis.laboratoryblocks.common.registry;

import de.artemis.laboratoryblocks.LaboratoryBlocks;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public final class ModCreativeModeTabs {
    public static final CreativeModeTab LABORATORY_BLOCKS_CREATIVE_TAB = new CreativeModeTab(LaboratoryBlocks.MOD_ID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModBlocks.QUANTUM_LABORATORY_SCREEN.get());
        }
    };

    private ModCreativeModeTabs() {
    }
}
