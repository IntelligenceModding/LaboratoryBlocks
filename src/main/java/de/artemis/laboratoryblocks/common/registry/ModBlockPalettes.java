package de.artemis.laboratoryblocks.common.registry;

import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.util.function.Supplier;
import net.minecraft.block.Block;

public final class ModBlockPalettes {
    // Both tabs use the same order. Gray tiles and mixed tiles have no black counterpart.
    public static final List<BlockPair> BLOCK_ORDER = Collections.unmodifiableList(Arrays.asList(
            new BlockPair(ModBlocks.LABORATORY_BLOCK, ModBlocks.DARK_LABORATORY_BLOCK),
            new BlockPair(ModBlocks.GLOWING_LABORATORY_BLOCK, ModBlocks.GLOWING_DARK_LABORATORY_BLOCK),
            new BlockPair(ModBlocks.REINFORCED_LABORATORY_BLOCK, ModBlocks.DARK_REINFORCED_LABORATORY_BLOCK),
            new BlockPair(ModBlocks.GLOWING_REINFORCED_LABORATORY_BLOCK, ModBlocks.GLOWING_DARK_REINFORCED_LABORATORY_BLOCK),
            new BlockPair(ModBlocks.LABORATORY_TILES, ModBlocks.DARK_LABORATORY_TILES),
            new BlockPair(ModBlocks.GLOWING_LABORATORY_TILES, ModBlocks.GLOWING_DARK_LABORATORY_TILES),
            new BlockPair(ModBlocks.GRAY_LABORATORY_TILES, null),
            new BlockPair(ModBlocks.GLOWING_GRAY_LABORATORY_TILES, null),
            new BlockPair(ModBlocks.MIXED_LABORATORY_TILES, null),
            new BlockPair(ModBlocks.GLOWING_MIXED_LABORATORY_TILES, null),
            new BlockPair(ModBlocks.OAK_LABORATORY_FLOOR, ModBlocks.DARKENED_OAK_LABORATORY_FLOOR),
            new BlockPair(ModBlocks.GLOWING_OAK_LABORATORY_FLOOR, ModBlocks.GLOWING_DARKENED_OAK_LABORATORY_FLOOR),
            new BlockPair(ModBlocks.OAK_LABORATORY_TILES, ModBlocks.DARKENED_OAK_LABORATORY_TILES),
            new BlockPair(ModBlocks.GLOWING_OAK_LABORATORY_TILES, ModBlocks.GLOWING_DARKENED_OAK_LABORATORY_TILES),
            new BlockPair(ModBlocks.SPRUCE_LABORATORY_FLOOR, ModBlocks.DARK_SPRUCE_LABORATORY_FLOOR),
            new BlockPair(ModBlocks.GLOWING_SPRUCE_LABORATORY_FLOOR, ModBlocks.GLOWING_DARK_SPRUCE_LABORATORY_FLOOR),
            new BlockPair(ModBlocks.SPRUCE_LABORATORY_TILES, ModBlocks.DARK_SPRUCE_LABORATORY_TILES),
            new BlockPair(ModBlocks.GLOWING_SPRUCE_LABORATORY_TILES, ModBlocks.GLOWING_DARK_SPRUCE_LABORATORY_TILES),
            new BlockPair(ModBlocks.BIRCH_LABORATORY_FLOOR, ModBlocks.DARK_BIRCH_LABORATORY_FLOOR),
            new BlockPair(ModBlocks.GLOWING_BIRCH_LABORATORY_FLOOR, ModBlocks.GLOWING_DARK_BIRCH_LABORATORY_FLOOR),
            new BlockPair(ModBlocks.BIRCH_LABORATORY_TILES, ModBlocks.DARK_BIRCH_LABORATORY_TILES),
            new BlockPair(ModBlocks.GLOWING_BIRCH_LABORATORY_TILES, ModBlocks.GLOWING_DARK_BIRCH_LABORATORY_TILES),
            new BlockPair(ModBlocks.DARK_OAK_LABORATORY_FLOOR, ModBlocks.DARK_DARK_OAK_LABORATORY_FLOOR),
            new BlockPair(ModBlocks.GLOWING_DARK_OAK_LABORATORY_FLOOR, ModBlocks.GLOWING_DARK_DARK_OAK_LABORATORY_FLOOR),
            new BlockPair(ModBlocks.DARK_OAK_LABORATORY_TILES, ModBlocks.DARK_DARK_OAK_LABORATORY_TILES),
            new BlockPair(ModBlocks.GLOWING_DARK_OAK_LABORATORY_TILES, ModBlocks.GLOWING_DARK_DARK_OAK_LABORATORY_TILES),
            new BlockPair(ModBlocks.JUNGLE_LABORATORY_FLOOR, ModBlocks.DARK_JUNGLE_LABORATORY_FLOOR),
            new BlockPair(ModBlocks.GLOWING_JUNGLE_LABORATORY_FLOOR, ModBlocks.GLOWING_DARK_JUNGLE_LABORATORY_FLOOR),
            new BlockPair(ModBlocks.JUNGLE_LABORATORY_TILES, ModBlocks.DARK_JUNGLE_LABORATORY_TILES),
            new BlockPair(ModBlocks.GLOWING_JUNGLE_LABORATORY_TILES, ModBlocks.GLOWING_DARK_JUNGLE_LABORATORY_TILES),
            new BlockPair(ModBlocks.ACACIA_LABORATORY_FLOOR, ModBlocks.DARK_ACACIA_LABORATORY_FLOOR),
            new BlockPair(ModBlocks.GLOWING_ACACIA_LABORATORY_FLOOR, ModBlocks.GLOWING_DARK_ACACIA_LABORATORY_FLOOR),
            new BlockPair(ModBlocks.ACACIA_LABORATORY_TILES, ModBlocks.DARK_ACACIA_LABORATORY_TILES),
            new BlockPair(ModBlocks.GLOWING_ACACIA_LABORATORY_TILES, ModBlocks.GLOWING_DARK_ACACIA_LABORATORY_TILES),
            new BlockPair(ModBlocks.CRIMSON_LABORATORY_FLOOR, ModBlocks.DARK_CRIMSON_LABORATORY_FLOOR),
            new BlockPair(ModBlocks.GLOWING_CRIMSON_LABORATORY_FLOOR, ModBlocks.GLOWING_DARK_CRIMSON_LABORATORY_FLOOR),
            new BlockPair(ModBlocks.CRIMSON_LABORATORY_TILES, ModBlocks.DARK_CRIMSON_LABORATORY_TILES),
            new BlockPair(ModBlocks.GLOWING_CRIMSON_LABORATORY_TILES, ModBlocks.GLOWING_DARK_CRIMSON_LABORATORY_TILES),
            new BlockPair(ModBlocks.WARPED_LABORATORY_FLOOR, ModBlocks.DARK_WARPED_LABORATORY_FLOOR),
            new BlockPair(ModBlocks.GLOWING_WARPED_LABORATORY_FLOOR, ModBlocks.GLOWING_DARK_WARPED_LABORATORY_FLOOR),
            new BlockPair(ModBlocks.WARPED_LABORATORY_TILES, ModBlocks.DARK_WARPED_LABORATORY_TILES),
            new BlockPair(ModBlocks.GLOWING_WARPED_LABORATORY_TILES, ModBlocks.GLOWING_DARK_WARPED_LABORATORY_TILES),
            new BlockPair(ModBlocks.LABORATORY_GLASS, ModBlocks.DARK_LABORATORY_GLASS),
            new BlockPair(ModBlocks.GLOWING_LABORATORY_GLASS, ModBlocks.GLOWING_DARK_LABORATORY_GLASS),
            new BlockPair(ModBlocks.LABORATORY_VENT, ModBlocks.DARK_LABORATORY_VENT),
            new BlockPair(ModBlocks.GLOWING_LABORATORY_VENT, ModBlocks.GLOWING_DARK_LABORATORY_VENT),
            new BlockPair(ModBlocks.LEFT_INDICATING_BLUE_LABORATORY_BLOCK, ModBlocks.DARK_LEFT_INDICATING_BLUE_LABORATORY_BLOCK),
            new BlockPair(ModBlocks.GLOWING_LEFT_INDICATING_BLUE_LABORATORY_BLOCK, ModBlocks.GLOWING_DARK_LEFT_INDICATING_BLUE_LABORATORY_BLOCK),
            new BlockPair(ModBlocks.RIGHT_INDICATING_BLUE_LABORATORY_BLOCK, ModBlocks.DARK_RIGHT_INDICATING_BLUE_LABORATORY_BLOCK),
            new BlockPair(ModBlocks.GLOWING_RIGHT_INDICATING_BLUE_LABORATORY_BLOCK, ModBlocks.GLOWING_DARK_RIGHT_INDICATING_BLUE_LABORATORY_BLOCK),
            new BlockPair(ModBlocks.LEFT_INDICATING_RED_LABORATORY_BLOCK, ModBlocks.DARK_LEFT_INDICATING_RED_LABORATORY_BLOCK),
            new BlockPair(ModBlocks.GLOWING_LEFT_INDICATING_RED_LABORATORY_BLOCK, ModBlocks.GLOWING_DARK_LEFT_INDICATING_RED_LABORATORY_BLOCK),
            new BlockPair(ModBlocks.RIGHT_INDICATING_RED_LABORATORY_BLOCK, ModBlocks.DARK_RIGHT_INDICATING_RED_LABORATORY_BLOCK),
            new BlockPair(ModBlocks.GLOWING_RIGHT_INDICATING_RED_LABORATORY_BLOCK, ModBlocks.GLOWING_DARK_RIGHT_INDICATING_RED_LABORATORY_BLOCK),
            new BlockPair(ModBlocks.LEFT_INDICATING_GREEN_LABORATORY_BLOCK, ModBlocks.DARK_LEFT_INDICATING_GREEN_LABORATORY_BLOCK),
            new BlockPair(ModBlocks.GLOWING_LEFT_INDICATING_GREEN_LABORATORY_BLOCK, ModBlocks.GLOWING_DARK_LEFT_INDICATING_GREEN_LABORATORY_BLOCK),
            new BlockPair(ModBlocks.RIGHT_INDICATING_GREEN_LABORATORY_BLOCK, ModBlocks.DARK_RIGHT_INDICATING_GREEN_LABORATORY_BLOCK),
            new BlockPair(ModBlocks.GLOWING_RIGHT_INDICATING_GREEN_LABORATORY_BLOCK, ModBlocks.GLOWING_DARK_RIGHT_INDICATING_GREEN_LABORATORY_BLOCK),
            new BlockPair(ModBlocks.LABORATORY_FAN, ModBlocks.DARK_LABORATORY_FAN),
            new BlockPair(ModBlocks.GLOWING_LABORATORY_FAN, ModBlocks.GLOWING_DARK_LABORATORY_FAN),
            new BlockPair(ModBlocks.LABORATORY_FAN_REDSTONE_CONTROLLED, ModBlocks.DARK_LABORATORY_FAN_REDSTONE_CONTROLLED),
            new BlockPair(ModBlocks.GLOWING_LABORATORY_FAN_REDSTONE_CONTROLLED, ModBlocks.GLOWING_DARK_LABORATORY_FAN_REDSTONE_CONTROLLED),
            new BlockPair(ModBlocks.CLEAR_LABORATORY_SCREEN, ModBlocks.DARK_CLEAR_LABORATORY_SCREEN),
            new BlockPair(ModBlocks.GLOWING_CLEAR_LABORATORY_SCREEN, ModBlocks.GLOWING_DARK_CLEAR_LABORATORY_SCREEN),
            new BlockPair(ModBlocks.WAVE_LABORATORY_SCREEN, ModBlocks.DARK_WAVE_LABORATORY_SCREEN),
            new BlockPair(ModBlocks.GLOWING_WAVE_LABORATORY_SCREEN, ModBlocks.GLOWING_DARK_WAVE_LABORATORY_SCREEN),
            new BlockPair(ModBlocks.TEXT_LABORATORY_SCREEN, ModBlocks.DARK_TEXT_LABORATORY_SCREEN),
            new BlockPair(ModBlocks.GLOWING_TEXT_LABORATORY_SCREEN, ModBlocks.GLOWING_DARK_TEXT_LABORATORY_SCREEN),
            new BlockPair(ModBlocks.QUANTUM_LABORATORY_SCREEN, ModBlocks.DARK_QUANTUM_LABORATORY_SCREEN),
            new BlockPair(ModBlocks.GLOWING_QUANTUM_LABORATORY_SCREEN, ModBlocks.GLOWING_DARK_QUANTUM_LABORATORY_SCREEN),
            new BlockPair(ModBlocks.LABORATORY_PILLAR, ModBlocks.DARK_LABORATORY_PILLAR),
            new BlockPair(ModBlocks.GLOWING_LABORATORY_PILLAR, ModBlocks.GLOWING_DARK_LABORATORY_PILLAR),
            new BlockPair(ModBlocks.LABORATORY_DOOR, ModBlocks.DARK_LABORATORY_DOOR),
            new BlockPair(ModBlocks.MESH_LABORATORY_DOOR, ModBlocks.DARK_MESH_LABORATORY_DOOR),
            new BlockPair(ModBlocks.GLASS_LABORATORY_DOOR, ModBlocks.DARK_GLASS_LABORATORY_DOOR),
            new BlockPair(ModBlocks.LABORATORY_TRAPDOOR, ModBlocks.DARK_LABORATORY_TRAPDOOR),
            new BlockPair(ModBlocks.MESH_LABORATORY_TRAPDOOR, ModBlocks.DARK_MESH_LABORATORY_TRAPDOOR),
            new BlockPair(ModBlocks.GLASS_LABORATORY_TRAPDOOR, ModBlocks.DARK_GLASS_LABORATORY_TRAPDOOR)
    ));

    public static final class BlockPair {
        private final Supplier<? extends Block> original, dark;
        private BlockPair(Supplier<? extends Block> original, Supplier<? extends Block> dark) { this.original=original; this.dark=dark; }
        public Supplier<? extends Block> original(){return original;}
        public Supplier<? extends Block> dark(){return dark;}
    }

    public static Block darkVariant(Block original) {
        return BLOCK_ORDER.stream().filter(pair -> pair.original().get() == original)
                .map(BlockPair::dark).filter(java.util.Objects::nonNull)
                .findFirst().orElseThrow(() -> new IllegalArgumentException("No dark variant for " + original)).get();
    }

    public static boolean isDark(Block block) {
        return BLOCK_ORDER.stream().anyMatch(pair -> pair.dark() != null && pair.dark().get() == block);
    }

    private ModBlockPalettes() {
    }
}
