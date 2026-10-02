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

public class ModModelProvider implements IDataProvider {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final String CUTOUT = "minecraft:cutout";

    private final Path blockstatesPath;
    private final Path blockModelPath;
    private final Path itemModelPath;

    public ModModelProvider(DataGenerator generator) {
        Path assetsPath = generator.getOutputFolder().resolve("assets").resolve(LaboratoryBlocks.MOD_ID);
        this.blockstatesPath = assetsPath.resolve("blockstates");
        this.blockModelPath = assetsPath.resolve("models").resolve("block");
        this.itemModelPath = assetsPath.resolve("models").resolve("item");
    }

    @Override
    public void run(@NotNull DirectoryCache cache) throws IOException {
        saveFlatItem(cache, "iron_screw");
        saveFlatItem(cache, "glowstone_particles");
        saveHandheldItem(cache, "configuration_tool");

        for (ModDatagenEntries.GeneratedBlockPair pair : ModDatagenEntries.ALL_PAIRS) {
            saveSimpleBlockstate(cache, pair.baseModelName(), pair.baseModelName());
            saveSimpleBlockstate(cache, pair.glowingModelName(), pair.glowingModelName());
            saveBlockItemDefinition(cache, pair.baseModelName(), pair.baseModelName() + "_inventory");
            saveBlockItemDefinition(cache, pair.glowingModelName(), pair.glowingModelName() + "_inventory");

            if (isGlass(pair)) {
                saveCutoutCubeInventoryModel(cache, pair.baseModelName() + "_inventory", pair.texturePath());
                saveCutoutGlowingInventoryModel(cache, pair.glowingModelName() + "_inventory", pair.texturePath());
            } else {
                saveCubeInventoryModel(cache, pair.baseModelName() + "_inventory", pair.texturePath());
                saveGlowingInventoryModel(cache, pair.glowingModelName() + "_inventory", pair.texturePath());
            }
        }

        for (ModDatagenEntries.GeneratedPillarPair pair : ModDatagenEntries.PILLAR_PAIRS) {
            saveSimpleBlockstate(cache, pair.baseModelName(), pair.baseModelName());
            saveSimpleBlockstate(cache, pair.glowingModelName(), pair.glowingModelName());
            saveCubeColumnModel(cache, pair.baseModelName(), pair.sideTexturePath(), pair.endTexturePath());
            saveCubeColumnModel(cache, pair.glowingModelName(), pair.sideTexturePath(), pair.endTexturePath());
            saveCubeColumnModel(cache, pair.baseModelName() + "_inventory", pair.sideTexturePath(), pair.endTexturePath());
            saveGlowingPillarInventoryModel(cache, pair.glowingModelName() + "_inventory", pair.sideTexturePath(), pair.endTexturePath());
            saveBlockItemDefinition(cache, pair.baseModelName(), pair.baseModelName() + "_inventory");
            saveBlockItemDefinition(cache, pair.glowingModelName(), pair.glowingModelName() + "_inventory");
        }

        for (net.minecraftforge.fml.RegistryObject<? extends net.minecraft.block.DoorBlock> door : ModDatagenEntries.DOORS) {
            String name = door.getId().getPath();
            saveFlatItem(cache, name);
            saveDoorBlockstate(cache, name);
            saveDoorModel(cache, name + "_bottom", "minecraft:block/door_bottom", name);
            saveDoorModel(cache, name + "_bottom_hinge", "minecraft:block/door_bottom_rh", name);
            saveDoorModel(cache, name + "_top", "minecraft:block/door_top", name);
            saveDoorModel(cache, name + "_top_hinge", "minecraft:block/door_top_rh", name);
        }

        for (net.minecraftforge.fml.RegistryObject<? extends net.minecraft.block.TrapDoorBlock> trapdoor : ModDatagenEntries.TRAPDOORS) {
            String name = trapdoor.getId().getPath();
            saveBlockItemDefinition(cache, name, name + "_bottom");
            saveTrapdoorBlockstate(cache, name);
            saveTrapdoorModel(cache, name + "_bottom", "minecraft:block/template_orientable_trapdoor_bottom", name);
            saveTrapdoorModel(cache, name + "_open", "minecraft:block/template_orientable_trapdoor_open", name);
            saveTrapdoorModel(cache, name + "_top", "minecraft:block/template_orientable_trapdoor_top", name);
        }

        saveCubeBlockModel(cache, "laboratory_fan", "laboratory_fan");
        saveCubeBlockModel(cache, "laboratory_fan_powered", "laboratory_fan");
        saveCubeBlockModel(cache, "laboratory_fan_unpowered", "laboratory_fan_unpowered");
        saveCubeBlockModel(cache, "laboratory_fan_inventory", "laboratory_fan");
        saveGlowingInventoryModel(cache, "glowing_laboratory_fan_inventory", "laboratory_fan");
        saveCubeBlockModel(cache, "laboratory_fan_redstone_controlled_inventory", "laboratory_fan_redstone_controlled");
        saveGlowingInventoryModel(cache, "glowing_laboratory_fan_redstone_controlled_inventory", "laboratory_fan_redstone_controlled");
        saveSimpleBlockstate(cache, "laboratory_fan", "laboratory_fan");
        saveSimpleBlockstate(cache, "glowing_laboratory_fan", "laboratory_fan");
        savePoweredBlockstate(cache, "laboratory_fan_redstone_controlled");
        savePoweredBlockstate(cache, "glowing_laboratory_fan_redstone_controlled");
        saveBlockItemDefinition(cache, "laboratory_fan", "laboratory_fan_inventory");
        saveBlockItemDefinition(cache, "glowing_laboratory_fan", "glowing_laboratory_fan_inventory");
        saveBlockItemDefinition(cache, "laboratory_fan_redstone_controlled", "laboratory_fan_redstone_controlled_inventory");
        saveBlockItemDefinition(cache, "glowing_laboratory_fan_redstone_controlled", "glowing_laboratory_fan_redstone_controlled_inventory");
    }

    @Override
    public @NotNull String getName() {
        return "Model Definitions: " + LaboratoryBlocks.MOD_ID;
    }

    private void saveFlatItem(DirectoryCache cache, String itemName) throws IOException {
        saveFlatItemModel(cache, itemName, "minecraft:item/generated");
    }

    @SuppressWarnings("all")
    private void saveHandheldItem(DirectoryCache cache, String itemName) throws IOException {
        saveFlatItemModel(cache, itemName, "minecraft:item/handheld");
    }

    private void saveFlatItemModel(DirectoryCache cache, String itemName, String parent) throws IOException {
        JsonObject json = new JsonObject();
        json.addProperty("parent", parent);

        JsonObject textures = new JsonObject();
        textures.addProperty("layer0", modPath("item/" + itemName));
        json.add("textures", textures);

        IDataProvider.save(GSON, cache, json, itemModelPath(id(itemName)));
    }

    private void saveSimpleBlockstate(DirectoryCache cache, String blockName, String modelName) throws IOException {
        JsonObject json = new JsonObject();
        JsonObject variants = new JsonObject();
        JsonObject variant = new JsonObject();
        variant.addProperty("model", modPath("block/" + modelName));
        variants.add("", variant);
        json.add("variants", variants);
        IDataProvider.save(GSON, cache, json, blockstatePath(id(blockName)));
    }

    private void savePoweredBlockstate(DirectoryCache cache, String blockName) throws IOException {
        JsonObject json = new JsonObject();
        JsonObject variants = new JsonObject();

        JsonObject unpowered = new JsonObject();
        unpowered.addProperty("model", modPath("block/laboratory_fan_unpowered"));
        variants.add("powered=false", unpowered);

        JsonObject powered = new JsonObject();
        powered.addProperty("model", modPath("block/laboratory_fan_powered"));
        variants.add("powered=true", powered);

        json.add("variants", variants);
        IDataProvider.save(GSON, cache, json, blockstatePath(id(blockName)));
    }

    private void saveDoorBlockstate(DirectoryCache cache, String blockName) throws IOException {
        JsonObject json = new JsonObject();
        JsonObject variants = new JsonObject();

        for (String facing : Arrays.asList("east", "north", "south", "west")) {
            for (String half : Arrays.asList("lower", "upper")) {
                for (String hinge : Arrays.asList("left", "right")) {
                    for (boolean open : Arrays.asList(false, true)) {
                        JsonObject variant = new JsonObject();
                        variant.addProperty("model", modPath("block/" + blockName + "_" + doorModelSuffix(half, hinge, open)));
                        int rotation = doorYRotation(facing, hinge, open);
                        if (rotation >= 0) {
                            variant.addProperty("y", rotation);
                        }
                        variants.add("facing=" + facing + ",half=" + half + ",hinge=" + hinge + ",open=" + open, variant);
                    }
                }
            }
        }

        json.add("variants", variants);
        IDataProvider.save(GSON, cache, json, blockstatePath(id(blockName)));
    }

    private void saveTrapdoorBlockstate(DirectoryCache cache, String blockName) throws IOException {
        JsonObject json = new JsonObject();
        JsonObject variants = new JsonObject();

        for (String facing : Arrays.asList("east", "north", "south", "west")) {
            for (String half : Arrays.asList("bottom", "top")) {
                for (boolean open : Arrays.asList(false, true)) {
                    JsonObject variant = new JsonObject();
                    variant.addProperty("model", modPath("block/" + blockName + "_" + trapdoorModelSuffix(half, open)));
                    int rotation = trapdoorYRotation(facing, open);
                    if (rotation >= 0) {
                        variant.addProperty("y", rotation);
                    }
                    variants.add("facing=" + facing + ",half=" + half + ",open=" + open, variant);
                }
            }
        }

        json.add("variants", variants);
        IDataProvider.save(GSON, cache, json, blockstatePath(id(blockName)));
    }

    private void saveCubeInventoryModel(DirectoryCache cache, String modelName, String texturePath) throws IOException {
        JsonObject json = new JsonObject();
        json.addProperty("parent", "minecraft:block/cube_all");

        JsonObject textures = new JsonObject();
        textures.addProperty("all", modPath("block/" + texturePath));
        json.add("textures", textures);

        IDataProvider.save(GSON, cache, json, blockModelPath(id(modelName)));
    }

    private void saveCubeBlockModel(DirectoryCache cache, String modelName, String texturePath) throws IOException {
        saveCubeInventoryModel(cache, modelName, texturePath);
    }

    private void saveCubeColumnModel(DirectoryCache cache, String modelName, String sideTexturePath, String endTexturePath) throws IOException {
        JsonObject json = new JsonObject();
        json.addProperty("parent", "minecraft:block/cube_column");

        JsonObject textures = new JsonObject();
        textures.addProperty("side", modPath("block/" + sideTexturePath));
        textures.addProperty("end", modPath("block/" + endTexturePath));
        json.add("textures", textures);

        IDataProvider.save(GSON, cache, json, blockModelPath(id(modelName)));
    }

    private void saveGlowingInventoryModel(DirectoryCache cache, String modelName, String baseTexturePath) throws IOException {
        JsonObject json = new JsonObject();
        json.addProperty("parent", modPath("block/glowing_block_inventory_overlay"));

        JsonObject textures = new JsonObject();
        textures.addProperty("base", modPath("block/" + baseTexturePath));
        textures.addProperty("overlay", modPath("block/glowing_block_inventory_overlay"));
        json.add("textures", textures);

        IDataProvider.save(GSON, cache, json, blockModelPath(id(modelName)));
    }

    private void saveGlowingPillarInventoryModel(DirectoryCache cache, String modelName, String sideTexturePath, String endTexturePath) throws IOException {
        JsonObject json = new JsonObject();
        json.addProperty("parent", modPath("block/glowing_column_inventory_overlay"));

        JsonObject textures = new JsonObject();
        textures.addProperty("base_side", modPath("block/" + sideTexturePath));
        textures.addProperty("base_end", modPath("block/" + endTexturePath));
        textures.addProperty("overlay", modPath("block/glowing_block_inventory_overlay"));
        json.add("textures", textures);

        IDataProvider.save(GSON, cache, json, blockModelPath(id(modelName)));
    }

    private void saveCutoutCubeInventoryModel(DirectoryCache cache, String modelName, String texturePath) throws IOException {
        JsonObject json = new JsonObject();
        json.addProperty("parent", "minecraft:block/cube_all");
        json.addProperty("render_type", CUTOUT);

        JsonObject textures = new JsonObject();
        textures.addProperty("all", modPath("block/" + texturePath));
        json.add("textures", textures);

        IDataProvider.save(GSON, cache, json, blockModelPath(id(modelName)));
    }

    private void saveCutoutGlowingInventoryModel(DirectoryCache cache, String modelName, String texturePath) throws IOException {
        JsonObject json = new JsonObject();
        json.addProperty("parent", modPath("block/glowing_block_inventory_overlay"));
        json.addProperty("render_type", CUTOUT);

        JsonObject textures = new JsonObject();
        textures.addProperty("base", modPath("block/" + texturePath));
        textures.addProperty("overlay", modPath("block/glowing_block_inventory_overlay"));
        json.add("textures", textures);

        IDataProvider.save(GSON, cache, json, blockModelPath(id(modelName)));
    }

    private void saveDoorModel(DirectoryCache cache, String modelName, String parent, String textureBase) throws IOException {
        JsonObject json = new JsonObject();
        json.addProperty("parent", parent);
        json.addProperty("render_type", CUTOUT);

        JsonObject textures = new JsonObject();
        textures.addProperty("bottom", modPath("block/" + textureBase + "_bottom"));
        textures.addProperty("top", modPath("block/" + textureBase + "_top"));
        json.add("textures", textures);

        IDataProvider.save(GSON, cache, json, blockModelPath(id(modelName)));
    }

    private void saveTrapdoorModel(DirectoryCache cache, String modelName, String parent, String textureBase) throws IOException {
        JsonObject json = new JsonObject();
        json.addProperty("parent", parent);
        json.addProperty("render_type", CUTOUT);

        JsonObject textures = new JsonObject();
        textures.addProperty("texture", modPath("block/" + textureBase));
        json.add("textures", textures);

        IDataProvider.save(GSON, cache, json, blockModelPath(id(modelName)));
    }

    private void saveBlockItemDefinition(DirectoryCache cache, String itemName, String blockModelName) throws IOException {
        JsonObject json = new JsonObject();
        json.addProperty("parent", modPath("block/" + blockModelName));
        IDataProvider.save(GSON, cache, json, itemModelPath(id(itemName)));
    }

    private static String doorModelSuffix(String half, String hinge, boolean open) {
        String halfPart = "upper".equals(half) ? "top" : "bottom";
        boolean rightHandModel = "right".equals(hinge) != open;
        return halfPart + (rightHandModel ? "_hinge" : "");
    }

    private static int doorYRotation(String facing, String hinge, boolean open) {
        if ("east".equals(facing)) {
            return open ? ("left".equals(hinge) ? 90 : 270) : -1;
        }
        if ("north".equals(facing)) {
            return open ? ("left".equals(hinge) ? -1 : 180) : 270;
        }
        if ("south".equals(facing)) {
            return open ? ("left".equals(hinge) ? 180 : -1) : 90;
        }
        if ("west".equals(facing)) {
            return open ? ("left".equals(hinge) ? 270 : 90) : 180;
        }
        return -1;
    }

    private static String trapdoorModelSuffix(String half, boolean open) {
        if (open) {
            return "open";
        }
        return "top".equals(half) ? "top" : "bottom";
    }

    private static int trapdoorYRotation(String facing, boolean open) {
        if (!open) {
            return -1;
        }

        if ("east".equals(facing)) {
            return 90;
        }
        if ("south".equals(facing)) {
            return 180;
        }
        if ("west".equals(facing)) {
            return 270;
        }
        return -1;
    }

    private static boolean isGlass(ModDatagenEntries.GeneratedBlockPair pair) {
        return "laboratory_glass".equals(pair.texturePath());
    }

    private static ResourceLocation id(String path) {
        return new ResourceLocation(LaboratoryBlocks.MOD_ID, path);
    }

    private Path blockstatePath(ResourceLocation id) {
        return this.blockstatesPath.resolve(id.getPath() + ".json");
    }

    private Path blockModelPath(ResourceLocation id) {
        return this.blockModelPath.resolve(id.getPath() + ".json");
    }

    private Path itemModelPath(ResourceLocation id) {
        return this.itemModelPath.resolve(id.getPath() + ".json");
    }

    private static String modPath(String path) {
        return LaboratoryBlocks.MOD_ID + ":" + path;
    }
}
