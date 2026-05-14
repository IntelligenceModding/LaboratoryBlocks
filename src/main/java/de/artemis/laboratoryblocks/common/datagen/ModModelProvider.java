package de.artemis.laboratoryblocks.common.datagen;

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

public class ModModelProvider implements DataProvider {
    private static final String CUTOUT = "minecraft:cutout";

    private final PackOutput.PathProvider blockstatesPathProvider;
    private final PackOutput.PathProvider blockModelPathProvider;
    private final PackOutput.PathProvider itemModelPathProvider;

    public ModModelProvider(PackOutput output) {
        this.blockstatesPathProvider = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "blockstates");
        this.blockModelPathProvider = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "models/block");
        this.itemModelPathProvider = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "models/item");
    }

    @Override
    public @NotNull CompletableFuture<?> run(@NotNull CachedOutput output) {
        List<CompletableFuture<?>> futures = new ArrayList<>();

        futures.addAll(saveFlatItem(output, "iron_screw"));
        futures.addAll(saveFlatItem(output, "glowstone_particles"));
        futures.addAll(saveHandheldItem(output, "configuration_tool"));

        ModDatagenEntries.ALL_PAIRS.forEach(pair -> {
            futures.add(saveSimpleBlockstate(output, pair.baseModelName(), pair.baseModelName()));
            futures.add(saveSimpleBlockstate(output, pair.glowingModelName(), pair.glowingModelName()));
            futures.add(saveBlockItemDefinition(output, pair.baseModelName(), pair.baseModelName() + "_inventory"));
            futures.add(saveBlockItemDefinition(output, pair.glowingModelName(), pair.glowingModelName() + "_inventory"));

            if (isGlass(pair)) {
                futures.add(saveCutoutCubeInventoryModel(output, pair.baseModelName() + "_inventory", pair.texturePath()));
                futures.add(saveCutoutGlowingInventoryModel(output, pair.glowingModelName() + "_inventory", pair.texturePath()));
            } else {
                futures.add(saveCubeInventoryModel(output, pair.baseModelName() + "_inventory", pair.texturePath()));
                futures.add(saveGlowingInventoryModel(output, pair.glowingModelName() + "_inventory", pair.texturePath()));
            }
        });

        ModDatagenEntries.PILLAR_PAIRS.forEach(pair -> {
            futures.add(saveSimpleBlockstate(output, pair.baseModelName(), pair.baseModelName()));
            futures.add(saveSimpleBlockstate(output, pair.glowingModelName(), pair.glowingModelName()));
            futures.add(saveCubeColumnModel(output, pair.baseModelName(), pair.sideTexturePath(), pair.endTexturePath()));
            futures.add(saveCubeColumnModel(output, pair.glowingModelName(), pair.sideTexturePath(), pair.endTexturePath()));
            futures.add(saveCubeColumnModel(output, pair.baseModelName() + "_inventory", pair.sideTexturePath(), pair.endTexturePath()));
            futures.add(saveGlowingPillarInventoryModel(output, pair.glowingModelName() + "_inventory", pair.sideTexturePath(), pair.endTexturePath()));
            futures.add(saveBlockItemDefinition(output, pair.baseModelName(), pair.baseModelName() + "_inventory"));
            futures.add(saveBlockItemDefinition(output, pair.glowingModelName(), pair.glowingModelName() + "_inventory"));
        });

        ModDatagenEntries.DOORS.forEach(door -> {
            String name = door.getId().getPath();
            futures.addAll(saveFlatItem(output, name));
            futures.add(saveDoorBlockstate(output, name));
            futures.add(saveDoorModel(output, name + "_bottom_left", "minecraft:block/door_bottom_left", name));
            futures.add(saveDoorModel(output, name + "_bottom_left_open", "minecraft:block/door_bottom_left_open", name));
            futures.add(saveDoorModel(output, name + "_bottom_right", "minecraft:block/door_bottom_right", name));
            futures.add(saveDoorModel(output, name + "_bottom_right_open", "minecraft:block/door_bottom_right_open", name));
            futures.add(saveDoorModel(output, name + "_top_left", "minecraft:block/door_top_left", name));
            futures.add(saveDoorModel(output, name + "_top_left_open", "minecraft:block/door_top_left_open", name));
            futures.add(saveDoorModel(output, name + "_top_right", "minecraft:block/door_top_right", name));
            futures.add(saveDoorModel(output, name + "_top_right_open", "minecraft:block/door_top_right_open", name));
        });

        ModDatagenEntries.TRAPDOORS.forEach(trapdoor -> {
            String name = trapdoor.getId().getPath();
            futures.add(saveBlockItemDefinition(output, name, name + "_bottom"));
            futures.add(saveTrapdoorBlockstate(output, name));
            futures.add(saveTrapdoorModel(output, name + "_bottom", "minecraft:block/template_orientable_trapdoor_bottom", name));
            futures.add(saveTrapdoorModel(output, name + "_open", "minecraft:block/template_orientable_trapdoor_open", name));
            futures.add(saveTrapdoorModel(output, name + "_top", "minecraft:block/template_orientable_trapdoor_top", name));
        });

        futures.add(saveCubeBlockModel(output, "laboratory_fan", "laboratory_fan"));
        futures.add(saveCubeBlockModel(output, "laboratory_fan_powered", "laboratory_fan"));
        futures.add(saveCubeBlockModel(output, "laboratory_fan_unpowered", "laboratory_fan_unpowered"));
        futures.add(saveCubeBlockModel(output, "laboratory_fan_inventory", "laboratory_fan"));
        futures.add(saveGlowingInventoryModel(output, "glowing_laboratory_fan_inventory", "laboratory_fan"));
        futures.add(saveCubeBlockModel(output, "laboratory_fan_redstone_controlled_inventory", "laboratory_fan_redstone_controlled"));
        futures.add(saveGlowingInventoryModel(output, "glowing_laboratory_fan_redstone_controlled_inventory", "laboratory_fan_redstone_controlled"));
        futures.add(saveSimpleBlockstate(output, "laboratory_fan", "laboratory_fan"));
        futures.add(saveSimpleBlockstate(output, "glowing_laboratory_fan", "laboratory_fan"));
        futures.add(savePoweredBlockstate(output, "laboratory_fan_redstone_controlled"));
        futures.add(savePoweredBlockstate(output, "glowing_laboratory_fan_redstone_controlled"));
        futures.add(saveBlockItemDefinition(output, "laboratory_fan", "laboratory_fan_inventory"));
        futures.add(saveBlockItemDefinition(output, "glowing_laboratory_fan", "glowing_laboratory_fan_inventory"));
        futures.add(saveBlockItemDefinition(output, "laboratory_fan_redstone_controlled", "laboratory_fan_redstone_controlled_inventory"));
        futures.add(saveBlockItemDefinition(output, "glowing_laboratory_fan_redstone_controlled", "glowing_laboratory_fan_redstone_controlled_inventory"));

        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }

    @Override
    public @NotNull String getName() {
        return "Model Definitions: " + LaboratoryBlocks.MOD_ID;
    }

    private List<CompletableFuture<?>> saveFlatItem(CachedOutput output, String itemName) {
        return List.of(saveFlatItemModel(output, itemName, "minecraft:item/generated"));
    }

    @SuppressWarnings("all")
    private List<CompletableFuture<?>> saveHandheldItem(CachedOutput output, String itemName) {
        return List.of(saveFlatItemModel(output, itemName, "minecraft:item/handheld"));
    }

    private CompletableFuture<?> saveFlatItemModel(CachedOutput output, String itemName, String parent) {
        JsonObject json = new JsonObject();
        json.addProperty("parent", parent);

        JsonObject textures = new JsonObject();
        textures.addProperty("layer0", modPath("item/" + itemName));
        json.add("textures", textures);

        return DataProvider.saveStable(output, json, itemModelPathProvider.json(id(itemName)));
    }

    private CompletableFuture<?> saveSimpleBlockstate(CachedOutput output, String blockName, String modelName) {
        JsonObject json = new JsonObject();
        JsonObject variants = new JsonObject();
        JsonObject variant = new JsonObject();
        variant.addProperty("model", modPath("block/" + modelName));
        variants.add("", variant);
        json.add("variants", variants);
        return DataProvider.saveStable(output, json, blockstatesPathProvider.json(id(blockName)));
    }

    private CompletableFuture<?> savePoweredBlockstate(CachedOutput output, String blockName) {
        JsonObject json = new JsonObject();
        JsonObject variants = new JsonObject();

        JsonObject unpowered = new JsonObject();
        unpowered.addProperty("model", modPath("block/laboratory_fan_unpowered"));
        variants.add("powered=false", unpowered);

        JsonObject powered = new JsonObject();
        powered.addProperty("model", modPath("block/laboratory_fan_powered"));
        variants.add("powered=true", powered);

        json.add("variants", variants);
        return DataProvider.saveStable(output, json, blockstatesPathProvider.json(id(blockName)));
    }

    private CompletableFuture<?> saveDoorBlockstate(CachedOutput output, String blockName) {
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
        return DataProvider.saveStable(output, json, blockstatesPathProvider.json(id(blockName)));
    }

    private CompletableFuture<?> saveTrapdoorBlockstate(CachedOutput output, String blockName) {
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
        return DataProvider.saveStable(output, json, blockstatesPathProvider.json(id(blockName)));
    }

    private CompletableFuture<?> saveCubeInventoryModel(CachedOutput output, String modelName, String texturePath) {
        JsonObject json = new JsonObject();
        json.addProperty("parent", "minecraft:block/cube_all");

        JsonObject textures = new JsonObject();
        textures.addProperty("all", modPath("block/" + texturePath));
        json.add("textures", textures);

        return DataProvider.saveStable(output, json, blockModelPathProvider.json(id(modelName)));
    }

    private CompletableFuture<?> saveCubeBlockModel(CachedOutput output, String modelName, String texturePath) {
        return saveCubeInventoryModel(output, modelName, texturePath);
    }

    private CompletableFuture<?> saveCubeColumnModel(CachedOutput output, String modelName, String sideTexturePath, String endTexturePath) {
        JsonObject json = new JsonObject();
        json.addProperty("parent", "minecraft:block/cube_column");

        JsonObject textures = new JsonObject();
        textures.addProperty("side", modPath("block/" + sideTexturePath));
        textures.addProperty("end", modPath("block/" + endTexturePath));
        json.add("textures", textures);

        return DataProvider.saveStable(output, json, blockModelPathProvider.json(id(modelName)));
    }

    private CompletableFuture<?> saveGlowingInventoryModel(CachedOutput output, String modelName, String baseTexturePath) {
        JsonObject json = new JsonObject();
        json.addProperty("parent", modPath("block/glowing_block_inventory_overlay"));

        JsonObject textures = new JsonObject();
        textures.addProperty("base", modPath("block/" + baseTexturePath));
        textures.addProperty("overlay", modPath("block/glowing_block_inventory_overlay"));
        json.add("textures", textures);

        return DataProvider.saveStable(output, json, blockModelPathProvider.json(id(modelName)));
    }

    private CompletableFuture<?> saveGlowingPillarInventoryModel(CachedOutput output, String modelName, String sideTexturePath, String endTexturePath) {
        JsonObject json = new JsonObject();
        json.addProperty("parent", modPath("block/glowing_column_inventory_overlay"));

        JsonObject textures = new JsonObject();
        textures.addProperty("base_side", modPath("block/" + sideTexturePath));
        textures.addProperty("base_end", modPath("block/" + endTexturePath));
        textures.addProperty("overlay", modPath("block/glowing_block_inventory_overlay"));
        json.add("textures", textures);

        return DataProvider.saveStable(output, json, blockModelPathProvider.json(id(modelName)));
    }

    private CompletableFuture<?> saveCutoutCubeInventoryModel(CachedOutput output, String modelName, String texturePath) {
        JsonObject json = new JsonObject();
        json.addProperty("parent", "minecraft:block/cube_all");
        json.addProperty("render_type", CUTOUT);

        JsonObject textures = new JsonObject();
        textures.addProperty("all", modPath("block/" + texturePath));
        json.add("textures", textures);

        return DataProvider.saveStable(output, json, blockModelPathProvider.json(id(modelName)));
    }

    private CompletableFuture<?> saveCutoutGlowingInventoryModel(CachedOutput output, String modelName, String texturePath) {
        JsonObject json = new JsonObject();
        json.addProperty("parent", modPath("block/glowing_block_inventory_overlay"));
        json.addProperty("render_type", CUTOUT);

        JsonObject textures = new JsonObject();
        textures.addProperty("base", modPath("block/" + texturePath));
        textures.addProperty("overlay", modPath("block/glowing_block_inventory_overlay"));
        json.add("textures", textures);

        return DataProvider.saveStable(output, json, blockModelPathProvider.json(id(modelName)));
    }

    private CompletableFuture<?> saveDoorModel(CachedOutput output, String modelName, String parent, String textureBase) {
        JsonObject json = new JsonObject();
        json.addProperty("parent", parent);
        json.addProperty("render_type", CUTOUT);

        JsonObject textures = new JsonObject();
        textures.addProperty("bottom", modPath("block/" + textureBase + "_bottom"));
        textures.addProperty("top", modPath("block/" + textureBase + "_top"));
        json.add("textures", textures);

        return DataProvider.saveStable(output, json, blockModelPathProvider.json(id(modelName)));
    }

    private CompletableFuture<?> saveTrapdoorModel(CachedOutput output, String modelName, String parent, String textureBase) {
        JsonObject json = new JsonObject();
        json.addProperty("parent", parent);
        json.addProperty("render_type", CUTOUT);

        JsonObject textures = new JsonObject();
        textures.addProperty("texture", modPath("block/" + textureBase));
        json.add("textures", textures);

        return DataProvider.saveStable(output, json, blockModelPathProvider.json(id(modelName)));
    }

    private CompletableFuture<?> saveBlockItemDefinition(CachedOutput output, String itemName, String blockModelName) {
        JsonObject json = new JsonObject();
        json.addProperty("parent", modPath("block/" + blockModelName));
        return DataProvider.saveStable(output, json, itemModelPathProvider.json(id(itemName)));
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
        return ResourceLocation.fromNamespaceAndPath(LaboratoryBlocks.MOD_ID, path);
    }

    private static String modPath(String path) {
        return LaboratoryBlocks.MOD_ID + ":" + path;
    }
}