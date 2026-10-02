package de.artemis.laboratoryblocks.common.particle;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.SpriteTexturedParticle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class ApplyingGlowstoneParticle extends SpriteTexturedParticle {
    private final float baseQuadSize;

    protected ApplyingGlowstoneParticle(ClientWorld level, double xCoord, double yCoord, double zCoord, IAnimatedSprite spriteSet, Random random, double xd, double yd, double zd) {
        super(level, xCoord, yCoord, zCoord, xd, yd, zd);
        this.pickSprite(spriteSet);

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
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class Provider implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite spriteSet;

        public Provider(IAnimatedSprite spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle createParticle(@NotNull BasicParticleType particleType, @NotNull ClientWorld level, double x, double y, double z, double dx, double dy, double dz) {
            Random random = level.random;
            ApplyingGlowstoneParticle applyingGlowstoneParticle = new ApplyingGlowstoneParticle(level, x, y, z, this.spriteSet, random, dx, dy, dz);
            applyingGlowstoneParticle.setColor(1F, 0.80F, 0.25F);

            return applyingGlowstoneParticle;
        }
    }
}
