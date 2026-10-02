package de.artemis.laboratoryblocks.common.util;

import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.jetbrains.annotations.NotNull;

import de.artemis.laboratoryblocks.common.registry.ModParticles;
import de.artemis.laboratoryblocks.common.registry.ModSoundEvents;

public final class ModUtils {

    private static final double SPAWN_OFFSET = 0.62D;
    private static final double OUTWARD_MOTION = 0.04D;
    private static final double PARTICLE_FACE_OFFSET = 0.10D;

    private ModUtils() {
    }

    public static void giveItemToPlayerOrDropAtClickedSide(@NotNull PlayerEntity player, @NotNull World level, @NotNull BlockPos blockPos, @NotNull BlockRayTraceResult hitResult, @NotNull ItemStack itemStack) {
        if (itemStack.isEmpty()) {
            return;
        }

        if (!player.addItem(itemStack.copy())) {
            spawnItemAtClickedSide(level, blockPos, hitResult, itemStack);
        }
    }

    public static void spawnItemAtClickedSide(@NotNull World level, @NotNull BlockPos blockPos, @NotNull BlockRayTraceResult hitResult, @NotNull ItemStack itemStack) {
        if (itemStack.isEmpty() || level.isClientSide()) {
            return;
        }

        Direction face = hitResult.getDirection();
        Vector3d center = Vector3d.atCenterOf(blockPos);

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

    public static void playGlowstoneApplySound(@NotNull World level, @NotNull BlockPos blockPos) {
        playSoftBlockSound(level, blockPos, SoundEvents.RESPAWN_ANCHOR_CHARGE, 0.52F, 0.92F, 0.05F);
    }

    public static void playGlowstoneRemoveSound(@NotNull World level, @NotNull BlockPos blockPos) {
        playSoftBlockSound(level, blockPos, SoundEvents.GRINDSTONE_USE, 0.38F, 0.94F, 0.05F);
        playSoftBlockSound(level, blockPos, ModSoundEvents.CONFIGURATION_TOOL_USE.get(), 0.24F, 0.98F, 0.03F);
    }

    public static void spawnGlowstoneApplyParticles(@NotNull World level, @NotNull BlockRayTraceResult hitResult) {
        spawnSurfacePuff(level, hitResult, ModParticles.APPLYING_GLOWSTONE_PARTICLE.get(), 12, 0.10D, 0.11D, 0.030D, 0.024D, 0.018D);
    }

    public static void spawnGlowstoneRemoveParticles(@NotNull World level, @NotNull BlockRayTraceResult hitResult) {
        spawnSurfacePuff(level, hitResult, ModParticles.REMOVING_MODIFIER_PARTICLE.get(), 10, 0.09D, 0.10D, 0.024D, 0.020D, 0.016D);
    }

    private static void playSoftBlockSound(@NotNull World level, @NotNull BlockPos blockPos, @NotNull SoundEvent soundEvent, float volume, float basePitch, float pitchVariation) {
        float pitch = basePitch + (level.random.nextFloat() * 2.0F - 1.0F) * pitchVariation;
        level.playSound(null, blockPos, soundEvent, SoundCategory.BLOCKS, volume, pitch);
    }

    private static void spawnSurfacePuff(@NotNull World level, @NotNull BlockRayTraceResult hitResult, @NotNull BasicParticleType particleType, int count, double faceOffset, double spread, double outwardMotion, double upwardMotion, double jitterMotion) {
        Direction face = hitResult.getDirection();
        Vector3d normal = new Vector3d(face.getStepX(), face.getStepY(), face.getStepZ());
        Vector3d tangentA = getFirstTangent(face);
        Vector3d tangentB = getSecondTangent(face);
        Vector3d anchor = hitResult.getLocation().add(normal.scale(PARTICLE_FACE_OFFSET + level.random.nextDouble() * faceOffset * 0.25D));
        ServerWorld serverLevel = level instanceof ServerWorld ? (ServerWorld) level : null;

        for (int i = 0; i < count; i++) {
            double offsetA = randomCentered(level) * spread;
            double offsetB = randomCentered(level) * spread;
            double offsetN = level.random.nextDouble() * faceOffset * 0.35D;

            Vector3d position = anchor
                    .add(tangentA.scale(offsetA))
                    .add(tangentB.scale(offsetB))
                    .add(normal.scale(offsetN));
            double motionX = normal.x * (outwardMotion + level.random.nextDouble() * 0.012D)
                    + tangentA.x * randomCentered(level) * jitterMotion
                    + tangentB.x * randomCentered(level) * jitterMotion;
            double motionY = normal.y * (outwardMotion + level.random.nextDouble() * 0.012D)
                    + tangentA.y * randomCentered(level) * jitterMotion
                    + tangentB.y * randomCentered(level) * jitterMotion
                    + upwardMotion
                    + level.random.nextDouble() * 0.012D;
            double motionZ = normal.z * (outwardMotion + level.random.nextDouble() * 0.012D)
                    + tangentA.z * randomCentered(level) * jitterMotion
                    + tangentB.z * randomCentered(level) * jitterMotion;

            if (serverLevel != null) {
                serverLevel.sendParticles(particleType, position.x, position.y, position.z, 1, motionX, motionY, motionZ, 0.0D);
            } else {
                level.addParticle(particleType, position.x, position.y, position.z, motionX, motionY, motionZ);
            }
        }
    }

    private static double randomCentered(@NotNull World level) {
        return level.random.nextDouble() - 0.5D;
    }

    private static @NotNull Vector3d getFirstTangent(@NotNull Direction face) {
        return face.getAxis() == Direction.Axis.X
                ? new Vector3d(0.0D, 1.0D, 0.0D)
                : new Vector3d(1.0D, 0.0D, 0.0D);
    }

    private static @NotNull Vector3d getSecondTangent(@NotNull Direction face) {
        return face.getAxis() == Direction.Axis.Z
                ? new Vector3d(0.0D, 1.0D, 0.0D)
                : new Vector3d(0.0D, 0.0D, 1.0D);
    }
}
