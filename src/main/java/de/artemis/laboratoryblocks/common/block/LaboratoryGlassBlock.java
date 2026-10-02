package de.artemis.laboratoryblocks.common.block;

import de.artemis.laboratoryblocks.common.registry.ModItems;
import de.artemis.laboratoryblocks.common.util.ModUtils;
import net.minecraft.block.BlockState;
import net.minecraft.block.GlassBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class LaboratoryGlassBlock extends GlassBlock {
    private final Supplier<LaboratoryGlassBlock> block;

    public LaboratoryGlassBlock(Supplier<LaboratoryGlassBlock> block, Properties properties) {
        super(properties);
        this.block = block;
    }

    @SuppressWarnings("deprecation")
    @Override
    public @NotNull ActionResultType use(@NotNull BlockState blockState, @NotNull World level, @NotNull BlockPos blockPos, PlayerEntity player, @NotNull Hand interactionHand, @NotNull BlockRayTraceResult blockHitResult) {
        ItemStack itemStackInHand = player.getItemInHand(interactionHand);

        if (itemStackInHand.getItem() == ModItems.GLOWSTONE_PARTICLES.get() || itemStackInHand.getItem() == ModItems.CONFIGURATION_TOOL.get()) {
            if (itemStackInHand.getItem() == ModItems.GLOWSTONE_PARTICLES.get() && !blockState.getBlock().getRegistryName().toString().contains("glowing")) {
                if (!level.isClientSide()) {
                    if (!player.isCreative()) {
                        itemStackInHand.shrink(1);
                    }
                    level.setBlock(blockPos, block.get().defaultBlockState(), 3);
                    ModUtils.playGlowstoneApplySound(level, blockPos);
                    ModUtils.spawnGlowstoneApplyParticles(level, blockHitResult);
                }
                return ActionResultType.sidedSuccess(level.isClientSide());
            }

            if (itemStackInHand.getItem() == ModItems.CONFIGURATION_TOOL.get() && blockState.getBlock().getRegistryName().toString().contains("glowing")) {
                if (!level.isClientSide()) {
                    if (!player.isCreative()) {
                        ModUtils.giveItemToPlayerOrDropAtClickedSide(player, level, blockPos, blockHitResult, new ItemStack(ModItems.GLOWSTONE_PARTICLES.get()));
                        itemStackInHand.hurtAndBreak(1, player, entity -> entity.broadcastBreakEvent(interactionHand));
                    }
                    level.setBlock(blockPos, block.get().defaultBlockState(), 3);
                    ModUtils.playGlowstoneRemoveSound(level, blockPos);
                    ModUtils.spawnGlowstoneRemoveParticles(level, blockHitResult);
                }
                return ActionResultType.sidedSuccess(level.isClientSide());
            }
        }
        return ActionResultType.PASS;
    }
}
