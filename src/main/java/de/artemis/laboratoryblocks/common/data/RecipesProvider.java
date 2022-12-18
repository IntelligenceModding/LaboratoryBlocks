package de.artemis.laboratoryblocks.common.data;

import de.artemis.laboratoryblocks.common.registration.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class RecipesProvider extends RecipeProvider implements IConditionBuilder {
    public RecipesProvider(DataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void buildCraftingRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(ModBlocks.LABORATORY_BLOCK.get(), 8).define('A', Blocks.STONE).define('B', Items.QUARTZ).pattern("AAA").pattern("ABA").pattern("AAA").unlockedBy("has_stone", has(Blocks.STONE)).unlockedBy("has_quartz", has(Items.QUARTZ)).save(consumer);
        ShapelessRecipeBuilder.shapeless(ModBlocks.ENLIGHTED_LABORATORY_BLOCK.get(), 1).requires(ModBlocks.LABORATORY_BLOCK.get()).requires(Items.GLOWSTONE_DUST).unlockedBy("has_laboratory_block", has(ModBlocks.LABORATORY_BLOCK.get())).unlockedBy("has_glowstone_dust", has(Items.GLOWSTONE_DUST)).save(consumer);
        ShapedRecipeBuilder.shaped(ModBlocks.OAK_LABORATORY_FLOOR.get(), 8).define('A', ModBlocks.LABORATORY_BLOCK.get()).define('B', Blocks.OAK_PLANKS).pattern("AAA").pattern("ABA").pattern("AAA").unlockedBy("has_laboratory_block", has(ModBlocks.LABORATORY_BLOCK.get())).unlockedBy("has_oak_planks", has(Blocks.OAK_PLANKS)).save(consumer);
        ShapelessRecipeBuilder.shapeless(ModBlocks.ENLIGHTED_OAK_LABORATORY_FLOOR.get(), 1).requires(ModBlocks.OAK_LABORATORY_FLOOR.get()).requires(Items.GLOWSTONE_DUST).unlockedBy("has_oak_laboratory_floor", has(ModBlocks.OAK_LABORATORY_FLOOR.get())).unlockedBy("has_glowstone_dust", has(Items.GLOWSTONE_DUST)).save(consumer);
        ShapedRecipeBuilder.shaped(ModBlocks.SPRUCE_LABORATORY_FLOOR.get(), 8).define('A', ModBlocks.LABORATORY_BLOCK.get()).define('B', Blocks.SPRUCE_PLANKS).pattern("AAA").pattern("ABA").pattern("AAA").unlockedBy("has_laboratory_block", has(ModBlocks.LABORATORY_BLOCK.get())).unlockedBy("has_spruce_planks", has(Blocks.SPRUCE_PLANKS)).save(consumer);
        ShapelessRecipeBuilder.shapeless(ModBlocks.ENLIGHTED_SPRUCE_LABORATORY_FLOOR.get(), 1).requires(ModBlocks.SPRUCE_LABORATORY_FLOOR.get()).requires(Items.GLOWSTONE_DUST).unlockedBy("has_spruce_laboratory_floor", has(ModBlocks.SPRUCE_LABORATORY_FLOOR.get())).unlockedBy("has_glowstone_dust", has(Items.GLOWSTONE_DUST)).save(consumer);
        ShapedRecipeBuilder.shaped(ModBlocks.BIRCH_LABORATORY_FLOOR.get(), 8).define('A', ModBlocks.LABORATORY_BLOCK.get()).define('B', Blocks.BIRCH_PLANKS).pattern("AAA").pattern("ABA").pattern("AAA").unlockedBy("has_laboratory_block", has(ModBlocks.LABORATORY_BLOCK.get())).unlockedBy("has_birch_planks", has(Blocks.BIRCH_PLANKS)).save(consumer);
        ShapelessRecipeBuilder.shapeless(ModBlocks.ENLIGHTED_BIRCH_LABORATORY_FLOOR.get(), 1).requires(ModBlocks.BIRCH_LABORATORY_FLOOR.get()).requires(Items.GLOWSTONE_DUST).unlockedBy("has_birch_laboratory_floor", has(ModBlocks.BIRCH_LABORATORY_FLOOR.get())).unlockedBy("has_glowstone_dust", has(Items.GLOWSTONE_DUST)).save(consumer);
        ShapedRecipeBuilder.shaped(ModBlocks.DARK_OAK_LABORATORY_FLOOR.get(), 8).define('A', ModBlocks.LABORATORY_BLOCK.get()).define('B', Blocks.DARK_OAK_PLANKS).pattern("AAA").pattern("ABA").pattern("AAA").unlockedBy("has_laboratory_block", has(ModBlocks.LABORATORY_BLOCK.get())).unlockedBy("has_dark_oak_planks", has(Blocks.DARK_OAK_PLANKS)).save(consumer);
        ShapelessRecipeBuilder.shapeless(ModBlocks.ENLIGHTED_DARK_OAK_LABORATORY_FLOOR.get(), 1).requires(ModBlocks.DARK_OAK_LABORATORY_FLOOR.get()).requires(Items.GLOWSTONE_DUST).unlockedBy("has_dark_oak_laboratory_floor", has(ModBlocks.DARK_OAK_LABORATORY_FLOOR.get())).unlockedBy("has_glowstone_dust", has(Items.GLOWSTONE_DUST)).save(consumer);
        ShapedRecipeBuilder.shaped(ModBlocks.ACACIA_LABORATORY_FLOOR.get(), 8).define('A', ModBlocks.LABORATORY_BLOCK.get()).define('B', Blocks.ACACIA_PLANKS).pattern("AAA").pattern("ABA").pattern("AAA").unlockedBy("has_laboratory_block", has(ModBlocks.LABORATORY_BLOCK.get())).unlockedBy("has_acacia_planks", has(Blocks.ACACIA_PLANKS)).save(consumer);
        ShapelessRecipeBuilder.shapeless(ModBlocks.ENLIGHTED_ACACIA_LABORATORY_FLOOR.get(), 1).requires(ModBlocks.ACACIA_LABORATORY_FLOOR.get()).requires(Items.GLOWSTONE_DUST).unlockedBy("has_acacia_laboratory_floor", has(ModBlocks.ACACIA_LABORATORY_FLOOR.get())).unlockedBy("has_glowstone_dust", has(Items.GLOWSTONE_DUST)).save(consumer);
        ShapedRecipeBuilder.shaped(ModBlocks.JUNGLE_LABORATORY_FLOOR.get(), 8).define('A', ModBlocks.LABORATORY_BLOCK.get()).define('B', Blocks.JUNGLE_PLANKS).pattern("AAA").pattern("ABA").pattern("AAA").unlockedBy("has_laboratory_block", has(ModBlocks.LABORATORY_BLOCK.get())).unlockedBy("has_jungle_planks", has(Blocks.JUNGLE_PLANKS)).save(consumer);
        ShapelessRecipeBuilder.shapeless(ModBlocks.ENLIGHTED_JUNGLE_LABORATORY_FLOOR.get(), 1).requires(ModBlocks.JUNGLE_LABORATORY_FLOOR.get()).requires(Items.GLOWSTONE_DUST).unlockedBy("has_jungle_laboratory_floor", has(ModBlocks.JUNGLE_LABORATORY_FLOOR.get())).unlockedBy("has_glowstone_dust", has(Items.GLOWSTONE_DUST)).save(consumer);
        ShapedRecipeBuilder.shaped(ModBlocks.MANGROVE_LABORATORY_FLOOR.get(), 8).define('A', ModBlocks.LABORATORY_BLOCK.get()).define('B', Blocks.MANGROVE_PLANKS).pattern("AAA").pattern("ABA").pattern("AAA").unlockedBy("has_laboratory_block", has(ModBlocks.LABORATORY_BLOCK.get())).unlockedBy("has_mangrove_planks", has(Blocks.MANGROVE_PLANKS)).save(consumer);
        ShapelessRecipeBuilder.shapeless(ModBlocks.ENLIGHTED_MANGROVE_LABORATORY_FLOOR.get(), 1).requires(ModBlocks.MANGROVE_LABORATORY_FLOOR.get()).requires(Items.GLOWSTONE_DUST).unlockedBy("has_mangrove_laboratory_floor", has(ModBlocks.MANGROVE_LABORATORY_FLOOR.get())).unlockedBy("has_glowstone_dust", has(Items.GLOWSTONE_DUST)).save(consumer);
        ShapedRecipeBuilder.shaped(ModBlocks.CRIMSON_LABORATORY_FLOOR.get(), 8).define('A', ModBlocks.LABORATORY_BLOCK.get()).define('B', Blocks.CRIMSON_PLANKS).pattern("AAA").pattern("ABA").pattern("AAA").unlockedBy("has_laboratory_block", has(ModBlocks.LABORATORY_BLOCK.get())).unlockedBy("has_crimson_planks", has(Blocks.CRIMSON_PLANKS)).save(consumer);
        ShapelessRecipeBuilder.shapeless(ModBlocks.ENLIGHTED_CRIMSON_LABORATORY_FLOOR.get(), 1).requires(ModBlocks.OAK_LABORATORY_FLOOR.get()).requires(Items.GLOWSTONE_DUST).unlockedBy("has_crimson_laboratory_floor", has(ModBlocks.CRIMSON_LABORATORY_FLOOR.get())).unlockedBy("has_glowstone_dust", has(Items.GLOWSTONE_DUST)).save(consumer);
        ShapedRecipeBuilder.shaped(ModBlocks.WARPED_LABORATORY_FLOOR.get(), 8).define('A', ModBlocks.LABORATORY_BLOCK.get()).define('B', Blocks.WARPED_PLANKS).pattern("AAA").pattern("ABA").pattern("AAA").unlockedBy("has_laboratory_block", has(ModBlocks.LABORATORY_BLOCK.get())).unlockedBy("has_warped_planks", has(Blocks.WARPED_PLANKS)).save(consumer);
        ShapelessRecipeBuilder.shapeless(ModBlocks.ENLIGHTED_WARPED_LABORATORY_FLOOR.get(), 1).requires(ModBlocks.WARPED_LABORATORY_FLOOR.get()).requires(Items.GLOWSTONE_DUST).unlockedBy("has_warped_laboratory_floor", has(ModBlocks.WARPED_LABORATORY_FLOOR.get())).unlockedBy("has_glowstone_dust", has(Items.GLOWSTONE_DUST)).save(consumer);

    }
}
