package de.artemis.laboratoryblocks.common.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.tags.BlockTags;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void generateTags() {
        var pickaxeTag = getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_PICKAXE);
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

        var doorsTag = getOrCreateTagBuilder(BlockTags.DOORS);
        ModDatagenEntries.DOORS.forEach(door -> doorsTag.add(door.get()));

        var trapdoorsTag = getOrCreateTagBuilder(BlockTags.TRAPDOORS);
        ModDatagenEntries.TRAPDOORS.forEach(trapdoor -> trapdoorsTag.add(trapdoor.get()));

        var axeTag = getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE);
        ModDatagenEntries.WOOD_FAMILIES.forEach(family -> family.pairs().forEach(pair -> {
            axeTag.add(pair.base().get());
            axeTag.add(pair.glowing().get());
        }));
    }
}
