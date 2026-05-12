package de.artemis.laboratoryblocks.common.datagen;

import de.artemis.laboratoryblocks.LaboratoryBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, LaboratoryBlocks.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        var pickaxeTag = tag(BlockTags.MINEABLE_WITH_PICKAXE);
        ModDatagenEntries.CORE_PAIRS.forEach(pair -> {
            pickaxeTag.add(pair.base().get());
            pickaxeTag.add(pair.glowing().get());
        });
        ModDatagenEntries.PILLAR_PAIRS.forEach(pair -> {
            pickaxeTag.add(pair.base().get());
            pickaxeTag.add(pair.glowing().get());
        });
        ModDatagenEntries.FAN_PAIRS.forEach(pair -> {
            pickaxeTag.add(pair.base().get());
            pickaxeTag.add(pair.glowing().get());
        });
        ModDatagenEntries.DOORS.forEach(door -> pickaxeTag.add(door.get()));
        ModDatagenEntries.TRAPDOORS.forEach(trapdoor -> pickaxeTag.add(trapdoor.get()));

        var doorsTag = tag(BlockTags.DOORS);
        ModDatagenEntries.DOORS.forEach(door -> doorsTag.add(door.get()));

        var trapdoorsTag = tag(BlockTags.TRAPDOORS);
        ModDatagenEntries.TRAPDOORS.forEach(trapdoor -> trapdoorsTag.add(trapdoor.get()));

        var axeTag = tag(BlockTags.MINEABLE_WITH_AXE);
        ModDatagenEntries.WOOD_FAMILIES.forEach(family -> family.pairs().forEach(pair -> {
            axeTag.add(pair.base().get());
            axeTag.add(pair.glowing().get());
        }));
    }
}