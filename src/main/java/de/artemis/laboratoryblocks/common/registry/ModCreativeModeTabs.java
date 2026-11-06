package de.artemis.laboratoryblocks.common.registry;

import de.artemis.laboratoryblocks.LaboratoryBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;

import java.util.Arrays;
import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, LaboratoryBlocks.MOD_ID);

    @SuppressWarnings("unused")
    public static final Supplier<CreativeModeTab> LABORATORY_BLOCKS_CREATIVE_TAB = CREATIVE_MODE_TAB.register("laboratory_blocks_creative_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> ModBlocks.QUANTUM_LABORATORY_SCREEN.get().asItem().getDefaultInstance())
                    .title(Component.translatable("itemGroup.laboratoryblocks"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.CONFIGURATION_TOOL.get());
                        output.accept(ModItems.IRON_SCREW.get());
                        output.accept(ModItems.GLOWSTONE_PARTICLES.get());
                        ModBlockPalettes.BLOCK_ORDER.forEach(pair -> output.accept(pair.original().get()));
                        output.accept(ModItems.MUSIC_DISC_EXPLORATION.get());
                        output.accept(ModItems.MUSIC_DISC_PONDERING_THE_COSMOS.get());
                    })
                    .build());

    public static final Supplier<CreativeModeTab> DARK_LABORATORY_BLOCKS_CREATIVE_TAB = CREATIVE_MODE_TAB.register("dark_laboratory_blocks_creative_tab",
        () -> CreativeModeTab.builder().icon(() -> ModBlocks.DARK_QUANTUM_LABORATORY_SCREEN.get().asItem().getDefaultInstance())
            .title(Component.translatable("itemGroup.laboratoryblocks.dark"))
            .displayItems((parameters, output) -> {
                output.accept(ModItems.CONFIGURATION_TOOL.get());
                output.accept(ModItems.IRON_SCREW.get());
                output.accept(ModItems.GLOWSTONE_PARTICLES.get());
                ModBlockPalettes.BLOCK_ORDER.stream().filter(pair -> pair.dark() != null).forEach(pair -> output.accept(pair.dark().get()));
                output.accept(ModItems.MUSIC_DISC_EXPLORATION.get());
                output.accept(ModItems.MUSIC_DISC_PONDERING_THE_COSMOS.get());
            }).build());

    private ModCreativeModeTabs() {
    }

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}