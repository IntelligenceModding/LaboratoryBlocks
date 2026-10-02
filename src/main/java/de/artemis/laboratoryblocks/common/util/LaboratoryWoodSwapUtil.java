package de.artemis.laboratoryblocks.common.util;

import de.artemis.laboratoryblocks.common.registry.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public final class LaboratoryWoodSwapUtil {

    private static final List<WoodFamily> FAMILIES = Arrays.asList(
            family(Blocks.OAK_PLANKS, ModBlocks.OAK_LABORATORY_FLOOR, ModBlocks.GLOWING_OAK_LABORATORY_FLOOR, ModBlocks.OAK_LABORATORY_TILES, ModBlocks.GLOWING_OAK_LABORATORY_TILES),
            family(Blocks.SPRUCE_PLANKS, ModBlocks.SPRUCE_LABORATORY_FLOOR, ModBlocks.GLOWING_SPRUCE_LABORATORY_FLOOR, ModBlocks.SPRUCE_LABORATORY_TILES, ModBlocks.GLOWING_SPRUCE_LABORATORY_TILES),
            family(Blocks.BIRCH_PLANKS, ModBlocks.BIRCH_LABORATORY_FLOOR, ModBlocks.GLOWING_BIRCH_LABORATORY_FLOOR, ModBlocks.BIRCH_LABORATORY_TILES, ModBlocks.GLOWING_BIRCH_LABORATORY_TILES),
            family(Blocks.DARK_OAK_PLANKS, ModBlocks.DARK_OAK_LABORATORY_FLOOR, ModBlocks.GLOWING_DARK_OAK_LABORATORY_FLOOR, ModBlocks.DARK_OAK_LABORATORY_TILES, ModBlocks.GLOWING_DARK_OAK_LABORATORY_TILES),
            family(Blocks.JUNGLE_PLANKS, ModBlocks.JUNGLE_LABORATORY_FLOOR, ModBlocks.GLOWING_JUNGLE_LABORATORY_FLOOR, ModBlocks.JUNGLE_LABORATORY_TILES, ModBlocks.GLOWING_JUNGLE_LABORATORY_TILES),
            family(Blocks.ACACIA_PLANKS, ModBlocks.ACACIA_LABORATORY_FLOOR, ModBlocks.GLOWING_ACACIA_LABORATORY_FLOOR, ModBlocks.ACACIA_LABORATORY_TILES, ModBlocks.GLOWING_ACACIA_LABORATORY_TILES),
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

        Block targetBlock;
        if (currentMatch.kind == BlockKind.FLOOR) {
            targetBlock = currentMatch.glowing ? targetFamily.glowingFloor.get() : targetFamily.floor.get();
        } else {
            targetBlock = currentMatch.glowing ? targetFamily.glowingTiles.get() : targetFamily.tiles.get();
        }

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
            RegistryObject<? extends Block> floor,
            RegistryObject<? extends Block> glowingFloor,
            RegistryObject<? extends Block> tiles,
            RegistryObject<? extends Block> glowingTiles
    ) {
        return new WoodFamily(planks, floor, glowingFloor, tiles, glowingTiles);
    }

    public static final class SwapResult {
        private final Block targetBlock;
        private final ItemStack returnedPlanks;

        private SwapResult(Block targetBlock, ItemStack returnedPlanks) {
            this.targetBlock = targetBlock;
            this.returnedPlanks = returnedPlanks;
        }

        public Block targetBlock() {
            return this.targetBlock;
        }

        public ItemStack returnedPlanks() {
            return this.returnedPlanks;
        }
    }

    private static final class WoodFamily {
        private final Block planks;
        private final RegistryObject<? extends Block> floor;
        private final RegistryObject<? extends Block> glowingFloor;
        private final RegistryObject<? extends Block> tiles;
        private final RegistryObject<? extends Block> glowingTiles;

        private WoodFamily(Block planks, RegistryObject<? extends Block> floor, RegistryObject<? extends Block> glowingFloor, RegistryObject<? extends Block> tiles, RegistryObject<? extends Block> glowingTiles) {
            this.planks = planks;
            this.floor = floor;
            this.glowingFloor = glowingFloor;
            this.tiles = tiles;
            this.glowingTiles = glowingTiles;
        }
    }

    private static final class BlockMatch {
        private final WoodFamily family;
        private final BlockKind kind;
        private final boolean glowing;

        private BlockMatch(WoodFamily family, BlockKind kind, boolean glowing) {
            this.family = family;
            this.kind = kind;
            this.glowing = glowing;
        }
    }

    private enum BlockKind {
        FLOOR,
        TILES
    }
}
