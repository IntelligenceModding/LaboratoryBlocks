package de.artemis.laboratoryblocks.common.datagen;

import de.artemis.laboratoryblocks.LaboratoryBlocks;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import de.artemis.laboratoryblocks.common.loot.AddMusicDiscLootModifier;
import net.minecraftforge.common.loot.LootTableIdCondition;



public class ModLootModifierProvider extends GlobalLootModifierProvider {
    public ModLootModifierProvider(DataGenerator output) {
        super(output, LaboratoryBlocks.MOD_ID);
    }

    @Override
    protected void start() {
        for (var entry : ModDatagenEntries.MUSIC_DISC_LOOT) {
            add("music_disc_" + entry.name(), de.artemis.laboratoryblocks.common.registry.ModLootModifiers.ADD_MUSIC_DISC.get(), new AddMusicDiscLootModifier(
                    new net.minecraft.world.level.storage.loot.predicates.LootItemCondition[] { LootTableIdCondition.builder(new ResourceLocation(entry.chest())).build(), net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition.randomChance(entry.chance()).build() },
                    entry.disc().get()));
        }
    }
}
