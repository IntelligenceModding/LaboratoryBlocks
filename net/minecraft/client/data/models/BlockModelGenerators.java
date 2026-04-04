package net.minecraft.client.data.models;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.mojang.datafixers.util.Pair;
import com.mojang.math.Quadrant;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import net.minecraft.client.color.item.GrassColorSource;
import net.minecraft.client.color.item.ItemTintSource;
import net.minecraft.client.data.models.blockstates.BlockModelDefinitionGenerator;
import net.minecraft.client.data.models.blockstates.ConditionBuilder;
import net.minecraft.client.data.models.blockstates.MultiPartGenerator;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelInstance;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.client.renderer.block.model.Variant;
import net.minecraft.client.renderer.block.model.VariantMutator;
import net.minecraft.client.renderer.block.model.multipart.CombinedCondition;
import net.minecraft.client.renderer.block.model.multipart.Condition;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.special.BannerSpecialRenderer;
import net.minecraft.client.renderer.special.BedSpecialRenderer;
import net.minecraft.client.renderer.special.ChestSpecialRenderer;
import net.minecraft.client.renderer.special.ConduitSpecialRenderer;
import net.minecraft.client.renderer.special.CopperGolemStatueSpecialRenderer;
import net.minecraft.client.renderer.special.DecoratedPotSpecialRenderer;
import net.minecraft.client.renderer.special.PlayerHeadSpecialRenderer;
import net.minecraft.client.renderer.special.ShulkerBoxSpecialRenderer;
import net.minecraft.client.renderer.special.SkullSpecialRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.core.Direction;
import net.minecraft.core.FrontAndTop;
import net.minecraft.data.BlockFamilies;
import net.minecraft.data.BlockFamily;
import net.minecraft.resources.Identifier;
import net.minecraft.util.StringRepresentable;
import net.minecraft.util.random.Weighted;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.BeehiveBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChiseledBookShelfBlock;
import net.minecraft.world.level.block.CopperGolemStatueBlock;
import net.minecraft.world.level.block.CrafterBlock;
import net.minecraft.world.level.block.CreakingHeartBlock;
import net.minecraft.world.level.block.DriedGhastBlock;
import net.minecraft.world.level.block.HangingMossBlock;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.LightBlock;
import net.minecraft.world.level.block.MangrovePropaguleBlock;
import net.minecraft.world.level.block.MossyCarpetBlock;
import net.minecraft.world.level.block.MultifaceBlock;
import net.minecraft.world.level.block.PitcherCropBlock;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.SnifferEggBlock;
import net.minecraft.world.level.block.TestBlock;
import net.minecraft.world.level.block.VaultBlock;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.entity.trialspawner.TrialSpawnerState;
import net.minecraft.world.level.block.entity.vault.VaultState;
import net.minecraft.world.level.block.state.StateHolder;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BambooLeaves;
import net.minecraft.world.level.block.state.properties.BellAttachType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.ComparatorMode;
import net.minecraft.world.level.block.state.properties.CreakingHeartState;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.DripstoneThickness;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.PistonType;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.RailShape;
import net.minecraft.world.level.block.state.properties.RedstoneSide;
import net.minecraft.world.level.block.state.properties.SculkSensorPhase;
import net.minecraft.world.level.block.state.properties.SideChainPart;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.block.state.properties.StairsShape;
import net.minecraft.world.level.block.state.properties.StructureMode;
import net.minecraft.world.level.block.state.properties.TestBlockMode;
import net.minecraft.world.level.block.state.properties.Tilt;
import net.minecraft.world.level.block.state.properties.WallSide;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jspecify.annotations.Nullable;

@OnlyIn(Dist.CLIENT)
public class BlockModelGenerators {
    public final Consumer<BlockModelDefinitionGenerator> blockStateOutput;
    public final ItemModelOutput itemModelOutput;
    public final BiConsumer<Identifier, ModelInstance> modelOutput;
    public static final List<Block> NON_ORIENTABLE_TRAPDOOR = List.of(Blocks.OAK_TRAPDOOR, Blocks.DARK_OAK_TRAPDOOR, Blocks.IRON_TRAPDOOR);
    public static final VariantMutator NOP = p_405027_ -> p_405027_;
    public static final VariantMutator UV_LOCK = VariantMutator.UV_LOCK.withValue(true);
    public static final VariantMutator X_ROT_90 = VariantMutator.X_ROT.withValue(Quadrant.R90);
    public static final VariantMutator X_ROT_180 = VariantMutator.X_ROT.withValue(Quadrant.R180);
    public static final VariantMutator X_ROT_270 = VariantMutator.X_ROT.withValue(Quadrant.R270);
    public static final VariantMutator Y_ROT_90 = VariantMutator.Y_ROT.withValue(Quadrant.R90);
    public static final VariantMutator Y_ROT_180 = VariantMutator.Y_ROT.withValue(Quadrant.R180);
    public static final VariantMutator Y_ROT_270 = VariantMutator.Y_ROT.withValue(Quadrant.R270);
    public static final Function<ConditionBuilder, ConditionBuilder> FLOWER_BED_MODEL_1_SEGMENT_CONDITION = p_412003_ -> p_412003_;
    public static final Function<ConditionBuilder, ConditionBuilder> FLOWER_BED_MODEL_2_SEGMENT_CONDITION = p_412001_ -> p_412001_.term(
        BlockStateProperties.FLOWER_AMOUNT, 2, 3, 4
    );
    public static final Function<ConditionBuilder, ConditionBuilder> FLOWER_BED_MODEL_3_SEGMENT_CONDITION = p_412006_ -> p_412006_.term(
        BlockStateProperties.FLOWER_AMOUNT, 3, 4
    );
    public static final Function<ConditionBuilder, ConditionBuilder> FLOWER_BED_MODEL_4_SEGMENT_CONDITION = p_412005_ -> p_412005_.term(
        BlockStateProperties.FLOWER_AMOUNT, 4
    );
    public static final Function<ConditionBuilder, ConditionBuilder> LEAF_LITTER_MODEL_1_SEGMENT_CONDITION = p_412004_ -> p_412004_.term(
        BlockStateProperties.SEGMENT_AMOUNT, 1
    );
    public static final Function<ConditionBuilder, ConditionBuilder> LEAF_LITTER_MODEL_2_SEGMENT_CONDITION = p_411999_ -> p_411999_.term(
        BlockStateProperties.SEGMENT_AMOUNT, 2, 3
    );
    public static final Function<ConditionBuilder, ConditionBuilder> LEAF_LITTER_MODEL_3_SEGMENT_CONDITION = p_412002_ -> p_412002_.term(
        BlockStateProperties.SEGMENT_AMOUNT, 3
    );
    public static final Function<ConditionBuilder, ConditionBuilder> LEAF_LITTER_MODEL_4_SEGMENT_CONDITION = p_412000_ -> p_412000_.term(
        BlockStateProperties.SEGMENT_AMOUNT, 4
    );
    public static final Map<Block, BlockModelGenerators.BlockStateGeneratorSupplier> FULL_BLOCK_MODEL_CUSTOM_GENERATORS = Map.of(
        Blocks.STONE,
        BlockModelGenerators::createMirroredCubeGenerator,
        Blocks.DEEPSLATE,
        BlockModelGenerators::createMirroredColumnGenerator,
        Blocks.MUD_BRICKS,
        BlockModelGenerators::createNorthWestMirroredCubeGenerator
    );
    public static final PropertyDispatch<VariantMutator> ROTATION_FACING = PropertyDispatch.modify(BlockStateProperties.FACING)
        .select(Direction.DOWN, X_ROT_90)
        .select(Direction.UP, X_ROT_270)
        .select(Direction.NORTH, NOP)
        .select(Direction.SOUTH, Y_ROT_180)
        .select(Direction.WEST, Y_ROT_270)
        .select(Direction.EAST, Y_ROT_90);
    public static final PropertyDispatch<VariantMutator> ROTATIONS_COLUMN_WITH_FACING = PropertyDispatch.modify(BlockStateProperties.FACING)
        .select(Direction.DOWN, X_ROT_180)
        .select(Direction.UP, NOP)
        .select(Direction.NORTH, X_ROT_90)
        .select(Direction.SOUTH, X_ROT_90.then(Y_ROT_180))
        .select(Direction.WEST, X_ROT_90.then(Y_ROT_270))
        .select(Direction.EAST, X_ROT_90.then(Y_ROT_90));
    public static final PropertyDispatch<VariantMutator> ROTATION_TORCH = PropertyDispatch.modify(BlockStateProperties.HORIZONTAL_FACING)
        .select(Direction.EAST, NOP)
        .select(Direction.SOUTH, Y_ROT_90)
        .select(Direction.WEST, Y_ROT_180)
        .select(Direction.NORTH, Y_ROT_270);
    public static final PropertyDispatch<VariantMutator> ROTATION_HORIZONTAL_FACING_ALT = PropertyDispatch.modify(BlockStateProperties.HORIZONTAL_FACING)
        .select(Direction.SOUTH, NOP)
        .select(Direction.WEST, Y_ROT_90)
        .select(Direction.NORTH, Y_ROT_180)
        .select(Direction.EAST, Y_ROT_270);
    public static final PropertyDispatch<VariantMutator> ROTATION_HORIZONTAL_FACING = PropertyDispatch.modify(BlockStateProperties.HORIZONTAL_FACING)
        .select(Direction.EAST, Y_ROT_90)
        .select(Direction.SOUTH, Y_ROT_180)
        .select(Direction.WEST, Y_ROT_270)
        .select(Direction.NORTH, NOP);
    public static final Map<Block, TexturedModel> TEXTURED_MODELS = ImmutableMap.<Block, TexturedModel>builder()
        .put(Blocks.SANDSTONE, TexturedModel.TOP_BOTTOM_WITH_WALL.get(Blocks.SANDSTONE))
        .put(Blocks.RED_SANDSTONE, TexturedModel.TOP_BOTTOM_WITH_WALL.get(Blocks.RED_SANDSTONE))
        .put(Blocks.SMOOTH_SANDSTONE, TexturedModel.createAllSame(TextureMapping.getBlockTexture(Blocks.SANDSTONE, "_top")))
        .put(Blocks.SMOOTH_RED_SANDSTONE, TexturedModel.createAllSame(TextureMapping.getBlockTexture(Blocks.RED_SANDSTONE, "_top")))
        .put(
            Blocks.CUT_SANDSTONE,
            TexturedModel.COLUMN
                .get(Blocks.SANDSTONE)
                .updateTextures(p_465393_ -> p_465393_.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(Blocks.CUT_SANDSTONE)))
        )
        .put(
            Blocks.CUT_RED_SANDSTONE,
            TexturedModel.COLUMN
                .get(Blocks.RED_SANDSTONE)
                .updateTextures(p_465369_ -> p_465369_.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(Blocks.CUT_RED_SANDSTONE)))
        )
        .put(Blocks.QUARTZ_BLOCK, TexturedModel.COLUMN.get(Blocks.QUARTZ_BLOCK))
        .put(Blocks.SMOOTH_QUARTZ, TexturedModel.createAllSame(TextureMapping.getBlockTexture(Blocks.QUARTZ_BLOCK, "_bottom")))
        .put(Blocks.BLACKSTONE, TexturedModel.COLUMN_WITH_WALL.get(Blocks.BLACKSTONE))
        .put(Blocks.DEEPSLATE, TexturedModel.COLUMN_WITH_WALL.get(Blocks.DEEPSLATE))
        .put(
            Blocks.CHISELED_QUARTZ_BLOCK,
            TexturedModel.COLUMN
                .get(Blocks.CHISELED_QUARTZ_BLOCK)
                .updateTextures(p_465367_ -> p_465367_.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(Blocks.CHISELED_QUARTZ_BLOCK)))
        )
        .put(Blocks.CHISELED_SANDSTONE, TexturedModel.COLUMN.get(Blocks.CHISELED_SANDSTONE).updateTextures(p_465378_ -> {
            p_465378_.put(TextureSlot.END, TextureMapping.getBlockTexture(Blocks.SANDSTONE, "_top"));
            p_465378_.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(Blocks.CHISELED_SANDSTONE));
        }))
        .put(Blocks.CHISELED_RED_SANDSTONE, TexturedModel.COLUMN.get(Blocks.CHISELED_RED_SANDSTONE).updateTextures(p_465421_ -> {
            p_465421_.put(TextureSlot.END, TextureMapping.getBlockTexture(Blocks.RED_SANDSTONE, "_top"));
            p_465421_.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(Blocks.CHISELED_RED_SANDSTONE));
        }))
        .put(Blocks.CHISELED_TUFF_BRICKS, TexturedModel.COLUMN_WITH_WALL.get(Blocks.CHISELED_TUFF_BRICKS))
        .put(Blocks.CHISELED_TUFF, TexturedModel.COLUMN_WITH_WALL.get(Blocks.CHISELED_TUFF))
        .build();
    public static final Map<BlockFamily.Variant, BiConsumer<BlockModelGenerators.BlockFamilyProvider, Block>> SHAPE_CONSUMERS = ImmutableMap.<BlockFamily.Variant, BiConsumer<BlockModelGenerators.BlockFamilyProvider, Block>>builder()
        .put(BlockFamily.Variant.BUTTON, BlockModelGenerators.BlockFamilyProvider::button)
        .put(BlockFamily.Variant.DOOR, BlockModelGenerators.BlockFamilyProvider::door)
        .put(BlockFamily.Variant.CHISELED, BlockModelGenerators.BlockFamilyProvider::fullBlockVariant)
        .put(BlockFamily.Variant.CRACKED, BlockModelGenerators.BlockFamilyProvider::fullBlockVariant)
        .put(BlockFamily.Variant.CUSTOM_FENCE, BlockModelGenerators.BlockFamilyProvider::customFence)
        .put(BlockFamily.Variant.FENCE, BlockModelGenerators.BlockFamilyProvider::fence)
        .put(BlockFamily.Variant.CUSTOM_FENCE_GATE, BlockModelGenerators.BlockFamilyProvider::customFenceGate)
        .put(BlockFamily.Variant.FENCE_GATE, BlockModelGenerators.BlockFamilyProvider::fenceGate)
        .put(BlockFamily.Variant.SIGN, BlockModelGenerators.BlockFamilyProvider::sign)
        .put(BlockFamily.Variant.SLAB, BlockModelGenerators.BlockFamilyProvider::slab)
        .put(BlockFamily.Variant.STAIRS, BlockModelGenerators.BlockFamilyProvider::stairs)
        .put(BlockFamily.Variant.PRESSURE_PLATE, BlockModelGenerators.BlockFamilyProvider::pressurePlate)
        .put(BlockFamily.Variant.TRAPDOOR, BlockModelGenerators.BlockFamilyProvider::trapdoor)
        .put(BlockFamily.Variant.WALL, BlockModelGenerators.BlockFamilyProvider::wall)
        .build();
    public static final Map<Direction, VariantMutator> MULTIFACE_GENERATOR = ImmutableMap.of(
        Direction.NORTH,
        NOP,
        Direction.EAST,
        Y_ROT_90.then(UV_LOCK),
        Direction.SOUTH,
        Y_ROT_180.then(UV_LOCK),
        Direction.WEST,
        Y_ROT_270.then(UV_LOCK),
        Direction.UP,
        X_ROT_270.then(UV_LOCK),
        Direction.DOWN,
        X_ROT_90.then(UV_LOCK)
    );
    public static final Map<BlockModelGenerators.BookSlotModelCacheKey, Identifier> CHISELED_BOOKSHELF_SLOT_MODEL_CACHE = new HashMap<>();

    public static Variant plainModel(Identifier modelLocation) {
        return new Variant(modelLocation);
    }

    public static MultiVariant variant(Variant variant) {
        return new MultiVariant(WeightedList.of(variant));
    }

    public static MultiVariant variants(Variant... variants) {
        return new MultiVariant(WeightedList.of(Arrays.stream(variants).map(p_408964_ -> new Weighted<>(p_408964_, 1)).toList()));
    }

    public static MultiVariant plainVariant(Identifier id) {
        return variant(plainModel(id));
    }

    public static ConditionBuilder condition() {
        return new ConditionBuilder();
    }

    @SafeVarargs
    public static <T extends Enum<T> & StringRepresentable> ConditionBuilder condition(EnumProperty<T> property, T value, T... otherValues) {
        return condition().term(property, value, otherValues);
    }

    public static ConditionBuilder condition(BooleanProperty property, boolean value) {
        return condition().term(property, value);
    }

    public static Condition or(ConditionBuilder... conditions) {
        return new CombinedCondition(CombinedCondition.Operation.OR, Stream.of(conditions).map(ConditionBuilder::build).toList());
    }

    public static Condition and(ConditionBuilder... conditions) {
        return new CombinedCondition(CombinedCondition.Operation.AND, Stream.of(conditions).map(ConditionBuilder::build).toList());
    }

    public static BlockModelDefinitionGenerator createMirroredCubeGenerator(
        Block block, Variant p_variant, TextureMapping textureMapping, BiConsumer<Identifier, ModelInstance> modelOutput
    ) {
        Variant variant = plainModel(ModelTemplates.CUBE_MIRRORED_ALL.create(block, textureMapping, modelOutput));
        return MultiVariantGenerator.dispatch(block, createRotatedVariants(p_variant, variant));
    }

    public static BlockModelDefinitionGenerator createNorthWestMirroredCubeGenerator(
        Block block, Variant variant, TextureMapping textureMapping, BiConsumer<Identifier, ModelInstance> modelOutput
    ) {
        MultiVariant multivariant = plainVariant(ModelTemplates.CUBE_NORTH_WEST_MIRRORED_ALL.create(block, textureMapping, modelOutput));
        return createSimpleBlock(block, multivariant);
    }

    public static BlockModelDefinitionGenerator createMirroredColumnGenerator(
        Block block, Variant p_variant, TextureMapping textureMapping, BiConsumer<Identifier, ModelInstance> modelOutput
    ) {
        Variant variant = plainModel(ModelTemplates.CUBE_COLUMN_MIRRORED.create(block, textureMapping, modelOutput));
        return MultiVariantGenerator.dispatch(block, createRotatedVariants(p_variant, variant)).with(createRotatedPillar());
    }

    public BlockModelGenerators(Consumer<BlockModelDefinitionGenerator> blockStateOutput, ItemModelOutput itemModelOutput, BiConsumer<Identifier, ModelInstance> modelOutput) {
        this.blockStateOutput = blockStateOutput;
        this.itemModelOutput = itemModelOutput;
        this.modelOutput = modelOutput;
    }

    public void registerSimpleItemModel(Item item, Identifier model) {
        this.itemModelOutput.accept(item, ItemModelUtils.plainModel(model));
    }

    public void registerSimpleItemModel(Block block, Identifier model) {
        this.itemModelOutput.accept(block.asItem(), ItemModelUtils.plainModel(model));
    }

    public void registerSimpleTintedItemModel(Block block, Identifier model, ItemTintSource tintSource) {
        this.itemModelOutput.accept(block.asItem(), ItemModelUtils.tintedModel(model, tintSource));
    }

    public Identifier createFlatItemModel(Item item) {
        return ModelTemplates.FLAT_ITEM.create(ModelLocationUtils.getModelLocation(item), TextureMapping.layer0(item), this.modelOutput);
    }

    public Identifier createFlatItemModelWithBlockTexture(Item item, Block block) {
        return ModelTemplates.FLAT_ITEM.create(ModelLocationUtils.getModelLocation(item), TextureMapping.layer0(block), this.modelOutput);
    }

    public Identifier createFlatItemModelWithBlockTexture(Item item, Block block, String suffix) {
        return ModelTemplates.FLAT_ITEM
            .create(
                ModelLocationUtils.getModelLocation(item), TextureMapping.layer0(TextureMapping.getBlockTexture(block, suffix)), this.modelOutput
            );
    }

    public Identifier createFlatItemModelWithBlockTextureAndOverlay(Item item, Block block, String suffix) {
        Identifier identifier = TextureMapping.getBlockTexture(block);
        Identifier identifier1 = TextureMapping.getBlockTexture(block, suffix);
        return ModelTemplates.TWO_LAYERED_ITEM
            .create(ModelLocationUtils.getModelLocation(item), TextureMapping.layered(identifier, identifier1), this.modelOutput);
    }

    public void registerSimpleFlatItemModel(Item item) {
        this.registerSimpleItemModel(item, this.createFlatItemModel(item));
    }

    public void registerSimpleFlatItemModel(Block block) {
        Item item = block.asItem();
        if (item != Items.AIR) {
            this.registerSimpleItemModel(item, this.createFlatItemModelWithBlockTexture(item, block));
        }
    }

    public void registerSimpleFlatItemModel(Block block, String suffix) {
        Item item = block.asItem();
        if (item != Items.AIR) {
            this.registerSimpleItemModel(item, this.createFlatItemModelWithBlockTexture(item, block, suffix));
        }
    }

    public void registerTwoLayerFlatItemModel(Block block, String suffix) {
        Item item = block.asItem();
        if (item != Items.AIR) {
            Identifier identifier = this.createFlatItemModelWithBlockTextureAndOverlay(item, block, suffix);
            this.registerSimpleItemModel(item, identifier);
        }
    }

    public static MultiVariant createRotatedVariants(Variant variant) {
        return variants(variant, variant.with(Y_ROT_90), variant.with(Y_ROT_180), variant.with(Y_ROT_270));
    }

    public static MultiVariant createRotatedVariants(Variant variant, Variant mirroredVariant) {
        return variants(variant, mirroredVariant, variant.with(Y_ROT_180), mirroredVariant.with(Y_ROT_180));
    }

    public static PropertyDispatch<MultiVariant> createBooleanModelDispatch(BooleanProperty property, MultiVariant onTrue, MultiVariant onFalse) {
        return PropertyDispatch.initial(property).select(true, onTrue).select(false, onFalse);
    }

    public void createRotatedMirroredVariantBlock(Block block) {
        Variant variant = plainModel(TexturedModel.CUBE.create(block, this.modelOutput));
        Variant variant1 = plainModel(TexturedModel.CUBE_MIRRORED.create(block, this.modelOutput));
        this.blockStateOutput.accept(MultiVariantGenerator.dispatch(block, createRotatedVariants(variant, variant1)));
    }

    public void createRotatedVariantBlock(Block block) {
        Variant variant = plainModel(TexturedModel.CUBE.create(block, this.modelOutput));
        this.blockStateOutput.accept(MultiVariantGenerator.dispatch(block, createRotatedVariants(variant)));
    }

    public void createBrushableBlock(Block block) {
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(block)
                    .with(
                        PropertyDispatch.initial(BlockStateProperties.DUSTED)
                            .generate(
                                p_465407_ -> {
                                    String s = "_" + p_465407_;
                                    Identifier identifier = TextureMapping.getBlockTexture(block, s);
                                    Identifier identifier1 = ModelTemplates.CUBE_ALL
                                        .createWithSuffix(block, s, new TextureMapping().put(TextureSlot.ALL, identifier), this.modelOutput);
                                    return plainVariant(identifier1);
                                }
                            )
                    )
            );
        this.registerSimpleItemModel(block, ModelLocationUtils.getModelLocation(block, "_0"));
    }

    public static BlockModelDefinitionGenerator createButton(Block block, MultiVariant unpowered, MultiVariant powered) {
        return MultiVariantGenerator.dispatch(block)
            .with(PropertyDispatch.initial(BlockStateProperties.POWERED).select(false, unpowered).select(true, powered))
            .with(
                PropertyDispatch.modify(BlockStateProperties.ATTACH_FACE, BlockStateProperties.HORIZONTAL_FACING)
                    .select(AttachFace.FLOOR, Direction.EAST, Y_ROT_90)
                    .select(AttachFace.FLOOR, Direction.WEST, Y_ROT_270)
                    .select(AttachFace.FLOOR, Direction.SOUTH, Y_ROT_180)
                    .select(AttachFace.FLOOR, Direction.NORTH, NOP)
                    .select(AttachFace.WALL, Direction.EAST, Y_ROT_90.then(X_ROT_90).then(UV_LOCK))
                    .select(AttachFace.WALL, Direction.WEST, Y_ROT_270.then(X_ROT_90).then(UV_LOCK))
                    .select(AttachFace.WALL, Direction.SOUTH, Y_ROT_180.then(X_ROT_90).then(UV_LOCK))
                    .select(AttachFace.WALL, Direction.NORTH, X_ROT_90.then(UV_LOCK))
                    .select(AttachFace.CEILING, Direction.EAST, Y_ROT_270.then(X_ROT_180))
                    .select(AttachFace.CEILING, Direction.WEST, Y_ROT_90.then(X_ROT_180))
                    .select(AttachFace.CEILING, Direction.SOUTH, X_ROT_180)
                    .select(AttachFace.CEILING, Direction.NORTH, Y_ROT_180.then(X_ROT_180))
            );
    }

    public static BlockModelDefinitionGenerator createDoor(
        Block block,
        MultiVariant topLeft,
        MultiVariant topLeftOpen,
        MultiVariant topRight,
        MultiVariant topRightOpen,
        MultiVariant bottomLeft,
        MultiVariant bottomLeftOpen,
        MultiVariant bottomRight,
        MultiVariant bottomRightOpen
    ) {
        return MultiVariantGenerator.dispatch(block)
            .with(
                PropertyDispatch.initial(
                        BlockStateProperties.HORIZONTAL_FACING,
                        BlockStateProperties.DOUBLE_BLOCK_HALF,
                        BlockStateProperties.DOOR_HINGE,
                        BlockStateProperties.OPEN
                    )
                    .select(Direction.EAST, DoubleBlockHalf.LOWER, DoorHingeSide.LEFT, false, topLeft)
                    .select(Direction.SOUTH, DoubleBlockHalf.LOWER, DoorHingeSide.LEFT, false, topLeft.with(Y_ROT_90))
                    .select(Direction.WEST, DoubleBlockHalf.LOWER, DoorHingeSide.LEFT, false, topLeft.with(Y_ROT_180))
                    .select(Direction.NORTH, DoubleBlockHalf.LOWER, DoorHingeSide.LEFT, false, topLeft.with(Y_ROT_270))
                    .select(Direction.EAST, DoubleBlockHalf.LOWER, DoorHingeSide.RIGHT, false, topRight)
                    .select(Direction.SOUTH, DoubleBlockHalf.LOWER, DoorHingeSide.RIGHT, false, topRight.with(Y_ROT_90))
                    .select(Direction.WEST, DoubleBlockHalf.LOWER, DoorHingeSide.RIGHT, false, topRight.with(Y_ROT_180))
                    .select(Direction.NORTH, DoubleBlockHalf.LOWER, DoorHingeSide.RIGHT, false, topRight.with(Y_ROT_270))
                    .select(Direction.EAST, DoubleBlockHalf.LOWER, DoorHingeSide.LEFT, true, topLeftOpen.with(Y_ROT_90))
                    .select(Direction.SOUTH, DoubleBlockHalf.LOWER, DoorHingeSide.LEFT, true, topLeftOpen.with(Y_ROT_180))
                    .select(Direction.WEST, DoubleBlockHalf.LOWER, DoorHingeSide.LEFT, true, topLeftOpen.with(Y_ROT_270))
                    .select(Direction.NORTH, DoubleBlockHalf.LOWER, DoorHingeSide.LEFT, true, topLeftOpen)
                    .select(Direction.EAST, DoubleBlockHalf.LOWER, DoorHingeSide.RIGHT, true, topRightOpen.with(Y_ROT_270))
                    .select(Direction.SOUTH, DoubleBlockHalf.LOWER, DoorHingeSide.RIGHT, true, topRightOpen)
                    .select(Direction.WEST, DoubleBlockHalf.LOWER, DoorHingeSide.RIGHT, true, topRightOpen.with(Y_ROT_90))
                    .select(Direction.NORTH, DoubleBlockHalf.LOWER, DoorHingeSide.RIGHT, true, topRightOpen.with(Y_ROT_180))
                    .select(Direction.EAST, DoubleBlockHalf.UPPER, DoorHingeSide.LEFT, false, bottomLeft)
                    .select(Direction.SOUTH, DoubleBlockHalf.UPPER, DoorHingeSide.LEFT, false, bottomLeft.with(Y_ROT_90))
                    .select(Direction.WEST, DoubleBlockHalf.UPPER, DoorHingeSide.LEFT, false, bottomLeft.with(Y_ROT_180))
                    .select(Direction.NORTH, DoubleBlockHalf.UPPER, DoorHingeSide.LEFT, false, bottomLeft.with(Y_ROT_270))
                    .select(Direction.EAST, DoubleBlockHalf.UPPER, DoorHingeSide.RIGHT, false, bottomRight)
                    .select(Direction.SOUTH, DoubleBlockHalf.UPPER, DoorHingeSide.RIGHT, false, bottomRight.with(Y_ROT_90))
                    .select(Direction.WEST, DoubleBlockHalf.UPPER, DoorHingeSide.RIGHT, false, bottomRight.with(Y_ROT_180))
                    .select(Direction.NORTH, DoubleBlockHalf.UPPER, DoorHingeSide.RIGHT, false, bottomRight.with(Y_ROT_270))
                    .select(Direction.EAST, DoubleBlockHalf.UPPER, DoorHingeSide.LEFT, true, bottomLeftOpen.with(Y_ROT_90))
                    .select(Direction.SOUTH, DoubleBlockHalf.UPPER, DoorHingeSide.LEFT, true, bottomLeftOpen.with(Y_ROT_180))
                    .select(Direction.WEST, DoubleBlockHalf.UPPER, DoorHingeSide.LEFT, true, bottomLeftOpen.with(Y_ROT_270))
                    .select(Direction.NORTH, DoubleBlockHalf.UPPER, DoorHingeSide.LEFT, true, bottomLeftOpen)
                    .select(Direction.EAST, DoubleBlockHalf.UPPER, DoorHingeSide.RIGHT, true, bottomRightOpen.with(Y_ROT_270))
                    .select(Direction.SOUTH, DoubleBlockHalf.UPPER, DoorHingeSide.RIGHT, true, bottomRightOpen)
                    .select(Direction.WEST, DoubleBlockHalf.UPPER, DoorHingeSide.RIGHT, true, bottomRightOpen.with(Y_ROT_90))
                    .select(Direction.NORTH, DoubleBlockHalf.UPPER, DoorHingeSide.RIGHT, true, bottomRightOpen.with(Y_ROT_180))
            );
    }

    public static BlockModelDefinitionGenerator createCustomFence(
        Block block, MultiVariant post, MultiVariant north, MultiVariant east, MultiVariant south, MultiVariant west
    ) {
        return MultiPartGenerator.multiPart(block)
            .with(post)
            .with(condition().term(BlockStateProperties.NORTH, true), north)
            .with(condition().term(BlockStateProperties.EAST, true), east)
            .with(condition().term(BlockStateProperties.SOUTH, true), south)
            .with(condition().term(BlockStateProperties.WEST, true), west);
    }

    public static BlockModelDefinitionGenerator createFence(Block block, MultiVariant post, MultiVariant side) {
        return MultiPartGenerator.multiPart(block)
            .with(post)
            .with(condition().term(BlockStateProperties.NORTH, true), side.with(UV_LOCK))
            .with(condition().term(BlockStateProperties.EAST, true), side.with(Y_ROT_90).with(UV_LOCK))
            .with(condition().term(BlockStateProperties.SOUTH, true), side.with(Y_ROT_180).with(UV_LOCK))
            .with(condition().term(BlockStateProperties.WEST, true), side.with(Y_ROT_270).with(UV_LOCK));
    }

    public static BlockModelDefinitionGenerator createWall(Block block, MultiVariant post, MultiVariant lowSide, MultiVariant tallSide) {
        return MultiPartGenerator.multiPart(block)
            .with(condition().term(BlockStateProperties.UP, true), post)
            .with(condition().term(BlockStateProperties.NORTH_WALL, WallSide.LOW), lowSide.with(UV_LOCK))
            .with(condition().term(BlockStateProperties.EAST_WALL, WallSide.LOW), lowSide.with(Y_ROT_90).with(UV_LOCK))
            .with(condition().term(BlockStateProperties.SOUTH_WALL, WallSide.LOW), lowSide.with(Y_ROT_180).with(UV_LOCK))
            .with(condition().term(BlockStateProperties.WEST_WALL, WallSide.LOW), lowSide.with(Y_ROT_270).with(UV_LOCK))
            .with(condition().term(BlockStateProperties.NORTH_WALL, WallSide.TALL), tallSide.with(UV_LOCK))
            .with(condition().term(BlockStateProperties.EAST_WALL, WallSide.TALL), tallSide.with(Y_ROT_90).with(UV_LOCK))
            .with(condition().term(BlockStateProperties.SOUTH_WALL, WallSide.TALL), tallSide.with(Y_ROT_180).with(UV_LOCK))
            .with(condition().term(BlockStateProperties.WEST_WALL, WallSide.TALL), tallSide.with(Y_ROT_270).with(UV_LOCK));
    }

    public static BlockModelDefinitionGenerator createFenceGate(
        Block block, MultiVariant open, MultiVariant closed, MultiVariant wallOpen, MultiVariant wallClosed, boolean uvLock
    ) {
        return MultiVariantGenerator.dispatch(block)
            .with(
                PropertyDispatch.initial(BlockStateProperties.IN_WALL, BlockStateProperties.OPEN)
                    .select(false, false, closed)
                    .select(true, false, wallClosed)
                    .select(false, true, open)
                    .select(true, true, wallOpen)
            )
            .with(uvLock ? UV_LOCK : NOP)
            .with(ROTATION_HORIZONTAL_FACING_ALT);
    }

    public static BlockModelDefinitionGenerator createStairs(Block block, MultiVariant inner, MultiVariant straight, MultiVariant outer) {
        return MultiVariantGenerator.dispatch(block)
            .with(
                PropertyDispatch.initial(BlockStateProperties.HORIZONTAL_FACING, BlockStateProperties.HALF, BlockStateProperties.STAIRS_SHAPE)
                    .select(Direction.EAST, Half.BOTTOM, StairsShape.STRAIGHT, straight)
                    .select(Direction.WEST, Half.BOTTOM, StairsShape.STRAIGHT, straight.with(Y_ROT_180).with(UV_LOCK))
                    .select(Direction.SOUTH, Half.BOTTOM, StairsShape.STRAIGHT, straight.with(Y_ROT_90).with(UV_LOCK))
                    .select(Direction.NORTH, Half.BOTTOM, StairsShape.STRAIGHT, straight.with(Y_ROT_270).with(UV_LOCK))
                    .select(Direction.EAST, Half.BOTTOM, StairsShape.OUTER_RIGHT, outer)
                    .select(Direction.WEST, Half.BOTTOM, StairsShape.OUTER_RIGHT, outer.with(Y_ROT_180).with(UV_LOCK))
                    .select(Direction.SOUTH, Half.BOTTOM, StairsShape.OUTER_RIGHT, outer.with(Y_ROT_90).with(UV_LOCK))
                    .select(Direction.NORTH, Half.BOTTOM, StairsShape.OUTER_RIGHT, outer.with(Y_ROT_270).with(UV_LOCK))
                    .select(Direction.EAST, Half.BOTTOM, StairsShape.OUTER_LEFT, outer.with(Y_ROT_270).with(UV_LOCK))
                    .select(Direction.WEST, Half.BOTTOM, StairsShape.OUTER_LEFT, outer.with(Y_ROT_90).with(UV_LOCK))
                    .select(Direction.SOUTH, Half.BOTTOM, StairsShape.OUTER_LEFT, outer)
                    .select(Direction.NORTH, Half.BOTTOM, StairsShape.OUTER_LEFT, outer.with(Y_ROT_180).with(UV_LOCK))
                    .select(Direction.EAST, Half.BOTTOM, StairsShape.INNER_RIGHT, inner)
                    .select(Direction.WEST, Half.BOTTOM, StairsShape.INNER_RIGHT, inner.with(Y_ROT_180).with(UV_LOCK))
                    .select(Direction.SOUTH, Half.BOTTOM, StairsShape.INNER_RIGHT, inner.with(Y_ROT_90).with(UV_LOCK))
                    .select(Direction.NORTH, Half.BOTTOM, StairsShape.INNER_RIGHT, inner.with(Y_ROT_270).with(UV_LOCK))
                    .select(Direction.EAST, Half.BOTTOM, StairsShape.INNER_LEFT, inner.with(Y_ROT_270).with(UV_LOCK))
                    .select(Direction.WEST, Half.BOTTOM, StairsShape.INNER_LEFT, inner.with(Y_ROT_90).with(UV_LOCK))
                    .select(Direction.SOUTH, Half.BOTTOM, StairsShape.INNER_LEFT, inner)
                    .select(Direction.NORTH, Half.BOTTOM, StairsShape.INNER_LEFT, inner.with(Y_ROT_180).with(UV_LOCK))
                    .select(Direction.EAST, Half.TOP, StairsShape.STRAIGHT, straight.with(X_ROT_180).with(UV_LOCK))
                    .select(Direction.WEST, Half.TOP, StairsShape.STRAIGHT, straight.with(X_ROT_180).with(Y_ROT_180).with(UV_LOCK))
                    .select(Direction.SOUTH, Half.TOP, StairsShape.STRAIGHT, straight.with(X_ROT_180).with(Y_ROT_90).with(UV_LOCK))
                    .select(Direction.NORTH, Half.TOP, StairsShape.STRAIGHT, straight.with(X_ROT_180).with(Y_ROT_270).with(UV_LOCK))
                    .select(Direction.EAST, Half.TOP, StairsShape.OUTER_RIGHT, outer.with(X_ROT_180).with(Y_ROT_90).with(UV_LOCK))
                    .select(Direction.WEST, Half.TOP, StairsShape.OUTER_RIGHT, outer.with(X_ROT_180).with(Y_ROT_270).with(UV_LOCK))
                    .select(Direction.SOUTH, Half.TOP, StairsShape.OUTER_RIGHT, outer.with(X_ROT_180).with(Y_ROT_180).with(UV_LOCK))
                    .select(Direction.NORTH, Half.TOP, StairsShape.OUTER_RIGHT, outer.with(X_ROT_180).with(UV_LOCK))
                    .select(Direction.EAST, Half.TOP, StairsShape.OUTER_LEFT, outer.with(X_ROT_180).with(UV_LOCK))
                    .select(Direction.WEST, Half.TOP, StairsShape.OUTER_LEFT, outer.with(X_ROT_180).with(Y_ROT_180).with(UV_LOCK))
                    .select(Direction.SOUTH, Half.TOP, StairsShape.OUTER_LEFT, outer.with(X_ROT_180).with(Y_ROT_90).with(UV_LOCK))
                    .select(Direction.NORTH, Half.TOP, StairsShape.OUTER_LEFT, outer.with(X_ROT_180).with(Y_ROT_270).with(UV_LOCK))
                    .select(Direction.EAST, Half.TOP, StairsShape.INNER_RIGHT, inner.with(X_ROT_180).with(Y_ROT_90).with(UV_LOCK))
                    .select(Direction.WEST, Half.TOP, StairsShape.INNER_RIGHT, inner.with(X_ROT_180).with(Y_ROT_270).with(UV_LOCK))
                    .select(Direction.SOUTH, Half.TOP, StairsShape.INNER_RIGHT, inner.with(X_ROT_180).with(Y_ROT_180).with(UV_LOCK))
                    .select(Direction.NORTH, Half.TOP, StairsShape.INNER_RIGHT, inner.with(X_ROT_180).with(UV_LOCK))
                    .select(Direction.EAST, Half.TOP, StairsShape.INNER_LEFT, inner.with(X_ROT_180).with(UV_LOCK))
                    .select(Direction.WEST, Half.TOP, StairsShape.INNER_LEFT, inner.with(X_ROT_180).with(Y_ROT_180).with(UV_LOCK))
                    .select(Direction.SOUTH, Half.TOP, StairsShape.INNER_LEFT, inner.with(X_ROT_180).with(Y_ROT_90).with(UV_LOCK))
                    .select(Direction.NORTH, Half.TOP, StairsShape.INNER_LEFT, inner.with(X_ROT_180).with(Y_ROT_270).with(UV_LOCK))
            );
    }

    public static BlockModelDefinitionGenerator createOrientableTrapdoor(
        Block block, MultiVariant top, MultiVariant bottom, MultiVariant open
    ) {
        return MultiVariantGenerator.dispatch(block)
            .with(
                PropertyDispatch.initial(BlockStateProperties.HORIZONTAL_FACING, BlockStateProperties.HALF, BlockStateProperties.OPEN)
                    .select(Direction.NORTH, Half.BOTTOM, false, bottom)
                    .select(Direction.SOUTH, Half.BOTTOM, false, bottom.with(Y_ROT_180))
                    .select(Direction.EAST, Half.BOTTOM, false, bottom.with(Y_ROT_90))
                    .select(Direction.WEST, Half.BOTTOM, false, bottom.with(Y_ROT_270))
                    .select(Direction.NORTH, Half.TOP, false, top)
                    .select(Direction.SOUTH, Half.TOP, false, top.with(Y_ROT_180))
                    .select(Direction.EAST, Half.TOP, false, top.with(Y_ROT_90))
                    .select(Direction.WEST, Half.TOP, false, top.with(Y_ROT_270))
                    .select(Direction.NORTH, Half.BOTTOM, true, open)
                    .select(Direction.SOUTH, Half.BOTTOM, true, open.with(Y_ROT_180))
                    .select(Direction.EAST, Half.BOTTOM, true, open.with(Y_ROT_90))
                    .select(Direction.WEST, Half.BOTTOM, true, open.with(Y_ROT_270))
                    .select(Direction.NORTH, Half.TOP, true, open.with(X_ROT_180).with(Y_ROT_180))
                    .select(Direction.SOUTH, Half.TOP, true, open.with(X_ROT_180))
                    .select(Direction.EAST, Half.TOP, true, open.with(X_ROT_180).with(Y_ROT_270))
                    .select(Direction.WEST, Half.TOP, true, open.with(X_ROT_180).with(Y_ROT_90))
            );
    }

    public static BlockModelDefinitionGenerator createTrapdoor(Block block, MultiVariant top, MultiVariant bottom, MultiVariant open) {
        return MultiVariantGenerator.dispatch(block)
            .with(
                PropertyDispatch.initial(BlockStateProperties.HORIZONTAL_FACING, BlockStateProperties.HALF, BlockStateProperties.OPEN)
                    .select(Direction.NORTH, Half.BOTTOM, false, bottom)
                    .select(Direction.SOUTH, Half.BOTTOM, false, bottom)
                    .select(Direction.EAST, Half.BOTTOM, false, bottom)
                    .select(Direction.WEST, Half.BOTTOM, false, bottom)
                    .select(Direction.NORTH, Half.TOP, false, top)
                    .select(Direction.SOUTH, Half.TOP, false, top)
                    .select(Direction.EAST, Half.TOP, false, top)
                    .select(Direction.WEST, Half.TOP, false, top)
                    .select(Direction.NORTH, Half.BOTTOM, true, open)
                    .select(Direction.SOUTH, Half.BOTTOM, true, open.with(Y_ROT_180))
                    .select(Direction.EAST, Half.BOTTOM, true, open.with(Y_ROT_90))
                    .select(Direction.WEST, Half.BOTTOM, true, open.with(Y_ROT_270))
                    .select(Direction.NORTH, Half.TOP, true, open)
                    .select(Direction.SOUTH, Half.TOP, true, open.with(Y_ROT_180))
                    .select(Direction.EAST, Half.TOP, true, open.with(Y_ROT_90))
                    .select(Direction.WEST, Half.TOP, true, open.with(Y_ROT_270))
            );
    }

    public static MultiVariantGenerator createSimpleBlock(Block block, MultiVariant variants) {
        return MultiVariantGenerator.dispatch(block, variants);
    }

    public static PropertyDispatch<VariantMutator> createRotatedPillar() {
        return PropertyDispatch.modify(BlockStateProperties.AXIS)
            .select(Direction.Axis.Y, NOP)
            .select(Direction.Axis.Z, X_ROT_90)
            .select(Direction.Axis.X, X_ROT_90.then(Y_ROT_90));
    }

    public static BlockModelDefinitionGenerator createPillarBlockUVLocked(Block block, TextureMapping textureMapping, BiConsumer<Identifier, ModelInstance> modelOutput) {
        MultiVariant multivariant = plainVariant(ModelTemplates.CUBE_COLUMN_UV_LOCKED_X.create(block, textureMapping, modelOutput));
        MultiVariant multivariant1 = plainVariant(ModelTemplates.CUBE_COLUMN_UV_LOCKED_Y.create(block, textureMapping, modelOutput));
        MultiVariant multivariant2 = plainVariant(ModelTemplates.CUBE_COLUMN_UV_LOCKED_Z.create(block, textureMapping, modelOutput));
        return MultiVariantGenerator.dispatch(block)
            .with(
                PropertyDispatch.initial(BlockStateProperties.AXIS)
                    .select(Direction.Axis.X, multivariant)
                    .select(Direction.Axis.Y, multivariant1)
                    .select(Direction.Axis.Z, multivariant2)
            );
    }

    public static BlockModelDefinitionGenerator createAxisAlignedPillarBlock(Block block, MultiVariant variants) {
        return MultiVariantGenerator.dispatch(block, variants).with(createRotatedPillar());
    }

    public void createAxisAlignedPillarBlockCustomModel(Block block, MultiVariant variants) {
        this.blockStateOutput.accept(createAxisAlignedPillarBlock(block, variants));
    }

    public void createAxisAlignedPillarBlock(Block axisAlignedPillarBlock, TexturedModel.Provider provider) {
        MultiVariant multivariant = plainVariant(provider.create(axisAlignedPillarBlock, this.modelOutput));
        this.blockStateOutput.accept(createAxisAlignedPillarBlock(axisAlignedPillarBlock, multivariant));
    }

    public void createHorizontallyRotatedBlock(Block horizontallyRotatedBlock, TexturedModel.Provider provider) {
        MultiVariant multivariant = plainVariant(provider.create(horizontallyRotatedBlock, this.modelOutput));
        this.blockStateOutput.accept(MultiVariantGenerator.dispatch(horizontallyRotatedBlock, multivariant).with(ROTATION_HORIZONTAL_FACING));
    }

    public static BlockModelDefinitionGenerator createRotatedPillarWithHorizontalVariant(Block block, MultiVariant variants, MultiVariant horizontalVariants) {
        return MultiVariantGenerator.dispatch(block)
            .with(
                PropertyDispatch.initial(BlockStateProperties.AXIS)
                    .select(Direction.Axis.Y, variants)
                    .select(Direction.Axis.Z, horizontalVariants.with(X_ROT_90))
                    .select(Direction.Axis.X, horizontalVariants.with(X_ROT_90).with(Y_ROT_90))
            );
    }

    public void createRotatedPillarWithHorizontalVariant(Block rotatedPillarBlock, TexturedModel.Provider modelProvider, TexturedModel.Provider horizontalModelProvider) {
        MultiVariant multivariant = plainVariant(modelProvider.create(rotatedPillarBlock, this.modelOutput));
        MultiVariant multivariant1 = plainVariant(horizontalModelProvider.create(rotatedPillarBlock, this.modelOutput));
        this.blockStateOutput.accept(createRotatedPillarWithHorizontalVariant(rotatedPillarBlock, multivariant, multivariant1));
    }

    public void createCreakingHeart(Block block) {
        MultiVariant multivariant = plainVariant(TexturedModel.COLUMN_ALT.create(block, this.modelOutput));
        MultiVariant multivariant1 = plainVariant(TexturedModel.COLUMN_HORIZONTAL_ALT.create(block, this.modelOutput));
        MultiVariant multivariant2 = plainVariant(this.createCreakingHeartModel(TexturedModel.COLUMN_ALT, block, "_awake"));
        MultiVariant multivariant3 = plainVariant(this.createCreakingHeartModel(TexturedModel.COLUMN_HORIZONTAL_ALT, block, "_awake"));
        MultiVariant multivariant4 = plainVariant(this.createCreakingHeartModel(TexturedModel.COLUMN_ALT, block, "_dormant"));
        MultiVariant multivariant5 = plainVariant(this.createCreakingHeartModel(TexturedModel.COLUMN_HORIZONTAL_ALT, block, "_dormant"));
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(block)
                    .with(
                        PropertyDispatch.initial(BlockStateProperties.AXIS, CreakingHeartBlock.STATE)
                            .select(Direction.Axis.Y, CreakingHeartState.UPROOTED, multivariant)
                            .select(Direction.Axis.Z, CreakingHeartState.UPROOTED, multivariant1.with(X_ROT_90))
                            .select(Direction.Axis.X, CreakingHeartState.UPROOTED, multivariant1.with(X_ROT_90).with(Y_ROT_90))
                            .select(Direction.Axis.Y, CreakingHeartState.DORMANT, multivariant4)
                            .select(Direction.Axis.Z, CreakingHeartState.DORMANT, multivariant5.with(X_ROT_90))
                            .select(Direction.Axis.X, CreakingHeartState.DORMANT, multivariant5.with(X_ROT_90).with(Y_ROT_90))
                            .select(Direction.Axis.Y, CreakingHeartState.AWAKE, multivariant2)
                            .select(Direction.Axis.Z, CreakingHeartState.AWAKE, multivariant3.with(X_ROT_90))
                            .select(Direction.Axis.X, CreakingHeartState.AWAKE, multivariant3.with(X_ROT_90).with(Y_ROT_90))
                    )
            );
    }

    public Identifier createCreakingHeartModel(TexturedModel.Provider modelProvider, Block block, String suffix) {
        return modelProvider.updateTexture(
                p_465434_ -> p_465434_.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(block, suffix))
                    .put(TextureSlot.END, TextureMapping.getBlockTexture(block, "_top" + suffix))
            )
            .createWithSuffix(block, suffix, this.modelOutput);
    }

    public Identifier createSuffixedVariant(Block block, String suffix, ModelTemplate modelTemplate, Function<Identifier, TextureMapping> textureMappingGetter) {
        return modelTemplate.createWithSuffix(block, suffix, textureMappingGetter.apply(TextureMapping.getBlockTexture(block, suffix)), this.modelOutput);
    }

    public static BlockModelDefinitionGenerator createPressurePlate(Block block, MultiVariant unpowered, MultiVariant powered) {
        return MultiVariantGenerator.dispatch(block).with(createBooleanModelDispatch(BlockStateProperties.POWERED, powered, unpowered));
    }

    public static BlockModelDefinitionGenerator createSlab(Block block, MultiVariant bottom, MultiVariant top, MultiVariant doubleVariants) {
        return MultiVariantGenerator.dispatch(block)
            .with(
                PropertyDispatch.initial(BlockStateProperties.SLAB_TYPE)
                    .select(SlabType.BOTTOM, bottom)
                    .select(SlabType.TOP, top)
                    .select(SlabType.DOUBLE, doubleVariants)
            );
    }

    public void createTrivialCube(Block block) {
        this.createTrivialBlock(block, TexturedModel.CUBE);
    }

    public void createTrivialBlock(Block block, TexturedModel.Provider provider) {
        this.blockStateOutput.accept(createSimpleBlock(block, plainVariant(provider.create(block, this.modelOutput))));
    }

    public void createTintedLeaves(Block block, TexturedModel.Provider provider, int tint) {
        Identifier identifier = provider.create(block, this.modelOutput);
        this.blockStateOutput.accept(createSimpleBlock(block, plainVariant(identifier)));
        this.registerSimpleTintedItemModel(block, identifier, ItemModelUtils.constantTint(tint));
    }

    public void createVine() {
        this.createMultifaceBlockStates(Blocks.VINE);
        Identifier identifier = this.createFlatItemModelWithBlockTexture(Items.VINE, Blocks.VINE);
        this.registerSimpleTintedItemModel(Blocks.VINE, identifier, ItemModelUtils.constantTint(-12012264));
    }

    public void createItemWithGrassTint(Block block) {
        Identifier identifier = this.createFlatItemModelWithBlockTexture(block.asItem(), block);
        this.registerSimpleTintedItemModel(block, identifier, new GrassColorSource());
    }

    public BlockModelGenerators.BlockFamilyProvider family(Block block) {
        TexturedModel texturedmodel = TEXTURED_MODELS.getOrDefault(block, TexturedModel.CUBE.get(block));
        return new BlockModelGenerators.BlockFamilyProvider(texturedmodel.getMapping()).fullBlock(block, texturedmodel.getTemplate());
    }

    public void createHangingSign(Block particleBlock, Block hangingSignBlock, Block wallHangingSignBlock) {
        MultiVariant multivariant = this.createParticleOnlyBlockModel(hangingSignBlock, particleBlock);
        this.blockStateOutput.accept(createSimpleBlock(hangingSignBlock, multivariant));
        this.blockStateOutput.accept(createSimpleBlock(wallHangingSignBlock, multivariant));
        this.registerSimpleFlatItemModel(hangingSignBlock.asItem());
    }

    public void createDoor(Block doorBlock) {
        TextureMapping texturemapping = TextureMapping.door(doorBlock);
        MultiVariant multivariant = plainVariant(ModelTemplates.DOOR_BOTTOM_LEFT.create(doorBlock, texturemapping, this.modelOutput));
        MultiVariant multivariant1 = plainVariant(ModelTemplates.DOOR_BOTTOM_LEFT_OPEN.create(doorBlock, texturemapping, this.modelOutput));
        MultiVariant multivariant2 = plainVariant(ModelTemplates.DOOR_BOTTOM_RIGHT.create(doorBlock, texturemapping, this.modelOutput));
        MultiVariant multivariant3 = plainVariant(ModelTemplates.DOOR_BOTTOM_RIGHT_OPEN.create(doorBlock, texturemapping, this.modelOutput));
        MultiVariant multivariant4 = plainVariant(ModelTemplates.DOOR_TOP_LEFT.create(doorBlock, texturemapping, this.modelOutput));
        MultiVariant multivariant5 = plainVariant(ModelTemplates.DOOR_TOP_LEFT_OPEN.create(doorBlock, texturemapping, this.modelOutput));
        MultiVariant multivariant6 = plainVariant(ModelTemplates.DOOR_TOP_RIGHT.create(doorBlock, texturemapping, this.modelOutput));
        MultiVariant multivariant7 = plainVariant(ModelTemplates.DOOR_TOP_RIGHT_OPEN.create(doorBlock, texturemapping, this.modelOutput));
        this.registerSimpleFlatItemModel(doorBlock.asItem());
        this.blockStateOutput
            .accept(
                createDoor(doorBlock, multivariant, multivariant1, multivariant2, multivariant3, multivariant4, multivariant5, multivariant6, multivariant7)
            );
    }

    public void copyDoorModel(Block doorBlock, Block sourceBlock) {
        MultiVariant multivariant = plainVariant(ModelTemplates.DOOR_BOTTOM_LEFT.getDefaultModelLocation(doorBlock));
        MultiVariant multivariant1 = plainVariant(ModelTemplates.DOOR_BOTTOM_LEFT_OPEN.getDefaultModelLocation(doorBlock));
        MultiVariant multivariant2 = plainVariant(ModelTemplates.DOOR_BOTTOM_RIGHT.getDefaultModelLocation(doorBlock));
        MultiVariant multivariant3 = plainVariant(ModelTemplates.DOOR_BOTTOM_RIGHT_OPEN.getDefaultModelLocation(doorBlock));
        MultiVariant multivariant4 = plainVariant(ModelTemplates.DOOR_TOP_LEFT.getDefaultModelLocation(doorBlock));
        MultiVariant multivariant5 = plainVariant(ModelTemplates.DOOR_TOP_LEFT_OPEN.getDefaultModelLocation(doorBlock));
        MultiVariant multivariant6 = plainVariant(ModelTemplates.DOOR_TOP_RIGHT.getDefaultModelLocation(doorBlock));
        MultiVariant multivariant7 = plainVariant(ModelTemplates.DOOR_TOP_RIGHT_OPEN.getDefaultModelLocation(doorBlock));
        this.itemModelOutput.copy(doorBlock.asItem(), sourceBlock.asItem());
        this.blockStateOutput
            .accept(
                createDoor(sourceBlock, multivariant, multivariant1, multivariant2, multivariant3, multivariant4, multivariant5, multivariant6, multivariant7)
            );
    }

    public void createOrientableTrapdoor(Block orientableTrapdoorBlock) {
        TextureMapping texturemapping = TextureMapping.defaultTexture(orientableTrapdoorBlock);
        MultiVariant multivariant = plainVariant(ModelTemplates.ORIENTABLE_TRAPDOOR_TOP.create(orientableTrapdoorBlock, texturemapping, this.modelOutput));
        Identifier identifier = ModelTemplates.ORIENTABLE_TRAPDOOR_BOTTOM.create(orientableTrapdoorBlock, texturemapping, this.modelOutput);
        MultiVariant multivariant1 = plainVariant(ModelTemplates.ORIENTABLE_TRAPDOOR_OPEN.create(orientableTrapdoorBlock, texturemapping, this.modelOutput));
        this.blockStateOutput.accept(createOrientableTrapdoor(orientableTrapdoorBlock, multivariant, plainVariant(identifier), multivariant1));
        this.registerSimpleItemModel(orientableTrapdoorBlock, identifier);
    }

    public void createTrapdoor(Block trapdoorBlock) {
        TextureMapping texturemapping = TextureMapping.defaultTexture(trapdoorBlock);
        MultiVariant multivariant = plainVariant(ModelTemplates.TRAPDOOR_TOP.create(trapdoorBlock, texturemapping, this.modelOutput));
        Identifier identifier = ModelTemplates.TRAPDOOR_BOTTOM.create(trapdoorBlock, texturemapping, this.modelOutput);
        MultiVariant multivariant1 = plainVariant(ModelTemplates.TRAPDOOR_OPEN.create(trapdoorBlock, texturemapping, this.modelOutput));
        this.blockStateOutput.accept(createTrapdoor(trapdoorBlock, multivariant, plainVariant(identifier), multivariant1));
        this.registerSimpleItemModel(trapdoorBlock, identifier);
    }

    public void copyTrapdoorModel(Block trapdoorBlock, Block sourceBlock) {
        MultiVariant multivariant = plainVariant(ModelTemplates.TRAPDOOR_TOP.getDefaultModelLocation(trapdoorBlock));
        MultiVariant multivariant1 = plainVariant(ModelTemplates.TRAPDOOR_BOTTOM.getDefaultModelLocation(trapdoorBlock));
        MultiVariant multivariant2 = plainVariant(ModelTemplates.TRAPDOOR_OPEN.getDefaultModelLocation(trapdoorBlock));
        this.itemModelOutput.copy(trapdoorBlock.asItem(), sourceBlock.asItem());
        this.blockStateOutput.accept(createTrapdoor(sourceBlock, multivariant, multivariant1, multivariant2));
    }

    public void createBigDripLeafBlock() {
        MultiVariant multivariant = plainVariant(ModelLocationUtils.getModelLocation(Blocks.BIG_DRIPLEAF));
        MultiVariant multivariant1 = plainVariant(ModelLocationUtils.getModelLocation(Blocks.BIG_DRIPLEAF, "_partial_tilt"));
        MultiVariant multivariant2 = plainVariant(ModelLocationUtils.getModelLocation(Blocks.BIG_DRIPLEAF, "_full_tilt"));
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(Blocks.BIG_DRIPLEAF)
                    .with(
                        PropertyDispatch.initial(BlockStateProperties.TILT)
                            .select(Tilt.NONE, multivariant)
                            .select(Tilt.UNSTABLE, multivariant)
                            .select(Tilt.PARTIAL, multivariant1)
                            .select(Tilt.FULL, multivariant2)
                    )
                    .with(ROTATION_HORIZONTAL_FACING)
            );
    }

    public BlockModelGenerators.WoodProvider woodProvider(Block logBlock) {
        return new BlockModelGenerators.WoodProvider(TextureMapping.logColumn(logBlock));
    }

    public void createNonTemplateModelBlock(Block block) {
        this.createNonTemplateModelBlock(block, block);
    }

    public void createNonTemplateModelBlock(Block block, Block modelBlock) {
        this.blockStateOutput.accept(createSimpleBlock(block, plainVariant(ModelLocationUtils.getModelLocation(modelBlock))));
    }

    public void createCrossBlockWithDefaultItem(Block block, BlockModelGenerators.PlantType plantType) {
        this.registerSimpleItemModel(block.asItem(), plantType.createItemModel(this, block));
        this.createCrossBlock(block, plantType);
    }

    public void createCrossBlockWithDefaultItem(Block block, BlockModelGenerators.PlantType plantType, TextureMapping textureMapping) {
        this.registerSimpleFlatItemModel(block);
        this.createCrossBlock(block, plantType, textureMapping);
    }

    public void createCrossBlock(Block block, BlockModelGenerators.PlantType plantType) {
        TextureMapping texturemapping = plantType.getTextureMapping(block);
        this.createCrossBlock(block, plantType, texturemapping);
    }

    public void createCrossBlock(Block block, BlockModelGenerators.PlantType plantType, TextureMapping textureMapping) {
        MultiVariant multivariant = plainVariant(plantType.getCross().create(block, textureMapping, this.modelOutput));
        this.blockStateOutput.accept(createSimpleBlock(block, multivariant));
    }

    public void createCrossBlock(Block block, BlockModelGenerators.PlantType plantType, Property<Integer> ageProperty, int... possibleValues) {
        if (ageProperty.getPossibleValues().size() != possibleValues.length) {
            throw new IllegalArgumentException("missing values for property: " + ageProperty);
        } else {
            this.registerSimpleFlatItemModel(block.asItem());
            this.blockStateOutput.accept(MultiVariantGenerator.dispatch(block).with(PropertyDispatch.initial(ageProperty).generate(p_465375_ -> {
                String s = "_stage" + possibleValues[p_465375_];
                TextureMapping texturemapping = TextureMapping.cross(TextureMapping.getBlockTexture(block, s));
                return plainVariant(plantType.getCross().createWithSuffix(block, s, texturemapping, this.modelOutput));
            })));
        }
    }

    public void createPlantWithDefaultItem(Block block, Block pottedBlock, BlockModelGenerators.PlantType plantType) {
        this.registerSimpleItemModel(block.asItem(), plantType.createItemModel(this, block));
        this.createPlant(block, pottedBlock, plantType);
    }

    public void createPlant(Block block, Block pottedBlock, BlockModelGenerators.PlantType plantType) {
        this.createCrossBlock(block, plantType);
        TextureMapping texturemapping = plantType.getPlantTextureMapping(block);
        MultiVariant multivariant = plainVariant(plantType.getCrossPot().create(pottedBlock, texturemapping, this.modelOutput));
        this.blockStateOutput.accept(createSimpleBlock(pottedBlock, multivariant));
    }

    public void createCoralFans(Block coralFanBlock, Block coralWallFanBlock) {
        TexturedModel texturedmodel = TexturedModel.CORAL_FAN.get(coralFanBlock);
        MultiVariant multivariant = plainVariant(texturedmodel.create(coralFanBlock, this.modelOutput));
        this.blockStateOutput.accept(createSimpleBlock(coralFanBlock, multivariant));
        MultiVariant multivariant1 = plainVariant(ModelTemplates.CORAL_WALL_FAN.create(coralWallFanBlock, texturedmodel.getMapping(), this.modelOutput));
        this.blockStateOutput.accept(MultiVariantGenerator.dispatch(coralWallFanBlock, multivariant1).with(ROTATION_HORIZONTAL_FACING));
        this.registerSimpleFlatItemModel(coralFanBlock);
    }

    public void createStems(Block unattachedStemBlock, Block attachedStemBlock) {
        this.registerSimpleFlatItemModel(unattachedStemBlock.asItem());
        TextureMapping texturemapping = TextureMapping.stem(unattachedStemBlock);
        TextureMapping texturemapping1 = TextureMapping.attachedStem(unattachedStemBlock, attachedStemBlock);
        MultiVariant multivariant = plainVariant(ModelTemplates.ATTACHED_STEM.create(attachedStemBlock, texturemapping1, this.modelOutput));
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(attachedStemBlock, multivariant)
                    .with(
                        PropertyDispatch.modify(BlockStateProperties.HORIZONTAL_FACING)
                            .select(Direction.WEST, NOP)
                            .select(Direction.SOUTH, Y_ROT_270)
                            .select(Direction.NORTH, Y_ROT_90)
                            .select(Direction.EAST, Y_ROT_180)
                    )
            );
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(unattachedStemBlock)
                    .with(
                        PropertyDispatch.initial(BlockStateProperties.AGE_7)
                            .generate(p_465366_ -> plainVariant(ModelTemplates.STEMS[p_465366_].create(unattachedStemBlock, texturemapping, this.modelOutput)))
                    )
            );
    }

    public void createPitcherPlant() {
        Block block = Blocks.PITCHER_PLANT;
        this.registerSimpleFlatItemModel(block.asItem());
        MultiVariant multivariant = plainVariant(ModelLocationUtils.getModelLocation(block, "_top"));
        MultiVariant multivariant1 = plainVariant(ModelLocationUtils.getModelLocation(block, "_bottom"));
        this.createDoubleBlock(block, multivariant, multivariant1);
    }

    public void createPitcherCrop() {
        Block block = Blocks.PITCHER_CROP;
        this.registerSimpleFlatItemModel(block.asItem());
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(block)
                    .with(PropertyDispatch.initial(PitcherCropBlock.AGE, BlockStateProperties.DOUBLE_BLOCK_HALF).generate((p_465404_, p_465405_) -> {
                        return switch (p_465405_) {
                            case UPPER -> plainVariant(ModelLocationUtils.getModelLocation(block, "_top_stage_" + p_465404_));
                            case LOWER -> plainVariant(ModelLocationUtils.getModelLocation(block, "_bottom_stage_" + p_465404_));
                        };
                    }))
            );
    }

    public void createCoral(
        Block coralBlock, Block deadCoralBlock, Block coralFullBlock, Block deadCoralFullBlock, Block coralFanBlock, Block deadCoralFanBlock, Block coralWallFanBlock, Block deadCoralWallFanBlock
    ) {
        this.createCrossBlockWithDefaultItem(coralBlock, BlockModelGenerators.PlantType.NOT_TINTED);
        this.createCrossBlockWithDefaultItem(deadCoralBlock, BlockModelGenerators.PlantType.NOT_TINTED);
        this.createTrivialCube(coralFullBlock);
        this.createTrivialCube(deadCoralFullBlock);
        this.createCoralFans(coralFanBlock, coralWallFanBlock);
        this.createCoralFans(deadCoralFanBlock, deadCoralWallFanBlock);
    }

    public void createDoublePlant(Block block, BlockModelGenerators.PlantType plantType) {
        MultiVariant multivariant = plainVariant(this.createSuffixedVariant(block, "_top", plantType.getCross(), TextureMapping::cross));
        MultiVariant multivariant1 = plainVariant(this.createSuffixedVariant(block, "_bottom", plantType.getCross(), TextureMapping::cross));
        this.createDoubleBlock(block, multivariant, multivariant1);
    }

    public void createDoublePlantWithDefaultItem(Block block, BlockModelGenerators.PlantType plantType) {
        this.registerSimpleFlatItemModel(block, "_top");
        this.createDoublePlant(block, plantType);
    }

    public void createTintedDoublePlant(Block block) {
        Identifier identifier = this.createFlatItemModelWithBlockTexture(block.asItem(), block, "_top");
        this.registerSimpleTintedItemModel(block, identifier, new GrassColorSource());
        this.createDoublePlant(block, BlockModelGenerators.PlantType.TINTED);
    }

    public void createSunflower() {
        this.registerSimpleFlatItemModel(Blocks.SUNFLOWER, "_front");
        MultiVariant multivariant = plainVariant(ModelLocationUtils.getModelLocation(Blocks.SUNFLOWER, "_top"));
        MultiVariant multivariant1 = plainVariant(
            this.createSuffixedVariant(Blocks.SUNFLOWER, "_bottom", BlockModelGenerators.PlantType.NOT_TINTED.getCross(), TextureMapping::cross)
        );
        this.createDoubleBlock(Blocks.SUNFLOWER, multivariant, multivariant1);
    }

    public void createTallSeagrass() {
        MultiVariant multivariant = plainVariant(
            this.createSuffixedVariant(Blocks.TALL_SEAGRASS, "_top", ModelTemplates.SEAGRASS, TextureMapping::defaultTexture)
        );
        MultiVariant multivariant1 = plainVariant(
            this.createSuffixedVariant(Blocks.TALL_SEAGRASS, "_bottom", ModelTemplates.SEAGRASS, TextureMapping::defaultTexture)
        );
        this.createDoubleBlock(Blocks.TALL_SEAGRASS, multivariant, multivariant1);
    }

    public void createSmallDripleaf() {
        MultiVariant multivariant = plainVariant(ModelLocationUtils.getModelLocation(Blocks.SMALL_DRIPLEAF, "_top"));
        MultiVariant multivariant1 = plainVariant(ModelLocationUtils.getModelLocation(Blocks.SMALL_DRIPLEAF, "_bottom"));
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(Blocks.SMALL_DRIPLEAF)
                    .with(
                        PropertyDispatch.initial(BlockStateProperties.DOUBLE_BLOCK_HALF)
                            .select(DoubleBlockHalf.LOWER, multivariant1)
                            .select(DoubleBlockHalf.UPPER, multivariant)
                    )
                    .with(ROTATION_HORIZONTAL_FACING)
            );
    }

    public void createDoubleBlock(Block block, MultiVariant lower, MultiVariant upper) {
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(block)
                    .with(
                        PropertyDispatch.initial(BlockStateProperties.DOUBLE_BLOCK_HALF)
                            .select(DoubleBlockHalf.LOWER, upper)
                            .select(DoubleBlockHalf.UPPER, lower)
                    )
            );
    }

    public void createPassiveRail(Block railBlock) {
        TextureMapping texturemapping = TextureMapping.rail(railBlock);
        TextureMapping texturemapping1 = TextureMapping.rail(TextureMapping.getBlockTexture(railBlock, "_corner"));
        MultiVariant multivariant = plainVariant(ModelTemplates.RAIL_FLAT.create(railBlock, texturemapping, this.modelOutput));
        MultiVariant multivariant1 = plainVariant(ModelTemplates.RAIL_CURVED.create(railBlock, texturemapping1, this.modelOutput));
        MultiVariant multivariant2 = plainVariant(ModelTemplates.RAIL_RAISED_NE.create(railBlock, texturemapping, this.modelOutput));
        MultiVariant multivariant3 = plainVariant(ModelTemplates.RAIL_RAISED_SW.create(railBlock, texturemapping, this.modelOutput));
        this.registerSimpleFlatItemModel(railBlock);
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(railBlock)
                    .with(
                        PropertyDispatch.initial(BlockStateProperties.RAIL_SHAPE)
                            .select(RailShape.NORTH_SOUTH, multivariant)
                            .select(RailShape.EAST_WEST, multivariant.with(Y_ROT_90))
                            .select(RailShape.ASCENDING_EAST, multivariant2.with(Y_ROT_90))
                            .select(RailShape.ASCENDING_WEST, multivariant3.with(Y_ROT_90))
                            .select(RailShape.ASCENDING_NORTH, multivariant2)
                            .select(RailShape.ASCENDING_SOUTH, multivariant3)
                            .select(RailShape.SOUTH_EAST, multivariant1)
                            .select(RailShape.SOUTH_WEST, multivariant1.with(Y_ROT_90))
                            .select(RailShape.NORTH_WEST, multivariant1.with(Y_ROT_180))
                            .select(RailShape.NORTH_EAST, multivariant1.with(Y_ROT_270))
                    )
            );
    }

    public void createActiveRail(Block railBlock) {
        MultiVariant multivariant = plainVariant(this.createSuffixedVariant(railBlock, "", ModelTemplates.RAIL_FLAT, TextureMapping::rail));
        MultiVariant multivariant1 = plainVariant(this.createSuffixedVariant(railBlock, "", ModelTemplates.RAIL_RAISED_NE, TextureMapping::rail));
        MultiVariant multivariant2 = plainVariant(this.createSuffixedVariant(railBlock, "", ModelTemplates.RAIL_RAISED_SW, TextureMapping::rail));
        MultiVariant multivariant3 = plainVariant(this.createSuffixedVariant(railBlock, "_on", ModelTemplates.RAIL_FLAT, TextureMapping::rail));
        MultiVariant multivariant4 = plainVariant(this.createSuffixedVariant(railBlock, "_on", ModelTemplates.RAIL_RAISED_NE, TextureMapping::rail));
        MultiVariant multivariant5 = plainVariant(this.createSuffixedVariant(railBlock, "_on", ModelTemplates.RAIL_RAISED_SW, TextureMapping::rail));
        this.registerSimpleFlatItemModel(railBlock);
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(railBlock)
                    .with(PropertyDispatch.initial(BlockStateProperties.POWERED, BlockStateProperties.RAIL_SHAPE_STRAIGHT).generate((p_403947_, p_403948_) -> {
                        return switch (p_403948_) {
                            case NORTH_SOUTH -> p_403947_ ? multivariant3 : multivariant;
                            case EAST_WEST -> (p_403947_ ? multivariant3 : multivariant).with(Y_ROT_90);
                            case ASCENDING_EAST -> (p_403947_ ? multivariant4 : multivariant1).with(Y_ROT_90);
                            case ASCENDING_WEST -> (p_403947_ ? multivariant5 : multivariant2).with(Y_ROT_90);
                            case ASCENDING_NORTH -> p_403947_ ? multivariant4 : multivariant1;
                            case ASCENDING_SOUTH -> p_403947_ ? multivariant5 : multivariant2;
                            default -> throw new UnsupportedOperationException("Fix you generator!");
                        };
                    }))
            );
    }

    public void createAirLikeBlock(Block airLikeBlock, Item particleItem) {
        MultiVariant multivariant = plainVariant(ModelTemplates.PARTICLE_ONLY.create(airLikeBlock, TextureMapping.particleFromItem(particleItem), this.modelOutput));
        this.blockStateOutput.accept(createSimpleBlock(airLikeBlock, multivariant));
    }

    public void createAirLikeBlock(Block airLikeBlock, Identifier particleTexture) {
        MultiVariant multivariant = plainVariant(ModelTemplates.PARTICLE_ONLY.create(airLikeBlock, TextureMapping.particle(particleTexture), this.modelOutput));
        this.blockStateOutput.accept(createSimpleBlock(airLikeBlock, multivariant));
    }

    public MultiVariant createParticleOnlyBlockModel(Block block, Block particleBlock) {
        return plainVariant(ModelTemplates.PARTICLE_ONLY.create(block, TextureMapping.particle(particleBlock), this.modelOutput));
    }

    public void createParticleOnlyBlock(Block block, Block particleBlock) {
        this.blockStateOutput.accept(createSimpleBlock(block, this.createParticleOnlyBlockModel(block, particleBlock)));
    }

    public void createParticleOnlyBlock(Block block) {
        this.createParticleOnlyBlock(block, block);
    }

    public void createFullAndCarpetBlocks(Block fullBlock, Block carpetBlock) {
        this.createTrivialCube(fullBlock);
        MultiVariant multivariant = plainVariant(TexturedModel.CARPET.get(fullBlock).create(carpetBlock, this.modelOutput));
        this.blockStateOutput.accept(createSimpleBlock(carpetBlock, multivariant));
    }

    public void createLeafLitter(Block block) {
        MultiVariant multivariant = plainVariant(TexturedModel.LEAF_LITTER_1.create(block, this.modelOutput));
        MultiVariant multivariant1 = plainVariant(TexturedModel.LEAF_LITTER_2.create(block, this.modelOutput));
        MultiVariant multivariant2 = plainVariant(TexturedModel.LEAF_LITTER_3.create(block, this.modelOutput));
        MultiVariant multivariant3 = plainVariant(TexturedModel.LEAF_LITTER_4.create(block, this.modelOutput));
        this.registerSimpleFlatItemModel(block.asItem());
        this.createSegmentedBlock(
            block,
            multivariant,
            LEAF_LITTER_MODEL_1_SEGMENT_CONDITION,
            multivariant1,
            LEAF_LITTER_MODEL_2_SEGMENT_CONDITION,
            multivariant2,
            LEAF_LITTER_MODEL_3_SEGMENT_CONDITION,
            multivariant3,
            LEAF_LITTER_MODEL_4_SEGMENT_CONDITION
        );
    }

    public void createFlowerBed(Block flowerBedBlock) {
        MultiVariant multivariant = plainVariant(TexturedModel.FLOWERBED_1.create(flowerBedBlock, this.modelOutput));
        MultiVariant multivariant1 = plainVariant(TexturedModel.FLOWERBED_2.create(flowerBedBlock, this.modelOutput));
        MultiVariant multivariant2 = plainVariant(TexturedModel.FLOWERBED_3.create(flowerBedBlock, this.modelOutput));
        MultiVariant multivariant3 = plainVariant(TexturedModel.FLOWERBED_4.create(flowerBedBlock, this.modelOutput));
        this.registerSimpleFlatItemModel(flowerBedBlock.asItem());
        this.createSegmentedBlock(
            flowerBedBlock,
            multivariant,
            FLOWER_BED_MODEL_1_SEGMENT_CONDITION,
            multivariant1,
            FLOWER_BED_MODEL_2_SEGMENT_CONDITION,
            multivariant2,
            FLOWER_BED_MODEL_3_SEGMENT_CONDITION,
            multivariant3,
            FLOWER_BED_MODEL_4_SEGMENT_CONDITION
        );
    }

    public void createSegmentedBlock(
        Block block,
        MultiVariant segment1Variant,
        Function<ConditionBuilder, ConditionBuilder> segment1Condition,
        MultiVariant segment2Variant,
        Function<ConditionBuilder, ConditionBuilder> segment2Condition,
        MultiVariant segment3Variant,
        Function<ConditionBuilder, ConditionBuilder> segment3Condition,
        MultiVariant segment4Variant,
        Function<ConditionBuilder, ConditionBuilder> segment4Condition
    ) {
        this.blockStateOutput
            .accept(
                MultiPartGenerator.multiPart(block)
                    .with(segment1Condition.apply(condition().term(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH)), segment1Variant)
                    .with(segment1Condition.apply(condition().term(BlockStateProperties.HORIZONTAL_FACING, Direction.EAST)), segment1Variant.with(Y_ROT_90))
                    .with(segment1Condition.apply(condition().term(BlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH)), segment1Variant.with(Y_ROT_180))
                    .with(segment1Condition.apply(condition().term(BlockStateProperties.HORIZONTAL_FACING, Direction.WEST)), segment1Variant.with(Y_ROT_270))
                    .with(segment2Condition.apply(condition().term(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH)), segment2Variant)
                    .with(segment2Condition.apply(condition().term(BlockStateProperties.HORIZONTAL_FACING, Direction.EAST)), segment2Variant.with(Y_ROT_90))
                    .with(segment2Condition.apply(condition().term(BlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH)), segment2Variant.with(Y_ROT_180))
                    .with(segment2Condition.apply(condition().term(BlockStateProperties.HORIZONTAL_FACING, Direction.WEST)), segment2Variant.with(Y_ROT_270))
                    .with(segment3Condition.apply(condition().term(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH)), segment3Variant)
                    .with(segment3Condition.apply(condition().term(BlockStateProperties.HORIZONTAL_FACING, Direction.EAST)), segment3Variant.with(Y_ROT_90))
                    .with(segment3Condition.apply(condition().term(BlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH)), segment3Variant.with(Y_ROT_180))
                    .with(segment3Condition.apply(condition().term(BlockStateProperties.HORIZONTAL_FACING, Direction.WEST)), segment3Variant.with(Y_ROT_270))
                    .with(segment4Condition.apply(condition().term(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH)), segment4Variant)
                    .with(segment4Condition.apply(condition().term(BlockStateProperties.HORIZONTAL_FACING, Direction.EAST)), segment4Variant.with(Y_ROT_90))
                    .with(segment4Condition.apply(condition().term(BlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH)), segment4Variant.with(Y_ROT_180))
                    .with(segment4Condition.apply(condition().term(BlockStateProperties.HORIZONTAL_FACING, Direction.WEST)), segment4Variant.with(Y_ROT_270))
            );
    }

    public void createColoredBlockWithRandomRotations(TexturedModel.Provider modelProvider, Block... coloredBlocks) {
        for (Block block : coloredBlocks) {
            Variant variant = plainModel(modelProvider.create(block, this.modelOutput));
            this.blockStateOutput.accept(MultiVariantGenerator.dispatch(block, createRotatedVariants(variant)));
        }
    }

    public void createColoredBlockWithStateRotations(TexturedModel.Provider modelProvider, Block... coloredBlocks) {
        for (Block block : coloredBlocks) {
            MultiVariant multivariant = plainVariant(modelProvider.create(block, this.modelOutput));
            this.blockStateOutput.accept(MultiVariantGenerator.dispatch(block, multivariant).with(ROTATION_HORIZONTAL_FACING_ALT));
        }
    }

    public void createGlassBlocks(Block glassBlock, Block paneBlock) {
        this.createTrivialCube(glassBlock);
        TextureMapping texturemapping = TextureMapping.pane(glassBlock, paneBlock);
        MultiVariant multivariant = plainVariant(ModelTemplates.STAINED_GLASS_PANE_POST.create(paneBlock, texturemapping, this.modelOutput));
        MultiVariant multivariant1 = plainVariant(ModelTemplates.STAINED_GLASS_PANE_SIDE.create(paneBlock, texturemapping, this.modelOutput));
        MultiVariant multivariant2 = plainVariant(ModelTemplates.STAINED_GLASS_PANE_SIDE_ALT.create(paneBlock, texturemapping, this.modelOutput));
        MultiVariant multivariant3 = plainVariant(ModelTemplates.STAINED_GLASS_PANE_NOSIDE.create(paneBlock, texturemapping, this.modelOutput));
        MultiVariant multivariant4 = plainVariant(ModelTemplates.STAINED_GLASS_PANE_NOSIDE_ALT.create(paneBlock, texturemapping, this.modelOutput));
        Item item = paneBlock.asItem();
        this.registerSimpleItemModel(item, this.createFlatItemModelWithBlockTexture(item, glassBlock));
        this.blockStateOutput
            .accept(
                MultiPartGenerator.multiPart(paneBlock)
                    .with(multivariant)
                    .with(condition().term(BlockStateProperties.NORTH, true), multivariant1)
                    .with(condition().term(BlockStateProperties.EAST, true), multivariant1.with(Y_ROT_90))
                    .with(condition().term(BlockStateProperties.SOUTH, true), multivariant2)
                    .with(condition().term(BlockStateProperties.WEST, true), multivariant2.with(Y_ROT_90))
                    .with(condition().term(BlockStateProperties.NORTH, false), multivariant3)
                    .with(condition().term(BlockStateProperties.EAST, false), multivariant4)
                    .with(condition().term(BlockStateProperties.SOUTH, false), multivariant4.with(Y_ROT_90))
                    .with(condition().term(BlockStateProperties.WEST, false), multivariant3.with(Y_ROT_270))
            );
    }

    public void createCommandBlock(Block commandBlock) {
        TextureMapping texturemapping = TextureMapping.commandBlock(commandBlock);
        MultiVariant multivariant = plainVariant(ModelTemplates.COMMAND_BLOCK.create(commandBlock, texturemapping, this.modelOutput));
        MultiVariant multivariant1 = plainVariant(
            this.createSuffixedVariant(
                commandBlock, "_conditional", ModelTemplates.COMMAND_BLOCK, p_465427_ -> texturemapping.copyAndUpdate(TextureSlot.SIDE, p_465427_)
            )
        );
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(commandBlock)
                    .with(createBooleanModelDispatch(BlockStateProperties.CONDITIONAL, multivariant1, multivariant))
                    .with(ROTATION_FACING)
            );
    }

    public void createAnvil(Block anvilBlock) {
        MultiVariant multivariant = plainVariant(TexturedModel.ANVIL.create(anvilBlock, this.modelOutput));
        this.blockStateOutput.accept(createSimpleBlock(anvilBlock, multivariant).with(ROTATION_HORIZONTAL_FACING_ALT));
    }

    public static MultiVariant createBambooModels(int age) {
        String s = "_age" + age;
        return new MultiVariant(
            WeightedList.of(
                IntStream.range(1, 5)
                    .mapToObj(p_465409_ -> new Weighted<>(plainModel(ModelLocationUtils.getModelLocation(Blocks.BAMBOO, p_465409_ + s)), 1))
                    .collect(Collectors.toList())
            )
        );
    }

    public void createBamboo() {
        this.blockStateOutput
            .accept(
                MultiPartGenerator.multiPart(Blocks.BAMBOO)
                    .with(condition().term(BlockStateProperties.AGE_1, 0), createBambooModels(0))
                    .with(condition().term(BlockStateProperties.AGE_1, 1), createBambooModels(1))
                    .with(
                        condition().term(BlockStateProperties.BAMBOO_LEAVES, BambooLeaves.SMALL),
                        plainVariant(ModelLocationUtils.getModelLocation(Blocks.BAMBOO, "_small_leaves"))
                    )
                    .with(
                        condition().term(BlockStateProperties.BAMBOO_LEAVES, BambooLeaves.LARGE),
                        plainVariant(ModelLocationUtils.getModelLocation(Blocks.BAMBOO, "_large_leaves"))
                    )
            );
    }

    public void createBarrel() {
        Identifier identifier = TextureMapping.getBlockTexture(Blocks.BARREL, "_top_open");
        MultiVariant multivariant = plainVariant(TexturedModel.CUBE_TOP_BOTTOM.create(Blocks.BARREL, this.modelOutput));
        MultiVariant multivariant1 = plainVariant(
            TexturedModel.CUBE_TOP_BOTTOM
                .get(Blocks.BARREL)
                .updateTextures(p_465411_ -> p_465411_.put(TextureSlot.TOP, identifier))
                .createWithSuffix(Blocks.BARREL, "_open", this.modelOutput)
        );
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(Blocks.BARREL)
                    .with(PropertyDispatch.initial(BlockStateProperties.OPEN).select(false, multivariant).select(true, multivariant1))
                    .with(ROTATIONS_COLUMN_WITH_FACING)
            );
    }

    public static <T extends Comparable<T>> PropertyDispatch<MultiVariant> createEmptyOrFullDispatch(
        Property<T> property, T threshold, MultiVariant full, MultiVariant empty
    ) {
        return PropertyDispatch.initial(property).generate(p_388058_ -> {
            boolean flag = p_388058_.compareTo(threshold) >= 0;
            return flag ? full : empty;
        });
    }

    public void createBeeNest(Block beeNestBlock, Function<Block, TextureMapping> textureMappingGetter) {
        TextureMapping texturemapping = textureMappingGetter.apply(beeNestBlock).copyForced(TextureSlot.SIDE, TextureSlot.PARTICLE);
        TextureMapping texturemapping1 = texturemapping.copyAndUpdate(TextureSlot.FRONT, TextureMapping.getBlockTexture(beeNestBlock, "_front_honey"));
        Identifier identifier = ModelTemplates.CUBE_ORIENTABLE_TOP_BOTTOM.createWithSuffix(beeNestBlock, "_empty", texturemapping, this.modelOutput);
        Identifier identifier1 = ModelTemplates.CUBE_ORIENTABLE_TOP_BOTTOM.createWithSuffix(beeNestBlock, "_honey", texturemapping1, this.modelOutput);
        this.itemModelOutput
            .accept(
                beeNestBlock.asItem(),
                ItemModelUtils.selectBlockItemProperty(
                    BeehiveBlock.HONEY_LEVEL, ItemModelUtils.plainModel(identifier), Map.of(5, ItemModelUtils.plainModel(identifier1))
                )
            );
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(beeNestBlock)
                    .with(createEmptyOrFullDispatch(BeehiveBlock.HONEY_LEVEL, 5, plainVariant(identifier1), plainVariant(identifier)))
                    .with(ROTATION_HORIZONTAL_FACING)
            );
    }

    public void createCropBlock(Block cropBlock, Property<Integer> ageProperty, int... ageToVisualStageMapping) {
        this.registerSimpleFlatItemModel(cropBlock.asItem());
        if (ageProperty.getPossibleValues().size() != ageToVisualStageMapping.length) {
            throw new IllegalArgumentException();
        } else {
            Int2ObjectMap<Identifier> int2objectmap = new Int2ObjectOpenHashMap<>();
            this.blockStateOutput
                .accept(
                    MultiVariantGenerator.dispatch(cropBlock)
                        .with(
                            PropertyDispatch.initial(ageProperty)
                                .generate(
                                    p_465386_ -> {
                                        int i = ageToVisualStageMapping[p_465386_];
                                        return plainVariant(
                                            int2objectmap.computeIfAbsent(
                                                i,
                                                p_465395_ -> this.createSuffixedVariant(
                                                    cropBlock, "_stage" + p_465395_, ModelTemplates.CROP, TextureMapping::crop
                                                )
                                            )
                                        );
                                    }
                                )
                        )
                );
        }
    }

    public void createBell() {
        MultiVariant multivariant = plainVariant(ModelLocationUtils.getModelLocation(Blocks.BELL, "_floor"));
        MultiVariant multivariant1 = plainVariant(ModelLocationUtils.getModelLocation(Blocks.BELL, "_ceiling"));
        MultiVariant multivariant2 = plainVariant(ModelLocationUtils.getModelLocation(Blocks.BELL, "_wall"));
        MultiVariant multivariant3 = plainVariant(ModelLocationUtils.getModelLocation(Blocks.BELL, "_between_walls"));
        this.registerSimpleFlatItemModel(Items.BELL);
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(Blocks.BELL)
                    .with(
                        PropertyDispatch.initial(BlockStateProperties.HORIZONTAL_FACING, BlockStateProperties.BELL_ATTACHMENT)
                            .select(Direction.NORTH, BellAttachType.FLOOR, multivariant)
                            .select(Direction.SOUTH, BellAttachType.FLOOR, multivariant.with(Y_ROT_180))
                            .select(Direction.EAST, BellAttachType.FLOOR, multivariant.with(Y_ROT_90))
                            .select(Direction.WEST, BellAttachType.FLOOR, multivariant.with(Y_ROT_270))
                            .select(Direction.NORTH, BellAttachType.CEILING, multivariant1)
                            .select(Direction.SOUTH, BellAttachType.CEILING, multivariant1.with(Y_ROT_180))
                            .select(Direction.EAST, BellAttachType.CEILING, multivariant1.with(Y_ROT_90))
                            .select(Direction.WEST, BellAttachType.CEILING, multivariant1.with(Y_ROT_270))
                            .select(Direction.NORTH, BellAttachType.SINGLE_WALL, multivariant2.with(Y_ROT_270))
                            .select(Direction.SOUTH, BellAttachType.SINGLE_WALL, multivariant2.with(Y_ROT_90))
                            .select(Direction.EAST, BellAttachType.SINGLE_WALL, multivariant2)
                            .select(Direction.WEST, BellAttachType.SINGLE_WALL, multivariant2.with(Y_ROT_180))
                            .select(Direction.SOUTH, BellAttachType.DOUBLE_WALL, multivariant3.with(Y_ROT_90))
                            .select(Direction.NORTH, BellAttachType.DOUBLE_WALL, multivariant3.with(Y_ROT_270))
                            .select(Direction.EAST, BellAttachType.DOUBLE_WALL, multivariant3)
                            .select(Direction.WEST, BellAttachType.DOUBLE_WALL, multivariant3.with(Y_ROT_180))
                    )
            );
    }

    public void createGrindstone() {
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(Blocks.GRINDSTONE, plainVariant(ModelLocationUtils.getModelLocation(Blocks.GRINDSTONE)))
                    .with(
                        PropertyDispatch.modify(BlockStateProperties.ATTACH_FACE, BlockStateProperties.HORIZONTAL_FACING)
                            .select(AttachFace.FLOOR, Direction.NORTH, NOP)
                            .select(AttachFace.FLOOR, Direction.EAST, Y_ROT_90)
                            .select(AttachFace.FLOOR, Direction.SOUTH, Y_ROT_180)
                            .select(AttachFace.FLOOR, Direction.WEST, Y_ROT_270)
                            .select(AttachFace.WALL, Direction.NORTH, X_ROT_90)
                            .select(AttachFace.WALL, Direction.EAST, X_ROT_90.then(Y_ROT_90))
                            .select(AttachFace.WALL, Direction.SOUTH, X_ROT_90.then(Y_ROT_180))
                            .select(AttachFace.WALL, Direction.WEST, X_ROT_90.then(Y_ROT_270))
                            .select(AttachFace.CEILING, Direction.SOUTH, X_ROT_180)
                            .select(AttachFace.CEILING, Direction.WEST, X_ROT_180.then(Y_ROT_90))
                            .select(AttachFace.CEILING, Direction.NORTH, X_ROT_180.then(Y_ROT_180))
                            .select(AttachFace.CEILING, Direction.EAST, X_ROT_180.then(Y_ROT_270))
                    )
            );
    }

    public void createFurnace(Block furnaceBlock, TexturedModel.Provider modelProvider) {
        MultiVariant multivariant = plainVariant(modelProvider.create(furnaceBlock, this.modelOutput));
        Identifier identifier = TextureMapping.getBlockTexture(furnaceBlock, "_front_on");
        MultiVariant multivariant1 = plainVariant(
            modelProvider.get(furnaceBlock)
                .updateTextures(p_465397_ -> p_465397_.put(TextureSlot.FRONT, identifier))
                .createWithSuffix(furnaceBlock, "_on", this.modelOutput)
        );
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(furnaceBlock)
                    .with(createBooleanModelDispatch(BlockStateProperties.LIT, multivariant1, multivariant))
                    .with(ROTATION_HORIZONTAL_FACING)
            );
    }

    public void createCampfires(Block... campfireBlocks) {
        MultiVariant multivariant = plainVariant(ModelLocationUtils.decorateBlockModelLocation("campfire_off"));

        for (Block block : campfireBlocks) {
            MultiVariant multivariant1 = plainVariant(ModelTemplates.CAMPFIRE.create(block, TextureMapping.campfire(block), this.modelOutput));
            this.registerSimpleFlatItemModel(block.asItem());
            this.blockStateOutput
                .accept(
                    MultiVariantGenerator.dispatch(block)
                        .with(createBooleanModelDispatch(BlockStateProperties.LIT, multivariant1, multivariant))
                        .with(ROTATION_HORIZONTAL_FACING_ALT)
                );
        }
    }

    public void createAzalea(Block azaleaBlock) {
        MultiVariant multivariant = plainVariant(ModelTemplates.AZALEA.create(azaleaBlock, TextureMapping.cubeTop(azaleaBlock), this.modelOutput));
        this.blockStateOutput.accept(createSimpleBlock(azaleaBlock, multivariant));
    }

    public void createPottedAzalea(Block pottedAzaleaBlock) {
        MultiVariant multivariant;
        if (pottedAzaleaBlock == Blocks.POTTED_FLOWERING_AZALEA) {
            multivariant = plainVariant(ModelTemplates.POTTED_FLOWERING_AZALEA.create(pottedAzaleaBlock, TextureMapping.pottedAzalea(pottedAzaleaBlock), this.modelOutput));
        } else {
            multivariant = plainVariant(ModelTemplates.POTTED_AZALEA.create(pottedAzaleaBlock, TextureMapping.pottedAzalea(pottedAzaleaBlock), this.modelOutput));
        }

        this.blockStateOutput.accept(createSimpleBlock(pottedAzaleaBlock, multivariant));
    }

    public void createBookshelf() {
        TextureMapping texturemapping = TextureMapping.column(
            TextureMapping.getBlockTexture(Blocks.BOOKSHELF), TextureMapping.getBlockTexture(Blocks.OAK_PLANKS)
        );
        MultiVariant multivariant = plainVariant(ModelTemplates.CUBE_COLUMN.create(Blocks.BOOKSHELF, texturemapping, this.modelOutput));
        this.blockStateOutput.accept(createSimpleBlock(Blocks.BOOKSHELF, multivariant));
    }

    public void createRedstoneWire() {
        this.registerSimpleFlatItemModel(Items.REDSTONE);
        this.blockStateOutput
            .accept(
                MultiPartGenerator.multiPart(Blocks.REDSTONE_WIRE)
                    .with(
                        or(
                            condition()
                                .term(BlockStateProperties.NORTH_REDSTONE, RedstoneSide.NONE)
                                .term(BlockStateProperties.EAST_REDSTONE, RedstoneSide.NONE)
                                .term(BlockStateProperties.SOUTH_REDSTONE, RedstoneSide.NONE)
                                .term(BlockStateProperties.WEST_REDSTONE, RedstoneSide.NONE),
                            condition()
                                .term(BlockStateProperties.NORTH_REDSTONE, RedstoneSide.SIDE, RedstoneSide.UP)
                                .term(BlockStateProperties.EAST_REDSTONE, RedstoneSide.SIDE, RedstoneSide.UP),
                            condition()
                                .term(BlockStateProperties.EAST_REDSTONE, RedstoneSide.SIDE, RedstoneSide.UP)
                                .term(BlockStateProperties.SOUTH_REDSTONE, RedstoneSide.SIDE, RedstoneSide.UP),
                            condition()
                                .term(BlockStateProperties.SOUTH_REDSTONE, RedstoneSide.SIDE, RedstoneSide.UP)
                                .term(BlockStateProperties.WEST_REDSTONE, RedstoneSide.SIDE, RedstoneSide.UP),
                            condition()
                                .term(BlockStateProperties.WEST_REDSTONE, RedstoneSide.SIDE, RedstoneSide.UP)
                                .term(BlockStateProperties.NORTH_REDSTONE, RedstoneSide.SIDE, RedstoneSide.UP)
                        ),
                        plainVariant(ModelLocationUtils.decorateBlockModelLocation("redstone_dust_dot"))
                    )
                    .with(
                        condition().term(BlockStateProperties.NORTH_REDSTONE, RedstoneSide.SIDE, RedstoneSide.UP),
                        plainVariant(ModelLocationUtils.decorateBlockModelLocation("redstone_dust_side0"))
                    )
                    .with(
                        condition().term(BlockStateProperties.SOUTH_REDSTONE, RedstoneSide.SIDE, RedstoneSide.UP),
                        plainVariant(ModelLocationUtils.decorateBlockModelLocation("redstone_dust_side_alt0"))
                    )
                    .with(
                        condition().term(BlockStateProperties.EAST_REDSTONE, RedstoneSide.SIDE, RedstoneSide.UP),
                        plainVariant(ModelLocationUtils.decorateBlockModelLocation("redstone_dust_side_alt1")).with(Y_ROT_270)
                    )
                    .with(
                        condition().term(BlockStateProperties.WEST_REDSTONE, RedstoneSide.SIDE, RedstoneSide.UP),
                        plainVariant(ModelLocationUtils.decorateBlockModelLocation("redstone_dust_side1")).with(Y_ROT_270)
                    )
                    .with(
                        condition().term(BlockStateProperties.NORTH_REDSTONE, RedstoneSide.UP),
                        plainVariant(ModelLocationUtils.decorateBlockModelLocation("redstone_dust_up"))
                    )
                    .with(
                        condition().term(BlockStateProperties.EAST_REDSTONE, RedstoneSide.UP),
                        plainVariant(ModelLocationUtils.decorateBlockModelLocation("redstone_dust_up")).with(Y_ROT_90)
                    )
                    .with(
                        condition().term(BlockStateProperties.SOUTH_REDSTONE, RedstoneSide.UP),
                        plainVariant(ModelLocationUtils.decorateBlockModelLocation("redstone_dust_up")).with(Y_ROT_180)
                    )
                    .with(
                        condition().term(BlockStateProperties.WEST_REDSTONE, RedstoneSide.UP),
                        plainVariant(ModelLocationUtils.decorateBlockModelLocation("redstone_dust_up")).with(Y_ROT_270)
                    )
            );
    }

    public void createComparator() {
        this.registerSimpleFlatItemModel(Items.COMPARATOR);
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(Blocks.COMPARATOR)
                    .with(
                        PropertyDispatch.initial(BlockStateProperties.MODE_COMPARATOR, BlockStateProperties.POWERED)
                            .select(ComparatorMode.COMPARE, false, plainVariant(ModelLocationUtils.getModelLocation(Blocks.COMPARATOR)))
                            .select(ComparatorMode.COMPARE, true, plainVariant(ModelLocationUtils.getModelLocation(Blocks.COMPARATOR, "_on")))
                            .select(ComparatorMode.SUBTRACT, false, plainVariant(ModelLocationUtils.getModelLocation(Blocks.COMPARATOR, "_subtract")))
                            .select(ComparatorMode.SUBTRACT, true, plainVariant(ModelLocationUtils.getModelLocation(Blocks.COMPARATOR, "_on_subtract")))
                    )
                    .with(ROTATION_HORIZONTAL_FACING_ALT)
            );
    }

    public void createSmoothStoneSlab() {
        TextureMapping texturemapping = TextureMapping.cube(Blocks.SMOOTH_STONE);
        TextureMapping texturemapping1 = TextureMapping.column(
            TextureMapping.getBlockTexture(Blocks.SMOOTH_STONE_SLAB, "_side"), texturemapping.get(TextureSlot.TOP)
        );
        MultiVariant multivariant = plainVariant(ModelTemplates.SLAB_BOTTOM.create(Blocks.SMOOTH_STONE_SLAB, texturemapping1, this.modelOutput));
        MultiVariant multivariant1 = plainVariant(ModelTemplates.SLAB_TOP.create(Blocks.SMOOTH_STONE_SLAB, texturemapping1, this.modelOutput));
        MultiVariant multivariant2 = plainVariant(
            ModelTemplates.CUBE_COLUMN.createWithOverride(Blocks.SMOOTH_STONE_SLAB, "_double", texturemapping1, this.modelOutput)
        );
        this.blockStateOutput.accept(createSlab(Blocks.SMOOTH_STONE_SLAB, multivariant, multivariant1, multivariant2));
        this.blockStateOutput
            .accept(createSimpleBlock(Blocks.SMOOTH_STONE, plainVariant(ModelTemplates.CUBE_ALL.create(Blocks.SMOOTH_STONE, texturemapping, this.modelOutput))));
    }

    public void createBrewingStand() {
        this.registerSimpleFlatItemModel(Items.BREWING_STAND);
        this.blockStateOutput
            .accept(
                MultiPartGenerator.multiPart(Blocks.BREWING_STAND)
                    .with(plainVariant(TextureMapping.getBlockTexture(Blocks.BREWING_STAND)))
                    .with(
                        condition().term(BlockStateProperties.HAS_BOTTLE_0, true),
                        plainVariant(TextureMapping.getBlockTexture(Blocks.BREWING_STAND, "_bottle0"))
                    )
                    .with(
                        condition().term(BlockStateProperties.HAS_BOTTLE_1, true),
                        plainVariant(TextureMapping.getBlockTexture(Blocks.BREWING_STAND, "_bottle1"))
                    )
                    .with(
                        condition().term(BlockStateProperties.HAS_BOTTLE_2, true),
                        plainVariant(TextureMapping.getBlockTexture(Blocks.BREWING_STAND, "_bottle2"))
                    )
                    .with(
                        condition().term(BlockStateProperties.HAS_BOTTLE_0, false),
                        plainVariant(TextureMapping.getBlockTexture(Blocks.BREWING_STAND, "_empty0"))
                    )
                    .with(
                        condition().term(BlockStateProperties.HAS_BOTTLE_1, false),
                        plainVariant(TextureMapping.getBlockTexture(Blocks.BREWING_STAND, "_empty1"))
                    )
                    .with(
                        condition().term(BlockStateProperties.HAS_BOTTLE_2, false),
                        plainVariant(TextureMapping.getBlockTexture(Blocks.BREWING_STAND, "_empty2"))
                    )
            );
    }

    public void createMushroomBlock(Block mushroomBlock) {
        MultiVariant multivariant = plainVariant(ModelTemplates.SINGLE_FACE.create(mushroomBlock, TextureMapping.defaultTexture(mushroomBlock), this.modelOutput));
        MultiVariant multivariant1 = plainVariant(ModelLocationUtils.decorateBlockModelLocation("mushroom_block_inside"));
        this.blockStateOutput
            .accept(
                MultiPartGenerator.multiPart(mushroomBlock)
                    .with(condition().term(BlockStateProperties.NORTH, true), multivariant)
                    .with(condition().term(BlockStateProperties.EAST, true), multivariant.with(Y_ROT_90).with(UV_LOCK))
                    .with(condition().term(BlockStateProperties.SOUTH, true), multivariant.with(Y_ROT_180).with(UV_LOCK))
                    .with(condition().term(BlockStateProperties.WEST, true), multivariant.with(Y_ROT_270).with(UV_LOCK))
                    .with(condition().term(BlockStateProperties.UP, true), multivariant.with(X_ROT_270).with(UV_LOCK))
                    .with(condition().term(BlockStateProperties.DOWN, true), multivariant.with(X_ROT_90).with(UV_LOCK))
                    .with(condition().term(BlockStateProperties.NORTH, false), multivariant1)
                    .with(condition().term(BlockStateProperties.EAST, false), multivariant1.with(Y_ROT_90))
                    .with(condition().term(BlockStateProperties.SOUTH, false), multivariant1.with(Y_ROT_180))
                    .with(condition().term(BlockStateProperties.WEST, false), multivariant1.with(Y_ROT_270))
                    .with(condition().term(BlockStateProperties.UP, false), multivariant1.with(X_ROT_270))
                    .with(condition().term(BlockStateProperties.DOWN, false), multivariant1.with(X_ROT_90))
            );
        this.registerSimpleItemModel(mushroomBlock, TexturedModel.CUBE.createWithSuffix(mushroomBlock, "_inventory", this.modelOutput));
    }

    public void createCakeBlock() {
        this.registerSimpleFlatItemModel(Items.CAKE);
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(Blocks.CAKE)
                    .with(
                        PropertyDispatch.initial(BlockStateProperties.BITES)
                            .select(0, plainVariant(ModelLocationUtils.getModelLocation(Blocks.CAKE)))
                            .select(1, plainVariant(ModelLocationUtils.getModelLocation(Blocks.CAKE, "_slice1")))
                            .select(2, plainVariant(ModelLocationUtils.getModelLocation(Blocks.CAKE, "_slice2")))
                            .select(3, plainVariant(ModelLocationUtils.getModelLocation(Blocks.CAKE, "_slice3")))
                            .select(4, plainVariant(ModelLocationUtils.getModelLocation(Blocks.CAKE, "_slice4")))
                            .select(5, plainVariant(ModelLocationUtils.getModelLocation(Blocks.CAKE, "_slice5")))
                            .select(6, plainVariant(ModelLocationUtils.getModelLocation(Blocks.CAKE, "_slice6")))
                    )
            );
    }

    public void createCartographyTable() {
        TextureMapping texturemapping = new TextureMapping()
            .put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(Blocks.CARTOGRAPHY_TABLE, "_side3"))
            .put(TextureSlot.DOWN, TextureMapping.getBlockTexture(Blocks.DARK_OAK_PLANKS))
            .put(TextureSlot.UP, TextureMapping.getBlockTexture(Blocks.CARTOGRAPHY_TABLE, "_top"))
            .put(TextureSlot.NORTH, TextureMapping.getBlockTexture(Blocks.CARTOGRAPHY_TABLE, "_side3"))
            .put(TextureSlot.EAST, TextureMapping.getBlockTexture(Blocks.CARTOGRAPHY_TABLE, "_side3"))
            .put(TextureSlot.SOUTH, TextureMapping.getBlockTexture(Blocks.CARTOGRAPHY_TABLE, "_side1"))
            .put(TextureSlot.WEST, TextureMapping.getBlockTexture(Blocks.CARTOGRAPHY_TABLE, "_side2"));
        this.blockStateOutput
            .accept(
                createSimpleBlock(
                    Blocks.CARTOGRAPHY_TABLE, plainVariant(ModelTemplates.CUBE.create(Blocks.CARTOGRAPHY_TABLE, texturemapping, this.modelOutput))
                )
            );
    }

    public void createSmithingTable() {
        TextureMapping texturemapping = new TextureMapping()
            .put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(Blocks.SMITHING_TABLE, "_front"))
            .put(TextureSlot.DOWN, TextureMapping.getBlockTexture(Blocks.SMITHING_TABLE, "_bottom"))
            .put(TextureSlot.UP, TextureMapping.getBlockTexture(Blocks.SMITHING_TABLE, "_top"))
            .put(TextureSlot.NORTH, TextureMapping.getBlockTexture(Blocks.SMITHING_TABLE, "_front"))
            .put(TextureSlot.SOUTH, TextureMapping.getBlockTexture(Blocks.SMITHING_TABLE, "_front"))
            .put(TextureSlot.EAST, TextureMapping.getBlockTexture(Blocks.SMITHING_TABLE, "_side"))
            .put(TextureSlot.WEST, TextureMapping.getBlockTexture(Blocks.SMITHING_TABLE, "_side"));
        this.blockStateOutput
            .accept(createSimpleBlock(Blocks.SMITHING_TABLE, plainVariant(ModelTemplates.CUBE.create(Blocks.SMITHING_TABLE, texturemapping, this.modelOutput))));
    }

    public void createCraftingTableLike(Block craftingTableBlock, Block craftingTableMaterialBlock, BiFunction<Block, Block, TextureMapping> textureMappingGetter) {
        TextureMapping texturemapping = textureMappingGetter.apply(craftingTableBlock, craftingTableMaterialBlock);
        this.blockStateOutput.accept(createSimpleBlock(craftingTableBlock, plainVariant(ModelTemplates.CUBE.create(craftingTableBlock, texturemapping, this.modelOutput))));
    }

    public void createGenericCube(Block block) {
        TextureMapping texturemapping = new TextureMapping()
            .put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(block, "_particle"))
            .put(TextureSlot.DOWN, TextureMapping.getBlockTexture(block, "_down"))
            .put(TextureSlot.UP, TextureMapping.getBlockTexture(block, "_up"))
            .put(TextureSlot.NORTH, TextureMapping.getBlockTexture(block, "_north"))
            .put(TextureSlot.SOUTH, TextureMapping.getBlockTexture(block, "_south"))
            .put(TextureSlot.EAST, TextureMapping.getBlockTexture(block, "_east"))
            .put(TextureSlot.WEST, TextureMapping.getBlockTexture(block, "_west"));
        this.blockStateOutput.accept(createSimpleBlock(block, plainVariant(ModelTemplates.CUBE.create(block, texturemapping, this.modelOutput))));
    }

    public void createPumpkins() {
        TextureMapping texturemapping = TextureMapping.column(Blocks.PUMPKIN);
        this.blockStateOutput.accept(createSimpleBlock(Blocks.PUMPKIN, plainVariant(ModelLocationUtils.getModelLocation(Blocks.PUMPKIN))));
        this.createPumpkinVariant(Blocks.CARVED_PUMPKIN, texturemapping);
        this.createPumpkinVariant(Blocks.JACK_O_LANTERN, texturemapping);
    }

    public void createPumpkinVariant(Block pumpkinBlock, TextureMapping columnTextureMapping) {
        MultiVariant multivariant = plainVariant(
            ModelTemplates.CUBE_ORIENTABLE
                .create(pumpkinBlock, columnTextureMapping.copyAndUpdate(TextureSlot.FRONT, TextureMapping.getBlockTexture(pumpkinBlock)), this.modelOutput)
        );
        this.blockStateOutput.accept(MultiVariantGenerator.dispatch(pumpkinBlock, multivariant).with(ROTATION_HORIZONTAL_FACING));
    }

    public void createCauldrons() {
        this.registerSimpleFlatItemModel(Items.CAULDRON);
        this.createNonTemplateModelBlock(Blocks.CAULDRON);
        this.blockStateOutput
            .accept(
                createSimpleBlock(
                    Blocks.LAVA_CAULDRON,
                    plainVariant(
                        ModelTemplates.CAULDRON_FULL
                            .create(Blocks.LAVA_CAULDRON, TextureMapping.cauldron(TextureMapping.getBlockTexture(Blocks.LAVA, "_still")), this.modelOutput)
                    )
                )
            );
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(Blocks.WATER_CAULDRON)
                    .with(
                        PropertyDispatch.initial(LayeredCauldronBlock.LEVEL)
                            .select(
                                1,
                                plainVariant(
                                    ModelTemplates.CAULDRON_LEVEL1
                                        .createWithSuffix(
                                            Blocks.WATER_CAULDRON,
                                            "_level1",
                                            TextureMapping.cauldron(TextureMapping.getBlockTexture(Blocks.WATER, "_still")),
                                            this.modelOutput
                                        )
                                )
                            )
                            .select(
                                2,
                                plainVariant(
                                    ModelTemplates.CAULDRON_LEVEL2
                                        .createWithSuffix(
                                            Blocks.WATER_CAULDRON,
                                            "_level2",
                                            TextureMapping.cauldron(TextureMapping.getBlockTexture(Blocks.WATER, "_still")),
                                            this.modelOutput
                                        )
                                )
                            )
                            .select(
                                3,
                                plainVariant(
                                    ModelTemplates.CAULDRON_FULL
                                        .createWithSuffix(
                                            Blocks.WATER_CAULDRON,
                                            "_full",
                                            TextureMapping.cauldron(TextureMapping.getBlockTexture(Blocks.WATER, "_still")),
                                            this.modelOutput
                                        )
                                )
                            )
                    )
            );
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(Blocks.POWDER_SNOW_CAULDRON)
                    .with(
                        PropertyDispatch.initial(LayeredCauldronBlock.LEVEL)
                            .select(
                                1,
                                plainVariant(
                                    ModelTemplates.CAULDRON_LEVEL1
                                        .createWithSuffix(
                                            Blocks.POWDER_SNOW_CAULDRON,
                                            "_level1",
                                            TextureMapping.cauldron(TextureMapping.getBlockTexture(Blocks.POWDER_SNOW)),
                                            this.modelOutput
                                        )
                                )
                            )
                            .select(
                                2,
                                plainVariant(
                                    ModelTemplates.CAULDRON_LEVEL2
                                        .createWithSuffix(
                                            Blocks.POWDER_SNOW_CAULDRON,
                                            "_level2",
                                            TextureMapping.cauldron(TextureMapping.getBlockTexture(Blocks.POWDER_SNOW)),
                                            this.modelOutput
                                        )
                                )
                            )
                            .select(
                                3,
                                plainVariant(
                                    ModelTemplates.CAULDRON_FULL
                                        .createWithSuffix(
                                            Blocks.POWDER_SNOW_CAULDRON,
                                            "_full",
                                            TextureMapping.cauldron(TextureMapping.getBlockTexture(Blocks.POWDER_SNOW)),
                                            this.modelOutput
                                        )
                                )
                            )
                    )
            );
    }

    public void createChorusFlower() {
        TextureMapping texturemapping = TextureMapping.defaultTexture(Blocks.CHORUS_FLOWER);
        MultiVariant multivariant = plainVariant(ModelTemplates.CHORUS_FLOWER.create(Blocks.CHORUS_FLOWER, texturemapping, this.modelOutput));
        MultiVariant multivariant1 = plainVariant(
            this.createSuffixedVariant(
                Blocks.CHORUS_FLOWER, "_dead", ModelTemplates.CHORUS_FLOWER, p_465418_ -> texturemapping.copyAndUpdate(TextureSlot.TEXTURE, p_465418_)
            )
        );
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(Blocks.CHORUS_FLOWER)
                    .with(createEmptyOrFullDispatch(BlockStateProperties.AGE_5, 5, multivariant1, multivariant))
            );
    }

    public void createCrafterBlock() {
        MultiVariant multivariant = plainVariant(ModelLocationUtils.getModelLocation(Blocks.CRAFTER));
        MultiVariant multivariant1 = plainVariant(ModelLocationUtils.getModelLocation(Blocks.CRAFTER, "_triggered"));
        MultiVariant multivariant2 = plainVariant(ModelLocationUtils.getModelLocation(Blocks.CRAFTER, "_crafting"));
        MultiVariant multivariant3 = plainVariant(ModelLocationUtils.getModelLocation(Blocks.CRAFTER, "_crafting_triggered"));
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(Blocks.CRAFTER)
                    .with(
                        PropertyDispatch.initial(BlockStateProperties.TRIGGERED, CrafterBlock.CRAFTING)
                            .select(false, false, multivariant)
                            .select(true, true, multivariant3)
                            .select(true, false, multivariant1)
                            .select(false, true, multivariant2)
                    )
                    .with(PropertyDispatch.modify(BlockStateProperties.ORIENTATION).generate(BlockModelGenerators::applyRotation))
            );
    }

    public void createDispenserBlock(Block dispenserBlock) {
        TextureMapping texturemapping = new TextureMapping()
            .put(TextureSlot.TOP, TextureMapping.getBlockTexture(Blocks.FURNACE, "_top"))
            .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(Blocks.FURNACE, "_side"))
            .put(TextureSlot.FRONT, TextureMapping.getBlockTexture(dispenserBlock, "_front"));
        TextureMapping texturemapping1 = new TextureMapping()
            .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(Blocks.FURNACE, "_top"))
            .put(TextureSlot.FRONT, TextureMapping.getBlockTexture(dispenserBlock, "_front_vertical"));
        MultiVariant multivariant = plainVariant(ModelTemplates.CUBE_ORIENTABLE.create(dispenserBlock, texturemapping, this.modelOutput));
        MultiVariant multivariant1 = plainVariant(ModelTemplates.CUBE_ORIENTABLE_VERTICAL.create(dispenserBlock, texturemapping1, this.modelOutput));
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(dispenserBlock)
                    .with(
                        PropertyDispatch.initial(BlockStateProperties.FACING)
                            .select(Direction.DOWN, multivariant1.with(X_ROT_180))
                            .select(Direction.UP, multivariant1)
                            .select(Direction.NORTH, multivariant)
                            .select(Direction.EAST, multivariant.with(Y_ROT_90))
                            .select(Direction.SOUTH, multivariant.with(Y_ROT_180))
                            .select(Direction.WEST, multivariant.with(Y_ROT_270))
                    )
            );
    }

    public void createEndPortalFrame() {
        MultiVariant multivariant = plainVariant(ModelLocationUtils.getModelLocation(Blocks.END_PORTAL_FRAME));
        MultiVariant multivariant1 = plainVariant(ModelLocationUtils.getModelLocation(Blocks.END_PORTAL_FRAME, "_filled"));
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(Blocks.END_PORTAL_FRAME)
                    .with(PropertyDispatch.initial(BlockStateProperties.EYE).select(false, multivariant).select(true, multivariant1))
                    .with(ROTATION_HORIZONTAL_FACING_ALT)
            );
    }

    public void createChorusPlant() {
        MultiVariant multivariant = plainVariant(ModelLocationUtils.getModelLocation(Blocks.CHORUS_PLANT, "_side"));
        Variant variant = plainModel(ModelLocationUtils.getModelLocation(Blocks.CHORUS_PLANT, "_noside"));
        Variant variant1 = plainModel(ModelLocationUtils.getModelLocation(Blocks.CHORUS_PLANT, "_noside1"));
        Variant variant2 = plainModel(ModelLocationUtils.getModelLocation(Blocks.CHORUS_PLANT, "_noside2"));
        Variant variant3 = plainModel(ModelLocationUtils.getModelLocation(Blocks.CHORUS_PLANT, "_noside3"));
        Variant variant4 = variant.with(UV_LOCK);
        Variant variant5 = variant1.with(UV_LOCK);
        Variant variant6 = variant2.with(UV_LOCK);
        Variant variant7 = variant3.with(UV_LOCK);
        this.blockStateOutput
            .accept(
                MultiPartGenerator.multiPart(Blocks.CHORUS_PLANT)
                    .with(condition().term(BlockStateProperties.NORTH, true), multivariant)
                    .with(condition().term(BlockStateProperties.EAST, true), multivariant.with(Y_ROT_90).with(UV_LOCK))
                    .with(condition().term(BlockStateProperties.SOUTH, true), multivariant.with(Y_ROT_180).with(UV_LOCK))
                    .with(condition().term(BlockStateProperties.WEST, true), multivariant.with(Y_ROT_270).with(UV_LOCK))
                    .with(condition().term(BlockStateProperties.UP, true), multivariant.with(X_ROT_270).with(UV_LOCK))
                    .with(condition().term(BlockStateProperties.DOWN, true), multivariant.with(X_ROT_90).with(UV_LOCK))
                    .with(
                        condition().term(BlockStateProperties.NORTH, false),
                        new MultiVariant(
                            WeightedList.of(new Weighted<>(variant, 2), new Weighted<>(variant1, 1), new Weighted<>(variant2, 1), new Weighted<>(variant3, 1))
                        )
                    )
                    .with(
                        condition().term(BlockStateProperties.EAST, false),
                        new MultiVariant(
                            WeightedList.of(
                                new Weighted<>(variant5.with(Y_ROT_90), 1),
                                new Weighted<>(variant6.with(Y_ROT_90), 1),
                                new Weighted<>(variant7.with(Y_ROT_90), 1),
                                new Weighted<>(variant4.with(Y_ROT_90), 2)
                            )
                        )
                    )
                    .with(
                        condition().term(BlockStateProperties.SOUTH, false),
                        new MultiVariant(
                            WeightedList.of(
                                new Weighted<>(variant6.with(Y_ROT_180), 1),
                                new Weighted<>(variant7.with(Y_ROT_180), 1),
                                new Weighted<>(variant4.with(Y_ROT_180), 2),
                                new Weighted<>(variant5.with(Y_ROT_180), 1)
                            )
                        )
                    )
                    .with(
                        condition().term(BlockStateProperties.WEST, false),
                        new MultiVariant(
                            WeightedList.of(
                                new Weighted<>(variant7.with(Y_ROT_270), 1),
                                new Weighted<>(variant4.with(Y_ROT_270), 2),
                                new Weighted<>(variant5.with(Y_ROT_270), 1),
                                new Weighted<>(variant6.with(Y_ROT_270), 1)
                            )
                        )
                    )
                    .with(
                        condition().term(BlockStateProperties.UP, false),
                        new MultiVariant(
                            WeightedList.of(
                                new Weighted<>(variant4.with(X_ROT_270), 2),
                                new Weighted<>(variant7.with(X_ROT_270), 1),
                                new Weighted<>(variant5.with(X_ROT_270), 1),
                                new Weighted<>(variant6.with(X_ROT_270), 1)
                            )
                        )
                    )
                    .with(
                        condition().term(BlockStateProperties.DOWN, false),
                        new MultiVariant(
                            WeightedList.of(
                                new Weighted<>(variant7.with(X_ROT_90), 1),
                                new Weighted<>(variant6.with(X_ROT_90), 1),
                                new Weighted<>(variant5.with(X_ROT_90), 1),
                                new Weighted<>(variant4.with(X_ROT_90), 2)
                            )
                        )
                    )
            );
    }

    public void createComposter() {
        this.blockStateOutput
            .accept(
                MultiPartGenerator.multiPart(Blocks.COMPOSTER)
                    .with(plainVariant(TextureMapping.getBlockTexture(Blocks.COMPOSTER)))
                    .with(
                        condition().term(BlockStateProperties.LEVEL_COMPOSTER, 1), plainVariant(TextureMapping.getBlockTexture(Blocks.COMPOSTER, "_contents1"))
                    )
                    .with(
                        condition().term(BlockStateProperties.LEVEL_COMPOSTER, 2), plainVariant(TextureMapping.getBlockTexture(Blocks.COMPOSTER, "_contents2"))
                    )
                    .with(
                        condition().term(BlockStateProperties.LEVEL_COMPOSTER, 3), plainVariant(TextureMapping.getBlockTexture(Blocks.COMPOSTER, "_contents3"))
                    )
                    .with(
                        condition().term(BlockStateProperties.LEVEL_COMPOSTER, 4), plainVariant(TextureMapping.getBlockTexture(Blocks.COMPOSTER, "_contents4"))
                    )
                    .with(
                        condition().term(BlockStateProperties.LEVEL_COMPOSTER, 5), plainVariant(TextureMapping.getBlockTexture(Blocks.COMPOSTER, "_contents5"))
                    )
                    .with(
                        condition().term(BlockStateProperties.LEVEL_COMPOSTER, 6), plainVariant(TextureMapping.getBlockTexture(Blocks.COMPOSTER, "_contents6"))
                    )
                    .with(
                        condition().term(BlockStateProperties.LEVEL_COMPOSTER, 7), plainVariant(TextureMapping.getBlockTexture(Blocks.COMPOSTER, "_contents7"))
                    )
                    .with(
                        condition().term(BlockStateProperties.LEVEL_COMPOSTER, 8),
                        plainVariant(TextureMapping.getBlockTexture(Blocks.COMPOSTER, "_contents_ready"))
                    )
            );
    }

    public void createCopperBulb(Block bulbBlock) {
        MultiVariant multivariant = plainVariant(ModelTemplates.CUBE_ALL.create(bulbBlock, TextureMapping.cube(bulbBlock), this.modelOutput));
        MultiVariant multivariant1 = plainVariant(this.createSuffixedVariant(bulbBlock, "_powered", ModelTemplates.CUBE_ALL, TextureMapping::cube));
        MultiVariant multivariant2 = plainVariant(this.createSuffixedVariant(bulbBlock, "_lit", ModelTemplates.CUBE_ALL, TextureMapping::cube));
        MultiVariant multivariant3 = plainVariant(this.createSuffixedVariant(bulbBlock, "_lit_powered", ModelTemplates.CUBE_ALL, TextureMapping::cube));
        this.blockStateOutput.accept(createCopperBulb(bulbBlock, multivariant, multivariant2, multivariant1, multivariant3));
    }

    public static BlockModelDefinitionGenerator createCopperBulb(
        Block block, MultiVariant unlit, MultiVariant unlitPowered, MultiVariant lit, MultiVariant litPowered
    ) {
        return MultiVariantGenerator.dispatch(block)
            .with(PropertyDispatch.initial(BlockStateProperties.LIT, BlockStateProperties.POWERED).generate((p_403926_, p_403927_) -> {
                if (p_403926_) {
                    return p_403927_ ? litPowered : unlitPowered;
                } else {
                    return p_403927_ ? lit : unlit;
                }
            }));
    }

    public void copyCopperBulbModel(Block bulbBlock, Block sourceBlock) {
        MultiVariant multivariant = plainVariant(ModelLocationUtils.getModelLocation(bulbBlock));
        MultiVariant multivariant1 = plainVariant(ModelLocationUtils.getModelLocation(bulbBlock, "_powered"));
        MultiVariant multivariant2 = plainVariant(ModelLocationUtils.getModelLocation(bulbBlock, "_lit"));
        MultiVariant multivariant3 = plainVariant(ModelLocationUtils.getModelLocation(bulbBlock, "_lit_powered"));
        this.itemModelOutput.copy(bulbBlock.asItem(), sourceBlock.asItem());
        this.blockStateOutput.accept(createCopperBulb(sourceBlock, multivariant, multivariant2, multivariant1, multivariant3));
    }

    public void createAmethystCluster(Block amethystBlock) {
        MultiVariant multivariant = plainVariant(ModelTemplates.CROSS.create(amethystBlock, TextureMapping.cross(amethystBlock), this.modelOutput));
        this.blockStateOutput.accept(MultiVariantGenerator.dispatch(amethystBlock, multivariant).with(ROTATIONS_COLUMN_WITH_FACING));
    }

    public void createAmethystClusters() {
        this.createAmethystCluster(Blocks.SMALL_AMETHYST_BUD);
        this.createAmethystCluster(Blocks.MEDIUM_AMETHYST_BUD);
        this.createAmethystCluster(Blocks.LARGE_AMETHYST_BUD);
        this.createAmethystCluster(Blocks.AMETHYST_CLUSTER);
    }

    public void createPointedDripstone() {
        PropertyDispatch.C2<MultiVariant, Direction, DripstoneThickness> c2 = PropertyDispatch.initial(
            BlockStateProperties.VERTICAL_DIRECTION, BlockStateProperties.DRIPSTONE_THICKNESS
        );

        for (DripstoneThickness dripstonethickness : DripstoneThickness.values()) {
            c2.select(Direction.UP, dripstonethickness, this.createPointedDripstoneVariant(Direction.UP, dripstonethickness));
        }

        for (DripstoneThickness dripstonethickness1 : DripstoneThickness.values()) {
            c2.select(Direction.DOWN, dripstonethickness1, this.createPointedDripstoneVariant(Direction.DOWN, dripstonethickness1));
        }

        this.blockStateOutput.accept(MultiVariantGenerator.dispatch(Blocks.POINTED_DRIPSTONE).with(c2));
    }

    public MultiVariant createPointedDripstoneVariant(Direction direction, DripstoneThickness thickness) {
        String s = "_" + direction.getSerializedName() + "_" + thickness.getSerializedName();
        TextureMapping texturemapping = TextureMapping.cross(TextureMapping.getBlockTexture(Blocks.POINTED_DRIPSTONE, s));
        return plainVariant(ModelTemplates.POINTED_DRIPSTONE.createWithSuffix(Blocks.POINTED_DRIPSTONE, s, texturemapping, this.modelOutput));
    }

    public void createNyliumBlock(Block nyliumBlock) {
        TextureMapping texturemapping = new TextureMapping()
            .put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(Blocks.NETHERRACK))
            .put(TextureSlot.TOP, TextureMapping.getBlockTexture(nyliumBlock))
            .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(nyliumBlock, "_side"));
        this.blockStateOutput
            .accept(createSimpleBlock(nyliumBlock, plainVariant(ModelTemplates.CUBE_BOTTOM_TOP.create(nyliumBlock, texturemapping, this.modelOutput))));
    }

    public void createDaylightDetector() {
        Identifier identifier = TextureMapping.getBlockTexture(Blocks.DAYLIGHT_DETECTOR, "_side");
        TextureMapping texturemapping = new TextureMapping()
            .put(TextureSlot.TOP, TextureMapping.getBlockTexture(Blocks.DAYLIGHT_DETECTOR, "_top"))
            .put(TextureSlot.SIDE, identifier);
        TextureMapping texturemapping1 = new TextureMapping()
            .put(TextureSlot.TOP, TextureMapping.getBlockTexture(Blocks.DAYLIGHT_DETECTOR, "_inverted_top"))
            .put(TextureSlot.SIDE, identifier);
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(Blocks.DAYLIGHT_DETECTOR)
                    .with(
                        PropertyDispatch.initial(BlockStateProperties.INVERTED)
                            .select(false, plainVariant(ModelTemplates.DAYLIGHT_DETECTOR.create(Blocks.DAYLIGHT_DETECTOR, texturemapping, this.modelOutput)))
                            .select(
                                true,
                                plainVariant(
                                    ModelTemplates.DAYLIGHT_DETECTOR
                                        .create(ModelLocationUtils.getModelLocation(Blocks.DAYLIGHT_DETECTOR, "_inverted"), texturemapping1, this.modelOutput)
                                )
                            )
                    )
            );
    }

    public void createRotatableColumn(Block rotatableColumnBlock) {
        this.blockStateOutput
            .accept(MultiVariantGenerator.dispatch(rotatableColumnBlock, plainVariant(ModelLocationUtils.getModelLocation(rotatableColumnBlock))).with(ROTATIONS_COLUMN_WITH_FACING));
    }

    public void createLightningRod(Block block, Block waxedBlock) {
        MultiVariant multivariant = plainVariant(ModelLocationUtils.getModelLocation(Blocks.LIGHTNING_ROD, "_on"));
        MultiVariant multivariant1 = plainVariant(ModelTemplates.LIGHTNING_ROD.create(block, TextureMapping.defaultTexture(block), this.modelOutput));
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(block)
                    .with(createBooleanModelDispatch(BlockStateProperties.POWERED, multivariant, multivariant1))
                    .with(ROTATIONS_COLUMN_WITH_FACING)
            );
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(waxedBlock)
                    .with(createBooleanModelDispatch(BlockStateProperties.POWERED, multivariant, multivariant1))
                    .with(ROTATIONS_COLUMN_WITH_FACING)
            );
        this.itemModelOutput.copy(block.asItem(), waxedBlock.asItem());
    }

    public void createFarmland() {
        TextureMapping texturemapping = new TextureMapping()
            .put(TextureSlot.DIRT, TextureMapping.getBlockTexture(Blocks.DIRT))
            .put(TextureSlot.TOP, TextureMapping.getBlockTexture(Blocks.FARMLAND));
        TextureMapping texturemapping1 = new TextureMapping()
            .put(TextureSlot.DIRT, TextureMapping.getBlockTexture(Blocks.DIRT))
            .put(TextureSlot.TOP, TextureMapping.getBlockTexture(Blocks.FARMLAND, "_moist"));
        MultiVariant multivariant = plainVariant(ModelTemplates.FARMLAND.create(Blocks.FARMLAND, texturemapping, this.modelOutput));
        MultiVariant multivariant1 = plainVariant(
            ModelTemplates.FARMLAND.create(TextureMapping.getBlockTexture(Blocks.FARMLAND, "_moist"), texturemapping1, this.modelOutput)
        );
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(Blocks.FARMLAND).with(createEmptyOrFullDispatch(BlockStateProperties.MOISTURE, 7, multivariant1, multivariant))
            );
    }

    public MultiVariant createFloorFireModels(Block block) {
        return variants(
            plainModel(
                ModelTemplates.FIRE_FLOOR.create(ModelLocationUtils.getModelLocation(block, "_floor0"), TextureMapping.fire0(block), this.modelOutput)
            ),
            plainModel(
                ModelTemplates.FIRE_FLOOR.create(ModelLocationUtils.getModelLocation(block, "_floor1"), TextureMapping.fire1(block), this.modelOutput)
            )
        );
    }

    public MultiVariant createSideFireModels(Block block) {
        return variants(
            plainModel(
                ModelTemplates.FIRE_SIDE.create(ModelLocationUtils.getModelLocation(block, "_side0"), TextureMapping.fire0(block), this.modelOutput)
            ),
            plainModel(
                ModelTemplates.FIRE_SIDE.create(ModelLocationUtils.getModelLocation(block, "_side1"), TextureMapping.fire1(block), this.modelOutput)
            ),
            plainModel(
                ModelTemplates.FIRE_SIDE_ALT
                    .create(ModelLocationUtils.getModelLocation(block, "_side_alt0"), TextureMapping.fire0(block), this.modelOutput)
            ),
            plainModel(
                ModelTemplates.FIRE_SIDE_ALT
                    .create(ModelLocationUtils.getModelLocation(block, "_side_alt1"), TextureMapping.fire1(block), this.modelOutput)
            )
        );
    }

    public MultiVariant createTopFireModels(Block block) {
        return variants(
            plainModel(ModelTemplates.FIRE_UP.create(ModelLocationUtils.getModelLocation(block, "_up0"), TextureMapping.fire0(block), this.modelOutput)),
            plainModel(ModelTemplates.FIRE_UP.create(ModelLocationUtils.getModelLocation(block, "_up1"), TextureMapping.fire1(block), this.modelOutput)),
            plainModel(
                ModelTemplates.FIRE_UP_ALT
                    .create(ModelLocationUtils.getModelLocation(block, "_up_alt0"), TextureMapping.fire0(block), this.modelOutput)
            ),
            plainModel(
                ModelTemplates.FIRE_UP_ALT
                    .create(ModelLocationUtils.getModelLocation(block, "_up_alt1"), TextureMapping.fire1(block), this.modelOutput)
            )
        );
    }

    public void createFire() {
        ConditionBuilder conditionbuilder = condition()
            .term(BlockStateProperties.NORTH, false)
            .term(BlockStateProperties.EAST, false)
            .term(BlockStateProperties.SOUTH, false)
            .term(BlockStateProperties.WEST, false)
            .term(BlockStateProperties.UP, false);
        MultiVariant multivariant = this.createFloorFireModels(Blocks.FIRE);
        MultiVariant multivariant1 = this.createSideFireModels(Blocks.FIRE);
        MultiVariant multivariant2 = this.createTopFireModels(Blocks.FIRE);
        this.blockStateOutput
            .accept(
                MultiPartGenerator.multiPart(Blocks.FIRE)
                    .with(conditionbuilder, multivariant)
                    .with(or(condition().term(BlockStateProperties.NORTH, true), conditionbuilder), multivariant1)
                    .with(or(condition().term(BlockStateProperties.EAST, true), conditionbuilder), multivariant1.with(Y_ROT_90))
                    .with(or(condition().term(BlockStateProperties.SOUTH, true), conditionbuilder), multivariant1.with(Y_ROT_180))
                    .with(or(condition().term(BlockStateProperties.WEST, true), conditionbuilder), multivariant1.with(Y_ROT_270))
                    .with(condition().term(BlockStateProperties.UP, true), multivariant2)
            );
    }

    public void createSoulFire() {
        MultiVariant multivariant = this.createFloorFireModels(Blocks.SOUL_FIRE);
        MultiVariant multivariant1 = this.createSideFireModels(Blocks.SOUL_FIRE);
        this.blockStateOutput
            .accept(
                MultiPartGenerator.multiPart(Blocks.SOUL_FIRE)
                    .with(multivariant)
                    .with(multivariant1)
                    .with(multivariant1.with(Y_ROT_90))
                    .with(multivariant1.with(Y_ROT_180))
                    .with(multivariant1.with(Y_ROT_270))
            );
    }

    public void createLantern(Block lanternBlock) {
        MultiVariant multivariant = plainVariant(TexturedModel.LANTERN.create(lanternBlock, this.modelOutput));
        MultiVariant multivariant1 = plainVariant(TexturedModel.HANGING_LANTERN.create(lanternBlock, this.modelOutput));
        this.registerSimpleFlatItemModel(lanternBlock.asItem());
        this.blockStateOutput
            .accept(MultiVariantGenerator.dispatch(lanternBlock).with(createBooleanModelDispatch(BlockStateProperties.HANGING, multivariant1, multivariant)));
    }

    public void createCopperLantern(Block lanternBlock, Block waxed) {
        Identifier identifier = TexturedModel.LANTERN.create(lanternBlock, this.modelOutput);
        Identifier identifier1 = TexturedModel.HANGING_LANTERN.create(lanternBlock, this.modelOutput);
        this.registerSimpleFlatItemModel(lanternBlock.asItem());
        this.itemModelOutput.copy(lanternBlock.asItem(), waxed.asItem());
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(lanternBlock)
                    .with(createBooleanModelDispatch(BlockStateProperties.HANGING, plainVariant(identifier1), plainVariant(identifier)))
            );
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(waxed)
                    .with(createBooleanModelDispatch(BlockStateProperties.HANGING, plainVariant(identifier1), plainVariant(identifier)))
            );
    }

    public void createCopperChain(Block chain, Block waxed) {
        MultiVariant multivariant = plainVariant(TexturedModel.CHAIN.create(chain, this.modelOutput));
        this.createAxisAlignedPillarBlockCustomModel(chain, multivariant);
        this.createAxisAlignedPillarBlockCustomModel(waxed, multivariant);
    }

    public void createMuddyMangroveRoots() {
        TextureMapping texturemapping = TextureMapping.column(
            TextureMapping.getBlockTexture(Blocks.MUDDY_MANGROVE_ROOTS, "_side"), TextureMapping.getBlockTexture(Blocks.MUDDY_MANGROVE_ROOTS, "_top")
        );
        MultiVariant multivariant = plainVariant(ModelTemplates.CUBE_COLUMN.create(Blocks.MUDDY_MANGROVE_ROOTS, texturemapping, this.modelOutput));
        this.blockStateOutput.accept(createAxisAlignedPillarBlock(Blocks.MUDDY_MANGROVE_ROOTS, multivariant));
    }

    public void createMangrovePropagule() {
        this.registerSimpleFlatItemModel(Items.MANGROVE_PROPAGULE);
        Block block = Blocks.MANGROVE_PROPAGULE;
        MultiVariant multivariant = plainVariant(ModelLocationUtils.getModelLocation(block));
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(Blocks.MANGROVE_PROPAGULE)
                    .with(
                        PropertyDispatch.initial(MangrovePropaguleBlock.HANGING, MangrovePropaguleBlock.AGE)
                            .generate(
                                (p_465391_, p_465392_) -> p_465391_
                                    ? plainVariant(ModelLocationUtils.getModelLocation(block, "_hanging_" + p_465392_))
                                    : multivariant
                            )
                    )
            );
    }

    public void createFrostedIce() {
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(Blocks.FROSTED_ICE)
                    .with(
                        PropertyDispatch.initial(BlockStateProperties.AGE_3)
                            .select(0, plainVariant(this.createSuffixedVariant(Blocks.FROSTED_ICE, "_0", ModelTemplates.CUBE_ALL, TextureMapping::cube)))
                            .select(1, plainVariant(this.createSuffixedVariant(Blocks.FROSTED_ICE, "_1", ModelTemplates.CUBE_ALL, TextureMapping::cube)))
                            .select(2, plainVariant(this.createSuffixedVariant(Blocks.FROSTED_ICE, "_2", ModelTemplates.CUBE_ALL, TextureMapping::cube)))
                            .select(3, plainVariant(this.createSuffixedVariant(Blocks.FROSTED_ICE, "_3", ModelTemplates.CUBE_ALL, TextureMapping::cube)))
                    )
            );
    }

    public void createGrassBlocks() {
        Identifier identifier = TextureMapping.getBlockTexture(Blocks.DIRT);
        TextureMapping texturemapping = new TextureMapping()
            .put(TextureSlot.BOTTOM, identifier)
            .copyForced(TextureSlot.BOTTOM, TextureSlot.PARTICLE)
            .put(TextureSlot.TOP, TextureMapping.getBlockTexture(Blocks.GRASS_BLOCK, "_top"))
            .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(Blocks.GRASS_BLOCK, "_snow"));
        MultiVariant multivariant = plainVariant(ModelTemplates.CUBE_BOTTOM_TOP.createWithSuffix(Blocks.GRASS_BLOCK, "_snow", texturemapping, this.modelOutput));
        Identifier identifier1 = ModelLocationUtils.getModelLocation(Blocks.GRASS_BLOCK);
        this.createGrassLikeBlock(Blocks.GRASS_BLOCK, createRotatedVariants(plainModel(identifier1)), multivariant);
        this.registerSimpleTintedItemModel(Blocks.GRASS_BLOCK, identifier1, new GrassColorSource());
        MultiVariant multivariant1 = createRotatedVariants(
            plainModel(
                TexturedModel.CUBE_TOP_BOTTOM
                    .get(Blocks.MYCELIUM)
                    .updateTextures(p_465423_ -> p_465423_.put(TextureSlot.BOTTOM, identifier))
                    .create(Blocks.MYCELIUM, this.modelOutput)
            )
        );
        this.createGrassLikeBlock(Blocks.MYCELIUM, multivariant1, multivariant);
        MultiVariant multivariant2 = createRotatedVariants(
            plainModel(
                TexturedModel.CUBE_TOP_BOTTOM
                    .get(Blocks.PODZOL)
                    .updateTextures(p_465377_ -> p_465377_.put(TextureSlot.BOTTOM, identifier))
                    .create(Blocks.PODZOL, this.modelOutput)
            )
        );
        this.createGrassLikeBlock(Blocks.PODZOL, multivariant2, multivariant);
    }

    public void createGrassLikeBlock(Block block, MultiVariant variants, MultiVariant snowyVariants) {
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(block)
                    .with(PropertyDispatch.initial(BlockStateProperties.SNOWY).select(true, snowyVariants).select(false, variants))
            );
    }

    public void createCocoa() {
        this.registerSimpleFlatItemModel(Items.COCOA_BEANS);
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(Blocks.COCOA)
                    .with(
                        PropertyDispatch.initial(BlockStateProperties.AGE_2)
                            .select(0, plainVariant(ModelLocationUtils.getModelLocation(Blocks.COCOA, "_stage0")))
                            .select(1, plainVariant(ModelLocationUtils.getModelLocation(Blocks.COCOA, "_stage1")))
                            .select(2, plainVariant(ModelLocationUtils.getModelLocation(Blocks.COCOA, "_stage2")))
                    )
                    .with(ROTATION_HORIZONTAL_FACING_ALT)
            );
    }

    public void createDirtPath() {
        Variant variant = plainModel(ModelLocationUtils.getModelLocation(Blocks.DIRT_PATH));
        this.blockStateOutput.accept(MultiVariantGenerator.dispatch(Blocks.DIRT_PATH, createRotatedVariants(variant)));
    }

    public void createWeightedPressurePlate(Block pressurePlateBlock, Block plateMaterialBlock) {
        TextureMapping texturemapping = TextureMapping.defaultTexture(plateMaterialBlock);
        MultiVariant multivariant = plainVariant(ModelTemplates.PRESSURE_PLATE_UP.create(pressurePlateBlock, texturemapping, this.modelOutput));
        MultiVariant multivariant1 = plainVariant(ModelTemplates.PRESSURE_PLATE_DOWN.create(pressurePlateBlock, texturemapping, this.modelOutput));
        this.blockStateOutput
            .accept(MultiVariantGenerator.dispatch(pressurePlateBlock).with(createEmptyOrFullDispatch(BlockStateProperties.POWER, 1, multivariant1, multivariant)));
    }

    public void createHopper() {
        MultiVariant multivariant = plainVariant(ModelLocationUtils.getModelLocation(Blocks.HOPPER));
        MultiVariant multivariant1 = plainVariant(ModelLocationUtils.getModelLocation(Blocks.HOPPER, "_side"));
        this.registerSimpleFlatItemModel(Items.HOPPER);
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(Blocks.HOPPER)
                    .with(
                        PropertyDispatch.initial(BlockStateProperties.FACING_HOPPER)
                            .select(Direction.DOWN, multivariant)
                            .select(Direction.NORTH, multivariant1)
                            .select(Direction.EAST, multivariant1.with(Y_ROT_90))
                            .select(Direction.SOUTH, multivariant1.with(Y_ROT_180))
                            .select(Direction.WEST, multivariant1.with(Y_ROT_270))
                    )
            );
    }

    public void copyModel(Block sourceBlock, Block targetBlock) {
        MultiVariant multivariant = plainVariant(ModelLocationUtils.getModelLocation(sourceBlock));
        this.blockStateOutput.accept(MultiVariantGenerator.dispatch(targetBlock, multivariant));
        this.itemModelOutput.copy(sourceBlock.asItem(), targetBlock.asItem());
    }

    public void createBarsAndItem(Block block) {
        TextureMapping texturemapping = TextureMapping.bars(block);
        this.createBars(
            block,
            ModelTemplates.BARS_POST_ENDS.create(block, texturemapping, this.modelOutput),
            ModelTemplates.BARS_POST.create(block, texturemapping, this.modelOutput),
            ModelTemplates.BARS_CAP.create(block, texturemapping, this.modelOutput),
            ModelTemplates.BARS_CAP_ALT.create(block, texturemapping, this.modelOutput),
            ModelTemplates.BARS_POST_SIDE.create(block, texturemapping, this.modelOutput),
            ModelTemplates.BARS_POST_SIDE_ALT.create(block, texturemapping, this.modelOutput)
        );
        this.registerSimpleFlatItemModel(block);
    }

    public void createBarsAndItem(Block block, Block waxedBlock) {
        TextureMapping texturemapping = TextureMapping.bars(block);
        Identifier identifier = ModelTemplates.BARS_POST_ENDS.create(block, texturemapping, this.modelOutput);
        Identifier identifier1 = ModelTemplates.BARS_POST.create(block, texturemapping, this.modelOutput);
        Identifier identifier2 = ModelTemplates.BARS_CAP.create(block, texturemapping, this.modelOutput);
        Identifier identifier3 = ModelTemplates.BARS_CAP_ALT.create(block, texturemapping, this.modelOutput);
        Identifier identifier4 = ModelTemplates.BARS_POST_SIDE.create(block, texturemapping, this.modelOutput);
        Identifier identifier5 = ModelTemplates.BARS_POST_SIDE_ALT.create(block, texturemapping, this.modelOutput);
        this.createBars(block, identifier, identifier1, identifier2, identifier3, identifier4, identifier5);
        this.createBars(waxedBlock, identifier, identifier1, identifier2, identifier3, identifier4, identifier5);
        this.registerSimpleFlatItemModel(block);
        this.itemModelOutput.copy(block.asItem(), waxedBlock.asItem());
    }

    public void createBars(
        Block block, Identifier postEnds, Identifier post, Identifier cap, Identifier capAlt, Identifier postSide, Identifier postSideAlt
    ) {
        MultiVariant multivariant = plainVariant(postEnds);
        MultiVariant multivariant1 = plainVariant(post);
        MultiVariant multivariant2 = plainVariant(cap);
        MultiVariant multivariant3 = plainVariant(capAlt);
        MultiVariant multivariant4 = plainVariant(postSide);
        MultiVariant multivariant5 = plainVariant(postSideAlt);
        this.blockStateOutput
            .accept(
                MultiPartGenerator.multiPart(block)
                    .with(multivariant)
                    .with(
                        condition()
                            .term(BlockStateProperties.NORTH, false)
                            .term(BlockStateProperties.EAST, false)
                            .term(BlockStateProperties.SOUTH, false)
                            .term(BlockStateProperties.WEST, false),
                        multivariant1
                    )
                    .with(
                        condition()
                            .term(BlockStateProperties.NORTH, true)
                            .term(BlockStateProperties.EAST, false)
                            .term(BlockStateProperties.SOUTH, false)
                            .term(BlockStateProperties.WEST, false),
                        multivariant2
                    )
                    .with(
                        condition()
                            .term(BlockStateProperties.NORTH, false)
                            .term(BlockStateProperties.EAST, true)
                            .term(BlockStateProperties.SOUTH, false)
                            .term(BlockStateProperties.WEST, false),
                        multivariant2.with(Y_ROT_90)
                    )
                    .with(
                        condition()
                            .term(BlockStateProperties.NORTH, false)
                            .term(BlockStateProperties.EAST, false)
                            .term(BlockStateProperties.SOUTH, true)
                            .term(BlockStateProperties.WEST, false),
                        multivariant3
                    )
                    .with(
                        condition()
                            .term(BlockStateProperties.NORTH, false)
                            .term(BlockStateProperties.EAST, false)
                            .term(BlockStateProperties.SOUTH, false)
                            .term(BlockStateProperties.WEST, true),
                        multivariant3.with(Y_ROT_90)
                    )
                    .with(condition().term(BlockStateProperties.NORTH, true), multivariant4)
                    .with(condition().term(BlockStateProperties.EAST, true), multivariant4.with(Y_ROT_90))
                    .with(condition().term(BlockStateProperties.SOUTH, true), multivariant5)
                    .with(condition().term(BlockStateProperties.WEST, true), multivariant5.with(Y_ROT_90))
            );
    }

    public void createNonTemplateHorizontalBlock(Block horizontalBlock) {
        this.blockStateOutput
            .accept(MultiVariantGenerator.dispatch(horizontalBlock, plainVariant(ModelLocationUtils.getModelLocation(horizontalBlock))).with(ROTATION_HORIZONTAL_FACING));
    }

    public void createLever() {
        MultiVariant multivariant = plainVariant(ModelLocationUtils.getModelLocation(Blocks.LEVER));
        MultiVariant multivariant1 = plainVariant(ModelLocationUtils.getModelLocation(Blocks.LEVER, "_on"));
        this.registerSimpleFlatItemModel(Blocks.LEVER);
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(Blocks.LEVER)
                    .with(createBooleanModelDispatch(BlockStateProperties.POWERED, multivariant, multivariant1))
                    .with(
                        PropertyDispatch.modify(BlockStateProperties.ATTACH_FACE, BlockStateProperties.HORIZONTAL_FACING)
                            .select(AttachFace.CEILING, Direction.NORTH, X_ROT_180.then(Y_ROT_180))
                            .select(AttachFace.CEILING, Direction.EAST, X_ROT_180.then(Y_ROT_270))
                            .select(AttachFace.CEILING, Direction.SOUTH, X_ROT_180)
                            .select(AttachFace.CEILING, Direction.WEST, X_ROT_180.then(Y_ROT_90))
                            .select(AttachFace.FLOOR, Direction.NORTH, NOP)
                            .select(AttachFace.FLOOR, Direction.EAST, Y_ROT_90)
                            .select(AttachFace.FLOOR, Direction.SOUTH, Y_ROT_180)
                            .select(AttachFace.FLOOR, Direction.WEST, Y_ROT_270)
                            .select(AttachFace.WALL, Direction.NORTH, X_ROT_90)
                            .select(AttachFace.WALL, Direction.EAST, X_ROT_90.then(Y_ROT_90))
                            .select(AttachFace.WALL, Direction.SOUTH, X_ROT_90.then(Y_ROT_180))
                            .select(AttachFace.WALL, Direction.WEST, X_ROT_90.then(Y_ROT_270))
                    )
            );
    }

    public void createLilyPad() {
        Identifier identifier = this.createFlatItemModelWithBlockTexture(Items.LILY_PAD, Blocks.LILY_PAD);
        this.registerSimpleTintedItemModel(Blocks.LILY_PAD, identifier, ItemModelUtils.constantTint(-9321636));
        Variant variant = plainModel(ModelLocationUtils.getModelLocation(Blocks.LILY_PAD));
        this.blockStateOutput.accept(MultiVariantGenerator.dispatch(Blocks.LILY_PAD, createRotatedVariants(variant)));
    }

    public void createFrogspawnBlock() {
        this.registerSimpleFlatItemModel(Blocks.FROGSPAWN);
        this.blockStateOutput.accept(createSimpleBlock(Blocks.FROGSPAWN, plainVariant(ModelLocationUtils.getModelLocation(Blocks.FROGSPAWN))));
    }

    public void createNetherPortalBlock() {
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(Blocks.NETHER_PORTAL)
                    .with(
                        PropertyDispatch.initial(BlockStateProperties.HORIZONTAL_AXIS)
                            .select(Direction.Axis.X, plainVariant(ModelLocationUtils.getModelLocation(Blocks.NETHER_PORTAL, "_ns")))
                            .select(Direction.Axis.Z, plainVariant(ModelLocationUtils.getModelLocation(Blocks.NETHER_PORTAL, "_ew")))
                    )
            );
    }

    public void createNetherrack() {
        Variant variant = plainModel(TexturedModel.CUBE.create(Blocks.NETHERRACK, this.modelOutput));
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(
                    Blocks.NETHERRACK,
                    variants(
                        variant,
                        variant.with(X_ROT_90),
                        variant.with(X_ROT_180),
                        variant.with(X_ROT_270),
                        variant.with(Y_ROT_90),
                        variant.with(Y_ROT_90.then(X_ROT_90)),
                        variant.with(Y_ROT_90.then(X_ROT_180)),
                        variant.with(Y_ROT_90.then(X_ROT_270)),
                        variant.with(Y_ROT_180),
                        variant.with(Y_ROT_180.then(X_ROT_90)),
                        variant.with(Y_ROT_180.then(X_ROT_180)),
                        variant.with(Y_ROT_180.then(X_ROT_270)),
                        variant.with(Y_ROT_270),
                        variant.with(Y_ROT_270.then(X_ROT_90)),
                        variant.with(Y_ROT_270.then(X_ROT_180)),
                        variant.with(Y_ROT_270.then(X_ROT_270))
                    )
                )
            );
    }

    public void createObserver() {
        MultiVariant multivariant = plainVariant(ModelLocationUtils.getModelLocation(Blocks.OBSERVER));
        MultiVariant multivariant1 = plainVariant(ModelLocationUtils.getModelLocation(Blocks.OBSERVER, "_on"));
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(Blocks.OBSERVER)
                    .with(createBooleanModelDispatch(BlockStateProperties.POWERED, multivariant1, multivariant))
                    .with(ROTATION_FACING)
            );
    }

    public void createPistons() {
        TextureMapping texturemapping = new TextureMapping()
            .put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(Blocks.PISTON, "_bottom"))
            .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(Blocks.PISTON, "_side"));
        Identifier identifier = TextureMapping.getBlockTexture(Blocks.PISTON, "_top_sticky");
        Identifier identifier1 = TextureMapping.getBlockTexture(Blocks.PISTON, "_top");
        TextureMapping texturemapping1 = texturemapping.copyAndUpdate(TextureSlot.PLATFORM, identifier);
        TextureMapping texturemapping2 = texturemapping.copyAndUpdate(TextureSlot.PLATFORM, identifier1);
        MultiVariant multivariant = plainVariant(ModelLocationUtils.getModelLocation(Blocks.PISTON, "_base"));
        this.createPistonVariant(Blocks.PISTON, multivariant, texturemapping2);
        this.createPistonVariant(Blocks.STICKY_PISTON, multivariant, texturemapping1);
        Identifier identifier2 = ModelTemplates.CUBE_BOTTOM_TOP
            .createWithSuffix(Blocks.PISTON, "_inventory", texturemapping.copyAndUpdate(TextureSlot.TOP, identifier1), this.modelOutput);
        Identifier identifier3 = ModelTemplates.CUBE_BOTTOM_TOP
            .createWithSuffix(Blocks.STICKY_PISTON, "_inventory", texturemapping.copyAndUpdate(TextureSlot.TOP, identifier), this.modelOutput);
        this.registerSimpleItemModel(Blocks.PISTON, identifier2);
        this.registerSimpleItemModel(Blocks.STICKY_PISTON, identifier3);
    }

    public void createPistonVariant(Block block, MultiVariant variants, TextureMapping topTextureMapping) {
        MultiVariant multivariant = plainVariant(ModelTemplates.PISTON.create(block, topTextureMapping, this.modelOutput));
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(block)
                    .with(createBooleanModelDispatch(BlockStateProperties.EXTENDED, variants, multivariant))
                    .with(ROTATION_FACING)
            );
    }

    public void createPistonHeads() {
        TextureMapping texturemapping = new TextureMapping()
            .put(TextureSlot.UNSTICKY, TextureMapping.getBlockTexture(Blocks.PISTON, "_top"))
            .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(Blocks.PISTON, "_side"));
        TextureMapping texturemapping1 = texturemapping.copyAndUpdate(TextureSlot.PLATFORM, TextureMapping.getBlockTexture(Blocks.PISTON, "_top_sticky"));
        TextureMapping texturemapping2 = texturemapping.copyAndUpdate(TextureSlot.PLATFORM, TextureMapping.getBlockTexture(Blocks.PISTON, "_top"));
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(Blocks.PISTON_HEAD)
                    .with(
                        PropertyDispatch.initial(BlockStateProperties.SHORT, BlockStateProperties.PISTON_TYPE)
                            .select(
                                false,
                                PistonType.DEFAULT,
                                plainVariant(ModelTemplates.PISTON_HEAD.createWithSuffix(Blocks.PISTON, "_head", texturemapping2, this.modelOutput))
                            )
                            .select(
                                false,
                                PistonType.STICKY,
                                plainVariant(ModelTemplates.PISTON_HEAD.createWithSuffix(Blocks.PISTON, "_head_sticky", texturemapping1, this.modelOutput))
                            )
                            .select(
                                true,
                                PistonType.DEFAULT,
                                plainVariant(ModelTemplates.PISTON_HEAD_SHORT.createWithSuffix(Blocks.PISTON, "_head_short", texturemapping2, this.modelOutput))
                            )
                            .select(
                                true,
                                PistonType.STICKY,
                                plainVariant(
                                    ModelTemplates.PISTON_HEAD_SHORT.createWithSuffix(Blocks.PISTON, "_head_short_sticky", texturemapping1, this.modelOutput)
                                )
                            )
                    )
                    .with(ROTATION_FACING)
            );
    }

    public void createTrialSpawner() {
        Block block = Blocks.TRIAL_SPAWNER;
        TextureMapping texturemapping = TextureMapping.trialSpawner(block, "_side_inactive", "_top_inactive");
        TextureMapping texturemapping1 = TextureMapping.trialSpawner(block, "_side_active", "_top_active");
        TextureMapping texturemapping2 = TextureMapping.trialSpawner(block, "_side_active", "_top_ejecting_reward");
        TextureMapping texturemapping3 = TextureMapping.trialSpawner(block, "_side_inactive_ominous", "_top_inactive_ominous");
        TextureMapping texturemapping4 = TextureMapping.trialSpawner(block, "_side_active_ominous", "_top_active_ominous");
        TextureMapping texturemapping5 = TextureMapping.trialSpawner(block, "_side_active_ominous", "_top_ejecting_reward_ominous");
        Identifier identifier = ModelTemplates.CUBE_BOTTOM_TOP_INNER_FACES.create(block, texturemapping, this.modelOutput);
        MultiVariant multivariant = plainVariant(identifier);
        MultiVariant multivariant1 = plainVariant(
            ModelTemplates.CUBE_BOTTOM_TOP_INNER_FACES.createWithSuffix(block, "_active", texturemapping1, this.modelOutput)
        );
        MultiVariant multivariant2 = plainVariant(
            ModelTemplates.CUBE_BOTTOM_TOP_INNER_FACES.createWithSuffix(block, "_ejecting_reward", texturemapping2, this.modelOutput)
        );
        MultiVariant multivariant3 = plainVariant(
            ModelTemplates.CUBE_BOTTOM_TOP_INNER_FACES.createWithSuffix(block, "_inactive_ominous", texturemapping3, this.modelOutput)
        );
        MultiVariant multivariant4 = plainVariant(
            ModelTemplates.CUBE_BOTTOM_TOP_INNER_FACES.createWithSuffix(block, "_active_ominous", texturemapping4, this.modelOutput)
        );
        MultiVariant multivariant5 = plainVariant(
            ModelTemplates.CUBE_BOTTOM_TOP_INNER_FACES.createWithSuffix(block, "_ejecting_reward_ominous", texturemapping5, this.modelOutput)
        );
        this.registerSimpleItemModel(block, identifier);
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(block)
                    .with(PropertyDispatch.initial(BlockStateProperties.TRIAL_SPAWNER_STATE, BlockStateProperties.OMINOUS).generate((p_403902_, p_403903_) -> {
                        return switch (p_403902_) {
                            case INACTIVE, COOLDOWN -> p_403903_ ? multivariant3 : multivariant;
                            case WAITING_FOR_PLAYERS, ACTIVE, WAITING_FOR_REWARD_EJECTION -> p_403903_ ? multivariant4 : multivariant1;
                            case EJECTING_REWARD -> p_403903_ ? multivariant5 : multivariant2;
                        };
                    }))
            );
    }

    public void createVault() {
        Block block = Blocks.VAULT;
        TextureMapping texturemapping = TextureMapping.vault(block, "_front_off", "_side_off", "_top", "_bottom");
        TextureMapping texturemapping1 = TextureMapping.vault(block, "_front_on", "_side_on", "_top", "_bottom");
        TextureMapping texturemapping2 = TextureMapping.vault(block, "_front_ejecting", "_side_on", "_top", "_bottom");
        TextureMapping texturemapping3 = TextureMapping.vault(block, "_front_ejecting", "_side_on", "_top_ejecting", "_bottom");
        Identifier identifier = ModelTemplates.VAULT.create(block, texturemapping, this.modelOutput);
        MultiVariant multivariant = plainVariant(identifier);
        MultiVariant multivariant1 = plainVariant(ModelTemplates.VAULT.createWithSuffix(block, "_active", texturemapping1, this.modelOutput));
        MultiVariant multivariant2 = plainVariant(ModelTemplates.VAULT.createWithSuffix(block, "_unlocking", texturemapping2, this.modelOutput));
        MultiVariant multivariant3 = plainVariant(ModelTemplates.VAULT.createWithSuffix(block, "_ejecting_reward", texturemapping3, this.modelOutput));
        TextureMapping texturemapping4 = TextureMapping.vault(block, "_front_off_ominous", "_side_off_ominous", "_top_ominous", "_bottom_ominous");
        TextureMapping texturemapping5 = TextureMapping.vault(block, "_front_on_ominous", "_side_on_ominous", "_top_ominous", "_bottom_ominous");
        TextureMapping texturemapping6 = TextureMapping.vault(block, "_front_ejecting_ominous", "_side_on_ominous", "_top_ominous", "_bottom_ominous");
        TextureMapping texturemapping7 = TextureMapping.vault(block, "_front_ejecting_ominous", "_side_on_ominous", "_top_ejecting_ominous", "_bottom_ominous");
        MultiVariant multivariant4 = plainVariant(ModelTemplates.VAULT.createWithSuffix(block, "_ominous", texturemapping4, this.modelOutput));
        MultiVariant multivariant5 = plainVariant(ModelTemplates.VAULT.createWithSuffix(block, "_active_ominous", texturemapping5, this.modelOutput));
        MultiVariant multivariant6 = plainVariant(ModelTemplates.VAULT.createWithSuffix(block, "_unlocking_ominous", texturemapping6, this.modelOutput));
        MultiVariant multivariant7 = plainVariant(ModelTemplates.VAULT.createWithSuffix(block, "_ejecting_reward_ominous", texturemapping7, this.modelOutput));
        this.registerSimpleItemModel(block, identifier);
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(block).with(PropertyDispatch.initial(VaultBlock.STATE, VaultBlock.OMINOUS).generate((p_403936_, p_403937_) -> {
                    return switch (p_403936_) {
                        case INACTIVE -> p_403937_ ? multivariant4 : multivariant;
                        case ACTIVE -> p_403937_ ? multivariant5 : multivariant1;
                        case UNLOCKING -> p_403937_ ? multivariant6 : multivariant2;
                        case EJECTING -> p_403937_ ? multivariant7 : multivariant3;
                    };
                })).with(ROTATION_HORIZONTAL_FACING)
            );
    }

    public void createSculkSensor() {
        Identifier identifier = ModelLocationUtils.getModelLocation(Blocks.SCULK_SENSOR, "_inactive");
        MultiVariant multivariant = plainVariant(identifier);
        MultiVariant multivariant1 = plainVariant(ModelLocationUtils.getModelLocation(Blocks.SCULK_SENSOR, "_active"));
        this.registerSimpleItemModel(Blocks.SCULK_SENSOR, identifier);
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(Blocks.SCULK_SENSOR)
                    .with(
                        PropertyDispatch.initial(BlockStateProperties.SCULK_SENSOR_PHASE)
                            .generate(
                                p_403854_ -> p_403854_ != SculkSensorPhase.ACTIVE && p_403854_ != SculkSensorPhase.COOLDOWN ? multivariant : multivariant1
                            )
                    )
            );
    }

    public void createCalibratedSculkSensor() {
        Identifier identifier = ModelLocationUtils.getModelLocation(Blocks.CALIBRATED_SCULK_SENSOR, "_inactive");
        MultiVariant multivariant = plainVariant(identifier);
        MultiVariant multivariant1 = plainVariant(ModelLocationUtils.getModelLocation(Blocks.CALIBRATED_SCULK_SENSOR, "_active"));
        this.registerSimpleItemModel(Blocks.CALIBRATED_SCULK_SENSOR, identifier);
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(Blocks.CALIBRATED_SCULK_SENSOR)
                    .with(
                        PropertyDispatch.initial(BlockStateProperties.SCULK_SENSOR_PHASE)
                            .generate(
                                p_403882_ -> p_403882_ != SculkSensorPhase.ACTIVE && p_403882_ != SculkSensorPhase.COOLDOWN ? multivariant : multivariant1
                            )
                    )
                    .with(ROTATION_HORIZONTAL_FACING)
            );
    }

    public void createSculkShrieker() {
        Identifier identifier = ModelTemplates.SCULK_SHRIEKER.create(Blocks.SCULK_SHRIEKER, TextureMapping.sculkShrieker(false), this.modelOutput);
        MultiVariant multivariant = plainVariant(identifier);
        MultiVariant multivariant1 = plainVariant(
            ModelTemplates.SCULK_SHRIEKER.createWithSuffix(Blocks.SCULK_SHRIEKER, "_can_summon", TextureMapping.sculkShrieker(true), this.modelOutput)
        );
        this.registerSimpleItemModel(Blocks.SCULK_SHRIEKER, identifier);
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(Blocks.SCULK_SHRIEKER)
                    .with(createBooleanModelDispatch(BlockStateProperties.CAN_SUMMON, multivariant1, multivariant))
            );
    }

    public void createScaffolding() {
        Identifier identifier = ModelLocationUtils.getModelLocation(Blocks.SCAFFOLDING, "_stable");
        MultiVariant multivariant = plainVariant(identifier);
        MultiVariant multivariant1 = plainVariant(ModelLocationUtils.getModelLocation(Blocks.SCAFFOLDING, "_unstable"));
        this.registerSimpleItemModel(Blocks.SCAFFOLDING, identifier);
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(Blocks.SCAFFOLDING).with(createBooleanModelDispatch(BlockStateProperties.BOTTOM, multivariant1, multivariant))
            );
    }

    public void createCaveVines() {
        MultiVariant multivariant = plainVariant(this.createSuffixedVariant(Blocks.CAVE_VINES, "", ModelTemplates.CROSS, TextureMapping::cross));
        MultiVariant multivariant1 = plainVariant(this.createSuffixedVariant(Blocks.CAVE_VINES, "_lit", ModelTemplates.CROSS, TextureMapping::cross));
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(Blocks.CAVE_VINES).with(createBooleanModelDispatch(BlockStateProperties.BERRIES, multivariant1, multivariant))
            );
        MultiVariant multivariant2 = plainVariant(this.createSuffixedVariant(Blocks.CAVE_VINES_PLANT, "", ModelTemplates.CROSS, TextureMapping::cross));
        MultiVariant multivariant3 = plainVariant(this.createSuffixedVariant(Blocks.CAVE_VINES_PLANT, "_lit", ModelTemplates.CROSS, TextureMapping::cross));
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(Blocks.CAVE_VINES_PLANT)
                    .with(createBooleanModelDispatch(BlockStateProperties.BERRIES, multivariant3, multivariant2))
            );
    }

    public void createRedstoneLamp() {
        MultiVariant multivariant = plainVariant(TexturedModel.CUBE.create(Blocks.REDSTONE_LAMP, this.modelOutput));
        MultiVariant multivariant1 = plainVariant(this.createSuffixedVariant(Blocks.REDSTONE_LAMP, "_on", ModelTemplates.CUBE_ALL, TextureMapping::cube));
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(Blocks.REDSTONE_LAMP).with(createBooleanModelDispatch(BlockStateProperties.LIT, multivariant1, multivariant))
            );
    }

    public void createNormalTorch(Block torchBlock, Block wallTorchBlock) {
        TextureMapping texturemapping = TextureMapping.torch(torchBlock);
        this.blockStateOutput.accept(createSimpleBlock(torchBlock, plainVariant(ModelTemplates.TORCH.create(torchBlock, texturemapping, this.modelOutput))));
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(wallTorchBlock, plainVariant(ModelTemplates.WALL_TORCH.create(wallTorchBlock, texturemapping, this.modelOutput)))
                    .with(ROTATION_TORCH)
            );
        this.registerSimpleFlatItemModel(torchBlock);
    }

    public void createRedstoneTorch() {
        TextureMapping texturemapping = TextureMapping.torch(Blocks.REDSTONE_TORCH);
        TextureMapping texturemapping1 = TextureMapping.torch(TextureMapping.getBlockTexture(Blocks.REDSTONE_TORCH, "_off"));
        MultiVariant multivariant = plainVariant(ModelTemplates.REDSTONE_TORCH.create(Blocks.REDSTONE_TORCH, texturemapping, this.modelOutput));
        MultiVariant multivariant1 = plainVariant(ModelTemplates.TORCH_UNLIT.createWithSuffix(Blocks.REDSTONE_TORCH, "_off", texturemapping1, this.modelOutput));
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(Blocks.REDSTONE_TORCH).with(createBooleanModelDispatch(BlockStateProperties.LIT, multivariant, multivariant1))
            );
        MultiVariant multivariant2 = plainVariant(ModelTemplates.REDSTONE_WALL_TORCH.create(Blocks.REDSTONE_WALL_TORCH, texturemapping, this.modelOutput));
        MultiVariant multivariant3 = plainVariant(
            ModelTemplates.WALL_TORCH_UNLIT.createWithSuffix(Blocks.REDSTONE_WALL_TORCH, "_off", texturemapping1, this.modelOutput)
        );
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(Blocks.REDSTONE_WALL_TORCH)
                    .with(createBooleanModelDispatch(BlockStateProperties.LIT, multivariant2, multivariant3))
                    .with(ROTATION_TORCH)
            );
        this.registerSimpleFlatItemModel(Blocks.REDSTONE_TORCH);
    }

    public void createRepeater() {
        this.registerSimpleFlatItemModel(Items.REPEATER);
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(Blocks.REPEATER)
                    .with(
                        PropertyDispatch.initial(BlockStateProperties.DELAY, BlockStateProperties.LOCKED, BlockStateProperties.POWERED)
                            .generate((p_465414_, p_465415_, p_465416_) -> {
                                StringBuilder stringbuilder = new StringBuilder();
                                stringbuilder.append('_').append(p_465414_).append("tick");
                                if (p_465416_) {
                                    stringbuilder.append("_on");
                                }

                                if (p_465415_) {
                                    stringbuilder.append("_locked");
                                }

                                return plainVariant(TextureMapping.getBlockTexture(Blocks.REPEATER, stringbuilder.toString()));
                            })
                    )
                    .with(ROTATION_HORIZONTAL_FACING_ALT)
            );
    }

    public void createSeaPickle() {
        this.registerSimpleFlatItemModel(Items.SEA_PICKLE);
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(Blocks.SEA_PICKLE)
                    .with(
                        PropertyDispatch.initial(BlockStateProperties.PICKLES, BlockStateProperties.WATERLOGGED)
                            .select(1, false, createRotatedVariants(plainModel(ModelLocationUtils.decorateBlockModelLocation("dead_sea_pickle"))))
                            .select(2, false, createRotatedVariants(plainModel(ModelLocationUtils.decorateBlockModelLocation("two_dead_sea_pickles"))))
                            .select(3, false, createRotatedVariants(plainModel(ModelLocationUtils.decorateBlockModelLocation("three_dead_sea_pickles"))))
                            .select(4, false, createRotatedVariants(plainModel(ModelLocationUtils.decorateBlockModelLocation("four_dead_sea_pickles"))))
                            .select(1, true, createRotatedVariants(plainModel(ModelLocationUtils.decorateBlockModelLocation("sea_pickle"))))
                            .select(2, true, createRotatedVariants(plainModel(ModelLocationUtils.decorateBlockModelLocation("two_sea_pickles"))))
                            .select(3, true, createRotatedVariants(plainModel(ModelLocationUtils.decorateBlockModelLocation("three_sea_pickles"))))
                            .select(4, true, createRotatedVariants(plainModel(ModelLocationUtils.decorateBlockModelLocation("four_sea_pickles"))))
                    )
            );
    }

    public void createSnowBlocks() {
        TextureMapping texturemapping = TextureMapping.cube(Blocks.SNOW);
        MultiVariant multivariant = plainVariant(ModelTemplates.CUBE_ALL.create(Blocks.SNOW_BLOCK, texturemapping, this.modelOutput));
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(Blocks.SNOW)
                    .with(
                        PropertyDispatch.initial(BlockStateProperties.LAYERS)
                            .generate(
                                p_465399_ -> p_465399_ < 8
                                    ? plainVariant(ModelLocationUtils.getModelLocation(Blocks.SNOW, "_height" + p_465399_ * 2))
                                    : multivariant
                            )
                    )
            );
        this.registerSimpleItemModel(Blocks.SNOW, ModelLocationUtils.getModelLocation(Blocks.SNOW, "_height2"));
        this.blockStateOutput.accept(createSimpleBlock(Blocks.SNOW_BLOCK, multivariant));
    }

    public void createStonecutter() {
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(Blocks.STONECUTTER, plainVariant(ModelLocationUtils.getModelLocation(Blocks.STONECUTTER)))
                    .with(ROTATION_HORIZONTAL_FACING)
            );
    }

    public void createStructureBlock() {
        Identifier identifier = TexturedModel.CUBE.create(Blocks.STRUCTURE_BLOCK, this.modelOutput);
        this.registerSimpleItemModel(Blocks.STRUCTURE_BLOCK, identifier);
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(Blocks.STRUCTURE_BLOCK)
                    .with(
                        PropertyDispatch.initial(BlockStateProperties.STRUCTUREBLOCK_MODE)
                            .generate(
                                p_465402_ -> plainVariant(
                                    this.createSuffixedVariant(
                                        Blocks.STRUCTURE_BLOCK, "_" + p_465402_.getSerializedName(), ModelTemplates.CUBE_ALL, TextureMapping::cube
                                    )
                                )
                            )
                    )
            );
    }

    public void createTestBlock() {
        Map<TestBlockMode, Identifier> map = new HashMap<>();

        for (TestBlockMode testblockmode : TestBlockMode.values()) {
            map.put(
                testblockmode,
                this.createSuffixedVariant(Blocks.TEST_BLOCK, "_" + testblockmode.getSerializedName(), ModelTemplates.CUBE_ALL, TextureMapping::cube)
            );
        }

        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(Blocks.TEST_BLOCK)
                    .with(PropertyDispatch.initial(BlockStateProperties.TEST_BLOCK_MODE).generate(p_465371_ -> plainVariant(map.get(p_465371_))))
            );
        this.itemModelOutput
            .accept(
                Items.TEST_BLOCK,
                ItemModelUtils.selectBlockItemProperty(
                    TestBlock.MODE,
                    ItemModelUtils.plainModel(map.get(TestBlockMode.START)),
                    Map.of(
                        TestBlockMode.FAIL,
                        ItemModelUtils.plainModel(map.get(TestBlockMode.FAIL)),
                        TestBlockMode.LOG,
                        ItemModelUtils.plainModel(map.get(TestBlockMode.LOG)),
                        TestBlockMode.ACCEPT,
                        ItemModelUtils.plainModel(map.get(TestBlockMode.ACCEPT))
                    )
                )
            );
    }

    public void createSweetBerryBush() {
        this.registerSimpleFlatItemModel(Items.SWEET_BERRIES);
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(Blocks.SWEET_BERRY_BUSH)
                    .with(
                        PropertyDispatch.initial(BlockStateProperties.AGE_3)
                            .generate(
                                p_465428_ -> plainVariant(
                                    this.createSuffixedVariant(Blocks.SWEET_BERRY_BUSH, "_stage" + p_465428_, ModelTemplates.CROSS, TextureMapping::cross)
                                )
                            )
                    )
            );
    }

    public void createTripwire() {
        this.registerSimpleFlatItemModel(Items.STRING);
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(Blocks.TRIPWIRE)
                    .with(
                        PropertyDispatch.initial(
                                BlockStateProperties.ATTACHED,
                                BlockStateProperties.EAST,
                                BlockStateProperties.NORTH,
                                BlockStateProperties.SOUTH,
                                BlockStateProperties.WEST
                            )
                            .select(false, false, false, false, false, plainVariant(ModelLocationUtils.getModelLocation(Blocks.TRIPWIRE, "_ns")))
                            .select(false, true, false, false, false, plainVariant(ModelLocationUtils.getModelLocation(Blocks.TRIPWIRE, "_n")).with(Y_ROT_90))
                            .select(false, false, true, false, false, plainVariant(ModelLocationUtils.getModelLocation(Blocks.TRIPWIRE, "_n")))
                            .select(false, false, false, true, false, plainVariant(ModelLocationUtils.getModelLocation(Blocks.TRIPWIRE, "_n")).with(Y_ROT_180))
                            .select(false, false, false, false, true, plainVariant(ModelLocationUtils.getModelLocation(Blocks.TRIPWIRE, "_n")).with(Y_ROT_270))
                            .select(false, true, true, false, false, plainVariant(ModelLocationUtils.getModelLocation(Blocks.TRIPWIRE, "_ne")))
                            .select(false, true, false, true, false, plainVariant(ModelLocationUtils.getModelLocation(Blocks.TRIPWIRE, "_ne")).with(Y_ROT_90))
                            .select(false, false, false, true, true, plainVariant(ModelLocationUtils.getModelLocation(Blocks.TRIPWIRE, "_ne")).with(Y_ROT_180))
                            .select(false, false, true, false, true, plainVariant(ModelLocationUtils.getModelLocation(Blocks.TRIPWIRE, "_ne")).with(Y_ROT_270))
                            .select(false, false, true, true, false, plainVariant(ModelLocationUtils.getModelLocation(Blocks.TRIPWIRE, "_ns")))
                            .select(false, true, false, false, true, plainVariant(ModelLocationUtils.getModelLocation(Blocks.TRIPWIRE, "_ns")).with(Y_ROT_90))
                            .select(false, true, true, true, false, plainVariant(ModelLocationUtils.getModelLocation(Blocks.TRIPWIRE, "_nse")))
                            .select(false, true, false, true, true, plainVariant(ModelLocationUtils.getModelLocation(Blocks.TRIPWIRE, "_nse")).with(Y_ROT_90))
                            .select(false, false, true, true, true, plainVariant(ModelLocationUtils.getModelLocation(Blocks.TRIPWIRE, "_nse")).with(Y_ROT_180))
                            .select(false, true, true, false, true, plainVariant(ModelLocationUtils.getModelLocation(Blocks.TRIPWIRE, "_nse")).with(Y_ROT_270))
                            .select(false, true, true, true, true, plainVariant(ModelLocationUtils.getModelLocation(Blocks.TRIPWIRE, "_nsew")))
                            .select(true, false, false, false, false, plainVariant(ModelLocationUtils.getModelLocation(Blocks.TRIPWIRE, "_attached_ns")))
                            .select(true, false, true, false, false, plainVariant(ModelLocationUtils.getModelLocation(Blocks.TRIPWIRE, "_attached_n")))
                            .select(
                                true,
                                false,
                                false,
                                true,
                                false,
                                plainVariant(ModelLocationUtils.getModelLocation(Blocks.TRIPWIRE, "_attached_n")).with(Y_ROT_180)
                            )
                            .select(
                                true,
                                true,
                                false,
                                false,
                                false,
                                plainVariant(ModelLocationUtils.getModelLocation(Blocks.TRIPWIRE, "_attached_n")).with(Y_ROT_90)
                            )
                            .select(
                                true,
                                false,
                                false,
                                false,
                                true,
                                plainVariant(ModelLocationUtils.getModelLocation(Blocks.TRIPWIRE, "_attached_n")).with(Y_ROT_270)
                            )
                            .select(true, true, true, false, false, plainVariant(ModelLocationUtils.getModelLocation(Blocks.TRIPWIRE, "_attached_ne")))
                            .select(
                                true,
                                true,
                                false,
                                true,
                                false,
                                plainVariant(ModelLocationUtils.getModelLocation(Blocks.TRIPWIRE, "_attached_ne")).with(Y_ROT_90)
                            )
                            .select(
                                true,
                                false,
                                false,
                                true,
                                true,
                                plainVariant(ModelLocationUtils.getModelLocation(Blocks.TRIPWIRE, "_attached_ne")).with(Y_ROT_180)
                            )
                            .select(
                                true,
                                false,
                                true,
                                false,
                                true,
                                plainVariant(ModelLocationUtils.getModelLocation(Blocks.TRIPWIRE, "_attached_ne")).with(Y_ROT_270)
                            )
                            .select(true, false, true, true, false, plainVariant(ModelLocationUtils.getModelLocation(Blocks.TRIPWIRE, "_attached_ns")))
                            .select(
                                true,
                                true,
                                false,
                                false,
                                true,
                                plainVariant(ModelLocationUtils.getModelLocation(Blocks.TRIPWIRE, "_attached_ns")).with(Y_ROT_90)
                            )
                            .select(true, true, true, true, false, plainVariant(ModelLocationUtils.getModelLocation(Blocks.TRIPWIRE, "_attached_nse")))
                            .select(
                                true,
                                true,
                                false,
                                true,
                                true,
                                plainVariant(ModelLocationUtils.getModelLocation(Blocks.TRIPWIRE, "_attached_nse")).with(Y_ROT_90)
                            )
                            .select(
                                true,
                                false,
                                true,
                                true,
                                true,
                                plainVariant(ModelLocationUtils.getModelLocation(Blocks.TRIPWIRE, "_attached_nse")).with(Y_ROT_180)
                            )
                            .select(
                                true,
                                true,
                                true,
                                false,
                                true,
                                plainVariant(ModelLocationUtils.getModelLocation(Blocks.TRIPWIRE, "_attached_nse")).with(Y_ROT_270)
                            )
                            .select(true, true, true, true, true, plainVariant(ModelLocationUtils.getModelLocation(Blocks.TRIPWIRE, "_attached_nsew")))
                    )
            );
    }

    public void createTripwireHook() {
        this.registerSimpleFlatItemModel(Blocks.TRIPWIRE_HOOK);
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(Blocks.TRIPWIRE_HOOK)
                    .with(
                        PropertyDispatch.initial(BlockStateProperties.ATTACHED, BlockStateProperties.POWERED)
                            .generate(
                                (p_465424_, p_465425_) -> plainVariant(
                                    ModelLocationUtils.getModelLocation(Blocks.TRIPWIRE_HOOK, (p_465424_ ? "_attached" : "") + (p_465425_ ? "_on" : ""))
                                )
                            )
                    )
                    .with(ROTATION_HORIZONTAL_FACING)
            );
    }

    public Variant createTurtleEggModel(int eggs, String variantName, TextureMapping textureMapping) {
        return switch (eggs) {
            case 1 -> plainModel(
                ModelTemplates.TURTLE_EGG.create(ModelLocationUtils.decorateBlockModelLocation(variantName + "turtle_egg"), textureMapping, this.modelOutput)
            );
            case 2 -> plainModel(
                ModelTemplates.TWO_TURTLE_EGGS
                    .create(ModelLocationUtils.decorateBlockModelLocation("two_" + variantName + "turtle_eggs"), textureMapping, this.modelOutput)
            );
            case 3 -> plainModel(
                ModelTemplates.THREE_TURTLE_EGGS
                    .create(ModelLocationUtils.decorateBlockModelLocation("three_" + variantName + "turtle_eggs"), textureMapping, this.modelOutput)
            );
            case 4 -> plainModel(
                ModelTemplates.FOUR_TURTLE_EGGS
                    .create(ModelLocationUtils.decorateBlockModelLocation("four_" + variantName + "turtle_eggs"), textureMapping, this.modelOutput)
            );
            default -> throw new UnsupportedOperationException();
        };
    }

    public Variant createTurtleEggModel(int eggs, int hatchAmount) {
        return switch (hatchAmount) {
            case 0 -> this.createTurtleEggModel(eggs, "", TextureMapping.cube(TextureMapping.getBlockTexture(Blocks.TURTLE_EGG)));
            case 1 -> this.createTurtleEggModel(
                eggs, "slightly_cracked_", TextureMapping.cube(TextureMapping.getBlockTexture(Blocks.TURTLE_EGG, "_slightly_cracked"))
            );
            case 2 -> this.createTurtleEggModel(
                eggs, "very_cracked_", TextureMapping.cube(TextureMapping.getBlockTexture(Blocks.TURTLE_EGG, "_very_cracked"))
            );
            default -> throw new UnsupportedOperationException();
        };
    }

    public void createTurtleEgg() {
        this.registerSimpleFlatItemModel(Items.TURTLE_EGG);
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(Blocks.TURTLE_EGG)
                    .with(
                        PropertyDispatch.initial(BlockStateProperties.EGGS, BlockStateProperties.HATCH)
                            .generate((p_408968_, p_408969_) -> createRotatedVariants(this.createTurtleEggModel(p_408968_, p_408969_)))
                    )
            );
    }

    public void createDriedGhastBlock() {
        Identifier identifier = ModelLocationUtils.getModelLocation(Blocks.DRIED_GHAST, "_hydration_0");
        this.registerSimpleItemModel(Blocks.DRIED_GHAST, identifier);
        Function<Integer, Identifier> function = p_465431_ -> {
            String s = switch (p_465431_) {
                case 1 -> "_hydration_1";
                case 2 -> "_hydration_2";
                case 3 -> "_hydration_3";
                default -> "_hydration_0";
            };
            TextureMapping texturemapping = TextureMapping.driedGhast(s);
            return ModelTemplates.DRIED_GHAST.createWithSuffix(Blocks.DRIED_GHAST, s, texturemapping, this.modelOutput);
        };
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(Blocks.DRIED_GHAST)
                    .with(PropertyDispatch.initial(DriedGhastBlock.HYDRATION_LEVEL).generate(p_465420_ -> plainVariant(function.apply(p_465420_))))
                    .with(ROTATION_HORIZONTAL_FACING)
            );
    }

    public void createSnifferEgg() {
        this.registerSimpleFlatItemModel(Items.SNIFFER_EGG);
        this.blockStateOutput
            .accept(MultiVariantGenerator.dispatch(Blocks.SNIFFER_EGG).with(PropertyDispatch.initial(SnifferEggBlock.HATCH).generate(p_465368_ -> {
                String s = switch (p_465368_) {
                    case 1 -> "_slightly_cracked";
                    case 2 -> "_very_cracked";
                    default -> "_not_cracked";
                };
                TextureMapping texturemapping = TextureMapping.snifferEgg(s);
                return plainVariant(ModelTemplates.SNIFFER_EGG.createWithSuffix(Blocks.SNIFFER_EGG, s, texturemapping, this.modelOutput));
            })));
    }

    public void createMultiface(Block multifaceBlock) {
        this.registerSimpleFlatItemModel(multifaceBlock);
        this.createMultifaceBlockStates(multifaceBlock);
    }

    public void createMultiface(Block block, Item item) {
        this.registerSimpleFlatItemModel(item);
        this.createMultifaceBlockStates(block);
    }

    public static <T extends Property<?>> Map<T, VariantMutator> selectMultifaceProperties(StateHolder<?, ?> state, Function<Direction, T> facePropertyGetter) {
        Builder<T, VariantMutator> builder = ImmutableMap.builderWithExpectedSize(MULTIFACE_GENERATOR.size());
        MULTIFACE_GENERATOR.forEach((p_403908_, p_403909_) -> {
            T t = facePropertyGetter.apply(p_403908_);
            if (state.hasProperty(t)) {
                builder.put(t, p_403909_);
            }
        });
        return builder.build();
    }

    public void createMultifaceBlockStates(Block block) {
        Map<Property<Boolean>, VariantMutator> map = selectMultifaceProperties(block.defaultBlockState(), MultifaceBlock::getFaceProperty);
        ConditionBuilder conditionbuilder = condition();
        map.forEach((p_403915_, p_403916_) -> conditionbuilder.term((Property<Boolean>)p_403915_, false));
        MultiVariant multivariant = plainVariant(ModelLocationUtils.getModelLocation(block));
        MultiPartGenerator multipartgenerator = MultiPartGenerator.multiPart(block);
        map.forEach((p_408981_, p_408982_) -> {
            multipartgenerator.with(condition().term((Property<Boolean>)p_408981_, true), multivariant.with(p_408982_));
            multipartgenerator.with(conditionbuilder, multivariant.with(p_408982_));
        });
        this.blockStateOutput.accept(multipartgenerator);
    }

    public void createMossyCarpet(Block block) {
        Map<Property<WallSide>, VariantMutator> map = selectMultifaceProperties(block.defaultBlockState(), MossyCarpetBlock::getPropertyForFace);
        ConditionBuilder conditionbuilder = condition().term(MossyCarpetBlock.BASE, false);
        map.forEach((p_403892_, p_403893_) -> conditionbuilder.term((Property<WallSide>)p_403892_, WallSide.NONE));
        MultiVariant multivariant = plainVariant(TexturedModel.CARPET.create(block, this.modelOutput));
        MultiVariant multivariant1 = plainVariant(
            TexturedModel.MOSSY_CARPET_SIDE
                .get(block)
                .updateTextures(p_465430_ -> p_465430_.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(block, "_side_tall")))
                .createWithSuffix(block, "_side_tall", this.modelOutput)
        );
        MultiVariant multivariant2 = plainVariant(
            TexturedModel.MOSSY_CARPET_SIDE
                .get(block)
                .updateTextures(p_465401_ -> p_465401_.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(block, "_side_small")))
                .createWithSuffix(block, "_side_small", this.modelOutput)
        );
        MultiPartGenerator multipartgenerator = MultiPartGenerator.multiPart(block);
        multipartgenerator.with(condition().term(MossyCarpetBlock.BASE, true), multivariant);
        multipartgenerator.with(conditionbuilder, multivariant);
        map.forEach((p_409002_, p_409003_) -> {
            multipartgenerator.with(condition().term((Property<WallSide>)p_409002_, WallSide.TALL), multivariant1.with(p_409003_));
            multipartgenerator.with(condition().term((Property<WallSide>)p_409002_, WallSide.LOW), multivariant2.with(p_409003_));
            multipartgenerator.with(conditionbuilder, multivariant1.with(p_409003_));
        });
        this.blockStateOutput.accept(multipartgenerator);
    }

    public void createHangingMoss(Block block) {
        this.registerSimpleFlatItemModel(block);
        this.blockStateOutput.accept(MultiVariantGenerator.dispatch(block).with(PropertyDispatch.initial(HangingMossBlock.TIP).generate(p_465413_ -> {
            String s = p_465413_ ? "_tip" : "";
            TextureMapping texturemapping = TextureMapping.cross(TextureMapping.getBlockTexture(block, s));
            return plainVariant(BlockModelGenerators.PlantType.NOT_TINTED.getCross().createWithSuffix(block, s, texturemapping, this.modelOutput));
        })));
    }

    public void createSculkCatalyst() {
        Identifier identifier = TextureMapping.getBlockTexture(Blocks.SCULK_CATALYST, "_bottom");
        TextureMapping texturemapping = new TextureMapping()
            .put(TextureSlot.BOTTOM, identifier)
            .put(TextureSlot.TOP, TextureMapping.getBlockTexture(Blocks.SCULK_CATALYST, "_top"))
            .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(Blocks.SCULK_CATALYST, "_side"));
        TextureMapping texturemapping1 = new TextureMapping()
            .put(TextureSlot.BOTTOM, identifier)
            .put(TextureSlot.TOP, TextureMapping.getBlockTexture(Blocks.SCULK_CATALYST, "_top_bloom"))
            .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(Blocks.SCULK_CATALYST, "_side_bloom"));
        Identifier identifier1 = ModelTemplates.CUBE_BOTTOM_TOP.create(Blocks.SCULK_CATALYST, texturemapping, this.modelOutput);
        MultiVariant multivariant = plainVariant(identifier1);
        MultiVariant multivariant1 = plainVariant(
            ModelTemplates.CUBE_BOTTOM_TOP.createWithSuffix(Blocks.SCULK_CATALYST, "_bloom", texturemapping1, this.modelOutput)
        );
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(Blocks.SCULK_CATALYST)
                    .with(PropertyDispatch.initial(BlockStateProperties.BLOOM).generate(p_403919_ -> p_403919_ ? multivariant1 : multivariant))
            );
        this.registerSimpleItemModel(Blocks.SCULK_CATALYST, identifier1);
    }

    public void createShelf(Block shelfBlock, Block particleBlock) {
        TextureMapping texturemapping = new TextureMapping()
            .put(TextureSlot.ALL, TextureMapping.getBlockTexture(shelfBlock))
            .put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(particleBlock));
        MultiPartGenerator multipartgenerator = MultiPartGenerator.multiPart(shelfBlock);
        this.addShelfPart(shelfBlock, texturemapping, multipartgenerator, ModelTemplates.SHELF_BODY, null, null);
        this.addShelfPart(shelfBlock, texturemapping, multipartgenerator, ModelTemplates.SHELF_UNPOWERED, false, null);
        this.addShelfPart(shelfBlock, texturemapping, multipartgenerator, ModelTemplates.SHELF_UNCONNECTED, true, SideChainPart.UNCONNECTED);
        this.addShelfPart(shelfBlock, texturemapping, multipartgenerator, ModelTemplates.SHELF_LEFT, true, SideChainPart.LEFT);
        this.addShelfPart(shelfBlock, texturemapping, multipartgenerator, ModelTemplates.SHELF_CENTER, true, SideChainPart.CENTER);
        this.addShelfPart(shelfBlock, texturemapping, multipartgenerator, ModelTemplates.SHELF_RIGHT, true, SideChainPart.RIGHT);
        this.blockStateOutput.accept(multipartgenerator);
        this.registerSimpleItemModel(shelfBlock, ModelTemplates.SHELF_INVENTORY.create(shelfBlock, texturemapping, this.modelOutput));
    }

    public void addShelfPart(
        Block block,
        TextureMapping textureMapping,
        MultiPartGenerator generator,
        ModelTemplate template,
        @Nullable Boolean powered,
        @Nullable SideChainPart sideChainPart
    ) {
        MultiVariant multivariant = plainVariant(template.create(block, textureMapping, this.modelOutput));
        forEachHorizontalDirection((p_432196_, p_432197_) -> generator.with(shelfCondition(p_432196_, powered, sideChainPart), multivariant.with(p_432197_)));
    }

    public static void forEachHorizontalDirection(BiConsumer<Direction, VariantMutator> action) {
        List.of(Pair.of(Direction.NORTH, NOP), Pair.of(Direction.EAST, Y_ROT_90), Pair.of(Direction.SOUTH, Y_ROT_180), Pair.of(Direction.WEST, Y_ROT_270))
            .forEach(p_432199_ -> {
                Direction direction = p_432199_.getFirst();
                VariantMutator variantmutator = p_432199_.getSecond();
                action.accept(direction, variantmutator);
            });
    }

    public static Condition shelfCondition(Direction direction, @Nullable Boolean powered, @Nullable SideChainPart sideChainPart) {
        ConditionBuilder conditionbuilder = condition(BlockStateProperties.HORIZONTAL_FACING, direction);
        if (powered == null) {
            return conditionbuilder.build();
        } else {
            ConditionBuilder conditionbuilder1 = condition(BlockStateProperties.POWERED, powered);
            return sideChainPart != null
                ? and(conditionbuilder, conditionbuilder1, condition(BlockStateProperties.SIDE_CHAIN_PART, sideChainPart))
                : and(conditionbuilder, conditionbuilder1);
        }
    }

    public void createChiseledBookshelf() {
        Block block = Blocks.CHISELED_BOOKSHELF;
        MultiVariant multivariant = plainVariant(ModelLocationUtils.getModelLocation(block));
        MultiPartGenerator multipartgenerator = MultiPartGenerator.multiPart(block);
        forEachHorizontalDirection((p_432202_, p_432203_) -> {
            Condition condition = condition().term(BlockStateProperties.HORIZONTAL_FACING, p_432202_).build();
            multipartgenerator.with(condition, multivariant.with(p_432203_).with(UV_LOCK));
            this.addSlotStateAndRotationVariants(multipartgenerator, condition, p_432203_);
        });
        this.blockStateOutput.accept(multipartgenerator);
        this.registerSimpleItemModel(block, ModelLocationUtils.getModelLocation(block, "_inventory"));
        CHISELED_BOOKSHELF_SLOT_MODEL_CACHE.clear();
    }

    public void addSlotStateAndRotationVariants(MultiPartGenerator generator, Condition condition, VariantMutator rotation) {
        List.of(
                Pair.of(ChiseledBookShelfBlock.SLOT_0_OCCUPIED, ModelTemplates.CHISELED_BOOKSHELF_SLOT_TOP_LEFT),
                Pair.of(ChiseledBookShelfBlock.SLOT_1_OCCUPIED, ModelTemplates.CHISELED_BOOKSHELF_SLOT_TOP_MID),
                Pair.of(ChiseledBookShelfBlock.SLOT_2_OCCUPIED, ModelTemplates.CHISELED_BOOKSHELF_SLOT_TOP_RIGHT),
                Pair.of(ChiseledBookShelfBlock.SLOT_3_OCCUPIED, ModelTemplates.CHISELED_BOOKSHELF_SLOT_BOTTOM_LEFT),
                Pair.of(ChiseledBookShelfBlock.SLOT_4_OCCUPIED, ModelTemplates.CHISELED_BOOKSHELF_SLOT_BOTTOM_MID),
                Pair.of(ChiseledBookShelfBlock.SLOT_5_OCCUPIED, ModelTemplates.CHISELED_BOOKSHELF_SLOT_BOTTOM_RIGHT)
            )
            .forEach(p_403863_ -> {
                BooleanProperty booleanproperty = p_403863_.getFirst();
                ModelTemplate modeltemplate = p_403863_.getSecond();
                this.addBookSlotModel(generator, condition, rotation, booleanproperty, modeltemplate, true);
                this.addBookSlotModel(generator, condition, rotation, booleanproperty, modeltemplate, false);
            });
    }

    public void addBookSlotModel(
        MultiPartGenerator generator, Condition conditon, VariantMutator rotation, BooleanProperty hasBookProperty, ModelTemplate template, boolean hasBook
    ) {
        String s = hasBook ? "_occupied" : "_empty";
        TextureMapping texturemapping = new TextureMapping().put(TextureSlot.TEXTURE, TextureMapping.getBlockTexture(Blocks.CHISELED_BOOKSHELF, s));
        BlockModelGenerators.BookSlotModelCacheKey blockmodelgenerators$bookslotmodelcachekey = new BlockModelGenerators.BookSlotModelCacheKey(template, s);
        MultiVariant multivariant = plainVariant(
            CHISELED_BOOKSHELF_SLOT_MODEL_CACHE.computeIfAbsent(
                blockmodelgenerators$bookslotmodelcachekey,
                p_465382_ -> template.createWithSuffix(Blocks.CHISELED_BOOKSHELF, s, texturemapping, this.modelOutput)
            )
        );
        generator.with(
            new CombinedCondition(CombinedCondition.Operation.AND, List.of(conditon, condition().term(hasBookProperty, hasBook).build())),
            multivariant.with(rotation)
        );
    }

    public void createMagmaBlock() {
        MultiVariant multivariant = plainVariant(
            ModelTemplates.CUBE_ALL.create(Blocks.MAGMA_BLOCK, TextureMapping.cube(ModelLocationUtils.decorateBlockModelLocation("magma")), this.modelOutput)
        );
        this.blockStateOutput.accept(createSimpleBlock(Blocks.MAGMA_BLOCK, multivariant));
    }

    public void createShulkerBox(Block block, @Nullable DyeColor color) {
        this.createParticleOnlyBlock(block);
        Item item = block.asItem();
        Identifier identifier = ModelTemplates.SHULKER_BOX_INVENTORY.create(item, TextureMapping.particle(block), this.modelOutput);
        ItemModel.Unbaked itemmodel$unbaked = color != null
            ? ItemModelUtils.specialModel(identifier, new ShulkerBoxSpecialRenderer.Unbaked(color))
            : ItemModelUtils.specialModel(identifier, new ShulkerBoxSpecialRenderer.Unbaked());
        this.itemModelOutput.accept(item, itemmodel$unbaked);
    }

    public void createGrowingPlant(Block plantBlock, Block tipBlock, BlockModelGenerators.PlantType plantType) {
        this.createCrossBlock(plantBlock, plantType);
        this.createCrossBlock(tipBlock, plantType);
    }

    public void createInfestedStone() {
        Identifier identifier = ModelLocationUtils.getModelLocation(Blocks.STONE);
        Variant variant = plainModel(identifier);
        Variant variant1 = plainModel(ModelLocationUtils.getModelLocation(Blocks.STONE, "_mirrored"));
        this.blockStateOutput.accept(MultiVariantGenerator.dispatch(Blocks.INFESTED_STONE, createRotatedVariants(variant, variant1)));
        this.registerSimpleItemModel(Blocks.INFESTED_STONE, identifier);
    }

    public void createInfestedDeepslate() {
        Identifier identifier = ModelLocationUtils.getModelLocation(Blocks.DEEPSLATE);
        Variant variant = plainModel(identifier);
        Variant variant1 = plainModel(ModelLocationUtils.getModelLocation(Blocks.DEEPSLATE, "_mirrored"));
        this.blockStateOutput
            .accept(MultiVariantGenerator.dispatch(Blocks.INFESTED_DEEPSLATE, createRotatedVariants(variant, variant1)).with(createRotatedPillar()));
        this.registerSimpleItemModel(Blocks.INFESTED_DEEPSLATE, identifier);
    }

    public void createNetherRoots(Block plantBlock, Block pottedPlantBlock) {
        this.createCrossBlockWithDefaultItem(plantBlock, BlockModelGenerators.PlantType.NOT_TINTED);
        TextureMapping texturemapping = TextureMapping.plant(TextureMapping.getBlockTexture(plantBlock, "_pot"));
        MultiVariant multivariant = plainVariant(BlockModelGenerators.PlantType.NOT_TINTED.getCrossPot().create(pottedPlantBlock, texturemapping, this.modelOutput));
        this.blockStateOutput.accept(createSimpleBlock(pottedPlantBlock, multivariant));
    }

    public void createRespawnAnchor() {
        Identifier identifier = TextureMapping.getBlockTexture(Blocks.RESPAWN_ANCHOR, "_bottom");
        Identifier identifier1 = TextureMapping.getBlockTexture(Blocks.RESPAWN_ANCHOR, "_top_off");
        Identifier identifier2 = TextureMapping.getBlockTexture(Blocks.RESPAWN_ANCHOR, "_top");
        Identifier[] aidentifier = new Identifier[5];

        for (int i = 0; i < 5; i++) {
            TextureMapping texturemapping = new TextureMapping()
                .put(TextureSlot.BOTTOM, identifier)
                .put(TextureSlot.TOP, i == 0 ? identifier1 : identifier2)
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(Blocks.RESPAWN_ANCHOR, "_side" + i));
            aidentifier[i] = ModelTemplates.CUBE_BOTTOM_TOP.createWithSuffix(Blocks.RESPAWN_ANCHOR, "_" + i, texturemapping, this.modelOutput);
        }

        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(Blocks.RESPAWN_ANCHOR)
                    .with(PropertyDispatch.initial(BlockStateProperties.RESPAWN_ANCHOR_CHARGES).generate(p_465388_ -> plainVariant(aidentifier[p_465388_])))
            );
        this.registerSimpleItemModel(Blocks.RESPAWN_ANCHOR, aidentifier[0]);
    }

    public static VariantMutator applyRotation(FrontAndTop rotation) {
        return switch (rotation) {
            case DOWN_NORTH -> X_ROT_90;
            case DOWN_SOUTH -> X_ROT_90.then(Y_ROT_180);
            case DOWN_WEST -> X_ROT_90.then(Y_ROT_270);
            case DOWN_EAST -> X_ROT_90.then(Y_ROT_90);
            case UP_NORTH -> X_ROT_270.then(Y_ROT_180);
            case UP_SOUTH -> X_ROT_270;
            case UP_WEST -> X_ROT_270.then(Y_ROT_90);
            case UP_EAST -> X_ROT_270.then(Y_ROT_270);
            case NORTH_UP -> NOP;
            case SOUTH_UP -> Y_ROT_180;
            case WEST_UP -> Y_ROT_270;
            case EAST_UP -> Y_ROT_90;
        };
    }

    public void createJigsaw() {
        Identifier identifier = TextureMapping.getBlockTexture(Blocks.JIGSAW, "_top");
        Identifier identifier1 = TextureMapping.getBlockTexture(Blocks.JIGSAW, "_bottom");
        Identifier identifier2 = TextureMapping.getBlockTexture(Blocks.JIGSAW, "_side");
        Identifier identifier3 = TextureMapping.getBlockTexture(Blocks.JIGSAW, "_lock");
        TextureMapping texturemapping = new TextureMapping()
            .put(TextureSlot.DOWN, identifier2)
            .put(TextureSlot.WEST, identifier2)
            .put(TextureSlot.EAST, identifier2)
            .put(TextureSlot.PARTICLE, identifier)
            .put(TextureSlot.NORTH, identifier)
            .put(TextureSlot.SOUTH, identifier1)
            .put(TextureSlot.UP, identifier3);
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(
                        Blocks.JIGSAW, plainVariant(ModelTemplates.CUBE_DIRECTIONAL.create(Blocks.JIGSAW, texturemapping, this.modelOutput))
                    )
                    .with(PropertyDispatch.modify(BlockStateProperties.ORIENTATION).generate(BlockModelGenerators::applyRotation))
            );
    }

    public void createPetrifiedOakSlab() {
        Block block = Blocks.OAK_PLANKS;
        MultiVariant multivariant = plainVariant(ModelLocationUtils.getModelLocation(block));
        TextureMapping texturemapping = TextureMapping.cube(block);
        Block block1 = Blocks.PETRIFIED_OAK_SLAB;
        MultiVariant multivariant1 = plainVariant(ModelTemplates.SLAB_BOTTOM.create(block1, texturemapping, this.modelOutput));
        MultiVariant multivariant2 = plainVariant(ModelTemplates.SLAB_TOP.create(block1, texturemapping, this.modelOutput));
        this.blockStateOutput.accept(createSlab(block1, multivariant1, multivariant2, multivariant));
    }

    public void createHead(Block headBlock, Block wallHeadBlock, SkullBlock.Type type, Identifier modelLocation) {
        MultiVariant multivariant = plainVariant(ModelLocationUtils.decorateBlockModelLocation("skull"));
        this.blockStateOutput.accept(createSimpleBlock(headBlock, multivariant));
        this.blockStateOutput.accept(createSimpleBlock(wallHeadBlock, multivariant));
        if (type == SkullBlock.Types.PLAYER) {
            this.itemModelOutput.accept(headBlock.asItem(), ItemModelUtils.specialModel(modelLocation, new PlayerHeadSpecialRenderer.Unbaked()));
        } else {
            this.itemModelOutput.accept(headBlock.asItem(), ItemModelUtils.specialModel(modelLocation, new SkullSpecialRenderer.Unbaked(type)));
        }
    }

    public void createHeads() {
        Identifier identifier = ModelLocationUtils.decorateItemModelLocation("template_skull");
        this.createHead(Blocks.CREEPER_HEAD, Blocks.CREEPER_WALL_HEAD, SkullBlock.Types.CREEPER, identifier);
        this.createHead(Blocks.PLAYER_HEAD, Blocks.PLAYER_WALL_HEAD, SkullBlock.Types.PLAYER, identifier);
        this.createHead(Blocks.ZOMBIE_HEAD, Blocks.ZOMBIE_WALL_HEAD, SkullBlock.Types.ZOMBIE, identifier);
        this.createHead(Blocks.SKELETON_SKULL, Blocks.SKELETON_WALL_SKULL, SkullBlock.Types.SKELETON, identifier);
        this.createHead(Blocks.WITHER_SKELETON_SKULL, Blocks.WITHER_SKELETON_WALL_SKULL, SkullBlock.Types.WITHER_SKELETON, identifier);
        this.createHead(Blocks.PIGLIN_HEAD, Blocks.PIGLIN_WALL_HEAD, SkullBlock.Types.PIGLIN, identifier);
        this.createHead(Blocks.DRAGON_HEAD, Blocks.DRAGON_WALL_HEAD, SkullBlock.Types.DRAGON, ModelLocationUtils.getModelLocation(Items.DRAGON_HEAD));
    }

    public void createCopperGolemStatues() {
        this.createCopperGolemStatue(Blocks.COPPER_GOLEM_STATUE, Blocks.COPPER_BLOCK, WeatheringCopper.WeatherState.UNAFFECTED);
        this.createCopperGolemStatue(Blocks.EXPOSED_COPPER_GOLEM_STATUE, Blocks.EXPOSED_COPPER, WeatheringCopper.WeatherState.EXPOSED);
        this.createCopperGolemStatue(Blocks.WEATHERED_COPPER_GOLEM_STATUE, Blocks.WEATHERED_COPPER, WeatheringCopper.WeatherState.WEATHERED);
        this.createCopperGolemStatue(Blocks.OXIDIZED_COPPER_GOLEM_STATUE, Blocks.OXIDIZED_COPPER, WeatheringCopper.WeatherState.OXIDIZED);
        this.copyModel(Blocks.COPPER_GOLEM_STATUE, Blocks.WAXED_COPPER_GOLEM_STATUE);
        this.copyModel(Blocks.EXPOSED_COPPER_GOLEM_STATUE, Blocks.WAXED_EXPOSED_COPPER_GOLEM_STATUE);
        this.copyModel(Blocks.WEATHERED_COPPER_GOLEM_STATUE, Blocks.WAXED_WEATHERED_COPPER_GOLEM_STATUE);
        this.copyModel(Blocks.OXIDIZED_COPPER_GOLEM_STATUE, Blocks.WAXED_OXIDIZED_COPPER_GOLEM_STATUE);
    }

    public void createCopperGolemStatue(Block statueBlock, Block copperBlock, WeatheringCopper.WeatherState weatherState) {
        MultiVariant multivariant = plainVariant(
            ModelTemplates.PARTICLE_ONLY.create(statueBlock, TextureMapping.particle(TextureMapping.getBlockTexture(copperBlock)), this.modelOutput)
        );
        Identifier identifier = ModelLocationUtils.decorateItemModelLocation("template_copper_golem_statue");
        this.blockStateOutput.accept(createSimpleBlock(statueBlock, multivariant));
        this.itemModelOutput
            .accept(
                statueBlock.asItem(),
                ItemModelUtils.selectBlockItemProperty(
                    CopperGolemStatueBlock.POSE,
                    ItemModelUtils.specialModel(identifier, new CopperGolemStatueSpecialRenderer.Unbaked(weatherState, CopperGolemStatueBlock.Pose.STANDING)),
                    Map.of(
                        CopperGolemStatueBlock.Pose.SITTING,
                        ItemModelUtils.specialModel(identifier, new CopperGolemStatueSpecialRenderer.Unbaked(weatherState, CopperGolemStatueBlock.Pose.SITTING)),
                        CopperGolemStatueBlock.Pose.STAR,
                        ItemModelUtils.specialModel(identifier, new CopperGolemStatueSpecialRenderer.Unbaked(weatherState, CopperGolemStatueBlock.Pose.STAR)),
                        CopperGolemStatueBlock.Pose.RUNNING,
                        ItemModelUtils.specialModel(identifier, new CopperGolemStatueSpecialRenderer.Unbaked(weatherState, CopperGolemStatueBlock.Pose.RUNNING))
                    )
                )
            );
    }

    public void createBanner(Block block, Block wallBlock, DyeColor color) {
        MultiVariant multivariant = plainVariant(ModelLocationUtils.decorateBlockModelLocation("banner"));
        Identifier identifier = ModelLocationUtils.decorateItemModelLocation("template_banner");
        this.blockStateOutput.accept(createSimpleBlock(block, multivariant));
        this.blockStateOutput.accept(createSimpleBlock(wallBlock, multivariant));
        Item item = block.asItem();
        this.itemModelOutput.accept(item, ItemModelUtils.specialModel(identifier, new BannerSpecialRenderer.Unbaked(color)));
    }

    public void createBanners() {
        this.createBanner(Blocks.WHITE_BANNER, Blocks.WHITE_WALL_BANNER, DyeColor.WHITE);
        this.createBanner(Blocks.ORANGE_BANNER, Blocks.ORANGE_WALL_BANNER, DyeColor.ORANGE);
        this.createBanner(Blocks.MAGENTA_BANNER, Blocks.MAGENTA_WALL_BANNER, DyeColor.MAGENTA);
        this.createBanner(Blocks.LIGHT_BLUE_BANNER, Blocks.LIGHT_BLUE_WALL_BANNER, DyeColor.LIGHT_BLUE);
        this.createBanner(Blocks.YELLOW_BANNER, Blocks.YELLOW_WALL_BANNER, DyeColor.YELLOW);
        this.createBanner(Blocks.LIME_BANNER, Blocks.LIME_WALL_BANNER, DyeColor.LIME);
        this.createBanner(Blocks.PINK_BANNER, Blocks.PINK_WALL_BANNER, DyeColor.PINK);
        this.createBanner(Blocks.GRAY_BANNER, Blocks.GRAY_WALL_BANNER, DyeColor.GRAY);
        this.createBanner(Blocks.LIGHT_GRAY_BANNER, Blocks.LIGHT_GRAY_WALL_BANNER, DyeColor.LIGHT_GRAY);
        this.createBanner(Blocks.CYAN_BANNER, Blocks.CYAN_WALL_BANNER, DyeColor.CYAN);
        this.createBanner(Blocks.PURPLE_BANNER, Blocks.PURPLE_WALL_BANNER, DyeColor.PURPLE);
        this.createBanner(Blocks.BLUE_BANNER, Blocks.BLUE_WALL_BANNER, DyeColor.BLUE);
        this.createBanner(Blocks.BROWN_BANNER, Blocks.BROWN_WALL_BANNER, DyeColor.BROWN);
        this.createBanner(Blocks.GREEN_BANNER, Blocks.GREEN_WALL_BANNER, DyeColor.GREEN);
        this.createBanner(Blocks.RED_BANNER, Blocks.RED_WALL_BANNER, DyeColor.RED);
        this.createBanner(Blocks.BLACK_BANNER, Blocks.BLACK_WALL_BANNER, DyeColor.BLACK);
    }

    public void createChest(Block chestBlock, Block particleBlock, Identifier texture, boolean useGiftTexture) {
        this.createParticleOnlyBlock(chestBlock, particleBlock);
        Item item = chestBlock.asItem();
        Identifier identifier = ModelTemplates.CHEST_INVENTORY.create(item, TextureMapping.particle(particleBlock), this.modelOutput);
        ItemModel.Unbaked itemmodel$unbaked = ItemModelUtils.specialModel(identifier, new ChestSpecialRenderer.Unbaked(texture));
        if (useGiftTexture) {
            ItemModel.Unbaked itemmodel$unbaked1 = ItemModelUtils.specialModel(
                identifier, new ChestSpecialRenderer.Unbaked(ChestSpecialRenderer.GIFT_CHEST_TEXTURE)
            );
            this.itemModelOutput.accept(item, ItemModelUtils.isXmas(itemmodel$unbaked1, itemmodel$unbaked));
        } else {
            this.itemModelOutput.accept(item, itemmodel$unbaked);
        }
    }

    public void createChests() {
        this.createChest(Blocks.CHEST, Blocks.OAK_PLANKS, ChestSpecialRenderer.NORMAL_CHEST_TEXTURE, true);
        this.createChest(Blocks.TRAPPED_CHEST, Blocks.OAK_PLANKS, ChestSpecialRenderer.TRAPPED_CHEST_TEXTURE, true);
        this.createChest(Blocks.ENDER_CHEST, Blocks.OBSIDIAN, ChestSpecialRenderer.ENDER_CHEST_TEXTURE, false);
    }

    public void createCopperChests() {
        this.createChest(Blocks.COPPER_CHEST, Blocks.COPPER_BLOCK, ChestSpecialRenderer.COPPER_CHEST_TEXTURE, false);
        this.createChest(Blocks.EXPOSED_COPPER_CHEST, Blocks.EXPOSED_COPPER, ChestSpecialRenderer.EXPOSED_COPPER_CHEST_TEXTURE, false);
        this.createChest(Blocks.WEATHERED_COPPER_CHEST, Blocks.WEATHERED_COPPER, ChestSpecialRenderer.WEATHERED_COPPER_CHEST_TEXTURE, false);
        this.createChest(Blocks.OXIDIZED_COPPER_CHEST, Blocks.OXIDIZED_COPPER, ChestSpecialRenderer.OXIDIZED_COPPER_CHEST_TEXTURE, false);
        this.copyModel(Blocks.COPPER_CHEST, Blocks.WAXED_COPPER_CHEST);
        this.copyModel(Blocks.EXPOSED_COPPER_CHEST, Blocks.WAXED_EXPOSED_COPPER_CHEST);
        this.copyModel(Blocks.WEATHERED_COPPER_CHEST, Blocks.WAXED_WEATHERED_COPPER_CHEST);
        this.copyModel(Blocks.OXIDIZED_COPPER_CHEST, Blocks.WAXED_OXIDIZED_COPPER_CHEST);
    }

    public void createBed(Block block, Block particleBlock, DyeColor color) {
        MultiVariant multivariant = plainVariant(ModelLocationUtils.decorateBlockModelLocation("bed"));
        this.blockStateOutput.accept(createSimpleBlock(block, multivariant));
        Item item = block.asItem();
        Identifier identifier = ModelTemplates.BED_INVENTORY
            .create(ModelLocationUtils.getModelLocation(item), TextureMapping.particle(particleBlock), this.modelOutput);
        this.itemModelOutput.accept(item, ItemModelUtils.specialModel(identifier, new BedSpecialRenderer.Unbaked(color)));
    }

    public void createBeds() {
        this.createBed(Blocks.WHITE_BED, Blocks.WHITE_WOOL, DyeColor.WHITE);
        this.createBed(Blocks.ORANGE_BED, Blocks.ORANGE_WOOL, DyeColor.ORANGE);
        this.createBed(Blocks.MAGENTA_BED, Blocks.MAGENTA_WOOL, DyeColor.MAGENTA);
        this.createBed(Blocks.LIGHT_BLUE_BED, Blocks.LIGHT_BLUE_WOOL, DyeColor.LIGHT_BLUE);
        this.createBed(Blocks.YELLOW_BED, Blocks.YELLOW_WOOL, DyeColor.YELLOW);
        this.createBed(Blocks.LIME_BED, Blocks.LIME_WOOL, DyeColor.LIME);
        this.createBed(Blocks.PINK_BED, Blocks.PINK_WOOL, DyeColor.PINK);
        this.createBed(Blocks.GRAY_BED, Blocks.GRAY_WOOL, DyeColor.GRAY);
        this.createBed(Blocks.LIGHT_GRAY_BED, Blocks.LIGHT_GRAY_WOOL, DyeColor.LIGHT_GRAY);
        this.createBed(Blocks.CYAN_BED, Blocks.CYAN_WOOL, DyeColor.CYAN);
        this.createBed(Blocks.PURPLE_BED, Blocks.PURPLE_WOOL, DyeColor.PURPLE);
        this.createBed(Blocks.BLUE_BED, Blocks.BLUE_WOOL, DyeColor.BLUE);
        this.createBed(Blocks.BROWN_BED, Blocks.BROWN_WOOL, DyeColor.BROWN);
        this.createBed(Blocks.GREEN_BED, Blocks.GREEN_WOOL, DyeColor.GREEN);
        this.createBed(Blocks.RED_BED, Blocks.RED_WOOL, DyeColor.RED);
        this.createBed(Blocks.BLACK_BED, Blocks.BLACK_WOOL, DyeColor.BLACK);
    }

    public void generateSimpleSpecialItemModel(Block block, SpecialModelRenderer.Unbaked specialModel) {
        Item item = block.asItem();
        Identifier identifier = ModelLocationUtils.getModelLocation(item);
        this.itemModelOutput.accept(item, ItemModelUtils.specialModel(identifier, specialModel));
    }

    public void run() {
        BlockFamilies.getAllFamilies()
            .filter(BlockFamily::shouldGenerateModel)
            .forEach(p_386718_ -> this.family(p_386718_.getBaseBlock()).generateFor(p_386718_));
        this.family(Blocks.CUT_COPPER)
            .generateFor(BlockFamilies.CUT_COPPER)
            .donateModelTo(Blocks.CUT_COPPER, Blocks.WAXED_CUT_COPPER)
            .donateModelTo(Blocks.CHISELED_COPPER, Blocks.WAXED_CHISELED_COPPER)
            .generateFor(BlockFamilies.WAXED_CUT_COPPER);
        this.family(Blocks.EXPOSED_CUT_COPPER)
            .generateFor(BlockFamilies.EXPOSED_CUT_COPPER)
            .donateModelTo(Blocks.EXPOSED_CUT_COPPER, Blocks.WAXED_EXPOSED_CUT_COPPER)
            .donateModelTo(Blocks.EXPOSED_CHISELED_COPPER, Blocks.WAXED_EXPOSED_CHISELED_COPPER)
            .generateFor(BlockFamilies.WAXED_EXPOSED_CUT_COPPER);
        this.family(Blocks.WEATHERED_CUT_COPPER)
            .generateFor(BlockFamilies.WEATHERED_CUT_COPPER)
            .donateModelTo(Blocks.WEATHERED_CUT_COPPER, Blocks.WAXED_WEATHERED_CUT_COPPER)
            .donateModelTo(Blocks.WEATHERED_CHISELED_COPPER, Blocks.WAXED_WEATHERED_CHISELED_COPPER)
            .generateFor(BlockFamilies.WAXED_WEATHERED_CUT_COPPER);
        this.family(Blocks.OXIDIZED_CUT_COPPER)
            .generateFor(BlockFamilies.OXIDIZED_CUT_COPPER)
            .donateModelTo(Blocks.OXIDIZED_CUT_COPPER, Blocks.WAXED_OXIDIZED_CUT_COPPER)
            .donateModelTo(Blocks.OXIDIZED_CHISELED_COPPER, Blocks.WAXED_OXIDIZED_CHISELED_COPPER)
            .generateFor(BlockFamilies.WAXED_OXIDIZED_CUT_COPPER);
        this.createCopperBulb(Blocks.COPPER_BULB);
        this.createCopperBulb(Blocks.EXPOSED_COPPER_BULB);
        this.createCopperBulb(Blocks.WEATHERED_COPPER_BULB);
        this.createCopperBulb(Blocks.OXIDIZED_COPPER_BULB);
        this.copyCopperBulbModel(Blocks.COPPER_BULB, Blocks.WAXED_COPPER_BULB);
        this.copyCopperBulbModel(Blocks.EXPOSED_COPPER_BULB, Blocks.WAXED_EXPOSED_COPPER_BULB);
        this.copyCopperBulbModel(Blocks.WEATHERED_COPPER_BULB, Blocks.WAXED_WEATHERED_COPPER_BULB);
        this.copyCopperBulbModel(Blocks.OXIDIZED_COPPER_BULB, Blocks.WAXED_OXIDIZED_COPPER_BULB);
        this.createNonTemplateModelBlock(Blocks.AIR);
        this.createNonTemplateModelBlock(Blocks.CAVE_AIR, Blocks.AIR);
        this.createNonTemplateModelBlock(Blocks.VOID_AIR, Blocks.AIR);
        this.createNonTemplateModelBlock(Blocks.BEACON);
        this.createNonTemplateModelBlock(Blocks.CACTUS);
        this.createNonTemplateModelBlock(Blocks.BUBBLE_COLUMN, Blocks.WATER);
        this.createNonTemplateModelBlock(Blocks.DRAGON_EGG);
        this.createNonTemplateModelBlock(Blocks.DRIED_KELP_BLOCK);
        this.createNonTemplateModelBlock(Blocks.ENCHANTING_TABLE);
        this.createNonTemplateModelBlock(Blocks.FLOWER_POT);
        this.registerSimpleFlatItemModel(Items.FLOWER_POT);
        this.createNonTemplateModelBlock(Blocks.HONEY_BLOCK);
        this.createNonTemplateModelBlock(Blocks.WATER);
        this.createNonTemplateModelBlock(Blocks.LAVA);
        this.createNonTemplateModelBlock(Blocks.SLIME_BLOCK);
        this.registerSimpleFlatItemModel(Items.IRON_CHAIN);
        Items.COPPER_CHAIN.waxedMapping().forEach(this::createCopperChainItem);
        this.createCandleAndCandleCake(Blocks.WHITE_CANDLE, Blocks.WHITE_CANDLE_CAKE);
        this.createCandleAndCandleCake(Blocks.ORANGE_CANDLE, Blocks.ORANGE_CANDLE_CAKE);
        this.createCandleAndCandleCake(Blocks.MAGENTA_CANDLE, Blocks.MAGENTA_CANDLE_CAKE);
        this.createCandleAndCandleCake(Blocks.LIGHT_BLUE_CANDLE, Blocks.LIGHT_BLUE_CANDLE_CAKE);
        this.createCandleAndCandleCake(Blocks.YELLOW_CANDLE, Blocks.YELLOW_CANDLE_CAKE);
        this.createCandleAndCandleCake(Blocks.LIME_CANDLE, Blocks.LIME_CANDLE_CAKE);
        this.createCandleAndCandleCake(Blocks.PINK_CANDLE, Blocks.PINK_CANDLE_CAKE);
        this.createCandleAndCandleCake(Blocks.GRAY_CANDLE, Blocks.GRAY_CANDLE_CAKE);
        this.createCandleAndCandleCake(Blocks.LIGHT_GRAY_CANDLE, Blocks.LIGHT_GRAY_CANDLE_CAKE);
        this.createCandleAndCandleCake(Blocks.CYAN_CANDLE, Blocks.CYAN_CANDLE_CAKE);
        this.createCandleAndCandleCake(Blocks.PURPLE_CANDLE, Blocks.PURPLE_CANDLE_CAKE);
        this.createCandleAndCandleCake(Blocks.BLUE_CANDLE, Blocks.BLUE_CANDLE_CAKE);
        this.createCandleAndCandleCake(Blocks.BROWN_CANDLE, Blocks.BROWN_CANDLE_CAKE);
        this.createCandleAndCandleCake(Blocks.GREEN_CANDLE, Blocks.GREEN_CANDLE_CAKE);
        this.createCandleAndCandleCake(Blocks.RED_CANDLE, Blocks.RED_CANDLE_CAKE);
        this.createCandleAndCandleCake(Blocks.BLACK_CANDLE, Blocks.BLACK_CANDLE_CAKE);
        this.createCandleAndCandleCake(Blocks.CANDLE, Blocks.CANDLE_CAKE);
        this.createNonTemplateModelBlock(Blocks.POTTED_BAMBOO);
        this.createNonTemplateModelBlock(Blocks.POTTED_CACTUS);
        this.createNonTemplateModelBlock(Blocks.POWDER_SNOW);
        this.createNonTemplateModelBlock(Blocks.SPORE_BLOSSOM);
        this.createAzalea(Blocks.AZALEA);
        this.createAzalea(Blocks.FLOWERING_AZALEA);
        this.createPottedAzalea(Blocks.POTTED_AZALEA);
        this.createPottedAzalea(Blocks.POTTED_FLOWERING_AZALEA);
        this.createCaveVines();
        this.createFullAndCarpetBlocks(Blocks.MOSS_BLOCK, Blocks.MOSS_CARPET);
        this.createMossyCarpet(Blocks.PALE_MOSS_CARPET);
        this.createHangingMoss(Blocks.PALE_HANGING_MOSS);
        this.createTrivialCube(Blocks.PALE_MOSS_BLOCK);
        this.createFlowerBed(Blocks.PINK_PETALS);
        this.createFlowerBed(Blocks.WILDFLOWERS);
        this.createLeafLitter(Blocks.LEAF_LITTER);
        this.createCrossBlock(Blocks.FIREFLY_BUSH, BlockModelGenerators.PlantType.EMISSIVE_NOT_TINTED);
        this.registerSimpleFlatItemModel(Items.FIREFLY_BUSH);
        this.createAirLikeBlock(Blocks.BARRIER, Items.BARRIER);
        this.registerSimpleFlatItemModel(Items.BARRIER);
        this.createLightBlock();
        this.createAirLikeBlock(Blocks.STRUCTURE_VOID, Items.STRUCTURE_VOID);
        this.registerSimpleFlatItemModel(Items.STRUCTURE_VOID);
        this.createAirLikeBlock(Blocks.MOVING_PISTON, TextureMapping.getBlockTexture(Blocks.PISTON, "_side"));
        this.createTrivialCube(Blocks.COAL_ORE);
        this.createTrivialCube(Blocks.DEEPSLATE_COAL_ORE);
        this.createTrivialCube(Blocks.COAL_BLOCK);
        this.createTrivialCube(Blocks.DIAMOND_ORE);
        this.createTrivialCube(Blocks.DEEPSLATE_DIAMOND_ORE);
        this.createTrivialCube(Blocks.DIAMOND_BLOCK);
        this.createTrivialCube(Blocks.EMERALD_ORE);
        this.createTrivialCube(Blocks.DEEPSLATE_EMERALD_ORE);
        this.createTrivialCube(Blocks.EMERALD_BLOCK);
        this.createTrivialCube(Blocks.GOLD_ORE);
        this.createTrivialCube(Blocks.NETHER_GOLD_ORE);
        this.createTrivialCube(Blocks.DEEPSLATE_GOLD_ORE);
        this.createTrivialCube(Blocks.GOLD_BLOCK);
        this.createTrivialCube(Blocks.IRON_ORE);
        this.createTrivialCube(Blocks.DEEPSLATE_IRON_ORE);
        this.createTrivialCube(Blocks.IRON_BLOCK);
        this.createTrivialBlock(Blocks.ANCIENT_DEBRIS, TexturedModel.COLUMN);
        this.createTrivialCube(Blocks.NETHERITE_BLOCK);
        this.createTrivialCube(Blocks.LAPIS_ORE);
        this.createTrivialCube(Blocks.DEEPSLATE_LAPIS_ORE);
        this.createTrivialCube(Blocks.LAPIS_BLOCK);
        this.createTrivialCube(Blocks.RESIN_BLOCK);
        this.createTrivialCube(Blocks.NETHER_QUARTZ_ORE);
        this.createTrivialCube(Blocks.REDSTONE_ORE);
        this.createTrivialCube(Blocks.DEEPSLATE_REDSTONE_ORE);
        this.createTrivialCube(Blocks.REDSTONE_BLOCK);
        this.createTrivialCube(Blocks.GILDED_BLACKSTONE);
        this.createTrivialCube(Blocks.BLUE_ICE);
        this.createTrivialCube(Blocks.CLAY);
        this.createTrivialCube(Blocks.COARSE_DIRT);
        this.createTrivialCube(Blocks.CRYING_OBSIDIAN);
        this.createTrivialCube(Blocks.END_STONE);
        this.createTrivialCube(Blocks.GLOWSTONE);
        this.createTrivialCube(Blocks.GRAVEL);
        this.createTrivialCube(Blocks.HONEYCOMB_BLOCK);
        this.createTrivialCube(Blocks.ICE);
        this.createTrivialBlock(Blocks.JUKEBOX, TexturedModel.CUBE_TOP);
        this.createTrivialBlock(Blocks.LODESTONE, TexturedModel.COLUMN);
        this.createTrivialBlock(Blocks.MELON, TexturedModel.COLUMN);
        this.createNonTemplateModelBlock(Blocks.MANGROVE_ROOTS);
        this.createNonTemplateModelBlock(Blocks.POTTED_MANGROVE_PROPAGULE);
        this.createTrivialCube(Blocks.NETHER_WART_BLOCK);
        this.createTrivialCube(Blocks.NOTE_BLOCK);
        this.createTrivialCube(Blocks.PACKED_ICE);
        this.createTrivialCube(Blocks.OBSIDIAN);
        this.createTrivialCube(Blocks.QUARTZ_BRICKS);
        this.createTrivialCube(Blocks.SEA_LANTERN);
        this.createTrivialCube(Blocks.SHROOMLIGHT);
        this.createTrivialCube(Blocks.SOUL_SAND);
        this.createTrivialCube(Blocks.SOUL_SOIL);
        this.createTrivialBlock(Blocks.SPAWNER, TexturedModel.CUBE_INNER_FACES);
        this.createCreakingHeart(Blocks.CREAKING_HEART);
        this.createTrivialCube(Blocks.SPONGE);
        this.createTrivialBlock(Blocks.SEAGRASS, TexturedModel.SEAGRASS);
        this.registerSimpleFlatItemModel(Items.SEAGRASS);
        this.createTrivialBlock(Blocks.TNT, TexturedModel.CUBE_TOP_BOTTOM);
        this.createTrivialBlock(Blocks.TARGET, TexturedModel.COLUMN);
        this.createTrivialCube(Blocks.WARPED_WART_BLOCK);
        this.createTrivialCube(Blocks.WET_SPONGE);
        this.createTrivialCube(Blocks.AMETHYST_BLOCK);
        this.createTrivialCube(Blocks.BUDDING_AMETHYST);
        this.createTrivialCube(Blocks.CALCITE);
        this.createTrivialCube(Blocks.DRIPSTONE_BLOCK);
        this.createTrivialCube(Blocks.RAW_IRON_BLOCK);
        this.createTrivialCube(Blocks.RAW_COPPER_BLOCK);
        this.createTrivialCube(Blocks.RAW_GOLD_BLOCK);
        this.createRotatedMirroredVariantBlock(Blocks.SCULK);
        this.createNonTemplateModelBlock(Blocks.HEAVY_CORE);
        this.createPetrifiedOakSlab();
        this.createTrivialCube(Blocks.COPPER_ORE);
        this.createTrivialCube(Blocks.DEEPSLATE_COPPER_ORE);
        this.createTrivialCube(Blocks.COPPER_BLOCK);
        this.createTrivialCube(Blocks.EXPOSED_COPPER);
        this.createTrivialCube(Blocks.WEATHERED_COPPER);
        this.createTrivialCube(Blocks.OXIDIZED_COPPER);
        this.copyModel(Blocks.COPPER_BLOCK, Blocks.WAXED_COPPER_BLOCK);
        this.copyModel(Blocks.EXPOSED_COPPER, Blocks.WAXED_EXPOSED_COPPER);
        this.copyModel(Blocks.WEATHERED_COPPER, Blocks.WAXED_WEATHERED_COPPER);
        this.copyModel(Blocks.OXIDIZED_COPPER, Blocks.WAXED_OXIDIZED_COPPER);
        this.createDoor(Blocks.COPPER_DOOR);
        this.createDoor(Blocks.EXPOSED_COPPER_DOOR);
        this.createDoor(Blocks.WEATHERED_COPPER_DOOR);
        this.createDoor(Blocks.OXIDIZED_COPPER_DOOR);
        this.copyDoorModel(Blocks.COPPER_DOOR, Blocks.WAXED_COPPER_DOOR);
        this.copyDoorModel(Blocks.EXPOSED_COPPER_DOOR, Blocks.WAXED_EXPOSED_COPPER_DOOR);
        this.copyDoorModel(Blocks.WEATHERED_COPPER_DOOR, Blocks.WAXED_WEATHERED_COPPER_DOOR);
        this.copyDoorModel(Blocks.OXIDIZED_COPPER_DOOR, Blocks.WAXED_OXIDIZED_COPPER_DOOR);
        this.createTrapdoor(Blocks.COPPER_TRAPDOOR);
        this.createTrapdoor(Blocks.EXPOSED_COPPER_TRAPDOOR);
        this.createTrapdoor(Blocks.WEATHERED_COPPER_TRAPDOOR);
        this.createTrapdoor(Blocks.OXIDIZED_COPPER_TRAPDOOR);
        this.copyTrapdoorModel(Blocks.COPPER_TRAPDOOR, Blocks.WAXED_COPPER_TRAPDOOR);
        this.copyTrapdoorModel(Blocks.EXPOSED_COPPER_TRAPDOOR, Blocks.WAXED_EXPOSED_COPPER_TRAPDOOR);
        this.copyTrapdoorModel(Blocks.WEATHERED_COPPER_TRAPDOOR, Blocks.WAXED_WEATHERED_COPPER_TRAPDOOR);
        this.copyTrapdoorModel(Blocks.OXIDIZED_COPPER_TRAPDOOR, Blocks.WAXED_OXIDIZED_COPPER_TRAPDOOR);
        this.createTrivialCube(Blocks.COPPER_GRATE);
        this.createTrivialCube(Blocks.EXPOSED_COPPER_GRATE);
        this.createTrivialCube(Blocks.WEATHERED_COPPER_GRATE);
        this.createTrivialCube(Blocks.OXIDIZED_COPPER_GRATE);
        this.copyModel(Blocks.COPPER_GRATE, Blocks.WAXED_COPPER_GRATE);
        this.copyModel(Blocks.EXPOSED_COPPER_GRATE, Blocks.WAXED_EXPOSED_COPPER_GRATE);
        this.copyModel(Blocks.WEATHERED_COPPER_GRATE, Blocks.WAXED_WEATHERED_COPPER_GRATE);
        this.copyModel(Blocks.OXIDIZED_COPPER_GRATE, Blocks.WAXED_OXIDIZED_COPPER_GRATE);
        this.createLightningRod(Blocks.LIGHTNING_ROD, Blocks.WAXED_LIGHTNING_ROD);
        this.createLightningRod(Blocks.EXPOSED_LIGHTNING_ROD, Blocks.WAXED_EXPOSED_LIGHTNING_ROD);
        this.createLightningRod(Blocks.WEATHERED_LIGHTNING_ROD, Blocks.WAXED_WEATHERED_LIGHTNING_ROD);
        this.createLightningRod(Blocks.OXIDIZED_LIGHTNING_ROD, Blocks.WAXED_OXIDIZED_LIGHTNING_ROD);
        this.createWeightedPressurePlate(Blocks.LIGHT_WEIGHTED_PRESSURE_PLATE, Blocks.GOLD_BLOCK);
        this.createWeightedPressurePlate(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE, Blocks.IRON_BLOCK);
        this.createShelf(Blocks.ACACIA_SHELF, Blocks.STRIPPED_ACACIA_LOG);
        this.createShelf(Blocks.BAMBOO_SHELF, Blocks.STRIPPED_BAMBOO_BLOCK);
        this.createShelf(Blocks.BIRCH_SHELF, Blocks.STRIPPED_BIRCH_LOG);
        this.createShelf(Blocks.CHERRY_SHELF, Blocks.STRIPPED_CHERRY_LOG);
        this.createShelf(Blocks.CRIMSON_SHELF, Blocks.STRIPPED_CRIMSON_STEM);
        this.createShelf(Blocks.DARK_OAK_SHELF, Blocks.STRIPPED_DARK_OAK_LOG);
        this.createShelf(Blocks.JUNGLE_SHELF, Blocks.STRIPPED_JUNGLE_LOG);
        this.createShelf(Blocks.MANGROVE_SHELF, Blocks.STRIPPED_MANGROVE_LOG);
        this.createShelf(Blocks.OAK_SHELF, Blocks.STRIPPED_OAK_LOG);
        this.createShelf(Blocks.PALE_OAK_SHELF, Blocks.STRIPPED_PALE_OAK_LOG);
        this.createShelf(Blocks.SPRUCE_SHELF, Blocks.STRIPPED_SPRUCE_LOG);
        this.createShelf(Blocks.WARPED_SHELF, Blocks.STRIPPED_WARPED_STEM);
        this.createAmethystClusters();
        this.createBookshelf();
        this.createChiseledBookshelf();
        this.createBrewingStand();
        this.createCakeBlock();
        this.createCampfires(Blocks.CAMPFIRE, Blocks.SOUL_CAMPFIRE);
        this.createCartographyTable();
        this.createCauldrons();
        this.createChorusFlower();
        this.createChorusPlant();
        this.createComposter();
        this.createDaylightDetector();
        this.createEndPortalFrame();
        this.createRotatableColumn(Blocks.END_ROD);
        this.createFarmland();
        this.createFire();
        this.createSoulFire();
        this.createFrostedIce();
        this.createGrassBlocks();
        this.createCocoa();
        this.createDirtPath();
        this.createGrindstone();
        this.createHopper();
        this.createBarsAndItem(Blocks.IRON_BARS);
        Blocks.COPPER_BARS.waxedMapping().forEach(this::createBarsAndItem);
        this.createLever();
        this.createLilyPad();
        this.createNetherPortalBlock();
        this.createNetherrack();
        this.createObserver();
        this.createPistons();
        this.createPistonHeads();
        this.createScaffolding();
        this.createRedstoneTorch();
        this.createRedstoneLamp();
        this.createRepeater();
        this.createSeaPickle();
        this.createSmithingTable();
        this.createSnowBlocks();
        this.createStonecutter();
        this.createStructureBlock();
        this.createSweetBerryBush();
        this.createTestBlock();
        this.createTrivialCube(Blocks.TEST_INSTANCE_BLOCK);
        this.createTripwire();
        this.createTripwireHook();
        this.createTurtleEgg();
        this.createSnifferEgg();
        this.createDriedGhastBlock();
        this.createVine();
        this.createMultiface(Blocks.GLOW_LICHEN);
        this.createMultiface(Blocks.SCULK_VEIN);
        this.createMultiface(Blocks.RESIN_CLUMP, Items.RESIN_CLUMP);
        this.createMagmaBlock();
        this.createJigsaw();
        this.createSculkSensor();
        this.createCalibratedSculkSensor();
        this.createSculkShrieker();
        this.createFrogspawnBlock();
        this.createMangrovePropagule();
        this.createMuddyMangroveRoots();
        this.createTrialSpawner();
        this.createVault();
        this.createNonTemplateHorizontalBlock(Blocks.LADDER);
        this.registerSimpleFlatItemModel(Blocks.LADDER);
        this.createNonTemplateHorizontalBlock(Blocks.LECTERN);
        this.createBigDripLeafBlock();
        this.createNonTemplateHorizontalBlock(Blocks.BIG_DRIPLEAF_STEM);
        this.createNormalTorch(Blocks.TORCH, Blocks.WALL_TORCH);
        this.createNormalTorch(Blocks.SOUL_TORCH, Blocks.SOUL_WALL_TORCH);
        this.createNormalTorch(Blocks.COPPER_TORCH, Blocks.COPPER_WALL_TORCH);
        this.createCraftingTableLike(Blocks.CRAFTING_TABLE, Blocks.OAK_PLANKS, TextureMapping::craftingTable);
        this.createCraftingTableLike(Blocks.FLETCHING_TABLE, Blocks.BIRCH_PLANKS, TextureMapping::fletchingTable);
        this.createNyliumBlock(Blocks.CRIMSON_NYLIUM);
        this.createNyliumBlock(Blocks.WARPED_NYLIUM);
        this.createDispenserBlock(Blocks.DISPENSER);
        this.createDispenserBlock(Blocks.DROPPER);
        this.createCrafterBlock();
        this.createLantern(Blocks.LANTERN);
        this.createLantern(Blocks.SOUL_LANTERN);
        Blocks.COPPER_LANTERN.waxedMapping().forEach(this::createCopperLantern);
        this.createAxisAlignedPillarBlockCustomModel(Blocks.IRON_CHAIN, plainVariant(TexturedModel.CHAIN.create(Blocks.IRON_CHAIN, this.modelOutput)));
        Blocks.COPPER_CHAIN.waxedMapping().forEach(this::createCopperChain);
        this.createAxisAlignedPillarBlock(Blocks.BASALT, TexturedModel.COLUMN);
        this.createAxisAlignedPillarBlock(Blocks.POLISHED_BASALT, TexturedModel.COLUMN);
        this.createTrivialCube(Blocks.SMOOTH_BASALT);
        this.createAxisAlignedPillarBlock(Blocks.BONE_BLOCK, TexturedModel.COLUMN);
        this.createRotatedVariantBlock(Blocks.DIRT);
        this.createRotatedVariantBlock(Blocks.ROOTED_DIRT);
        this.createRotatedVariantBlock(Blocks.SAND);
        this.createBrushableBlock(Blocks.SUSPICIOUS_SAND);
        this.createBrushableBlock(Blocks.SUSPICIOUS_GRAVEL);
        this.createRotatedVariantBlock(Blocks.RED_SAND);
        this.createRotatedMirroredVariantBlock(Blocks.BEDROCK);
        this.createTrivialBlock(Blocks.REINFORCED_DEEPSLATE, TexturedModel.CUBE_TOP_BOTTOM);
        this.createRotatedPillarWithHorizontalVariant(Blocks.HAY_BLOCK, TexturedModel.COLUMN, TexturedModel.COLUMN_HORIZONTAL);
        this.createRotatedPillarWithHorizontalVariant(Blocks.PURPUR_PILLAR, TexturedModel.COLUMN_ALT, TexturedModel.COLUMN_HORIZONTAL_ALT);
        this.createRotatedPillarWithHorizontalVariant(Blocks.QUARTZ_PILLAR, TexturedModel.COLUMN_ALT, TexturedModel.COLUMN_HORIZONTAL_ALT);
        this.createRotatedPillarWithHorizontalVariant(Blocks.OCHRE_FROGLIGHT, TexturedModel.COLUMN, TexturedModel.COLUMN_HORIZONTAL);
        this.createRotatedPillarWithHorizontalVariant(Blocks.VERDANT_FROGLIGHT, TexturedModel.COLUMN, TexturedModel.COLUMN_HORIZONTAL);
        this.createRotatedPillarWithHorizontalVariant(Blocks.PEARLESCENT_FROGLIGHT, TexturedModel.COLUMN, TexturedModel.COLUMN_HORIZONTAL);
        this.createHorizontallyRotatedBlock(Blocks.LOOM, TexturedModel.ORIENTABLE);
        this.createPumpkins();
        this.createBeeNest(Blocks.BEE_NEST, TextureMapping::orientableCube);
        this.createBeeNest(Blocks.BEEHIVE, TextureMapping::orientableCubeSameEnds);
        this.createCropBlock(Blocks.BEETROOTS, BlockStateProperties.AGE_3, 0, 1, 2, 3);
        this.createCropBlock(Blocks.CARROTS, BlockStateProperties.AGE_7, 0, 0, 1, 1, 2, 2, 2, 3);
        this.createCropBlock(Blocks.NETHER_WART, BlockStateProperties.AGE_3, 0, 1, 1, 2);
        this.createCropBlock(Blocks.POTATOES, BlockStateProperties.AGE_7, 0, 0, 1, 1, 2, 2, 2, 3);
        this.createCropBlock(Blocks.WHEAT, BlockStateProperties.AGE_7, 0, 1, 2, 3, 4, 5, 6, 7);
        this.createCrossBlock(Blocks.TORCHFLOWER_CROP, BlockModelGenerators.PlantType.NOT_TINTED, BlockStateProperties.AGE_1, 0, 1);
        this.createPitcherCrop();
        this.createPitcherPlant();
        this.createBanners();
        this.createBeds();
        this.createHeads();
        this.createChests();
        this.createCopperChests();
        this.createShulkerBox(Blocks.SHULKER_BOX, null);
        this.createShulkerBox(Blocks.WHITE_SHULKER_BOX, DyeColor.WHITE);
        this.createShulkerBox(Blocks.ORANGE_SHULKER_BOX, DyeColor.ORANGE);
        this.createShulkerBox(Blocks.MAGENTA_SHULKER_BOX, DyeColor.MAGENTA);
        this.createShulkerBox(Blocks.LIGHT_BLUE_SHULKER_BOX, DyeColor.LIGHT_BLUE);
        this.createShulkerBox(Blocks.YELLOW_SHULKER_BOX, DyeColor.YELLOW);
        this.createShulkerBox(Blocks.LIME_SHULKER_BOX, DyeColor.LIME);
        this.createShulkerBox(Blocks.PINK_SHULKER_BOX, DyeColor.PINK);
        this.createShulkerBox(Blocks.GRAY_SHULKER_BOX, DyeColor.GRAY);
        this.createShulkerBox(Blocks.LIGHT_GRAY_SHULKER_BOX, DyeColor.LIGHT_GRAY);
        this.createShulkerBox(Blocks.CYAN_SHULKER_BOX, DyeColor.CYAN);
        this.createShulkerBox(Blocks.PURPLE_SHULKER_BOX, DyeColor.PURPLE);
        this.createShulkerBox(Blocks.BLUE_SHULKER_BOX, DyeColor.BLUE);
        this.createShulkerBox(Blocks.BROWN_SHULKER_BOX, DyeColor.BROWN);
        this.createShulkerBox(Blocks.GREEN_SHULKER_BOX, DyeColor.GREEN);
        this.createShulkerBox(Blocks.RED_SHULKER_BOX, DyeColor.RED);
        this.createShulkerBox(Blocks.BLACK_SHULKER_BOX, DyeColor.BLACK);
        this.createCopperGolemStatues();
        this.createParticleOnlyBlock(Blocks.CONDUIT);
        this.generateSimpleSpecialItemModel(Blocks.CONDUIT, new ConduitSpecialRenderer.Unbaked());
        this.createParticleOnlyBlock(Blocks.DECORATED_POT, Blocks.TERRACOTTA);
        this.generateSimpleSpecialItemModel(Blocks.DECORATED_POT, new DecoratedPotSpecialRenderer.Unbaked());
        this.createParticleOnlyBlock(Blocks.END_PORTAL, Blocks.OBSIDIAN);
        this.createParticleOnlyBlock(Blocks.END_GATEWAY, Blocks.OBSIDIAN);
        this.createTrivialCube(Blocks.AZALEA_LEAVES);
        this.createTrivialCube(Blocks.FLOWERING_AZALEA_LEAVES);
        this.createTrivialCube(Blocks.WHITE_CONCRETE);
        this.createTrivialCube(Blocks.ORANGE_CONCRETE);
        this.createTrivialCube(Blocks.MAGENTA_CONCRETE);
        this.createTrivialCube(Blocks.LIGHT_BLUE_CONCRETE);
        this.createTrivialCube(Blocks.YELLOW_CONCRETE);
        this.createTrivialCube(Blocks.LIME_CONCRETE);
        this.createTrivialCube(Blocks.PINK_CONCRETE);
        this.createTrivialCube(Blocks.GRAY_CONCRETE);
        this.createTrivialCube(Blocks.LIGHT_GRAY_CONCRETE);
        this.createTrivialCube(Blocks.CYAN_CONCRETE);
        this.createTrivialCube(Blocks.PURPLE_CONCRETE);
        this.createTrivialCube(Blocks.BLUE_CONCRETE);
        this.createTrivialCube(Blocks.BROWN_CONCRETE);
        this.createTrivialCube(Blocks.GREEN_CONCRETE);
        this.createTrivialCube(Blocks.RED_CONCRETE);
        this.createTrivialCube(Blocks.BLACK_CONCRETE);
        this.createColoredBlockWithRandomRotations(
            TexturedModel.CUBE,
            Blocks.WHITE_CONCRETE_POWDER,
            Blocks.ORANGE_CONCRETE_POWDER,
            Blocks.MAGENTA_CONCRETE_POWDER,
            Blocks.LIGHT_BLUE_CONCRETE_POWDER,
            Blocks.YELLOW_CONCRETE_POWDER,
            Blocks.LIME_CONCRETE_POWDER,
            Blocks.PINK_CONCRETE_POWDER,
            Blocks.GRAY_CONCRETE_POWDER,
            Blocks.LIGHT_GRAY_CONCRETE_POWDER,
            Blocks.CYAN_CONCRETE_POWDER,
            Blocks.PURPLE_CONCRETE_POWDER,
            Blocks.BLUE_CONCRETE_POWDER,
            Blocks.BROWN_CONCRETE_POWDER,
            Blocks.GREEN_CONCRETE_POWDER,
            Blocks.RED_CONCRETE_POWDER,
            Blocks.BLACK_CONCRETE_POWDER
        );
        this.createTrivialCube(Blocks.TERRACOTTA);
        this.createTrivialCube(Blocks.WHITE_TERRACOTTA);
        this.createTrivialCube(Blocks.ORANGE_TERRACOTTA);
        this.createTrivialCube(Blocks.MAGENTA_TERRACOTTA);
        this.createTrivialCube(Blocks.LIGHT_BLUE_TERRACOTTA);
        this.createTrivialCube(Blocks.YELLOW_TERRACOTTA);
        this.createTrivialCube(Blocks.LIME_TERRACOTTA);
        this.createTrivialCube(Blocks.PINK_TERRACOTTA);
        this.createTrivialCube(Blocks.GRAY_TERRACOTTA);
        this.createTrivialCube(Blocks.LIGHT_GRAY_TERRACOTTA);
        this.createTrivialCube(Blocks.CYAN_TERRACOTTA);
        this.createTrivialCube(Blocks.PURPLE_TERRACOTTA);
        this.createTrivialCube(Blocks.BLUE_TERRACOTTA);
        this.createTrivialCube(Blocks.BROWN_TERRACOTTA);
        this.createTrivialCube(Blocks.GREEN_TERRACOTTA);
        this.createTrivialCube(Blocks.RED_TERRACOTTA);
        this.createTrivialCube(Blocks.BLACK_TERRACOTTA);
        this.createTrivialCube(Blocks.TINTED_GLASS);
        this.createGlassBlocks(Blocks.GLASS, Blocks.GLASS_PANE);
        this.createGlassBlocks(Blocks.WHITE_STAINED_GLASS, Blocks.WHITE_STAINED_GLASS_PANE);
        this.createGlassBlocks(Blocks.ORANGE_STAINED_GLASS, Blocks.ORANGE_STAINED_GLASS_PANE);
        this.createGlassBlocks(Blocks.MAGENTA_STAINED_GLASS, Blocks.MAGENTA_STAINED_GLASS_PANE);
        this.createGlassBlocks(Blocks.LIGHT_BLUE_STAINED_GLASS, Blocks.LIGHT_BLUE_STAINED_GLASS_PANE);
        this.createGlassBlocks(Blocks.YELLOW_STAINED_GLASS, Blocks.YELLOW_STAINED_GLASS_PANE);
        this.createGlassBlocks(Blocks.LIME_STAINED_GLASS, Blocks.LIME_STAINED_GLASS_PANE);
        this.createGlassBlocks(Blocks.PINK_STAINED_GLASS, Blocks.PINK_STAINED_GLASS_PANE);
        this.createGlassBlocks(Blocks.GRAY_STAINED_GLASS, Blocks.GRAY_STAINED_GLASS_PANE);
        this.createGlassBlocks(Blocks.LIGHT_GRAY_STAINED_GLASS, Blocks.LIGHT_GRAY_STAINED_GLASS_PANE);
        this.createGlassBlocks(Blocks.CYAN_STAINED_GLASS, Blocks.CYAN_STAINED_GLASS_PANE);
        this.createGlassBlocks(Blocks.PURPLE_STAINED_GLASS, Blocks.PURPLE_STAINED_GLASS_PANE);
        this.createGlassBlocks(Blocks.BLUE_STAINED_GLASS, Blocks.BLUE_STAINED_GLASS_PANE);
        this.createGlassBlocks(Blocks.BROWN_STAINED_GLASS, Blocks.BROWN_STAINED_GLASS_PANE);
        this.createGlassBlocks(Blocks.GREEN_STAINED_GLASS, Blocks.GREEN_STAINED_GLASS_PANE);
        this.createGlassBlocks(Blocks.RED_STAINED_GLASS, Blocks.RED_STAINED_GLASS_PANE);
        this.createGlassBlocks(Blocks.BLACK_STAINED_GLASS, Blocks.BLACK_STAINED_GLASS_PANE);
        this.createColoredBlockWithStateRotations(
            TexturedModel.GLAZED_TERRACOTTA,
            Blocks.WHITE_GLAZED_TERRACOTTA,
            Blocks.ORANGE_GLAZED_TERRACOTTA,
            Blocks.MAGENTA_GLAZED_TERRACOTTA,
            Blocks.LIGHT_BLUE_GLAZED_TERRACOTTA,
            Blocks.YELLOW_GLAZED_TERRACOTTA,
            Blocks.LIME_GLAZED_TERRACOTTA,
            Blocks.PINK_GLAZED_TERRACOTTA,
            Blocks.GRAY_GLAZED_TERRACOTTA,
            Blocks.LIGHT_GRAY_GLAZED_TERRACOTTA,
            Blocks.CYAN_GLAZED_TERRACOTTA,
            Blocks.PURPLE_GLAZED_TERRACOTTA,
            Blocks.BLUE_GLAZED_TERRACOTTA,
            Blocks.BROWN_GLAZED_TERRACOTTA,
            Blocks.GREEN_GLAZED_TERRACOTTA,
            Blocks.RED_GLAZED_TERRACOTTA,
            Blocks.BLACK_GLAZED_TERRACOTTA
        );
        this.createFullAndCarpetBlocks(Blocks.WHITE_WOOL, Blocks.WHITE_CARPET);
        this.createFullAndCarpetBlocks(Blocks.ORANGE_WOOL, Blocks.ORANGE_CARPET);
        this.createFullAndCarpetBlocks(Blocks.MAGENTA_WOOL, Blocks.MAGENTA_CARPET);
        this.createFullAndCarpetBlocks(Blocks.LIGHT_BLUE_WOOL, Blocks.LIGHT_BLUE_CARPET);
        this.createFullAndCarpetBlocks(Blocks.YELLOW_WOOL, Blocks.YELLOW_CARPET);
        this.createFullAndCarpetBlocks(Blocks.LIME_WOOL, Blocks.LIME_CARPET);
        this.createFullAndCarpetBlocks(Blocks.PINK_WOOL, Blocks.PINK_CARPET);
        this.createFullAndCarpetBlocks(Blocks.GRAY_WOOL, Blocks.GRAY_CARPET);
        this.createFullAndCarpetBlocks(Blocks.LIGHT_GRAY_WOOL, Blocks.LIGHT_GRAY_CARPET);
        this.createFullAndCarpetBlocks(Blocks.CYAN_WOOL, Blocks.CYAN_CARPET);
        this.createFullAndCarpetBlocks(Blocks.PURPLE_WOOL, Blocks.PURPLE_CARPET);
        this.createFullAndCarpetBlocks(Blocks.BLUE_WOOL, Blocks.BLUE_CARPET);
        this.createFullAndCarpetBlocks(Blocks.BROWN_WOOL, Blocks.BROWN_CARPET);
        this.createFullAndCarpetBlocks(Blocks.GREEN_WOOL, Blocks.GREEN_CARPET);
        this.createFullAndCarpetBlocks(Blocks.RED_WOOL, Blocks.RED_CARPET);
        this.createFullAndCarpetBlocks(Blocks.BLACK_WOOL, Blocks.BLACK_CARPET);
        this.createTrivialCube(Blocks.MUD);
        this.createTrivialCube(Blocks.PACKED_MUD);
        this.createPlant(Blocks.FERN, Blocks.POTTED_FERN, BlockModelGenerators.PlantType.TINTED);
        this.createItemWithGrassTint(Blocks.FERN);
        this.createPlantWithDefaultItem(Blocks.DANDELION, Blocks.POTTED_DANDELION, BlockModelGenerators.PlantType.NOT_TINTED);
        this.createPlantWithDefaultItem(Blocks.POPPY, Blocks.POTTED_POPPY, BlockModelGenerators.PlantType.NOT_TINTED);
        this.createPlantWithDefaultItem(Blocks.OPEN_EYEBLOSSOM, Blocks.POTTED_OPEN_EYEBLOSSOM, BlockModelGenerators.PlantType.EMISSIVE_NOT_TINTED);
        this.createPlantWithDefaultItem(Blocks.CLOSED_EYEBLOSSOM, Blocks.POTTED_CLOSED_EYEBLOSSOM, BlockModelGenerators.PlantType.NOT_TINTED);
        this.createPlantWithDefaultItem(Blocks.BLUE_ORCHID, Blocks.POTTED_BLUE_ORCHID, BlockModelGenerators.PlantType.NOT_TINTED);
        this.createPlantWithDefaultItem(Blocks.ALLIUM, Blocks.POTTED_ALLIUM, BlockModelGenerators.PlantType.NOT_TINTED);
        this.createPlantWithDefaultItem(Blocks.AZURE_BLUET, Blocks.POTTED_AZURE_BLUET, BlockModelGenerators.PlantType.NOT_TINTED);
        this.createPlantWithDefaultItem(Blocks.RED_TULIP, Blocks.POTTED_RED_TULIP, BlockModelGenerators.PlantType.NOT_TINTED);
        this.createPlantWithDefaultItem(Blocks.ORANGE_TULIP, Blocks.POTTED_ORANGE_TULIP, BlockModelGenerators.PlantType.NOT_TINTED);
        this.createPlantWithDefaultItem(Blocks.WHITE_TULIP, Blocks.POTTED_WHITE_TULIP, BlockModelGenerators.PlantType.NOT_TINTED);
        this.createPlantWithDefaultItem(Blocks.PINK_TULIP, Blocks.POTTED_PINK_TULIP, BlockModelGenerators.PlantType.NOT_TINTED);
        this.createPlantWithDefaultItem(Blocks.OXEYE_DAISY, Blocks.POTTED_OXEYE_DAISY, BlockModelGenerators.PlantType.NOT_TINTED);
        this.createPlantWithDefaultItem(Blocks.CORNFLOWER, Blocks.POTTED_CORNFLOWER, BlockModelGenerators.PlantType.NOT_TINTED);
        this.createPlantWithDefaultItem(Blocks.LILY_OF_THE_VALLEY, Blocks.POTTED_LILY_OF_THE_VALLEY, BlockModelGenerators.PlantType.NOT_TINTED);
        this.createPlantWithDefaultItem(Blocks.WITHER_ROSE, Blocks.POTTED_WITHER_ROSE, BlockModelGenerators.PlantType.NOT_TINTED);
        this.createPlantWithDefaultItem(Blocks.RED_MUSHROOM, Blocks.POTTED_RED_MUSHROOM, BlockModelGenerators.PlantType.NOT_TINTED);
        this.createPlantWithDefaultItem(Blocks.BROWN_MUSHROOM, Blocks.POTTED_BROWN_MUSHROOM, BlockModelGenerators.PlantType.NOT_TINTED);
        this.createPlantWithDefaultItem(Blocks.DEAD_BUSH, Blocks.POTTED_DEAD_BUSH, BlockModelGenerators.PlantType.NOT_TINTED);
        this.createPlantWithDefaultItem(Blocks.TORCHFLOWER, Blocks.POTTED_TORCHFLOWER, BlockModelGenerators.PlantType.NOT_TINTED);
        this.createPointedDripstone();
        this.createMushroomBlock(Blocks.BROWN_MUSHROOM_BLOCK);
        this.createMushroomBlock(Blocks.RED_MUSHROOM_BLOCK);
        this.createMushroomBlock(Blocks.MUSHROOM_STEM);
        this.createCrossBlock(Blocks.SHORT_GRASS, BlockModelGenerators.PlantType.TINTED);
        this.createItemWithGrassTint(Blocks.SHORT_GRASS);
        this.createCrossBlockWithDefaultItem(Blocks.SHORT_DRY_GRASS, BlockModelGenerators.PlantType.NOT_TINTED);
        this.createCrossBlockWithDefaultItem(Blocks.TALL_DRY_GRASS, BlockModelGenerators.PlantType.NOT_TINTED);
        this.createCrossBlock(Blocks.BUSH, BlockModelGenerators.PlantType.TINTED);
        this.createItemWithGrassTint(Blocks.BUSH);
        this.createCrossBlock(Blocks.SUGAR_CANE, BlockModelGenerators.PlantType.TINTED);
        this.registerSimpleFlatItemModel(Items.SUGAR_CANE);
        this.createGrowingPlant(Blocks.KELP, Blocks.KELP_PLANT, BlockModelGenerators.PlantType.NOT_TINTED);
        this.registerSimpleFlatItemModel(Items.KELP);
        this.createCrossBlock(Blocks.HANGING_ROOTS, BlockModelGenerators.PlantType.NOT_TINTED);
        this.createGrowingPlant(Blocks.WEEPING_VINES, Blocks.WEEPING_VINES_PLANT, BlockModelGenerators.PlantType.NOT_TINTED);
        this.createGrowingPlant(Blocks.TWISTING_VINES, Blocks.TWISTING_VINES_PLANT, BlockModelGenerators.PlantType.NOT_TINTED);
        this.registerSimpleFlatItemModel(Blocks.WEEPING_VINES, "_plant");
        this.registerSimpleFlatItemModel(Blocks.TWISTING_VINES, "_plant");
        this.createCrossBlockWithDefaultItem(
            Blocks.BAMBOO_SAPLING, BlockModelGenerators.PlantType.TINTED, TextureMapping.cross(TextureMapping.getBlockTexture(Blocks.BAMBOO, "_stage0"))
        );
        this.createBamboo();
        this.createCrossBlockWithDefaultItem(Blocks.CACTUS_FLOWER, BlockModelGenerators.PlantType.NOT_TINTED);
        this.createCrossBlockWithDefaultItem(Blocks.COBWEB, BlockModelGenerators.PlantType.NOT_TINTED);
        this.createDoublePlantWithDefaultItem(Blocks.LILAC, BlockModelGenerators.PlantType.NOT_TINTED);
        this.createDoublePlantWithDefaultItem(Blocks.ROSE_BUSH, BlockModelGenerators.PlantType.NOT_TINTED);
        this.createDoublePlantWithDefaultItem(Blocks.PEONY, BlockModelGenerators.PlantType.NOT_TINTED);
        this.createTintedDoublePlant(Blocks.TALL_GRASS);
        this.createTintedDoublePlant(Blocks.LARGE_FERN);
        this.createSunflower();
        this.createTallSeagrass();
        this.createSmallDripleaf();
        this.createCoral(
            Blocks.TUBE_CORAL,
            Blocks.DEAD_TUBE_CORAL,
            Blocks.TUBE_CORAL_BLOCK,
            Blocks.DEAD_TUBE_CORAL_BLOCK,
            Blocks.TUBE_CORAL_FAN,
            Blocks.DEAD_TUBE_CORAL_FAN,
            Blocks.TUBE_CORAL_WALL_FAN,
            Blocks.DEAD_TUBE_CORAL_WALL_FAN
        );
        this.createCoral(
            Blocks.BRAIN_CORAL,
            Blocks.DEAD_BRAIN_CORAL,
            Blocks.BRAIN_CORAL_BLOCK,
            Blocks.DEAD_BRAIN_CORAL_BLOCK,
            Blocks.BRAIN_CORAL_FAN,
            Blocks.DEAD_BRAIN_CORAL_FAN,
            Blocks.BRAIN_CORAL_WALL_FAN,
            Blocks.DEAD_BRAIN_CORAL_WALL_FAN
        );
        this.createCoral(
            Blocks.BUBBLE_CORAL,
            Blocks.DEAD_BUBBLE_CORAL,
            Blocks.BUBBLE_CORAL_BLOCK,
            Blocks.DEAD_BUBBLE_CORAL_BLOCK,
            Blocks.BUBBLE_CORAL_FAN,
            Blocks.DEAD_BUBBLE_CORAL_FAN,
            Blocks.BUBBLE_CORAL_WALL_FAN,
            Blocks.DEAD_BUBBLE_CORAL_WALL_FAN
        );
        this.createCoral(
            Blocks.FIRE_CORAL,
            Blocks.DEAD_FIRE_CORAL,
            Blocks.FIRE_CORAL_BLOCK,
            Blocks.DEAD_FIRE_CORAL_BLOCK,
            Blocks.FIRE_CORAL_FAN,
            Blocks.DEAD_FIRE_CORAL_FAN,
            Blocks.FIRE_CORAL_WALL_FAN,
            Blocks.DEAD_FIRE_CORAL_WALL_FAN
        );
        this.createCoral(
            Blocks.HORN_CORAL,
            Blocks.DEAD_HORN_CORAL,
            Blocks.HORN_CORAL_BLOCK,
            Blocks.DEAD_HORN_CORAL_BLOCK,
            Blocks.HORN_CORAL_FAN,
            Blocks.DEAD_HORN_CORAL_FAN,
            Blocks.HORN_CORAL_WALL_FAN,
            Blocks.DEAD_HORN_CORAL_WALL_FAN
        );
        this.createStems(Blocks.MELON_STEM, Blocks.ATTACHED_MELON_STEM);
        this.createStems(Blocks.PUMPKIN_STEM, Blocks.ATTACHED_PUMPKIN_STEM);
        this.woodProvider(Blocks.MANGROVE_LOG).logWithHorizontal(Blocks.MANGROVE_LOG).wood(Blocks.MANGROVE_WOOD);
        this.woodProvider(Blocks.STRIPPED_MANGROVE_LOG).logWithHorizontal(Blocks.STRIPPED_MANGROVE_LOG).wood(Blocks.STRIPPED_MANGROVE_WOOD);
        this.createHangingSign(Blocks.STRIPPED_MANGROVE_LOG, Blocks.MANGROVE_HANGING_SIGN, Blocks.MANGROVE_WALL_HANGING_SIGN);
        this.createTintedLeaves(Blocks.MANGROVE_LEAVES, TexturedModel.LEAVES, -7158200);
        this.woodProvider(Blocks.ACACIA_LOG).logWithHorizontal(Blocks.ACACIA_LOG).wood(Blocks.ACACIA_WOOD);
        this.woodProvider(Blocks.STRIPPED_ACACIA_LOG).logWithHorizontal(Blocks.STRIPPED_ACACIA_LOG).wood(Blocks.STRIPPED_ACACIA_WOOD);
        this.createHangingSign(Blocks.STRIPPED_ACACIA_LOG, Blocks.ACACIA_HANGING_SIGN, Blocks.ACACIA_WALL_HANGING_SIGN);
        this.createPlantWithDefaultItem(Blocks.ACACIA_SAPLING, Blocks.POTTED_ACACIA_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
        this.createTintedLeaves(Blocks.ACACIA_LEAVES, TexturedModel.LEAVES, -12012264);
        this.woodProvider(Blocks.CHERRY_LOG).logUVLocked(Blocks.CHERRY_LOG).wood(Blocks.CHERRY_WOOD);
        this.woodProvider(Blocks.STRIPPED_CHERRY_LOG).logUVLocked(Blocks.STRIPPED_CHERRY_LOG).wood(Blocks.STRIPPED_CHERRY_WOOD);
        this.createHangingSign(Blocks.STRIPPED_CHERRY_LOG, Blocks.CHERRY_HANGING_SIGN, Blocks.CHERRY_WALL_HANGING_SIGN);
        this.createPlantWithDefaultItem(Blocks.CHERRY_SAPLING, Blocks.POTTED_CHERRY_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
        this.createTrivialBlock(Blocks.CHERRY_LEAVES, TexturedModel.LEAVES);
        this.woodProvider(Blocks.BIRCH_LOG).logWithHorizontal(Blocks.BIRCH_LOG).wood(Blocks.BIRCH_WOOD);
        this.woodProvider(Blocks.STRIPPED_BIRCH_LOG).logWithHorizontal(Blocks.STRIPPED_BIRCH_LOG).wood(Blocks.STRIPPED_BIRCH_WOOD);
        this.createHangingSign(Blocks.STRIPPED_BIRCH_LOG, Blocks.BIRCH_HANGING_SIGN, Blocks.BIRCH_WALL_HANGING_SIGN);
        this.createPlantWithDefaultItem(Blocks.BIRCH_SAPLING, Blocks.POTTED_BIRCH_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
        this.createTintedLeaves(Blocks.BIRCH_LEAVES, TexturedModel.LEAVES, -8345771);
        this.woodProvider(Blocks.OAK_LOG).logWithHorizontal(Blocks.OAK_LOG).wood(Blocks.OAK_WOOD);
        this.woodProvider(Blocks.STRIPPED_OAK_LOG).logWithHorizontal(Blocks.STRIPPED_OAK_LOG).wood(Blocks.STRIPPED_OAK_WOOD);
        this.createHangingSign(Blocks.STRIPPED_OAK_LOG, Blocks.OAK_HANGING_SIGN, Blocks.OAK_WALL_HANGING_SIGN);
        this.createPlantWithDefaultItem(Blocks.OAK_SAPLING, Blocks.POTTED_OAK_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
        this.createTintedLeaves(Blocks.OAK_LEAVES, TexturedModel.LEAVES, -12012264);
        this.woodProvider(Blocks.SPRUCE_LOG).logWithHorizontal(Blocks.SPRUCE_LOG).wood(Blocks.SPRUCE_WOOD);
        this.woodProvider(Blocks.STRIPPED_SPRUCE_LOG).logWithHorizontal(Blocks.STRIPPED_SPRUCE_LOG).wood(Blocks.STRIPPED_SPRUCE_WOOD);
        this.createHangingSign(Blocks.STRIPPED_SPRUCE_LOG, Blocks.SPRUCE_HANGING_SIGN, Blocks.SPRUCE_WALL_HANGING_SIGN);
        this.createPlantWithDefaultItem(Blocks.SPRUCE_SAPLING, Blocks.POTTED_SPRUCE_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
        this.createTintedLeaves(Blocks.SPRUCE_LEAVES, TexturedModel.LEAVES, -10380959);
        this.woodProvider(Blocks.DARK_OAK_LOG).logWithHorizontal(Blocks.DARK_OAK_LOG).wood(Blocks.DARK_OAK_WOOD);
        this.woodProvider(Blocks.STRIPPED_DARK_OAK_LOG).logWithHorizontal(Blocks.STRIPPED_DARK_OAK_LOG).wood(Blocks.STRIPPED_DARK_OAK_WOOD);
        this.createHangingSign(Blocks.STRIPPED_DARK_OAK_LOG, Blocks.DARK_OAK_HANGING_SIGN, Blocks.DARK_OAK_WALL_HANGING_SIGN);
        this.createPlantWithDefaultItem(Blocks.DARK_OAK_SAPLING, Blocks.POTTED_DARK_OAK_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
        this.createTintedLeaves(Blocks.DARK_OAK_LEAVES, TexturedModel.LEAVES, -12012264);
        this.woodProvider(Blocks.PALE_OAK_LOG).logWithHorizontal(Blocks.PALE_OAK_LOG).wood(Blocks.PALE_OAK_WOOD);
        this.woodProvider(Blocks.STRIPPED_PALE_OAK_LOG).logWithHorizontal(Blocks.STRIPPED_PALE_OAK_LOG).wood(Blocks.STRIPPED_PALE_OAK_WOOD);
        this.createHangingSign(Blocks.STRIPPED_PALE_OAK_LOG, Blocks.PALE_OAK_HANGING_SIGN, Blocks.PALE_OAK_WALL_HANGING_SIGN);
        this.createPlantWithDefaultItem(Blocks.PALE_OAK_SAPLING, Blocks.POTTED_PALE_OAK_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
        this.createTrivialBlock(Blocks.PALE_OAK_LEAVES, TexturedModel.LEAVES);
        this.woodProvider(Blocks.JUNGLE_LOG).logWithHorizontal(Blocks.JUNGLE_LOG).wood(Blocks.JUNGLE_WOOD);
        this.woodProvider(Blocks.STRIPPED_JUNGLE_LOG).logWithHorizontal(Blocks.STRIPPED_JUNGLE_LOG).wood(Blocks.STRIPPED_JUNGLE_WOOD);
        this.createHangingSign(Blocks.STRIPPED_JUNGLE_LOG, Blocks.JUNGLE_HANGING_SIGN, Blocks.JUNGLE_WALL_HANGING_SIGN);
        this.createPlantWithDefaultItem(Blocks.JUNGLE_SAPLING, Blocks.POTTED_JUNGLE_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
        this.createTintedLeaves(Blocks.JUNGLE_LEAVES, TexturedModel.LEAVES, -12012264);
        this.woodProvider(Blocks.CRIMSON_STEM).log(Blocks.CRIMSON_STEM).wood(Blocks.CRIMSON_HYPHAE);
        this.woodProvider(Blocks.STRIPPED_CRIMSON_STEM).log(Blocks.STRIPPED_CRIMSON_STEM).wood(Blocks.STRIPPED_CRIMSON_HYPHAE);
        this.createHangingSign(Blocks.STRIPPED_CRIMSON_STEM, Blocks.CRIMSON_HANGING_SIGN, Blocks.CRIMSON_WALL_HANGING_SIGN);
        this.createPlantWithDefaultItem(Blocks.CRIMSON_FUNGUS, Blocks.POTTED_CRIMSON_FUNGUS, BlockModelGenerators.PlantType.NOT_TINTED);
        this.createNetherRoots(Blocks.CRIMSON_ROOTS, Blocks.POTTED_CRIMSON_ROOTS);
        this.woodProvider(Blocks.WARPED_STEM).log(Blocks.WARPED_STEM).wood(Blocks.WARPED_HYPHAE);
        this.woodProvider(Blocks.STRIPPED_WARPED_STEM).log(Blocks.STRIPPED_WARPED_STEM).wood(Blocks.STRIPPED_WARPED_HYPHAE);
        this.createHangingSign(Blocks.STRIPPED_WARPED_STEM, Blocks.WARPED_HANGING_SIGN, Blocks.WARPED_WALL_HANGING_SIGN);
        this.createPlantWithDefaultItem(Blocks.WARPED_FUNGUS, Blocks.POTTED_WARPED_FUNGUS, BlockModelGenerators.PlantType.NOT_TINTED);
        this.createNetherRoots(Blocks.WARPED_ROOTS, Blocks.POTTED_WARPED_ROOTS);
        this.woodProvider(Blocks.BAMBOO_BLOCK).logUVLocked(Blocks.BAMBOO_BLOCK);
        this.woodProvider(Blocks.STRIPPED_BAMBOO_BLOCK).logUVLocked(Blocks.STRIPPED_BAMBOO_BLOCK);
        this.createHangingSign(Blocks.BAMBOO_PLANKS, Blocks.BAMBOO_HANGING_SIGN, Blocks.BAMBOO_WALL_HANGING_SIGN);
        this.createCrossBlock(Blocks.NETHER_SPROUTS, BlockModelGenerators.PlantType.NOT_TINTED);
        this.registerSimpleFlatItemModel(Items.NETHER_SPROUTS);
        this.createDoor(Blocks.IRON_DOOR);
        this.createTrapdoor(Blocks.IRON_TRAPDOOR);
        this.createSmoothStoneSlab();
        this.createPassiveRail(Blocks.RAIL);
        this.createActiveRail(Blocks.POWERED_RAIL);
        this.createActiveRail(Blocks.DETECTOR_RAIL);
        this.createActiveRail(Blocks.ACTIVATOR_RAIL);
        this.createComparator();
        this.createCommandBlock(Blocks.COMMAND_BLOCK);
        this.createCommandBlock(Blocks.REPEATING_COMMAND_BLOCK);
        this.createCommandBlock(Blocks.CHAIN_COMMAND_BLOCK);
        this.createAnvil(Blocks.ANVIL);
        this.createAnvil(Blocks.CHIPPED_ANVIL);
        this.createAnvil(Blocks.DAMAGED_ANVIL);
        this.createBarrel();
        this.createBell();
        this.createFurnace(Blocks.FURNACE, TexturedModel.ORIENTABLE_ONLY_TOP);
        this.createFurnace(Blocks.BLAST_FURNACE, TexturedModel.ORIENTABLE_ONLY_TOP);
        this.createFurnace(Blocks.SMOKER, TexturedModel.ORIENTABLE);
        this.createRedstoneWire();
        this.createRespawnAnchor();
        this.createSculkCatalyst();
        this.copyModel(Blocks.CHISELED_STONE_BRICKS, Blocks.INFESTED_CHISELED_STONE_BRICKS);
        this.copyModel(Blocks.COBBLESTONE, Blocks.INFESTED_COBBLESTONE);
        this.copyModel(Blocks.CRACKED_STONE_BRICKS, Blocks.INFESTED_CRACKED_STONE_BRICKS);
        this.copyModel(Blocks.MOSSY_STONE_BRICKS, Blocks.INFESTED_MOSSY_STONE_BRICKS);
        this.createInfestedStone();
        this.copyModel(Blocks.STONE_BRICKS, Blocks.INFESTED_STONE_BRICKS);
        this.createInfestedDeepslate();
    }

    public void createLightBlock() {
        ItemModel.Unbaked itemmodel$unbaked = ItemModelUtils.plainModel(this.createFlatItemModel(Items.LIGHT));
        Map<Integer, ItemModel.Unbaked> map = new HashMap<>(16);
        PropertyDispatch.C1<MultiVariant, Integer> c1 = PropertyDispatch.initial(BlockStateProperties.LEVEL);

        for (int i = 0; i <= 15; i++) {
            String s = String.format(Locale.ROOT, "_%02d", i);
            Identifier identifier = TextureMapping.getItemTexture(Items.LIGHT, s);
            c1.select(i, plainVariant(ModelTemplates.PARTICLE_ONLY.createWithSuffix(Blocks.LIGHT, s, TextureMapping.particle(identifier), this.modelOutput)));
            ItemModel.Unbaked itemmodel$unbaked1 = ItemModelUtils.plainModel(
                ModelTemplates.FLAT_ITEM.create(ModelLocationUtils.getModelLocation(Items.LIGHT, s), TextureMapping.layer0(identifier), this.modelOutput)
            );
            map.put(i, itemmodel$unbaked1);
        }

        this.itemModelOutput.accept(Items.LIGHT, ItemModelUtils.selectBlockItemProperty(LightBlock.LEVEL, itemmodel$unbaked, map));
        this.blockStateOutput.accept(MultiVariantGenerator.dispatch(Blocks.LIGHT).with(c1));
    }

    public void createCopperChainItem(Item item, Item waxedItem) {
        Identifier identifier = this.createFlatItemModel(item);
        this.registerSimpleItemModel(item, identifier);
        this.registerSimpleItemModel(waxedItem, identifier);
    }

    public void createCandleAndCandleCake(Block candleBlock, Block candleCakeBlock) {
        this.registerSimpleFlatItemModel(candleBlock.asItem());
        TextureMapping texturemapping = TextureMapping.cube(TextureMapping.getBlockTexture(candleBlock));
        TextureMapping texturemapping1 = TextureMapping.cube(TextureMapping.getBlockTexture(candleBlock, "_lit"));
        MultiVariant multivariant = plainVariant(ModelTemplates.CANDLE.createWithSuffix(candleBlock, "_one_candle", texturemapping, this.modelOutput));
        MultiVariant multivariant1 = plainVariant(ModelTemplates.TWO_CANDLES.createWithSuffix(candleBlock, "_two_candles", texturemapping, this.modelOutput));
        MultiVariant multivariant2 = plainVariant(ModelTemplates.THREE_CANDLES.createWithSuffix(candleBlock, "_three_candles", texturemapping, this.modelOutput));
        MultiVariant multivariant3 = plainVariant(ModelTemplates.FOUR_CANDLES.createWithSuffix(candleBlock, "_four_candles", texturemapping, this.modelOutput));
        MultiVariant multivariant4 = plainVariant(ModelTemplates.CANDLE.createWithSuffix(candleBlock, "_one_candle_lit", texturemapping1, this.modelOutput));
        MultiVariant multivariant5 = plainVariant(ModelTemplates.TWO_CANDLES.createWithSuffix(candleBlock, "_two_candles_lit", texturemapping1, this.modelOutput));
        MultiVariant multivariant6 = plainVariant(
            ModelTemplates.THREE_CANDLES.createWithSuffix(candleBlock, "_three_candles_lit", texturemapping1, this.modelOutput)
        );
        MultiVariant multivariant7 = plainVariant(
            ModelTemplates.FOUR_CANDLES.createWithSuffix(candleBlock, "_four_candles_lit", texturemapping1, this.modelOutput)
        );
        this.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(candleBlock)
                    .with(
                        PropertyDispatch.initial(BlockStateProperties.CANDLES, BlockStateProperties.LIT)
                            .select(1, false, multivariant)
                            .select(2, false, multivariant1)
                            .select(3, false, multivariant2)
                            .select(4, false, multivariant3)
                            .select(1, true, multivariant4)
                            .select(2, true, multivariant5)
                            .select(3, true, multivariant6)
                            .select(4, true, multivariant7)
                    )
            );
        MultiVariant multivariant8 = plainVariant(ModelTemplates.CANDLE_CAKE.create(candleCakeBlock, TextureMapping.candleCake(candleBlock, false), this.modelOutput));
        MultiVariant multivariant9 = plainVariant(
            ModelTemplates.CANDLE_CAKE.createWithSuffix(candleCakeBlock, "_lit", TextureMapping.candleCake(candleBlock, true), this.modelOutput)
        );
        this.blockStateOutput
            .accept(MultiVariantGenerator.dispatch(candleCakeBlock).with(createBooleanModelDispatch(BlockStateProperties.LIT, multivariant9, multivariant8)));
    }

    @OnlyIn(Dist.CLIENT)
    public class BlockFamilyProvider {
        private final TextureMapping mapping;
        private final Map<ModelTemplate, Identifier> models = new HashMap<>();
        private @Nullable BlockFamily family;
        private @Nullable Variant fullBlock;
        private final Set<Block> skipGeneratingModelsFor = new HashSet<>();

        public BlockFamilyProvider(TextureMapping mapping) {
            this.mapping = mapping;
        }

        public BlockModelGenerators.BlockFamilyProvider fullBlock(Block block, ModelTemplate modelTemplate) {
            this.fullBlock = BlockModelGenerators.plainModel(modelTemplate.create(block, this.mapping, BlockModelGenerators.this.modelOutput));
            if (BlockModelGenerators.FULL_BLOCK_MODEL_CUSTOM_GENERATORS.containsKey(block)) {
                BlockModelGenerators.this.blockStateOutput
                    .accept(
                        BlockModelGenerators.FULL_BLOCK_MODEL_CUSTOM_GENERATORS
                            .get(block)
                            .create(block, this.fullBlock, this.mapping, BlockModelGenerators.this.modelOutput)
                    );
            } else {
                BlockModelGenerators.this.blockStateOutput
                    .accept(BlockModelGenerators.createSimpleBlock(block, BlockModelGenerators.variant(this.fullBlock)));
            }

            return this;
        }

        public BlockModelGenerators.BlockFamilyProvider donateModelTo(Block sourceBlock, Block block) {
            Identifier identifier = ModelLocationUtils.getModelLocation(sourceBlock);
            BlockModelGenerators.this.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, BlockModelGenerators.plainVariant(identifier)));
            BlockModelGenerators.this.itemModelOutput.copy(sourceBlock.asItem(), block.asItem());
            this.skipGeneratingModelsFor.add(block);
            return this;
        }

        public BlockModelGenerators.BlockFamilyProvider button(Block buttonBlock) {
            MultiVariant multivariant = BlockModelGenerators.plainVariant(
                ModelTemplates.BUTTON.create(buttonBlock, this.mapping, BlockModelGenerators.this.modelOutput)
            );
            MultiVariant multivariant1 = BlockModelGenerators.plainVariant(
                ModelTemplates.BUTTON_PRESSED.create(buttonBlock, this.mapping, BlockModelGenerators.this.modelOutput)
            );
            BlockModelGenerators.this.blockStateOutput.accept(BlockModelGenerators.createButton(buttonBlock, multivariant, multivariant1));
            Identifier identifier = ModelTemplates.BUTTON_INVENTORY.create(buttonBlock, this.mapping, BlockModelGenerators.this.modelOutput);
            BlockModelGenerators.this.registerSimpleItemModel(buttonBlock, identifier);
            return this;
        }

        public BlockModelGenerators.BlockFamilyProvider wall(Block wallBlock) {
            MultiVariant multivariant = BlockModelGenerators.plainVariant(
                ModelTemplates.WALL_POST.create(wallBlock, this.mapping, BlockModelGenerators.this.modelOutput)
            );
            MultiVariant multivariant1 = BlockModelGenerators.plainVariant(
                ModelTemplates.WALL_LOW_SIDE.create(wallBlock, this.mapping, BlockModelGenerators.this.modelOutput)
            );
            MultiVariant multivariant2 = BlockModelGenerators.plainVariant(
                ModelTemplates.WALL_TALL_SIDE.create(wallBlock, this.mapping, BlockModelGenerators.this.modelOutput)
            );
            BlockModelGenerators.this.blockStateOutput.accept(BlockModelGenerators.createWall(wallBlock, multivariant, multivariant1, multivariant2));
            Identifier identifier = ModelTemplates.WALL_INVENTORY.create(wallBlock, this.mapping, BlockModelGenerators.this.modelOutput);
            BlockModelGenerators.this.registerSimpleItemModel(wallBlock, identifier);
            return this;
        }

        public BlockModelGenerators.BlockFamilyProvider customFence(Block fenceBlock) {
            TextureMapping texturemapping = TextureMapping.customParticle(fenceBlock);
            MultiVariant multivariant = BlockModelGenerators.plainVariant(
                ModelTemplates.CUSTOM_FENCE_POST.create(fenceBlock, texturemapping, BlockModelGenerators.this.modelOutput)
            );
            MultiVariant multivariant1 = BlockModelGenerators.plainVariant(
                ModelTemplates.CUSTOM_FENCE_SIDE_NORTH.create(fenceBlock, texturemapping, BlockModelGenerators.this.modelOutput)
            );
            MultiVariant multivariant2 = BlockModelGenerators.plainVariant(
                ModelTemplates.CUSTOM_FENCE_SIDE_EAST.create(fenceBlock, texturemapping, BlockModelGenerators.this.modelOutput)
            );
            MultiVariant multivariant3 = BlockModelGenerators.plainVariant(
                ModelTemplates.CUSTOM_FENCE_SIDE_SOUTH.create(fenceBlock, texturemapping, BlockModelGenerators.this.modelOutput)
            );
            MultiVariant multivariant4 = BlockModelGenerators.plainVariant(
                ModelTemplates.CUSTOM_FENCE_SIDE_WEST.create(fenceBlock, texturemapping, BlockModelGenerators.this.modelOutput)
            );
            BlockModelGenerators.this.blockStateOutput
                .accept(BlockModelGenerators.createCustomFence(fenceBlock, multivariant, multivariant1, multivariant2, multivariant3, multivariant4));
            Identifier identifier = ModelTemplates.CUSTOM_FENCE_INVENTORY.create(fenceBlock, texturemapping, BlockModelGenerators.this.modelOutput);
            BlockModelGenerators.this.registerSimpleItemModel(fenceBlock, identifier);
            return this;
        }

        public BlockModelGenerators.BlockFamilyProvider fence(Block fenceBlock) {
            MultiVariant multivariant = BlockModelGenerators.plainVariant(
                ModelTemplates.FENCE_POST.create(fenceBlock, this.mapping, BlockModelGenerators.this.modelOutput)
            );
            MultiVariant multivariant1 = BlockModelGenerators.plainVariant(
                ModelTemplates.FENCE_SIDE.create(fenceBlock, this.mapping, BlockModelGenerators.this.modelOutput)
            );
            BlockModelGenerators.this.blockStateOutput.accept(BlockModelGenerators.createFence(fenceBlock, multivariant, multivariant1));
            Identifier identifier = ModelTemplates.FENCE_INVENTORY.create(fenceBlock, this.mapping, BlockModelGenerators.this.modelOutput);
            BlockModelGenerators.this.registerSimpleItemModel(fenceBlock, identifier);
            return this;
        }

        public BlockModelGenerators.BlockFamilyProvider customFenceGate(Block customFenceGateBlock) {
            TextureMapping texturemapping = TextureMapping.customParticle(customFenceGateBlock);
            MultiVariant multivariant = BlockModelGenerators.plainVariant(
                ModelTemplates.CUSTOM_FENCE_GATE_OPEN.create(customFenceGateBlock, texturemapping, BlockModelGenerators.this.modelOutput)
            );
            MultiVariant multivariant1 = BlockModelGenerators.plainVariant(
                ModelTemplates.CUSTOM_FENCE_GATE_CLOSED.create(customFenceGateBlock, texturemapping, BlockModelGenerators.this.modelOutput)
            );
            MultiVariant multivariant2 = BlockModelGenerators.plainVariant(
                ModelTemplates.CUSTOM_FENCE_GATE_WALL_OPEN.create(customFenceGateBlock, texturemapping, BlockModelGenerators.this.modelOutput)
            );
            MultiVariant multivariant3 = BlockModelGenerators.plainVariant(
                ModelTemplates.CUSTOM_FENCE_GATE_WALL_CLOSED.create(customFenceGateBlock, texturemapping, BlockModelGenerators.this.modelOutput)
            );
            BlockModelGenerators.this.blockStateOutput
                .accept(BlockModelGenerators.createFenceGate(customFenceGateBlock, multivariant, multivariant1, multivariant2, multivariant3, false));
            return this;
        }

        public BlockModelGenerators.BlockFamilyProvider fenceGate(Block fenceGateBlock) {
            MultiVariant multivariant = BlockModelGenerators.plainVariant(
                ModelTemplates.FENCE_GATE_OPEN.create(fenceGateBlock, this.mapping, BlockModelGenerators.this.modelOutput)
            );
            MultiVariant multivariant1 = BlockModelGenerators.plainVariant(
                ModelTemplates.FENCE_GATE_CLOSED.create(fenceGateBlock, this.mapping, BlockModelGenerators.this.modelOutput)
            );
            MultiVariant multivariant2 = BlockModelGenerators.plainVariant(
                ModelTemplates.FENCE_GATE_WALL_OPEN.create(fenceGateBlock, this.mapping, BlockModelGenerators.this.modelOutput)
            );
            MultiVariant multivariant3 = BlockModelGenerators.plainVariant(
                ModelTemplates.FENCE_GATE_WALL_CLOSED.create(fenceGateBlock, this.mapping, BlockModelGenerators.this.modelOutput)
            );
            BlockModelGenerators.this.blockStateOutput
                .accept(BlockModelGenerators.createFenceGate(fenceGateBlock, multivariant, multivariant1, multivariant2, multivariant3, true));
            return this;
        }

        public BlockModelGenerators.BlockFamilyProvider pressurePlate(Block pressurePlateBlock) {
            MultiVariant multivariant = BlockModelGenerators.plainVariant(
                ModelTemplates.PRESSURE_PLATE_UP.create(pressurePlateBlock, this.mapping, BlockModelGenerators.this.modelOutput)
            );
            MultiVariant multivariant1 = BlockModelGenerators.plainVariant(
                ModelTemplates.PRESSURE_PLATE_DOWN.create(pressurePlateBlock, this.mapping, BlockModelGenerators.this.modelOutput)
            );
            BlockModelGenerators.this.blockStateOutput.accept(BlockModelGenerators.createPressurePlate(pressurePlateBlock, multivariant, multivariant1));
            return this;
        }

        public BlockModelGenerators.BlockFamilyProvider sign(Block signBlock) {
            if (this.family == null) {
                throw new IllegalStateException("Family not defined");
            } else {
                Block block = this.family.getVariants().get(BlockFamily.Variant.WALL_SIGN);
                MultiVariant multivariant = BlockModelGenerators.plainVariant(
                    ModelTemplates.PARTICLE_ONLY.create(signBlock, this.mapping, BlockModelGenerators.this.modelOutput)
                );
                BlockModelGenerators.this.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(signBlock, multivariant));
                BlockModelGenerators.this.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, multivariant));
                BlockModelGenerators.this.registerSimpleFlatItemModel(signBlock.asItem());
                return this;
            }
        }

        public BlockModelGenerators.BlockFamilyProvider slab(Block slabBlock) {
            if (this.fullBlock == null) {
                throw new IllegalStateException("Full block not generated yet");
            } else {
                Identifier identifier = this.getOrCreateModel(ModelTemplates.SLAB_BOTTOM, slabBlock);
                MultiVariant multivariant = BlockModelGenerators.plainVariant(this.getOrCreateModel(ModelTemplates.SLAB_TOP, slabBlock));
                BlockModelGenerators.this.blockStateOutput
                    .accept(
                        BlockModelGenerators.createSlab(
                            slabBlock, BlockModelGenerators.plainVariant(identifier), multivariant, BlockModelGenerators.variant(this.fullBlock)
                        )
                    );
                BlockModelGenerators.this.registerSimpleItemModel(slabBlock, identifier);
                return this;
            }
        }

        public BlockModelGenerators.BlockFamilyProvider stairs(Block stairsBlock) {
            MultiVariant multivariant = BlockModelGenerators.plainVariant(this.getOrCreateModel(ModelTemplates.STAIRS_INNER, stairsBlock));
            Identifier identifier = this.getOrCreateModel(ModelTemplates.STAIRS_STRAIGHT, stairsBlock);
            MultiVariant multivariant1 = BlockModelGenerators.plainVariant(this.getOrCreateModel(ModelTemplates.STAIRS_OUTER, stairsBlock));
            BlockModelGenerators.this.blockStateOutput
                .accept(BlockModelGenerators.createStairs(stairsBlock, multivariant, BlockModelGenerators.plainVariant(identifier), multivariant1));
            BlockModelGenerators.this.registerSimpleItemModel(stairsBlock, identifier);
            return this;
        }

        public BlockModelGenerators.BlockFamilyProvider fullBlockVariant(Block block) {
            TexturedModel texturedmodel = BlockModelGenerators.TEXTURED_MODELS.getOrDefault(block, TexturedModel.CUBE.get(block));
            MultiVariant multivariant = BlockModelGenerators.plainVariant(texturedmodel.create(block, BlockModelGenerators.this.modelOutput));
            BlockModelGenerators.this.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, multivariant));
            return this;
        }

        public BlockModelGenerators.BlockFamilyProvider door(Block doorBlock) {
            BlockModelGenerators.this.createDoor(doorBlock);
            return this;
        }

        public void trapdoor(Block trapdoorBlock) {
            if (BlockModelGenerators.NON_ORIENTABLE_TRAPDOOR.contains(trapdoorBlock)) {
                BlockModelGenerators.this.createTrapdoor(trapdoorBlock);
            } else {
                BlockModelGenerators.this.createOrientableTrapdoor(trapdoorBlock);
            }
        }

        public Identifier getOrCreateModel(ModelTemplate modelTemplate, Block block) {
            return this.models.computeIfAbsent(modelTemplate, p_465436_ -> p_465436_.create(block, this.mapping, BlockModelGenerators.this.modelOutput));
        }

        public BlockModelGenerators.BlockFamilyProvider generateFor(BlockFamily family) {
            this.family = family;
            family.getVariants().forEach((p_388584_, p_388675_) -> {
                if (!this.skipGeneratingModelsFor.contains(p_388675_)) {
                    BiConsumer<BlockModelGenerators.BlockFamilyProvider, Block> biconsumer = BlockModelGenerators.SHAPE_CONSUMERS.get(p_388584_);
                    if (biconsumer != null) {
                        biconsumer.accept(this, p_388675_);
                    }
                }
            });
            return this;
        }
    }

    @FunctionalInterface
    @OnlyIn(Dist.CLIENT)
    public interface BlockStateGeneratorSupplier {
        BlockModelDefinitionGenerator create(Block block, Variant variant, TextureMapping textureMapping, BiConsumer<Identifier, ModelInstance> output);
    }

    @OnlyIn(Dist.CLIENT)
    public record BookSlotModelCacheKey(ModelTemplate template, String modelSuffix) {
    }

    @OnlyIn(Dist.CLIENT)
    public static enum PlantType {
        TINTED(ModelTemplates.TINTED_CROSS, ModelTemplates.TINTED_FLOWER_POT_CROSS, false),
        NOT_TINTED(ModelTemplates.CROSS, ModelTemplates.FLOWER_POT_CROSS, false),
        EMISSIVE_NOT_TINTED(ModelTemplates.CROSS_EMISSIVE, ModelTemplates.FLOWER_POT_CROSS_EMISSIVE, true);

        private final ModelTemplate blockTemplate;
        private final ModelTemplate flowerPotTemplate;
        private final boolean isEmissive;

        private PlantType(ModelTemplate blockTemplate, ModelTemplate flowerPotTemplate, boolean isEmissive) {
            this.blockTemplate = blockTemplate;
            this.flowerPotTemplate = flowerPotTemplate;
            this.isEmissive = isEmissive;
        }

        public ModelTemplate getCross() {
            return this.blockTemplate;
        }

        public ModelTemplate getCrossPot() {
            return this.flowerPotTemplate;
        }

        public Identifier createItemModel(BlockModelGenerators generator, Block block) {
            Item item = block.asItem();
            return this.isEmissive
                ? generator.createFlatItemModelWithBlockTextureAndOverlay(item, block, "_emissive")
                : generator.createFlatItemModelWithBlockTexture(item, block);
        }

        public TextureMapping getTextureMapping(Block block) {
            return this.isEmissive ? TextureMapping.crossEmissive(block) : TextureMapping.cross(block);
        }

        public TextureMapping getPlantTextureMapping(Block block) {
            return this.isEmissive ? TextureMapping.plantEmissive(block) : TextureMapping.plant(block);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public class WoodProvider {
        private final TextureMapping logMapping;

        public WoodProvider(TextureMapping logMapping) {
            this.logMapping = logMapping;
        }

        public BlockModelGenerators.WoodProvider wood(Block woodBlock) {
            TextureMapping texturemapping = this.logMapping.copyAndUpdate(TextureSlot.END, this.logMapping.get(TextureSlot.SIDE));
            Identifier identifier = ModelTemplates.CUBE_COLUMN.create(woodBlock, texturemapping, BlockModelGenerators.this.modelOutput);
            BlockModelGenerators.this.blockStateOutput
                .accept(BlockModelGenerators.createAxisAlignedPillarBlock(woodBlock, BlockModelGenerators.plainVariant(identifier)));
            BlockModelGenerators.this.registerSimpleItemModel(woodBlock, identifier);
            return this;
        }

        public BlockModelGenerators.WoodProvider log(Block logBlock) {
            Identifier identifier = ModelTemplates.CUBE_COLUMN.create(logBlock, this.logMapping, BlockModelGenerators.this.modelOutput);
            BlockModelGenerators.this.blockStateOutput
                .accept(BlockModelGenerators.createAxisAlignedPillarBlock(logBlock, BlockModelGenerators.plainVariant(identifier)));
            BlockModelGenerators.this.registerSimpleItemModel(logBlock, identifier);
            return this;
        }

        public BlockModelGenerators.WoodProvider logWithHorizontal(Block logBlock) {
            Identifier identifier = ModelTemplates.CUBE_COLUMN.create(logBlock, this.logMapping, BlockModelGenerators.this.modelOutput);
            MultiVariant multivariant = BlockModelGenerators.plainVariant(
                ModelTemplates.CUBE_COLUMN_HORIZONTAL.create(logBlock, this.logMapping, BlockModelGenerators.this.modelOutput)
            );
            BlockModelGenerators.this.blockStateOutput
                .accept(BlockModelGenerators.createRotatedPillarWithHorizontalVariant(logBlock, BlockModelGenerators.plainVariant(identifier), multivariant));
            BlockModelGenerators.this.registerSimpleItemModel(logBlock, identifier);
            return this;
        }

        public BlockModelGenerators.WoodProvider logUVLocked(Block logBlock) {
            BlockModelGenerators.this.blockStateOutput
                .accept(BlockModelGenerators.createPillarBlockUVLocked(logBlock, this.logMapping, BlockModelGenerators.this.modelOutput));
            BlockModelGenerators.this.registerSimpleItemModel(
                logBlock, ModelTemplates.CUBE_COLUMN.create(logBlock, this.logMapping, BlockModelGenerators.this.modelOutput)
            );
            return this;
        }
    }

    /**
     * Neo: create a {@link BlockModelGenerators.BlockFamilyProvider} which re-uses the existing model of the given full
     * block instead of creating a model and blockstate file for it. Intended for use cases where the full block is
     * separately generated or otherwise exists such as when a dummy {@link BlockFamily} is used to create additional
     * variants for existing vanilla block families
     */
    public BlockModelGenerators.BlockFamilyProvider familyWithExistingFullBlock(Block fullBlock) {
        var provider = new BlockModelGenerators.BlockFamilyProvider(TextureMapping.cube(fullBlock));
        provider.fullBlock = BlockModelGenerators.plainModel(ModelLocationUtils.getModelLocation(fullBlock));
        return provider;
    }
}
