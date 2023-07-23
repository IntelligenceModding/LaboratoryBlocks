package de.artemis.laboratoryblocks.common.registration;

import de.artemis.laboratoryblocks.InventoryTab;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.RegistryObject;

import static de.artemis.laboratoryblocks.common.registration.Registration.CREATIVE_MODE_TABS;

public class ModCreativeModTabs {

    public static RegistryObject<CreativeModeTab> INVENTORY_TAB = CREATIVE_MODE_TABS.register("inventory_tab", ModCreativeModTabs::createInventoryTab);

    private static CreativeModeTab createInventoryTab() {
        CreativeModeTab.Builder builder = new CreativeModeTab.Builder(CreativeModeTab.Row.BOTTOM, -1);
        InventoryTab.createInventoryTab(builder);
        return builder.build();
    }

    public static void register() {
    }
}
