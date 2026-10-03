package de.artemis.laboratoryblocks.common.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        var pickaxeTag = tag(BlockTags.MINEABLE_WITH_PICKAXE);
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

        var doorsTag = tag(BlockTags.DOORS);
        ModDatagenEntries.DOORS.forEach(door -> doorsTag.add(key(door.get())));

        var trapdoorsTag = tag(BlockTags.TRAPDOORS);
        ModDatagenEntries.TRAPDOORS.forEach(trapdoor -> trapdoorsTag.add(key(trapdoor.get())));

        var axeTag = tag(BlockTags.MINEABLE_WITH_AXE);
        ModDatagenEntries.WOOD_FAMILIES.forEach(family -> family.pairs().forEach(pair -> {
            axeTag.add(key(pair.base().get()));
            axeTag.add(key(pair.glowing().get()));
        }));
    }

    private static ResourceKey<Block> key(Block block) {
        return ResourceKey.create(Registries.BLOCK, BuiltInRegistries.BLOCK.getKey(block));
    }
}
