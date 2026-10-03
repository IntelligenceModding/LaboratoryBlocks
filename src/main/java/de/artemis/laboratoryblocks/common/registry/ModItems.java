package de.artemis.laboratoryblocks.common.registry;

import de.artemis.laboratoryblocks.LaboratoryBlocks;
import de.artemis.laboratoryblocks.common.item.ConfigurationToolItem;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
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
        Identifier id = id(name);
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, id);
        T item = Registry.register(BuiltInRegistries.ITEM, id, itemFactory.apply(properties.apply(new Item.Properties().setId(key))));
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
            ConfigurationToolItem::new, properties -> properties.durability(640).rarity(Rarity.UNCOMMON).enchantable(10).repairable(Items.IRON_INGOT));

    private static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(LaboratoryBlocks.MOD_ID, path);
    }
}
