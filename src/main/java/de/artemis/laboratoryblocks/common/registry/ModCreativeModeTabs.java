package de.artemis.laboratoryblocks.common.registry;
import de.artemis.laboratoryblocks.LaboratoryBlocks;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
public final class ModCreativeModeTabs {
    public static final CreativeModeTab LABORATORY_BLOCKS_CREATIVE_TAB = createTab(false);
    public static final CreativeModeTab DARK_LABORATORY_BLOCKS_CREATIVE_TAB = createTab(true);
    private static CreativeModeTab createTab(boolean dark) {
        return FabricItemGroupBuilder.create(new ResourceLocation(LaboratoryBlocks.MOD_ID,
                        dark ? "dark_laboratory_blocks_creative_tab" : "laboratory_blocks_creative_tab"))
                .icon(() -> new ItemStack((dark ? ModBlocks.DARK_QUANTUM_LABORATORY_SCREEN : ModBlocks.QUANTUM_LABORATORY_SCREEN).get()))
                .appendItems(items -> {
                    items.add(new ItemStack(ModItems.CONFIGURATION_TOOL.get()));
                    items.add(new ItemStack(ModItems.IRON_SCREW.get()));
                    items.add(new ItemStack(ModItems.GLOWSTONE_PARTICLES.get()));
                    items.add(new ItemStack(ModItems.MUSIC_DISC_EXPLORATION.get()));
                    items.add(new ItemStack(ModItems.MUSIC_DISC_PONDERING_THE_COSMOS.get()));
                    ModBlockPalettes.BLOCK_ORDER.forEach(pair -> {
                        var block = dark ? pair.dark() : pair.original();
                        if (block != null) items.add(new ItemStack(block.get()));
                    });
                }).build();
    }
    public static void register() {}
    private ModCreativeModeTabs() {}
}
