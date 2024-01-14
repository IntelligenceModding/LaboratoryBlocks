package de.artemis.laboratoryblocks.common.data;

import de.artemis.laboratoryblocks.LaboratoryBlocks;
import de.artemis.laboratoryblocks.common.registration.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class ModelAndBlockStateProvider extends BlockStateProvider {
    public ModelAndBlockStateProvider(PackOutput packOutput, ExistingFileHelper exFileHelper) {
        super(packOutput, LaboratoryBlocks.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(ModBlocks.LABORATORY_BLOCK.get());
        block(ModBlocks.ENLIGHTED_LABORATORY_BLOCK.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/laboratory_block"));
        simpleBlock(ModBlocks.LABORATORY_TILES.get());
        block(ModBlocks.ENLIGHTED_LABORATORY_TILES.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/laboratory_tiles"));
        simpleBlock(ModBlocks.GRAY_LABORATORY_TILES.get());
        block(ModBlocks.ENLIGHTED_GRAY_LABORATORY_TILES.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/gray_laboratory_tiles"));
        simpleBlock(ModBlocks.MIXED_LABORATORY_TILES.get());
        block(ModBlocks.ENLIGHTED_MIXED_LABORATORY_TILES.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/mixed_laboratory_tiles"));

        simpleBlock(ModBlocks.OAK_LABORATORY_FLOOR.get());
        block(ModBlocks.ENLIGHTED_OAK_LABORATORY_FLOOR.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/oak_laboratory_floor"));
        simpleBlock(ModBlocks.SPRUCE_LABORATORY_FLOOR.get());
        block(ModBlocks.ENLIGHTED_SPRUCE_LABORATORY_FLOOR.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/spruce_laboratory_floor"));
        simpleBlock(ModBlocks.BIRCH_LABORATORY_FLOOR.get());
        block(ModBlocks.ENLIGHTED_BIRCH_LABORATORY_FLOOR.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/birch_laboratory_floor"));
        simpleBlock(ModBlocks.DARK_OAK_LABORATORY_FLOOR.get());
        block(ModBlocks.ENLIGHTED_DARK_OAK_LABORATORY_FLOOR.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/dark_oak_laboratory_floor"));
        simpleBlock(ModBlocks.JUNGLE_LABORATORY_FLOOR.get());
        block(ModBlocks.ENLIGHTED_JUNGLE_LABORATORY_FLOOR.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/jungle_laboratory_floor"));
        simpleBlock(ModBlocks.ACACIA_LABORATORY_FLOOR.get());
        block(ModBlocks.ENLIGHTED_ACACIA_LABORATORY_FLOOR.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/acacia_laboratory_floor"));
        simpleBlock(ModBlocks.MANGROVE_LABORATORY_FLOOR.get());
        block(ModBlocks.ENLIGHTED_MANGROVE_LABORATORY_FLOOR.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/mangrove_laboratory_floor"));
        simpleBlock(ModBlocks.CRIMSON_LABORATORY_FLOOR.get());
        block(ModBlocks.ENLIGHTED_CRIMSON_LABORATORY_FLOOR.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/crimson_laboratory_floor"));
        simpleBlock(ModBlocks.WARPED_LABORATORY_FLOOR.get());
        block(ModBlocks.ENLIGHTED_WARPED_LABORATORY_FLOOR.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/warped_laboratory_floor"));
        simpleBlock(ModBlocks.CHERRY_LABORATORY_FLOOR.get());
        block(ModBlocks.ENLIGHTED_CHERRY_LABORATORY_FLOOR.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/cherry_laboratory_floor"));
        simpleBlock(ModBlocks.BAMBOO_LABORATORY_FLOOR.get());
        block(ModBlocks.ENLIGHTED_BAMBOO_LABORATORY_FLOOR.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/bamboo_laboratory_floor"));
        simpleBlock(ModBlocks.OAK_LABORATORY_TILES.get());
        block(ModBlocks.ENLIGHTED_OAK_LABORATORY_TILES.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/oak_laboratory_tiles"));
        simpleBlock(ModBlocks.SPRUCE_LABORATORY_TILES.get());
        block(ModBlocks.ENLIGHTED_SPRUCE_LABORATORY_TILES.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/spruce_laboratory_tiles"));
        simpleBlock(ModBlocks.BIRCH_LABORATORY_TILES.get());
        block(ModBlocks.ENLIGHTED_BIRCH_LABORATORY_TILES.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/birch_laboratory_tiles"));
        simpleBlock(ModBlocks.DARK_OAK_LABORATORY_TILES.get());
        block(ModBlocks.ENLIGHTED_DARK_OAK_LABORATORY_TILES.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/dark_oak_laboratory_tiles"));
        simpleBlock(ModBlocks.JUNGLE_LABORATORY_TILES.get());
        block(ModBlocks.ENLIGHTED_JUNGLE_LABORATORY_TILES.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/jungle_laboratory_tiles"));
        simpleBlock(ModBlocks.ACACIA_LABORATORY_TILES.get());
        block(ModBlocks.ENLIGHTED_ACACIA_LABORATORY_TILES.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/acacia_laboratory_tiles"));
        simpleBlock(ModBlocks.MANGROVE_LABORATORY_TILES.get());
        block(ModBlocks.ENLIGHTED_MANGROVE_LABORATORY_TILES.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/mangrove_laboratory_tiles"));
        simpleBlock(ModBlocks.CRIMSON_LABORATORY_TILES.get());
        block(ModBlocks.ENLIGHTED_CRIMSON_LABORATORY_TILES.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/crimson_laboratory_tiles"));
        simpleBlock(ModBlocks.WARPED_LABORATORY_TILES.get());
        block(ModBlocks.ENLIGHTED_WARPED_LABORATORY_TILES.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/warped_laboratory_tiles"));
        simpleBlock(ModBlocks.CHERRY_LABORATORY_TILES.get());
        block(ModBlocks.ENLIGHTED_CHERRY_LABORATORY_TILES.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/cherry_laboratory_tiles"));
        simpleBlock(ModBlocks.BAMBOO_LABORATORY_TILES.get());
        block(ModBlocks.ENLIGHTED_BAMBOO_LABORATORY_TILES.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/bamboo_laboratory_tiles"));
        simpleBlock(ModBlocks.LABORATORY_FAN.get());
        block(ModBlocks.ENLIGHTED_LABORATORY_FAN.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/laboratory_fan"));
        simpleBlock(ModBlocks.SCREWED_LABORATORY_BLOCK.get());
        block(ModBlocks.ENLIGHTED_SCREWED_LABORATORY_BLOCK.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/screwed_laboratory_block"));
        simpleBlock(ModBlocks.LABORATORY_VENT.get());
        block(ModBlocks.ENLIGHTED_LABORATORY_VENT.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/laboratory_vent"));
        simpleBlock(ModBlocks.CLEAR_LABORATORY_SCREEN.get());
        block(ModBlocks.ENLIGHTED_CLEAR_LABORATORY_SCREEN.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/clear_laboratory_screen"));

        simpleBlock(ModBlocks.LEFT_FACED_BLUE_SIGNALING_LABORATORY_BLOCK.get());
        block(ModBlocks.ENLIGHTED_LEFT_FACED_BLUE_SIGNALING_LABORATORY_BLOCK.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/left-faced_blue_signaling_laboratory_block"));
        simpleBlock(ModBlocks.RIGHT_FACED_BLUE_SIGNALING_LABORATORY_BLOCK.get());
        block(ModBlocks.ENLIGHTED_RIGHT_FACED_BLUE_SIGNALING_LABORATORY_BLOCK.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/right-faced_blue_signaling_laboratory_block"));
        simpleBlock(ModBlocks.LEFT_FACED_RED_SIGNALING_LABORATORY_BLOCK.get());
        block(ModBlocks.ENLIGHTED_LEFT_FACED_RED_SIGNALING_LABORATORY_BLOCK.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/left-faced_red_signaling_laboratory_block"));
        simpleBlock(ModBlocks.RIGHT_FACED_RED_SIGNALING_LABORATORY_BLOCK.get());
        block(ModBlocks.ENLIGHTED_RIGHT_FACED_RED_SIGNALING_LABORATORY_BLOCK.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/right-faced_red_signaling_laboratory_block"));
        simpleBlock(ModBlocks.LEFT_FACED_GREEN_SIGNALING_LABORATORY_BLOCK.get());
        block(ModBlocks.ENLIGHTED_LEFT_FACED_GREEN_SIGNALING_LABORATORY_BLOCK.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/left-faced_green_signaling_laboratory_block"));
        simpleBlock(ModBlocks.RIGHT_FACED_GREEN_SIGNALING_LABORATORY_BLOCK.get());
        block(ModBlocks.ENLIGHTED_RIGHT_FACED_GREEN_SIGNALING_LABORATORY_BLOCK.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/right-faced_green_signaling_laboratory_block"));

        simpleBlockWithRenderType(ModBlocks.LABORATORY_GLASS.get(), "cutout");
        simpleBlockWithRenderType(ModBlocks.ENLIGHTED_LABORATORY_GLASS.get(), "cutout");

        simpleBlock(ModBlocks.PLA_BLOCK.get());
        block(ModBlocks.ENLIGHTED_PLA_BLOCK.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/pla_block"));
        simpleBlock(ModBlocks.PLA_TILES.get());
        block(ModBlocks.ENLIGHTED_PLA_TILES.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/pla_tiles"));

        pillarBlock(ModBlocks.LABORATORY_PILLAR.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/laboratory_pillar"), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/laboratory_pillar_top"));
        pillarBlock(ModBlocks.ENLIGHTED_LABORATORY_PILLAR.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/laboratory_pillar"), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/laboratory_pillar_top"));
        pillarBlock(ModBlocks.GRAY_LABORATORY_PILLAR.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/gray_laboratory_pillar"), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/gray_laboratory_pillar_top"));
        pillarBlock(ModBlocks.ENLIGHTED_GRAY_LABORATORY_PILLAR.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/gray_laboratory_pillar"), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/gray_laboratory_pillar_top"));

        carpetBlock(ModBlocks.PLA_FLOORING.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/pla_block"));
        carpetBlock(ModBlocks.ENLIGHTED_PLA_FLOORING.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/pla_block"));
        carpetBlock(ModBlocks.TILED_PLA_FLOORING.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/pla_tiles"));
        carpetBlock(ModBlocks.ENLIGHTED_TILED_PLA_FLOORING.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/pla_tiles"));

    }

    public void block(Block block, ResourceLocation texture) {
        simpleBlock(block, models().withExistingParent(ForgeRegistries.BLOCKS.getKey(block).toString(), new ResourceLocation("block/cube_all"))
                .texture("all", texture));
    }

    public void simpleBlockWithRenderType(Block block, String renderType) {
        simpleBlock(block, cubeAllWithRenderType(block, renderType));
    }

    public ModelFile cubeAllWithRenderType(Block block, String renderType) {
        return models().cubeAll(ForgeRegistries.BLOCKS.getKey(block).toString(), blockTexture(block)).renderType(renderType);
    }

    public void blockWithRenderType(Block block, ResourceLocation texture, String renderType) {
        simpleBlock(block, models().withExistingParent(ForgeRegistries.BLOCKS.getKey(block).toString(), new ResourceLocation("block/cube_all"))
                .texture("all", texture).renderType(renderType));
    }

    public void pillarBlock(Block block, ResourceLocation texture_side, ResourceLocation texture_top) {
        ModelFile block_model = models().withExistingParent(DataProvider.getRegistryName(block.asItem()), "block/cube_column").texture("side", texture_side).texture("end", texture_top);
        getVariantBuilder(block).partialState().setModels(ConfiguredModel.builder().modelFile(block_model).build());
    }

    public void carpetBlock(Block block, ResourceLocation texture) {
        ModelFile block_model = models().withExistingParent(DataProvider.getRegistryName(block.asItem()), "block/carpet").texture("wool", texture);
        getVariantBuilder(block).partialState().setModels(ConfiguredModel.builder().modelFile(block_model).build());
    }
}
