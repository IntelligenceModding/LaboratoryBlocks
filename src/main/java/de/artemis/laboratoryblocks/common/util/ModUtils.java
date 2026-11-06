package de.artemis.laboratoryblocks.common.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import de.artemis.laboratoryblocks.common.registry.ModParticles;
import de.artemis.laboratoryblocks.common.registry.ModSoundEvents;

public final class ModUtils {

    private static final double SPAWN_OFFSET = 0.62D;
    private static final double OUTWARD_MOTION = 0.04D;
    private static final double PARTICLE_FACE_OFFSET = 0.10D;

    private ModUtils() {
    }

    public static void giveItemToPlayerOrDropAtClickedSide(@NotNull Player player, @NotNull Level level, @NotNull BlockPos blockPos, @NotNull BlockHitResult hitResult, @NotNull ItemStack itemStack) {
        if (itemStack.isEmpty()) {
            return;
        }

        if (!player.getInventory().add(itemStack.copy())) {
            spawnItemAtClickedSide(level, blockPos, hitResult, itemStack);
        }
    }

    public static void spawnItemAtClickedSide(@NotNull Level level, @NotNull BlockPos blockPos, @NotNull BlockHitResult hitResult, @NotNull ItemStack itemStack) {
        if (itemStack.isEmpty() || level.isClientSide()) {
            return;
        }

        Direction face = hitResult.getDirection();
        Vec3 center = Vec3.atCenterOf(blockPos);

        double x = center.x + face.getStepX() * SPAWN_OFFSET;
        double y = center.y + face.getStepY() * SPAWN_OFFSET;
        double z = center.z + face.getStepZ() * SPAWN_OFFSET;

        ItemEntity itemEntity = new ItemEntity(level, x, y, z, itemStack.copy());
        itemEntity.setDeltaMovement(
                face.getStepX() * OUTWARD_MOTION,
                face == Direction.UP ? OUTWARD_MOTION : 0.0D,
                face.getStepZ() * OUTWARD_MOTION
        );

        level.addFreshEntity(itemEntity);
    }

    public static void playGlowstoneApplySound(@NotNull Level level, @NotNull BlockPos blockPos) {
        playSoftBlockSound(level, blockPos, SoundEvents.RESPAWN_ANCHOR_CHARGE, 0.52F, 0.92F, 0.05F);
    }

    public static void playGlowstoneRemoveSound(@NotNull Level level, @NotNull BlockPos blockPos) {
        playSoftBlockSound(level, blockPos, SoundEvents.AXE_WAX_OFF, 0.38F, 0.94F, 0.05F);
        playSoftBlockSound(level, blockPos, ModSoundEvents.CONFIGURATION_TOOL_USE.get(), 0.24F, 0.98F, 0.03F);
    }

    public static void spawnGlowstoneApplyParticles(@NotNull Level level, @NotNull BlockHitResult hitResult) {
        spawnSurfacePuff(level, hitResult, ModParticles.APPLYING_GLOWSTONE_PARTICLE.get(), 12, 0.10D, 0.11D, 0.030D, 0.024D, 0.018D);
    }

    public static void spawnGlowstoneRemoveParticles(@NotNull Level level, @NotNull BlockHitResult hitResult) {
        spawnSurfacePuff(level, hitResult, ModParticles.REMOVING_MODIFIER_PARTICLE.get(), 10, 0.09D, 0.10D, 0.024D, 0.020D, 0.016D);
    }

    private static void playSoftBlockSound(@NotNull Level level, @NotNull BlockPos blockPos, @NotNull SoundEvent soundEvent, float volume, float basePitch, float pitchVariation) {
        float pitch = basePitch + (level.getRandom().nextFloat() * 2.0F - 1.0F) * pitchVariation;
        level.playSound(null, blockPos, soundEvent, SoundSource.BLOCKS, volume, pitch);
    }

    private static void spawnSurfacePuff(@NotNull Level level, @NotNull BlockHitResult hitResult, @NotNull SimpleParticleType particleType, int count, double faceOffset, double spread, double outwardMotion, double upwardMotion, double jitterMotion) {
        Direction face = hitResult.getDirection();
        Vec3 normal = new Vec3(face.getStepX(), face.getStepY(), face.getStepZ());
        Vec3 tangentA = getFirstTangent(face);
        Vec3 tangentB = getSecondTangent(face);
        Vec3 anchor = hitResult.getLocation().add(normal.scale(PARTICLE_FACE_OFFSET + level.getRandom().nextDouble() * faceOffset * 0.25D));

        for (int i = 0; i < count; i++) {
            double offsetA = randomCentered(level) * spread;
            double offsetB = randomCentered(level) * spread;
            double offsetN = level.getRandom().nextDouble() * faceOffset * 0.35D;

            Vec3 position = anchor
                    .add(tangentA.scale(offsetA))
                    .add(tangentB.scale(offsetB))
                    .add(normal.scale(offsetN));
            double motionX = normal.x * (outwardMotion + level.getRandom().nextDouble() * 0.012D)
                    + tangentA.x * randomCentered(level) * jitterMotion
                    + tangentB.x * randomCentered(level) * jitterMotion;
            double motionY = normal.y * (outwardMotion + level.getRandom().nextDouble() * 0.012D)
                    + tangentA.y * randomCentered(level) * jitterMotion
                    + tangentB.y * randomCentered(level) * jitterMotion
                    + upwardMotion
                    + level.getRandom().nextDouble() * 0.012D;
            double motionZ = normal.z * (outwardMotion + level.getRandom().nextDouble() * 0.012D)
                    + tangentA.z * randomCentered(level) * jitterMotion
                    + tangentB.z * randomCentered(level) * jitterMotion;

            level.addParticle(particleType, position.x, position.y, position.z, motionX, motionY, motionZ);
        }
    }

    private static double randomCentered(@NotNull Level level) {
        return level.getRandom().nextDouble() - 0.5D;
    }

    private static @NotNull Vec3 getFirstTangent(@NotNull Direction face) {
        return face.getAxis() == Direction.Axis.X
                ? new Vec3(0.0D, 1.0D, 0.0D)
                : new Vec3(1.0D, 0.0D, 0.0D);
    }

    private static @NotNull Vec3 getSecondTangent(@NotNull Direction face) {
        return face.getAxis() == Direction.Axis.Z
                ? new Vec3(0.0D, 1.0D, 0.0D)
                : new Vec3(0.0D, 0.0D, 1.0D);
    }
}
