package de.artemis.laboratoryblocks.common.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ConfigurationToolItem extends Item {
    public ConfigurationToolItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemStack, @Nullable World level, @NotNull List<ITextComponent> tooltipComponents, @NotNull ITooltipFlag flag) {
        tooltipComponents.add(new TranslationTextComponent("tooltip.laboratoryblocks.configuration_tool.remove_glowstone").withStyle(TextFormatting.GRAY));
        super.appendHoverText(itemStack, level, tooltipComponents, flag);
    }
}
