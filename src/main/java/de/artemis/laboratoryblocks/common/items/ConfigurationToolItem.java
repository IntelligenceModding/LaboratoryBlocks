package de.artemis.laboratoryblocks.common.items;

import de.artemis.laboratoryblocks.common.registration.ModKeyBindings;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ConfigurationToolItem extends Item {
    public ConfigurationToolItem(Properties properties) {
        super(properties);
    }


    @Override
    public void appendHoverText(@NotNull ItemStack itemStack, @Nullable Level level, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        tooltip.add(Component.translatable("tooltip.laboratoryblocks.configuration_tool", ModKeyBindings.ALTERNATIVE_CONFIGURATION_TOOL_ACTION.getKey().getDisplayName().getString()).withStyle(ChatFormatting.GRAY));

        super.appendHoverText(itemStack, level, tooltip, flag);
    }
}
