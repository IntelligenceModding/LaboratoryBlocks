package de.artemis.laboratoryblocks.common.util;

import de.artemis.laboratoryblocks.common.registry.ModBlocks;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import de.artemis.laboratoryblocks.common.registry.RegistrySupplier;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public final class LaboratoryWoodSwapUtil {

    private static final List<WoodFamily> FAMILIES = List.of(
            family(Blocks.OAK_PLANKS, ModBlocks.OAK_LABORATORY_FLOOR, ModBlocks.GLOWING_OAK_LABORATORY_FLOOR, ModBlocks.OAK_LABORATORY_TILES, ModBlocks.GLOWING_OAK_LABORATORY_TILES),
            family(Blocks.SPRUCE_PLANKS, ModBlocks.SPRUCE_LABORATORY_FLOOR, ModBlocks.GLOWING_SPRUCE_LABORATORY_FLOOR, ModBlocks.SPRUCE_LABORATORY_TILES, ModBlocks.GLOWING_SPRUCE_LABORATORY_TILES),
            family(Blocks.POPLAR_PLANKS, ModBlocks.POPLAR_LABORATORY_FLOOR, ModBlocks.GLOWING_POPLAR_LABORATORY_FLOOR, ModBlocks.POPLAR_LABORATORY_TILES, ModBlocks.GLOWING_POPLAR_LABORATORY_TILES),
            family(Blocks.BIRCH_PLANKS, ModBlocks.BIRCH_LABORATORY_FLOOR, ModBlocks.GLOWING_BIRCH_LABORATORY_FLOOR, ModBlocks.BIRCH_LABORATORY_TILES, ModBlocks.GLOWING_BIRCH_LABORATORY_TILES),
            family(Blocks.DARK_OAK_PLANKS, ModBlocks.DARK_OAK_LABORATORY_FLOOR, ModBlocks.GLOWING_DARK_OAK_LABORATORY_FLOOR, ModBlocks.DARK_OAK_LABORATORY_TILES, ModBlocks.GLOWING_DARK_OAK_LABORATORY_TILES),
            family(Blocks.PALE_OAK_PLANKS, ModBlocks.PALE_OAK_LABORATORY_FLOOR, ModBlocks.GLOWING_PALE_OAK_LABORATORY_FLOOR, ModBlocks.PALE_OAK_LABORATORY_TILES, ModBlocks.GLOWING_PALE_OAK_LABORATORY_TILES),
            family(Blocks.JUNGLE_PLANKS, ModBlocks.JUNGLE_LABORATORY_FLOOR, ModBlocks.GLOWING_JUNGLE_LABORATORY_FLOOR, ModBlocks.JUNGLE_LABORATORY_TILES, ModBlocks.GLOWING_JUNGLE_LABORATORY_TILES),
            family(Blocks.ACACIA_PLANKS, ModBlocks.ACACIA_LABORATORY_FLOOR, ModBlocks.GLOWING_ACACIA_LABORATORY_FLOOR, ModBlocks.ACACIA_LABORATORY_TILES, ModBlocks.GLOWING_ACACIA_LABORATORY_TILES),
            family(Blocks.MANGROVE_PLANKS, ModBlocks.MANGROVE_LABORATORY_FLOOR, ModBlocks.GLOWING_MANGROVE_LABORATORY_FLOOR, ModBlocks.MANGROVE_LABORATORY_TILES, ModBlocks.GLOWING_MANGROVE_LABORATORY_TILES),
            family(Blocks.CHERRY_PLANKS, ModBlocks.CHERRY_LABORATORY_FLOOR, ModBlocks.GLOWING_CHERRY_LABORATORY_FLOOR, ModBlocks.CHERRY_LABORATORY_TILES, ModBlocks.GLOWING_CHERRY_LABORATORY_TILES),
            family(Blocks.BAMBOO_PLANKS, ModBlocks.BAMBOO_LABORATORY_FLOOR, ModBlocks.GLOWING_BAMBOO_LABORATORY_FLOOR, ModBlocks.BAMBOO_LABORATORY_TILES, ModBlocks.GLOWING_BAMBOO_LABORATORY_TILES),
            family(Blocks.CRIMSON_PLANKS, ModBlocks.CRIMSON_LABORATORY_FLOOR, ModBlocks.GLOWING_CRIMSON_LABORATORY_FLOOR, ModBlocks.CRIMSON_LABORATORY_TILES, ModBlocks.GLOWING_CRIMSON_LABORATORY_TILES),
            family(Blocks.WARPED_PLANKS, ModBlocks.WARPED_LABORATORY_FLOOR, ModBlocks.GLOWING_WARPED_LABORATORY_FLOOR, ModBlocks.WARPED_LABORATORY_TILES, ModBlocks.GLOWING_WARPED_LABORATORY_TILES),
            family(Blocks.OAK_PLANKS, ModBlocks.DARKENED_OAK_LABORATORY_FLOOR, ModBlocks.GLOWING_DARKENED_OAK_LABORATORY_FLOOR, ModBlocks.DARKENED_OAK_LABORATORY_TILES, ModBlocks.GLOWING_DARKENED_OAK_LABORATORY_TILES),
            family(Blocks.SPRUCE_PLANKS, ModBlocks.DARK_SPRUCE_LABORATORY_FLOOR, ModBlocks.GLOWING_DARK_SPRUCE_LABORATORY_FLOOR, ModBlocks.DARK_SPRUCE_LABORATORY_TILES, ModBlocks.GLOWING_DARK_SPRUCE_LABORATORY_TILES),
            family(Blocks.POPLAR_PLANKS, ModBlocks.DARK_POPLAR_LABORATORY_FLOOR, ModBlocks.GLOWING_DARK_POPLAR_LABORATORY_FLOOR, ModBlocks.DARK_POPLAR_LABORATORY_TILES, ModBlocks.GLOWING_DARK_POPLAR_LABORATORY_TILES),
            family(Blocks.BIRCH_PLANKS, ModBlocks.DARK_BIRCH_LABORATORY_FLOOR, ModBlocks.GLOWING_DARK_BIRCH_LABORATORY_FLOOR, ModBlocks.DARK_BIRCH_LABORATORY_TILES, ModBlocks.GLOWING_DARK_BIRCH_LABORATORY_TILES),
            family(Blocks.DARK_OAK_PLANKS, ModBlocks.DARK_DARK_OAK_LABORATORY_FLOOR, ModBlocks.GLOWING_DARK_DARK_OAK_LABORATORY_FLOOR, ModBlocks.DARK_DARK_OAK_LABORATORY_TILES, ModBlocks.GLOWING_DARK_DARK_OAK_LABORATORY_TILES),
            family(Blocks.PALE_OAK_PLANKS, ModBlocks.DARK_PALE_OAK_LABORATORY_FLOOR, ModBlocks.GLOWING_DARK_PALE_OAK_LABORATORY_FLOOR, ModBlocks.DARK_PALE_OAK_LABORATORY_TILES, ModBlocks.GLOWING_DARK_PALE_OAK_LABORATORY_TILES),
            family(Blocks.JUNGLE_PLANKS, ModBlocks.DARK_JUNGLE_LABORATORY_FLOOR, ModBlocks.GLOWING_DARK_JUNGLE_LABORATORY_FLOOR, ModBlocks.DARK_JUNGLE_LABORATORY_TILES, ModBlocks.GLOWING_DARK_JUNGLE_LABORATORY_TILES),
            family(Blocks.ACACIA_PLANKS, ModBlocks.DARK_ACACIA_LABORATORY_FLOOR, ModBlocks.GLOWING_DARK_ACACIA_LABORATORY_FLOOR, ModBlocks.DARK_ACACIA_LABORATORY_TILES, ModBlocks.GLOWING_DARK_ACACIA_LABORATORY_TILES),
            family(Blocks.MANGROVE_PLANKS, ModBlocks.DARK_MANGROVE_LABORATORY_FLOOR, ModBlocks.GLOWING_DARK_MANGROVE_LABORATORY_FLOOR, ModBlocks.DARK_MANGROVE_LABORATORY_TILES, ModBlocks.GLOWING_DARK_MANGROVE_LABORATORY_TILES),
            family(Blocks.CHERRY_PLANKS, ModBlocks.DARK_CHERRY_LABORATORY_FLOOR, ModBlocks.GLOWING_DARK_CHERRY_LABORATORY_FLOOR, ModBlocks.DARK_CHERRY_LABORATORY_TILES, ModBlocks.GLOWING_DARK_CHERRY_LABORATORY_TILES),
            family(Blocks.BAMBOO_PLANKS, ModBlocks.DARK_BAMBOO_LABORATORY_FLOOR, ModBlocks.GLOWING_DARK_BAMBOO_LABORATORY_FLOOR, ModBlocks.DARK_BAMBOO_LABORATORY_TILES, ModBlocks.GLOWING_DARK_BAMBOO_LABORATORY_TILES),
            family(Blocks.CRIMSON_PLANKS, ModBlocks.DARK_CRIMSON_LABORATORY_FLOOR, ModBlocks.GLOWING_DARK_CRIMSON_LABORATORY_FLOOR, ModBlocks.DARK_CRIMSON_LABORATORY_TILES, ModBlocks.GLOWING_DARK_CRIMSON_LABORATORY_TILES),
            family(Blocks.WARPED_PLANKS, ModBlocks.DARK_WARPED_LABORATORY_FLOOR, ModBlocks.GLOWING_DARK_WARPED_LABORATORY_FLOOR, ModBlocks.DARK_WARPED_LABORATORY_TILES, ModBlocks.GLOWING_DARK_WARPED_LABORATORY_TILES)
    );

    private LaboratoryWoodSwapUtil() {
    }

    @Nullable
    public static SwapResult getSwap(Block currentBlock, Item heldItem) {
        WoodFamily targetFamily = findFamilyByPlank(Block.byItem(heldItem), currentBlock);
        if (targetFamily == null) {
            return null;
        }

        BlockMatch currentMatch = findBlockMatch(currentBlock);
        if (currentMatch == null || currentMatch.family == targetFamily) {
            return null;
        }

        Block targetBlock = switch (currentMatch.kind) {
            case FLOOR -> currentMatch.glowing ? targetFamily.glowingFloor.get() : targetFamily.floor.get();
            case TILES -> currentMatch.glowing ? targetFamily.glowingTiles.get() : targetFamily.tiles.get();
        };

        return new SwapResult(targetBlock, new ItemStack(currentMatch.family.planks));
    }

    @Nullable
    private static WoodFamily findFamilyByPlank(Block plankBlock, Block currentBlock) {
        for (WoodFamily family : FAMILIES) {
            if (family.planks == plankBlock && isDark(family.floor.get()) == isDark(currentBlock)) {
                return family;
            }
        }
        return null;
    }

    @Nullable
    private static BlockMatch findBlockMatch(Block block) {
        for (WoodFamily family : FAMILIES) {
            if (family.floor.get() == block) {
                return new BlockMatch(family, BlockKind.FLOOR, false);
            }
            if (family.glowingFloor.get() == block) {
                return new BlockMatch(family, BlockKind.FLOOR, true);
            }
            if (family.tiles.get() == block) {
                return new BlockMatch(family, BlockKind.TILES, false);
            }
            if (family.glowingTiles.get() == block) {
                return new BlockMatch(family, BlockKind.TILES, true);
            }
        }
        return null;
    }

    private static boolean isDark(Block block) {
        String path = net.minecraft.core.registries.BuiltInRegistries.BLOCK.getKey(block)
                .getPath().replaceFirst("^glowing_", "");
        return path.startsWith("darkened_oak_") ||
                (path.startsWith("dark_") && !path.startsWith("dark_oak_"));
    }

    private static WoodFamily family(
            Block planks,
            RegistrySupplier<? extends Block> floor,
            RegistrySupplier<? extends Block> glowingFloor,
            RegistrySupplier<? extends Block> tiles,
            RegistrySupplier<? extends Block> glowingTiles
    ) {
        return new WoodFamily(planks, floor, glowingFloor, tiles, glowingTiles);
    }

    public record SwapResult(Block targetBlock, ItemStack returnedPlanks) {
    }

    private record WoodFamily(
            Block planks,
            RegistrySupplier<? extends Block> floor,
            RegistrySupplier<? extends Block> glowingFloor,
            RegistrySupplier<? extends Block> tiles,
            RegistrySupplier<? extends Block> glowingTiles
    ) {
    }

    private record BlockMatch(WoodFamily family, BlockKind kind, boolean glowing) {
    }

    private enum BlockKind {
        FLOOR,
        TILES
    }
}
