package de.artemis.laboratoryblocks.common.data;

import de.artemis.laboratoryblocks.common.registration.ModBlocks;
import de.artemis.laboratoryblocks.common.registration.Registration;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

public class BlockLootTables extends BlockLoot {

    @Override
    protected void addTables() {
        dropSelf(ModBlocks.PLA_BLOCK.get());
        dropSelf(ModBlocks.ENLIGHTED_PLA_BLOCK.get());
        dropSelf(ModBlocks.PLA_TILES.get());
        dropSelf(ModBlocks.ENLIGHTED_PLA_TILES.get());
        dropSelf(ModBlocks.PLA_FLOORING.get());
        dropSelf(ModBlocks.ENLIGHTED_PLA_FLOORING.get());
        dropSelf(ModBlocks.TILED_PLA_FLOORING.get());
        dropSelf(ModBlocks.ENLIGHTED_TILED_PLA_FLOORING.get());
        dropSelf(ModBlocks.LABORATORY_BLOCK.get());
        dropSelf(ModBlocks.ENLIGHTED_LABORATORY_BLOCK.get());
        dropSelf(ModBlocks.LABORATORY_TILES.get());
        dropSelf(ModBlocks.ENLIGHTED_LABORATORY_TILES.get());
        dropSelf(ModBlocks.GRAY_LABORATORY_TILES.get());
        dropSelf(ModBlocks.ENLIGHTED_GRAY_LABORATORY_TILES.get());
        dropSelf(ModBlocks.MIXED_LABORATORY_TILES.get());
        dropSelf(ModBlocks.ENLIGHTED_MIXED_LABORATORY_TILES.get());
        dropSelf(ModBlocks.OAK_LABORATORY_FLOOR.get());
        dropSelf(ModBlocks.ENLIGHTED_OAK_LABORATORY_FLOOR.get());
        dropSelf(ModBlocks.SPRUCE_LABORATORY_FLOOR.get());
        dropSelf(ModBlocks.ENLIGHTED_SPRUCE_LABORATORY_FLOOR.get());
        dropSelf(ModBlocks.BIRCH_LABORATORY_FLOOR.get());
        dropSelf(ModBlocks.ENLIGHTED_BIRCH_LABORATORY_FLOOR.get());
        dropSelf(ModBlocks.DARK_OAK_LABORATORY_FLOOR.get());
        dropSelf(ModBlocks.ENLIGHTED_DARK_OAK_LABORATORY_FLOOR.get());
        dropSelf(ModBlocks.ACACIA_LABORATORY_FLOOR.get());
        dropSelf(ModBlocks.ENLIGHTED_ACACIA_LABORATORY_FLOOR.get());
        dropSelf(ModBlocks.JUNGLE_LABORATORY_FLOOR.get());
        dropSelf(ModBlocks.ENLIGHTED_JUNGLE_LABORATORY_FLOOR.get());
        dropSelf(ModBlocks.CRIMSON_LABORATORY_FLOOR.get());
        dropSelf(ModBlocks.ENLIGHTED_CRIMSON_LABORATORY_FLOOR.get());
        dropSelf(ModBlocks.WARPED_LABORATORY_FLOOR.get());
        dropSelf(ModBlocks.ENLIGHTED_WARPED_LABORATORY_FLOOR.get());
        dropSelf(ModBlocks.OAK_LABORATORY_TILES.get());
        dropSelf(ModBlocks.ENLIGHTED_OAK_LABORATORY_TILES.get());
        dropSelf(ModBlocks.SPRUCE_LABORATORY_TILES.get());
        dropSelf(ModBlocks.ENLIGHTED_SPRUCE_LABORATORY_TILES.get());
        dropSelf(ModBlocks.BIRCH_LABORATORY_TILES.get());
        dropSelf(ModBlocks.ENLIGHTED_BIRCH_LABORATORY_TILES.get());
        dropSelf(ModBlocks.DARK_OAK_LABORATORY_TILES.get());
        dropSelf(ModBlocks.ENLIGHTED_DARK_OAK_LABORATORY_TILES.get());
        dropSelf(ModBlocks.ACACIA_LABORATORY_TILES.get());
        dropSelf(ModBlocks.ENLIGHTED_ACACIA_LABORATORY_TILES.get());
        dropSelf(ModBlocks.JUNGLE_LABORATORY_TILES.get());
        dropSelf(ModBlocks.ENLIGHTED_JUNGLE_LABORATORY_TILES.get());
        dropSelf(ModBlocks.CRIMSON_LABORATORY_TILES.get());
        dropSelf(ModBlocks.ENLIGHTED_CRIMSON_LABORATORY_TILES.get());
        dropSelf(ModBlocks.WARPED_LABORATORY_TILES.get());
        dropSelf(ModBlocks.ENLIGHTED_WARPED_LABORATORY_TILES.get());
        dropSelf(ModBlocks.LABORATORY_VENT.get());
        dropSelf(ModBlocks.ENLIGHTED_LABORATORY_VENT.get());
        dropSelf(ModBlocks.LEFT_FACED_BLUE_SIGNALING_LABORATORY_BLOCK.get());
        dropSelf(ModBlocks.ENLIGHTED_LEFT_FACED_BLUE_SIGNALING_LABORATORY_BLOCK.get());
        dropSelf(ModBlocks.RIGHT_FACED_BLUE_SIGNALING_LABORATORY_BLOCK.get());
        dropSelf(ModBlocks.ENLIGHTED_RIGHT_FACED_BLUE_SIGNALING_LABORATORY_BLOCK.get());
        dropSelf(ModBlocks.LEFT_FACED_RED_SIGNALING_LABORATORY_BLOCK.get());
        dropSelf(ModBlocks.ENLIGHTED_LEFT_FACED_RED_SIGNALING_LABORATORY_BLOCK.get());
        dropSelf(ModBlocks.RIGHT_FACED_RED_SIGNALING_LABORATORY_BLOCK.get());
        dropSelf(ModBlocks.ENLIGHTED_RIGHT_FACED_RED_SIGNALING_LABORATORY_BLOCK.get());
        dropSelf(ModBlocks.LEFT_FACED_GREEN_SIGNALING_LABORATORY_BLOCK.get());
        dropSelf(ModBlocks.ENLIGHTED_LEFT_FACED_GREEN_SIGNALING_LABORATORY_BLOCK.get());
        dropSelf(ModBlocks.RIGHT_FACED_GREEN_SIGNALING_LABORATORY_BLOCK.get());
        dropSelf(ModBlocks.ENLIGHTED_RIGHT_FACED_GREEN_SIGNALING_LABORATORY_BLOCK.get());
        dropSelf(ModBlocks.LABORATORY_FAN.get());
        dropSelf(ModBlocks.ENLIGHTED_LABORATORY_FAN.get());
        dropSelf(ModBlocks.SCREWED_LABORATORY_BLOCK.get());
        dropSelf(ModBlocks.ENLIGHTED_SCREWED_LABORATORY_BLOCK.get());
        dropSelf(ModBlocks.CLEAR_LABORATORY_SCREEN.get());
        dropSelf(ModBlocks.ENLIGHTED_CLEAR_LABORATORY_SCREEN.get());
        dropSelf(ModBlocks.LABORATORY_PILLAR.get());
        dropSelf(ModBlocks.ENLIGHTED_LABORATORY_PILLAR.get());
        dropSelf(ModBlocks.GRAY_LABORATORY_PILLAR.get());
        dropSelf(ModBlocks.ENLIGHTED_GRAY_LABORATORY_PILLAR.get());

        dropWhenSilkTouch(ModBlocks.LABORATORY_GLASS.get());
        add(ModBlocks.ENLIGHTED_LABORATORY_GLASS.get(), (p_124233_) -> {
            return createSingleItemTableWithSilkTouch(p_124233_, Items.GLOWSTONE_DUST, ConstantValue.exactly(1.0F));
        });
    }

    @Override
    @NotNull
    protected Iterable<Block> getKnownBlocks() {
        return Registration.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
