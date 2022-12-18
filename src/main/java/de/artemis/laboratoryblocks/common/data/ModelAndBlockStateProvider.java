package de.artemis.laboratoryblocks.common.data;

import de.artemis.laboratoryblocks.LaboratoryBlocks;
import de.artemis.laboratoryblocks.common.registration.ModBlocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.StructureBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class ModelAndBlockStateProvider extends BlockStateProvider {
    public ModelAndBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, LaboratoryBlocks.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(ModBlocks.LABORATORY_BLOCK.get());
        enlightedBlock(ModBlocks.ENLIGHTED_LABORATORY_BLOCK.get(), new ResourceLocation(LaboratoryBlocks.MODID, "block/laboratory_block"));
        simpleBlock(ModBlocks.OAK_LABORATORY_FLOOR.get());
        enlightedBlock(ModBlocks.ENLIGHTED_OAK_LABORATORY_FLOOR.get(), new ResourceLocation(LaboratoryBlocks.MODID, "block/oak_laboratory_floor"));
        simpleBlock(ModBlocks.SPRUCE_LABORATORY_FLOOR.get());
        enlightedBlock(ModBlocks.ENLIGHTED_SPRUCE_LABORATORY_FLOOR.get(), new ResourceLocation(LaboratoryBlocks.MODID, "block/spruce_laboratory_floor"));
        simpleBlock(ModBlocks.BIRCH_LABORATORY_FLOOR.get());
        enlightedBlock(ModBlocks.ENLIGHTED_BIRCH_LABORATORY_FLOOR.get(), new ResourceLocation(LaboratoryBlocks.MODID, "block/birch_laboratory_floor"));
        simpleBlock(ModBlocks.DARK_OAK_LABORATORY_FLOOR.get());
        enlightedBlock(ModBlocks.ENLIGHTED_DARK_OAK_LABORATORY_FLOOR.get(), new ResourceLocation(LaboratoryBlocks.MODID, "block/dark_oak_laboratory_floor"));
        simpleBlock(ModBlocks.JUNGLE_LABORATORY_FLOOR.get());
        enlightedBlock(ModBlocks.ENLIGHTED_JUNGLE_LABORATORY_FLOOR.get(), new ResourceLocation(LaboratoryBlocks.MODID, "block/jungle_laboratory_floor"));
        simpleBlock(ModBlocks.ACACIA_LABORATORY_FLOOR.get());
        enlightedBlock(ModBlocks.ENLIGHTED_ACACIA_LABORATORY_FLOOR.get(), new ResourceLocation(LaboratoryBlocks.MODID, "block/acacia_laboratory_floor"));
        simpleBlock(ModBlocks.MANGROVE_LABORATORY_FLOOR.get());
        enlightedBlock(ModBlocks.ENLIGHTED_MANGROVE_LABORATORY_FLOOR.get(), new ResourceLocation(LaboratoryBlocks.MODID, "block/mangrove_laboratory_floor"));
        simpleBlock(ModBlocks.CRIMSON_LABORATORY_FLOOR.get());
        enlightedBlock(ModBlocks.ENLIGHTED_CRIMSON_LABORATORY_FLOOR.get(), new ResourceLocation(LaboratoryBlocks.MODID, "block/crimson_laboratory_floor"));
        simpleBlock(ModBlocks.WARPED_LABORATORY_FLOOR.get());
        enlightedBlock(ModBlocks.ENLIGHTED_WARPED_LABORATORY_FLOOR.get(), new ResourceLocation(LaboratoryBlocks.MODID, "block/warped_laboratory_floor"));
        simpleBlockWithRenderType(ModBlocks.LABORATORY_GLASS.get(), "cutout");
        enlightedBlockWithRenderType(ModBlocks.ENLIGHTED_LABORATORY_GLASS.get(), new ResourceLocation(LaboratoryBlocks.MODID, "block/laboratory_glass"), "cutout");
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
