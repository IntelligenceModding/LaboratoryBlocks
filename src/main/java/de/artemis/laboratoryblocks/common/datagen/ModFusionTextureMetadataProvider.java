package de.artemis.laboratoryblocks.common.datagen;

import com.google.gson.JsonObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.artemis.laboratoryblocks.LaboratoryBlocks;
import net.minecraft.data.IDataProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class ModFusionTextureMetadataProvider implements IDataProvider {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final List<String> ANIMATED_SCREENS = Arrays.asList(
            "wave_laboratory_screen",
            "text_laboratory_screen",
            "quantum_laboratory_screen"
    );

    private final Path texturesPath;

    public ModFusionTextureMetadataProvider(DataGenerator generator) {
        this.texturesPath = generator.getOutputFolder()
                .resolve("assets")
                .resolve(LaboratoryBlocks.MOD_ID)
                .resolve("textures")
                .resolve("block");
    }

    @Override
    public void run(@NotNull DirectoryCache cache) throws IOException {
        for (ModDatagenEntries.GeneratedBlockPair pair : ModDatagenEntries.ALL_PAIRS) {
            saveTextureMetadata(cache, pair);
        }
    }

    @Override
    public @NotNull String getName() {
        return "Fusion Texture Metadata Provider: " + LaboratoryBlocks.MOD_ID;
    }

    private void saveTextureMetadata(DirectoryCache cache, ModDatagenEntries.GeneratedBlockPair pair) throws IOException {
        JsonObject json = new JsonObject();

        if (isAnimatedScreen(pair)) {
            JsonObject animation = new JsonObject();
            animation.addProperty("frametime", 2);
            json.add("animation", animation);
        }

        JsonObject fusion = new JsonObject();
        fusion.addProperty("type", "connecting");
        fusion.addProperty("layout", isIndicating(pair) ? "horizontal" : "pieced");
        if (isGlass(pair)) {
            fusion.addProperty("render_type", "cutout");
        }
        json.add("fusion", fusion);

        IDataProvider.save(GSON, cache, json, texturePath(texture(pair.fusionTexturePath())));
    }

    private static boolean isAnimatedScreen(ModDatagenEntries.GeneratedBlockPair pair) {
        return ANIMATED_SCREENS.contains(pair.texturePath());
    }

    private static boolean isIndicating(ModDatagenEntries.GeneratedBlockPair pair) {
        return pair.texturePath().contains("_indicating_");
    }

    private static boolean isGlass(ModDatagenEntries.GeneratedBlockPair pair) {
        return "laboratory_glass".equals(pair.texturePath());
    }

    private static ResourceLocation texture(String path) {
        return new ResourceLocation(LaboratoryBlocks.MOD_ID, path);
    }

    private Path texturePath(ResourceLocation id) {
        return this.texturesPath.resolve(id.getPath() + ".png.mcmeta");
    }
}
