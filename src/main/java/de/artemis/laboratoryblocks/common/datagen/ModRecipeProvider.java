package de.artemis.laboratoryblocks.common.datagen;

import de.artemis.laboratoryblocks.common.registry.ModBlocks;
import de.artemis.laboratoryblocks.common.registry.ModBlockPalettes;
import de.artemis.laboratoryblocks.common.registry.ModItems;

import net.minecraft.util.registry.Registry;


import net.minecraft.data.RecipeProvider;

import net.minecraft.data.DataGenerator;

import net.minecraft.data.SingleItemRecipeBuilder;

import net.minecraft.util.ResourceLocation;

import net.minecraft.item.Items;
import net.minecraft.util.IItemProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.item.DyeColor;
import net.minecraft.item.crafting.Ingredient;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public class ModRecipeProvider extends RecipeProvider {
    private java.util.function.Consumer<net.minecraft.data.IFinishedRecipe> output;
    public ModRecipeProvider(DataGenerator output) {
        super(output);
    }

    @Override
    protected void buildShapelessRecipes(java.util.function.Consumer<net.minecraft.data.IFinishedRecipe> recipeOutput) {
        this.output = recipeOutput;
        ModBlockPalettes.BLOCK_ORDER.stream().filter(pair -> pair.dark() != null).forEach(pair -> {
            net.minecraft.data.ShapelessRecipeBuilder.shapeless(pair.dark().get(), 8)
                    .requires(pair.original().get(), 8)
                    .requires(Items.BLACK_DYE)
                    .unlockedBy(getHasName(pair.original().get()), has(pair.original().get()))
                    .save(output, modLoc(pair.dark().get() == ModBlocks.DARK_LABORATORY_BLOCK.get()
                            ? "dark_laboratory_block"
                            : Registry.BLOCK.getKey(pair.dark().get()).getPath() + "_from_black_dye"));
            net.minecraft.data.ShapelessRecipeBuilder.shapeless(pair.original().get(), 8)
                    .requires(pair.dark().get(), 8)
                    .requires(Items.WHITE_DYE)
                    .unlockedBy(getHasName(pair.dark().get()), has(pair.dark().get()))
                    .save(output, modLoc(Registry.BLOCK.getKey(pair.original().get()).getPath() + "_from_white_dye"));
        });
        net.minecraft.data.ShapedRecipeBuilder.shaped(ModItems.CONFIGURATION_TOOL.get())
                .pattern("  C")
                .pattern("DB ")
                .pattern("AD ")
                .define('A', Items.IRON_INGOT)
                .define('B', woolIngredient(DyeColor.ORANGE))
                .define('C', ModItems.IRON_SCREW.get())
                .define('D', Items.IRON_NUGGET)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(output);

        net.minecraft.data.ShapedRecipeBuilder.shaped(ModItems.IRON_SCREW.get(), 16)
                .pattern("ABA")
                .pattern(" A ")
                .define('A', Items.IRON_NUGGET)
                .define('B', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(output);

        net.minecraft.data.ShapelessRecipeBuilder.shapeless(ModItems.GLOWSTONE_PARTICLES.get(), 8)
                .requires(Items.GLOWSTONE_DUST)
                .unlockedBy(getHasName(Items.GLOWSTONE_DUST), has(Items.GLOWSTONE_DUST))
                .save(output);

        net.minecraft.data.ShapelessRecipeBuilder.shapeless(Items.GLOWSTONE_DUST)
                .requires(ModItems.GLOWSTONE_PARTICLES.get(), 8)
                .unlockedBy(getHasName(ModItems.GLOWSTONE_PARTICLES.get()), has(ModItems.GLOWSTONE_PARTICLES.get()))
                .save(output, modLoc("glowstone_dust_from_glowstone_particles"));

        net.minecraft.data.ShapedRecipeBuilder.shaped(ModBlocks.LABORATORY_BLOCK.get(), 8)
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
        net.minecraft.data.ShapedRecipeBuilder.shaped(palette(ModBlocks.REINFORCED_LABORATORY_BLOCK.get(), dark), 2)
                .pattern("A A")
                .pattern(" B ")
                .pattern("A A")
                .define('A', ModItems.IRON_SCREW.get())
                .define('B', palette(ModBlocks.LABORATORY_BLOCK.get(), dark))
                .unlockedBy(getHasName(palette(ModBlocks.LABORATORY_BLOCK.get(), dark)), has(palette(ModBlocks.LABORATORY_BLOCK.get(), dark)))
                .save(output);

        net.minecraft.data.ShapedRecipeBuilder.shaped(palette(ModBlocks.LABORATORY_TILES.get(), dark), 8)
                .pattern("AA")
                .pattern("AA")
                .define('A', palette(ModBlocks.LABORATORY_BLOCK.get(), dark))
                .unlockedBy(getHasName(palette(ModBlocks.LABORATORY_BLOCK.get(), dark)), has(palette(ModBlocks.LABORATORY_BLOCK.get(), dark)))
                .save(output);

        if (!dark) {
            net.minecraft.data.ShapedRecipeBuilder.shaped(ModBlocks.GRAY_LABORATORY_TILES.get(), 8)
                    .pattern("AAA")
                    .pattern("ABA")
                    .pattern("AAA")
                    .define('A', ModBlocks.LABORATORY_TILES.get())
                    .define('B', Items.GRAY_DYE)
                    .unlockedBy(getHasName(ModBlocks.LABORATORY_TILES.get()), has(ModBlocks.LABORATORY_TILES.get()))
                    .unlockedBy("has_gray_dye", has(Items.GRAY_DYE))
                    .save(output);
        }

        if (!dark) {
            net.minecraft.data.ShapelessRecipeBuilder.shapeless(ModBlocks.MIXED_LABORATORY_TILES.get(), 2)
                    .requires(ModBlocks.GRAY_LABORATORY_TILES.get())
                    .requires(ModBlocks.LABORATORY_TILES.get())
                    .unlockedBy(getHasName(ModBlocks.GRAY_LABORATORY_TILES.get()), has(ModBlocks.GRAY_LABORATORY_TILES.get()))
                    .save(output);
        }

        net.minecraft.data.ShapedRecipeBuilder.shaped(palette(ModBlocks.LABORATORY_VENT.get(), dark))
                .pattern(" B ")
                .pattern("BAB")
                .pattern(" B ")
                .define('A', palette(ModBlocks.LABORATORY_BLOCK.get(), dark))
                .define('B', Items.IRON_NUGGET)
                .unlockedBy(getHasName(palette(ModBlocks.LABORATORY_BLOCK.get(), dark)), has(palette(ModBlocks.LABORATORY_BLOCK.get(), dark)))
                .unlockedBy(getHasName(Items.IRON_NUGGET), has(Items.IRON_NUGGET))
                .save(output);

        net.minecraft.data.ShapedRecipeBuilder.shaped(palette(ModBlocks.LABORATORY_PILLAR.get(), dark), 2)
                .pattern("A")
                .pattern("A")
                .define('A', palette(ModBlocks.LABORATORY_BLOCK.get(), dark))
                .unlockedBy(getHasName(palette(ModBlocks.LABORATORY_BLOCK.get(), dark)), has(palette(ModBlocks.LABORATORY_BLOCK.get(), dark)))
                .save(output);

        net.minecraft.data.ShapedRecipeBuilder.shaped(palette(ModBlocks.LABORATORY_GLASS.get(), dark), 8)
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', Items.GLASS)
                .define('B', palette(ModBlocks.LABORATORY_BLOCK.get(), dark))
                .unlockedBy(getHasName(Items.GLASS), has(Items.GLASS))
                .unlockedBy(getHasName(palette(ModBlocks.LABORATORY_BLOCK.get(), dark)), has(palette(ModBlocks.LABORATORY_BLOCK.get(), dark)))
                .save(output);

        net.minecraft.data.ShapedRecipeBuilder.shaped(palette(ModBlocks.LABORATORY_FAN.get(), dark))
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

        net.minecraft.data.ShapelessRecipeBuilder.shapeless(palette(ModBlocks.LABORATORY_FAN_REDSTONE_CONTROLLED.get(), dark))
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
        net.minecraft.data.ShapedRecipeBuilder.shaped(family.floorPair().base().get(), 8)
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', base)
                .define('B', family.planks())
                .unlockedBy(getHasName(base), has(base))
                .unlockedBy(getHasName(family.planks()), has(family.planks()))
                .save(output);

        net.minecraft.data.ShapedRecipeBuilder.shaped(family.tilePair().base().get(), 8)
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
        net.minecraft.data.ShapelessRecipeBuilder.shapeless(glowing)
                .requires(base)
                .requires(ModItems.GLOWSTONE_PARTICLES.get())
                .unlockedBy(getHasName(base), has(base))
                .save(output);
    }

    private void addScreenRecipes(boolean dark) {
        net.minecraft.data.ShapedRecipeBuilder.shaped(palette(ModBlocks.CLEAR_LABORATORY_SCREEN.get(), dark), 4)
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

        net.minecraft.data.ShapelessRecipeBuilder.shapeless(palette(ModBlocks.WAVE_LABORATORY_SCREEN.get(), dark), 4)
                .requires(palette(ModBlocks.CLEAR_LABORATORY_SCREEN.get(), dark), 4)
                .requires(Items.CYAN_DYE)
                .unlockedBy(getHasName(palette(ModBlocks.CLEAR_LABORATORY_SCREEN.get(), dark)), has(palette(ModBlocks.CLEAR_LABORATORY_SCREEN.get(), dark)))
                .unlockedBy("has_cyan_dye", has(Items.CYAN_DYE))
                .save(output);

        net.minecraft.data.ShapelessRecipeBuilder.shapeless(palette(ModBlocks.TEXT_LABORATORY_SCREEN.get(), dark), 4)
                .requires(palette(ModBlocks.CLEAR_LABORATORY_SCREEN.get(), dark), 4)
                .requires(Items.LIME_DYE)
                .unlockedBy(getHasName(palette(ModBlocks.CLEAR_LABORATORY_SCREEN.get(), dark)), has(palette(ModBlocks.CLEAR_LABORATORY_SCREEN.get(), dark)))
                .unlockedBy("has_lime_dye", has(Items.LIME_DYE))
                .save(output);

        net.minecraft.data.ShapelessRecipeBuilder.shapeless(palette(ModBlocks.QUANTUM_LABORATORY_SCREEN.get(), dark), 4)
                .requires(palette(ModBlocks.CLEAR_LABORATORY_SCREEN.get(), dark), 4)
                .requires(Items.PURPLE_DYE)
                .unlockedBy(getHasName(palette(ModBlocks.CLEAR_LABORATORY_SCREEN.get(), dark)), has(palette(ModBlocks.CLEAR_LABORATORY_SCREEN.get(), dark)))
                .unlockedBy("has_purple_dye", has(Items.PURPLE_DYE))
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
        net.minecraft.data.ShapedRecipeBuilder.shaped(rightBlock, 8)
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
        net.minecraft.data.ShapelessRecipeBuilder.shapeless(leftBlock)
                .requires(rightBlock)
                .unlockedBy(getHasName(rightBlock), has(rightBlock))
                .save(output);
        net.minecraft.data.ShapelessRecipeBuilder.shapeless(rightBlock)
                .requires(leftBlock)
                .unlockedBy(getHasName(leftBlock), has(leftBlock))
                .save(output, modLoc(Registry.BLOCK.getKey(rightBlock).getPath() + "_from_left"));
    }

    private void addDoorAndTrapdoorRecipes(boolean dark) {
        net.minecraft.data.ShapedRecipeBuilder.shaped(palette(ModBlocks.LABORATORY_DOOR.get(), dark), 3)
                .pattern("AA ")
                .pattern("AA ")
                .pattern("AA ")
                .define('A', palette(ModBlocks.LABORATORY_BLOCK.get(), dark))
                .unlockedBy(getHasName(palette(ModBlocks.LABORATORY_BLOCK.get(), dark)), has(palette(ModBlocks.LABORATORY_BLOCK.get(), dark)))
                .save(output);

        net.minecraft.data.ShapedRecipeBuilder.shaped(palette(ModBlocks.LABORATORY_TRAPDOOR.get(), dark), 2)
                .pattern("   ")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', palette(ModBlocks.LABORATORY_BLOCK.get(), dark))
                .unlockedBy(getHasName(palette(ModBlocks.LABORATORY_BLOCK.get(), dark)), has(palette(ModBlocks.LABORATORY_BLOCK.get(), dark)))
                .save(output);

        net.minecraft.data.ShapedRecipeBuilder.shaped(palette(ModBlocks.MESH_LABORATORY_DOOR.get(), dark), 3)
                .pattern("AA ")
                .pattern("BB ")
                .pattern("AA ")
                .define('A', palette(ModBlocks.LABORATORY_BLOCK.get(), dark))
                .define('B', Items.IRON_BARS)
                .unlockedBy(getHasName(palette(ModBlocks.LABORATORY_BLOCK.get(), dark)), has(palette(ModBlocks.LABORATORY_BLOCK.get(), dark)))
                .unlockedBy(getHasName(Items.IRON_BARS), has(Items.IRON_BARS))
                .save(output);

        net.minecraft.data.ShapedRecipeBuilder.shaped(palette(ModBlocks.MESH_LABORATORY_TRAPDOOR.get(), dark), 2)
                .pattern("   ")
                .pattern("ABA")
                .pattern("ABA")
                .define('A', palette(ModBlocks.LABORATORY_BLOCK.get(), dark))
                .define('B', Items.IRON_BARS)
                .unlockedBy(getHasName(palette(ModBlocks.LABORATORY_BLOCK.get(), dark)), has(palette(ModBlocks.LABORATORY_BLOCK.get(), dark)))
                .unlockedBy(getHasName(Items.IRON_BARS), has(Items.IRON_BARS))
                .save(output);

        net.minecraft.data.ShapedRecipeBuilder.shaped(palette(ModBlocks.GLASS_LABORATORY_DOOR.get(), dark), 3)
                .pattern("AA ")
                .pattern("BB ")
                .pattern("AA ")
                .define('A', palette(ModBlocks.LABORATORY_BLOCK.get(), dark))
                .define('B', Items.GLASS_PANE)
                .unlockedBy(getHasName(palette(ModBlocks.LABORATORY_BLOCK.get(), dark)), has(palette(ModBlocks.LABORATORY_BLOCK.get(), dark)))
                .unlockedBy(getHasName(Items.GLASS_PANE), has(Items.GLASS_PANE))
                .save(output);

        net.minecraft.data.ShapedRecipeBuilder.shaped(palette(ModBlocks.GLASS_LABORATORY_TRAPDOOR.get(), dark), 2)
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
        ResourceLocation inputId = Registry.BLOCK.getKey(input);
        ResourceLocation outputId = Registry.BLOCK.getKey(outputBlock);

        SingleItemRecipeBuilder.stonecutting(Ingredient.of(input), outputBlock, count)
                .unlocks(getHasName(input), has(input))
                .save(output, modLoc(outputId.getPath() + "_from_" + inputId.getPath() + "_stonecutting"));
    }

    private static ResourceLocation modLoc(String path) {
        return new ResourceLocation("laboratoryblocks", path);
    }

    private Ingredient woolIngredient(DyeColor color) {
        return Ingredient.of(Registry.ITEM.get(new ResourceLocation(color.getName() + "_wool")));
    }
    private static String getHasName(IItemProvider item) { return "has_" + item.asItem().getRegistryName().getPath(); }
}