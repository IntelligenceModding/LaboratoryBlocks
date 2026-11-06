package de.artemis.laboratoryblocks.common.event;

import de.artemis.laboratoryblocks.LaboratoryBlocks;
import de.artemis.laboratoryblocks.common.registry.ModItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@EventBusSubscriber(modid = LaboratoryBlocks.MOD_ID)
public final class MusicDiscCreativeTabEvents {
    @SubscribeEvent
    public static void addDiscs(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey().equals(CreativeModeTabs.TOOLS_AND_UTILITIES)) {
            event.insertAfter(new ItemStack(Items.MUSIC_DISC_5), new ItemStack(ModItems.MUSIC_DISC_EXPLORATION.get()),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(ModItems.MUSIC_DISC_EXPLORATION.get()), new ItemStack(ModItems.MUSIC_DISC_PONDERING_THE_COSMOS.get()),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }

    private MusicDiscCreativeTabEvents() {}
}
