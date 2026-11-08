package de.artemis.laboratoryblocks.common.event;
import de.artemis.laboratoryblocks.common.datagen.ModDatagenEntries;
import de.artemis.laboratoryblocks.common.registry.ModItems;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
public final class MusicDiscEvents {
    public static void register() {
        LootTableEvents.MODIFY.register((resources, manager, id, table, source) -> {
            for (var entry : ModDatagenEntries.MUSIC_DISC_LOOT) {
                if (id.equals(new ResourceLocation("minecraft", entry.chest()))) {
                    table.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                            .when(LootItemRandomChanceCondition.randomChance(entry.chance()))
                            .add(LootItem.lootTableItem(entry.disc().get())));
                }
            }
        });
    }
    private MusicDiscEvents() {}
}