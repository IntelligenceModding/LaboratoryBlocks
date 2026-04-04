package de.artemis.laboratoryblocks.common.datagen;

import com.supermartijn642.fusion.api.provider.FusionTextureMetadataProvider;
import com.supermartijn642.fusion.api.texture.DefaultTextureTypes;
import com.supermartijn642.fusion.api.texture.data.ConnectingTextureData;
import com.supermartijn642.fusion.api.texture.data.ConnectingTextureLayout;
import de.artemis.laboratoryblocks.LaboratoryBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;

public class ModFusionTextureMetadataProvider extends FusionTextureMetadataProvider {
    public ModFusionTextureMetadataProvider(PackOutput output) {
        super(LaboratoryBlocks.MOD_ID, output);
    }

    @Override
    protected void generate() {
        var defaultTextureData = ConnectingTextureData.builder()
                .layout(ConnectingTextureLayout.PIECED)
                .build();
        var indicatingTextureData = ConnectingTextureData.builder()
                .layout(ConnectingTextureLayout.HORIZONTAL)
                .build();

        ModDatagenEntries.ALL_PAIRS.forEach(pair ->
                this.addTextureMetadata(
                        texture(pair.fusionTexturePath()),
                        DefaultTextureTypes.CONNECTING,
                        isIndicating(pair) ? indicatingTextureData : defaultTextureData
                )
        );
    }

    private static boolean isIndicating(ModDatagenEntries.GeneratedBlockPair pair) {
        return pair.texturePath().contains("_indicating_");
    }

    private static Identifier texture(String path) {
        return Identifier.fromNamespaceAndPath(LaboratoryBlocks.MOD_ID, "block/" + path);
    }
}
