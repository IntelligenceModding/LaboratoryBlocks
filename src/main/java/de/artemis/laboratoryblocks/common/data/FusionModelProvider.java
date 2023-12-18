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

        var modelData = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/laboratory_block-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.ENLIGHTED_LABORATORY_BLOCK.get())))
                .build();
        var modelInstance = ModelInstance.of(DefaultModelTypes.CONNECTING, modelData);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/laboratory_block"), modelInstance);

        var modelData1 = ConnectingModelDataBuilder.builder()
                .parent(new ResourceLocation("minecraft", "block/cube_all"))
                .texture("all", new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/laboratory_block-fusion"))
                .connection(DefaultConnectionPredicates.isSameBlock().or(DefaultConnectionPredicates.matchBlock(ModBlocks.LABORATORY_BLOCK.get())))
                .build();
        var modelInstance1 = ModelInstance.of(DefaultModelTypes.CONNECTING, modelData1);
        this.addModel(new ResourceLocation(LaboratoryBlocks.MOD_ID, "block/enlighted_laboratory_block"), modelInstance1);

    }
}
