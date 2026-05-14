package de.artemis.laboratoryblocks.common.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
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
    public void appendHoverText(@NotNull ItemStack itemStack, Item.@NotNull TooltipContext context, @NotNull List<Component> tooltipComponents, @NotNull TooltipFlag flag) {
        tooltipComponents.add(Component.translatable("tooltip.laboratoryblocks.configuration_tool.remove_glowstone").withStyle(ChatFormatting.GRAY));
        super.appendHoverText(itemStack, context, tooltipComponents, flag);
    }
}