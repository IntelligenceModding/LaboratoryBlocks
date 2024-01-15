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



        var modelDataLaboratoryVent = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/laboratory_vent-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.ENLIGHTED_LABORATORY_VENT.get())))
                .build();
        var modelInstanceLaboratoryVent = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataLaboratoryVent);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/laboratory_vent"), modelInstanceLaboratoryVent);

        var modelDataEnlightedLaboratoryVent = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/laboratory_vent-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.LABORATORY_VENT.get())))
                .build();
        var modelInstanceEnlightedLaboratoryVent = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataEnlightedLaboratoryVent);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_laboratory_vent"), modelInstanceEnlightedLaboratoryVent);



        var modelDataLaboratoryVentConnecting = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/laboratory_vent_connecting-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.ENLIGHTED_LABORATORY_VENT_CONNECTING.get())).or(DefaultConnectionPredicates.matchBlock(ModBlocks.LABORATORY_BLOCK.get())).or(DefaultConnectionPredicates.matchBlock(ModBlocks.ENLIGHTED_LABORATORY_BLOCK.get())))
                .build();
        var modelInstanceLaboratoryVentConnecting = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataLaboratoryVentConnecting);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/laboratory_vent_connecting"), modelInstanceLaboratoryVentConnecting);

        var modelDataEnlightedLaboratoryVentConnecting = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/laboratory_vent_connecting-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.LABORATORY_VENT_CONNECTING.get())).or(DefaultConnectionPredicates.matchBlock(ModBlocks.LABORATORY_BLOCK.get())).or(DefaultConnectionPredicates.matchBlock(ModBlocks.ENLIGHTED_LABORATORY_BLOCK.get())))
                .build();
        var modelInstanceEnlightedLaboratoryVentConnecting = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataEnlightedLaboratoryVentConnecting);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_laboratory_vent_connecting"), modelInstanceEnlightedLaboratoryVentConnecting);



        var modelDataClearLaboratoryScreen = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/clear_laboratory_screen-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.ENLIGHTED_CLEAR_LABORATORY_SCREEN.get())))
                .build();
        var modelInstanceClearLaboratoryScreen = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataClearLaboratoryScreen);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/clear_laboratory_screen"), modelInstanceClearLaboratoryScreen);

        var modelDataEnlightedClearLaboratoryScreen = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/clear_laboratory_screen-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.CLEAR_LABORATORY_SCREEN.get())))
                .build();
        var modelInstanceEnlightedClearLaboratoryScreen = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataEnlightedClearLaboratoryScreen);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_clear_laboratory_screen"), modelInstanceEnlightedClearLaboratoryScreen);



        var modelDataLeftFacedBlueSignalingLaboratoryBlock = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/left-faced_blue_signaling_laboratory_block-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.ENLIGHTED_LEFT_FACED_BLUE_SIGNALING_LABORATORY_BLOCK.get())))
                .build();
        var modelInstanceLeftFacedBlueSignalingLaboratoryBlock = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataLeftFacedBlueSignalingLaboratoryBlock);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/left-faced_blue_signaling_laboratory_block"), modelInstanceLeftFacedBlueSignalingLaboratoryBlock);

        var modelDataEnlightedLeftFacedBlueSignalingLaboratoryBlock = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/left-faced_blue_signaling_laboratory_block-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.LEFT_FACED_BLUE_SIGNALING_LABORATORY_BLOCK.get())))
                .build();
        var modelInstanceEnlightedLeftFacedBlueSignalingLaboratoryBlock = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataEnlightedLeftFacedBlueSignalingLaboratoryBlock);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_left-faced_blue_signaling_laboratory_block"), modelInstanceEnlightedLeftFacedBlueSignalingLaboratoryBlock);



        var modelDataLeftFacedGreenSignalingLaboratoryBlock = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/left-faced_green_signaling_laboratory_block-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.ENLIGHTED_LEFT_FACED_GREEN_SIGNALING_LABORATORY_BLOCK.get())))
                .build();
        var modelInstanceLeftFacedGreenSignalingLaboratoryBlock = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataLeftFacedGreenSignalingLaboratoryBlock);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/left-faced_green_signaling_laboratory_block"), modelInstanceLeftFacedGreenSignalingLaboratoryBlock);

        var modelDataEnlightedLeftFacedGreenSignalingLaboratoryBlock = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/left-faced_green_signaling_laboratory_block-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.LEFT_FACED_GREEN_SIGNALING_LABORATORY_BLOCK.get())))
                .build();
        var modelInstanceEnlightedLeftFacedGreenSignalingLaboratoryBlock = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataEnlightedLeftFacedGreenSignalingLaboratoryBlock);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_left-faced_green_signaling_laboratory_block"), modelInstanceEnlightedLeftFacedGreenSignalingLaboratoryBlock);



        var modelDataLeftFacedRedSignalingLaboratoryBlock = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/left-faced_red_signaling_laboratory_block-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.ENLIGHTED_LEFT_FACED_RED_SIGNALING_LABORATORY_BLOCK.get())))
                .build();
        var modelInstanceLeftFacedRedSignalingLaboratoryBlock = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataLeftFacedRedSignalingLaboratoryBlock);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/left-faced_red_signaling_laboratory_block"), modelInstanceLeftFacedRedSignalingLaboratoryBlock);

        var modelDataEnlightedLeftFacedRedSignalingLaboratoryBlock = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/left-faced_red_signaling_laboratory_block-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.LEFT_FACED_RED_SIGNALING_LABORATORY_BLOCK.get())))
                .build();
        var modelInstanceEnlightedLeftFacedRedSignalingLaboratoryBlock = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataEnlightedLeftFacedRedSignalingLaboratoryBlock);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_left-faced_red_signaling_laboratory_block"), modelInstanceEnlightedLeftFacedRedSignalingLaboratoryBlock);



        var modelDataRightFacedBlueSignalingLaboratoryBlock = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/right-faced_blue_signaling_laboratory_block-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.ENLIGHTED_RIGHT_FACED_BLUE_SIGNALING_LABORATORY_BLOCK.get())))
                .build();
        var modelInstanceRightFacedBlueSignalingLaboratoryBlock = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataRightFacedBlueSignalingLaboratoryBlock);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/right-faced_blue_signaling_laboratory_block"), modelInstanceRightFacedBlueSignalingLaboratoryBlock);

        var modelDataEnlightedRightFacedBlueSignalingLaboratoryBlock = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/right-faced_blue_signaling_laboratory_block-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.RIGHT_FACED_BLUE_SIGNALING_LABORATORY_BLOCK.get())))
                .build();
        var modelInstanceEnlightedRightFacedBlueSignalingLaboratoryBlock = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataEnlightedRightFacedBlueSignalingLaboratoryBlock);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_right-faced_blue_signaling_laboratory_block"), modelInstanceEnlightedRightFacedBlueSignalingLaboratoryBlock);



        var modelDataRightFacedGreenSignalingLaboratoryBlock = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/right-faced_green_signaling_laboratory_block-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.ENLIGHTED_RIGHT_FACED_GREEN_SIGNALING_LABORATORY_BLOCK.get())))
                .build();
        var modelInstanceRightFacedGreenSignalingLaboratoryBlock = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataRightFacedGreenSignalingLaboratoryBlock);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/right-faced_green_signaling_laboratory_block"), modelInstanceRightFacedGreenSignalingLaboratoryBlock);

        var modelDataEnlightedRightFacedGreenSignalingLaboratoryBlock = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/right-faced_green_signaling_laboratory_block-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.RIGHT_FACED_GREEN_SIGNALING_LABORATORY_BLOCK.get())))
                .build();
        var modelInstanceEnlightedRightFacedGreenSignalingLaboratoryBlock = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataEnlightedRightFacedGreenSignalingLaboratoryBlock);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_right-faced_green_signaling_laboratory_block"), modelInstanceEnlightedRightFacedGreenSignalingLaboratoryBlock);



        var modelDataRightFacedRedSignalingLaboratoryBlock = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/right-faced_red_signaling_laboratory_block-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.ENLIGHTED_RIGHT_FACED_RED_SIGNALING_LABORATORY_BLOCK.get())))
                .build();
        var modelInstanceRightFacedRedSignalingLaboratoryBlock = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataRightFacedRedSignalingLaboratoryBlock);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/right-faced_red_signaling_laboratory_block"), modelInstanceRightFacedRedSignalingLaboratoryBlock);

        var modelDataEnlightedRightFacedRedSignalingLaboratoryBlock = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/right-faced_red_signaling_laboratory_block-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.RIGHT_FACED_RED_SIGNALING_LABORATORY_BLOCK.get())))
                .build();
        var modelInstanceEnlightedRightFacedRedSignalingLaboratoryBlock = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataEnlightedRightFacedRedSignalingLaboratoryBlock);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_right-faced_red_signaling_laboratory_block"), modelInstanceEnlightedRightFacedRedSignalingLaboratoryBlock);



        var modelDataScrewedLaboratoryBlock = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/screwed_laboratory_block-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.ENLIGHTED_SCREWED_LABORATORY_BLOCK.get())))
                .build();
        var modelInstanceScrewedLaboratoryBlock = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataScrewedLaboratoryBlock);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/screwed_laboratory_block"), modelInstanceScrewedLaboratoryBlock);

        var modelDataEnlightedScrewedLaboratoryBlock = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/screwed_laboratory_block-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.SCREWED_LABORATORY_BLOCK.get())))
                .build();
        var modelInstanceEnlightedScrewedLaboratoryBlock = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataEnlightedScrewedLaboratoryBlock);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_screwed_laboratory_block"), modelInstanceEnlightedScrewedLaboratoryBlock);



        var modelDataLaboratoryTiles = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/laboratory_tiles-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.ENLIGHTED_LABORATORY_TILES.get())))
                .build();
        var modelInstanceLaboratoryTiles = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataLaboratoryTiles);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/laboratory_tiles"), modelInstanceLaboratoryTiles);

        var modelDataEnlightedLaboratoryTiles = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/laboratory_tiles-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.LABORATORY_TILES.get())))
                .build();
        var modelInstanceEnlightedLaboratoryTiles = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataEnlightedLaboratoryTiles);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_laboratory_tiles"), modelInstanceEnlightedLaboratoryTiles);



        var modelDataGrayLaboratoryTiles = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/gray_laboratory_tiles-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.ENLIGHTED_GRAY_LABORATORY_TILES.get())))
                .build();
        var modelInstanceGrayLaboratoryTiles = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataGrayLaboratoryTiles);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/gray_laboratory_tiles"), modelInstanceGrayLaboratoryTiles);

        var modelDataEnlightedGrayLaboratoryTiles = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/gray_laboratory_tiles-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.GRAY_LABORATORY_TILES.get())))
                .build();
        var modelInstanceEnlightedGrayLaboratoryTiles = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataEnlightedGrayLaboratoryTiles);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_gray_laboratory_tiles"), modelInstanceEnlightedGrayLaboratoryTiles);



        var modelDataMixedLaboratoryTiles = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/mixed_laboratory_tiles-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.ENLIGHTED_MIXED_LABORATORY_TILES.get())))
                .build();
        var modelInstanceMixedLaboratoryTiles = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataMixedLaboratoryTiles);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/mixed_laboratory_tiles"), modelInstanceMixedLaboratoryTiles);

        var modelDataEnlightedMixedLaboratoryTiles = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/mixed_laboratory_tiles-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.MIXED_LABORATORY_TILES.get())))
                .build();
        var modelInstanceEnlightedMixedLaboratoryTiles = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataEnlightedMixedLaboratoryTiles);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_mixed_laboratory_tiles"), modelInstanceEnlightedMixedLaboratoryTiles);



        var modelDataLaboratoryGlass = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/laboratory_glass-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock())
                .build();
        var modelInstanceLaboratoryGlass = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataLaboratoryGlass);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/laboratory_glass"), modelInstanceLaboratoryGlass);

        var modelDataEnlightedLaboratoryGlass = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/laboratory_glass-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock())
                .build();
        var modelInstanceEnlightedLaboratoryGlass = ModelInstance.of(DefaultModelTypes.CONNECTING, modelDataEnlightedLaboratoryGlass);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_laboratory_glass"), modelInstanceEnlightedLaboratoryGlass);



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
