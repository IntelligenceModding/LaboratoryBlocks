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

public class RemovingModifierParticle extends SpriteTexturedParticle {
    private final float baseQuadSize;

    protected RemovingModifierParticle(ClientWorld level, double xCoord, double yCoord, double zCoord, IAnimatedSprite spriteSet, Random random, double xd, double yd, double zd) {
        super(level, xCoord, yCoord, zCoord, xd, yd, zd);
        this.pickSprite(spriteSet);

        this.hasPhysics = false;
        this.xd = xd;
        this.yd = yd;
        this.zd = zd;
        this.quadSize *= 1.52F + random.nextFloat() * 0.44F;
        this.baseQuadSize = this.quadSize;
        this.lifetime = 8 + random.nextInt(4);
        this.alpha = 0.72F;

        this.rCol = 0.92F;
        this.gCol = 0.92F;
        this.bCol = 0.96F;
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
        this.alpha = 0.72F * remaining * remaining;
        this.quadSize = this.baseQuadSize * (0.95F + remaining * 0.08F);
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
            return new RemovingModifierParticle(level, x, y, z, this.spriteSet, random, dx, dy, dz);
        }
    }
}
