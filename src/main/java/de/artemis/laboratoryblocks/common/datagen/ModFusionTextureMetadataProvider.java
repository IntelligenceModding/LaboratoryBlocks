package de.artemis.laboratoryblocks.common.datagen;

import com.google.gson.JsonObject;
import de.artemis.laboratoryblocks.LaboratoryBlocks;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

public class ModFusionTextureMetadataProvider implements DataProvider {
    private static final List<String> ANIMATED_SCREENS = List.of(
            "wave_laboratory_screen",
            "text_laboratory_screen",
            "quantum_laboratory_screen"
    );

    private final DataGenerator.PathProvider textures;

    public ModFusionTextureMetadataProvider(DataGenerator generator) {
        this.textures = generator.createPathProvider(DataGenerator.Target.RESOURCE_PACK, "textures/block");
    }

    @Override
    public void run(@NotNull CachedOutput output) throws IOException {
        for (ModDatagenEntries.GeneratedBlockPair pair : ModDatagenEntries.ALL_PAIRS) {
            saveTextureMetadata(output, pair);
        }
    }

    @Override
    public @NotNull String getName() {
        return "Fusion Texture Metadata Provider: " + LaboratoryBlocks.MOD_ID;
    }

    private void saveTextureMetadata(CachedOutput output, ModDatagenEntries.GeneratedBlockPair pair) throws IOException {
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

        DataProvider.saveStable(output, json, textures.file(texture(pair.fusionTexturePath()), "png.mcmeta"));
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
}
