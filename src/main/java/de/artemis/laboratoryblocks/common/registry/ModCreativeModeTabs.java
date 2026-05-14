package de.artemis.laboratoryblocks.common.registry;

import de.artemis.laboratoryblocks.LaboratoryBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

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
                    .displayItems((itemDisplayParameters, output) -> Arrays.stream(new Item[]{
                            ModItems.CONFIGURATION_TOOL.get(),
                            ModItems.IRON_SCREW.get(),
                            ModItems.GLOWSTONE_PARTICLES.get(),
                            ModBlocks.LABORATORY_BLOCK.get().asItem(),
                            ModBlocks.GLOWING_LABORATORY_BLOCK.get().asItem(),
                            ModBlocks.REINFORCED_LABORATORY_BLOCK.get().asItem(),
                            ModBlocks.GLOWING_REINFORCED_LABORATORY_BLOCK.get().asItem(),
                            ModBlocks.LABORATORY_TILES.get().asItem(),
                            ModBlocks.GLOWING_LABORATORY_TILES.get().asItem(),
                            ModBlocks.GRAY_LABORATORY_TILES.get().asItem(),
                            ModBlocks.GLOWING_GRAY_LABORATORY_TILES.get().asItem(),
                            ModBlocks.MIXED_LABORATORY_TILES.get().asItem(),
                            ModBlocks.GLOWING_MIXED_LABORATORY_TILES.get().asItem(),
                            ModBlocks.OAK_LABORATORY_FLOOR.get().asItem(),
                            ModBlocks.GLOWING_OAK_LABORATORY_FLOOR.get().asItem(),
                            ModBlocks.OAK_LABORATORY_TILES.get().asItem(),
                            ModBlocks.GLOWING_OAK_LABORATORY_TILES.get().asItem(),
                            ModBlocks.SPRUCE_LABORATORY_FLOOR.get().asItem(),
                            ModBlocks.GLOWING_SPRUCE_LABORATORY_FLOOR.get().asItem(),
                            ModBlocks.SPRUCE_LABORATORY_TILES.get().asItem(),
                            ModBlocks.GLOWING_SPRUCE_LABORATORY_TILES.get().asItem(),
                            ModBlocks.BIRCH_LABORATORY_FLOOR.get().asItem(),
                            ModBlocks.GLOWING_BIRCH_LABORATORY_FLOOR.get().asItem(),
                            ModBlocks.BIRCH_LABORATORY_TILES.get().asItem(),
                            ModBlocks.GLOWING_BIRCH_LABORATORY_TILES.get().asItem(),
                            ModBlocks.DARK_OAK_LABORATORY_FLOOR.get().asItem(),
                            ModBlocks.GLOWING_DARK_OAK_LABORATORY_FLOOR.get().asItem(),
                            ModBlocks.DARK_OAK_LABORATORY_TILES.get().asItem(),
                            ModBlocks.GLOWING_DARK_OAK_LABORATORY_TILES.get().asItem(),
                            ModBlocks.JUNGLE_LABORATORY_FLOOR.get().asItem(),
                            ModBlocks.GLOWING_JUNGLE_LABORATORY_FLOOR.get().asItem(),
                            ModBlocks.JUNGLE_LABORATORY_TILES.get().asItem(),
                            ModBlocks.GLOWING_JUNGLE_LABORATORY_TILES.get().asItem(),
                            ModBlocks.ACACIA_LABORATORY_FLOOR.get().asItem(),
                            ModBlocks.GLOWING_ACACIA_LABORATORY_FLOOR.get().asItem(),
                            ModBlocks.ACACIA_LABORATORY_TILES.get().asItem(),
                            ModBlocks.GLOWING_ACACIA_LABORATORY_TILES.get().asItem(),
                            ModBlocks.MANGROVE_LABORATORY_FLOOR.get().asItem(),
                            ModBlocks.GLOWING_MANGROVE_LABORATORY_FLOOR.get().asItem(),
                            ModBlocks.MANGROVE_LABORATORY_TILES.get().asItem(),
                            ModBlocks.GLOWING_MANGROVE_LABORATORY_TILES.get().asItem(),
                            ModBlocks.CHERRY_LABORATORY_FLOOR.get().asItem(),
                            ModBlocks.GLOWING_CHERRY_LABORATORY_FLOOR.get().asItem(),
                            ModBlocks.CHERRY_LABORATORY_TILES.get().asItem(),
                            ModBlocks.GLOWING_CHERRY_LABORATORY_TILES.get().asItem(),
                            ModBlocks.BAMBOO_LABORATORY_FLOOR.get().asItem(),
                            ModBlocks.GLOWING_BAMBOO_LABORATORY_FLOOR.get().asItem(),
                            ModBlocks.BAMBOO_LABORATORY_TILES.get().asItem(),
                            ModBlocks.GLOWING_BAMBOO_LABORATORY_TILES.get().asItem(),
                            ModBlocks.CRIMSON_LABORATORY_FLOOR.get().asItem(),
                            ModBlocks.GLOWING_CRIMSON_LABORATORY_FLOOR.get().asItem(),
                            ModBlocks.CRIMSON_LABORATORY_TILES.get().asItem(),
                            ModBlocks.GLOWING_CRIMSON_LABORATORY_TILES.get().asItem(),
                            ModBlocks.WARPED_LABORATORY_FLOOR.get().asItem(),
                            ModBlocks.GLOWING_WARPED_LABORATORY_FLOOR.get().asItem(),
                            ModBlocks.WARPED_LABORATORY_TILES.get().asItem(),
                            ModBlocks.GLOWING_WARPED_LABORATORY_TILES.get().asItem(),
                            ModBlocks.LABORATORY_GLASS.get().asItem(),
                            ModBlocks.GLOWING_LABORATORY_GLASS.get().asItem(),
                            ModBlocks.LABORATORY_VENT.get().asItem(),
                            ModBlocks.GLOWING_LABORATORY_VENT.get().asItem(),
                            ModBlocks.LEFT_INDICATING_BLUE_LABORATORY_BLOCK.get().asItem(),
                            ModBlocks.GLOWING_LEFT_INDICATING_BLUE_LABORATORY_BLOCK.get().asItem(),
                            ModBlocks.RIGHT_INDICATING_BLUE_LABORATORY_BLOCK.get().asItem(),
                            ModBlocks.GLOWING_RIGHT_INDICATING_BLUE_LABORATORY_BLOCK.get().asItem(),
                            ModBlocks.LEFT_INDICATING_RED_LABORATORY_BLOCK.get().asItem(),
                            ModBlocks.GLOWING_LEFT_INDICATING_RED_LABORATORY_BLOCK.get().asItem(),
                            ModBlocks.RIGHT_INDICATING_RED_LABORATORY_BLOCK.get().asItem(),
                            ModBlocks.GLOWING_RIGHT_INDICATING_RED_LABORATORY_BLOCK.get().asItem(),
                            ModBlocks.LEFT_INDICATING_GREEN_LABORATORY_BLOCK.get().asItem(),
                            ModBlocks.GLOWING_LEFT_INDICATING_GREEN_LABORATORY_BLOCK.get().asItem(),
                            ModBlocks.RIGHT_INDICATING_GREEN_LABORATORY_BLOCK.get().asItem(),
                            ModBlocks.GLOWING_RIGHT_INDICATING_GREEN_LABORATORY_BLOCK.get().asItem(),
                            ModBlocks.LABORATORY_FAN.get().asItem(),
                            ModBlocks.GLOWING_LABORATORY_FAN.get().asItem(),
                            ModBlocks.LABORATORY_FAN_REDSTONE_CONTROLLED.get().asItem(),
                            ModBlocks.GLOWING_LABORATORY_FAN_REDSTONE_CONTROLLED.get().asItem(),
                            ModBlocks.CLEAR_LABORATORY_SCREEN.get().asItem(),
                            ModBlocks.GLOWING_CLEAR_LABORATORY_SCREEN.get().asItem(),
                            ModBlocks.WAVE_LABORATORY_SCREEN.get().asItem(),
                            ModBlocks.GLOWING_WAVE_LABORATORY_SCREEN.get().asItem(),
                            ModBlocks.TEXT_LABORATORY_SCREEN.get().asItem(),
                            ModBlocks.GLOWING_TEXT_LABORATORY_SCREEN.get().asItem(),
                            ModBlocks.QUANTUM_LABORATORY_SCREEN.get().asItem(),
                            ModBlocks.GLOWING_QUANTUM_LABORATORY_SCREEN.get().asItem(),
                            ModBlocks.LABORATORY_PILLAR.get().asItem(),
                            ModBlocks.GLOWING_LABORATORY_PILLAR.get().asItem(),
                            ModBlocks.GRAY_LABORATORY_PILLAR.get().asItem(),
                            ModBlocks.GLOWING_GRAY_LABORATORY_PILLAR.get().asItem(),
                            ModBlocks.LABORATORY_DOOR.get().asItem(),
                            ModBlocks.MESH_LABORATORY_DOOR.get().asItem(),
                            ModBlocks.GLASS_LABORATORY_DOOR.get().asItem(),
                            ModBlocks.LABORATORY_TRAPDOOR.get().asItem(),
                            ModBlocks.MESH_LABORATORY_TRAPDOOR.get().asItem(),
                            ModBlocks.GLASS_LABORATORY_TRAPDOOR.get().asItem()
                    }).forEach(output::accept))
                    .build());

    private ModCreativeModeTabs() {
    }

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}