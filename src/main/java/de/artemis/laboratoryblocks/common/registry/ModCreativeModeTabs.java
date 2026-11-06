package de.artemis.laboratoryblocks.common.registry;

import de.artemis.laboratoryblocks.LaboratoryBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, LaboratoryBlocks.MOD_ID);

    public static final Supplier<CreativeModeTab> LABORATORY_BLOCKS_CREATIVE_TAB = CREATIVE_MODE_TAB.register(
            "laboratory_blocks_creative_tab", () -> createTab(false));

    public static final Supplier<CreativeModeTab> DARK_LABORATORY_BLOCKS_CREATIVE_TAB = CREATIVE_MODE_TAB.register(
            "dark_laboratory_blocks_creative_tab", () -> createTab(true));

    private static CreativeModeTab createTab(boolean dark) {
        return CreativeModeTab.builder()
                .icon(() -> (dark ? ModBlocks.DARK_QUANTUM_LABORATORY_SCREEN : ModBlocks.QUANTUM_LABORATORY_SCREEN)
                        .get().asItem().getDefaultInstance())
                .title(Component.translatable(dark ? "itemGroup.laboratoryblocks.dark" : "itemGroup.laboratoryblocks"))
                .displayItems((_, output) -> {
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

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
