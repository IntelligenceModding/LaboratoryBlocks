package de.artemis.laboratoryblocks.common.registry;

import de.artemis.laboratoryblocks.LaboratoryBlocks;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public final class ModCreativeModeTabs {
    public static final CreativeModeTab LABORATORY_BLOCKS_CREATIVE_TAB = FabricItemGroupBuilder
            .create(new ResourceLocation(LaboratoryBlocks.MOD_ID, "laboratory_blocks_creative_tab"))
            .icon(() -> new ItemStack(ModBlocks.QUANTUM_LABORATORY_SCREEN.get()))
            .build();

    private ModCreativeModeTabs() {
    }

    public static void register() {
    }
}
