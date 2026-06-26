package de.artemis.laboratoryblocks.common.block;

import de.artemis.laboratoryblocks.common.registry.ModItems;
import de.artemis.laboratoryblocks.common.util.LaboratoryWoodSwapUtil;
import de.artemis.laboratoryblocks.common.util.ModUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class LaboratoryBlock extends Block {
    private final Supplier<LaboratoryBlock> block;

    public LaboratoryBlock(Supplier<LaboratoryBlock> block, Properties properties) {
        super(properties);
        this.block = block;
    }

    @SuppressWarnings("deprecation")
    public @NotNull InteractionResult useItemOn(@NotNull ItemStack itemStack, @NotNull BlockState blockState, @NotNull Level level, @NotNull BlockPos blockPos, Player player, @NotNull InteractionHand interactionHand, @NotNull BlockHitResult blockHitResult) {
        ItemStack itemStackInHand = player.getItemInHand(interactionHand);

        if (itemStackInHand.is(ModItems.GLOWSTONE_PARTICLES.get()) || itemStackInHand.is(ModItems.CONFIGURATION_TOOL.get())) {

            // Applying Glowstone
            if (itemStackInHand.is(ModItems.GLOWSTONE_PARTICLES.get()) && !blockState.getBlock().builtInRegistryHolder().unwrapKey().get().toString().contains("glowing")) {
                if (!player.isCreative()) {
                    itemStackInHand.shrink(1);
                }
                level.setBlock(blockPos, block.get().defaultBlockState(), 3);
                ModUtils.playGlowstoneApplySound(level, blockPos);
                ModUtils.spawnGlowstoneApplyParticles(level, blockHitResult);

                return InteractionResult.SUCCESS;
            }

            // Removing Glowstone
            else if (itemStackInHand.is(ModItems.CONFIGURATION_TOOL.get()) && blockState.getBlock().builtInRegistryHolder().unwrapKey().get().toString().contains("glowing")) {
                if (!player.isCreative()) {
                    ModUtils.giveItemToPlayerOrDropAtClickedSide(player, level, blockPos, blockHitResult, new ItemStack(ModItems.GLOWSTONE_PARTICLES.get()));
                    itemStackInHand.hurtAndBreak(1, player, itemStackInHand.getEquipmentSlot());
                }
                level.setBlock(blockPos, block.get().defaultBlockState(), 3);
                ModUtils.playGlowstoneRemoveSound(level, blockPos);
                ModUtils.spawnGlowstoneRemoveParticles(level, blockHitResult);

                return InteractionResult.SUCCESS;
            }
        }

        LaboratoryWoodSwapUtil.SwapResult woodSwap = LaboratoryWoodSwapUtil.getSwap(blockState.getBlock(), itemStackInHand.getItem());
        if (woodSwap != null) {
            if (level.isClientSide()) {
                return InteractionResult.SUCCESS;
            }

            if (!player.isCreative()) {
                itemStackInHand.shrink(1);
                ModUtils.giveItemToPlayerOrDropAtClickedSide(player, level, blockPos, blockHitResult, woodSwap.returnedPlanks());
            }

            BlockState targetState = woodSwap.targetBlock().defaultBlockState();
            level.setBlock(blockPos, targetState, 3);
            float pitch = 0.92F + level.getRandom().nextFloat() * 0.08F;
            level.playSound(null, blockPos, targetState.getSoundType().getPlaceSound(), SoundSource.BLOCKS, 0.7F, pitch);
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.FAIL;
    }
}
