package de.artemis.laboratoryblocks.common.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagsProvider.BlockTagsProvider {
    public ModBlockTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        var pickaxeTag = builder(BlockTags.MINEABLE_WITH_PICKAXE);
        ModDatagenEntries.CORE_PAIRS.forEach(pair -> {
            pickaxeTag.add(key(pair.base().get()));
            pickaxeTag.add(key(pair.glowing().get()));
        });
        ModDatagenEntries.PILLAR_PAIRS.forEach(pair -> {
            pickaxeTag.add(key(pair.base().get()));
            pickaxeTag.add(key(pair.glowing().get()));
        });
        ModDatagenEntries.FAN_PAIRS.forEach(pair -> {
            pickaxeTag.add(key(pair.base().get()));
            pickaxeTag.add(key(pair.glowing().get()));
        });
        ModDatagenEntries.DOORS.forEach(door -> pickaxeTag.add(key(door.get())));
        ModDatagenEntries.TRAPDOORS.forEach(trapdoor -> pickaxeTag.add(key(trapdoor.get())));

        var doorsTag = builder(BlockTags.DOORS);
        ModDatagenEntries.DOORS.forEach(door -> doorsTag.add(key(door.get())));

        var trapdoorsTag = builder(BlockTags.TRAPDOORS);
        ModDatagenEntries.TRAPDOORS.forEach(trapdoor -> trapdoorsTag.add(key(trapdoor.get())));

        var axeTag = builder(BlockTags.MINEABLE_WITH_AXE);
        ModDatagenEntries.WOOD_FAMILIES.forEach(family -> family.pairs().forEach(pair -> {
            axeTag.add(key(pair.base().get()));
            axeTag.add(key(pair.glowing().get()));
        }));
    }

    private static ResourceKey<Block> key(Block block) {
        return ResourceKey.create(Registries.BLOCK, BuiltInRegistries.BLOCK.getKey(block));
    }
}
