package de.artemis.laboratoryblocks.common.registry;

import de.artemis.laboratoryblocks.LaboratoryBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;


import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final CreativeModeTab LABORATORY_BLOCKS_CREATIVE_TAB = registerTab("laboratory_blocks_creative_tab", false);
    public static final CreativeModeTab DARK_LABORATORY_BLOCKS_CREATIVE_TAB = registerTab("dark_laboratory_blocks_creative_tab", true);

    private static CreativeModeTab registerTab(String name, boolean dark) {
        return Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
                ResourceLocation.fromNamespaceAndPath(LaboratoryBlocks.MOD_ID, name), createTab(dark));
    }

    private static CreativeModeTab createTab(boolean dark) {
        return CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
                .icon(() -> (dark ? ModBlocks.DARK_QUANTUM_LABORATORY_SCREEN : ModBlocks.QUANTUM_LABORATORY_SCREEN)
                        .get().asItem().getDefaultInstance())
                .title(Component.translatable(dark ? "itemGroup.laboratoryblocks.dark" : "itemGroup.laboratoryblocks"))
                .displayItems((parameters, output) -> {
                    output.accept(ModItems.CONFIGURATION_TOOL.get());
                    output.accept(ModItems.IRON_SCREW.get());
                    output.accept(ModItems.GLOWSTONE_PARTICLES.get());
                    output.accept(ModItems.MUSIC_DISC_EXPLORATION.get());
                    output.accept(ModItems.MUSIC_DISC_PONDERING_THE_COSMOS.get());
                    ModBlockPalettes.BLOCK_ORDER.forEach(entry -> {
                        Supplier<? extends Block> block = dark ? entry.dark() : entry.original();
                        if (block != null) {
                            output.accept(block.get());
                        }
                    });
                })
                .build();
    }

    private ModCreativeModeTabs() {
    }

    public static void register() {
    }
}
