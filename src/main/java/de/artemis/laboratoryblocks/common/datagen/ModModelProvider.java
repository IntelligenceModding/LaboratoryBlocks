package de.artemis.laboratoryblocks.common.datagen;

import com.google.gson.JsonObject;
import de.artemis.laboratoryblocks.LaboratoryBlocks;
import de.artemis.laboratoryblocks.common.registry.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.core.Holder;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class ModModelProvider extends ModelProvider {
    private static final String CUTOUT = "minecraft:cutout";

    private final PackOutput.PathProvider blockstatesPathProvider;
    private final PackOutput.PathProvider blockModelPathProvider;
    private final PackOutput.PathProvider itemPathProvider;

    public ModModelProvider(PackOutput output) {
        super(output, LaboratoryBlocks.MOD_ID);
        this.blockstatesPathProvider = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "blockstates");
        this.blockModelPathProvider = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "models/block");
        this.itemPathProvider = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "items");
    }

    @Override
    protected void registerModels(@NonNull BlockModelGenerators blockModels, @NonNull ItemModelGenerators itemModels) {
        registerFlatItemModels(itemModels);
        ModDatagenEntries.ALL_PAIRS.forEach(pair -> registerCubePair(blockModels, pair));
        ModDatagenEntries.PILLAR_PAIRS.forEach(pair -> registerPillarPair(blockModels, pair));
        ModDatagenEntries.DOORS.forEach(door -> blockModels.createDoor(door.get()));
        ModDatagenEntries.TRAPDOORS.forEach(trapdoor -> blockModels.createTrapdoor(trapdoor.get()));
    }

    @Override
    public @NonNull CompletableFuture<?> run(@NonNull CachedOutput output) {
        return super.run(output).thenCompose(ignored -> writeCustomModels(output));
    }

    private void registerFlatItemModels(ItemModelGenerators itemModels) {
        itemModels.generateFlatItem(ModItems.IRON_SCREW.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.GLOWSTONE_PARTICLES.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.CONFIGURATION_TOOL.get(), ModelTemplates.FLAT_ITEM);
    }

    private void registerCubePair(BlockModelGenerators blockModels, ModDatagenEntries.GeneratedBlockPair pair) {
        Identifier basePlacedModel = modModel(pair.baseModelName());
        Identifier glowingPlacedModel = modModel(pair.glowingModelName());
        Identifier baseInventoryModel = ModelTemplates.CUBE_ALL.create(
                modModel(pair.baseModelName() + "_inventory"),
                TextureMapping.cube(textureMaterial(pair.texturePath())),
                blockModels.modelOutput
        );
        Identifier glowingInventoryModel = modModel(pair.glowingModelName() + "_inventory");

        registerBlockState(blockModels, pair.base().get(), basePlacedModel);
        registerBlockState(blockModels, pair.glowing().get(), glowingPlacedModel);
        registerItemModel(blockModels, pair.base().get(), baseInventoryModel);
        registerItemModel(blockModels, pair.glowing().get(), glowingInventoryModel);
    }

    private void registerPillarPair(BlockModelGenerators blockModels, ModDatagenEntries.GeneratedPillarPair pair) {
        Identifier basePlacedModel = ModelTemplates.CUBE_COLUMN.create(
                modModel(pair.baseModelName()),
                TextureMapping.column(textureMaterial(pair.sideTexturePath()), textureMaterial(pair.endTexturePath())),
                blockModels.modelOutput
        );
        Identifier glowingPlacedModel = ModelTemplates.CUBE_COLUMN.create(
                modModel(pair.glowingModelName()),
                TextureMapping.column(textureMaterial(pair.sideTexturePath()), textureMaterial(pair.endTexturePath())),
                blockModels.modelOutput
        );
        Identifier baseInventoryModel = ModelTemplates.CUBE_COLUMN.create(
                modModel(pair.baseModelName() + "_inventory"),
                TextureMapping.column(textureMaterial(pair.sideTexturePath()), textureMaterial(pair.endTexturePath())),
                blockModels.modelOutput
        );
        Identifier glowingInventoryModel = modModel(pair.glowingModelName() + "_inventory");

        registerBlockState(blockModels, pair.base().get(), basePlacedModel);
        registerBlockState(blockModels, pair.glowing().get(), glowingPlacedModel);
        registerItemModel(blockModels, pair.base().get(), baseInventoryModel);
        registerItemModel(blockModels, pair.glowing().get(), glowingInventoryModel);
    }

    private void registerBlockState(BlockModelGenerators blockModels, Block block, Identifier model) {
        blockModels.blockStateOutput.accept(
                BlockModelGenerators.createSimpleBlock(block, BlockModelGenerators.plainVariant(model))
        );
    }

    private void registerItemModel(BlockModelGenerators blockModels, Block block, Identifier model) {
        blockModels.registerSimpleItemModel(block, model);
    }

    private static Identifier texture(String path) {
        return Identifier.fromNamespaceAndPath(LaboratoryBlocks.MOD_ID, "block/" + path);
    }

    private static Material textureMaterial(String path) {
        return new Material(texture(path));
    }

    private static Identifier modModel(String path) {
        return Identifier.fromNamespaceAndPath(LaboratoryBlocks.MOD_ID, "block/" + path);
    }

    private CompletableFuture<?> writeCustomModels(CachedOutput output) {
        List<CompletableFuture<?>> futures = new ArrayList<>();

        ModDatagenEntries.ALL_PAIRS.stream()
                .filter(pair -> !isGlass(pair))
                .forEach(pair -> futures.add(saveGlowingInventoryModel(output, pair.glowingModelName() + "_inventory", pair.texturePath())));
        ModDatagenEntries.PILLAR_PAIRS.forEach(pair ->
                futures.add(saveGlowingPillarInventoryModel(output, pair.glowingModelName() + "_inventory", pair.sideTexturePath(), pair.endTexturePath()))
        );

        ModDatagenEntries.DOORS.forEach(door -> {
            String name = door.getId().getPath();
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
        futures.add(saveItemModel(output, "laboratory_fan", "laboratory_fan_inventory"));
        futures.add(saveItemModel(output, "glowing_laboratory_fan", "glowing_laboratory_fan_inventory"));
        futures.add(saveItemModel(output, "laboratory_fan_redstone_controlled", "laboratory_fan_redstone_controlled_inventory"));
        futures.add(saveItemModel(output, "glowing_laboratory_fan_redstone_controlled", "glowing_laboratory_fan_redstone_controlled_inventory"));

        futures.add(saveCutoutCubeInventoryModel(output, "laboratory_glass_inventory", "laboratory_glass"));
        futures.add(saveCutoutGlowingInventoryModel(output, "glowing_laboratory_glass_inventory", "laboratory_glass"));

        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
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

    private CompletableFuture<?> saveCubeBlockModel(CachedOutput output, String modelName, String textureName) {
        JsonObject json = new JsonObject();
        json.addProperty("parent", "minecraft:block/cube_all");

        JsonObject textures = new JsonObject();
        textures.addProperty("all", modPath("block/" + textureName));
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

    private CompletableFuture<?> saveItemModel(CachedOutput output, String itemName, String blockModelName) {
        JsonObject root = new JsonObject();
        JsonObject model = new JsonObject();
        model.addProperty("type", "minecraft:model");
        model.addProperty("model", modPath("block/" + blockModelName));
        root.add("model", model);
        return DataProvider.saveStable(output, root, itemPathProvider.json(id(itemName)));
    }

    private static boolean isGlass(ModDatagenEntries.GeneratedBlockPair pair) {
        return "laboratory_glass".equals(pair.texturePath());
    }

    private static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(LaboratoryBlocks.MOD_ID, path);
    }

    private static String modPath(String path) {
        return LaboratoryBlocks.MOD_ID + ":" + path;
    }

    @SuppressWarnings("deprecation")
    @Override
    protected @NonNull Stream<? extends Holder<Block>> getKnownBlocks() {
        Stream<Holder<Block>> pairBlocks = ModDatagenEntries.ALL_PAIRS.stream()
                .flatMap(pair -> Stream.of(pair.base().get().builtInRegistryHolder(), pair.glowing().get().builtInRegistryHolder()));
        Stream<Holder<Block>> pillarBlocks = ModDatagenEntries.PILLAR_PAIRS.stream()
                .flatMap(pair -> Stream.of(pair.base().get().builtInRegistryHolder(), pair.glowing().get().builtInRegistryHolder()));
        Stream<Holder<Block>> doors = ModDatagenEntries.DOORS.stream()
                .map(door -> door.get().builtInRegistryHolder());
        Stream<Holder<Block>> trapdoors = ModDatagenEntries.TRAPDOORS.stream()
                .map(trapdoor -> trapdoor.get().builtInRegistryHolder());
        return Stream.of(pairBlocks, pillarBlocks, doors, trapdoors).flatMap(stream -> stream);
    }

    @SuppressWarnings("deprecation")
    @Override
    protected @NonNull Stream<? extends Holder<Item>> getKnownItems() {
        return Stream.of(
                ModItems.IRON_SCREW.get().builtInRegistryHolder(),
                ModItems.GLOWSTONE_PARTICLES.get().builtInRegistryHolder(),
                ModItems.CONFIGURATION_TOOL.get().builtInRegistryHolder()
        );
    }
}
