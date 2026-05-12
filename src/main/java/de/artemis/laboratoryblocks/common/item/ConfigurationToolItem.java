package de.artemis.laboratoryblocks.common.item;

import de.artemis.laboratoryblocks.client.ModKeyBindings;
import de.artemis.laboratoryblocks.common.util.KeyBindingUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class ConfigurationToolItem extends Item {
    public ConfigurationToolItem(Properties properties) {
        super(properties);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void appendHoverText(@NotNull ItemStack itemStack, Item.@NotNull TooltipContext context, @NotNull TooltipDisplay tooltipDisplay, @NotNull Consumer<Component> tooltipAdder, @NotNull TooltipFlag flag) {
        if (KeyBindingUtil.isKeyPressed(ModKeyBindings.SHOW_INFORMATION)) {
            tooltipAdder.accept(
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
            tooltipAdder.accept(
                    Component.translatable(
                            "tooltip.laboratoryblocks.configuration_tool_preview",
                            Component.literal(ModKeyBindings.REMOVE_GLOWSTONE_CONFIGURATION_TOOL_ACTION.getKey()
                                            .getDisplayName().getString())
                                    .withStyle(Style.EMPTY.withColor(0x549CFC))
                    ).withStyle(ChatFormatting.GRAY)
            );
        }

        super.appendHoverText(itemStack, context, tooltipDisplay, tooltipAdder, flag);
    }
}