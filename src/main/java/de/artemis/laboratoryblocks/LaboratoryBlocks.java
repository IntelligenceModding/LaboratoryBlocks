package de.artemis.laboratoryblocks;

import com.mojang.logging.LogUtils;
import de.artemis.laboratoryblocks.common.registration.ModBlocks;
import de.artemis.laboratoryblocks.common.registration.ModItems;
import de.artemis.laboratoryblocks.common.registration.Registration;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Collections;

@Mod(LaboratoryBlocks.MOD_ID)
public class LaboratoryBlocks {

    public static final String MOD_ID = "laboratoryblocks";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final CreativeModeTab INVENTORY_TAB = new CreativeModeTab(MOD_ID) {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(ModBlocks.LABORATORY_BLOCK.get());
        }

        @Override
        public void fillItemList(@NotNull NonNullList<ItemStack> items) {
            ArrayList<Item> blockList = new ArrayList<>();

            int run = 0;

            Collections.addAll(blockList, ModItems.STARCH.get(), ModItems.COMPRESSED_STARCH.get(), ModItems.PLA_SHEETS.get(), ModBlocks.PLA_BLOCK.get().asItem(), ModBlocks.ENLIGHTED_PLA_BLOCK.get().asItem(), ModBlocks.PLA_TILES.get().asItem(), ModBlocks.ENLIGHTED_PLA_TILES.get().asItem(), ModBlocks.PLA_FLOORING.get().asItem(), ModBlocks.ENLIGHTED_PLA_FLOORING.get().asItem(), ModBlocks.TILED_PLA_FLOORING.get().asItem(), ModBlocks.ENLIGHTED_TILED_PLA_FLOORING.get().asItem(), ModBlocks.LABORATORY_BLOCK.get().asItem(), ModBlocks.ENLIGHTED_LABORATORY_BLOCK.get().asItem(),ModBlocks.LABORATORY_TILES.get().asItem(), ModBlocks.ENLIGHTED_LABORATORY_TILES.get().asItem(), ModBlocks.GRAY_LABORATORY_TILES.get().asItem(), ModBlocks.ENLIGHTED_GRAY_LABORATORY_TILES.get().asItem(), ModBlocks.MIXED_LABORATORY_TILES.get().asItem(), ModBlocks.ENLIGHTED_MIXED_LABORATORY_TILES.get().asItem(), ModBlocks.OAK_LABORATORY_TILES.get().asItem(), ModBlocks.ENLIGHTED_OAK_LABORATORY_TILES.get().asItem(), ModBlocks.SPRUCE_LABORATORY_TILES.get().asItem(), ModBlocks.ENLIGHTED_SPRUCE_LABORATORY_TILES.get().asItem(), ModBlocks.BIRCH_LABORATORY_TILES.get().asItem(), ModBlocks.ENLIGHTED_BIRCH_LABORATORY_TILES.get().asItem(), ModBlocks.DARK_OAK_LABORATORY_TILES.get().asItem(), ModBlocks.ENLIGHTED_DARK_OAK_LABORATORY_TILES.get().asItem(), ModBlocks.JUNGLE_LABORATORY_TILES.get().asItem(), ModBlocks.ENLIGHTED_JUNGLE_LABORATORY_TILES.get().asItem(), ModBlocks.ACACIA_LABORATORY_TILES.get().asItem(), ModBlocks.ENLIGHTED_ACACIA_LABORATORY_TILES.get().asItem(), ModBlocks.MANGROVE_LABORATORY_TILES.get().asItem(), ModBlocks.ENLIGHTED_MANGROVE_LABORATORY_TILES.get().asItem(), ModBlocks.CRIMSON_LABORATORY_TILES.get().asItem(), ModBlocks.ENLIGHTED_CRIMSON_LABORATORY_TILES.get().asItem(), ModBlocks.WARPED_LABORATORY_TILES.get().asItem(), ModBlocks.ENLIGHTED_WARPED_LABORATORY_TILES.get().asItem(), ModBlocks.OAK_LABORATORY_FLOOR.get().asItem(), ModBlocks.ENLIGHTED_OAK_LABORATORY_FLOOR.get().asItem(), ModBlocks.SPRUCE_LABORATORY_FLOOR.get().asItem(), ModBlocks.ENLIGHTED_SPRUCE_LABORATORY_FLOOR.get().asItem(), ModBlocks.BIRCH_LABORATORY_FLOOR.get().asItem(), ModBlocks.ENLIGHTED_BIRCH_LABORATORY_FLOOR.get().asItem(), ModBlocks.DARK_OAK_LABORATORY_FLOOR.get().asItem(), ModBlocks.ENLIGHTED_DARK_OAK_LABORATORY_FLOOR.get().asItem(), ModBlocks.JUNGLE_LABORATORY_FLOOR.get().asItem(), ModBlocks.ENLIGHTED_JUNGLE_LABORATORY_FLOOR.get().asItem(), ModBlocks.ACACIA_LABORATORY_FLOOR.get().asItem(), ModBlocks.ENLIGHTED_ACACIA_LABORATORY_FLOOR.get().asItem(), ModBlocks.MANGROVE_LABORATORY_FLOOR.get().asItem(), ModBlocks.ENLIGHTED_MANGROVE_LABORATORY_FLOOR.get().asItem(), ModBlocks.CRIMSON_LABORATORY_FLOOR.get().asItem(), ModBlocks.ENLIGHTED_CRIMSON_LABORATORY_FLOOR.get().asItem(), ModBlocks.WARPED_LABORATORY_FLOOR.get().asItem(), ModBlocks.ENLIGHTED_WARPED_LABORATORY_FLOOR.get().asItem(), ModBlocks.LABORATORY_GLASS.get().asItem(), ModBlocks.ENLIGHTED_LABORATORY_GLASS.get().asItem(), ModBlocks.LABORATORY_VENT.get().asItem(), ModBlocks.ENLIGHTED_LABORATORY_VENT.get().asItem(), ModBlocks.LEFT_FACED_BLUE_SIGNALING_LABORATORY_BLOCK.get().asItem(), ModBlocks.ENLIGHTED_LEFT_FACED_BLUE_SIGNALING_LABORATORY_BLOCK.get().asItem(), ModBlocks.RIGHT_FACED_BLUE_SIGNALING_LABORATORY_BLOCK.get().asItem(), ModBlocks.ENLIGHTED_RIGHT_FACED_BLUE_SIGNALING_LABORATORY_BLOCK.get().asItem(), ModBlocks.LEFT_FACED_RED_SIGNALING_LABORATORY_BLOCK.get().asItem(), ModBlocks.ENLIGHTED_LEFT_FACED_RED_SIGNALING_LABORATORY_BLOCK.get().asItem(), ModBlocks.RIGHT_FACED_RED_SIGNALING_LABORATORY_BLOCK.get().asItem(), ModBlocks.ENLIGHTED_RIGHT_FACED_RED_SIGNALING_LABORATORY_BLOCK.get().asItem(), ModBlocks.LEFT_FACED_GREEN_SIGNALING_LABORATORY_BLOCK.get().asItem(), ModBlocks.ENLIGHTED_LEFT_FACED_GREEN_SIGNALING_LABORATORY_BLOCK.get().asItem(), ModBlocks.RIGHT_FACED_GREEN_SIGNALING_LABORATORY_BLOCK.get().asItem(), ModBlocks.ENLIGHTED_RIGHT_FACED_GREEN_SIGNALING_LABORATORY_BLOCK.get().asItem());
            for (Item x : blockList) {

                items.add(run, new ItemStack(x));
                run++;
            }
        }
    };

    public LaboratoryBlocks() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);
        Registration.register();
    }
}
