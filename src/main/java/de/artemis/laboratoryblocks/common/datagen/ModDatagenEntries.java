package de.artemis.laboratoryblocks.common.datagen;

import de.artemis.laboratoryblocks.common.registry.ModBlocks;
import de.artemis.laboratoryblocks.common.registry.ModItems;
import net.minecraft.world.item.Item;
import java.util.function.Supplier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Stream;

@SuppressWarnings("unused")
public final class ModDatagenEntries {
    public static final List<GeneratedBlockPair> CORE_PAIRS = List.of(
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
            pair(ModBlocks.LABORATORY_GLASS, ModBlocks.GLOWING_LABORATORY_GLASS, "laboratory_glass"),
            pair(ModBlocks.DARK_LABORATORY_BLOCK, ModBlocks.GLOWING_DARK_LABORATORY_BLOCK, "dark_laboratory_block"),
            pair(ModBlocks.DARK_REINFORCED_LABORATORY_BLOCK, ModBlocks.GLOWING_DARK_REINFORCED_LABORATORY_BLOCK, "dark_reinforced_laboratory_block"),
            pair(ModBlocks.DARK_LABORATORY_TILES, ModBlocks.GLOWING_DARK_LABORATORY_TILES, "dark_laboratory_tiles"),
            pair(ModBlocks.DARK_CLEAR_LABORATORY_SCREEN, ModBlocks.GLOWING_DARK_CLEAR_LABORATORY_SCREEN, "dark_clear_laboratory_screen"),
            pair(ModBlocks.DARK_WAVE_LABORATORY_SCREEN, ModBlocks.GLOWING_DARK_WAVE_LABORATORY_SCREEN, "dark_wave_laboratory_screen"),
            pair(ModBlocks.DARK_TEXT_LABORATORY_SCREEN, ModBlocks.GLOWING_DARK_TEXT_LABORATORY_SCREEN, "dark_text_laboratory_screen"),
            pair(ModBlocks.DARK_QUANTUM_LABORATORY_SCREEN, ModBlocks.GLOWING_DARK_QUANTUM_LABORATORY_SCREEN, "dark_quantum_laboratory_screen"),
            pair(ModBlocks.DARK_LEFT_INDICATING_BLUE_LABORATORY_BLOCK, ModBlocks.GLOWING_DARK_LEFT_INDICATING_BLUE_LABORATORY_BLOCK, "dark_left_indicating_blue_laboratory_block"),
            pair(ModBlocks.DARK_RIGHT_INDICATING_BLUE_LABORATORY_BLOCK, ModBlocks.GLOWING_DARK_RIGHT_INDICATING_BLUE_LABORATORY_BLOCK, "dark_right_indicating_blue_laboratory_block"),
            pair(ModBlocks.DARK_LEFT_INDICATING_RED_LABORATORY_BLOCK, ModBlocks.GLOWING_DARK_LEFT_INDICATING_RED_LABORATORY_BLOCK, "dark_left_indicating_red_laboratory_block"),
            pair(ModBlocks.DARK_RIGHT_INDICATING_RED_LABORATORY_BLOCK, ModBlocks.GLOWING_DARK_RIGHT_INDICATING_RED_LABORATORY_BLOCK, "dark_right_indicating_red_laboratory_block"),
            pair(ModBlocks.DARK_LEFT_INDICATING_GREEN_LABORATORY_BLOCK, ModBlocks.GLOWING_DARK_LEFT_INDICATING_GREEN_LABORATORY_BLOCK, "dark_left_indicating_green_laboratory_block"),
            pair(ModBlocks.DARK_RIGHT_INDICATING_GREEN_LABORATORY_BLOCK, ModBlocks.GLOWING_DARK_RIGHT_INDICATING_GREEN_LABORATORY_BLOCK, "dark_right_indicating_green_laboratory_block"),
            pair(ModBlocks.DARK_LABORATORY_VENT, ModBlocks.GLOWING_DARK_LABORATORY_VENT, "dark_laboratory_vent"),
            pair(ModBlocks.DARK_LABORATORY_GLASS, ModBlocks.GLOWING_DARK_LABORATORY_GLASS, "dark_laboratory_glass")
    );

    public static final List<GeneratedPillarPair> PILLAR_PAIRS = List.of(
            pillar(ModBlocks.LABORATORY_PILLAR, ModBlocks.GLOWING_LABORATORY_PILLAR, "laboratory_pillar", "laboratory_pillar_top"),
            pillar(ModBlocks.DARK_LABORATORY_PILLAR, ModBlocks.GLOWING_DARK_LABORATORY_PILLAR, "dark_laboratory_pillar", "dark_laboratory_pillar_top")
    );

    public static final List<GeneratedFanPair> FAN_PAIRS = List.of(
            fan(ModBlocks.LABORATORY_FAN, ModBlocks.GLOWING_LABORATORY_FAN, "laboratory_fan"),
            fan(ModBlocks.LABORATORY_FAN_REDSTONE_CONTROLLED, ModBlocks.GLOWING_LABORATORY_FAN_REDSTONE_CONTROLLED, "laboratory_fan_redstone_controlled"),
            fan(ModBlocks.DARK_LABORATORY_FAN, ModBlocks.GLOWING_DARK_LABORATORY_FAN, "dark_laboratory_fan"),
            fan(ModBlocks.DARK_LABORATORY_FAN_REDSTONE_CONTROLLED, ModBlocks.GLOWING_DARK_LABORATORY_FAN_REDSTONE_CONTROLLED, "dark_laboratory_fan_redstone_controlled")
    );

    public static final List<WoodFamily> WOOD_FAMILIES = List.of(
            wood(Blocks.OAK_PLANKS, ModBlocks.OAK_LABORATORY_FLOOR, ModBlocks.GLOWING_OAK_LABORATORY_FLOOR, "oak_laboratory_floor", ModBlocks.OAK_LABORATORY_TILES, ModBlocks.GLOWING_OAK_LABORATORY_TILES, "oak_laboratory_tiles"),
            wood(Blocks.SPRUCE_PLANKS, ModBlocks.SPRUCE_LABORATORY_FLOOR, ModBlocks.GLOWING_SPRUCE_LABORATORY_FLOOR, "spruce_laboratory_floor", ModBlocks.SPRUCE_LABORATORY_TILES, ModBlocks.GLOWING_SPRUCE_LABORATORY_TILES, "spruce_laboratory_tiles"),
            wood(Blocks.BIRCH_PLANKS, ModBlocks.BIRCH_LABORATORY_FLOOR, ModBlocks.GLOWING_BIRCH_LABORATORY_FLOOR, "birch_laboratory_floor", ModBlocks.BIRCH_LABORATORY_TILES, ModBlocks.GLOWING_BIRCH_LABORATORY_TILES, "birch_laboratory_tiles"),
            wood(Blocks.DARK_OAK_PLANKS, ModBlocks.DARK_OAK_LABORATORY_FLOOR, ModBlocks.GLOWING_DARK_OAK_LABORATORY_FLOOR, "dark_oak_laboratory_floor", ModBlocks.DARK_OAK_LABORATORY_TILES, ModBlocks.GLOWING_DARK_OAK_LABORATORY_TILES, "dark_oak_laboratory_tiles"),
            wood(Blocks.JUNGLE_PLANKS, ModBlocks.JUNGLE_LABORATORY_FLOOR, ModBlocks.GLOWING_JUNGLE_LABORATORY_FLOOR, "jungle_laboratory_floor", ModBlocks.JUNGLE_LABORATORY_TILES, ModBlocks.GLOWING_JUNGLE_LABORATORY_TILES, "jungle_laboratory_tiles"),
            wood(Blocks.ACACIA_PLANKS, ModBlocks.ACACIA_LABORATORY_FLOOR, ModBlocks.GLOWING_ACACIA_LABORATORY_FLOOR, "acacia_laboratory_floor", ModBlocks.ACACIA_LABORATORY_TILES, ModBlocks.GLOWING_ACACIA_LABORATORY_TILES, "acacia_laboratory_tiles"),
            wood(Blocks.CRIMSON_PLANKS, ModBlocks.CRIMSON_LABORATORY_FLOOR, ModBlocks.GLOWING_CRIMSON_LABORATORY_FLOOR, "crimson_laboratory_floor", ModBlocks.CRIMSON_LABORATORY_TILES, ModBlocks.GLOWING_CRIMSON_LABORATORY_TILES, "crimson_laboratory_tiles"),
            wood(Blocks.WARPED_PLANKS, ModBlocks.WARPED_LABORATORY_FLOOR, ModBlocks.GLOWING_WARPED_LABORATORY_FLOOR, "warped_laboratory_floor", ModBlocks.WARPED_LABORATORY_TILES, ModBlocks.GLOWING_WARPED_LABORATORY_TILES, "warped_laboratory_tiles"),
            wood(Blocks.SPRUCE_PLANKS, ModBlocks.DARK_SPRUCE_LABORATORY_FLOOR, ModBlocks.GLOWING_DARK_SPRUCE_LABORATORY_FLOOR, "dark_spruce_laboratory_floor", ModBlocks.DARK_SPRUCE_LABORATORY_TILES, ModBlocks.GLOWING_DARK_SPRUCE_LABORATORY_TILES, "dark_spruce_laboratory_tiles"),
            wood(Blocks.BIRCH_PLANKS, ModBlocks.DARK_BIRCH_LABORATORY_FLOOR, ModBlocks.GLOWING_DARK_BIRCH_LABORATORY_FLOOR, "dark_birch_laboratory_floor", ModBlocks.DARK_BIRCH_LABORATORY_TILES, ModBlocks.GLOWING_DARK_BIRCH_LABORATORY_TILES, "dark_birch_laboratory_tiles"),
            wood(Blocks.DARK_OAK_PLANKS, ModBlocks.DARK_DARK_OAK_LABORATORY_FLOOR, ModBlocks.GLOWING_DARK_DARK_OAK_LABORATORY_FLOOR, "dark_dark_oak_laboratory_floor", ModBlocks.DARK_DARK_OAK_LABORATORY_TILES, ModBlocks.GLOWING_DARK_DARK_OAK_LABORATORY_TILES, "dark_dark_oak_laboratory_tiles"),
            wood(Blocks.JUNGLE_PLANKS, ModBlocks.DARK_JUNGLE_LABORATORY_FLOOR, ModBlocks.GLOWING_DARK_JUNGLE_LABORATORY_FLOOR, "dark_jungle_laboratory_floor", ModBlocks.DARK_JUNGLE_LABORATORY_TILES, ModBlocks.GLOWING_DARK_JUNGLE_LABORATORY_TILES, "dark_jungle_laboratory_tiles"),
            wood(Blocks.ACACIA_PLANKS, ModBlocks.DARK_ACACIA_LABORATORY_FLOOR, ModBlocks.GLOWING_DARK_ACACIA_LABORATORY_FLOOR, "dark_acacia_laboratory_floor", ModBlocks.DARK_ACACIA_LABORATORY_TILES, ModBlocks.GLOWING_DARK_ACACIA_LABORATORY_TILES, "dark_acacia_laboratory_tiles"),
            wood(Blocks.CRIMSON_PLANKS, ModBlocks.DARK_CRIMSON_LABORATORY_FLOOR, ModBlocks.GLOWING_DARK_CRIMSON_LABORATORY_FLOOR, "dark_crimson_laboratory_floor", ModBlocks.DARK_CRIMSON_LABORATORY_TILES, ModBlocks.GLOWING_DARK_CRIMSON_LABORATORY_TILES, "dark_crimson_laboratory_tiles"),
            wood(Blocks.WARPED_PLANKS, ModBlocks.DARK_WARPED_LABORATORY_FLOOR, ModBlocks.GLOWING_DARK_WARPED_LABORATORY_FLOOR, "dark_warped_laboratory_floor", ModBlocks.DARK_WARPED_LABORATORY_TILES, ModBlocks.GLOWING_DARK_WARPED_LABORATORY_TILES, "dark_warped_laboratory_tiles"),
            wood(Blocks.OAK_PLANKS, ModBlocks.DARKENED_OAK_LABORATORY_FLOOR, ModBlocks.GLOWING_DARKENED_OAK_LABORATORY_FLOOR, "darkened_oak_laboratory_floor", ModBlocks.DARKENED_OAK_LABORATORY_TILES, ModBlocks.GLOWING_DARKENED_OAK_LABORATORY_TILES, "darkened_oak_laboratory_tiles")
    );

    public static final List<RegistryObject<? extends DoorBlock>> DOORS = List.of(
            ModBlocks.LABORATORY_DOOR,
            ModBlocks.MESH_LABORATORY_DOOR,
            ModBlocks.GLASS_LABORATORY_DOOR,
            ModBlocks.DARK_LABORATORY_DOOR,
            ModBlocks.DARK_MESH_LABORATORY_DOOR,
            ModBlocks.DARK_GLASS_LABORATORY_DOOR
    );

    public static final List<RegistryObject<? extends TrapDoorBlock>> TRAPDOORS = List.of(
            ModBlocks.LABORATORY_TRAPDOOR,
            ModBlocks.MESH_LABORATORY_TRAPDOOR,
            ModBlocks.GLASS_LABORATORY_TRAPDOOR,
            ModBlocks.DARK_LABORATORY_TRAPDOOR,
            ModBlocks.DARK_MESH_LABORATORY_TRAPDOOR,
            ModBlocks.DARK_GLASS_LABORATORY_TRAPDOOR
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

    public record GeneratedBlockPair(RegistryObject<? extends Block> base, RegistryObject<? extends Block> glowing, String texturePath) {
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

    public record WoodFamily(@Nullable Block planks, GeneratedBlockPair floorPair, GeneratedBlockPair tilePair) {
        public Stream<GeneratedBlockPair> pairs() {
            return Stream.of(this.floorPair, this.tilePair);
        }
    }

    public record GeneratedPillarPair(
            RegistryObject<? extends Block> base,
            RegistryObject<? extends Block> glowing,
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
            RegistryObject<? extends Block> base,
            RegistryObject<? extends Block> glowing,
            String poweredTexturePath
    ) {
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
    public record MusicDiscLoot(String name, Supplier<? extends Item> disc, String chest, float chance) {}
    public static final List<MusicDiscLoot> MUSIC_DISC_LOOT = List.of(
        new MusicDiscLoot("exploration", ModItems.MUSIC_DISC_EXPLORATION, "chests/abandoned_mineshaft", 0.10F),
        new MusicDiscLoot("pondering_the_cosmos", ModItems.MUSIC_DISC_PONDERING_THE_COSMOS, "chests/end_city_treasure", 0.10F));
}