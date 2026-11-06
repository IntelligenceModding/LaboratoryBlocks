package de.artemis.laboratoryblocks.common.datagen;

import de.artemis.laboratoryblocks.LaboratoryBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import de.artemis.laboratoryblocks.common.loot.AddMusicDiscLootModifier;
import net.minecraftforge.common.loot.LootTableIdCondition;

import java.util.concurrent.CompletableFuture;

public class ModLootModifierProvider extends GlobalLootModifierProvider {
    public ModLootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, LaboratoryBlocks.MOD_ID, registries);
    }

    @Override
    protected void start(HolderLookup.Provider registries) {
        for (var entry : ModDatagenEntries.MUSIC_DISC_LOOT) {
            add("music_disc_" + entry.name(), new AddMusicDiscLootModifier(
                    new net.minecraft.world.level.storage.loot.predicates.LootItemCondition[] { LootTableIdCondition.builder(ResourceLocation.withDefaultNamespace(entry.chest())).build(), net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition.randomChance(entry.chance()).build() },
                    entry.disc().get()));
        }
    }
}
