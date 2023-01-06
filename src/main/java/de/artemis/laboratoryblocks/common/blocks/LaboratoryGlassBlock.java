package de.artemis.laboratoryblocks.common.blocks;

import de.artemis.laboratoryblocks.common.registration.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.GlassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class LaboratoryGlassBlock extends GlassBlock {
    private final Supplier<LaboratoryGlassBlock> block;

    public LaboratoryGlassBlock(Supplier<LaboratoryGlassBlock> block, Properties properties) {
        super(properties);
        this.block = block;
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState blockState, @NotNull Level level, @NotNull BlockPos blockPos, Player player, @NotNull InteractionHand interactionHand, @NotNull BlockHitResult blockHitResult) {
        ItemStack itemStackInHand = player.getItemInHand(interactionHand);
        RandomSource randomsource = level.getRandom();

        if (level.isClientSide) {
            if (itemStackInHand.is(Items.GLOWSTONE_DUST) && !blockState.getBlock().builtInRegistryHolder().unwrapKey().get().toString().contains("enlighted")) {
                level.addParticle(ModParticles.APPLYING_GLOWSTONE_PARTICLE.get(), blockPos.getX(), blockPos.getY(), blockPos.getZ(), randomsource.nextGaussian() * 0.025D, randomsource.nextGaussian() * 0.025D, randomsource.nextGaussian() * 0.025D);
                level.addParticle(ModParticles.APPLYING_GLOWSTONE_PARTICLE.get(), blockPos.getX() + 1.0F, blockPos.getY(), blockPos.getZ(), randomsource.nextGaussian() * 0.025D, randomsource.nextGaussian() * 0.025D, randomsource.nextGaussian() * 0.025D);
                level.addParticle(ModParticles.APPLYING_GLOWSTONE_PARTICLE.get(), blockPos.getX() + 1.0F, blockPos.getY() + 1.0F, blockPos.getZ(), randomsource.nextGaussian() * 0.025D, randomsource.nextGaussian() * 0.025D, randomsource.nextGaussian() * 0.025D);
                level.addParticle(ModParticles.APPLYING_GLOWSTONE_PARTICLE.get(), blockPos.getX(), blockPos.getY() + 1.0F, blockPos.getZ(), randomsource.nextGaussian() * 0.025D, randomsource.nextGaussian() * 0.025D, randomsource.nextGaussian() * 0.025D);
                level.addParticle(ModParticles.APPLYING_GLOWSTONE_PARTICLE.get(), blockPos.getX(), blockPos.getY(), blockPos.getZ() + 1.0F, randomsource.nextGaussian() * 0.025D, randomsource.nextGaussian() * 0.025D, randomsource.nextGaussian() * 0.025D);
                level.addParticle(ModParticles.APPLYING_GLOWSTONE_PARTICLE.get(), blockPos.getX() + 1.0F, blockPos.getY(), blockPos.getZ() + 1.0F, randomsource.nextGaussian() * 0.025D, randomsource.nextGaussian() * 0.025D, randomsource.nextGaussian() * 0.025D);
                level.addParticle(ModParticles.APPLYING_GLOWSTONE_PARTICLE.get(), blockPos.getX() + 1.0F, blockPos.getY() + 1.0F, blockPos.getZ() + 1.0F, randomsource.nextGaussian() * 0.025D, randomsource.nextGaussian() * 0.025D, randomsource.nextGaussian() * 0.025D);
                level.addParticle(ModParticles.APPLYING_GLOWSTONE_PARTICLE.get(), blockPos.getX(), blockPos.getY() + 1.0F, blockPos.getZ() + 1.0F, randomsource.nextGaussian() * 0.025D, randomsource.nextGaussian() * 0.025D, randomsource.nextGaussian() * 0.025D);
            } else if (itemStackInHand.is(Tags.Items.TOOLS_PICKAXES) && blockState.getBlock().builtInRegistryHolder().unwrapKey().get().toString().contains("enlighted")) {
                level.addParticle(ModParticles.REMOVING_GLOWSTONE_PARTICLE.get(), blockPos.getX(), blockPos.getY(), blockPos.getZ(), randomsource.nextGaussian() * 0.025D, randomsource.nextGaussian() * 0.025D, randomsource.nextGaussian() * 0.025D);
                level.addParticle(ModParticles.REMOVING_GLOWSTONE_PARTICLE.get(), blockPos.getX() + 1.0F, blockPos.getY(), blockPos.getZ(), randomsource.nextGaussian() * 0.025D, randomsource.nextGaussian() * 0.025D, randomsource.nextGaussian() * 0.025D);
                level.addParticle(ModParticles.REMOVING_GLOWSTONE_PARTICLE.get(), blockPos.getX() + 1.0F, blockPos.getY() + 1.0F, blockPos.getZ(), randomsource.nextGaussian() * 0.025D, randomsource.nextGaussian() * 0.025D, randomsource.nextGaussian() * 0.025D);
                level.addParticle(ModParticles.REMOVING_GLOWSTONE_PARTICLE.get(), blockPos.getX(), blockPos.getY() + 1.0F, blockPos.getZ(), randomsource.nextGaussian() * 0.025D, randomsource.nextGaussian() * 0.025D, randomsource.nextGaussian() * 0.025D);
                level.addParticle(ModParticles.REMOVING_GLOWSTONE_PARTICLE.get(), blockPos.getX(), blockPos.getY(), blockPos.getZ() + 1.0F, randomsource.nextGaussian() * 0.025D, randomsource.nextGaussian() * 0.025D, randomsource.nextGaussian() * 0.025D);
                level.addParticle(ModParticles.REMOVING_GLOWSTONE_PARTICLE.get(), blockPos.getX() + 1.0F, blockPos.getY(), blockPos.getZ() + 1.0F, randomsource.nextGaussian() * 0.025D, randomsource.nextGaussian() * 0.025D, randomsource.nextGaussian() * 0.025D);
                level.addParticle(ModParticles.REMOVING_GLOWSTONE_PARTICLE.get(), blockPos.getX() + 1.0F, blockPos.getY() + 1.0F, blockPos.getZ() + 1.0F, randomsource.nextGaussian() * 0.025D, randomsource.nextGaussian() * 0.025D, randomsource.nextGaussian() * 0.025D);
                level.addParticle(ModParticles.REMOVING_GLOWSTONE_PARTICLE.get(), blockPos.getX(), blockPos.getY() + 1.0F, blockPos.getZ() + 1.0F, randomsource.nextGaussian() * 0.025D, randomsource.nextGaussian() * 0.025D, randomsource.nextGaussian() * 0.025D);
            }
        }

        if (itemStackInHand.is(Items.GLOWSTONE_DUST) || itemStackInHand.is(Tags.Items.TOOLS_PICKAXES)) {
            if (itemStackInHand.is(Items.GLOWSTONE_DUST) && !blockState.getBlock().builtInRegistryHolder().unwrapKey().get().toString().contains("enlighted")) {
                if (!player.isCreative()) {
                    itemStackInHand.shrink(1);
                }
                level.setBlock(blockPos, block.get().defaultBlockState(), 3);
                level.playSound(player, blockPos, SoundEvents.RESPAWN_ANCHOR_CHARGE, SoundSource.BLOCKS, 1.0F, 1.0F);
                return InteractionResult.SUCCESS;
            } else if (itemStackInHand.is(Tags.Items.TOOLS_PICKAXES) && blockState.getBlock().builtInRegistryHolder().unwrapKey().get().toString().contains("enlighted")) {
                if (!player.isCreative()) {
                    if (!player.getInventory().add(new ItemStack(Items.GLOWSTONE_DUST))) {
                        ItemEntity itemEntity = new ItemEntity(level, blockPos.getX() + 0.5F, blockPos.getY() + 1.0F, blockPos.getZ() + 0.5F, new ItemStack(Items.GLOWSTONE_DUST));
                        itemEntity.setDefaultPickUpDelay();
                        level.addFreshEntity(itemEntity);
                    }
                    itemStackInHand.hurt(1, RandomSource.create(), null);
                }
                level.setBlock(blockPos, block.get().defaultBlockState(), 3);
                level.playSound(player, blockPos, SoundEvents.AXE_WAX_OFF, SoundSource.BLOCKS, 1.0F, 1.0F);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.FAIL;
    }
}