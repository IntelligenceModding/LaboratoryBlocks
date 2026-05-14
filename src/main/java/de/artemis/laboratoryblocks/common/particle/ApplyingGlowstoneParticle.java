package de.artemis.laboratoryblocks.common.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import org.jetbrains.annotations.NotNull;

public class ApplyingGlowstoneParticle extends TextureSheetParticle {
    private final float baseQuadSize;

    protected ApplyingGlowstoneParticle(ClientLevel level, double xCoord, double yCoord, double zCoord, SpriteSet spriteSet, RandomSource random, double xd, double yd, double zd) {
        super(level, xCoord, yCoord, zCoord, xd, yd, zd);
        this.setSprite(spriteSet.get(random));

        this.friction = 0.90F;
        this.hasPhysics = false;
        this.xd = xd;
        this.yd = yd;
        this.zd = zd;
        this.quadSize *= 1.80F + random.nextFloat() * 0.56F;
        this.baseQuadSize = this.quadSize;
        this.lifetime = 10 + random.nextInt(5);
        this.alpha = 0.90F;

        this.rCol = 1f;
        this.gCol = 1f;
        this.bCol = 1f;
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.removed) {
            fadeOut();
        }
    }

    private void fadeOut() {
        float progress = this.age / (float) this.lifetime;
        float remaining = 1.0F - progress;
        this.alpha = 0.90F * remaining * remaining;
        this.quadSize = this.baseQuadSize * (0.88F + remaining * 0.22F);
    }

    @NotNull
    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class Provider implements net.minecraft.client.particle.ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public Provider(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle createParticle(@NotNull SimpleParticleType particleType, @NotNull ClientLevel level, double x, double y, double z, double dx, double dy, double dz) {
            RandomSource random = level.random;
            ApplyingGlowstoneParticle applyingGlowstoneParticle = new ApplyingGlowstoneParticle(level, x, y, z, this.spriteSet, random, dx, dy, dz);
            applyingGlowstoneParticle.setColor(1F, 0.80F, 0.25F);

            return applyingGlowstoneParticle;
        }
    }
}