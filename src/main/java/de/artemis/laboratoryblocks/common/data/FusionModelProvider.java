package de.artemis.laboratoryblocks.common.data;

import com.supermartijn642.fusion.api.model.DefaultModelTypes;
import com.supermartijn642.fusion.api.model.ModelInstance;
import com.supermartijn642.fusion.api.model.data.ConnectingModelDataBuilder;
import com.supermartijn642.fusion.api.predicate.DefaultConnectionPredicates;
import de.artemis.laboratoryblocks.LaboratoryBlocks;
import de.artemis.laboratoryblocks.common.registration.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;

public class FusionModelProvider extends com.supermartijn642.fusion.api.provider.FusionModelProvider {
    public FusionModelProvider(PackOutput output) {
        super(LaboratoryBlocks.MOD_ID, output);
    }

    @Override
    protected void generate() {

        var modelDataLaboratoryBlock = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/laboratory_block-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.ENLIGHTED_LABORATORY_BLOCK.get())))
                .build();
        var modelInstanceLaboratoryBlock = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataLaboratoryBlock);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/laboratory_block"), modelInstanceLaboratoryBlock);

        var modelDataEnlightedLaboratoryBlock = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/laboratory_block-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.LABORATORY_BLOCK.get())))
                .build();
        var modelInstanceEnlightedLaboratoryBlock = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataEnlightedLaboratoryBlock);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_laboratory_block"), modelInstanceEnlightedLaboratoryBlock);



        var modelDataOakLaboratoryFloor = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/oak_laboratory_floor-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.ENLIGHTED_OAK_LABORATORY_FLOOR.get())))
                .build();
        var modelInstanceOakLaboratoryFloor = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataOakLaboratoryFloor);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/oak_laboratory_floor"), modelInstanceOakLaboratoryFloor);

        var modelDataEnlightedOakLaboratoryFloor = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/oak_laboratory_floor-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.OAK_LABORATORY_FLOOR.get())))
                .build();
        var modelInstanceEnlightedOakLaboratoryFloor = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataEnlightedOakLaboratoryFloor);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_oak_laboratory_floor"), modelInstanceEnlightedOakLaboratoryFloor);

        var modelDataOakLaboratoryTiles = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/oak_laboratory_tiles-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.ENLIGHTED_OAK_LABORATORY_TILES.get())))
                .build();
        var modelInstanceOakLaboratoryTiles = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataOakLaboratoryTiles);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/oak_laboratory_tiles"), modelInstanceOakLaboratoryTiles);

        var modelDataEnlightedOakLaboratoryTiles = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/oak_laboratory_tiles-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.OAK_LABORATORY_TILES.get())))
                .build();
        var modelInstanceEnlightedOakLaboratoryTiles = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataEnlightedOakLaboratoryTiles);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_oak_laboratory_tiles"), modelInstanceEnlightedOakLaboratoryTiles);



        var modelDataSpruceLaboratoryFloor = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/spruce_laboratory_floor-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.ENLIGHTED_SPRUCE_LABORATORY_FLOOR.get())))
                .build();
        var modelInstanceSpruceLaboratoryFloor = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataSpruceLaboratoryFloor);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/spruce_laboratory_floor"), modelInstanceSpruceLaboratoryFloor);

        var modelDataEnlightedSpruceLaboratoryFloor = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/spruce_laboratory_floor-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.SPRUCE_LABORATORY_FLOOR.get())))
                .build();
        var modelInstanceEnlightedSpruceLaboratoryFloor = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataEnlightedSpruceLaboratoryFloor);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_spruce_laboratory_floor"), modelInstanceEnlightedSpruceLaboratoryFloor);

        var modelDataSpruceLaboratoryTiles = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/spruce_laboratory_tiles-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.ENLIGHTED_SPRUCE_LABORATORY_TILES.get())))
                .build();
        var modelInstanceSpruceLaboratoryTiles = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataSpruceLaboratoryTiles);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/spruce_laboratory_tiles"), modelInstanceSpruceLaboratoryTiles);

        var modelDataEnlightedSpruceLaboratoryTiles = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/spruce_laboratory_tiles-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.SPRUCE_LABORATORY_TILES.get())))
                .build();
        var modelInstanceEnlightedSpruceLaboratoryTiles = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataEnlightedSpruceLaboratoryTiles);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_spruce_laboratory_tiles"), modelInstanceEnlightedSpruceLaboratoryTiles);



        var modelDataBirchLaboratoryFloor = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/birch_laboratory_floor-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.ENLIGHTED_BIRCH_LABORATORY_FLOOR.get())))
                .build();
        var modelInstanceBirchLaboratoryFloor = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataBirchLaboratoryFloor);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/birch_laboratory_floor"), modelInstanceBirchLaboratoryFloor);

        var modelDataEnlightedBirchLaboratoryFloor = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/birch_laboratory_floor-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.BIRCH_LABORATORY_FLOOR.get())))
                .build();
        var modelInstanceEnlightedBirchLaboratoryFloor = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataEnlightedBirchLaboratoryFloor);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_birch_laboratory_floor"), modelInstanceEnlightedBirchLaboratoryFloor);

        var modelDataBirchLaboratoryTiles = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/birch_laboratory_tiles-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.ENLIGHTED_BIRCH_LABORATORY_TILES.get())))
                .build();
        var modelInstanceBirchLaboratoryTiles = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataBirchLaboratoryTiles);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/birch_laboratory_tiles"), modelInstanceBirchLaboratoryTiles);

        var modelDataEnlightedBirchLaboratoryTiles = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/birch_laboratory_tiles-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.BIRCH_LABORATORY_TILES.get())))
                .build();
        var modelInstanceEnlightedBirchLaboratoryTiles = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataEnlightedBirchLaboratoryTiles);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_birch_laboratory_tiles"), modelInstanceEnlightedBirchLaboratoryTiles);



        var modelDataDarkOakLaboratoryFloor = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/dark_oak_laboratory_floor-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.ENLIGHTED_DARK_OAK_LABORATORY_FLOOR.get())))
                .build();
        var modelInstanceDarkOakLaboratoryFloor = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataDarkOakLaboratoryFloor);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/dark_oak_laboratory_floor"), modelInstanceDarkOakLaboratoryFloor);

        var modelDataEnlightedDarkOakLaboratoryFloor = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/dark_oak_laboratory_floor-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.DARK_OAK_LABORATORY_FLOOR.get())))
                .build();
        var modelInstanceEnlightedDarkOakLaboratoryFloor = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataEnlightedDarkOakLaboratoryFloor);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_dark_oak_laboratory_floor"), modelInstanceEnlightedDarkOakLaboratoryFloor);

        var modelDataDarkOakLaboratoryTiles = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/dark_oak_laboratory_tiles-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.ENLIGHTED_DARK_OAK_LABORATORY_TILES.get())))
                .build();
        var modelInstanceDarkOakLaboratoryTiles = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataDarkOakLaboratoryTiles);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/dark_oak_laboratory_tiles"), modelInstanceDarkOakLaboratoryTiles);

        var modelDataEnlightedDarkOakLaboratoryTiles = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/dark_oak_laboratory_tiles-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.DARK_OAK_LABORATORY_TILES.get())))
                .build();
        var modelInstanceEnlightedDarkOakLaboratoryTiles = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataEnlightedDarkOakLaboratoryTiles);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_dark_oak_laboratory_tiles"), modelInstanceEnlightedDarkOakLaboratoryTiles);



        var modelDataAcaciaLaboratoryFloor = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/acacia_laboratory_floor-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.ENLIGHTED_ACACIA_LABORATORY_FLOOR.get())))
                .build();
        var modelInstanceAcaciaLaboratoryFloor = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataAcaciaLaboratoryFloor);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/acacia_laboratory_floor"), modelInstanceAcaciaLaboratoryFloor);

        var modelDataEnlightedAcaciaLaboratoryFloor = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/acacia_laboratory_floor-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.ACACIA_LABORATORY_FLOOR.get())))
                .build();
        var modelInstanceEnlightedAcaciaLaboratoryFloor = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataEnlightedAcaciaLaboratoryFloor);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_acacia_laboratory_floor"), modelInstanceEnlightedAcaciaLaboratoryFloor);

        var modelDataAcaciaLaboratoryTiles = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/acacia_laboratory_tiles-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.ENLIGHTED_ACACIA_LABORATORY_TILES.get())))
                .build();
        var modelInstanceAcaciaLaboratoryTiles = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataAcaciaLaboratoryTiles);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/acacia_laboratory_tiles"), modelInstanceAcaciaLaboratoryTiles);

        var modelDataEnlightedAcaciaLaboratoryTiles = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/acacia_laboratory_tiles-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.ACACIA_LABORATORY_TILES.get())))
                .build();
        var modelInstanceEnlightedAcaciaLaboratoryTiles = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataEnlightedAcaciaLaboratoryTiles);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_acacia_laboratory_tiles"), modelInstanceEnlightedAcaciaLaboratoryTiles);



        var modelDataJungleLaboratoryFloor = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/jungle_laboratory_floor-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.ENLIGHTED_JUNGLE_LABORATORY_FLOOR.get())))
                .build();
        var modelInstanceJungleLaboratoryFloor = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataJungleLaboratoryFloor);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/jungle_laboratory_floor"), modelInstanceJungleLaboratoryFloor);

        var modelDataEnlightedJungleLaboratoryFloor = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/jungle_laboratory_floor-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.JUNGLE_LABORATORY_FLOOR.get())))
                .build();
        var modelInstanceEnlightedJungleLaboratoryFloor = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataEnlightedJungleLaboratoryFloor);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_jungle_laboratory_floor"), modelInstanceEnlightedJungleLaboratoryFloor);

        var modelDataJungleLaboratoryTiles = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/jungle_laboratory_tiles-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.ENLIGHTED_JUNGLE_LABORATORY_TILES.get())))
                .build();
        var modelInstanceJungleLaboratoryTiles = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataJungleLaboratoryTiles);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/jungle_laboratory_tiles"), modelInstanceJungleLaboratoryTiles);

        var modelDataEnlightedJungleLaboratoryTiles = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/jungle_laboratory_tiles-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.JUNGLE_LABORATORY_TILES.get())))
                .build();
        var modelInstanceEnlightedJungleLaboratoryTiles = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataEnlightedJungleLaboratoryTiles);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_jungle_laboratory_tiles"), modelInstanceEnlightedJungleLaboratoryTiles);



        var modelDataMangroveLaboratoryFloor = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/mangrove_laboratory_floor-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.ENLIGHTED_MANGROVE_LABORATORY_FLOOR.get())))
                .build();
        var modelInstanceMangroveLaboratoryFloor = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataMangroveLaboratoryFloor);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/mangrove_laboratory_floor"), modelInstanceMangroveLaboratoryFloor);

        var modelDataEnlightedMangroveLaboratoryFloor = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/mangrove_laboratory_floor-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.MANGROVE_LABORATORY_FLOOR.get())))
                .build();
        var modelInstanceEnlightedMangroveLaboratoryFloor = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataEnlightedMangroveLaboratoryFloor);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_mangrove_laboratory_floor"), modelInstanceEnlightedMangroveLaboratoryFloor);

        var modelDataMangroveLaboratoryTiles = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/mangrove_laboratory_tiles-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.ENLIGHTED_MANGROVE_LABORATORY_TILES.get())))
                .build();
        var modelInstanceMangroveLaboratoryTiles = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataMangroveLaboratoryTiles);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/mangrove_laboratory_tiles"), modelInstanceMangroveLaboratoryTiles);

        var modelDataEnlightedMangroveLaboratoryTiles = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/mangrove_laboratory_tiles-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.MANGROVE_LABORATORY_TILES.get())))
                .build();
        var modelInstanceEnlightedMangroveLaboratoryTiles = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataEnlightedMangroveLaboratoryTiles);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_mangrove_laboratory_tiles"), modelInstanceEnlightedMangroveLaboratoryTiles);



        var modelDataCrimsonLaboratoryFloor = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/crimson_laboratory_floor-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.ENLIGHTED_CRIMSON_LABORATORY_FLOOR.get())))
                .build();
        var modelInstanceCrimsonLaboratoryFloor = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataCrimsonLaboratoryFloor);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/crimson_laboratory_floor"), modelInstanceCrimsonLaboratoryFloor);

        var modelDataEnlightedCrimsonLaboratoryFloor = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/crimson_laboratory_floor-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.CRIMSON_LABORATORY_FLOOR.get())))
                .build();
        var modelInstanceEnlightedCrimsonLaboratoryFloor = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataEnlightedCrimsonLaboratoryFloor);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_crimson_laboratory_floor"), modelInstanceEnlightedCrimsonLaboratoryFloor);

        var modelDataCrimsonLaboratoryTiles = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/crimson_laboratory_tiles-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.ENLIGHTED_CRIMSON_LABORATORY_TILES.get())))
                .build();
        var modelInstanceCrimsonLaboratoryTiles = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataCrimsonLaboratoryTiles);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/crimson_laboratory_tiles"), modelInstanceCrimsonLaboratoryTiles);

        var modelDataEnlightedCrimsonLaboratoryTiles = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/crimson_laboratory_tiles-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.CRIMSON_LABORATORY_TILES.get())))
                .build();
        var modelInstanceEnlightedCrimsonLaboratoryTiles = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataEnlightedCrimsonLaboratoryTiles);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_crimson_laboratory_tiles"), modelInstanceEnlightedCrimsonLaboratoryTiles);



        var modelDataWarpedLaboratoryFloor = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/warped_laboratory_floor-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.ENLIGHTED_WARPED_LABORATORY_FLOOR.get())))
                .build();
        var modelInstanceWarpedLaboratoryFloor = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataWarpedLaboratoryFloor);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/warped_laboratory_floor"), modelInstanceWarpedLaboratoryFloor);

        var modelDataEnlightedWarpedLaboratoryFloor = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/warped_laboratory_floor-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.WARPED_LABORATORY_FLOOR.get())))
                .build();
        var modelInstanceEnlightedWarpedLaboratoryFloor = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataEnlightedWarpedLaboratoryFloor);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_warped_laboratory_floor"), modelInstanceEnlightedWarpedLaboratoryFloor);

        var modelDataWarpedLaboratoryTiles = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/warped_laboratory_tiles-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.ENLIGHTED_WARPED_LABORATORY_TILES.get())))
                .build();
        var modelInstanceWarpedLaboratoryTiles = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataWarpedLaboratoryTiles);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/warped_laboratory_tiles"), modelInstanceWarpedLaboratoryTiles);

        var modelDataEnlightedWarpedLaboratoryTiles = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/warped_laboratory_tiles-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.WARPED_LABORATORY_TILES.get())))
                .build();
        var modelInstanceEnlightedWarpedLaboratoryTiles = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataEnlightedWarpedLaboratoryTiles);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_warped_laboratory_tiles"), modelInstanceEnlightedWarpedLaboratoryTiles);



        var modelDataCherryLaboratoryFloor = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/cherry_laboratory_floor-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.ENLIGHTED_CHERRY_LABORATORY_FLOOR.get())))
                .build();
        var modelInstanceCherryLaboratoryFloor = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataCherryLaboratoryFloor);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/cherry_laboratory_floor"), modelInstanceCherryLaboratoryFloor);

        var modelDataEnlightedCherryLaboratoryFloor = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/cherry_laboratory_floor-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.CHERRY_LABORATORY_FLOOR.get())))
                .build();
        var modelInstanceEnlightedCherryLaboratoryFloor = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataEnlightedCherryLaboratoryFloor);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_cherry_laboratory_floor"), modelInstanceEnlightedCherryLaboratoryFloor);

        var modelDataCherryLaboratoryTiles = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/cherry_laboratory_tiles-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.ENLIGHTED_CHERRY_LABORATORY_TILES.get())))
                .build();
        var modelInstanceCherryLaboratoryTiles = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataCherryLaboratoryTiles);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/cherry_laboratory_tiles"), modelInstanceCherryLaboratoryTiles);

        var modelDataEnlightedCherryLaboratoryTiles = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/cherry_laboratory_tiles-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.CHERRY_LABORATORY_TILES.get())))
                .build();
        var modelInstanceEnlightedCherryLaboratoryTiles = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataEnlightedCherryLaboratoryTiles);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_cherry_laboratory_tiles"), modelInstanceEnlightedCherryLaboratoryTiles);



        var modelDataBambooLaboratoryFloor = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/bamboo_laboratory_floor-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.ENLIGHTED_BAMBOO_LABORATORY_FLOOR.get())))
                .build();
        var modelInstanceBambooLaboratoryFloor = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataBambooLaboratoryFloor);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/bamboo_laboratory_floor"), modelInstanceBambooLaboratoryFloor);

        var modelDataEnlightedBambooLaboratoryFloor = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/bamboo_laboratory_floor-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.BAMBOO_LABORATORY_FLOOR.get())))
                .build();
        var modelInstanceEnlightedBambooLaboratoryFloor = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataEnlightedBambooLaboratoryFloor);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_bamboo_laboratory_floor"), modelInstanceEnlightedBambooLaboratoryFloor);

        var modelDataBambooLaboratoryTiles = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/bamboo_laboratory_tiles-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.ENLIGHTED_BAMBOO_LABORATORY_TILES.get())))
                .build();
        var modelInstanceBambooLaboratoryTiles = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataBambooLaboratoryTiles);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/bamboo_laboratory_tiles"), modelInstanceBambooLaboratoryTiles);

        var modelDataEnlightedBambooLaboratoryTiles = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/bamboo_laboratory_tiles-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.BAMBOO_LABORATORY_TILES.get())))
                .build();
        var modelInstanceEnlightedBambooLaboratoryTiles = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataEnlightedBambooLaboratoryTiles);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_bamboo_laboratory_tiles"), modelInstanceEnlightedBambooLaboratoryTiles);

    }
}
