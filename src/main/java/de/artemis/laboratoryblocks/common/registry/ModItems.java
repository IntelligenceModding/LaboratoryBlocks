package de.artemis.laboratoryblocks.common.registry;

import de.artemis.laboratoryblocks.LaboratoryBlocks;
import de.artemis.laboratoryblocks.common.item.ConfigurationToolItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
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

    public static final DeferredItem<Item> MUSIC_DISC_EXPLORATION = register("music_disc_exploration",
            Item::new, properties -> properties.stacksTo(1).rarity(Rarity.UNCOMMON)
                    .jukeboxPlayable(ResourceKey.create(Registries.JUKEBOX_SONG,
                            Identifier.fromNamespaceAndPath(LaboratoryBlocks.MOD_ID, "exploration"))));

    public static final DeferredItem<Item> MUSIC_DISC_PONDERING_THE_COSMOS = register("music_disc_pondering_the_cosmos",
            Item::new, properties -> properties.stacksTo(1).rarity(Rarity.UNCOMMON)
                    .jukeboxPlayable(ResourceKey.create(Registries.JUKEBOX_SONG,
                            Identifier.fromNamespaceAndPath(LaboratoryBlocks.MOD_ID, "pondering_the_cosmos"))));

    public static final DeferredItem<ConfigurationToolItem> CONFIGURATION_TOOL = register("configuration_tool",
            ConfigurationToolItem::new, properties -> properties.durability(640).rarity(Rarity.UNCOMMON));
}
