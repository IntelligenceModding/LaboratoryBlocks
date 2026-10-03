package de.artemis.laboratoryblocks.common.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ConfigurationToolItem extends Item {
    public ConfigurationToolItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isEnchantable(@NotNull ItemStack itemStack) {
        return itemStack.getCount() == 1 && itemStack.isDamageableItem();
    }

    @Override
    public int getEnchantmentValue() {
        return 10;
    }

    @Override
    public boolean isValidRepairItem(@NotNull ItemStack itemStack, @NotNull ItemStack repairItem) {
        return repairItem.is(Items.IRON_INGOT);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemStack, Item.@NotNull TooltipContext context, @NotNull List<Component> tooltipComponents, @NotNull TooltipFlag flag) {
        tooltipComponents.add(Component.translatable("tooltip.laboratoryblocks.configuration_tool.remove_glowstone").withStyle(ChatFormatting.GRAY));
        super.appendHoverText(itemStack, context, tooltipComponents, flag);
    }
}
