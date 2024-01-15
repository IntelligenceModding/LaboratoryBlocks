package de.artemis.laboratoryblocks.common.registration;

import de.artemis.laboratoryblocks.common.blocks.LaboratoryBlock;
import de.artemis.laboratoryblocks.common.blocks.LaboratoryCarpetBlock;
import de.artemis.laboratoryblocks.common.blocks.LaboratoryGlassBlock;
import de.artemis.laboratoryblocks.common.blocks.RedstoneControlledLaboratoryBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = Registration.BLOCKS.register(name, block);
        Registration.ITEMS.register(name, () -> new BlockItem(toReturn.get(),
                new Item.Properties()));

        return toReturn;
    }

    private static boolean never(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, EntityType<?> entityType) {
        return false;
    }

    private static boolean never(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return false;
    }

    public static void register() {
    }

    public static final RegistryObject<LaboratoryBlock> LABORATORY_PILLAR = register("laboratory_pillar",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_LABORATORY_PILLAR, BlockBehaviour.Properties.of().strength(2.5F, 3.0F).sound(SoundType.METAL)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_LABORATORY_PILLAR = register("enlighted_laboratory_pillar",
            () -> new LaboratoryBlock(ModBlocks.LABORATORY_PILLAR, BlockBehaviour.Properties.of().strength(2.5F, 3.0F).sound(SoundType.METAL).lightLevel((p_187433_) -> 14)));

    public static final RegistryObject<LaboratoryBlock> GRAY_LABORATORY_PILLAR = register("gray_laboratory_pillar",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_GRAY_LABORATORY_PILLAR, BlockBehaviour.Properties.of().strength(2.5F, 3.0F).sound(SoundType.METAL)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_GRAY_LABORATORY_PILLAR = register("enlighted_gray_laboratory_pillar",
            () -> new LaboratoryBlock(ModBlocks.GRAY_LABORATORY_PILLAR, BlockBehaviour.Properties.of().strength(2.5F, 3.0F).sound(SoundType.METAL).lightLevel((p_187433_) -> 14)));

    public static final RegistryObject<LaboratoryBlock> CLEAR_LABORATORY_SCREEN = register("clear_laboratory_screen",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_CLEAR_LABORATORY_SCREEN, BlockBehaviour.Properties.of().strength(1.25F, 1.5F).sound(SoundType.METAL)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_CLEAR_LABORATORY_SCREEN = register("enlighted_clear_laboratory_screen",
            () -> new LaboratoryBlock(ModBlocks.CLEAR_LABORATORY_SCREEN, BlockBehaviour.Properties.of().strength(1.25F, 1.5F).sound(SoundType.METAL).lightLevel((p_187433_) -> 14)));

    public static final RegistryObject<RedstoneControlledLaboratoryBlock> LABORATORY_FAN = register("laboratory_fan",
            () -> new RedstoneControlledLaboratoryBlock(ModBlocks.ENLIGHTED_LABORATORY_FAN, ModBlocks.LABORATORY_FAN_REDSTONE_CONTROLLED, BlockBehaviour.Properties.of().strength(2.5F, 3.0F).sound(SoundType.METAL)));

    public static final RegistryObject<RedstoneControlledLaboratoryBlock> ENLIGHTED_LABORATORY_FAN = register("enlighted_laboratory_fan",
            () -> new RedstoneControlledLaboratoryBlock(ModBlocks.LABORATORY_FAN, ModBlocks.ENLIGHTED_LABORATORY_FAN_REDSTONE_CONTROLLED, BlockBehaviour.Properties.of().strength(2.5F, 3.0F).sound(SoundType.METAL).lightLevel((p_187433_) -> 14)));

    public static final RegistryObject<RedstoneControlledLaboratoryBlock> LABORATORY_FAN_REDSTONE_CONTROLLED = register("laboratory_fan_redstone_controlled",
            () -> new RedstoneControlledLaboratoryBlock(ModBlocks.ENLIGHTED_LABORATORY_FAN_REDSTONE_CONTROLLED, ModBlocks.LABORATORY_FAN, BlockBehaviour.Properties.of().strength(2.5F, 3.0F).sound(SoundType.METAL)));

    public static final RegistryObject<RedstoneControlledLaboratoryBlock> ENLIGHTED_LABORATORY_FAN_REDSTONE_CONTROLLED = register("enlighted_laboratory_fan_redstone_controlled",
            () -> new RedstoneControlledLaboratoryBlock(ModBlocks.LABORATORY_FAN_REDSTONE_CONTROLLED, ModBlocks.ENLIGHTED_LABORATORY_FAN, BlockBehaviour.Properties.of().strength(2.5F, 3.0F).sound(SoundType.METAL).lightLevel((p_187433_) -> 14)));

    public static final RegistryObject<LaboratoryBlock> LEFT_FACED_BLUE_SIGNALING_LABORATORY_BLOCK = register("left-faced_blue_signaling_laboratory_block",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_LEFT_FACED_BLUE_SIGNALING_LABORATORY_BLOCK, BlockBehaviour.Properties.of().strength(2.5F, 3.0F).sound(SoundType.METAL)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_LEFT_FACED_BLUE_SIGNALING_LABORATORY_BLOCK = register("enlighted_left-faced_blue_signaling_laboratory_block",
            () -> new LaboratoryBlock(ModBlocks.LEFT_FACED_BLUE_SIGNALING_LABORATORY_BLOCK, BlockBehaviour.Properties.of().strength(2.5F, 3.0F).sound(SoundType.METAL).lightLevel((p_187433_) -> 14)));

    public static final RegistryObject<LaboratoryBlock> RIGHT_FACED_BLUE_SIGNALING_LABORATORY_BLOCK = register("right-faced_blue_signaling_laboratory_block",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_RIGHT_FACED_BLUE_SIGNALING_LABORATORY_BLOCK, BlockBehaviour.Properties.of().strength(2.5F, 3.0F).sound(SoundType.METAL)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_RIGHT_FACED_BLUE_SIGNALING_LABORATORY_BLOCK = register("enlighted_right-faced_blue_signaling_laboratory_block",
            () -> new LaboratoryBlock(ModBlocks.RIGHT_FACED_BLUE_SIGNALING_LABORATORY_BLOCK, BlockBehaviour.Properties.of().strength(2.5F, 3.0F).sound(SoundType.METAL).lightLevel((p_187433_) -> 14)));

    public static final RegistryObject<LaboratoryBlock> LEFT_FACED_RED_SIGNALING_LABORATORY_BLOCK = register("left-faced_red_signaling_laboratory_block",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_LEFT_FACED_RED_SIGNALING_LABORATORY_BLOCK, BlockBehaviour.Properties.of().strength(2.5F, 3.0F).sound(SoundType.METAL)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_LEFT_FACED_RED_SIGNALING_LABORATORY_BLOCK = register("enlighted_left-faced_red_signaling_laboratory_block",
            () -> new LaboratoryBlock(ModBlocks.LEFT_FACED_RED_SIGNALING_LABORATORY_BLOCK, BlockBehaviour.Properties.of().strength(2.5F, 3.0F).sound(SoundType.METAL).lightLevel((p_187433_) -> 14)));

    public static final RegistryObject<LaboratoryBlock> RIGHT_FACED_RED_SIGNALING_LABORATORY_BLOCK = register("right-faced_red_signaling_laboratory_block",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_RIGHT_FACED_RED_SIGNALING_LABORATORY_BLOCK, BlockBehaviour.Properties.of().strength(2.5F, 3.0F).sound(SoundType.METAL)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_RIGHT_FACED_RED_SIGNALING_LABORATORY_BLOCK = register("enlighted_right-faced_red_signaling_laboratory_block",
            () -> new LaboratoryBlock(ModBlocks.RIGHT_FACED_RED_SIGNALING_LABORATORY_BLOCK, BlockBehaviour.Properties.of().strength(2.5F, 3.0F).sound(SoundType.METAL).lightLevel((p_187433_) -> 14)));

    public static final RegistryObject<LaboratoryBlock> LEFT_FACED_GREEN_SIGNALING_LABORATORY_BLOCK = register("left-faced_green_signaling_laboratory_block",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_LEFT_FACED_GREEN_SIGNALING_LABORATORY_BLOCK, BlockBehaviour.Properties.of().strength(2.5F, 3.0F).sound(SoundType.METAL)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_LEFT_FACED_GREEN_SIGNALING_LABORATORY_BLOCK = register("enlighted_left-faced_green_signaling_laboratory_block",
            () -> new LaboratoryBlock(ModBlocks.LEFT_FACED_GREEN_SIGNALING_LABORATORY_BLOCK, BlockBehaviour.Properties.of().strength(2.5F, 3.0F).sound(SoundType.METAL).lightLevel((p_187433_) -> 14)));

    public static final RegistryObject<LaboratoryBlock> RIGHT_FACED_GREEN_SIGNALING_LABORATORY_BLOCK = register("right-faced_green_signaling_laboratory_block",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_RIGHT_FACED_GREEN_SIGNALING_LABORATORY_BLOCK, BlockBehaviour.Properties.of().strength(2.5F, 3.0F).sound(SoundType.METAL)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_RIGHT_FACED_GREEN_SIGNALING_LABORATORY_BLOCK = register("enlighted_right-faced_green_signaling_laboratory_block",
            () -> new LaboratoryBlock(ModBlocks.RIGHT_FACED_GREEN_SIGNALING_LABORATORY_BLOCK, BlockBehaviour.Properties.of().strength(2.5F, 3.0F).sound(SoundType.METAL).lightLevel((p_187433_) -> 14)));

    public static final RegistryObject<LaboratoryBlock> LABORATORY_VENT = register("laboratory_vent",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_LABORATORY_VENT, BlockBehaviour.Properties.of().strength(2.5F, 3.0F).sound(SoundType.METAL)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_LABORATORY_VENT = register("enlighted_laboratory_vent",
            () -> new LaboratoryBlock(ModBlocks.LABORATORY_VENT, BlockBehaviour.Properties.of().strength(2.5F, 3.0F).sound(SoundType.METAL).lightLevel((p_187433_) -> 14)));

    public static final RegistryObject<LaboratoryBlock> LABORATORY_VENT_CONNECTING = register("laboratory_vent_connecting",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_LABORATORY_VENT_CONNECTING, BlockBehaviour.Properties.of().strength(2.5F, 3.0F).sound(SoundType.METAL)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_LABORATORY_VENT_CONNECTING = register("enlighted_laboratory_vent_connecting",
            () -> new LaboratoryBlock(ModBlocks.LABORATORY_VENT_CONNECTING, BlockBehaviour.Properties.of().strength(2.5F, 3.0F).sound(SoundType.METAL).lightLevel((p_187433_) -> 14)));

    public static final RegistryObject<LaboratoryBlock> PLA_BLOCK = register("pla_block",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_PLA_BLOCK, BlockBehaviour.Properties.of().strength(0.5F, 0.5F).sound(SoundType.BONE_BLOCK)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_PLA_BLOCK = register("enlighted_pla_block",
            () -> new LaboratoryBlock(ModBlocks.PLA_BLOCK, BlockBehaviour.Properties.of().strength(0.5F, 0.5F).sound(SoundType.BONE_BLOCK).lightLevel((p_187433_) -> 14)));

    public static final RegistryObject<LaboratoryBlock> PLA_TILES = register("pla_tiles",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_PLA_TILES, BlockBehaviour.Properties.of().strength(0.5F, 0.5F).sound(SoundType.BONE_BLOCK)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_PLA_TILES = register("enlighted_pla_tiles",
            () -> new LaboratoryBlock(ModBlocks.PLA_TILES, BlockBehaviour.Properties.of().strength(0.5F, 0.5F).sound(SoundType.BONE_BLOCK).lightLevel((p_187433_) -> 14)));

    public static final RegistryObject<LaboratoryCarpetBlock> PLA_FLOORING = register("pla_flooring",
            () -> new LaboratoryCarpetBlock(ModBlocks.ENLIGHTED_PLA_FLOORING, BlockBehaviour.Properties.of().ignitedByLava().strength(0.1F).sound(SoundType.BONE_BLOCK)));

    public static final RegistryObject<LaboratoryCarpetBlock> ENLIGHTED_PLA_FLOORING = register("enlighted_pla_flooring",
            () -> new LaboratoryCarpetBlock(ModBlocks.PLA_FLOORING, BlockBehaviour.Properties.of().ignitedByLava().strength(0.1F).sound(SoundType.BONE_BLOCK).lightLevel((p_187433_) -> 14)));

    public static final RegistryObject<LaboratoryCarpetBlock> TILED_PLA_FLOORING = register("tiled_pla_flooring",
            () -> new LaboratoryCarpetBlock(ModBlocks.ENLIGHTED_TILED_PLA_FLOORING, BlockBehaviour.Properties.of().ignitedByLava().strength(0.1F).sound(SoundType.BONE_BLOCK)));

    public static final RegistryObject<LaboratoryCarpetBlock> ENLIGHTED_TILED_PLA_FLOORING = register("enlighted_tiled_pla_flooring",
            () -> new LaboratoryCarpetBlock(ModBlocks.TILED_PLA_FLOORING, BlockBehaviour.Properties.of().ignitedByLava().strength(0.1F).sound(SoundType.BONE_BLOCK).lightLevel((p_187433_) -> 14)));

    public static final RegistryObject<LaboratoryBlock> LABORATORY_BLOCK = register("laboratory_block",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_LABORATORY_BLOCK, BlockBehaviour.Properties.of().strength(2.5F, 3.0F).sound(SoundType.METAL)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_LABORATORY_BLOCK = register("enlighted_laboratory_block",
            () -> new LaboratoryBlock(ModBlocks.LABORATORY_BLOCK, BlockBehaviour.Properties.of().strength(2.5F, 3.0F).sound(SoundType.METAL).lightLevel((p_187433_) -> 14)));

    public static final RegistryObject<LaboratoryBlock> SCREWED_LABORATORY_BLOCK = register("screwed_laboratory_block",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_SCREWED_LABORATORY_BLOCK, BlockBehaviour.Properties.of().strength(2.5F, 3.0F).sound(SoundType.METAL)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_SCREWED_LABORATORY_BLOCK = register("enlighted_screwed_laboratory_block",
            () -> new LaboratoryBlock(ModBlocks.SCREWED_LABORATORY_BLOCK, BlockBehaviour.Properties.of().strength(2.5F, 3.0F).sound(SoundType.METAL).lightLevel((p_187433_) -> 14)));

    public static final RegistryObject<LaboratoryBlock> LABORATORY_TILES = register("laboratory_tiles",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_LABORATORY_TILES, BlockBehaviour.Properties.of().strength(2.5F, 3.0F).sound(SoundType.METAL)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_LABORATORY_TILES = register("enlighted_laboratory_tiles",
            () -> new LaboratoryBlock(ModBlocks.LABORATORY_TILES, BlockBehaviour.Properties.of().strength(2.5F, 3.0F).sound(SoundType.METAL).lightLevel((p_187433_) -> 14)));

    public static final RegistryObject<LaboratoryBlock> GRAY_LABORATORY_TILES = register("gray_laboratory_tiles",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_GRAY_LABORATORY_TILES, BlockBehaviour.Properties.of().strength(2.5F, 3.0F).sound(SoundType.METAL)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_GRAY_LABORATORY_TILES = register("enlighted_gray_laboratory_tiles",
            () -> new LaboratoryBlock(ModBlocks.GRAY_LABORATORY_TILES, BlockBehaviour.Properties.of().strength(2.5F, 3.0F).sound(SoundType.METAL).lightLevel((p_187433_) -> 14)));

    public static final RegistryObject<LaboratoryBlock> MIXED_LABORATORY_TILES = register("mixed_laboratory_tiles",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_MIXED_LABORATORY_TILES, BlockBehaviour.Properties.of().strength(2.5F, 3.0F).sound(SoundType.METAL)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_MIXED_LABORATORY_TILES = register("enlighted_mixed_laboratory_tiles",
            () -> new LaboratoryBlock(ModBlocks.MIXED_LABORATORY_TILES, BlockBehaviour.Properties.of().strength(2.5F, 3.0F).sound(SoundType.METAL).lightLevel((p_187433_) -> 14)));

    public static final RegistryObject<LaboratoryBlock> OAK_LABORATORY_FLOOR = register("oak_laboratory_floor",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_OAK_LABORATORY_FLOOR, BlockBehaviour.Properties.of().ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_OAK_LABORATORY_FLOOR = register("enlighted_oak_laboratory_floor",
            () -> new LaboratoryBlock(ModBlocks.OAK_LABORATORY_FLOOR, BlockBehaviour.Properties.of().ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD).lightLevel((p_187433_) -> 14)));

    public static final RegistryObject<LaboratoryBlock> SPRUCE_LABORATORY_FLOOR = register("spruce_laboratory_floor",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_SPRUCE_LABORATORY_FLOOR, BlockBehaviour.Properties.of().ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_SPRUCE_LABORATORY_FLOOR = register("enlighted_spruce_laboratory_floor",
            () -> new LaboratoryBlock(ModBlocks.SPRUCE_LABORATORY_FLOOR, BlockBehaviour.Properties.of().ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD).lightLevel((p_187433_) -> 14)));

    public static final RegistryObject<LaboratoryBlock> BIRCH_LABORATORY_FLOOR = register("birch_laboratory_floor",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_BIRCH_LABORATORY_FLOOR, BlockBehaviour.Properties.of().ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_BIRCH_LABORATORY_FLOOR = register("enlighted_birch_laboratory_floor",
            () -> new LaboratoryBlock(ModBlocks.BIRCH_LABORATORY_FLOOR, BlockBehaviour.Properties.of().ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD).lightLevel((p_187433_) -> 14)));

    public static final RegistryObject<LaboratoryBlock> DARK_OAK_LABORATORY_FLOOR = register("dark_oak_laboratory_floor",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_DARK_OAK_LABORATORY_FLOOR, BlockBehaviour.Properties.of().ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_DARK_OAK_LABORATORY_FLOOR = register("enlighted_dark_oak_laboratory_floor",
            () -> new LaboratoryBlock(ModBlocks.DARK_OAK_LABORATORY_FLOOR, BlockBehaviour.Properties.of().ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD).lightLevel((p_187433_) -> 14)));

    public static final RegistryObject<LaboratoryBlock> JUNGLE_LABORATORY_FLOOR = register("jungle_laboratory_floor",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_JUNGLE_LABORATORY_FLOOR, BlockBehaviour.Properties.of().ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_JUNGLE_LABORATORY_FLOOR = register("enlighted_jungle_laboratory_floor",
            () -> new LaboratoryBlock(ModBlocks.JUNGLE_LABORATORY_FLOOR, BlockBehaviour.Properties.of().ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD).lightLevel((p_187433_) -> 14)));

    public static final RegistryObject<LaboratoryBlock> ACACIA_LABORATORY_FLOOR = register("acacia_laboratory_floor",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_ACACIA_LABORATORY_FLOOR, BlockBehaviour.Properties.of().ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_ACACIA_LABORATORY_FLOOR = register("enlighted_acacia_laboratory_floor",
            () -> new LaboratoryBlock(ModBlocks.ACACIA_LABORATORY_FLOOR, BlockBehaviour.Properties.of().ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD).lightLevel((p_187433_) -> 14)));

    public static final RegistryObject<LaboratoryBlock> MANGROVE_LABORATORY_FLOOR = register("mangrove_laboratory_floor",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_MANGROVE_LABORATORY_FLOOR, BlockBehaviour.Properties.of().ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_MANGROVE_LABORATORY_FLOOR = register("enlighted_mangrove_laboratory_floor",
            () -> new LaboratoryBlock(ModBlocks.MANGROVE_LABORATORY_FLOOR, BlockBehaviour.Properties.of().ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD).lightLevel((p_187433_) -> 14)));

    public static final RegistryObject<LaboratoryBlock> CHERRY_LABORATORY_FLOOR = register("cherry_laboratory_floor",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_CHERRY_LABORATORY_FLOOR, BlockBehaviour.Properties.of().ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.CHERRY_WOOD)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_CHERRY_LABORATORY_FLOOR = register("enlighted_cherry_laboratory_floor",
            () -> new LaboratoryBlock(ModBlocks.CHERRY_LABORATORY_FLOOR, BlockBehaviour.Properties.of().ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.CHERRY_WOOD).lightLevel((p_187433_) -> 14)));

    public static final RegistryObject<LaboratoryBlock> BAMBOO_LABORATORY_FLOOR = register("bamboo_laboratory_floor",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_BAMBOO_LABORATORY_FLOOR, BlockBehaviour.Properties.of().ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.BAMBOO_WOOD)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_BAMBOO_LABORATORY_FLOOR = register("enlighted_bamboo_laboratory_floor",
            () -> new LaboratoryBlock(ModBlocks.BAMBOO_LABORATORY_FLOOR, BlockBehaviour.Properties.of().ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.BAMBOO_WOOD).lightLevel((p_187433_) -> 14)));

    public static final RegistryObject<LaboratoryBlock> CRIMSON_LABORATORY_FLOOR = register("crimson_laboratory_floor",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_CRIMSON_LABORATORY_FLOOR, BlockBehaviour.Properties.of().ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_CRIMSON_LABORATORY_FLOOR = register("enlighted_crimson_laboratory_floor",
            () -> new LaboratoryBlock(ModBlocks.CRIMSON_LABORATORY_FLOOR, BlockBehaviour.Properties.of().ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD).lightLevel((p_187433_) -> 14)));

    public static final RegistryObject<LaboratoryBlock> WARPED_LABORATORY_FLOOR = register("warped_laboratory_floor",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_WARPED_LABORATORY_FLOOR, BlockBehaviour.Properties.of().ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_WARPED_LABORATORY_FLOOR = register("enlighted_warped_laboratory_floor",
            () -> new LaboratoryBlock(ModBlocks.WARPED_LABORATORY_FLOOR, BlockBehaviour.Properties.of().ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD).lightLevel((p_187433_) -> 14)));

    public static final RegistryObject<LaboratoryGlassBlock> LABORATORY_GLASS = register("laboratory_glass",
            () -> new LaboratoryGlassBlock(ModBlocks.ENLIGHTED_LABORATORY_GLASS, BlockBehaviour.Properties.of().strength(0.3F).sound(SoundType.GLASS).noOcclusion().isValidSpawn(ModBlocks::never).isRedstoneConductor(ModBlocks::never).isSuffocating(ModBlocks::never).isViewBlocking(ModBlocks::never)));

    public static final RegistryObject<LaboratoryGlassBlock> ENLIGHTED_LABORATORY_GLASS = register("enlighted_laboratory_glass",
            () -> new LaboratoryGlassBlock(ModBlocks.LABORATORY_GLASS, BlockBehaviour.Properties.of().strength(0.3F).sound(SoundType.GLASS).noOcclusion().isValidSpawn(ModBlocks::never).isRedstoneConductor(ModBlocks::never).isSuffocating(ModBlocks::never).isViewBlocking(ModBlocks::never).lightLevel((p_187433_) -> 14)));

    public static final RegistryObject<LaboratoryBlock> OAK_LABORATORY_TILES = register("oak_laboratory_tiles",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_OAK_LABORATORY_TILES, BlockBehaviour.Properties.of().ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_OAK_LABORATORY_TILES = register("enlighted_oak_laboratory_tiles",
            () -> new LaboratoryBlock(ModBlocks.OAK_LABORATORY_TILES, BlockBehaviour.Properties.of().ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD).lightLevel((p_187433_) -> 14)));

    public static final RegistryObject<LaboratoryBlock> SPRUCE_LABORATORY_TILES = register("spruce_laboratory_tiles",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_SPRUCE_LABORATORY_TILES, BlockBehaviour.Properties.of().ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_SPRUCE_LABORATORY_TILES = register("enlighted_spruce_laboratory_tiles",
            () -> new LaboratoryBlock(ModBlocks.SPRUCE_LABORATORY_TILES, BlockBehaviour.Properties.of().ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD).lightLevel((p_187433_) -> 14)));

    public static final RegistryObject<LaboratoryBlock> BIRCH_LABORATORY_TILES = register("birch_laboratory_tiles",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_BIRCH_LABORATORY_TILES, BlockBehaviour.Properties.of().ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_BIRCH_LABORATORY_TILES = register("enlighted_birch_laboratory_tiles",
            () -> new LaboratoryBlock(ModBlocks.BIRCH_LABORATORY_TILES, BlockBehaviour.Properties.of().ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD).lightLevel((p_187433_) -> 14)));

    public static final RegistryObject<LaboratoryBlock> DARK_OAK_LABORATORY_TILES = register("dark_oak_laboratory_tiles",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_DARK_OAK_LABORATORY_TILES, BlockBehaviour.Properties.of().ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_DARK_OAK_LABORATORY_TILES = register("enlighted_dark_oak_laboratory_tiles",
            () -> new LaboratoryBlock(ModBlocks.DARK_OAK_LABORATORY_TILES, BlockBehaviour.Properties.of().ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD).lightLevel((p_187433_) -> 14)));

    public static final RegistryObject<LaboratoryBlock> JUNGLE_LABORATORY_TILES = register("jungle_laboratory_tiles",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_JUNGLE_LABORATORY_TILES, BlockBehaviour.Properties.of().ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_JUNGLE_LABORATORY_TILES = register("enlighted_jungle_laboratory_tiles",
            () -> new LaboratoryBlock(ModBlocks.JUNGLE_LABORATORY_TILES, BlockBehaviour.Properties.of().ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD).lightLevel((p_187433_) -> 14)));

    public static final RegistryObject<LaboratoryBlock> ACACIA_LABORATORY_TILES = register("acacia_laboratory_tiles",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_ACACIA_LABORATORY_TILES, BlockBehaviour.Properties.of().ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_ACACIA_LABORATORY_TILES = register("enlighted_acacia_laboratory_tiles",
            () -> new LaboratoryBlock(ModBlocks.ACACIA_LABORATORY_TILES, BlockBehaviour.Properties.of().ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD).lightLevel((p_187433_) -> 14)));

    public static final RegistryObject<LaboratoryBlock> MANGROVE_LABORATORY_TILES = register("mangrove_laboratory_tiles",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_MANGROVE_LABORATORY_TILES, BlockBehaviour.Properties.of().ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_MANGROVE_LABORATORY_TILES = register("enlighted_mangrove_laboratory_tiles",
            () -> new LaboratoryBlock(ModBlocks.MANGROVE_LABORATORY_TILES, BlockBehaviour.Properties.of().ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD).lightLevel((p_187433_) -> 14)));

    public static final RegistryObject<LaboratoryBlock> CHERRY_LABORATORY_TILES = register("cherry_laboratory_tiles",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_CHERRY_LABORATORY_TILES, BlockBehaviour.Properties.of().ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.CHERRY_WOOD)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_CHERRY_LABORATORY_TILES = register("enlighted_cherry_laboratory_tiles",
            () -> new LaboratoryBlock(ModBlocks.CHERRY_LABORATORY_TILES, BlockBehaviour.Properties.of().ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.CHERRY_WOOD).lightLevel((p_187433_) -> 14)));

    public static final RegistryObject<LaboratoryBlock> BAMBOO_LABORATORY_TILES = register("bamboo_laboratory_tiles",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_BAMBOO_LABORATORY_TILES, BlockBehaviour.Properties.of().ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.BAMBOO_WOOD)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_BAMBOO_LABORATORY_TILES = register("enlighted_bamboo_laboratory_tiles",
            () -> new LaboratoryBlock(ModBlocks.BAMBOO_LABORATORY_TILES, BlockBehaviour.Properties.of().ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.BAMBOO_WOOD).lightLevel((p_187433_) -> 14)));

    public static final RegistryObject<LaboratoryBlock> CRIMSON_LABORATORY_TILES = register("crimson_laboratory_tiles",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_CRIMSON_LABORATORY_TILES, BlockBehaviour.Properties.of().ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_CRIMSON_LABORATORY_TILES = register("enlighted_crimson_laboratory_tiles",
            () -> new LaboratoryBlock(ModBlocks.CRIMSON_LABORATORY_TILES, BlockBehaviour.Properties.of().ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD).lightLevel((p_187433_) -> 14)));

    public static final RegistryObject<LaboratoryBlock> WARPED_LABORATORY_TILES = register("warped_laboratory_tiles",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_WARPED_LABORATORY_TILES, BlockBehaviour.Properties.of().ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_WARPED_LABORATORY_TILES = register("enlighted_warped_laboratory_tiles",
            () -> new LaboratoryBlock(ModBlocks.WARPED_LABORATORY_TILES, BlockBehaviour.Properties.of().ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD).lightLevel((p_187433_) -> 14)));

}
