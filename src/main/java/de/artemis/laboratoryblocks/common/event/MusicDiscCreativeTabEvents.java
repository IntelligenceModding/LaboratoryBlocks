package de.artemis.laboratoryblocks.common.event;

import de.artemis.laboratoryblocks.LaboratoryBlocks;
import de.artemis.laboratoryblocks.common.registry.ModItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;

@Mod.EventBusSubscriber(modid = LaboratoryBlocks.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class MusicDiscCreativeTabEvents {
    @SubscribeEvent
    public static void addDiscs(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey().equals(CreativeModeTabs.TOOLS_AND_UTILITIES)) {
            event.accept(ModItems.MUSIC_DISC_EXPLORATION);
            event.accept(ModItems.MUSIC_DISC_PONDERING_THE_COSMOS);
        }
    }

    private MusicDiscCreativeTabEvents() {}
}
