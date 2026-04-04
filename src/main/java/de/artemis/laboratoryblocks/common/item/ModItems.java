package de.artemis.laboratoryblocks.common.item;

import de.artemis.laboratoryblocks.LaboratoryBlocks;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(LaboratoryBlocks.MOD_ID);

    private static <T extends Item> DeferredItem<T> register(String name, Function<Item.Properties, T> itemFactory, UnaryOperator<Item.Properties> properties) {
        return ITEMS.registerItem(name, itemFactory, properties);
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    public static final DeferredItem<Item> IRON_SCREW = register("iron_screw",
            Item::new, UnaryOperator.identity());

    public static final DeferredItem<Item> GLOWSTONE_PARTICLES = register("glowstone_particles",
            Item::new, UnaryOperator.identity());

    public static final DeferredItem<Item> REDSTONE_PARTICLES = register("redstone_particles",
            Item::new, UnaryOperator.identity());

    public static final DeferredItem<ConfigurationToolItem> CONFIGURATION_TOOL = register("configuration_tool",
            ConfigurationToolItem::new, properties -> properties.durability(640).rarity(Rarity.UNCOMMON));
}

