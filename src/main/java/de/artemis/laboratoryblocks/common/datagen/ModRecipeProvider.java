package de.artemis.laboratoryblocks.common.datagen;

import de.artemis.laboratoryblocks.common.registry.ModBlocks;
import de.artemis.laboratoryblocks.common.registry.ModBlockPalettes;
import de.artemis.laboratoryblocks.common.registry.ModItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.TagKey;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.references.BlockItemIds;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

public class ModRecipeProvider extends RecipeProvider {
    private final HolderGetter<Item> items;

    public ModRecipeProvider(HolderLookup.Provider provider, BootstrapContext<Recipe<?>> recipes, BootstrapContext<Advancement> advancements) {
        super(recipes, advancements);
        this.items = provider.lookupOrThrow(Registries.ITEM);
    }

    @Override
    public void buildRecipes() {
        ModBlockPalettes.BLOCK_ORDER.stream().filter(pair -> pair.dark() != null).forEach(pair -> {
            shapeless(RecipeCategory.BUILDING_BLOCKS, pair.dark().get(), 8)
                    .requires(pair.original().get(), 8)
                    .requires(tag(dyeTag(DyeColor.BLACK)))
                    .unlockedBy(getHasName(pair.original().get()), has(pair.original().get()))
                    .save(output, modLoc(pair.dark().get() == ModBlocks.DARK_LABORATORY_BLOCK.get()
                            ? "dark_laboratory_block"
                            : BuiltInRegistries.BLOCK.getKey(pair.dark().get()).getPath() + "_from_black_dye"));
            shapeless(RecipeCategory.BUILDING_BLOCKS, pair.original().get(), 8)
                    .requires(pair.dark().get(), 8)
                    .requires(tag(dyeTag(DyeColor.WHITE)))
                    .unlockedBy(getHasName(pair.dark().get()), has(pair.dark().get()))
                    .save(output, modLoc(BuiltInRegistries.BLOCK.getKey(pair.original().get()).getPath() + "_from_white_dye"));
        });
        shaped(RecipeCategory.TOOLS, ModItems.CONFIGURATION_TOOL.get())
                .pattern("  C")
                .pattern("DB ")
                .pattern("AD ")
                .define('A', Items.IRON_INGOT)
                .define('B', woolIngredient(DyeColor.ORANGE))
                .define('C', ModItems.IRON_SCREW.get())
                .define('D', Items.IRON_NUGGET)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(output);

        shaped(RecipeCategory.MISC, ModItems.IRON_SCREW.get(), 16)
                .pattern("ABA")
                .pattern(" A ")
                .define('A', Items.IRON_NUGGET)
                .define('B', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(output);

        shapeless(RecipeCategory.MISC, ModItems.GLOWSTONE_PARTICLES.get(), 8)
                .requires(Items.GLOWSTONE_DUST)
                .unlockedBy(getHasName(Items.GLOWSTONE_DUST), has(Items.GLOWSTONE_DUST))
                .save(output);

        shapeless(RecipeCategory.MISC, Items.GLOWSTONE_DUST)
                .requires(ModItems.GLOWSTONE_PARTICLES.get(), 8)
                .unlockedBy(getHasName(ModItems.GLOWSTONE_PARTICLES.get()), has(ModItems.GLOWSTONE_PARTICLES.get()))
                .save(output, modLoc("glowstone_dust_from_glowstone_particles"));

        shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LABORATORY_BLOCK.get(), 8)
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', Blocks.STONE)
                .define('B', Items.QUARTZ)
                .unlockedBy(getHasName(Blocks.STONE), has(Blocks.STONE))
                .save(output);

        addPaletteRecipes(false);
        addPaletteRecipes(true);

        ModDatagenEntries.WOOD_FAMILIES.forEach(this::addWoodRecipes);
        addStonecutterRecipes(false);
        addStonecutterRecipes(true);

        ModDatagenEntries.ALL_PAIRS.forEach(pair -> addGlowstoneUpgrade(pair.base().get(), pair.glowing().get()));
        ModDatagenEntries.PILLAR_PAIRS.forEach(pair -> addGlowstoneUpgrade(pair.base().get(), pair.glowing().get()));
        ModDatagenEntries.FAN_PAIRS.forEach(pair -> addGlowstoneUpgrade(pair.base().get(), pair.glowing().get()));
    }

    private void addPaletteRecipes(boolean dark) {
        shaped(RecipeCategory.BUILDING_BLOCKS, palette(ModBlocks.REINFORCED_LABORATORY_BLOCK.get(), dark), 2)
                .pattern("A A")
                .pattern(" B ")
                .pattern("A A")
                .define('A', ModItems.IRON_SCREW.get())
                .define('B', palette(ModBlocks.LABORATORY_BLOCK.get(), dark))
                .unlockedBy(getHasName(palette(ModBlocks.LABORATORY_BLOCK.get(), dark)), has(palette(ModBlocks.LABORATORY_BLOCK.get(), dark)))
                .save(output);

        shaped(RecipeCategory.BUILDING_BLOCKS, palette(ModBlocks.LABORATORY_TILES.get(), dark), 8)
                .pattern("AA")
                .pattern("AA")
                .define('A', palette(ModBlocks.LABORATORY_BLOCK.get(), dark))
                .unlockedBy(getHasName(palette(ModBlocks.LABORATORY_BLOCK.get(), dark)), has(palette(ModBlocks.LABORATORY_BLOCK.get(), dark)))
                .save(output);

        if (!dark) {
            shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.GRAY_LABORATORY_TILES.get(), 8)
                    .pattern("AAA")
                    .pattern("ABA")
                    .pattern("AAA")
                    .define('A', ModBlocks.LABORATORY_TILES.get())
                    .define('B', dyeTag(DyeColor.GRAY))
                    .unlockedBy(getHasName(ModBlocks.LABORATORY_TILES.get()), has(ModBlocks.LABORATORY_TILES.get()))
                    .unlockedBy("has_gray_dye", has(dyeTag(DyeColor.GRAY)))
                    .save(output);
        }

        if (!dark) {
            shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MIXED_LABORATORY_TILES.get(), 2)
                    .requires(ModBlocks.GRAY_LABORATORY_TILES.get())
                    .requires(ModBlocks.LABORATORY_TILES.get())
                    .unlockedBy(getHasName(ModBlocks.GRAY_LABORATORY_TILES.get()), has(ModBlocks.GRAY_LABORATORY_TILES.get()))
                    .save(output);
        }

        shaped(RecipeCategory.BUILDING_BLOCKS, palette(ModBlocks.LABORATORY_VENT.get(), dark))
                .pattern(" B ")
                .pattern("BAB")
                .pattern(" B ")
                .define('A', palette(ModBlocks.LABORATORY_BLOCK.get(), dark))
                .define('B', Items.IRON_NUGGET)
                .unlockedBy(getHasName(palette(ModBlocks.LABORATORY_BLOCK.get(), dark)), has(palette(ModBlocks.LABORATORY_BLOCK.get(), dark)))
                .unlockedBy(getHasName(Items.IRON_NUGGET), has(Items.IRON_NUGGET))
                .save(output);

        shaped(RecipeCategory.BUILDING_BLOCKS, palette(ModBlocks.LABORATORY_PILLAR.get(), dark), 2)
                .pattern("A")
                .pattern("A")
                .define('A', palette(ModBlocks.LABORATORY_BLOCK.get(), dark))
                .unlockedBy(getHasName(palette(ModBlocks.LABORATORY_BLOCK.get(), dark)), has(palette(ModBlocks.LABORATORY_BLOCK.get(), dark)))
                .save(output);

        shaped(RecipeCategory.BUILDING_BLOCKS, palette(ModBlocks.LABORATORY_GLASS.get(), dark), 8)
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', Items.GLASS)
                .define('B', palette(ModBlocks.LABORATORY_BLOCK.get(), dark))
                .unlockedBy(getHasName(Items.GLASS), has(Items.GLASS))
                .unlockedBy(getHasName(palette(ModBlocks.LABORATORY_BLOCK.get(), dark)), has(palette(ModBlocks.LABORATORY_BLOCK.get(), dark)))
                .save(output);

        shaped(RecipeCategory.BUILDING_BLOCKS, palette(ModBlocks.LABORATORY_FAN.get(), dark))
                .pattern("ABA")
                .pattern("BCB")
                .pattern("ABA")
                .define('A', palette(ModBlocks.LABORATORY_BLOCK.get(), dark))
                .define('B', Items.IRON_NUGGET)
                .define('C', ModItems.IRON_SCREW.get())
                .unlockedBy(getHasName(palette(ModBlocks.LABORATORY_BLOCK.get(), dark)), has(palette(ModBlocks.LABORATORY_BLOCK.get(), dark)))
                .unlockedBy(getHasName(Items.IRON_NUGGET), has(Items.IRON_NUGGET))
                .unlockedBy(getHasName(ModItems.IRON_SCREW.get()), has(ModItems.IRON_SCREW.get()))
                .save(output);

        shapeless(RecipeCategory.BUILDING_BLOCKS, palette(ModBlocks.LABORATORY_FAN_REDSTONE_CONTROLLED.get(), dark))
                .requires(palette(ModBlocks.LABORATORY_FAN.get(), dark))
                .requires(Items.REDSTONE)
                .unlockedBy(getHasName(palette(ModBlocks.LABORATORY_FAN.get(), dark)), has(palette(ModBlocks.LABORATORY_FAN.get(), dark)))
                .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE))
                .save(output);

        addScreenRecipes(dark);
        addDoorAndTrapdoorRecipes(dark);

        addIndicatingRecipes(
                palette(ModBlocks.RIGHT_INDICATING_BLUE_LABORATORY_BLOCK.get(), dark),
                palette(ModBlocks.LEFT_INDICATING_BLUE_LABORATORY_BLOCK.get(), dark),
                DyeColor.BLUE
        );
        addIndicatingRecipes(
                palette(ModBlocks.RIGHT_INDICATING_RED_LABORATORY_BLOCK.get(), dark),
                palette(ModBlocks.LEFT_INDICATING_RED_LABORATORY_BLOCK.get(), dark),
                DyeColor.RED
        );
        addIndicatingRecipes(
                palette(ModBlocks.RIGHT_INDICATING_GREEN_LABORATORY_BLOCK.get(), dark),
                palette(ModBlocks.LEFT_INDICATING_GREEN_LABORATORY_BLOCK.get(), dark),
                DyeColor.GREEN
        );

    }

    private Block palette(Block original, boolean dark) {
        return dark ? ModBlockPalettes.darkVariant(original) : original;
    }

    private void addWoodRecipes(ModDatagenEntries.WoodFamily family) {
        Block base = ModBlockPalettes.isDark(family.floorPair().base().get()) ? ModBlocks.DARK_LABORATORY_BLOCK.get() : ModBlocks.LABORATORY_BLOCK.get();
        shaped(RecipeCategory.BUILDING_BLOCKS, family.floorPair().base().get(), 8)
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', base)
                .define('B', family.planks())
                .unlockedBy(getHasName(base), has(base))
                .unlockedBy(getHasName(family.planks()), has(family.planks()))
                .save(output);

        shaped(RecipeCategory.BUILDING_BLOCKS, family.tilePair().base().get(), 8)
                .pattern("AA")
                .pattern("AA")
                .define('A', family.floorPair().base().get())
                .unlockedBy(getHasName(family.floorPair().base().get()), has(family.floorPair().base().get()))
                .save(output);

        addStonecuttingRecipe(family.floorPair().base().get(), family.tilePair().base().get(), 2);
        // Each glowing block contains one recoverable Glowstone Particle; never multiply it.
        addStonecuttingRecipe(family.floorPair().glowing().get(), family.tilePair().glowing().get(), 1);
    }

    private void addGlowstoneUpgrade(Block base, Block glowing) {
        shapeless(RecipeCategory.BUILDING_BLOCKS, glowing)
                .requires(base)
                .requires(ModItems.GLOWSTONE_PARTICLES.get())
                .unlockedBy(getHasName(base), has(base))
                .save(output);
    }

    private void addScreenRecipes(boolean dark) {
        shaped(RecipeCategory.BUILDING_BLOCKS, palette(ModBlocks.CLEAR_LABORATORY_SCREEN.get(), dark), 4)
                .pattern("BAB")
                .pattern("ACA")
                .pattern("BAB")
                .define('A', palette(ModBlocks.LABORATORY_BLOCK.get(), dark))
                .define('B', ModItems.IRON_SCREW.get())
                .define('C', palette(ModBlocks.LABORATORY_GLASS.get(), dark))
                .unlockedBy(getHasName(palette(ModBlocks.LABORATORY_BLOCK.get(), dark)), has(palette(ModBlocks.LABORATORY_BLOCK.get(), dark)))
                .unlockedBy(getHasName(ModItems.IRON_SCREW.get()), has(ModItems.IRON_SCREW.get()))
                .unlockedBy(getHasName(palette(ModBlocks.LABORATORY_GLASS.get(), dark)), has(palette(ModBlocks.LABORATORY_GLASS.get(), dark)))
                .save(output);

        shapeless(RecipeCategory.BUILDING_BLOCKS, palette(ModBlocks.WAVE_LABORATORY_SCREEN.get(), dark), 4)
                .requires(palette(ModBlocks.CLEAR_LABORATORY_SCREEN.get(), dark), 4)
                .requires(tag(dyeTag(DyeColor.CYAN)))
                .unlockedBy(getHasName(palette(ModBlocks.CLEAR_LABORATORY_SCREEN.get(), dark)), has(palette(ModBlocks.CLEAR_LABORATORY_SCREEN.get(), dark)))
                .unlockedBy("has_cyan_dye", has(dyeTag(DyeColor.CYAN)))
                .save(output);

        shapeless(RecipeCategory.BUILDING_BLOCKS, palette(ModBlocks.TEXT_LABORATORY_SCREEN.get(), dark), 4)
                .requires(palette(ModBlocks.CLEAR_LABORATORY_SCREEN.get(), dark), 4)
                .requires(tag(dyeTag(DyeColor.LIME)))
                .unlockedBy(getHasName(palette(ModBlocks.CLEAR_LABORATORY_SCREEN.get(), dark)), has(palette(ModBlocks.CLEAR_LABORATORY_SCREEN.get(), dark)))
                .unlockedBy("has_lime_dye", has(dyeTag(DyeColor.LIME)))
                .save(output);

        shapeless(RecipeCategory.BUILDING_BLOCKS, palette(ModBlocks.QUANTUM_LABORATORY_SCREEN.get(), dark), 4)
                .requires(palette(ModBlocks.CLEAR_LABORATORY_SCREEN.get(), dark), 4)
                .requires(tag(dyeTag(DyeColor.PURPLE)))
                .unlockedBy(getHasName(palette(ModBlocks.CLEAR_LABORATORY_SCREEN.get(), dark)), has(palette(ModBlocks.CLEAR_LABORATORY_SCREEN.get(), dark)))
                .unlockedBy("has_purple_dye", has(dyeTag(DyeColor.PURPLE)))
                .save(output);
    }

    private void addStonecutterRecipes(boolean dark) {
        addStonecuttingRecipe(palette(ModBlocks.LABORATORY_BLOCK.get(), dark), palette(ModBlocks.LABORATORY_TILES.get(), dark), 2);
        addStonecuttingRecipe(palette(ModBlocks.LABORATORY_BLOCK.get(), dark), palette(ModBlocks.LABORATORY_PILLAR.get(), dark), 1);
        addStonecuttingRecipe(palette(ModBlocks.LABORATORY_BLOCK.get(), dark), palette(ModBlocks.LABORATORY_DOOR.get(), dark), 1);
        addStonecuttingRecipe(palette(ModBlocks.LABORATORY_BLOCK.get(), dark), palette(ModBlocks.LABORATORY_TRAPDOOR.get(), dark), 1);
        addStonecuttingRecipe(palette(ModBlocks.GLOWING_LABORATORY_BLOCK.get(), dark), palette(ModBlocks.GLOWING_LABORATORY_TILES.get(), dark), 1);
        addStonecuttingRecipe(palette(ModBlocks.GLOWING_LABORATORY_BLOCK.get(), dark), palette(ModBlocks.GLOWING_LABORATORY_PILLAR.get(), dark), 1);
    }

    private void addIndicatingRecipes(Block rightBlock, Block leftBlock, DyeColor color) {
        shaped(RecipeCategory.BUILDING_BLOCKS, rightBlock, 8)
                .pattern("AAA")
                .pattern("BBC")
                .pattern("AAA")
                .define('A', ModBlockPalettes.isDark(rightBlock) ? ModBlocks.DARK_LABORATORY_BLOCK.get() : ModBlocks.LABORATORY_BLOCK.get())
                .define('B', woolIngredient(color))
                .define('C', woolIngredient(DyeColor.BLACK))
                .unlockedBy("has_laboratory_block", has(ModBlockPalettes.isDark(rightBlock) ? ModBlocks.DARK_LABORATORY_BLOCK.get() : ModBlocks.LABORATORY_BLOCK.get()))
                .unlockedBy("has_wool", has(ItemTags.WOOL))
                .save(output);

        // Shaped crafting also accepts mirrored patterns, so opposite arrows need an explicit conversion.
        shapeless(RecipeCategory.BUILDING_BLOCKS, leftBlock)
                .requires(rightBlock)
                .unlockedBy(getHasName(rightBlock), has(rightBlock))
                .save(output);
        shapeless(RecipeCategory.BUILDING_BLOCKS, rightBlock)
                .requires(leftBlock)
                .unlockedBy(getHasName(leftBlock), has(leftBlock))
                .save(output, modLoc(BuiltInRegistries.BLOCK.getKey(rightBlock).getPath() + "_from_left"));
    }

    private void addDoorAndTrapdoorRecipes(boolean dark) {
        shaped(RecipeCategory.BUILDING_BLOCKS, palette(ModBlocks.LABORATORY_DOOR.get(), dark), 3)
                .pattern("AA ")
                .pattern("AA ")
                .pattern("AA ")
                .define('A', palette(ModBlocks.LABORATORY_BLOCK.get(), dark))
                .unlockedBy(getHasName(palette(ModBlocks.LABORATORY_BLOCK.get(), dark)), has(palette(ModBlocks.LABORATORY_BLOCK.get(), dark)))
                .save(output);

        shaped(RecipeCategory.BUILDING_BLOCKS, palette(ModBlocks.LABORATORY_TRAPDOOR.get(), dark), 2)
                .pattern("   ")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', palette(ModBlocks.LABORATORY_BLOCK.get(), dark))
                .unlockedBy(getHasName(palette(ModBlocks.LABORATORY_BLOCK.get(), dark)), has(palette(ModBlocks.LABORATORY_BLOCK.get(), dark)))
                .save(output);

        shaped(RecipeCategory.BUILDING_BLOCKS, palette(ModBlocks.MESH_LABORATORY_DOOR.get(), dark), 3)
                .pattern("AA ")
                .pattern("BB ")
                .pattern("AA ")
                .define('A', palette(ModBlocks.LABORATORY_BLOCK.get(), dark))
                .define('B', Items.IRON_BARS)
                .unlockedBy(getHasName(palette(ModBlocks.LABORATORY_BLOCK.get(), dark)), has(palette(ModBlocks.LABORATORY_BLOCK.get(), dark)))
                .unlockedBy(getHasName(Items.IRON_BARS), has(Items.IRON_BARS))
                .save(output);

        shaped(RecipeCategory.BUILDING_BLOCKS, palette(ModBlocks.MESH_LABORATORY_TRAPDOOR.get(), dark), 2)
                .pattern("   ")
                .pattern("ABA")
                .pattern("ABA")
                .define('A', palette(ModBlocks.LABORATORY_BLOCK.get(), dark))
                .define('B', Items.IRON_BARS)
                .unlockedBy(getHasName(palette(ModBlocks.LABORATORY_BLOCK.get(), dark)), has(palette(ModBlocks.LABORATORY_BLOCK.get(), dark)))
                .unlockedBy(getHasName(Items.IRON_BARS), has(Items.IRON_BARS))
                .save(output);

        shaped(RecipeCategory.BUILDING_BLOCKS, palette(ModBlocks.GLASS_LABORATORY_DOOR.get(), dark), 3)
                .pattern("AA ")
                .pattern("BB ")
                .pattern("AA ")
                .define('A', palette(ModBlocks.LABORATORY_BLOCK.get(), dark))
                .define('B', Items.GLASS_PANE)
                .unlockedBy(getHasName(palette(ModBlocks.LABORATORY_BLOCK.get(), dark)), has(palette(ModBlocks.LABORATORY_BLOCK.get(), dark)))
                .unlockedBy(getHasName(Items.GLASS_PANE), has(Items.GLASS_PANE))
                .save(output);

        shaped(RecipeCategory.BUILDING_BLOCKS, palette(ModBlocks.GLASS_LABORATORY_TRAPDOOR.get(), dark), 2)
                .pattern("   ")
                .pattern("ABA")
                .pattern("ABA")
                .define('A', palette(ModBlocks.LABORATORY_BLOCK.get(), dark))
                .define('B', Items.GLASS_PANE)
                .unlockedBy(getHasName(palette(ModBlocks.LABORATORY_BLOCK.get(), dark)), has(palette(ModBlocks.LABORATORY_BLOCK.get(), dark)))
                .unlockedBy(getHasName(Items.GLASS_PANE), has(Items.GLASS_PANE))
                .save(output);
    }

    private void addStonecuttingRecipe(Block input, Block outputBlock, int count) {
        Identifier inputId = BuiltInRegistries.BLOCK.getKey(input);
        Identifier outputId = BuiltInRegistries.BLOCK.getKey(outputBlock);

        SingleItemRecipeBuilder.stonecutting(Ingredient.of(input), RecipeCategory.BUILDING_BLOCKS, outputBlock, count)
                .unlockedBy(getHasName(input), has(input))
                .save(output, modLoc(outputId.getPath() + "_from_" + inputId.getPath() + "_stonecutting"));
    }

    private static @NotNull ResourceKey<net.minecraft.world.item.crafting.Recipe<?>> modLoc(String path) {
        return ResourceKey.create(Registries.RECIPE, Identifier.fromNamespaceAndPath(ModBlocks.LABORATORY_BLOCK.getId().getNamespace(), path));
    }

    private Ingredient woolIngredient(DyeColor color) {
        return Ingredient.of(items.getOrThrow(BlockItemIds.WOOL.pick(color).item()).value());
    }
    private static TagKey<Item> dyeTag(DyeColor color) {
        return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("c", "dyes/" + color.getName()));
    }
}
