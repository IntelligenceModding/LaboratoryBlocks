package de.artemis.laboratoryblocks.common.datagen;

import de.artemis.laboratoryblocks.common.registry.ModBlocks;
import de.artemis.laboratoryblocks.common.registry.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
        super(provider, recipeOutput);
    }

    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> provider) {
            super(packOutput, provider);
        }

        @Override
        protected @NotNull RecipeProvider createRecipeProvider(HolderLookup.@NotNull Provider provider, @NotNull RecipeOutput recipeOutput) {
            return new ModRecipeProvider(provider, recipeOutput);
        }

        @Override
        public @NotNull String getName() {
            return "Laboratory Blocks Recipes";
        }
    }

    @Override
    protected void buildRecipes() {
        shaped(RecipeCategory.TOOLS, ModItems.CONFIGURATION_TOOL.get())
                .pattern("  C")
                .pattern("DB ")
                .pattern("AD ")
                .define('A', Items.IRON_INGOT)
                .define('B', Items.ORANGE_WOOL)
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

        shapeless(RecipeCategory.MISC, ModItems.REDSTONE_PARTICLES.get(), 8)
                .requires(Items.REDSTONE)
                .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE))
                .save(output);

        shapeless(RecipeCategory.MISC, Items.REDSTONE)
                .requires(ModItems.REDSTONE_PARTICLES.get(), 8)
                .unlockedBy(getHasName(ModItems.REDSTONE_PARTICLES.get()), has(ModItems.REDSTONE_PARTICLES.get()))
                .save(output, modLoc("redstone_from_redstone_particles"));

        shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LABORATORY_BLOCK.get(), 8)
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', Blocks.STONE)
                .define('B', Items.QUARTZ)
                .unlockedBy(getHasName(Blocks.STONE), has(Blocks.STONE))
                .save(output);

        shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.REINFORCED_LABORATORY_BLOCK.get(), 2)
                .pattern("A A")
                .pattern(" B ")
                .pattern("A A")
                .define('A', ModItems.IRON_SCREW.get())
                .define('B', ModBlocks.LABORATORY_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.LABORATORY_BLOCK.get()), has(ModBlocks.LABORATORY_BLOCK.get()))
                .save(output);

        shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LABORATORY_TILES.get(), 8)
                .pattern("AA")
                .pattern("AA")
                .define('A', ModBlocks.LABORATORY_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.LABORATORY_BLOCK.get()), has(ModBlocks.LABORATORY_BLOCK.get()))
                .save(output);

        shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.GRAY_LABORATORY_TILES.get(), 8)
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', ModBlocks.LABORATORY_TILES.get())
                .define('B', Items.GRAY_DYE)
                .unlockedBy(getHasName(ModBlocks.LABORATORY_TILES.get()), has(ModBlocks.LABORATORY_TILES.get()))
                .save(output);

        shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MIXED_LABORATORY_TILES.get(), 2)
                .requires(ModBlocks.GRAY_LABORATORY_TILES.get())
                .requires(ModBlocks.LABORATORY_TILES.get())
                .unlockedBy(getHasName(ModBlocks.GRAY_LABORATORY_TILES.get()), has(ModBlocks.GRAY_LABORATORY_TILES.get()))
                .save(output);

        shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LABORATORY_VENT.get())
                .pattern(" B ")
                .pattern("BAB")
                .pattern(" B ")
                .define('A', ModBlocks.LABORATORY_BLOCK.get())
                .define('B', Items.IRON_NUGGET)
                .unlockedBy(getHasName(ModBlocks.LABORATORY_BLOCK.get()), has(ModBlocks.LABORATORY_BLOCK.get()))
                .unlockedBy(getHasName(Items.IRON_NUGGET), has(Items.IRON_NUGGET))
                .save(output);

        shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LABORATORY_PILLAR.get(), 2)
                .pattern("A")
                .pattern("A")
                .define('A', ModBlocks.LABORATORY_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.LABORATORY_BLOCK.get()), has(ModBlocks.LABORATORY_BLOCK.get()))
                .save(output);

        shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.GRAY_LABORATORY_PILLAR.get(), 8)
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', ModBlocks.LABORATORY_PILLAR.get())
                .define('B', Items.GRAY_DYE)
                .unlockedBy(getHasName(ModBlocks.LABORATORY_PILLAR.get()), has(ModBlocks.LABORATORY_PILLAR.get()))
                .unlockedBy(getHasName(Items.GRAY_DYE), has(Items.GRAY_DYE))
                .save(output);

        shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LABORATORY_GLASS.get(), 8)
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', Items.GLASS)
                .define('B', ModBlocks.LABORATORY_BLOCK.get())
                .unlockedBy(getHasName(Items.GLASS), has(Items.GLASS))
                .unlockedBy(getHasName(ModBlocks.LABORATORY_BLOCK.get()), has(ModBlocks.LABORATORY_BLOCK.get()))
                .save(output);

        shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LABORATORY_FAN.get())
                .pattern("ABA")
                .pattern("BCB")
                .pattern("ABA")
                .define('A', ModBlocks.LABORATORY_BLOCK.get())
                .define('B', Items.IRON_NUGGET)
                .define('C', ModItems.IRON_SCREW.get())
                .unlockedBy(getHasName(ModBlocks.LABORATORY_BLOCK.get()), has(ModBlocks.LABORATORY_BLOCK.get()))
                .unlockedBy(getHasName(Items.IRON_NUGGET), has(Items.IRON_NUGGET))
                .unlockedBy(getHasName(ModItems.IRON_SCREW.get()), has(ModItems.IRON_SCREW.get()))
                .save(output);

        shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LABORATORY_FAN_REDSTONE_CONTROLLED.get())
                .requires(ModBlocks.LABORATORY_FAN.get())
                .requires(ModItems.REDSTONE_PARTICLES.get())
                .unlockedBy(getHasName(ModBlocks.LABORATORY_FAN.get()), has(ModBlocks.LABORATORY_FAN.get()))
                .unlockedBy(getHasName(ModItems.REDSTONE_PARTICLES.get()), has(ModItems.REDSTONE_PARTICLES.get()))
                .save(output);

        addScreenRecipes();
        addDoorAndTrapdoorRecipes();

        addIndicatingRecipes(
                ModBlocks.RIGHT_INDICATING_BLUE_LABORATORY_BLOCK.get(),
                ModBlocks.LEFT_INDICATING_BLUE_LABORATORY_BLOCK.get(),
                Items.BLUE_WOOL
        );
        addIndicatingRecipes(
                ModBlocks.RIGHT_INDICATING_RED_LABORATORY_BLOCK.get(),
                ModBlocks.LEFT_INDICATING_RED_LABORATORY_BLOCK.get(),
                Items.RED_WOOL
        );
        addIndicatingRecipes(
                ModBlocks.RIGHT_INDICATING_GREEN_LABORATORY_BLOCK.get(),
                ModBlocks.LEFT_INDICATING_GREEN_LABORATORY_BLOCK.get(),
                Items.GREEN_WOOL
        );

        ModDatagenEntries.WOOD_FAMILIES.forEach(this::addWoodRecipes);

        ModDatagenEntries.ALL_PAIRS.forEach(pair -> addGlowstoneUpgrade(pair.base().get(), pair.glowing().get()));
        ModDatagenEntries.PILLAR_PAIRS.forEach(pair -> addGlowstoneUpgrade(pair.base().get(), pair.glowing().get()));
        ModDatagenEntries.FAN_PAIRS.forEach(pair -> addGlowstoneUpgrade(pair.base().get(), pair.glowing().get()));
    }

    private void addWoodRecipes(ModDatagenEntries.WoodFamily family) {
        shaped(RecipeCategory.BUILDING_BLOCKS, family.floorPair().base().get(), 8)
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', ModBlocks.LABORATORY_BLOCK.get())
                .define('B', family.planks())
                .unlockedBy(getHasName(ModBlocks.LABORATORY_BLOCK.get()), has(ModBlocks.LABORATORY_BLOCK.get()))
                .unlockedBy(getHasName(family.planks()), has(family.planks()))
                .save(output);

        shaped(RecipeCategory.BUILDING_BLOCKS, family.tilePair().base().get(), 8)
                .pattern("AA")
                .pattern("AA")
                .define('A', family.floorPair().base().get())
                .unlockedBy(getHasName(family.floorPair().base().get()), has(family.floorPair().base().get()))
                .save(output);
    }

    private void addGlowstoneUpgrade(Block base, Block glowing) {
        shapeless(RecipeCategory.BUILDING_BLOCKS, glowing)
                .requires(base)
                .requires(ModItems.GLOWSTONE_PARTICLES.get())
                .unlockedBy(getHasName(base), has(base))
                .save(output);
    }

    private void addScreenRecipes() {
        shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CLEAR_LABORATORY_SCREEN.get(), 4)
                .pattern("BAB")
                .pattern("ACA")
                .pattern("BAB")
                .define('A', ModBlocks.LABORATORY_BLOCK.get())
                .define('B', ModItems.IRON_SCREW.get())
                .define('C', ModBlocks.LABORATORY_GLASS.get())
                .unlockedBy(getHasName(ModBlocks.LABORATORY_BLOCK.get()), has(ModBlocks.LABORATORY_BLOCK.get()))
                .unlockedBy(getHasName(ModItems.IRON_SCREW.get()), has(ModItems.IRON_SCREW.get()))
                .unlockedBy(getHasName(ModBlocks.LABORATORY_GLASS.get()), has(ModBlocks.LABORATORY_GLASS.get()))
                .save(output);

        shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.WAVE_LABORATORY_SCREEN.get(), 4)
                .requires(ModBlocks.CLEAR_LABORATORY_SCREEN.get(), 4)
                .requires(Items.CYAN_DYE)
                .unlockedBy(getHasName(ModBlocks.CLEAR_LABORATORY_SCREEN.get()), has(ModBlocks.CLEAR_LABORATORY_SCREEN.get()))
                .unlockedBy(getHasName(Items.CYAN_DYE), has(Items.CYAN_DYE))
                .save(output);

        shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.TEXT_LABORATORY_SCREEN.get(), 4)
                .requires(ModBlocks.CLEAR_LABORATORY_SCREEN.get(), 4)
                .requires(Items.LIME_DYE)
                .unlockedBy(getHasName(ModBlocks.CLEAR_LABORATORY_SCREEN.get()), has(ModBlocks.CLEAR_LABORATORY_SCREEN.get()))
                .unlockedBy(getHasName(Items.LIME_DYE), has(Items.LIME_DYE))
                .save(output);

        shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.QUANTUM_LABORATORY_SCREEN.get(), 4)
                .requires(ModBlocks.CLEAR_LABORATORY_SCREEN.get(), 4)
                .requires(Items.PURPLE_DYE)
                .unlockedBy(getHasName(ModBlocks.CLEAR_LABORATORY_SCREEN.get()), has(ModBlocks.CLEAR_LABORATORY_SCREEN.get()))
                .unlockedBy(getHasName(Items.PURPLE_DYE), has(Items.PURPLE_DYE))
                .save(output);
    }

    private void addIndicatingRecipes(Block rightBlock, Block leftBlock, net.minecraft.world.item.Item colorWool) {
        shaped(RecipeCategory.BUILDING_BLOCKS, rightBlock, 8)
                .pattern("AAA")
                .pattern("BCB")
                .pattern("AAA")
                .define('A', ModBlocks.LABORATORY_BLOCK.get())
                .define('B', colorWool)
                .define('C', Blocks.BLACK_WOOL)
                .unlockedBy(getHasName(ModBlocks.LABORATORY_BLOCK.get()), has(ModBlocks.LABORATORY_BLOCK.get()))
                .unlockedBy("has_wool", has(ItemTags.WOOL))
                .save(output);

        shapeless(RecipeCategory.BUILDING_BLOCKS, leftBlock)
                .requires(rightBlock)
                .unlockedBy(getHasName(rightBlock), has(rightBlock))
                .save(output);
    }

    private void addDoorAndTrapdoorRecipes() {
        shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LABORATORY_DOOR.get(), 3)
                .pattern("AA ")
                .pattern("AA ")
                .pattern("AA ")
                .define('A', ModBlocks.LABORATORY_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.LABORATORY_BLOCK.get()), has(ModBlocks.LABORATORY_BLOCK.get()))
                .save(output);

        shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LABORATORY_TRAPDOOR.get(), 2)
                .pattern("   ")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModBlocks.LABORATORY_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.LABORATORY_BLOCK.get()), has(ModBlocks.LABORATORY_BLOCK.get()))
                .save(output);

        shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MESH_LABORATORY_DOOR.get(), 3)
                .pattern("AA ")
                .pattern("BB ")
                .pattern("AA ")
                .define('A', ModBlocks.LABORATORY_BLOCK.get())
                .define('B', Items.IRON_BARS)
                .unlockedBy(getHasName(ModBlocks.LABORATORY_BLOCK.get()), has(ModBlocks.LABORATORY_BLOCK.get()))
                .unlockedBy(getHasName(Items.IRON_BARS), has(Items.IRON_BARS))
                .save(output);

        shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MESH_LABORATORY_TRAPDOOR.get(), 2)
                .pattern("   ")
                .pattern("ABA")
                .pattern("ABA")
                .define('A', ModBlocks.LABORATORY_BLOCK.get())
                .define('B', Items.IRON_BARS)
                .unlockedBy(getHasName(ModBlocks.LABORATORY_BLOCK.get()), has(ModBlocks.LABORATORY_BLOCK.get()))
                .unlockedBy(getHasName(Items.IRON_BARS), has(Items.IRON_BARS))
                .save(output);

        shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.GLASS_LABORATORY_DOOR.get(), 3)
                .pattern("AA ")
                .pattern("BB ")
                .pattern("AA ")
                .define('A', ModBlocks.LABORATORY_BLOCK.get())
                .define('B', Items.GLASS_PANE)
                .unlockedBy(getHasName(ModBlocks.LABORATORY_BLOCK.get()), has(ModBlocks.LABORATORY_BLOCK.get()))
                .unlockedBy(getHasName(Items.GLASS_PANE), has(Items.GLASS_PANE))
                .save(output);

        shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.GLASS_LABORATORY_TRAPDOOR.get(), 2)
                .pattern("   ")
                .pattern("ABA")
                .pattern("ABA")
                .define('A', ModBlocks.LABORATORY_BLOCK.get())
                .define('B', Items.GLASS_PANE)
                .unlockedBy(getHasName(ModBlocks.LABORATORY_BLOCK.get()), has(ModBlocks.LABORATORY_BLOCK.get()))
                .unlockedBy(getHasName(Items.GLASS_PANE), has(Items.GLASS_PANE))
                .save(output);
    }

    private static @NotNull ResourceKey<net.minecraft.world.item.crafting.Recipe<?>> modLoc(String path) {
        return ResourceKey.create(Registries.RECIPE, Identifier.fromNamespaceAndPath(ModBlocks.LABORATORY_BLOCK.getId().getNamespace(), path));
    }
}