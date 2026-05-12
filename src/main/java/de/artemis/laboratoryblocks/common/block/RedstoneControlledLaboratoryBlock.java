package de.artemis.laboratoryblocks.common.block;

import de.artemis.laboratoryblocks.common.registry.ModItems;
import de.artemis.laboratoryblocks.client.ModKeyBindings;
import de.artemis.laboratoryblocks.common.registry.ModParticles;
import de.artemis.laboratoryblocks.common.registry.ModSoundEvents;
import de.artemis.laboratoryblocks.common.util.KeyBindingUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
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
    private final Supplier<RedstoneControlledLaboratoryBlock> glowstone_block;
    private final Supplier<RedstoneControlledLaboratoryBlock> redstone_block;
    public static final BooleanProperty POWERED = BooleanProperty.create("powered");
    public static final BooleanProperty INVERTED = BooleanProperty.create("inverted");

    public RedstoneControlledLaboratoryBlock(Supplier<RedstoneControlledLaboratoryBlock> glowstone_block, Supplier<RedstoneControlledLaboratoryBlock> redstone_block, Properties properties) {
        super(properties);
        this.glowstone_block = glowstone_block;
        this.redstone_block = redstone_block;
        this.registerDefaultState(this.defaultBlockState().setValue(POWERED, false).setValue(INVERTED, false));
    }

    @Override
    protected void neighborChanged(@NotNull BlockState blockState, Level level, @NotNull BlockPos blockPos, @NotNull Block block, Orientation orientation, boolean isMoving) {
        boolean inverted = blockState.getValue(INVERTED);
        boolean powered = blockState.getValue(POWERED);
        boolean shouldBePowered = level.hasNeighborSignal(blockPos);

        if (inverted) {
            shouldBePowered = !shouldBePowered;
        }

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
        boolean inverted = blockState.getValue(INVERTED);
        boolean shouldBePowered = serverLevel.hasNeighborSignal(blockPos);

        if (inverted) {
            shouldBePowered = !shouldBePowered;
        }

        if (blockState.getValue(POWERED) && !shouldBePowered) {
            serverLevel.setBlock(blockPos, blockState.cycle(POWERED), 2);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public @NotNull InteractionResult useItemOn(@NotNull ItemStack itemStack, @NotNull BlockState blockState, @NotNull Level level, @NotNull BlockPos blockPos, Player player, @NotNull InteractionHand interactionHand, @NotNull BlockHitResult blockHitResult) {
        ItemStack itemStackInHand = player.getItemInHand(interactionHand);

        if (itemStackInHand.is(ModItems.GLOWSTONE_PARTICLES.get()) || itemStackInHand.is(ModItems.CONFIGURATION_TOOL.get()) || itemStackInHand.is(ModItems.REDSTONE_PARTICLES.get())) {

            // Reversing Redstone Control
            if (itemStackInHand.is(ModItems.CONFIGURATION_TOOL.get()) && blockState.getBlock().builtInRegistryHolder().unwrapKey().get().toString().contains("redstone")) {
                BlockState updatedState = blockState.cycle(INVERTED);
                level.setBlock(blockPos, updatedState, 2);

                boolean powered = updatedState.getValue(POWERED);
                boolean shouldBePowered = level.hasNeighborSignal(blockPos);
                if (updatedState.getValue(INVERTED)) {
                    shouldBePowered = !shouldBePowered;
                }

                if (powered != shouldBePowered) {
                    if (powered) {
                        level.scheduleTick(blockPos, this, 4);
                    } else {
                        level.setBlock(blockPos, updatedState.cycle(POWERED), 2);
                    }
                }

                level.playSound(player, blockPos, ModSoundEvents.CONFIGURATION_TOOL_USE.get(), SoundSource.BLOCKS, 1.0F, 1.0F);

                for (float i = 0; i <= 1; i += 0.2F) {
                    level.addParticle(ModParticles.APPLYING_REDSTONE_PARTICLE.get(), blockPos.getX() + i, blockPos.getY(), blockPos.getZ(), 0, 0, 0);
                    level.addParticle(ModParticles.APPLYING_REDSTONE_PARTICLE.get(), blockPos.getX() + i, blockPos.getY() + 1, blockPos.getZ(), 0, 0, 0);
                    level.addParticle(ModParticles.APPLYING_REDSTONE_PARTICLE.get(), blockPos.getX(), blockPos.getY() + i, blockPos.getZ(), 0, 0, 0);
                    level.addParticle(ModParticles.APPLYING_REDSTONE_PARTICLE.get(), blockPos.getX() + 1, blockPos.getY() + i, blockPos.getZ(), 0, 0, 0);
                    level.addParticle(ModParticles.APPLYING_REDSTONE_PARTICLE.get(), blockPos.getX() + i, blockPos.getY(), blockPos.getZ() + 1, 0, 0, 0);
                    level.addParticle(ModParticles.APPLYING_REDSTONE_PARTICLE.get(), blockPos.getX() + i, blockPos.getY() + 1, blockPos.getZ() + 1, 0, 0, 0);
                    level.addParticle(ModParticles.APPLYING_REDSTONE_PARTICLE.get(), blockPos.getX(), blockPos.getY() + i, blockPos.getZ() + 1, 0, 0, 0);
                    level.addParticle(ModParticles.APPLYING_REDSTONE_PARTICLE.get(), blockPos.getX() + 1, blockPos.getY() + i, blockPos.getZ() + 1, 0, 0, 0);
                    level.addParticle(ModParticles.APPLYING_REDSTONE_PARTICLE.get(), blockPos.getX(), blockPos.getY(), blockPos.getZ() + i, 0, 0, 0);
                    level.addParticle(ModParticles.APPLYING_REDSTONE_PARTICLE.get(), blockPos.getX() + 1, blockPos.getY(), blockPos.getZ() + i, 0, 0, 0);
                    level.addParticle(ModParticles.APPLYING_REDSTONE_PARTICLE.get(), blockPos.getX(), blockPos.getY() + 1, blockPos.getZ() + i, 0, 0, 0);
                    level.addParticle(ModParticles.APPLYING_REDSTONE_PARTICLE.get(), blockPos.getX() + 1, blockPos.getY() + 1, blockPos.getZ() + i, 0, 0, 0);
                }

                return InteractionResult.SUCCESS;

            }

            // Applying Glowstone
            if (itemStackInHand.is(ModItems.GLOWSTONE_PARTICLES.get()) && !blockState.getBlock().builtInRegistryHolder().unwrapKey().get().toString().contains("glowing")) {
                if (!player.isCreative()) {
                    itemStackInHand.shrink(1);
                }
                level.setBlock(blockPos, glowstone_block.get().defaultBlockState(), 3);
                level.playSound(player, blockPos, SoundEvents.RESPAWN_ANCHOR_CHARGE, SoundSource.BLOCKS, 1.0F, 1.0F);

                for (float i = 0; i <= 1; i += 0.2F) {
                    level.addParticle(ModParticles.APPLYING_GLOWSTONE_PARTICLE.get(), blockPos.getX() + i, blockPos.getY(), blockPos.getZ(), 0, 0, 0);
                    level.addParticle(ModParticles.APPLYING_GLOWSTONE_PARTICLE.get(), blockPos.getX() + i, blockPos.getY() + 1, blockPos.getZ(), 0, 0, 0);
                    level.addParticle(ModParticles.APPLYING_GLOWSTONE_PARTICLE.get(), blockPos.getX(), blockPos.getY() + i, blockPos.getZ(), 0, 0, 0);
                    level.addParticle(ModParticles.APPLYING_GLOWSTONE_PARTICLE.get(), blockPos.getX() + 1, blockPos.getY() + i, blockPos.getZ(), 0, 0, 0);
                    level.addParticle(ModParticles.APPLYING_GLOWSTONE_PARTICLE.get(), blockPos.getX() + i, blockPos.getY(), blockPos.getZ() + 1, 0, 0, 0);
                    level.addParticle(ModParticles.APPLYING_GLOWSTONE_PARTICLE.get(), blockPos.getX() + i, blockPos.getY() + 1, blockPos.getZ() + 1, 0, 0, 0);
                    level.addParticle(ModParticles.APPLYING_GLOWSTONE_PARTICLE.get(), blockPos.getX(), blockPos.getY() + i, blockPos.getZ() + 1, 0, 0, 0);
                    level.addParticle(ModParticles.APPLYING_GLOWSTONE_PARTICLE.get(), blockPos.getX() + 1, blockPos.getY() + i, blockPos.getZ() + 1, 0, 0, 0);
                    level.addParticle(ModParticles.APPLYING_GLOWSTONE_PARTICLE.get(), blockPos.getX(), blockPos.getY(), blockPos.getZ() + i, 0, 0, 0);
                    level.addParticle(ModParticles.APPLYING_GLOWSTONE_PARTICLE.get(), blockPos.getX() + 1, blockPos.getY(), blockPos.getZ() + i, 0, 0, 0);
                    level.addParticle(ModParticles.APPLYING_GLOWSTONE_PARTICLE.get(), blockPos.getX(), blockPos.getY() + 1, blockPos.getZ() + i, 0, 0, 0);
                    level.addParticle(ModParticles.APPLYING_GLOWSTONE_PARTICLE.get(), blockPos.getX() + 1, blockPos.getY() + 1, blockPos.getZ() + i, 0, 0, 0);
                }

                return InteractionResult.SUCCESS;
            }

            //Applying Redstone
            if (itemStackInHand.is(ModItems.REDSTONE_PARTICLES.get()) && !blockState.getBlock().builtInRegistryHolder().unwrapKey().get().toString().contains("redstone")) {
                if (!player.isCreative()) {
                    itemStackInHand.shrink(1);
                }
                level.setBlock(blockPos, redstone_block.get().defaultBlockState(), 3);
                level.playSound(player, blockPos, SoundEvents.BONE_BLOCK_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);

                for (float i = 0; i <= 1; i += 0.2F) {
                    level.addParticle(ModParticles.APPLYING_REDSTONE_PARTICLE.get(), blockPos.getX() + i, blockPos.getY(), blockPos.getZ(), 0, 0, 0);
                    level.addParticle(ModParticles.APPLYING_REDSTONE_PARTICLE.get(), blockPos.getX() + i, blockPos.getY() + 1, blockPos.getZ(), 0, 0, 0);
                    level.addParticle(ModParticles.APPLYING_REDSTONE_PARTICLE.get(), blockPos.getX(), blockPos.getY() + i, blockPos.getZ(), 0, 0, 0);
                    level.addParticle(ModParticles.APPLYING_REDSTONE_PARTICLE.get(), blockPos.getX() + 1, blockPos.getY() + i, blockPos.getZ(), 0, 0, 0);
                    level.addParticle(ModParticles.APPLYING_REDSTONE_PARTICLE.get(), blockPos.getX() + i, blockPos.getY(), blockPos.getZ() + 1, 0, 0, 0);
                    level.addParticle(ModParticles.APPLYING_REDSTONE_PARTICLE.get(), blockPos.getX() + i, blockPos.getY() + 1, blockPos.getZ() + 1, 0, 0, 0);
                    level.addParticle(ModParticles.APPLYING_REDSTONE_PARTICLE.get(), blockPos.getX(), blockPos.getY() + i, blockPos.getZ() + 1, 0, 0, 0);
                    level.addParticle(ModParticles.APPLYING_REDSTONE_PARTICLE.get(), blockPos.getX() + 1, blockPos.getY() + i, blockPos.getZ() + 1, 0, 0, 0);
                    level.addParticle(ModParticles.APPLYING_REDSTONE_PARTICLE.get(), blockPos.getX(), blockPos.getY(), blockPos.getZ() + i, 0, 0, 0);
                    level.addParticle(ModParticles.APPLYING_REDSTONE_PARTICLE.get(), blockPos.getX() + 1, blockPos.getY(), blockPos.getZ() + i, 0, 0, 0);
                    level.addParticle(ModParticles.APPLYING_REDSTONE_PARTICLE.get(), blockPos.getX(), blockPos.getY() + 1, blockPos.getZ() + i, 0, 0, 0);
                    level.addParticle(ModParticles.APPLYING_REDSTONE_PARTICLE.get(), blockPos.getX() + 1, blockPos.getY() + 1, blockPos.getZ() + i, 0, 0, 0);
                }

                return InteractionResult.SUCCESS;
            }

            // Removing Glowstone
            if (itemStackInHand.is(ModItems.CONFIGURATION_TOOL.get()) && blockState.getBlock().builtInRegistryHolder().unwrapKey().get().toString().contains("glowing") && KeyBindingUtil.isKeyPressed(ModKeyBindings.REMOVE_GLOWSTONE_CONFIGURATION_TOOL_ACTION)) {
                if (!player.isCreative()) {
                    if (!player.getInventory().add(new ItemStack(ModItems.GLOWSTONE_PARTICLES.get()))) {
                        ItemEntity itemEntity = new ItemEntity(level, blockPos.getX() + 0.5F, blockPos.getY() + 1.0F, blockPos.getZ() + 0.5F, new ItemStack(ModItems.GLOWSTONE_PARTICLES.get()));
                        itemEntity.setDefaultPickUpDelay();
                        level.addFreshEntity(itemEntity);
                    }

                    itemStackInHand.hurtAndBreak(1, player, itemStackInHand.getEquipmentSlot());
                }
                level.setBlock(blockPos, glowstone_block.get().defaultBlockState(), 3);
                level.playSound(player, blockPos, SoundEvents.AXE_WAX_OFF, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.playSound(player, blockPos, ModSoundEvents.CONFIGURATION_TOOL_USE.get(), SoundSource.BLOCKS, 1.0F, 1.0F);

                for (float i = 0; i <= 1; i += 0.2F) {
                    level.addParticle(ModParticles.REMOVING_MODIFIER_PARTICLE.get(), blockPos.getX() + i, blockPos.getY(), blockPos.getZ(), 0, 0, 0);
                    level.addParticle(ModParticles.REMOVING_MODIFIER_PARTICLE.get(), blockPos.getX() + i, blockPos.getY() + 1, blockPos.getZ(), 0, 0, 0);
                    level.addParticle(ModParticles.REMOVING_MODIFIER_PARTICLE.get(), blockPos.getX(), blockPos.getY() + i, blockPos.getZ(), 0, 0, 0);
                    level.addParticle(ModParticles.REMOVING_MODIFIER_PARTICLE.get(), blockPos.getX() + 1, blockPos.getY() + i, blockPos.getZ(), 0, 0, 0);
                    level.addParticle(ModParticles.REMOVING_MODIFIER_PARTICLE.get(), blockPos.getX() + i, blockPos.getY(), blockPos.getZ() + 1, 0, 0, 0);
                    level.addParticle(ModParticles.REMOVING_MODIFIER_PARTICLE.get(), blockPos.getX() + i, blockPos.getY() + 1, blockPos.getZ() + 1, 0, 0, 0);
                    level.addParticle(ModParticles.REMOVING_MODIFIER_PARTICLE.get(), blockPos.getX(), blockPos.getY() + i, blockPos.getZ() + 1, 0, 0, 0);
                    level.addParticle(ModParticles.REMOVING_MODIFIER_PARTICLE.get(), blockPos.getX() + 1, blockPos.getY() + i, blockPos.getZ() + 1, 0, 0, 0);
                    level.addParticle(ModParticles.REMOVING_MODIFIER_PARTICLE.get(), blockPos.getX(), blockPos.getY(), blockPos.getZ() + i, 0, 0, 0);
                    level.addParticle(ModParticles.REMOVING_MODIFIER_PARTICLE.get(), blockPos.getX() + 1, blockPos.getY(), blockPos.getZ() + i, 0, 0, 0);
                    level.addParticle(ModParticles.REMOVING_MODIFIER_PARTICLE.get(), blockPos.getX(), blockPos.getY() + 1, blockPos.getZ() + i, 0, 0, 0);
                    level.addParticle(ModParticles.REMOVING_MODIFIER_PARTICLE.get(), blockPos.getX() + 1, blockPos.getY() + 1, blockPos.getZ() + i, 0, 0, 0);
                }

                return InteractionResult.SUCCESS;
            }

            // Removing Redstone
            if (itemStackInHand.is(ModItems.CONFIGURATION_TOOL.get()) && blockState.getBlock().builtInRegistryHolder().unwrapKey().get().toString().contains("redstone") && KeyBindingUtil.isKeyPressed(ModKeyBindings.REMOVE_REDSTONE_CONFIGURATION_TOOL_ACTION)) {
                if (!player.isCreative()) {
                    if (!player.getInventory().add(new ItemStack(ModItems.REDSTONE_PARTICLES.get()))) {
                        ItemEntity itemEntity = new ItemEntity(level, blockPos.getX() + 0.5F, blockPos.getY() + 1.0F, blockPos.getZ() + 0.5F, new ItemStack(ModItems.REDSTONE_PARTICLES.get()));
                        itemEntity.setDefaultPickUpDelay();
                        level.addFreshEntity(itemEntity);
                    }

                    itemStackInHand.hurtAndBreak(1, player, itemStackInHand.getEquipmentSlot());
                }
                level.setBlock(blockPos, redstone_block.get().defaultBlockState(), 3);
                level.playSound(player, blockPos, SoundEvents.BONE_BLOCK_BREAK, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.playSound(player, blockPos, ModSoundEvents.CONFIGURATION_TOOL_USE.get(), SoundSource.BLOCKS, 1.0F, 1.0F);

                for (float i = 0; i <= 1; i += 0.2F) {
                    level.addParticle(ModParticles.REMOVING_MODIFIER_PARTICLE.get(), blockPos.getX() + i, blockPos.getY(), blockPos.getZ(), 0, 0, 0);
                    level.addParticle(ModParticles.REMOVING_MODIFIER_PARTICLE.get(), blockPos.getX() + i, blockPos.getY() + 1, blockPos.getZ(), 0, 0, 0);
                    level.addParticle(ModParticles.REMOVING_MODIFIER_PARTICLE.get(), blockPos.getX(), blockPos.getY() + i, blockPos.getZ(), 0, 0, 0);
                    level.addParticle(ModParticles.REMOVING_MODIFIER_PARTICLE.get(), blockPos.getX() + 1, blockPos.getY() + i, blockPos.getZ(), 0, 0, 0);
                    level.addParticle(ModParticles.REMOVING_MODIFIER_PARTICLE.get(), blockPos.getX() + i, blockPos.getY(), blockPos.getZ() + 1, 0, 0, 0);
                    level.addParticle(ModParticles.REMOVING_MODIFIER_PARTICLE.get(), blockPos.getX() + i, blockPos.getY() + 1, blockPos.getZ() + 1, 0, 0, 0);
                    level.addParticle(ModParticles.REMOVING_MODIFIER_PARTICLE.get(), blockPos.getX(), blockPos.getY() + i, blockPos.getZ() + 1, 0, 0, 0);
                    level.addParticle(ModParticles.REMOVING_MODIFIER_PARTICLE.get(), blockPos.getX() + 1, blockPos.getY() + i, blockPos.getZ() + 1, 0, 0, 0);
                    level.addParticle(ModParticles.REMOVING_MODIFIER_PARTICLE.get(), blockPos.getX(), blockPos.getY(), blockPos.getZ() + i, 0, 0, 0);
                    level.addParticle(ModParticles.REMOVING_MODIFIER_PARTICLE.get(), blockPos.getX() + 1, blockPos.getY(), blockPos.getZ() + i, 0, 0, 0);
                    level.addParticle(ModParticles.REMOVING_MODIFIER_PARTICLE.get(), blockPos.getX(), blockPos.getY() + 1, blockPos.getZ() + i, 0, 0, 0);
                    level.addParticle(ModParticles.REMOVING_MODIFIER_PARTICLE.get(), blockPos.getX() + 1, blockPos.getY() + 1, blockPos.getZ() + i, 0, 0, 0);
                }

                return InteractionResult.SUCCESS;

            }
        }

        return InteractionResult.FAIL;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(POWERED).add(INVERTED);
    }
}