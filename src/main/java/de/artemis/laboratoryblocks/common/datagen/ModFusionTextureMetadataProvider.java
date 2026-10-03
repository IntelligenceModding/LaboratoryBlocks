package de.artemis.laboratoryblocks.common.datagen;

import com.google.gson.JsonObject;
import de.artemis.laboratoryblocks.LaboratoryBlocks;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModFusionTextureMetadataProvider implements DataProvider {
    private final PackOutput.PathProvider textures;

    public ModFusionTextureMetadataProvider(PackOutput output) {
        this.textures = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "textures/block");
    }

    @Override
    public @NotNull CompletableFuture<?> run(@NotNull CachedOutput output) {
        List<CompletableFuture<?>> futures = new ArrayList<>();
        ModDatagenEntries.ALL_PAIRS.forEach(pair -> futures.add(saveTextureMetadata(output, pair)));
        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }

    @Override
    public @NotNull String getName() {
        return "Fusion Texture Metadata Provider: " + LaboratoryBlocks.MOD_ID;
    }

    private CompletableFuture<?> saveTextureMetadata(CachedOutput output, ModDatagenEntries.GeneratedBlockPair pair) {
        if (isAnimatedScreen(pair)) {
            return CompletableFuture.completedFuture(null);
        }

        JsonObject json = new JsonObject();

        JsonObject fusion = new JsonObject();
        fusion.addProperty("type", "connecting");
        fusion.addProperty("layout", isIndicating(pair) ? "horizontal" : "pieced");
        json.add("fusion", fusion);

        return DataProvider.saveStable(output, json, textures.file(texture(pair.fusionTexturePath()), "png.mcmeta"));
    }

    private static boolean isAnimatedScreen(ModDatagenEntries.GeneratedBlockPair pair) {
        return java.util.stream.Stream.of(
                "wave_laboratory_screen",
                "text_laboratory_screen",
                "quantum_laboratory_screen"
        ).anyMatch(pair.texturePath()::equals);
    }

    private static boolean isIndicating(ModDatagenEntries.GeneratedBlockPair pair) {
        return pair.texturePath().contains("_indicating_");
    }

    private static Identifier texture(String path) {
        return Identifier.fromNamespaceAndPath(LaboratoryBlocks.MOD_ID, path);
    }
}