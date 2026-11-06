package de.artemis.laboratoryblocks.common.registry;
import de.artemis.laboratoryblocks.LaboratoryBlocks;
import net.minecraft.util.NonNullList;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public final class ModCreativeModeTabs {
    public static final ItemGroup LABORATORY_BLOCKS_CREATIVE_TAB = tab(false);
    public static final ItemGroup DARK_LABORATORY_BLOCKS_CREATIVE_TAB = tab(true);
    private static ItemGroup tab(boolean dark) {
        return new ItemGroup(LaboratoryBlocks.MOD_ID + (dark ? ".dark" : "")) {
            @Override public ItemStack makeIcon() {
                return new ItemStack(dark ? ModBlocks.DARK_QUANTUM_LABORATORY_SCREEN.get() : ModBlocks.QUANTUM_LABORATORY_SCREEN.get());
            }
            @Override public void fillItemList(NonNullList<ItemStack> items) {
                items.add(new ItemStack(ModItems.CONFIGURATION_TOOL.get()));
                items.add(new ItemStack(ModItems.IRON_SCREW.get()));
                items.add(new ItemStack(ModItems.GLOWSTONE_PARTICLES.get()));
                ModBlockPalettes.BLOCK_ORDER.forEach(pair -> {
                    java.util.function.Supplier<? extends net.minecraft.block.Block> block = dark ? pair.dark() : pair.original();
                    if (block != null) items.add(new ItemStack(block.get()));
                });
                items.add(new ItemStack(ModItems.MUSIC_DISC_EXPLORATION.get()));
                items.add(new ItemStack(ModItems.MUSIC_DISC_PONDERING_THE_COSMOS.get()));
            }
        };
    }
    private ModCreativeModeTabs() {}
}
