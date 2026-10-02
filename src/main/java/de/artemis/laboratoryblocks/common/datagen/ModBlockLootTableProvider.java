package de.artemis.laboratoryblocks.common.datagen;

import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootTables {
    private static final Set<Block> GENERATED_BLOCKS = ModDatagenEntries.ALL_PAIRS.stream()
            .flatMap(pair -> Stream.of(pair.base().get(), pair.glowing().get()))
            .collect(java.util.stream.Collectors.toSet());
    private static final Set<Block> GENERATED_PILLARS = ModDatagenEntries.PILLAR_PAIRS.stream()
            .flatMap(pair -> Stream.of(pair.base().get(), pair.glowing().get()))
            .collect(java.util.stream.Collectors.toSet());
    private static final Set<Block> GENERATED_FANS = ModDatagenEntries.FAN_PAIRS.stream()
            .flatMap(pair -> Stream.of(pair.base().get(), pair.glowing().get()))
            .collect(java.util.stream.Collectors.toSet());
    private static final Set<Block> GENERATED_DOORS = ModDatagenEntries.DOORS.stream()
            .map(door -> (Block)door.get())
            .collect(java.util.stream.Collectors.toSet());
    private static final Set<Block> GENERATED_TRAPDOORS = ModDatagenEntries.TRAPDOORS.stream()
            .map(trapdoor -> (Block)trapdoor.get())
            .collect(java.util.stream.Collectors.toSet());

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
        List<Block> blocks = new ArrayList<>();
        blocks.addAll(GENERATED_BLOCKS);
        blocks.addAll(GENERATED_PILLARS);
        blocks.addAll(GENERATED_FANS);
        blocks.addAll(GENERATED_DOORS);
        blocks.addAll(GENERATED_TRAPDOORS);
        return blocks;
    }
}
