package de.artemis.laboratoryblocks.common.util;

import de.artemis.laboratoryblocks.common.registry.ModBlocks;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.registries.DeferredBlock;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public final class LaboratoryWoodSwapUtil {

    private static final List<WoodFamily> FAMILIES = List.of(
            family(Blocks.OAK_PLANKS, ModBlocks.OAK_LABORATORY_FLOOR, ModBlocks.GLOWING_OAK_LABORATORY_FLOOR, ModBlocks.OAK_LABORATORY_TILES, ModBlocks.GLOWING_OAK_LABORATORY_TILES),
            family(Blocks.SPRUCE_PLANKS, ModBlocks.SPRUCE_LABORATORY_FLOOR, ModBlocks.GLOWING_SPRUCE_LABORATORY_FLOOR, ModBlocks.SPRUCE_LABORATORY_TILES, ModBlocks.GLOWING_SPRUCE_LABORATORY_TILES),
            family(Blocks.BIRCH_PLANKS, ModBlocks.BIRCH_LABORATORY_FLOOR, ModBlocks.GLOWING_BIRCH_LABORATORY_FLOOR, ModBlocks.BIRCH_LABORATORY_TILES, ModBlocks.GLOWING_BIRCH_LABORATORY_TILES),
            family(Blocks.DARK_OAK_PLANKS, ModBlocks.DARK_OAK_LABORATORY_FLOOR, ModBlocks.GLOWING_DARK_OAK_LABORATORY_FLOOR, ModBlocks.DARK_OAK_LABORATORY_TILES, ModBlocks.GLOWING_DARK_OAK_LABORATORY_TILES),
            family(Blocks.PALE_OAK_PLANKS, ModBlocks.PALE_OAK_LABORATORY_FLOOR, ModBlocks.GLOWING_PALE_OAK_LABORATORY_FLOOR, ModBlocks.PALE_OAK_LABORATORY_TILES, ModBlocks.GLOWING_PALE_OAK_LABORATORY_TILES),
            family(Blocks.JUNGLE_PLANKS, ModBlocks.JUNGLE_LABORATORY_FLOOR, ModBlocks.GLOWING_JUNGLE_LABORATORY_FLOOR, ModBlocks.JUNGLE_LABORATORY_TILES, ModBlocks.GLOWING_JUNGLE_LABORATORY_TILES),
            family(Blocks.ACACIA_PLANKS, ModBlocks.ACACIA_LABORATORY_FLOOR, ModBlocks.GLOWING_ACACIA_LABORATORY_FLOOR, ModBlocks.ACACIA_LABORATORY_TILES, ModBlocks.GLOWING_ACACIA_LABORATORY_TILES),
            family(Blocks.MANGROVE_PLANKS, ModBlocks.MANGROVE_LABORATORY_FLOOR, ModBlocks.GLOWING_MANGROVE_LABORATORY_FLOOR, ModBlocks.MANGROVE_LABORATORY_TILES, ModBlocks.GLOWING_MANGROVE_LABORATORY_TILES),
            family(Blocks.CHERRY_PLANKS, ModBlocks.CHERRY_LABORATORY_FLOOR, ModBlocks.GLOWING_CHERRY_LABORATORY_FLOOR, ModBlocks.CHERRY_LABORATORY_TILES, ModBlocks.GLOWING_CHERRY_LABORATORY_TILES),
            family(Blocks.BAMBOO_PLANKS, ModBlocks.BAMBOO_LABORATORY_FLOOR, ModBlocks.GLOWING_BAMBOO_LABORATORY_FLOOR, ModBlocks.BAMBOO_LABORATORY_TILES, ModBlocks.GLOWING_BAMBOO_LABORATORY_TILES),
            family(Blocks.CRIMSON_PLANKS, ModBlocks.CRIMSON_LABORATORY_FLOOR, ModBlocks.GLOWING_CRIMSON_LABORATORY_FLOOR, ModBlocks.CRIMSON_LABORATORY_TILES, ModBlocks.GLOWING_CRIMSON_LABORATORY_TILES),
            family(Blocks.WARPED_PLANKS, ModBlocks.WARPED_LABORATORY_FLOOR, ModBlocks.GLOWING_WARPED_LABORATORY_FLOOR, ModBlocks.WARPED_LABORATORY_TILES, ModBlocks.GLOWING_WARPED_LABORATORY_TILES)
    );

    private LaboratoryWoodSwapUtil() {
    }

    @Nullable
    public static SwapResult getSwap(Block currentBlock, Item heldItem) {
        WoodFamily targetFamily = findFamilyByPlank(Block.byItem(heldItem));
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
    private static WoodFamily findFamilyByPlank(Block plankBlock) {
        for (WoodFamily family : FAMILIES) {
            if (family.planks == plankBlock) {
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

    private static WoodFamily family(
            Block planks,
            DeferredBlock<? extends Block> floor,
            DeferredBlock<? extends Block> glowingFloor,
            DeferredBlock<? extends Block> tiles,
            DeferredBlock<? extends Block> glowingTiles
    ) {
        return new WoodFamily(planks, floor, glowingFloor, tiles, glowingTiles);
    }

    public record SwapResult(Block targetBlock, ItemStack returnedPlanks) {
    }

    private record WoodFamily(
            Block planks,
            DeferredBlock<? extends Block> floor,
            DeferredBlock<? extends Block> glowingFloor,
            DeferredBlock<? extends Block> tiles,
            DeferredBlock<? extends Block> glowingTiles
    ) {
    }

    private record BlockMatch(WoodFamily family, BlockKind kind, boolean glowing) {
    }

    private enum BlockKind {
        FLOOR,
        TILES
    }
}