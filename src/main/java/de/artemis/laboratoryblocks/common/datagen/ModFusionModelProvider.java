package de.artemis.laboratoryblocks.common.datagen;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import de.artemis.laboratoryblocks.LaboratoryBlocks;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModFusionModelProvider implements DataProvider {
    private static final String CUTOUT = "minecraft:cutout";

    private final PackOutput.PathProvider models;

    public ModFusionModelProvider(PackOutput output) {
        this.models = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "models/block");
    }

    @Override
    public @NotNull CompletableFuture<?> run(@NotNull CachedOutput output) {
        List<CompletableFuture<?>> futures = new ArrayList<>();
        ModDatagenEntries.ALL_PAIRS.forEach(pair -> {
            futures.add(saveConnectingModel(output, pair.baseModelName(), pair.glowingModelName(), pair.fusionTexturePath(), isGlass(pair)));
            futures.add(saveConnectingModel(output, pair.glowingModelName(), pair.baseModelName(), pair.fusionTexturePath(), isGlass(pair)));
        });
        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }

    @Override
    public @NotNull String getName() {
        return "Fusion Model Provider: " + LaboratoryBlocks.MOD_ID;
    }

    private CompletableFuture<?> saveConnectingModel(CachedOutput output, String modelName, String matchingBlock, String texturePath, boolean cutout) {
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

        return DataProvider.saveStable(output, json, models.json(id(modelName)));
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
        return ResourceLocation.fromNamespaceAndPath(LaboratoryBlocks.MOD_ID, path);
    }

    private static String modPath(String path) {
        return LaboratoryBlocks.MOD_ID + ":" + path;
    }
}