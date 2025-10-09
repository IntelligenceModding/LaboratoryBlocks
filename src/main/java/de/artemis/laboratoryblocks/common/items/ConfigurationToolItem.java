package de.artemis.laboratoryblocks.common.items;

import de.artemis.laboratoryblocks.common.registration.ModKeyBindings;
import de.artemis.laboratoryblocks.common.util.KeyBindingUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ConfigurationToolItem extends Item {
    public ConfigurationToolItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemStack, Item.@NotNull TooltipContext context, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        if (KeyBindingUtil.isKeyPressed(ModKeyBindings.SHOW_INFORMATION)) {
            tooltip.add(
                    Component.translatable(
                            "tooltip.laboratoryblocks.configuration_tool",
                            Component.literal(ModKeyBindings.REMOVE_GLOWSTONE_CONFIGURATION_TOOL_ACTION.getKey()
                                            .getDisplayName().getString())
                                    .withStyle(Style.EMPTY.withColor(0x549CFC)),
                            Component.literal(ModKeyBindings.REMOVE_REDSTONE_CONFIGURATION_TOOL_ACTION.getKey()
                                            .getDisplayName().getString())
                                    .withStyle(Style.EMPTY.withColor(0x549CFC))
                    ).withStyle(ChatFormatting.GRAY)
            );
        } else {
            tooltip.add(
                    Component.translatable(
                            "tooltip.laboratoryblocks.configuration_tool_preview",
                            Component.literal(ModKeyBindings.REMOVE_GLOWSTONE_CONFIGURATION_TOOL_ACTION.getKey()
                                            .getDisplayName().getString())
                                    .withStyle(Style.EMPTY.withColor(0x549CFC))
                    ).withStyle(ChatFormatting.GRAY)
            );
        }

        super.appendHoverText(itemStack, context, tooltip, flag);
    }
}
