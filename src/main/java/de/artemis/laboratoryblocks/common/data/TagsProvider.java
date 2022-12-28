package de.artemis.laboratoryblocks.common.data;

import de.artemis.laboratoryblocks.LaboratoryBlocks;
import de.artemis.laboratoryblocks.common.registration.ModBlocks;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

public class TagsProvider {
    public static class BlockTagsProvider extends net.minecraft.data.tags.TagsProvider<Block> {
        private DataGenerator generator;

        @SuppressWarnings("deprecation")
        protected BlockTagsProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
            super(generator, Registry.BLOCK, LaboratoryBlocks.MOD_ID, existingFileHelper);
            this.generator = generator;
        }

        @Override
        protected void addTags() {
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.LABORATORY_BLOCK.get(), ModBlocks.ENLIGHTED_LABORATORY_BLOCK.get(), ModBlocks.OAK_LABORATORY_FLOOR.get(), ModBlocks.ENLIGHTED_OAK_LABORATORY_FLOOR.get(), ModBlocks.SPRUCE_LABORATORY_FLOOR.get(), ModBlocks.ENLIGHTED_SPRUCE_LABORATORY_FLOOR.get(), ModBlocks.BIRCH_LABORATORY_FLOOR.get(), ModBlocks.ENLIGHTED_BIRCH_LABORATORY_FLOOR.get(), ModBlocks.DARK_OAK_LABORATORY_FLOOR.get(), ModBlocks.ENLIGHTED_DARK_OAK_LABORATORY_FLOOR.get(), ModBlocks.ACACIA_LABORATORY_FLOOR.get(), ModBlocks.ENLIGHTED_ACACIA_LABORATORY_FLOOR.get(), ModBlocks.JUNGLE_LABORATORY_FLOOR.get(), ModBlocks.ENLIGHTED_JUNGLE_LABORATORY_FLOOR.get(), ModBlocks.MANGROVE_LABORATORY_FLOOR.get(), ModBlocks.ENLIGHTED_MANGROVE_LABORATORY_FLOOR.get(), ModBlocks.CRIMSON_LABORATORY_FLOOR.get(), ModBlocks.ENLIGHTED_CRIMSON_LABORATORY_FLOOR.get(), ModBlocks.WARPED_LABORATORY_FLOOR.get(), ModBlocks.ENLIGHTED_WARPED_LABORATORY_FLOOR.get());
        }

        @NotNull
        @Override
        protected Path getPath(ResourceLocation location) {
            return this.generator.getOutputFolder().resolve("data/" + location.getNamespace() + "/tags/blocks/" + location.getPath() + ".json");
        }

        @NotNull
        @Override
        public String getName() {
            return "Block tags";
        }
    }
}
