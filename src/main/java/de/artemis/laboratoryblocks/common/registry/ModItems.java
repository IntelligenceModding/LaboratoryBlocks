package de.artemis.laboratoryblocks.common.registry;

import de.artemis.laboratoryblocks.LaboratoryBlocks;
import de.artemis.laboratoryblocks.common.item.ConfigurationToolItem;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class ModItems {
    private static final List<RegistrySupplier<? extends Item>> ENTRIES = new ArrayList<>();
    public static final List<RegistrySupplier<? extends Item>> ITEMS = Collections.unmodifiableList(ENTRIES);

    private static <T extends Item> RegistrySupplier<T> register(String name, Function<Item.Properties, T> itemFactory, UnaryOperator<Item.Properties> properties) {
        ResourceLocation id = id(name);
        T item = Registry.register(Registry.ITEM, id, itemFactory.apply(properties.apply(new Item.Properties().tab(ModCreativeModeTabs.LABORATORY_BLOCKS_CREATIVE_TAB))));
        RegistrySupplier<T> entry = new RegistrySupplier<>(id, item);
        ENTRIES.add(entry);
        return entry;
    }

    static RegistrySupplier<BlockItem> registerBlockItem(String name, Block block) {
        return register(name, properties -> new BlockItem(block, properties), UnaryOperator.identity());
    }

    public static void register() {
    }

    public static final RegistrySupplier<Item> IRON_SCREW = register("iron_screw",
            Item::new, UnaryOperator.identity());

    public static final RegistrySupplier<Item> GLOWSTONE_PARTICLES = register("glowstone_particles",
            Item::new, UnaryOperator.identity());

    public static final RegistrySupplier<ConfigurationToolItem> CONFIGURATION_TOOL = register("configuration_tool",
            ConfigurationToolItem::new, properties -> properties.durability(640).rarity(Rarity.UNCOMMON));

    private static ResourceLocation id(String path) {
        return new ResourceLocation(LaboratoryBlocks.MOD_ID, path);
    }
}
