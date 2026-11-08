package de.artemis.laboratoryblocks.common.event;

import de.artemis.laboratoryblocks.common.datagen.ModDatagenEntries;
import de.artemis.laboratoryblocks.common.registry.ModItems;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

public final class MusicDiscEvents {
    public static void register() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(entries -> {
            entries.accept(ModItems.MUSIC_DISC_EXPLORATION.get());
            entries.accept(ModItems.MUSIC_DISC_PONDERING_THE_COSMOS.get());
        });
        LootTableEvents.MODIFY.register((key, table, source, registries) -> {
            for (var entry : ModDatagenEntries.MUSIC_DISC_LOOT) {
                if (key.location().equals(ResourceLocation.withDefaultNamespace(entry.chest()))) {
                    table.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                            .when(LootItemRandomChanceCondition.randomChance(entry.chance()))
                            .add(LootItem.lootTableItem(entry.disc().get())));
                }
            }
        });
    }

    private MusicDiscEvents() {
    }
}
