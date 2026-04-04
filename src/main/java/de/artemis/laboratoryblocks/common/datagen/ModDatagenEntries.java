package de.artemis.laboratoryblocks.common.datagen;

import de.artemis.laboratoryblocks.common.block.ModBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.List;
import java.util.stream.Stream;

public final class ModDatagenEntries {
    public static final List<GeneratedBlockPair> CORE_PAIRS = List.of(
            pair(ModBlocks.LABORATORY_BLOCK, ModBlocks.GLOWING_LABORATORY_BLOCK, "laboratory_block"),
            pair(ModBlocks.REINFORCED_LABORATORY_BLOCK, ModBlocks.GLOWING_REINFORCED_LABORATORY_BLOCK, "reinforced_laboratory_block"),
            pair(ModBlocks.LABORATORY_TILES, ModBlocks.GLOWING_LABORATORY_TILES, "laboratory_tiles"),
            pair(ModBlocks.GRAY_LABORATORY_TILES, ModBlocks.GLOWING_GRAY_LABORATORY_TILES, "gray_laboratory_tiles"),
            pair(ModBlocks.MIXED_LABORATORY_TILES, ModBlocks.GLOWING_MIXED_LABORATORY_TILES, "mixed_laboratory_tiles"),
            pair(ModBlocks.LEFT_INDICATING_BLUE_LABORATORY_BLOCK, ModBlocks.GLOWING_LEFT_INDICATING_BLUE_LABORATORY_BLOCK, "left_indicating_blue_laboratory_block"),
            pair(ModBlocks.RIGHT_INDICATING_BLUE_LABORATORY_BLOCK, ModBlocks.GLOWING_RIGHT_INDICATING_BLUE_LABORATORY_BLOCK, "right_indicating_blue_laboratory_block"),
            pair(ModBlocks.LEFT_INDICATING_RED_LABORATORY_BLOCK, ModBlocks.GLOWING_LEFT_INDICATING_RED_LABORATORY_BLOCK, "left_indicating_red_laboratory_block"),
            pair(ModBlocks.RIGHT_INDICATING_RED_LABORATORY_BLOCK, ModBlocks.GLOWING_RIGHT_INDICATING_RED_LABORATORY_BLOCK, "right_indicating_red_laboratory_block"),
            pair(ModBlocks.LEFT_INDICATING_GREEN_LABORATORY_BLOCK, ModBlocks.GLOWING_LEFT_INDICATING_GREEN_LABORATORY_BLOCK, "left_indicating_green_laboratory_block"),
            pair(ModBlocks.RIGHT_INDICATING_GREEN_LABORATORY_BLOCK, ModBlocks.GLOWING_RIGHT_INDICATING_GREEN_LABORATORY_BLOCK, "right_indicating_green_laboratory_block"),
            pair(ModBlocks.LABORATORY_VENT, ModBlocks.GLOWING_LABORATORY_VENT, "laboratory_vent"),
            pair(ModBlocks.LABORATORY_GLASS, ModBlocks.GLOWING_LABORATORY_GLASS, "laboratory_glass")
    );

    public static final List<GeneratedPillarPair> PILLAR_PAIRS = List.of(
            pillar(ModBlocks.LABORATORY_PILLAR, ModBlocks.GLOWING_LABORATORY_PILLAR, "laboratory_pillar", "laboratory_pillar_top"),
            pillar(ModBlocks.GRAY_LABORATORY_PILLAR, ModBlocks.GLOWING_GRAY_LABORATORY_PILLAR, "gray_laboratory_pillar", "gray_laboratory_pillar_top")
    );

    public static final List<GeneratedFanPair> FAN_PAIRS = List.of(
            fan(ModBlocks.LABORATORY_FAN, ModBlocks.GLOWING_LABORATORY_FAN, "laboratory_fan"),
            fan(ModBlocks.LABORATORY_FAN_REDSTONE_CONTROLLED, ModBlocks.GLOWING_LABORATORY_FAN_REDSTONE_CONTROLLED, "laboratory_fan_redstone_controlled")
    );

    public static final List<WoodFamily> WOOD_FAMILIES = List.of(
            wood(Blocks.OAK_PLANKS, ModBlocks.OAK_LABORATORY_FLOOR, ModBlocks.GLOWING_OAK_LABORATORY_FLOOR, "oak_laboratory_floor", ModBlocks.OAK_LABORATORY_TILES, ModBlocks.GLOWING_OAK_LABORATORY_TILES, "oak_laboratory_tiles"),
            wood(Blocks.SPRUCE_PLANKS, ModBlocks.SPRUCE_LABORATORY_FLOOR, ModBlocks.GLOWING_SPRUCE_LABORATORY_FLOOR, "spruce_laboratory_floor", ModBlocks.SPRUCE_LABORATORY_TILES, ModBlocks.GLOWING_SPRUCE_LABORATORY_TILES, "spruce_laboratory_tiles"),
            wood(Blocks.BIRCH_PLANKS, ModBlocks.BIRCH_LABORATORY_FLOOR, ModBlocks.GLOWING_BIRCH_LABORATORY_FLOOR, "birch_laboratory_floor", ModBlocks.BIRCH_LABORATORY_TILES, ModBlocks.GLOWING_BIRCH_LABORATORY_TILES, "birch_laboratory_tiles"),
            wood(Blocks.DARK_OAK_PLANKS, ModBlocks.DARK_OAK_LABORATORY_FLOOR, ModBlocks.GLOWING_DARK_OAK_LABORATORY_FLOOR, "dark_oak_laboratory_floor", ModBlocks.DARK_OAK_LABORATORY_TILES, ModBlocks.GLOWING_DARK_OAK_LABORATORY_TILES, "dark_oak_laboratory_tiles"),
            wood(Blocks.PALE_OAK_PLANKS, ModBlocks.PALE_OAK_LABORATORY_FLOOR, ModBlocks.GLOWING_PALE_OAK_LABORATORY_FLOOR, "pale_oak_laboratory_floor", ModBlocks.PALE_OAK_LABORATORY_TILES, ModBlocks.GLOWING_PALE_OAK_LABORATORY_TILES, "pale_oak_laboratory_tiles"),
            wood(Blocks.JUNGLE_PLANKS, ModBlocks.JUNGLE_LABORATORY_FLOOR, ModBlocks.GLOWING_JUNGLE_LABORATORY_FLOOR, "jungle_laboratory_floor", ModBlocks.JUNGLE_LABORATORY_TILES, ModBlocks.GLOWING_JUNGLE_LABORATORY_TILES, "jungle_laboratory_tiles"),
            wood(Blocks.ACACIA_PLANKS, ModBlocks.ACACIA_LABORATORY_FLOOR, ModBlocks.GLOWING_ACACIA_LABORATORY_FLOOR, "acacia_laboratory_floor", ModBlocks.ACACIA_LABORATORY_TILES, ModBlocks.GLOWING_ACACIA_LABORATORY_TILES, "acacia_laboratory_tiles"),
            wood(Blocks.MANGROVE_PLANKS, ModBlocks.MANGROVE_LABORATORY_FLOOR, ModBlocks.GLOWING_MANGROVE_LABORATORY_FLOOR, "mangrove_laboratory_floor", ModBlocks.MANGROVE_LABORATORY_TILES, ModBlocks.GLOWING_MANGROVE_LABORATORY_TILES, "mangrove_laboratory_tiles"),
            wood(Blocks.CHERRY_PLANKS, ModBlocks.CHERRY_LABORATORY_FLOOR, ModBlocks.GLOWING_CHERRY_LABORATORY_FLOOR, "cherry_laboratory_floor", ModBlocks.CHERRY_LABORATORY_TILES, ModBlocks.GLOWING_CHERRY_LABORATORY_TILES, "cherry_laboratory_tiles"),
            wood(Blocks.BAMBOO_PLANKS, ModBlocks.BAMBOO_LABORATORY_FLOOR, ModBlocks.GLOWING_BAMBOO_LABORATORY_FLOOR, "bamboo_laboratory_floor", ModBlocks.BAMBOO_LABORATORY_TILES, ModBlocks.GLOWING_BAMBOO_LABORATORY_TILES, "bamboo_laboratory_tiles"),
            wood(Blocks.CRIMSON_PLANKS, ModBlocks.CRIMSON_LABORATORY_FLOOR, ModBlocks.GLOWING_CRIMSON_LABORATORY_FLOOR, "crimson_laboratory_floor", ModBlocks.CRIMSON_LABORATORY_TILES, ModBlocks.GLOWING_CRIMSON_LABORATORY_TILES, "crimson_laboratory_tiles"),
            wood(Blocks.WARPED_PLANKS, ModBlocks.WARPED_LABORATORY_FLOOR, ModBlocks.GLOWING_WARPED_LABORATORY_FLOOR, "warped_laboratory_floor", ModBlocks.WARPED_LABORATORY_TILES, ModBlocks.GLOWING_WARPED_LABORATORY_TILES, "warped_laboratory_tiles")
    );

    public static final List<DeferredBlock<? extends DoorBlock>> DOORS = List.of(
            ModBlocks.LABORATORY_DOOR,
            ModBlocks.MESH_LABORATORY_DOOR,
            ModBlocks.GLASS_LABORATORY_DOOR
    );

    public static final List<DeferredBlock<? extends TrapDoorBlock>> TRAPDOORS = List.of(
            ModBlocks.LABORATORY_TRAPDOOR,
            ModBlocks.MESH_LABORATORY_TRAPDOOR,
            ModBlocks.GLASS_LABORATORY_TRAPDOOR
    );

    public static final List<GeneratedBlockPair> ALL_PAIRS = Stream.concat(
            CORE_PAIRS.stream(),
            WOOD_FAMILIES.stream().flatMap(WoodFamily::pairs)
    ).toList();

    public static final List<Object> GENERATED_PAIRS = Stream.concat(
            ALL_PAIRS.stream().map(pair -> (Object)pair),
            PILLAR_PAIRS.stream().map(pair -> (Object)pair)
    ).toList();

    private ModDatagenEntries() {
    }

    public record GeneratedBlockPair(DeferredBlock<? extends Block> base, DeferredBlock<? extends Block> glowing, String texturePath) {
        public String baseModelName() {
            return this.base.getId().getPath();
        }

        public String glowingModelName() {
            return this.glowing.getId().getPath();
        }

        public String fusionTexturePath() {
            return this.texturePath + "-fusion";
        }
    }

    public record WoodFamily(Block planks, GeneratedBlockPair floorPair, GeneratedBlockPair tilePair) {
        public Stream<GeneratedBlockPair> pairs() {
            return Stream.of(this.floorPair, this.tilePair);
        }
    }

    public record GeneratedPillarPair(
            DeferredBlock<? extends Block> base,
            DeferredBlock<? extends Block> glowing,
            String sideTexturePath,
            String endTexturePath
    ) {
        public String baseModelName() {
            return this.base.getId().getPath();
        }

        public String glowingModelName() {
            return this.glowing.getId().getPath();
        }
    }

    public record GeneratedFanPair(
            DeferredBlock<? extends Block> base,
            DeferredBlock<? extends Block> glowing,
            String poweredTexturePath
    ) {
        public String baseModelName() {
            return this.base.getId().getPath();
        }

        public String glowingModelName() {
            return this.glowing.getId().getPath();
        }
    }

    private static GeneratedBlockPair pair(DeferredBlock<? extends Block> base, DeferredBlock<? extends Block> glowing, String texturePath) {
        return new GeneratedBlockPair(base, glowing, texturePath);
    }

    private static GeneratedPillarPair pillar(
            DeferredBlock<? extends Block> base,
            DeferredBlock<? extends Block> glowing,
            String sideTexturePath,
            String endTexturePath
    ) {
        return new GeneratedPillarPair(base, glowing, sideTexturePath, endTexturePath);
    }

    private static GeneratedFanPair fan(
            DeferredBlock<? extends Block> base,
            DeferredBlock<? extends Block> glowing,
            String poweredTexturePath
    ) {
        return new GeneratedFanPair(base, glowing, poweredTexturePath);
    }

    private static WoodFamily wood(
            Block planks,
            DeferredBlock<? extends Block> floor,
            DeferredBlock<? extends Block> glowingFloor,
            String floorTexturePath,
            DeferredBlock<? extends Block> tiles,
            DeferredBlock<? extends Block> glowingTiles,
            String tileTexturePath
    ) {
        return new WoodFamily(
                planks,
                pair(floor, glowingFloor, floorTexturePath),
                pair(tiles, glowingTiles, tileTexturePath)
        );
    }
}
