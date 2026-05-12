package de.artemis.laboratoryblocks.common.registry;

import de.artemis.laboratoryblocks.LaboratoryBlocks;
import de.artemis.laboratoryblocks.common.block.LaboratoryBlock;
import de.artemis.laboratoryblocks.common.block.LaboratoryGlassBlock;
import de.artemis.laboratoryblocks.common.block.RedstoneControlledLaboratoryBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(LaboratoryBlocks.MOD_ID);

    private static <T extends Block> DeferredBlock<T> register(
            String name,
            Function<BlockBehaviour.Properties, T> blockFactory,
            UnaryOperator<BlockBehaviour.Properties> properties
    ) {
        DeferredBlock<T> block = BLOCKS.registerBlock(name, blockFactory, properties);
        ModItems.ITEMS.registerSimpleBlockItem(block, UnaryOperator.identity());
        return block;
    }

    private static boolean never(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, EntityType<?> entityType) {
        return false;
    }

    private static boolean never(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return false;
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

    public static final DeferredBlock<DoorBlock> LABORATORY_DOOR = register("laboratory_door",
            properties -> new DoorBlock(ModBlockSetTypes.LABORATORY, properties),
            properties -> properties.strength(3.0F).noOcclusion().pushReaction(PushReaction.DESTROY));

    public static final DeferredBlock<DoorBlock> MESH_LABORATORY_DOOR = register("mesh_laboratory_door",
            properties -> new DoorBlock(ModBlockSetTypes.LABORATORY, properties),
            properties -> properties.strength(3.0F).noOcclusion().pushReaction(PushReaction.DESTROY));

    public static final DeferredBlock<DoorBlock> GLASS_LABORATORY_DOOR = register("glass_laboratory_door",
            properties -> new DoorBlock(ModBlockSetTypes.LABORATORY, properties),
            properties -> properties.strength(3.0F).noOcclusion().pushReaction(PushReaction.DESTROY));

    public static final DeferredBlock<TrapDoorBlock> LABORATORY_TRAPDOOR = register("laboratory_trapdoor",
            properties -> new TrapDoorBlock(ModBlockSetTypes.LABORATORY, properties),
            properties -> properties.strength(3.0F).noOcclusion().pushReaction(PushReaction.DESTROY));

    public static final DeferredBlock<TrapDoorBlock> MESH_LABORATORY_TRAPDOOR = register("mesh_laboratory_trapdoor",
            properties -> new TrapDoorBlock(ModBlockSetTypes.LABORATORY, properties),
            properties -> properties.strength(3.0F).noOcclusion().pushReaction(PushReaction.DESTROY));

    public static final DeferredBlock<TrapDoorBlock> GLASS_LABORATORY_TRAPDOOR = register("glass_laboratory_trapdoor",
            properties -> new TrapDoorBlock(ModBlockSetTypes.LABORATORY, properties),
            properties -> properties.strength(3.0F).noOcclusion().pushReaction(PushReaction.DESTROY));

    public static final DeferredBlock<LaboratoryBlock> LABORATORY_PILLAR = register("laboratory_pillar",
            properties -> new LaboratoryBlock(ModBlocks.GLOWING_LABORATORY_PILLAR, properties),
            properties -> properties.strength(2.5F, 3.0F).sound(ModSoundTypes.LABORATORY_BLOCK));

    public static final DeferredBlock<LaboratoryBlock> GLOWING_LABORATORY_PILLAR = register("glowing_laboratory_pillar",
            properties -> new LaboratoryBlock(ModBlocks.LABORATORY_PILLAR, properties),
            properties -> properties.strength(2.5F, 3.0F).sound(ModSoundTypes.LABORATORY_BLOCK).lightLevel(state -> 14));

    public static final DeferredBlock<LaboratoryBlock> GRAY_LABORATORY_PILLAR = register("gray_laboratory_pillar",
            properties -> new LaboratoryBlock(ModBlocks.GLOWING_GRAY_LABORATORY_PILLAR, properties),
            properties -> properties.strength(2.5F, 3.0F).sound(ModSoundTypes.LABORATORY_BLOCK));

    public static final DeferredBlock<LaboratoryBlock> GLOWING_GRAY_LABORATORY_PILLAR = register("glowing_gray_laboratory_pillar",
            properties -> new LaboratoryBlock(ModBlocks.GRAY_LABORATORY_PILLAR, properties),
            properties -> properties.strength(2.5F, 3.0F).sound(ModSoundTypes.LABORATORY_BLOCK).lightLevel(state -> 14));

    public static final DeferredBlock<LaboratoryBlock> CLEAR_LABORATORY_SCREEN = register("clear_laboratory_screen",
            properties -> new LaboratoryBlock(ModBlocks.GLOWING_CLEAR_LABORATORY_SCREEN, properties),
            properties -> properties.strength(1.25F, 1.5F).sound(ModSoundTypes.LABORATORY_BLOCK));

    public static final DeferredBlock<LaboratoryBlock> GLOWING_CLEAR_LABORATORY_SCREEN = register("glowing_clear_laboratory_screen",
            properties -> new LaboratoryBlock(ModBlocks.CLEAR_LABORATORY_SCREEN, properties),
            properties -> properties.strength(1.25F, 1.5F).sound(ModSoundTypes.LABORATORY_BLOCK).lightLevel(state -> 14));

    public static final DeferredBlock<LaboratoryBlock> WAVE_LABORATORY_SCREEN = register("wave_laboratory_screen",
            properties -> new LaboratoryBlock(ModBlocks.GLOWING_WAVE_LABORATORY_SCREEN, properties),
            properties -> properties.strength(1.25F, 1.5F).sound(ModSoundTypes.LABORATORY_BLOCK));

    public static final DeferredBlock<LaboratoryBlock> GLOWING_WAVE_LABORATORY_SCREEN = register("glowing_wave_laboratory_screen",
            properties -> new LaboratoryBlock(ModBlocks.WAVE_LABORATORY_SCREEN, properties),
            properties -> properties.strength(1.25F, 1.5F).sound(ModSoundTypes.LABORATORY_BLOCK).lightLevel(state -> 14));

    public static final DeferredBlock<LaboratoryBlock> TEXT_LABORATORY_SCREEN = register("text_laboratory_screen",
            properties -> new LaboratoryBlock(ModBlocks.GLOWING_TEXT_LABORATORY_SCREEN, properties),
            properties -> properties.strength(1.25F, 1.5F).sound(ModSoundTypes.LABORATORY_BLOCK));

    public static final DeferredBlock<LaboratoryBlock> GLOWING_TEXT_LABORATORY_SCREEN = register("glowing_text_laboratory_screen",
            properties -> new LaboratoryBlock(ModBlocks.TEXT_LABORATORY_SCREEN, properties),
            properties -> properties.strength(1.25F, 1.5F).sound(ModSoundTypes.LABORATORY_BLOCK).lightLevel(state -> 14));

    public static final DeferredBlock<LaboratoryBlock> QUANTUM_LABORATORY_SCREEN = register("quantum_laboratory_screen",
            properties -> new LaboratoryBlock(ModBlocks.GLOWING_QUANTUM_LABORATORY_SCREEN, properties),
            properties -> properties.strength(1.25F, 1.5F).sound(ModSoundTypes.LABORATORY_BLOCK));

    public static final DeferredBlock<LaboratoryBlock> GLOWING_QUANTUM_LABORATORY_SCREEN = register("glowing_quantum_laboratory_screen",
            properties -> new LaboratoryBlock(ModBlocks.QUANTUM_LABORATORY_SCREEN, properties),
            properties -> properties.strength(1.25F, 1.5F).sound(ModSoundTypes.LABORATORY_BLOCK).lightLevel(state -> 14));

    public static final DeferredBlock<RedstoneControlledLaboratoryBlock> LABORATORY_FAN = register("laboratory_fan",
            properties -> new RedstoneControlledLaboratoryBlock(ModBlocks.GLOWING_LABORATORY_FAN, ModBlocks.LABORATORY_FAN_REDSTONE_CONTROLLED, properties),
            properties -> properties.strength(2.5F, 3.0F).sound(ModSoundTypes.LABORATORY_BLOCK));

    public static final DeferredBlock<RedstoneControlledLaboratoryBlock> GLOWING_LABORATORY_FAN = register("glowing_laboratory_fan",
            properties -> new RedstoneControlledLaboratoryBlock(ModBlocks.LABORATORY_FAN, ModBlocks.GLOWING_LABORATORY_FAN_REDSTONE_CONTROLLED, properties),
            properties -> properties.strength(2.5F, 3.0F).sound(ModSoundTypes.LABORATORY_BLOCK).lightLevel(state -> 14));

    public static final DeferredBlock<RedstoneControlledLaboratoryBlock> LABORATORY_FAN_REDSTONE_CONTROLLED = register("laboratory_fan_redstone_controlled",
            properties -> new RedstoneControlledLaboratoryBlock(ModBlocks.GLOWING_LABORATORY_FAN_REDSTONE_CONTROLLED, ModBlocks.LABORATORY_FAN, properties),
            properties -> properties.strength(2.5F, 3.0F).sound(ModSoundTypes.LABORATORY_BLOCK));

    public static final DeferredBlock<RedstoneControlledLaboratoryBlock> GLOWING_LABORATORY_FAN_REDSTONE_CONTROLLED = register("glowing_laboratory_fan_redstone_controlled",
            properties -> new RedstoneControlledLaboratoryBlock(ModBlocks.LABORATORY_FAN_REDSTONE_CONTROLLED, ModBlocks.GLOWING_LABORATORY_FAN, properties),
            properties -> properties.strength(2.5F, 3.0F).sound(ModSoundTypes.LABORATORY_BLOCK).lightLevel(state -> 14));

    public static final DeferredBlock<LaboratoryBlock> LEFT_INDICATING_BLUE_LABORATORY_BLOCK = register("left_indicating_blue_laboratory_block",
            properties -> new LaboratoryBlock(ModBlocks.GLOWING_LEFT_INDICATING_BLUE_LABORATORY_BLOCK, properties),
            properties -> properties.strength(2.5F, 3.0F).sound(ModSoundTypes.LABORATORY_BLOCK));

    public static final DeferredBlock<LaboratoryBlock> GLOWING_LEFT_INDICATING_BLUE_LABORATORY_BLOCK = register("glowing_left_indicating_blue_laboratory_block",
            properties -> new LaboratoryBlock(ModBlocks.LEFT_INDICATING_BLUE_LABORATORY_BLOCK, properties),
            properties -> properties.strength(2.5F, 3.0F).sound(ModSoundTypes.LABORATORY_BLOCK).lightLevel(state -> 14));

    public static final DeferredBlock<LaboratoryBlock> RIGHT_INDICATING_BLUE_LABORATORY_BLOCK = register("right_indicating_blue_laboratory_block",
            properties -> new LaboratoryBlock(ModBlocks.GLOWING_RIGHT_INDICATING_BLUE_LABORATORY_BLOCK, properties),
            properties -> properties.strength(2.5F, 3.0F).sound(ModSoundTypes.LABORATORY_BLOCK));

    public static final DeferredBlock<LaboratoryBlock> GLOWING_RIGHT_INDICATING_BLUE_LABORATORY_BLOCK = register("glowing_right_indicating_blue_laboratory_block",
            properties -> new LaboratoryBlock(ModBlocks.RIGHT_INDICATING_BLUE_LABORATORY_BLOCK, properties),
            properties -> properties.strength(2.5F, 3.0F).sound(ModSoundTypes.LABORATORY_BLOCK).lightLevel(state -> 14));

    public static final DeferredBlock<LaboratoryBlock> LEFT_INDICATING_RED_LABORATORY_BLOCK = register("left_indicating_red_laboratory_block",
            properties -> new LaboratoryBlock(ModBlocks.GLOWING_LEFT_INDICATING_RED_LABORATORY_BLOCK, properties),
            properties -> properties.strength(2.5F, 3.0F).sound(ModSoundTypes.LABORATORY_BLOCK));

    public static final DeferredBlock<LaboratoryBlock> GLOWING_LEFT_INDICATING_RED_LABORATORY_BLOCK = register("glowing_left_indicating_red_laboratory_block",
            properties -> new LaboratoryBlock(ModBlocks.LEFT_INDICATING_RED_LABORATORY_BLOCK, properties),
            properties -> properties.strength(2.5F, 3.0F).sound(ModSoundTypes.LABORATORY_BLOCK).lightLevel(state -> 14));

    public static final DeferredBlock<LaboratoryBlock> RIGHT_INDICATING_RED_LABORATORY_BLOCK = register("right_indicating_red_laboratory_block",
            properties -> new LaboratoryBlock(ModBlocks.GLOWING_RIGHT_INDICATING_RED_LABORATORY_BLOCK, properties),
            properties -> properties.strength(2.5F, 3.0F).sound(ModSoundTypes.LABORATORY_BLOCK));

    public static final DeferredBlock<LaboratoryBlock> GLOWING_RIGHT_INDICATING_RED_LABORATORY_BLOCK = register("glowing_right_indicating_red_laboratory_block",
            properties -> new LaboratoryBlock(ModBlocks.RIGHT_INDICATING_RED_LABORATORY_BLOCK, properties),
            properties -> properties.strength(2.5F, 3.0F).sound(ModSoundTypes.LABORATORY_BLOCK).lightLevel(state -> 14));

    public static final DeferredBlock<LaboratoryBlock> LEFT_INDICATING_GREEN_LABORATORY_BLOCK = register("left_indicating_green_laboratory_block",
            properties -> new LaboratoryBlock(ModBlocks.GLOWING_LEFT_INDICATING_GREEN_LABORATORY_BLOCK, properties),
            properties -> properties.strength(2.5F, 3.0F).sound(ModSoundTypes.LABORATORY_BLOCK));

    public static final DeferredBlock<LaboratoryBlock> GLOWING_LEFT_INDICATING_GREEN_LABORATORY_BLOCK = register("glowing_left_indicating_green_laboratory_block",
            properties -> new LaboratoryBlock(ModBlocks.LEFT_INDICATING_GREEN_LABORATORY_BLOCK, properties),
            properties -> properties.strength(2.5F, 3.0F).sound(ModSoundTypes.LABORATORY_BLOCK).lightLevel(state -> 14));

    public static final DeferredBlock<LaboratoryBlock> RIGHT_INDICATING_GREEN_LABORATORY_BLOCK = register("right_indicating_green_laboratory_block",
            properties -> new LaboratoryBlock(ModBlocks.GLOWING_RIGHT_INDICATING_GREEN_LABORATORY_BLOCK, properties),
            properties -> properties.strength(2.5F, 3.0F).sound(ModSoundTypes.LABORATORY_BLOCK));

    public static final DeferredBlock<LaboratoryBlock> GLOWING_RIGHT_INDICATING_GREEN_LABORATORY_BLOCK = register("glowing_right_indicating_green_laboratory_block",
            properties -> new LaboratoryBlock(ModBlocks.RIGHT_INDICATING_GREEN_LABORATORY_BLOCK, properties),
            properties -> properties.strength(2.5F, 3.0F).sound(ModSoundTypes.LABORATORY_BLOCK).lightLevel(state -> 14));

    public static final DeferredBlock<LaboratoryBlock> LABORATORY_VENT = register("laboratory_vent",
            properties -> new LaboratoryBlock(ModBlocks.GLOWING_LABORATORY_VENT, properties),
            properties -> properties.strength(2.5F, 3.0F).sound(ModSoundTypes.LABORATORY_BLOCK));

    public static final DeferredBlock<LaboratoryBlock> GLOWING_LABORATORY_VENT = register("glowing_laboratory_vent",
            properties -> new LaboratoryBlock(ModBlocks.LABORATORY_VENT, properties),
            properties -> properties.strength(2.5F, 3.0F).sound(ModSoundTypes.LABORATORY_BLOCK).lightLevel(state -> 14));

    public static final DeferredBlock<LaboratoryBlock> LABORATORY_BLOCK = register("laboratory_block",
            properties -> new LaboratoryBlock(ModBlocks.GLOWING_LABORATORY_BLOCK, properties),
            properties -> properties.strength(2.5F, 3.0F).sound(ModSoundTypes.LABORATORY_BLOCK));

    public static final DeferredBlock<LaboratoryBlock> GLOWING_LABORATORY_BLOCK = register("glowing_laboratory_block",
            properties -> new LaboratoryBlock(ModBlocks.LABORATORY_BLOCK, properties),
            properties -> properties.strength(2.5F, 3.0F).sound(ModSoundTypes.LABORATORY_BLOCK).lightLevel(state -> 14));

    public static final DeferredBlock<LaboratoryBlock> REINFORCED_LABORATORY_BLOCK = register("reinforced_laboratory_block",
            properties -> new LaboratoryBlock(ModBlocks.GLOWING_REINFORCED_LABORATORY_BLOCK, properties),
            properties -> properties.strength(2.5F, 3.0F).sound(ModSoundTypes.LABORATORY_BLOCK));

    public static final DeferredBlock<LaboratoryBlock> GLOWING_REINFORCED_LABORATORY_BLOCK = register("glowing_reinforced_laboratory_block",
            properties -> new LaboratoryBlock(ModBlocks.REINFORCED_LABORATORY_BLOCK, properties),
            properties -> properties.strength(2.5F, 3.0F).sound(ModSoundTypes.LABORATORY_BLOCK).lightLevel(state -> 14));

    public static final DeferredBlock<LaboratoryBlock> LABORATORY_TILES = register("laboratory_tiles",
            properties -> new LaboratoryBlock(ModBlocks.GLOWING_LABORATORY_TILES, properties),
            properties -> properties.strength(2.5F, 3.0F).sound(ModSoundTypes.LABORATORY_BLOCK));

    public static final DeferredBlock<LaboratoryBlock> GLOWING_LABORATORY_TILES = register("glowing_laboratory_tiles",
            properties -> new LaboratoryBlock(ModBlocks.LABORATORY_TILES, properties),
            properties -> properties.strength(2.5F, 3.0F).sound(ModSoundTypes.LABORATORY_BLOCK).lightLevel(state -> 14));

    public static final DeferredBlock<LaboratoryBlock> GRAY_LABORATORY_TILES = register("gray_laboratory_tiles",
            properties -> new LaboratoryBlock(ModBlocks.GLOWING_GRAY_LABORATORY_TILES, properties),
            properties -> properties.strength(2.5F, 3.0F).sound(ModSoundTypes.LABORATORY_BLOCK));

    public static final DeferredBlock<LaboratoryBlock> GLOWING_GRAY_LABORATORY_TILES = register("glowing_gray_laboratory_tiles",
            properties -> new LaboratoryBlock(ModBlocks.GRAY_LABORATORY_TILES, properties),
            properties -> properties.strength(2.5F, 3.0F).sound(ModSoundTypes.LABORATORY_BLOCK).lightLevel(state -> 14));

    public static final DeferredBlock<LaboratoryBlock> MIXED_LABORATORY_TILES = register("mixed_laboratory_tiles",
            properties -> new LaboratoryBlock(ModBlocks.GLOWING_MIXED_LABORATORY_TILES, properties),
            properties -> properties.strength(2.5F, 3.0F).sound(ModSoundTypes.LABORATORY_BLOCK));

    public static final DeferredBlock<LaboratoryBlock> GLOWING_MIXED_LABORATORY_TILES = register("glowing_mixed_laboratory_tiles",
            properties -> new LaboratoryBlock(ModBlocks.MIXED_LABORATORY_TILES, properties),
            properties -> properties.strength(2.5F, 3.0F).sound(ModSoundTypes.LABORATORY_BLOCK).lightLevel(state -> 14));

    public static final DeferredBlock<LaboratoryBlock> OAK_LABORATORY_FLOOR = register("oak_laboratory_floor",
            properties -> new LaboratoryBlock(ModBlocks.GLOWING_OAK_LABORATORY_FLOOR, properties),
            properties -> properties.ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD));

    public static final DeferredBlock<LaboratoryBlock> GLOWING_OAK_LABORATORY_FLOOR = register("glowing_oak_laboratory_floor",
            properties -> new LaboratoryBlock(ModBlocks.OAK_LABORATORY_FLOOR, properties),
            properties -> properties.ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD).lightLevel(state -> 14));

    public static final DeferredBlock<LaboratoryBlock> OAK_LABORATORY_TILES = register("oak_laboratory_tiles",
            properties -> new LaboratoryBlock(ModBlocks.GLOWING_OAK_LABORATORY_TILES, properties),
            properties -> properties.ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD));

    public static final DeferredBlock<LaboratoryBlock> GLOWING_OAK_LABORATORY_TILES = register("glowing_oak_laboratory_tiles",
            properties -> new LaboratoryBlock(ModBlocks.OAK_LABORATORY_TILES, properties),
            properties -> properties.ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD).lightLevel(state -> 14));

    public static final DeferredBlock<LaboratoryBlock> SPRUCE_LABORATORY_FLOOR = register("spruce_laboratory_floor",
            properties -> new LaboratoryBlock(ModBlocks.GLOWING_SPRUCE_LABORATORY_FLOOR, properties),
            properties -> properties.ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD));

    public static final DeferredBlock<LaboratoryBlock> GLOWING_SPRUCE_LABORATORY_FLOOR = register("glowing_spruce_laboratory_floor",
            properties -> new LaboratoryBlock(ModBlocks.SPRUCE_LABORATORY_FLOOR, properties),
            properties -> properties.ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD).lightLevel(state -> 14));

    public static final DeferredBlock<LaboratoryBlock> SPRUCE_LABORATORY_TILES = register("spruce_laboratory_tiles",
            properties -> new LaboratoryBlock(ModBlocks.GLOWING_SPRUCE_LABORATORY_TILES, properties),
            properties -> properties.ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD));

    public static final DeferredBlock<LaboratoryBlock> GLOWING_SPRUCE_LABORATORY_TILES = register("glowing_spruce_laboratory_tiles",
            properties -> new LaboratoryBlock(ModBlocks.SPRUCE_LABORATORY_TILES, properties),
            properties -> properties.ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD).lightLevel(state -> 14));

    public static final DeferredBlock<LaboratoryBlock> BIRCH_LABORATORY_FLOOR = register("birch_laboratory_floor",
            properties -> new LaboratoryBlock(ModBlocks.GLOWING_BIRCH_LABORATORY_FLOOR, properties),
            properties -> properties.ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD));

    public static final DeferredBlock<LaboratoryBlock> GLOWING_BIRCH_LABORATORY_FLOOR = register("glowing_birch_laboratory_floor",
            properties -> new LaboratoryBlock(ModBlocks.BIRCH_LABORATORY_FLOOR, properties),
            properties -> properties.ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD).lightLevel(state -> 14));

    public static final DeferredBlock<LaboratoryBlock> BIRCH_LABORATORY_TILES = register("birch_laboratory_tiles",
            properties -> new LaboratoryBlock(ModBlocks.GLOWING_BIRCH_LABORATORY_TILES, properties),
            properties -> properties.ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD));

    public static final DeferredBlock<LaboratoryBlock> GLOWING_BIRCH_LABORATORY_TILES = register("glowing_birch_laboratory_tiles",
            properties -> new LaboratoryBlock(ModBlocks.BIRCH_LABORATORY_TILES, properties),
            properties -> properties.ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD).lightLevel(state -> 14));

    public static final DeferredBlock<LaboratoryBlock> DARK_OAK_LABORATORY_FLOOR = register("dark_oak_laboratory_floor",
            properties -> new LaboratoryBlock(ModBlocks.GLOWING_DARK_OAK_LABORATORY_FLOOR, properties),
            properties -> properties.ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD));

    public static final DeferredBlock<LaboratoryBlock> GLOWING_DARK_OAK_LABORATORY_FLOOR = register("glowing_dark_oak_laboratory_floor",
            properties -> new LaboratoryBlock(ModBlocks.DARK_OAK_LABORATORY_FLOOR, properties),
            properties -> properties.ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD).lightLevel(state -> 14));

    public static final DeferredBlock<LaboratoryBlock> DARK_OAK_LABORATORY_TILES = register("dark_oak_laboratory_tiles",
            properties -> new LaboratoryBlock(ModBlocks.GLOWING_DARK_OAK_LABORATORY_TILES, properties),
            properties -> properties.ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD));

    public static final DeferredBlock<LaboratoryBlock> GLOWING_DARK_OAK_LABORATORY_TILES = register("glowing_dark_oak_laboratory_tiles",
            properties -> new LaboratoryBlock(ModBlocks.DARK_OAK_LABORATORY_TILES, properties),
            properties -> properties.ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD).lightLevel(state -> 14));

    public static final DeferredBlock<LaboratoryBlock> PALE_OAK_LABORATORY_FLOOR = register("pale_oak_laboratory_floor",
            properties -> new LaboratoryBlock(ModBlocks.GLOWING_PALE_OAK_LABORATORY_FLOOR, properties),
            properties -> properties.ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD));

    public static final DeferredBlock<LaboratoryBlock> GLOWING_PALE_OAK_LABORATORY_FLOOR = register("glowing_pale_oak_laboratory_floor",
            properties -> new LaboratoryBlock(ModBlocks.PALE_OAK_LABORATORY_FLOOR, properties),
            properties -> properties.ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD).lightLevel(state -> 14));

    public static final DeferredBlock<LaboratoryBlock> PALE_OAK_LABORATORY_TILES = register("pale_oak_laboratory_tiles",
            properties -> new LaboratoryBlock(ModBlocks.GLOWING_PALE_OAK_LABORATORY_TILES, properties),
            properties -> properties.ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD));

    public static final DeferredBlock<LaboratoryBlock> GLOWING_PALE_OAK_LABORATORY_TILES = register("glowing_pale_oak_laboratory_tiles",
            properties -> new LaboratoryBlock(ModBlocks.PALE_OAK_LABORATORY_TILES, properties),
            properties -> properties.ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD).lightLevel(state -> 14));

    public static final DeferredBlock<LaboratoryBlock> JUNGLE_LABORATORY_FLOOR = register("jungle_laboratory_floor",
            properties -> new LaboratoryBlock(ModBlocks.GLOWING_JUNGLE_LABORATORY_FLOOR, properties),
            properties -> properties.ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD));

    public static final DeferredBlock<LaboratoryBlock> GLOWING_JUNGLE_LABORATORY_FLOOR = register("glowing_jungle_laboratory_floor",
            properties -> new LaboratoryBlock(ModBlocks.JUNGLE_LABORATORY_FLOOR, properties),
            properties -> properties.ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD).lightLevel(state -> 14));

    public static final DeferredBlock<LaboratoryBlock> JUNGLE_LABORATORY_TILES = register("jungle_laboratory_tiles",
            properties -> new LaboratoryBlock(ModBlocks.GLOWING_JUNGLE_LABORATORY_TILES, properties),
            properties -> properties.ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD));

    public static final DeferredBlock<LaboratoryBlock> GLOWING_JUNGLE_LABORATORY_TILES = register("glowing_jungle_laboratory_tiles",
            properties -> new LaboratoryBlock(ModBlocks.JUNGLE_LABORATORY_TILES, properties),
            properties -> properties.ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD).lightLevel(state -> 14));

    public static final DeferredBlock<LaboratoryBlock> ACACIA_LABORATORY_FLOOR = register("acacia_laboratory_floor",
            properties -> new LaboratoryBlock(ModBlocks.GLOWING_ACACIA_LABORATORY_FLOOR, properties),
            properties -> properties.ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD));

    public static final DeferredBlock<LaboratoryBlock> GLOWING_ACACIA_LABORATORY_FLOOR = register("glowing_acacia_laboratory_floor",
            properties -> new LaboratoryBlock(ModBlocks.ACACIA_LABORATORY_FLOOR, properties),
            properties -> properties.ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD).lightLevel(state -> 14));

    public static final DeferredBlock<LaboratoryBlock> ACACIA_LABORATORY_TILES = register("acacia_laboratory_tiles",
            properties -> new LaboratoryBlock(ModBlocks.GLOWING_ACACIA_LABORATORY_TILES, properties),
            properties -> properties.ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD));

    public static final DeferredBlock<LaboratoryBlock> GLOWING_ACACIA_LABORATORY_TILES = register("glowing_acacia_laboratory_tiles",
            properties -> new LaboratoryBlock(ModBlocks.ACACIA_LABORATORY_TILES, properties),
            properties -> properties.ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD).lightLevel(state -> 14));

    public static final DeferredBlock<LaboratoryBlock> MANGROVE_LABORATORY_FLOOR = register("mangrove_laboratory_floor",
            properties -> new LaboratoryBlock(ModBlocks.GLOWING_MANGROVE_LABORATORY_FLOOR, properties),
            properties -> properties.ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD));

    public static final DeferredBlock<LaboratoryBlock> GLOWING_MANGROVE_LABORATORY_FLOOR = register("glowing_mangrove_laboratory_floor",
            properties -> new LaboratoryBlock(ModBlocks.MANGROVE_LABORATORY_FLOOR, properties),
            properties -> properties.ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD).lightLevel(state -> 14));

    public static final DeferredBlock<LaboratoryBlock> MANGROVE_LABORATORY_TILES = register("mangrove_laboratory_tiles",
            properties -> new LaboratoryBlock(ModBlocks.GLOWING_MANGROVE_LABORATORY_TILES, properties),
            properties -> properties.ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD));

    public static final DeferredBlock<LaboratoryBlock> GLOWING_MANGROVE_LABORATORY_TILES = register("glowing_mangrove_laboratory_tiles",
            properties -> new LaboratoryBlock(ModBlocks.MANGROVE_LABORATORY_TILES, properties),
            properties -> properties.ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD).lightLevel(state -> 14));

    public static final DeferredBlock<LaboratoryBlock> CHERRY_LABORATORY_FLOOR = register("cherry_laboratory_floor",
            properties -> new LaboratoryBlock(ModBlocks.GLOWING_CHERRY_LABORATORY_FLOOR, properties),
            properties -> properties.ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.CHERRY_WOOD));

    public static final DeferredBlock<LaboratoryBlock> GLOWING_CHERRY_LABORATORY_FLOOR = register("glowing_cherry_laboratory_floor",
            properties -> new LaboratoryBlock(ModBlocks.CHERRY_LABORATORY_FLOOR, properties),
            properties -> properties.ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.CHERRY_WOOD).lightLevel(state -> 14));

    public static final DeferredBlock<LaboratoryBlock> CHERRY_LABORATORY_TILES = register("cherry_laboratory_tiles",
            properties -> new LaboratoryBlock(ModBlocks.GLOWING_CHERRY_LABORATORY_TILES, properties),
            properties -> properties.ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.CHERRY_WOOD));

    public static final DeferredBlock<LaboratoryBlock> GLOWING_CHERRY_LABORATORY_TILES = register("glowing_cherry_laboratory_tiles",
            properties -> new LaboratoryBlock(ModBlocks.CHERRY_LABORATORY_TILES, properties),
            properties -> properties.ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.CHERRY_WOOD).lightLevel(state -> 14));

    public static final DeferredBlock<LaboratoryBlock> BAMBOO_LABORATORY_FLOOR = register("bamboo_laboratory_floor",
            properties -> new LaboratoryBlock(ModBlocks.GLOWING_BAMBOO_LABORATORY_FLOOR, properties),
            properties -> properties.ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.BAMBOO_WOOD));

    public static final DeferredBlock<LaboratoryBlock> GLOWING_BAMBOO_LABORATORY_FLOOR = register("glowing_bamboo_laboratory_floor",
            properties -> new LaboratoryBlock(ModBlocks.BAMBOO_LABORATORY_FLOOR, properties),
            properties -> properties.ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.BAMBOO_WOOD).lightLevel(state -> 14));

    public static final DeferredBlock<LaboratoryBlock> BAMBOO_LABORATORY_TILES = register("bamboo_laboratory_tiles",
            properties -> new LaboratoryBlock(ModBlocks.GLOWING_BAMBOO_LABORATORY_TILES, properties),
            properties -> properties.ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.BAMBOO_WOOD));

    public static final DeferredBlock<LaboratoryBlock> GLOWING_BAMBOO_LABORATORY_TILES = register("glowing_bamboo_laboratory_tiles",
            properties -> new LaboratoryBlock(ModBlocks.BAMBOO_LABORATORY_TILES, properties),
            properties -> properties.ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.BAMBOO_WOOD).lightLevel(state -> 14));

    public static final DeferredBlock<LaboratoryBlock> CRIMSON_LABORATORY_FLOOR = register("crimson_laboratory_floor",
            properties -> new LaboratoryBlock(ModBlocks.GLOWING_CRIMSON_LABORATORY_FLOOR, properties),
            properties -> properties.ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD));

    public static final DeferredBlock<LaboratoryBlock> GLOWING_CRIMSON_LABORATORY_FLOOR = register("glowing_crimson_laboratory_floor",
            properties -> new LaboratoryBlock(ModBlocks.CRIMSON_LABORATORY_FLOOR, properties),
            properties -> properties.ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD).lightLevel(state -> 14));

    public static final DeferredBlock<LaboratoryBlock> CRIMSON_LABORATORY_TILES = register("crimson_laboratory_tiles",
            properties -> new LaboratoryBlock(ModBlocks.GLOWING_CRIMSON_LABORATORY_TILES, properties),
            properties -> properties.ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD));

    public static final DeferredBlock<LaboratoryBlock> GLOWING_CRIMSON_LABORATORY_TILES = register("glowing_crimson_laboratory_tiles",
            properties -> new LaboratoryBlock(ModBlocks.CRIMSON_LABORATORY_TILES, properties),
            properties -> properties.ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD).lightLevel(state -> 14));

    public static final DeferredBlock<LaboratoryBlock> WARPED_LABORATORY_FLOOR = register("warped_laboratory_floor",
            properties -> new LaboratoryBlock(ModBlocks.GLOWING_WARPED_LABORATORY_FLOOR, properties),
            properties -> properties.ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD));

    public static final DeferredBlock<LaboratoryBlock> GLOWING_WARPED_LABORATORY_FLOOR = register("glowing_warped_laboratory_floor",
            properties -> new LaboratoryBlock(ModBlocks.WARPED_LABORATORY_FLOOR, properties),
            properties -> properties.ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD).lightLevel(state -> 14));

    public static final DeferredBlock<LaboratoryBlock> WARPED_LABORATORY_TILES = register("warped_laboratory_tiles",
            properties -> new LaboratoryBlock(ModBlocks.GLOWING_WARPED_LABORATORY_TILES, properties),
            properties -> properties.ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD));

    public static final DeferredBlock<LaboratoryBlock> GLOWING_WARPED_LABORATORY_TILES = register("glowing_warped_laboratory_tiles",
            properties -> new LaboratoryBlock(ModBlocks.WARPED_LABORATORY_TILES, properties),
            properties -> properties.ignitedByLava().strength(2.5F, 3.0F).sound(SoundType.WOOD).lightLevel(state -> 14));

    public static final DeferredBlock<LaboratoryGlassBlock> LABORATORY_GLASS = register("laboratory_glass",
            properties -> new LaboratoryGlassBlock(ModBlocks.GLOWING_LABORATORY_GLASS, properties),
            properties -> properties.strength(0.3F).sound(SoundType.GLASS).noOcclusion()
                    .isValidSpawn(ModBlocks::never).isRedstoneConductor(ModBlocks::never)
                    .isSuffocating(ModBlocks::never).isViewBlocking(ModBlocks::never));

    public static final DeferredBlock<LaboratoryGlassBlock> GLOWING_LABORATORY_GLASS = register("glowing_laboratory_glass",
            properties -> new LaboratoryGlassBlock(ModBlocks.LABORATORY_GLASS, properties),
            properties -> properties.strength(0.3F).sound(SoundType.GLASS).noOcclusion()
                    .isValidSpawn(ModBlocks::never).isRedstoneConductor(ModBlocks::never)
                    .isSuffocating(ModBlocks::never).isViewBlocking(ModBlocks::never).lightLevel(state -> 14));

}