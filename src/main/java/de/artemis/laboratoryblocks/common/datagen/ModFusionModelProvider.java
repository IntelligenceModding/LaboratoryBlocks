package de.artemis.laboratoryblocks.common.datagen;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import de.artemis.laboratoryblocks.LaboratoryBlocks;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class ModFusionModelProvider implements DataProvider {
    private static final String CUTOUT = "minecraft:cutout";

    private final DataGenerator.PathProvider models;

    public ModFusionModelProvider(DataGenerator generator) {
        this.models = generator.createPathProvider(DataGenerator.Target.RESOURCE_PACK, "models/block");
    }

    @Override
    public void run(@NotNull CachedOutput output) throws IOException {
        for (ModDatagenEntries.GeneratedBlockPair pair : ModDatagenEntries.ALL_PAIRS) {
            saveConnectingModel(output, pair.baseModelName(), pair.glowingModelName(), pair.fusionTexturePath(), isGlass(pair));
            saveConnectingModel(output, pair.glowingModelName(), pair.baseModelName(), pair.fusionTexturePath(), isGlass(pair));
        }
    }

    @Override
    public @NotNull String getName() {
        return "Fusion Model Provider: " + LaboratoryBlocks.MOD_ID;
    }

    private void saveConnectingModel(CachedOutput output, String modelName, String matchingBlock, String texturePath, boolean cutout) throws IOException {
        JsonObject json = new JsonObject();
        json.addProperty("type", "fusion:connecting");
        json.addProperty("parent", "minecraft:block/cube_all");
        json.addProperty("loader", "fusion:model");
        if (cutout) {
            json.addProperty("render_type", CUTOUT);
        }
        json.add("connections", createConnections(matchingBlock));

        JsonObject textures = new JsonObject();
        textures.addProperty("all", modPath("block/" + texturePath));
        json.add("textures", textures);

        DataProvider.saveStable(output, json, models.json(id(modelName)));
    }

    private static JsonObject createConnections(String matchingBlock) {
        JsonObject connections = new JsonObject();
        connections.addProperty("type", "fusion:or");

        JsonArray predicates = new JsonArray();
        JsonObject sameBlock = new JsonObject();
        sameBlock.addProperty("type", "fusion:is_same_block");
        predicates.add(sameBlock);

        JsonObject matchBlock = new JsonObject();
        matchBlock.addProperty("type", "fusion:match_block");
        matchBlock.addProperty("block", modPath(matchingBlock));
        predicates.add(matchBlock);

        connections.add("predicates", predicates);
        return connections;
    }

    private static boolean isGlass(ModDatagenEntries.GeneratedBlockPair pair) {
        return "laboratory_glass".equals(pair.texturePath());
    }
    private static ResourceLocation id(String path) {
        return new ResourceLocation(LaboratoryBlocks.MOD_ID, path);
    }

    private static String modPath(String path) {
        return LaboratoryBlocks.MOD_ID + ":" + path;
    }
}
