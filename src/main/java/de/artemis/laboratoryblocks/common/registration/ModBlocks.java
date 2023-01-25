package de.artemis.laboratoryblocks.common.registration;

import de.artemis.laboratoryblocks.LaboratoryBlocks;
import de.artemis.laboratoryblocks.common.blocks.LaboratoryBlock;
import de.artemis.laboratoryblocks.common.blocks.LaboratoryCarpetBlock;
import de.artemis.laboratoryblocks.common.blocks.LaboratoryGlassBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = Registration.BLOCKS.register(name, block);
        Registration.ITEMS.register(name, () -> new BlockItem(toReturn.get(),
                new Item.Properties()));

        return toReturn;
    }

    private static <T extends Block> RegistryObject<T> registerWithTab(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = Registration.BLOCKS.register(name, block);
        Registration.ITEMS.register(name, () -> new BlockItem(toReturn.get(),
                new Item.Properties().tab(LaboratoryBlocks.INVENTORY_TAB)));

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

    public static final RegistryObject<LaboratoryBlock> LABORATORY_FAN = registerWithTab("laboratory_fan",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_LABORATORY_FAN, BlockBehaviour.Properties.of(Material.METAL).strength(2.5F, 3.0F).sound(SoundType.METAL)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_LABORATORY_FAN = registerWithTab("enlighted_laboratory_fan",
            () -> new LaboratoryBlock(ModBlocks.LABORATORY_FAN, BlockBehaviour.Properties.of(Material.METAL).strength(2.5F, 3.0F).sound(SoundType.METAL).lightLevel((p_187433_) -> 10)));

    public static final RegistryObject<LaboratoryBlock> LEFT_FACED_BLUE_SIGNALING_LABORATORY_BLOCK = registerWithTab("left-faced_blue_signaling_laboratory_block",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_LEFT_FACED_BLUE_SIGNALING_LABORATORY_BLOCK, BlockBehaviour.Properties.of(Material.METAL).strength(2.5F, 3.0F).sound(SoundType.METAL)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_LEFT_FACED_BLUE_SIGNALING_LABORATORY_BLOCK = registerWithTab("enlighted_left-faced_blue_signaling_laboratory_block",
            () -> new LaboratoryBlock(ModBlocks.LEFT_FACED_BLUE_SIGNALING_LABORATORY_BLOCK, BlockBehaviour.Properties.of(Material.METAL).strength(2.5F, 3.0F).sound(SoundType.METAL).lightLevel((p_187433_) -> 10)));

    public static final RegistryObject<LaboratoryBlock> RIGHT_FACED_BLUE_SIGNALING_LABORATORY_BLOCK = registerWithTab("right-faced_blue_signaling_laboratory_block",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_RIGHT_FACED_BLUE_SIGNALING_LABORATORY_BLOCK, BlockBehaviour.Properties.of(Material.METAL).strength(2.5F, 3.0F).sound(SoundType.METAL)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_RIGHT_FACED_BLUE_SIGNALING_LABORATORY_BLOCK = registerWithTab("enlighted_right-faced_blue_signaling_laboratory_block",
            () -> new LaboratoryBlock(ModBlocks.RIGHT_FACED_BLUE_SIGNALING_LABORATORY_BLOCK, BlockBehaviour.Properties.of(Material.METAL).strength(2.5F, 3.0F).sound(SoundType.METAL).lightLevel((p_187433_) -> 10)));

    public static final RegistryObject<LaboratoryBlock> LEFT_FACED_RED_SIGNALING_LABORATORY_BLOCK = registerWithTab("left-faced_red_signaling_laboratory_block",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_LEFT_FACED_RED_SIGNALING_LABORATORY_BLOCK, BlockBehaviour.Properties.of(Material.METAL).strength(2.5F, 3.0F).sound(SoundType.METAL)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_LEFT_FACED_RED_SIGNALING_LABORATORY_BLOCK = registerWithTab("enlighted_left-faced_red_signaling_laboratory_block",
            () -> new LaboratoryBlock(ModBlocks.LEFT_FACED_RED_SIGNALING_LABORATORY_BLOCK, BlockBehaviour.Properties.of(Material.METAL).strength(2.5F, 3.0F).sound(SoundType.METAL).lightLevel((p_187433_) -> 10)));

    public static final RegistryObject<LaboratoryBlock> RIGHT_FACED_RED_SIGNALING_LABORATORY_BLOCK = registerWithTab("right-faced_red_signaling_laboratory_block",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_RIGHT_FACED_RED_SIGNALING_LABORATORY_BLOCK, BlockBehaviour.Properties.of(Material.METAL).strength(2.5F, 3.0F).sound(SoundType.METAL)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_RIGHT_FACED_RED_SIGNALING_LABORATORY_BLOCK = registerWithTab("enlighted_right-faced_red_signaling_laboratory_block",
            () -> new LaboratoryBlock(ModBlocks.RIGHT_FACED_RED_SIGNALING_LABORATORY_BLOCK, BlockBehaviour.Properties.of(Material.METAL).strength(2.5F, 3.0F).sound(SoundType.METAL).lightLevel((p_187433_) -> 10)));

    public static final RegistryObject<LaboratoryBlock> LEFT_FACED_GREEN_SIGNALING_LABORATORY_BLOCK = registerWithTab("left-faced_green_signaling_laboratory_block",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_LEFT_FACED_GREEN_SIGNALING_LABORATORY_BLOCK, BlockBehaviour.Properties.of(Material.METAL).strength(2.5F, 3.0F).sound(SoundType.METAL)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_LEFT_FACED_GREEN_SIGNALING_LABORATORY_BLOCK = registerWithTab("enlighted_left-faced_green_signaling_laboratory_block",
            () -> new LaboratoryBlock(ModBlocks.LEFT_FACED_GREEN_SIGNALING_LABORATORY_BLOCK, BlockBehaviour.Properties.of(Material.METAL).strength(2.5F, 3.0F).sound(SoundType.METAL).lightLevel((p_187433_) -> 10)));

    public static final RegistryObject<LaboratoryBlock> RIGHT_FACED_GREEN_SIGNALING_LABORATORY_BLOCK = registerWithTab("right-faced_green_signaling_laboratory_block",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_RIGHT_FACED_GREEN_SIGNALING_LABORATORY_BLOCK, BlockBehaviour.Properties.of(Material.METAL).strength(2.5F, 3.0F).sound(SoundType.METAL)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_RIGHT_FACED_GREEN_SIGNALING_LABORATORY_BLOCK = registerWithTab("enlighted_right-faced_green_signaling_laboratory_block",
            () -> new LaboratoryBlock(ModBlocks.RIGHT_FACED_GREEN_SIGNALING_LABORATORY_BLOCK, BlockBehaviour.Properties.of(Material.METAL).strength(2.5F, 3.0F).sound(SoundType.METAL).lightLevel((p_187433_) -> 10)));

    public static final RegistryObject<LaboratoryBlock> LABORATORY_VENT = registerWithTab("laboratory_vent",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_LABORATORY_VENT, BlockBehaviour.Properties.of(Material.METAL).strength(2.5F, 3.0F).sound(SoundType.METAL)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_LABORATORY_VENT = registerWithTab("enlighted_laboratory_vent",
            () -> new LaboratoryBlock(ModBlocks.LABORATORY_VENT, BlockBehaviour.Properties.of(Material.METAL).strength(2.5F, 3.0F).sound(SoundType.METAL).lightLevel((p_187433_) -> 10)));

    public static final RegistryObject<LaboratoryBlock> PLA_BLOCK = registerWithTab("pla_block",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_PLA_BLOCK, BlockBehaviour.Properties.of(Material.STONE).strength(0.5F, 0.5F).sound(SoundType.SCAFFOLDING)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_PLA_BLOCK = registerWithTab("enlighted_pla_block",
            () -> new LaboratoryBlock(ModBlocks.PLA_BLOCK, BlockBehaviour.Properties.of(Material.STONE).strength(0.5F, 0.5F).sound(SoundType.SCAFFOLDING).lightLevel((p_187433_) -> 10)));

    public static final RegistryObject<LaboratoryBlock> PLA_TILES = registerWithTab("pla_tiles",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_PLA_TILES, BlockBehaviour.Properties.of(Material.STONE).strength(0.5F, 0.5F).sound(SoundType.SCAFFOLDING)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_PLA_TILES = registerWithTab("enlighted_pla_tiles",
            () -> new LaboratoryBlock(ModBlocks.PLA_TILES, BlockBehaviour.Properties.of(Material.STONE).strength(0.5F, 0.5F).sound(SoundType.SCAFFOLDING).lightLevel((p_187433_) -> 10)));

    public static final RegistryObject<LaboratoryCarpetBlock> PLA_FLOORING = registerWithTab("pla_flooring",
            () -> new LaboratoryCarpetBlock(ModBlocks.ENLIGHTED_PLA_FLOORING, BlockBehaviour.Properties.of(Material.CLOTH_DECORATION).strength(0.1F).sound(SoundType.SCAFFOLDING)));

    public static final RegistryObject<LaboratoryCarpetBlock> ENLIGHTED_PLA_FLOORING = registerWithTab("enlighted_pla_flooring",
            () -> new LaboratoryCarpetBlock(ModBlocks.PLA_FLOORING, BlockBehaviour.Properties.of(Material.CLOTH_DECORATION).strength(0.1F).sound(SoundType.SCAFFOLDING).lightLevel((p_187433_) -> 10)));

    public static final RegistryObject<LaboratoryCarpetBlock> TILED_PLA_FLOORING = registerWithTab("tiled_pla_flooring",
            () -> new LaboratoryCarpetBlock(ModBlocks.ENLIGHTED_TILED_PLA_FLOORING, BlockBehaviour.Properties.of(Material.CLOTH_DECORATION).strength(0.1F).sound(SoundType.SCAFFOLDING)));

    public static final RegistryObject<LaboratoryCarpetBlock> ENLIGHTED_TILED_PLA_FLOORING = registerWithTab("enlighted_tiled_pla_flooring",
            () -> new LaboratoryCarpetBlock(ModBlocks.TILED_PLA_FLOORING, BlockBehaviour.Properties.of(Material.CLOTH_DECORATION).strength(0.1F).sound(SoundType.SCAFFOLDING).lightLevel((p_187433_) -> 10)));

    public static final RegistryObject<LaboratoryBlock> LABORATORY_BLOCK = registerWithTab("laboratory_block",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_LABORATORY_BLOCK, BlockBehaviour.Properties.of(Material.METAL).strength(2.5F, 3.0F).sound(SoundType.METAL)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_LABORATORY_BLOCK = registerWithTab("enlighted_laboratory_block",
            () -> new LaboratoryBlock(ModBlocks.LABORATORY_BLOCK, BlockBehaviour.Properties.of(Material.METAL).strength(2.5F, 3.0F).sound(SoundType.METAL).lightLevel((p_187433_) -> 10)));

    public static final RegistryObject<LaboratoryBlock> LABORATORY_TILES = registerWithTab("laboratory_tiles",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_LABORATORY_TILES, BlockBehaviour.Properties.of(Material.METAL).strength(2.5F, 3.0F).sound(SoundType.METAL)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_LABORATORY_TILES = registerWithTab("enlighted_laboratory_tiles",
            () -> new LaboratoryBlock(ModBlocks.LABORATORY_TILES, BlockBehaviour.Properties.of(Material.METAL).strength(2.5F, 3.0F).sound(SoundType.METAL).lightLevel((p_187433_) -> 10)));

    public static final RegistryObject<LaboratoryBlock> GRAY_LABORATORY_TILES = registerWithTab("gray_laboratory_tiles",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_GRAY_LABORATORY_TILES, BlockBehaviour.Properties.of(Material.METAL).strength(2.5F, 3.0F).sound(SoundType.METAL)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_GRAY_LABORATORY_TILES = registerWithTab("enlighted_gray_laboratory_tiles",
            () -> new LaboratoryBlock(ModBlocks.GRAY_LABORATORY_TILES, BlockBehaviour.Properties.of(Material.METAL).strength(2.5F, 3.0F).sound(SoundType.METAL).lightLevel((p_187433_) -> 10)));

    public static final RegistryObject<LaboratoryBlock> MIXED_LABORATORY_TILES = registerWithTab("mixed_laboratory_tiles",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_MIXED_LABORATORY_TILES, BlockBehaviour.Properties.of(Material.METAL).strength(2.5F, 3.0F).sound(SoundType.METAL)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_MIXED_LABORATORY_TILES = registerWithTab("enlighted_mixed_laboratory_tiles",
            () -> new LaboratoryBlock(ModBlocks.MIXED_LABORATORY_TILES, BlockBehaviour.Properties.of(Material.METAL).strength(2.5F, 3.0F).sound(SoundType.METAL).lightLevel((p_187433_) -> 10)));

    public static final RegistryObject<LaboratoryBlock> OAK_LABORATORY_FLOOR = registerWithTab("oak_laboratory_floor",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_OAK_LABORATORY_FLOOR, BlockBehaviour.Properties.of(Material.WOOD).strength(2.5F, 3.0F).sound(SoundType.WOOD)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_OAK_LABORATORY_FLOOR = registerWithTab("enlighted_oak_laboratory_floor",
            () -> new LaboratoryBlock(ModBlocks.OAK_LABORATORY_FLOOR, BlockBehaviour.Properties.of(Material.WOOD).strength(2.5F, 3.0F).sound(SoundType.WOOD).lightLevel((p_187433_) -> 10)));

    public static final RegistryObject<LaboratoryBlock> SPRUCE_LABORATORY_FLOOR = registerWithTab("spruce_laboratory_floor",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_SPRUCE_LABORATORY_FLOOR, BlockBehaviour.Properties.of(Material.WOOD).strength(2.5F, 3.0F).sound(SoundType.WOOD)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_SPRUCE_LABORATORY_FLOOR = registerWithTab("enlighted_spruce_laboratory_floor",
            () -> new LaboratoryBlock(ModBlocks.SPRUCE_LABORATORY_FLOOR, BlockBehaviour.Properties.of(Material.WOOD).strength(2.5F, 3.0F).sound(SoundType.WOOD).lightLevel((p_187433_) -> 10)));

    public static final RegistryObject<LaboratoryBlock> BIRCH_LABORATORY_FLOOR = registerWithTab("birch_laboratory_floor",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_BIRCH_LABORATORY_FLOOR, BlockBehaviour.Properties.of(Material.WOOD).strength(2.5F, 3.0F).sound(SoundType.WOOD)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_BIRCH_LABORATORY_FLOOR = registerWithTab("enlighted_birch_laboratory_floor",
            () -> new LaboratoryBlock(ModBlocks.BIRCH_LABORATORY_FLOOR, BlockBehaviour.Properties.of(Material.WOOD).strength(2.5F, 3.0F).sound(SoundType.WOOD).lightLevel((p_187433_) -> 10)));

    public static final RegistryObject<LaboratoryBlock> DARK_OAK_LABORATORY_FLOOR = registerWithTab("dark_oak_laboratory_floor",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_DARK_OAK_LABORATORY_FLOOR, BlockBehaviour.Properties.of(Material.WOOD).strength(2.5F, 3.0F).sound(SoundType.WOOD)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_DARK_OAK_LABORATORY_FLOOR = registerWithTab("enlighted_dark_oak_laboratory_floor",
            () -> new LaboratoryBlock(ModBlocks.DARK_OAK_LABORATORY_FLOOR, BlockBehaviour.Properties.of(Material.WOOD).strength(2.5F, 3.0F).sound(SoundType.WOOD).lightLevel((p_187433_) -> 10)));

    public static final RegistryObject<LaboratoryBlock> JUNGLE_LABORATORY_FLOOR = registerWithTab("jungle_laboratory_floor",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_JUNGLE_LABORATORY_FLOOR, BlockBehaviour.Properties.of(Material.WOOD).strength(2.5F, 3.0F).sound(SoundType.WOOD)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_JUNGLE_LABORATORY_FLOOR = registerWithTab("enlighted_jungle_laboratory_floor",
            () -> new LaboratoryBlock(ModBlocks.JUNGLE_LABORATORY_FLOOR, BlockBehaviour.Properties.of(Material.WOOD).strength(2.5F, 3.0F).sound(SoundType.WOOD).lightLevel((p_187433_) -> 10)));

    public static final RegistryObject<LaboratoryBlock> ACACIA_LABORATORY_FLOOR = registerWithTab("acacia_laboratory_floor",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_ACACIA_LABORATORY_FLOOR, BlockBehaviour.Properties.of(Material.WOOD).strength(2.5F, 3.0F).sound(SoundType.WOOD)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_ACACIA_LABORATORY_FLOOR = registerWithTab("enlighted_acacia_laboratory_floor",
            () -> new LaboratoryBlock(ModBlocks.ACACIA_LABORATORY_FLOOR, BlockBehaviour.Properties.of(Material.WOOD).strength(2.5F, 3.0F).sound(SoundType.WOOD).lightLevel((p_187433_) -> 10)));

    public static final RegistryObject<LaboratoryBlock> CRIMSON_LABORATORY_FLOOR = registerWithTab("crimson_laboratory_floor",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_CRIMSON_LABORATORY_FLOOR, BlockBehaviour.Properties.of(Material.WOOD).strength(2.5F, 3.0F).sound(SoundType.WOOD)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_CRIMSON_LABORATORY_FLOOR = registerWithTab("enlighted_crimson_laboratory_floor",
            () -> new LaboratoryBlock(ModBlocks.CRIMSON_LABORATORY_FLOOR, BlockBehaviour.Properties.of(Material.WOOD).strength(2.5F, 3.0F).sound(SoundType.WOOD).lightLevel((p_187433_) -> 10)));

    public static final RegistryObject<LaboratoryBlock> WARPED_LABORATORY_FLOOR = registerWithTab("warped_laboratory_floor",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_WARPED_LABORATORY_FLOOR, BlockBehaviour.Properties.of(Material.WOOD).strength(2.5F, 3.0F).sound(SoundType.WOOD)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_WARPED_LABORATORY_FLOOR = registerWithTab("enlighted_warped_laboratory_floor",
            () -> new LaboratoryBlock(ModBlocks.WARPED_LABORATORY_FLOOR, BlockBehaviour.Properties.of(Material.WOOD).strength(2.5F, 3.0F).sound(SoundType.WOOD).lightLevel((p_187433_) -> 10)));

    public static final RegistryObject<LaboratoryGlassBlock> LABORATORY_GLASS = registerWithTab("laboratory_glass",
            () -> new LaboratoryGlassBlock(ModBlocks.ENLIGHTED_LABORATORY_GLASS, BlockBehaviour.Properties.of(Material.GLASS).strength(0.3F).sound(SoundType.GLASS).noOcclusion().isValidSpawn(ModBlocks::never).isRedstoneConductor(ModBlocks::never).isSuffocating(ModBlocks::never).isViewBlocking(ModBlocks::never)));

    public static final RegistryObject<LaboratoryGlassBlock> ENLIGHTED_LABORATORY_GLASS = registerWithTab("enlighted_laboratory_glass",
            () -> new LaboratoryGlassBlock(ModBlocks.LABORATORY_GLASS, BlockBehaviour.Properties.of(Material.GLASS).strength(0.3F).sound(SoundType.GLASS).noOcclusion().isValidSpawn(ModBlocks::never).isRedstoneConductor(ModBlocks::never).isSuffocating(ModBlocks::never).isViewBlocking(ModBlocks::never).lightLevel((p_187433_) -> 10)));

    public static final RegistryObject<LaboratoryBlock> OAK_LABORATORY_TILES = registerWithTab("oak_laboratory_tiles",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_OAK_LABORATORY_TILES, BlockBehaviour.Properties.of(Material.WOOD).strength(2.5F, 3.0F).sound(SoundType.WOOD)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_OAK_LABORATORY_TILES = registerWithTab("enlighted_oak_laboratory_tiles",
            () -> new LaboratoryBlock(ModBlocks.OAK_LABORATORY_TILES, BlockBehaviour.Properties.of(Material.WOOD).strength(2.5F, 3.0F).sound(SoundType.WOOD).lightLevel((p_187433_) -> 10)));

    public static final RegistryObject<LaboratoryBlock> SPRUCE_LABORATORY_TILES = registerWithTab("spruce_laboratory_tiles",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_SPRUCE_LABORATORY_TILES, BlockBehaviour.Properties.of(Material.WOOD).strength(2.5F, 3.0F).sound(SoundType.WOOD)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_SPRUCE_LABORATORY_TILES = registerWithTab("enlighted_spruce_laboratory_tiles",
            () -> new LaboratoryBlock(ModBlocks.SPRUCE_LABORATORY_TILES, BlockBehaviour.Properties.of(Material.WOOD).strength(2.5F, 3.0F).sound(SoundType.WOOD).lightLevel((p_187433_) -> 10)));

    public static final RegistryObject<LaboratoryBlock> BIRCH_LABORATORY_TILES = registerWithTab("birch_laboratory_tiles",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_BIRCH_LABORATORY_TILES, BlockBehaviour.Properties.of(Material.WOOD).strength(2.5F, 3.0F).sound(SoundType.WOOD)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_BIRCH_LABORATORY_TILES = registerWithTab("enlighted_birch_laboratory_tiles",
            () -> new LaboratoryBlock(ModBlocks.BIRCH_LABORATORY_TILES, BlockBehaviour.Properties.of(Material.WOOD).strength(2.5F, 3.0F).sound(SoundType.WOOD).lightLevel((p_187433_) -> 10)));

    public static final RegistryObject<LaboratoryBlock> DARK_OAK_LABORATORY_TILES = registerWithTab("dark_oak_laboratory_tiles",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_DARK_OAK_LABORATORY_TILES, BlockBehaviour.Properties.of(Material.WOOD).strength(2.5F, 3.0F).sound(SoundType.WOOD)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_DARK_OAK_LABORATORY_TILES = registerWithTab("enlighted_dark_oak_laboratory_tiles",
            () -> new LaboratoryBlock(ModBlocks.DARK_OAK_LABORATORY_TILES, BlockBehaviour.Properties.of(Material.WOOD).strength(2.5F, 3.0F).sound(SoundType.WOOD).lightLevel((p_187433_) -> 10)));

    public static final RegistryObject<LaboratoryBlock> JUNGLE_LABORATORY_TILES = registerWithTab("jungle_laboratory_tiles",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_JUNGLE_LABORATORY_TILES, BlockBehaviour.Properties.of(Material.WOOD).strength(2.5F, 3.0F).sound(SoundType.WOOD)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_JUNGLE_LABORATORY_TILES = registerWithTab("enlighted_jungle_laboratory_tiles",
            () -> new LaboratoryBlock(ModBlocks.JUNGLE_LABORATORY_TILES, BlockBehaviour.Properties.of(Material.WOOD).strength(2.5F, 3.0F).sound(SoundType.WOOD).lightLevel((p_187433_) -> 10)));

    public static final RegistryObject<LaboratoryBlock> ACACIA_LABORATORY_TILES = registerWithTab("acacia_laboratory_tiles",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_ACACIA_LABORATORY_TILES, BlockBehaviour.Properties.of(Material.WOOD).strength(2.5F, 3.0F).sound(SoundType.WOOD)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_ACACIA_LABORATORY_TILES = registerWithTab("enlighted_acacia_laboratory_tiles",
            () -> new LaboratoryBlock(ModBlocks.ACACIA_LABORATORY_TILES, BlockBehaviour.Properties.of(Material.WOOD).strength(2.5F, 3.0F).sound(SoundType.WOOD).lightLevel((p_187433_) -> 10)));

    public static final RegistryObject<LaboratoryBlock> CRIMSON_LABORATORY_TILES = registerWithTab("crimson_laboratory_tiles",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_CRIMSON_LABORATORY_TILES, BlockBehaviour.Properties.of(Material.WOOD).strength(2.5F, 3.0F).sound(SoundType.WOOD)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_CRIMSON_LABORATORY_TILES = registerWithTab("enlighted_crimson_laboratory_tiles",
            () -> new LaboratoryBlock(ModBlocks.CRIMSON_LABORATORY_TILES, BlockBehaviour.Properties.of(Material.WOOD).strength(2.5F, 3.0F).sound(SoundType.WOOD).lightLevel((p_187433_) -> 10)));

    public static final RegistryObject<LaboratoryBlock> WARPED_LABORATORY_TILES = registerWithTab("warped_laboratory_tiles",
            () -> new LaboratoryBlock(ModBlocks.ENLIGHTED_WARPED_LABORATORY_TILES, BlockBehaviour.Properties.of(Material.WOOD).strength(2.5F, 3.0F).sound(SoundType.WOOD)));

    public static final RegistryObject<LaboratoryBlock> ENLIGHTED_WARPED_LABORATORY_TILES = registerWithTab("enlighted_warped_laboratory_tiles",
            () -> new LaboratoryBlock(ModBlocks.WARPED_LABORATORY_TILES, BlockBehaviour.Properties.of(Material.WOOD).strength(2.5F, 3.0F).sound(SoundType.WOOD).lightLevel((p_187433_) -> 10)));

}
