package de.artemis.laboratoryblocks.common.block;

import de.artemis.laboratoryblocks.common.registry.ModItems;
import de.artemis.laboratoryblocks.common.util.ModUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer;
import net.minecraft.state.BooleanProperty;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.jetbrains.annotations.NotNull;

import java.util.Random;
import java.util.function.Supplier;

public class RedstoneControlledLaboratoryBlock extends Block {
    private final Supplier<RedstoneControlledLaboratoryBlock> glowstoneVariant;
    public static final BooleanProperty POWERED = BooleanProperty.create("powered");

    public RedstoneControlledLaboratoryBlock(Supplier<RedstoneControlledLaboratoryBlock> glowstoneVariant, Properties properties) {
        super(properties);
        this.glowstoneVariant = glowstoneVariant;
        this.registerDefaultState(this.defaultBlockState().setValue(POWERED, false));
    }

    @Override
    public void neighborChanged(@NotNull BlockState blockState, World level, @NotNull BlockPos blockPos, @NotNull Block block, @NotNull BlockPos neighborPos, boolean isMoving) {
        boolean powered = blockState.getValue(POWERED);
        boolean shouldBePowered = level.hasNeighborSignal(blockPos);

        if (!level.isClientSide()) {
            if (powered != shouldBePowered) {
                if (powered) {
                    level.getBlockTicks().scheduleTick(blockPos, this, 4);
                } else {
                    level.setBlock(blockPos, blockState.cycle(POWERED), 2);
                }
            }
        }
    }

    @Override
    public void tick(BlockState blockState, @NotNull ServerWorld serverLevel, @NotNull BlockPos blockPos, @NotNull Random random) {
        boolean shouldBePowered = serverLevel.hasNeighborSignal(blockPos);

        if (blockState.getValue(POWERED) && !shouldBePowered) {
            serverLevel.setBlock(blockPos, blockState.cycle(POWERED), 2);
        }
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
                    level.setBlock(blockPos, copyPoweredState(blockState, glowstoneVariant.get().defaultBlockState()), 3);
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
                    level.setBlock(blockPos, copyPoweredState(blockState, glowstoneVariant.get().defaultBlockState()), 3);
                    ModUtils.playGlowstoneRemoveSound(level, blockPos);
                    ModUtils.spawnGlowstoneRemoveParticles(level, blockHitResult);
                }
                return ActionResultType.sidedSuccess(level.isClientSide());
            }
        }

        return ActionResultType.PASS;
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(POWERED);
    }

    private static BlockState copyPoweredState(BlockState source, BlockState target) {
        return target.setValue(POWERED, source.getValue(POWERED));
    }
}
