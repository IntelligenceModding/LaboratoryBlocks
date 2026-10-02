package de.artemis.laboratoryblocks.common.datagen;

import de.artemis.laboratoryblocks.LaboratoryBlocks;
import net.minecraft.block.Block;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.TagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, LaboratoryBlocks.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        TagsProvider.Builder<Block> doorsTag = tag(BlockTags.DOORS);
        ModDatagenEntries.DOORS.forEach(door -> doorsTag.add(door.get()));

        TagsProvider.Builder<Block> trapdoorsTag = tag(BlockTags.TRAPDOORS);
        ModDatagenEntries.TRAPDOORS.forEach(trapdoor -> trapdoorsTag.add(trapdoor.get()));
    }
}
