package de.artemis.laboratoryblocks.common.datagen;

import de.artemis.laboratoryblocks.LaboratoryBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;

public record ModMusicDiscLootProvider(LootTableSubProvider.Context output) implements LootTableSubProvider {
    public static ResourceKey<LootTable> table(String name) {
        return ResourceKey.create(Registries.LOOT_TABLE,
                Identifier.fromNamespaceAndPath(LaboratoryBlocks.MOD_ID, "inject/music_disc_" + name));
    }

    @Override
    public void run() {
        for (var entry : ModDatagenEntries.MUSIC_DISC_LOOT) {
            output.accept(table(entry.name()), LootTable.lootTable().withPool(
                    LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                            .when(LootItemRandomChanceCondition.randomChance(entry.chance()))
                            .add(LootItem.lootTableItem(entry.disc().get()))));
        }
    }
}
