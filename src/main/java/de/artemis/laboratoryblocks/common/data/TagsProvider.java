package de.artemis.laboratoryblocks.common.data;

import de.artemis.laboratoryblocks.LaboratoryBlocks;
import de.artemis.laboratoryblocks.common.registration.ModBlocks;
import de.artemis.laboratoryblocks.common.registration.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

public class TagsProvider {
    public static class BlockTagsProvider extends net.minecraft.data.tags.TagsProvider<Block> {
        private final PackOutput packOutput;

        protected BlockTagsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> future, ExistingFileHelper existingFileHelper) {
            super(packOutput, Registries.BLOCK, future, LaboratoryBlocks.MOD_ID, existingFileHelper);
            this.packOutput = packOutput;
        }

        @Override
        protected void addTags(@NotNull HolderLookup.Provider provider) {
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(getKey(ModBlocks.LABORATORY_FAN_REDSTONE_CONTROLLED.get()), getKey(ModBlocks.ENLIGHTED_LABORATORY_FAN_REDSTONE_CONTROLLED.get()), getKey(ModBlocks.LABORATORY_BLOCK.get()), getKey(ModBlocks.ENLIGHTED_LABORATORY_BLOCK.get()), getKey(ModBlocks.LABORATORY_TILES.get()), getKey(ModBlocks.ENLIGHTED_LABORATORY_TILES.get()), getKey(ModBlocks.GRAY_LABORATORY_TILES.get()), getKey(ModBlocks.ENLIGHTED_GRAY_LABORATORY_TILES.get()), getKey(ModBlocks.MIXED_LABORATORY_TILES.get()), getKey(ModBlocks.ENLIGHTED_MIXED_LABORATORY_TILES.get()), getKey(ModBlocks.LABORATORY_VENT.get()), getKey(ModBlocks.ENLIGHTED_LABORATORY_VENT.get()), getKey(ModBlocks.LEFT_FACED_BLUE_SIGNALING_LABORATORY_BLOCK.get()), getKey(ModBlocks.ENLIGHTED_LEFT_FACED_BLUE_SIGNALING_LABORATORY_BLOCK.get()), getKey(ModBlocks.RIGHT_FACED_BLUE_SIGNALING_LABORATORY_BLOCK.get()), getKey(ModBlocks.ENLIGHTED_RIGHT_FACED_BLUE_SIGNALING_LABORATORY_BLOCK.get()), getKey(ModBlocks.LEFT_FACED_RED_SIGNALING_LABORATORY_BLOCK.get()), getKey(ModBlocks.ENLIGHTED_LEFT_FACED_RED_SIGNALING_LABORATORY_BLOCK.get()), getKey(ModBlocks.RIGHT_FACED_RED_SIGNALING_LABORATORY_BLOCK.get()), getKey(ModBlocks.ENLIGHTED_RIGHT_FACED_RED_SIGNALING_LABORATORY_BLOCK.get()), getKey(ModBlocks.LEFT_FACED_GREEN_SIGNALING_LABORATORY_BLOCK.get()), getKey(ModBlocks.ENLIGHTED_LEFT_FACED_GREEN_SIGNALING_LABORATORY_BLOCK.get()), getKey(ModBlocks.RIGHT_FACED_GREEN_SIGNALING_LABORATORY_BLOCK.get()), getKey(ModBlocks.ENLIGHTED_RIGHT_FACED_GREEN_SIGNALING_LABORATORY_BLOCK.get()), getKey(ModBlocks.LABORATORY_FAN.get()), getKey(ModBlocks.ENLIGHTED_LABORATORY_FAN.get()), getKey(ModBlocks.SCREWED_LABORATORY_BLOCK.get()), getKey(ModBlocks.ENLIGHTED_SCREWED_LABORATORY_BLOCK.get()), getKey(ModBlocks.CLEAR_LABORATORY_SCREEN.get()), getKey(ModBlocks.ENLIGHTED_CLEAR_LABORATORY_SCREEN.get()), getKey(ModBlocks.LABORATORY_PILLAR.get()), getKey(ModBlocks.ENLIGHTED_LABORATORY_PILLAR.get()), getKey(ModBlocks.GRAY_LABORATORY_PILLAR.get()), getKey(ModBlocks.ENLIGHTED_GRAY_LABORATORY_PILLAR.get()));
            tag(BlockTags.MINEABLE_WITH_AXE).add(getKey(ModBlocks.OAK_LABORATORY_FLOOR.get()), getKey(ModBlocks.ENLIGHTED_OAK_LABORATORY_FLOOR.get()), getKey(ModBlocks.SPRUCE_LABORATORY_FLOOR.get()), getKey(ModBlocks.ENLIGHTED_SPRUCE_LABORATORY_FLOOR.get()), getKey(ModBlocks.BIRCH_LABORATORY_FLOOR.get()), getKey(ModBlocks.ENLIGHTED_BIRCH_LABORATORY_FLOOR.get()), getKey(ModBlocks.DARK_OAK_LABORATORY_FLOOR.get()), getKey(ModBlocks.ENLIGHTED_DARK_OAK_LABORATORY_FLOOR.get()), getKey(ModBlocks.ACACIA_LABORATORY_FLOOR.get()), getKey(ModBlocks.ENLIGHTED_ACACIA_LABORATORY_FLOOR.get()), getKey(ModBlocks.JUNGLE_LABORATORY_FLOOR.get()), getKey(ModBlocks.ENLIGHTED_JUNGLE_LABORATORY_FLOOR.get()), getKey(ModBlocks.MANGROVE_LABORATORY_FLOOR.get()), getKey(ModBlocks.ENLIGHTED_MANGROVE_LABORATORY_FLOOR.get()), getKey(ModBlocks.CRIMSON_LABORATORY_FLOOR.get()), getKey(ModBlocks.ENLIGHTED_CRIMSON_LABORATORY_FLOOR.get()), getKey(ModBlocks.WARPED_LABORATORY_FLOOR.get()), getKey(ModBlocks.ENLIGHTED_WARPED_LABORATORY_FLOOR.get()), getKey(ModBlocks.OAK_LABORATORY_TILES.get()), getKey(ModBlocks.ENLIGHTED_OAK_LABORATORY_TILES.get()), getKey(ModBlocks.SPRUCE_LABORATORY_TILES.get()), getKey(ModBlocks.ENLIGHTED_SPRUCE_LABORATORY_TILES.get()), getKey(ModBlocks.BIRCH_LABORATORY_TILES.get()), getKey(ModBlocks.ENLIGHTED_BIRCH_LABORATORY_TILES.get()), getKey(ModBlocks.DARK_OAK_LABORATORY_TILES.get()), getKey(ModBlocks.ENLIGHTED_DARK_OAK_LABORATORY_TILES.get()), getKey(ModBlocks.ACACIA_LABORATORY_TILES.get()), getKey(ModBlocks.ENLIGHTED_ACACIA_LABORATORY_TILES.get()), getKey(ModBlocks.JUNGLE_LABORATORY_TILES.get()), getKey(ModBlocks.ENLIGHTED_JUNGLE_LABORATORY_TILES.get()), getKey(ModBlocks.MANGROVE_LABORATORY_TILES.get()), getKey(ModBlocks.ENLIGHTED_MANGROVE_LABORATORY_TILES.get()), getKey(ModBlocks.CRIMSON_LABORATORY_TILES.get()), getKey(ModBlocks.ENLIGHTED_CRIMSON_LABORATORY_TILES.get()), getKey(ModBlocks.WARPED_LABORATORY_TILES.get()), getKey(ModBlocks.ENLIGHTED_WARPED_LABORATORY_TILES.get()), getKey(ModBlocks.CHERRY_LABORATORY_FLOOR.get()), getKey(ModBlocks.ENLIGHTED_CHERRY_LABORATORY_FLOOR.get()), getKey(ModBlocks.CHERRY_LABORATORY_TILES.get()), getKey(ModBlocks.ENLIGHTED_CHERRY_LABORATORY_TILES.get()), getKey(ModBlocks.BAMBOO_LABORATORY_FLOOR.get()), getKey(ModBlocks.ENLIGHTED_BAMBOO_LABORATORY_FLOOR.get()), getKey(ModBlocks.BAMBOO_LABORATORY_TILES.get()), getKey(ModBlocks.ENLIGHTED_BAMBOO_LABORATORY_TILES.get()));
        }

        private ResourceKey<Block> getKey(Block block) {
            return ForgeRegistries.BLOCKS.getResourceKey(block).get();
        }

        @NotNull
        @Override
        protected Path getPath(ResourceLocation location) {
            return this.packOutput.getOutputFolder().resolve("data/" + location.getNamespace() + "/tags/blocks/" + location.getPath() + ".json");
        }

        @NotNull
        @Override
        public String getName() {
            return "Block tags";
        }
    }

    public static class ItemTagsProvider extends net.minecraft.data.tags.TagsProvider<Item> {
        private final PackOutput packOutput;

        protected ItemTagsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> future, ExistingFileHelper existingFileHelper) {
            super(packOutput, Registries.ITEM, future, LaboratoryBlocks.MOD_ID, existingFileHelper);
            this.packOutput = packOutput;
        }

        @Override
        protected void addTags(@NotNull HolderLookup.Provider provider) {
            tag(ModTags.Item.STARCH_INGREDIENT).add(getKey(Items.SUGAR_CANE), getKey(Items.BEETROOT), getKey(Items.SUGAR), getKey(Items.WHEAT));
        }

        private ResourceKey<Item> getKey(Item item) {
            return ForgeRegistries.ITEMS.getResourceKey(item).get();
        }

        @NotNull
        @Override
        protected Path getPath(ResourceLocation location) {
            return this.packOutput.getOutputFolder().resolve("data/" + location.getNamespace() + "/tags/items/" + location.getPath() + ".json");
        }

        @NotNull
        @Override
        public String getName() {
            return "Item tags";
        }
    }
}
