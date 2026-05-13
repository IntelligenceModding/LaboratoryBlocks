package de.artemis.laboratoryblocks.common.block;

import de.artemis.laboratoryblocks.common.registry.ModItems;
import de.artemis.laboratoryblocks.common.util.ModUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

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
    protected void neighborChanged(@NotNull BlockState blockState, Level level, @NotNull BlockPos blockPos, @NotNull Block block, Orientation orientation, boolean isMoving) {
        boolean powered = blockState.getValue(POWERED);
        boolean shouldBePowered = level.hasNeighborSignal(blockPos);

        if (!level.isClientSide()) {
            if (powered != shouldBePowered) {
                if (powered) {
                    level.scheduleTick(blockPos, this, 4);
                } else {
                    level.setBlock(blockPos, blockState.cycle(POWERED), 2);
                }
            }
        }
    }

    @Override
    protected void tick(BlockState blockState, @NotNull ServerLevel serverLevel, @NotNull BlockPos blockPos, @NotNull RandomSource randomSource) {
        boolean shouldBePowered = serverLevel.hasNeighborSignal(blockPos);

        if (blockState.getValue(POWERED) && !shouldBePowered) {
            serverLevel.setBlock(blockPos, blockState.cycle(POWERED), 2);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public @NotNull InteractionResult useItemOn(@NotNull ItemStack itemStack, @NotNull BlockState blockState, @NotNull Level level, @NotNull BlockPos blockPos, Player player, @NotNull InteractionHand interactionHand, @NotNull BlockHitResult blockHitResult) {
        ItemStack itemStackInHand = player.getItemInHand(interactionHand);

        if (itemStackInHand.is(ModItems.GLOWSTONE_PARTICLES.get()) || itemStackInHand.is(ModItems.CONFIGURATION_TOOL.get())) {
            // Applying Glowstone
            if (itemStackInHand.is(ModItems.GLOWSTONE_PARTICLES.get()) && !blockState.getBlock().builtInRegistryHolder().unwrapKey().get().toString().contains("glowing")) {
                if (!player.isCreative()) {
                    itemStackInHand.shrink(1);
                }
                level.setBlock(blockPos, copyPoweredState(blockState, glowstoneVariant.get().defaultBlockState()), 3);
                ModUtils.playGlowstoneApplySound(level, blockPos);
                ModUtils.spawnGlowstoneApplyParticles(level, blockHitResult);

                return InteractionResult.SUCCESS;
            }

            // Removing Glowstone
            if (itemStackInHand.is(ModItems.CONFIGURATION_TOOL.get()) && blockState.getBlock().builtInRegistryHolder().unwrapKey().get().toString().contains("glowing")) {
                if (!player.isCreative()) {
                    ModUtils.giveItemToPlayerOrDropAtClickedSide(player, level, blockPos, blockHitResult, new ItemStack(ModItems.GLOWSTONE_PARTICLES.get()));
                    itemStackInHand.hurtAndBreak(1, player, itemStackInHand.getEquipmentSlot());
                }
                level.setBlock(blockPos, copyPoweredState(blockState, glowstoneVariant.get().defaultBlockState()), 3);
                ModUtils.playGlowstoneRemoveSound(level, blockPos);
                ModUtils.spawnGlowstoneRemoveParticles(level, blockHitResult);

                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.FAIL;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(POWERED);
    }

    private static BlockState copyPoweredState(BlockState source, BlockState target) {
        return target.setValue(POWERED, source.getValue(POWERED));
    }
}