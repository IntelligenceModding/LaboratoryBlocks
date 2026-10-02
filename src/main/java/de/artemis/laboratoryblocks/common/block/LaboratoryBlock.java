package de.artemis.laboratoryblocks.common.block;

import de.artemis.laboratoryblocks.common.registry.ModItems;
import de.artemis.laboratoryblocks.common.util.LaboratoryWoodSwapUtil;
import de.artemis.laboratoryblocks.common.util.ModUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class LaboratoryBlock extends Block {
    private final Supplier<LaboratoryBlock> block;

    public LaboratoryBlock(Supplier<LaboratoryBlock> block, Properties properties) {
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

        LaboratoryWoodSwapUtil.SwapResult woodSwap = LaboratoryWoodSwapUtil.getSwap(blockState.getBlock(), itemStackInHand.getItem());
        if (woodSwap != null) {
            if (!level.isClientSide()) {
                if (!player.isCreative()) {
                    itemStackInHand.shrink(1);
                    ModUtils.giveItemToPlayerOrDropAtClickedSide(player, level, blockPos, blockHitResult, woodSwap.returnedPlanks());
                }

                BlockState targetState = woodSwap.targetBlock().defaultBlockState();
                level.setBlock(blockPos, targetState, 3);
                float pitch = 0.92F + level.random.nextFloat() * 0.08F;
                level.playSound(null, blockPos, targetState.getSoundType().getPlaceSound(), SoundCategory.BLOCKS, 0.7F, pitch);
            }
            return ActionResultType.sidedSuccess(level.isClientSide());
        }

        return ActionResultType.PASS;
    }
}
