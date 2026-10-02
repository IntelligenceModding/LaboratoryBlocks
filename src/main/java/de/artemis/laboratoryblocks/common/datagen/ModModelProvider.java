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

public class ModModelProvider implements DataProvider {
    private static final String CUTOUT = "minecraft:cutout";

    private final DataGenerator.PathProvider blockstatesPathProvider;
    private final DataGenerator.PathProvider blockModelPathProvider;
    private final DataGenerator.PathProvider itemModelPathProvider;

    public ModModelProvider(DataGenerator generator) {
        this.blockstatesPathProvider = generator.createPathProvider(DataGenerator.Target.RESOURCE_PACK, "blockstates");
        this.blockModelPathProvider = generator.createPathProvider(DataGenerator.Target.RESOURCE_PACK, "models/block");
        this.itemModelPathProvider = generator.createPathProvider(DataGenerator.Target.RESOURCE_PACK, "models/item");
    }

    @Override
    public void run(@NotNull CachedOutput output) throws IOException {
        saveFlatItem(output, "iron_screw");
        saveFlatItem(output, "glowstone_particles");
        saveHandheldItem(output, "configuration_tool");

        for (ModDatagenEntries.GeneratedBlockPair pair : ModDatagenEntries.ALL_PAIRS) {
            saveSimpleBlockstate(output, pair.baseModelName(), pair.baseModelName());
            saveSimpleBlockstate(output, pair.glowingModelName(), pair.glowingModelName());
            saveBlockItemDefinition(output, pair.baseModelName(), pair.baseModelName() + "_inventory");
            saveBlockItemDefinition(output, pair.glowingModelName(), pair.glowingModelName() + "_inventory");

            if (isGlass(pair)) {
                saveCutoutCubeInventoryModel(output, pair.baseModelName() + "_inventory", pair.texturePath());
                saveCutoutGlowingInventoryModel(output, pair.glowingModelName() + "_inventory", pair.texturePath());
            } else {
                saveCubeInventoryModel(output, pair.baseModelName() + "_inventory", pair.texturePath());
                saveGlowingInventoryModel(output, pair.glowingModelName() + "_inventory", pair.texturePath());
            }
        }

        for (ModDatagenEntries.GeneratedPillarPair pair : ModDatagenEntries.PILLAR_PAIRS) {
            saveSimpleBlockstate(output, pair.baseModelName(), pair.baseModelName());
            saveSimpleBlockstate(output, pair.glowingModelName(), pair.glowingModelName());
            saveCubeColumnModel(output, pair.baseModelName(), pair.sideTexturePath(), pair.endTexturePath());
            saveCubeColumnModel(output, pair.glowingModelName(), pair.sideTexturePath(), pair.endTexturePath());
            saveCubeColumnModel(output, pair.baseModelName() + "_inventory", pair.sideTexturePath(), pair.endTexturePath());
            saveGlowingPillarInventoryModel(output, pair.glowingModelName() + "_inventory", pair.sideTexturePath(), pair.endTexturePath());
            saveBlockItemDefinition(output, pair.baseModelName(), pair.baseModelName() + "_inventory");
            saveBlockItemDefinition(output, pair.glowingModelName(), pair.glowingModelName() + "_inventory");
        }

        for (var door : ModDatagenEntries.DOORS) {
            String name = door.getId().getPath();
            saveFlatItem(output, name);
            saveDoorBlockstate(output, name);
            saveDoorModel(output, name + "_bottom_left", "minecraft:block/door_bottom_left", name);
            saveDoorModel(output, name + "_bottom_left_open", "minecraft:block/door_bottom_left_open", name);
            saveDoorModel(output, name + "_bottom_right", "minecraft:block/door_bottom_right", name);
            saveDoorModel(output, name + "_bottom_right_open", "minecraft:block/door_bottom_right_open", name);
            saveDoorModel(output, name + "_top_left", "minecraft:block/door_top_left", name);
            saveDoorModel(output, name + "_top_left_open", "minecraft:block/door_top_left_open", name);
            saveDoorModel(output, name + "_top_right", "minecraft:block/door_top_right", name);
            saveDoorModel(output, name + "_top_right_open", "minecraft:block/door_top_right_open", name);
        }

        for (var trapdoor : ModDatagenEntries.TRAPDOORS) {
            String name = trapdoor.getId().getPath();
            saveBlockItemDefinition(output, name, name + "_bottom");
            saveTrapdoorBlockstate(output, name);
            saveTrapdoorModel(output, name + "_bottom", "minecraft:block/template_orientable_trapdoor_bottom", name);
            saveTrapdoorModel(output, name + "_open", "minecraft:block/template_orientable_trapdoor_open", name);
            saveTrapdoorModel(output, name + "_top", "minecraft:block/template_orientable_trapdoor_top", name);
        }

        saveCubeBlockModel(output, "laboratory_fan", "laboratory_fan");
        saveCubeBlockModel(output, "laboratory_fan_powered", "laboratory_fan");
        saveCubeBlockModel(output, "laboratory_fan_unpowered", "laboratory_fan_unpowered");
        saveCubeBlockModel(output, "laboratory_fan_inventory", "laboratory_fan");
        saveGlowingInventoryModel(output, "glowing_laboratory_fan_inventory", "laboratory_fan");
        saveCubeBlockModel(output, "laboratory_fan_redstone_controlled_inventory", "laboratory_fan_redstone_controlled");
        saveGlowingInventoryModel(output, "glowing_laboratory_fan_redstone_controlled_inventory", "laboratory_fan_redstone_controlled");
        saveSimpleBlockstate(output, "laboratory_fan", "laboratory_fan");
        saveSimpleBlockstate(output, "glowing_laboratory_fan", "laboratory_fan");
        savePoweredBlockstate(output, "laboratory_fan_redstone_controlled");
        savePoweredBlockstate(output, "glowing_laboratory_fan_redstone_controlled");
        saveBlockItemDefinition(output, "laboratory_fan", "laboratory_fan_inventory");
        saveBlockItemDefinition(output, "glowing_laboratory_fan", "glowing_laboratory_fan_inventory");
        saveBlockItemDefinition(output, "laboratory_fan_redstone_controlled", "laboratory_fan_redstone_controlled_inventory");
        saveBlockItemDefinition(output, "glowing_laboratory_fan_redstone_controlled", "glowing_laboratory_fan_redstone_controlled_inventory");
    }

    @Override
    public @NotNull String getName() {
        return "Model Definitions: " + LaboratoryBlocks.MOD_ID;
    }

    private void saveFlatItem(CachedOutput output, String itemName) throws IOException {
        saveFlatItemModel(output, itemName, "minecraft:item/generated");
    }

    @SuppressWarnings("all")
    private void saveHandheldItem(CachedOutput output, String itemName) throws IOException {
        saveFlatItemModel(output, itemName, "minecraft:item/handheld");
    }

    private void saveFlatItemModel(CachedOutput output, String itemName, String parent) throws IOException {
        JsonObject json = new JsonObject();
        json.addProperty("parent", parent);

        JsonObject textures = new JsonObject();
        textures.addProperty("layer0", modPath("item/" + itemName));
        json.add("textures", textures);

        DataProvider.saveStable(output, json, itemModelPathProvider.json(id(itemName)));
    }

    private void saveSimpleBlockstate(CachedOutput output, String blockName, String modelName) throws IOException {
        JsonObject json = new JsonObject();
        JsonObject variants = new JsonObject();
        JsonObject variant = new JsonObject();
        variant.addProperty("model", modPath("block/" + modelName));
        variants.add("", variant);
        json.add("variants", variants);
        DataProvider.saveStable(output, json, blockstatesPathProvider.json(id(blockName)));
    }

    private void savePoweredBlockstate(CachedOutput output, String blockName) throws IOException {
        JsonObject json = new JsonObject();
        JsonObject variants = new JsonObject();

        JsonObject unpowered = new JsonObject();
        unpowered.addProperty("model", modPath("block/laboratory_fan_unpowered"));
        variants.add("powered=false", unpowered);

        JsonObject powered = new JsonObject();
        powered.addProperty("model", modPath("block/laboratory_fan_powered"));
        variants.add("powered=true", powered);

        json.add("variants", variants);
        DataProvider.saveStable(output, json, blockstatesPathProvider.json(id(blockName)));
    }

    private void saveDoorBlockstate(CachedOutput output, String blockName) throws IOException {
        JsonObject json = new JsonObject();
        JsonObject variants = new JsonObject();

        for (String facing : List.of("east", "north", "south", "west")) {
            for (String half : List.of("lower", "upper")) {
                for (String hinge : List.of("left", "right")) {
                    for (boolean open : List.of(false, true)) {
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
        DataProvider.saveStable(output, json, blockstatesPathProvider.json(id(blockName)));
    }

    private void saveTrapdoorBlockstate(CachedOutput output, String blockName) throws IOException {
        JsonObject json = new JsonObject();
        JsonObject variants = new JsonObject();

        for (String facing : List.of("east", "north", "south", "west")) {
            for (String half : List.of("bottom", "top")) {
                for (boolean open : List.of(false, true)) {
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
        DataProvider.saveStable(output, json, blockstatesPathProvider.json(id(blockName)));
    }

    private void saveCubeInventoryModel(CachedOutput output, String modelName, String texturePath) throws IOException {
        JsonObject json = new JsonObject();
        json.addProperty("parent", "minecraft:block/cube_all");

        JsonObject textures = new JsonObject();
        textures.addProperty("all", modPath("block/" + texturePath));
        json.add("textures", textures);

        DataProvider.saveStable(output, json, blockModelPathProvider.json(id(modelName)));
    }

    private void saveCubeBlockModel(CachedOutput output, String modelName, String texturePath) throws IOException {
        saveCubeInventoryModel(output, modelName, texturePath);
    }

    private void saveCubeColumnModel(CachedOutput output, String modelName, String sideTexturePath, String endTexturePath) throws IOException {
        JsonObject json = new JsonObject();
        json.addProperty("parent", "minecraft:block/cube_column");

        JsonObject textures = new JsonObject();
        textures.addProperty("side", modPath("block/" + sideTexturePath));
        textures.addProperty("end", modPath("block/" + endTexturePath));
        json.add("textures", textures);

        DataProvider.saveStable(output, json, blockModelPathProvider.json(id(modelName)));
    }

    private void saveGlowingInventoryModel(CachedOutput output, String modelName, String baseTexturePath) throws IOException {
        JsonObject json = new JsonObject();
        json.addProperty("parent", modPath("block/glowing_block_inventory_overlay"));

        JsonObject textures = new JsonObject();
        textures.addProperty("base", modPath("block/" + baseTexturePath));
        textures.addProperty("overlay", modPath("block/glowing_block_inventory_overlay"));
        json.add("textures", textures);

        DataProvider.saveStable(output, json, blockModelPathProvider.json(id(modelName)));
    }

    private void saveGlowingPillarInventoryModel(CachedOutput output, String modelName, String sideTexturePath, String endTexturePath) throws IOException {
        JsonObject json = new JsonObject();
        json.addProperty("parent", modPath("block/glowing_column_inventory_overlay"));

        JsonObject textures = new JsonObject();
        textures.addProperty("base_side", modPath("block/" + sideTexturePath));
        textures.addProperty("base_end", modPath("block/" + endTexturePath));
        textures.addProperty("overlay", modPath("block/glowing_block_inventory_overlay"));
        json.add("textures", textures);

        DataProvider.saveStable(output, json, blockModelPathProvider.json(id(modelName)));
    }

    private void saveCutoutCubeInventoryModel(CachedOutput output, String modelName, String texturePath) throws IOException {
        JsonObject json = new JsonObject();
        json.addProperty("parent", "minecraft:block/cube_all");
        json.addProperty("render_type", CUTOUT);

        JsonObject textures = new JsonObject();
        textures.addProperty("all", modPath("block/" + texturePath));
        json.add("textures", textures);

        DataProvider.saveStable(output, json, blockModelPathProvider.json(id(modelName)));
    }

    private void saveCutoutGlowingInventoryModel(CachedOutput output, String modelName, String texturePath) throws IOException {
        JsonObject json = new JsonObject();
        json.addProperty("parent", modPath("block/glowing_block_inventory_overlay"));
        json.addProperty("render_type", CUTOUT);

        JsonObject textures = new JsonObject();
        textures.addProperty("base", modPath("block/" + texturePath));
        textures.addProperty("overlay", modPath("block/glowing_block_inventory_overlay"));
        json.add("textures", textures);

        DataProvider.saveStable(output, json, blockModelPathProvider.json(id(modelName)));
    }

    private void saveDoorModel(CachedOutput output, String modelName, String parent, String textureBase) throws IOException {
        JsonObject json = new JsonObject();
        json.addProperty("parent", parent);
        json.addProperty("render_type", CUTOUT);

        JsonObject textures = new JsonObject();
        textures.addProperty("bottom", modPath("block/" + textureBase + "_bottom"));
        textures.addProperty("top", modPath("block/" + textureBase + "_top"));
        json.add("textures", textures);

        DataProvider.saveStable(output, json, blockModelPathProvider.json(id(modelName)));
    }

    private void saveTrapdoorModel(CachedOutput output, String modelName, String parent, String textureBase) throws IOException {
        JsonObject json = new JsonObject();
        json.addProperty("parent", parent);
        json.addProperty("render_type", CUTOUT);

        JsonObject textures = new JsonObject();
        textures.addProperty("texture", modPath("block/" + textureBase));
        json.add("textures", textures);

        DataProvider.saveStable(output, json, blockModelPathProvider.json(id(modelName)));
    }

    private void saveBlockItemDefinition(CachedOutput output, String itemName, String blockModelName) throws IOException {
        JsonObject json = new JsonObject();
        json.addProperty("parent", modPath("block/" + blockModelName));
        DataProvider.saveStable(output, json, itemModelPathProvider.json(id(itemName)));
    }

    private static String doorModelSuffix(String half, String hinge, boolean open) {
        String halfPart = "upper".equals(half) ? "top" : "bottom";
        return halfPart + "_" + hinge + (open ? "_open" : "");
    }

    private static int doorYRotation(String facing, String hinge, boolean open) {
        return switch (facing) {
            case "east" -> open ? ("left".equals(hinge) ? 90 : 270) : -1;
            case "north" -> open ? ("left".equals(hinge) ? -1 : 180) : 270;
            case "south" -> open ? ("left".equals(hinge) ? 180 : -1) : 90;
            case "west" -> open ? ("left".equals(hinge) ? 270 : 90) : 180;
            default -> -1;
        };
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

        return switch (facing) {
            case "east" -> 90;
            case "south" -> 180;
            case "west" -> 270;
            default -> -1;
        };
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
