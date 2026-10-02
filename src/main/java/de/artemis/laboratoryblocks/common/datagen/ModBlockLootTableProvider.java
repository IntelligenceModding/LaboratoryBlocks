package de.artemis.laboratoryblocks.common.datagen;

import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Stream;
import java.util.Set;

public class ModBlockLootTableProvider extends BlockLoot {
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

    @Override
    protected void addTables() {
        GENERATED_BLOCKS.forEach(this::dropSelf);
        GENERATED_PILLARS.forEach(this::dropSelf);
        GENERATED_FANS.forEach(this::dropSelf);
        GENERATED_DOORS.forEach(door -> add(door, createDoorTable(door)));
        GENERATED_TRAPDOORS.forEach(this::dropSelf);
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return Stream.of(GENERATED_BLOCKS.stream(), GENERATED_PILLARS.stream(), GENERATED_FANS.stream(), GENERATED_DOORS.stream(), GENERATED_TRAPDOORS.stream())
                .flatMap(stream -> stream)
                .toList();
    }
}
