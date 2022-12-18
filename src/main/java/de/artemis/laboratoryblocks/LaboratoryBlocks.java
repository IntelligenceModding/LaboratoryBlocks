package de.artemis.laboratoryblocks;

import com.mojang.logging.LogUtils;
import de.artemis.laboratoryblocks.common.registration.ModBlocks;
import de.artemis.laboratoryblocks.common.registration.Registration;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Collections;

@Mod(LaboratoryBlocks.MODID)
public class LaboratoryBlocks {

    public static final String MODID = "laboratoryblocks";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final CreativeModeTab INVENTORY_TAB = new CreativeModeTab(MODID) {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(ModBlocks.LABORATORY_BLOCK.get());
        }

        @Override
        public void fillItemList(@NotNull NonNullList<ItemStack> items) {
            ArrayList<Item> blockList = new ArrayList<>();

            int run = 0;

            Collections.addAll(blockList, ModBlocks.LABORATORY_BLOCK.get().asItem(), ModBlocks.ENLIGHTED_LABORATORY_BLOCK.get().asItem(), ModBlocks.OAK_LABORATORY_FLOOR.get().asItem(), ModBlocks.ENLIGHTED_OAK_LABORATORY_FLOOR.get().asItem(), ModBlocks.SPRUCE_LABORATORY_FLOOR.get().asItem(), ModBlocks.ENLIGHTED_SPRUCE_LABORATORY_FLOOR.get().asItem(), ModBlocks.BIRCH_LABORATORY_FLOOR.get().asItem(), ModBlocks.ENLIGHTED_BIRCH_LABORATORY_FLOOR.get().asItem(), ModBlocks.DARK_OAK_LABORATORY_FLOOR.get().asItem(), ModBlocks.ENLIGHTED_DARK_OAK_LABORATORY_FLOOR.get().asItem(), ModBlocks.ACACIA_LABORATORY_FLOOR.get().asItem(), ModBlocks.ENLIGHTED_ACACIA_LABORATORY_FLOOR.get().asItem(), ModBlocks.MANGROVE_LABORATORY_FLOOR.get().asItem(), ModBlocks.ENLIGHTED_MANGROVE_LABORATORY_FLOOR.get().asItem(), ModBlocks.CRIMSON_LABORATORY_FLOOR.get().asItem(), ModBlocks.ENLIGHTED_CRIMSON_LABORATORY_FLOOR.get().asItem(), ModBlocks.WARPED_LABORATORY_FLOOR.get().asItem(), ModBlocks.ENLIGHTED_WARPED_LABORATORY_FLOOR.get().asItem(), ModBlocks.LABORATORY_GLASS.get().asItem(), ModBlocks.ENLIGHTED_LABORATORY_GLASS.get().asItem());
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
