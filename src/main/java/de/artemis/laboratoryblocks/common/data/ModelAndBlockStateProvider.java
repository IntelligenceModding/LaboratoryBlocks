package de.artemis.laboratoryblocks.common.data;

import de.artemis.laboratoryblocks.LaboratoryBlocks;
import de.artemis.laboratoryblocks.common.registration.ModBlocks;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.MultiPartBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class ModelAndBlockStateProvider extends BlockStateProvider {
    public ModelAndBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, LaboratoryBlocks.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(ModBlocks.LABORATORY_BLOCK.get());
        enlightedBlock(ModBlocks.ENLIGHTED_LABORATORY_BLOCK.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/laboratory_block"));
        simpleBlock(ModBlocks.LABORATORY_TILES.get());
        enlightedBlock(ModBlocks.ENLIGHTED_LABORATORY_TILES.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/laboratory_tiles"));
        simpleBlock(ModBlocks.GRAY_LABORATORY_TILES.get());
        enlightedBlock(ModBlocks.ENLIGHTED_GRAY_LABORATORY_TILES.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/gray_laboratory_tiles"));
        simpleBlock(ModBlocks.MIXED_LABORATORY_TILES.get());
        enlightedBlock(ModBlocks.ENLIGHTED_MIXED_LABORATORY_TILES.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/mixed_laboratory_tiles"));

        simpleBlock(ModBlocks.OAK_LABORATORY_FLOOR.get());
        enlightedBlock(ModBlocks.ENLIGHTED_OAK_LABORATORY_FLOOR.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/oak_laboratory_floor"));
        simpleBlock(ModBlocks.SPRUCE_LABORATORY_FLOOR.get());
        enlightedBlock(ModBlocks.ENLIGHTED_SPRUCE_LABORATORY_FLOOR.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/spruce_laboratory_floor"));
        simpleBlock(ModBlocks.BIRCH_LABORATORY_FLOOR.get());
        enlightedBlock(ModBlocks.ENLIGHTED_BIRCH_LABORATORY_FLOOR.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/birch_laboratory_floor"));
        simpleBlock(ModBlocks.DARK_OAK_LABORATORY_FLOOR.get());
        enlightedBlock(ModBlocks.ENLIGHTED_DARK_OAK_LABORATORY_FLOOR.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/dark_oak_laboratory_floor"));
        simpleBlock(ModBlocks.JUNGLE_LABORATORY_FLOOR.get());
        enlightedBlock(ModBlocks.ENLIGHTED_JUNGLE_LABORATORY_FLOOR.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/jungle_laboratory_floor"));
        simpleBlock(ModBlocks.ACACIA_LABORATORY_FLOOR.get());
        enlightedBlock(ModBlocks.ENLIGHTED_ACACIA_LABORATORY_FLOOR.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/acacia_laboratory_floor"));
        simpleBlock(ModBlocks.MANGROVE_LABORATORY_FLOOR.get());
        enlightedBlock(ModBlocks.ENLIGHTED_MANGROVE_LABORATORY_FLOOR.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/mangrove_laboratory_floor"));
        simpleBlock(ModBlocks.CRIMSON_LABORATORY_FLOOR.get());
        enlightedBlock(ModBlocks.ENLIGHTED_CRIMSON_LABORATORY_FLOOR.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/crimson_laboratory_floor"));
        simpleBlock(ModBlocks.WARPED_LABORATORY_FLOOR.get());
        enlightedBlock(ModBlocks.ENLIGHTED_WARPED_LABORATORY_FLOOR.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/warped_laboratory_floor"));

        simpleBlock(ModBlocks.OAK_LABORATORY_TILES.get());
        enlightedBlock(ModBlocks.ENLIGHTED_OAK_LABORATORY_TILES.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/oak_laboratory_tiles"));
        simpleBlock(ModBlocks.SPRUCE_LABORATORY_TILES.get());
        enlightedBlock(ModBlocks.ENLIGHTED_SPRUCE_LABORATORY_TILES.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/spruce_laboratory_tiles"));
        simpleBlock(ModBlocks.BIRCH_LABORATORY_TILES.get());
        enlightedBlock(ModBlocks.ENLIGHTED_BIRCH_LABORATORY_TILES.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/birch_laboratory_tiles"));
        simpleBlock(ModBlocks.DARK_OAK_LABORATORY_TILES.get());
        enlightedBlock(ModBlocks.ENLIGHTED_DARK_OAK_LABORATORY_TILES.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/dark_oak_laboratory_tiles"));
        simpleBlock(ModBlocks.JUNGLE_LABORATORY_TILES.get());
        enlightedBlock(ModBlocks.ENLIGHTED_JUNGLE_LABORATORY_TILES.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/jungle_laboratory_tiles"));
        simpleBlock(ModBlocks.ACACIA_LABORATORY_TILES.get());
        enlightedBlock(ModBlocks.ENLIGHTED_ACACIA_LABORATORY_TILES.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/acacia_laboratory_tiles"));
        simpleBlock(ModBlocks.MANGROVE_LABORATORY_TILES.get());
        enlightedBlock(ModBlocks.ENLIGHTED_MANGROVE_LABORATORY_TILES.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/mangrove_laboratory_tiles"));
        simpleBlock(ModBlocks.CRIMSON_LABORATORY_TILES.get());
        enlightedBlock(ModBlocks.ENLIGHTED_CRIMSON_LABORATORY_TILES.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/crimson_laboratory_tiles"));
        simpleBlock(ModBlocks.WARPED_LABORATORY_TILES.get());
        enlightedBlock(ModBlocks.ENLIGHTED_WARPED_LABORATORY_TILES.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/warped_laboratory_tiles"));

        simpleBlockWithRenderType(ModBlocks.LABORATORY_GLASS.get(), "cutout");
        enlightedBlockWithRenderType(ModBlocks.ENLIGHTED_LABORATORY_GLASS.get(), new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/laboratory_glass"), "cutout");

    }

    public void enlightedBlock(Block block, ResourceLocation texture) {
        simpleBlock(block, models().withExistingParent(ForgeRegistries.BLOCKS.getKey(block).toString(), new ResourceLocation("block/cube_all"))
                .texture("all", texture));
    }

    public void simpleBlockWithRenderType(Block block, String renderType) {
        simpleBlock(block, cubeAllWithRenderType(block, renderType));
    }

    public ModelFile cubeAllWithRenderType(Block block, String renderType) {
        return models().cubeAll(ForgeRegistries.BLOCKS.getKey(block).toString(), blockTexture(block)).renderType(renderType);
    }

    public void enlightedBlockWithRenderType(Block block, ResourceLocation texture, String renderType) {
        simpleBlock(block, models().withExistingParent(ForgeRegistries.BLOCKS.getKey(block).toString(), new ResourceLocation("block/cube_all"))
                .texture("all", texture).renderType(renderType));
    }

}
