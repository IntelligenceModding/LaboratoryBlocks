package de.artemis.laboratoryblocks.common.datagen;

import de.artemis.laboratoryblocks.common.registry.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.TrapDoorBlock;
import net.minecraftforge.fml.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

@SuppressWarnings("unused")
public final class ModDatagenEntries {
    public static final List<GeneratedBlockPair> CORE_PAIRS = Collections.unmodifiableList(Arrays.asList(
            pair(ModBlocks.LABORATORY_BLOCK, ModBlocks.GLOWING_LABORATORY_BLOCK, "laboratory_block"),
            pair(ModBlocks.REINFORCED_LABORATORY_BLOCK, ModBlocks.GLOWING_REINFORCED_LABORATORY_BLOCK, "reinforced_laboratory_block"),
            pair(ModBlocks.LABORATORY_TILES, ModBlocks.GLOWING_LABORATORY_TILES, "laboratory_tiles"),
            pair(ModBlocks.GRAY_LABORATORY_TILES, ModBlocks.GLOWING_GRAY_LABORATORY_TILES, "gray_laboratory_tiles"),
            pair(ModBlocks.MIXED_LABORATORY_TILES, ModBlocks.GLOWING_MIXED_LABORATORY_TILES, "mixed_laboratory_tiles"),
            pair(ModBlocks.CLEAR_LABORATORY_SCREEN, ModBlocks.GLOWING_CLEAR_LABORATORY_SCREEN, "clear_laboratory_screen"),
            pair(ModBlocks.WAVE_LABORATORY_SCREEN, ModBlocks.GLOWING_WAVE_LABORATORY_SCREEN, "wave_laboratory_screen"),
            pair(ModBlocks.TEXT_LABORATORY_SCREEN, ModBlocks.GLOWING_TEXT_LABORATORY_SCREEN, "text_laboratory_screen"),
            pair(ModBlocks.QUANTUM_LABORATORY_SCREEN, ModBlocks.GLOWING_QUANTUM_LABORATORY_SCREEN, "quantum_laboratory_screen"),
            pair(ModBlocks.LEFT_INDICATING_BLUE_LABORATORY_BLOCK, ModBlocks.GLOWING_LEFT_INDICATING_BLUE_LABORATORY_BLOCK, "left_indicating_blue_laboratory_block"),
            pair(ModBlocks.RIGHT_INDICATING_BLUE_LABORATORY_BLOCK, ModBlocks.GLOWING_RIGHT_INDICATING_BLUE_LABORATORY_BLOCK, "right_indicating_blue_laboratory_block"),
            pair(ModBlocks.LEFT_INDICATING_RED_LABORATORY_BLOCK, ModBlocks.GLOWING_LEFT_INDICATING_RED_LABORATORY_BLOCK, "left_indicating_red_laboratory_block"),
            pair(ModBlocks.RIGHT_INDICATING_RED_LABORATORY_BLOCK, ModBlocks.GLOWING_RIGHT_INDICATING_RED_LABORATORY_BLOCK, "right_indicating_red_laboratory_block"),
            pair(ModBlocks.LEFT_INDICATING_GREEN_LABORATORY_BLOCK, ModBlocks.GLOWING_LEFT_INDICATING_GREEN_LABORATORY_BLOCK, "left_indicating_green_laboratory_block"),
            pair(ModBlocks.RIGHT_INDICATING_GREEN_LABORATORY_BLOCK, ModBlocks.GLOWING_RIGHT_INDICATING_GREEN_LABORATORY_BLOCK, "right_indicating_green_laboratory_block"),
            pair(ModBlocks.LABORATORY_VENT, ModBlocks.GLOWING_LABORATORY_VENT, "laboratory_vent"),
            pair(ModBlocks.LABORATORY_GLASS, ModBlocks.GLOWING_LABORATORY_GLASS, "laboratory_glass")
    ));

    public static final List<GeneratedPillarPair> PILLAR_PAIRS = Collections.unmodifiableList(Arrays.asList(
            pillar(ModBlocks.LABORATORY_PILLAR, ModBlocks.GLOWING_LABORATORY_PILLAR, "laboratory_pillar", "laboratory_pillar_top"),
            pillar(ModBlocks.GRAY_LABORATORY_PILLAR, ModBlocks.GLOWING_GRAY_LABORATORY_PILLAR, "gray_laboratory_pillar", "gray_laboratory_pillar_top")
    ));

    public static final List<GeneratedFanPair> FAN_PAIRS = Collections.unmodifiableList(Arrays.asList(
            fan(ModBlocks.LABORATORY_FAN, ModBlocks.GLOWING_LABORATORY_FAN, "laboratory_fan"),
            fan(ModBlocks.LABORATORY_FAN_REDSTONE_CONTROLLED, ModBlocks.GLOWING_LABORATORY_FAN_REDSTONE_CONTROLLED, "laboratory_fan_redstone_controlled")
    ));

    public static final List<WoodFamily> WOOD_FAMILIES = Collections.unmodifiableList(Arrays.asList(
            wood(Blocks.OAK_PLANKS, ModBlocks.OAK_LABORATORY_FLOOR, ModBlocks.GLOWING_OAK_LABORATORY_FLOOR, "oak_laboratory_floor", ModBlocks.OAK_LABORATORY_TILES, ModBlocks.GLOWING_OAK_LABORATORY_TILES, "oak_laboratory_tiles"),
            wood(Blocks.SPRUCE_PLANKS, ModBlocks.SPRUCE_LABORATORY_FLOOR, ModBlocks.GLOWING_SPRUCE_LABORATORY_FLOOR, "spruce_laboratory_floor", ModBlocks.SPRUCE_LABORATORY_TILES, ModBlocks.GLOWING_SPRUCE_LABORATORY_TILES, "spruce_laboratory_tiles"),
            wood(Blocks.BIRCH_PLANKS, ModBlocks.BIRCH_LABORATORY_FLOOR, ModBlocks.GLOWING_BIRCH_LABORATORY_FLOOR, "birch_laboratory_floor", ModBlocks.BIRCH_LABORATORY_TILES, ModBlocks.GLOWING_BIRCH_LABORATORY_TILES, "birch_laboratory_tiles"),
            wood(Blocks.DARK_OAK_PLANKS, ModBlocks.DARK_OAK_LABORATORY_FLOOR, ModBlocks.GLOWING_DARK_OAK_LABORATORY_FLOOR, "dark_oak_laboratory_floor", ModBlocks.DARK_OAK_LABORATORY_TILES, ModBlocks.GLOWING_DARK_OAK_LABORATORY_TILES, "dark_oak_laboratory_tiles"),
            wood(Blocks.JUNGLE_PLANKS, ModBlocks.JUNGLE_LABORATORY_FLOOR, ModBlocks.GLOWING_JUNGLE_LABORATORY_FLOOR, "jungle_laboratory_floor", ModBlocks.JUNGLE_LABORATORY_TILES, ModBlocks.GLOWING_JUNGLE_LABORATORY_TILES, "jungle_laboratory_tiles"),
            wood(Blocks.ACACIA_PLANKS, ModBlocks.ACACIA_LABORATORY_FLOOR, ModBlocks.GLOWING_ACACIA_LABORATORY_FLOOR, "acacia_laboratory_floor", ModBlocks.ACACIA_LABORATORY_TILES, ModBlocks.GLOWING_ACACIA_LABORATORY_TILES, "acacia_laboratory_tiles"),
            wood(Blocks.CRIMSON_PLANKS, ModBlocks.CRIMSON_LABORATORY_FLOOR, ModBlocks.GLOWING_CRIMSON_LABORATORY_FLOOR, "crimson_laboratory_floor", ModBlocks.CRIMSON_LABORATORY_TILES, ModBlocks.GLOWING_CRIMSON_LABORATORY_TILES, "crimson_laboratory_tiles"),
            wood(Blocks.WARPED_PLANKS, ModBlocks.WARPED_LABORATORY_FLOOR, ModBlocks.GLOWING_WARPED_LABORATORY_FLOOR, "warped_laboratory_floor", ModBlocks.WARPED_LABORATORY_TILES, ModBlocks.GLOWING_WARPED_LABORATORY_TILES, "warped_laboratory_tiles")
    ));

    public static final List<RegistryObject<? extends DoorBlock>> DOORS = Collections.unmodifiableList(Arrays.asList(
            ModBlocks.LABORATORY_DOOR,
            ModBlocks.MESH_LABORATORY_DOOR,
            ModBlocks.GLASS_LABORATORY_DOOR
    ));

    public static final List<RegistryObject<? extends TrapDoorBlock>> TRAPDOORS = Collections.unmodifiableList(Arrays.asList(
            ModBlocks.LABORATORY_TRAPDOOR,
            ModBlocks.MESH_LABORATORY_TRAPDOOR,
            ModBlocks.GLASS_LABORATORY_TRAPDOOR
    ));

    public static final List<GeneratedBlockPair> ALL_PAIRS = Collections.unmodifiableList(allPairs());
    public static final List<Object> GENERATED_PAIRS = Collections.unmodifiableList(generatedPairs());

    private ModDatagenEntries() {
    }

    private static List<GeneratedBlockPair> allPairs() {
        List<GeneratedBlockPair> pairs = new ArrayList<>(CORE_PAIRS);
        for (WoodFamily family : WOOD_FAMILIES) {
            pairs.add(family.floorPair());
            pairs.add(family.tilePair());
        }
        return pairs;
    }

    private static List<Object> generatedPairs() {
        List<Object> pairs = new ArrayList<>();
        pairs.addAll(ALL_PAIRS);
        pairs.addAll(PILLAR_PAIRS);
        return pairs;
    }

    public static final class GeneratedBlockPair {
        private final RegistryObject<? extends Block> base;
        private final RegistryObject<? extends Block> glowing;
        private final String texturePath;

        private GeneratedBlockPair(RegistryObject<? extends Block> base, RegistryObject<? extends Block> glowing, String texturePath) {
            this.base = base;
            this.glowing = glowing;
            this.texturePath = texturePath;
        }

        public RegistryObject<? extends Block> base() {
            return this.base;
        }

        public RegistryObject<? extends Block> glowing() {
            return this.glowing;
        }

        public String texturePath() {
            return this.texturePath;
        }

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

    public static final class WoodFamily {
        @Nullable
        private final Block planks;
        private final GeneratedBlockPair floorPair;
        private final GeneratedBlockPair tilePair;

        private WoodFamily(@Nullable Block planks, GeneratedBlockPair floorPair, GeneratedBlockPair tilePair) {
            this.planks = planks;
            this.floorPair = floorPair;
            this.tilePair = tilePair;
        }

        @Nullable
        public Block planks() {
            return this.planks;
        }

        public GeneratedBlockPair floorPair() {
            return this.floorPair;
        }

        public GeneratedBlockPair tilePair() {
            return this.tilePair;
        }

        public Stream<GeneratedBlockPair> pairs() {
            return Stream.of(this.floorPair, this.tilePair);
        }
    }

    public static final class GeneratedPillarPair {
        private final RegistryObject<? extends Block> base;
        private final RegistryObject<? extends Block> glowing;
        private final String sideTexturePath;
        private final String endTexturePath;

        private GeneratedPillarPair(RegistryObject<? extends Block> base, RegistryObject<? extends Block> glowing, String sideTexturePath, String endTexturePath) {
            this.base = base;
            this.glowing = glowing;
            this.sideTexturePath = sideTexturePath;
            this.endTexturePath = endTexturePath;
        }

        public RegistryObject<? extends Block> base() {
            return this.base;
        }

        public RegistryObject<? extends Block> glowing() {
            return this.glowing;
        }

        public String sideTexturePath() {
            return this.sideTexturePath;
        }

        public String endTexturePath() {
            return this.endTexturePath;
        }

        public String baseModelName() {
            return this.base.getId().getPath();
        }

        public String glowingModelName() {
            return this.glowing.getId().getPath();
        }
    }

    public static final class GeneratedFanPair {
        private final RegistryObject<? extends Block> base;
        private final RegistryObject<? extends Block> glowing;
        private final String poweredTexturePath;

        private GeneratedFanPair(RegistryObject<? extends Block> base, RegistryObject<? extends Block> glowing, String poweredTexturePath) {
            this.base = base;
            this.glowing = glowing;
            this.poweredTexturePath = poweredTexturePath;
        }

        public RegistryObject<? extends Block> base() {
            return this.base;
        }

        public RegistryObject<? extends Block> glowing() {
            return this.glowing;
        }

        public String poweredTexturePath() {
            return this.poweredTexturePath;
        }

        public String baseModelName() {
            return this.base.getId().getPath();
        }

        public String glowingModelName() {
            return this.glowing.getId().getPath();
        }
    }

    private static GeneratedBlockPair pair(RegistryObject<? extends Block> base, RegistryObject<? extends Block> glowing, String texturePath) {
        return new GeneratedBlockPair(base, glowing, texturePath);
    }

    private static GeneratedPillarPair pillar(
            RegistryObject<? extends Block> base,
            RegistryObject<? extends Block> glowing,
            String sideTexturePath,
            String endTexturePath
    ) {
        return new GeneratedPillarPair(base, glowing, sideTexturePath, endTexturePath);
    }

    private static GeneratedFanPair fan(
            RegistryObject<? extends Block> base,
            RegistryObject<? extends Block> glowing,
            String poweredTexturePath
    ) {
        return new GeneratedFanPair(base, glowing, poweredTexturePath);
    }

    private static WoodFamily wood(
            @Nullable Block planks,
            RegistryObject<? extends Block> floor,
            RegistryObject<? extends Block> glowingFloor,
            String floorTexturePath,
            RegistryObject<? extends Block> tiles,
            RegistryObject<? extends Block> glowingTiles,
            String tileTexturePath
    ) {
        return new WoodFamily(
                planks,
                pair(floor, glowingFloor, floorTexturePath),
                pair(tiles, glowingTiles, tileTexturePath)
        );
    }
}
