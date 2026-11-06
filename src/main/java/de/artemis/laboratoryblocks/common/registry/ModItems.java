package de.artemis.laboratoryblocks.common.registry;

import de.artemis.laboratoryblocks.LaboratoryBlocks;
import de.artemis.laboratoryblocks.common.item.ConfigurationToolItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.RecordItem;



import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, LaboratoryBlocks.MOD_ID);

    private static <T extends Item> RegistryObject<T> register(String name, Function<Item.Properties, T> itemFactory, UnaryOperator<Item.Properties> properties) {
        return ITEMS.register(name, () -> itemFactory.apply(properties.apply(new Item.Properties().tab(ModCreativeModeTabs.LABORATORY_BLOCKS_CREATIVE_TAB))));
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    public static final RegistryObject<Item> IRON_SCREW = register("iron_screw",
            Item::new, UnaryOperator.identity());

    public static final RegistryObject<Item> GLOWSTONE_PARTICLES = register("glowstone_particles",
            Item::new, UnaryOperator.identity());

    public static final RegistryObject<Item> MUSIC_DISC_EXPLORATION = register("music_disc_exploration",
            properties -> new RecordItem(13, ModSoundEvents.MUSIC_DISC_EXPLORATION, properties, 2688),
            properties -> properties.stacksTo(1).rarity(Rarity.UNCOMMON).tab(net.minecraft.world.item.CreativeModeTab.TAB_MISC));

    public static final RegistryObject<Item> MUSIC_DISC_PONDERING_THE_COSMOS = register("music_disc_pondering_the_cosmos",
            properties -> new RecordItem(14, ModSoundEvents.MUSIC_DISC_PONDERING_THE_COSMOS, properties, 6155),
            properties -> properties.stacksTo(1).rarity(Rarity.UNCOMMON).tab(net.minecraft.world.item.CreativeModeTab.TAB_MISC));

    public static final RegistryObject<ConfigurationToolItem> CONFIGURATION_TOOL = register("configuration_tool",
            ConfigurationToolItem::new, properties -> properties.durability(640).rarity(Rarity.UNCOMMON));
}