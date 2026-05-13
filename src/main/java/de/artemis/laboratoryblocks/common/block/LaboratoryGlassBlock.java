package de.artemis.laboratoryblocks.common.block;

import de.artemis.laboratoryblocks.common.registry.ModItems;
import de.artemis.laboratoryblocks.common.util.ModUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.TransparentBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class LaboratoryGlassBlock extends TransparentBlock {
    private final Supplier<LaboratoryGlassBlock> block;

    public LaboratoryGlassBlock(Supplier<LaboratoryGlassBlock> block, Properties properties) {
        super(properties);
        this.block = block;
    }

    @SuppressWarnings("deprecation")
    @Override
    public @NotNull InteractionResult useItemOn(@NotNull ItemStack itemStack, @NotNull BlockState blockState, @NotNull Level level, @NotNull BlockPos blockPos, Player player, @NotNull InteractionHand interactionHand, @NotNull BlockHitResult blockHitResult) {
        ItemStack itemStackInHand = player.getItemInHand(interactionHand);

        if (itemStackInHand.is(ModItems.GLOWSTONE_PARTICLES.get()) || itemStackInHand.is(ModItems.CONFIGURATION_TOOL.get())) {

            //Applying Glowstone
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
        return InteractionResult.FAIL;
    }
}