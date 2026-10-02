package de.artemis.laboratoryblocks.common.registry;

import de.artemis.laboratoryblocks.LaboratoryBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public final class ModCreativeModeTabs {
    public static final ItemGroup LABORATORY_BLOCKS_CREATIVE_TAB = new ItemGroup(LaboratoryBlocks.MOD_ID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModBlocks.QUANTUM_LABORATORY_SCREEN.get());
        }
    };

    private ModCreativeModeTabs() {
    }
}
