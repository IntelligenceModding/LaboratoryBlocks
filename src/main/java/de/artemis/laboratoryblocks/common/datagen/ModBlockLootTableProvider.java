package de.artemis.laboratoryblocks.common.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.world.level.block.Block;

import java.util.Set;
import java.util.stream.Stream;

public class ModBlockLootTableProvider extends FabricBlockLootTableProvider {
    private static final Set<Block> GENERATED_BLOCKS = ModDatagenEntries.ALL_PAIRS.stream()
            .flatMap(pair -> Stream.of(pair.base().get(), pair.glowing().get()))
            .collect(java.util.stream.Collectors.toUnmodifiableSet());
    private static final Set<Block> GENERATED_PILLARS = ModDatagenEntries.PILLAR_PAIRS.stream()
            .flatMap(pair -> Stream.of(pair.base().get(), pair.glowing().get()))
            .collect(java.util.stream.Collectors.toUnmodifiableSet());
    private static final Set<Block> GENERATED_FANS = ModDatagenEntries.FAN_PAIRS.stream()
            .flatMap(pair -> Stream.of(pair.base().get(), pair.glowing().get()))
            .collect(java.util.stream.Collectors.toUnmodifiableSet());
    private static final Set<Block> GENERATED_DOORS = ModDatagenEntries.DOORS.stream()
            .map(door -> (Block)door.get())
            .collect(java.util.stream.Collectors.toUnmodifiableSet());
    private static final Set<Block> GENERATED_TRAPDOORS = ModDatagenEntries.TRAPDOORS.stream()
            .map(trapdoor -> (Block)trapdoor.get())
            .collect(java.util.stream.Collectors.toUnmodifiableSet());

    protected ModBlockLootTableProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void generateBlockLootTables() {
        GENERATED_BLOCKS.forEach(this::dropSelf);
        GENERATED_PILLARS.forEach(this::dropSelf);
        GENERATED_FANS.forEach(this::dropSelf);
        GENERATED_DOORS.forEach(door -> add(door, createDoorTable(door)));
        GENERATED_TRAPDOORS.forEach(this::dropSelf);
    }
}
