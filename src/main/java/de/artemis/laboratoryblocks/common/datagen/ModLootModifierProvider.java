package de.artemis.laboratoryblocks.common.datagen;

import de.artemis.laboratoryblocks.LaboratoryBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.AddTableLootModifier;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class ModLootModifierProvider extends GlobalLootModifierProvider {
    public ModLootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, LaboratoryBlocks.MOD_ID);
    }

    @Override
    protected void start() {
        for (var entry : ModDatagenEntries.MUSIC_DISC_LOOT) {
            add("music_disc_" + entry.name(), new AddTableLootModifier(
                    new net.minecraft.world.level.storage.loot.predicates.LootItemCondition[] { LootTableIdCondition.builder(Identifier.withDefaultNamespace(entry.chest())).build() },
                    IGlobalLootModifier.DEFAULT_PRIORITY, ModMusicDiscLootProvider.table(entry.name())));
        }
    }
}
