package de.artemis.laboratoryblocks.client.particle.custom;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import org.jetbrains.annotations.NotNull;

public class RemovingModifierParticle extends SingleQuadParticle {
    protected RemovingModifierParticle(ClientLevel level, double xCoord, double yCoord, double zCoord, SpriteSet spriteSet, RandomSource random, double xd, double yd, double zd) {
        super(level, xCoord, yCoord, zCoord, xd, yd, zd, spriteSet.get(random));

        this.friction = 0.8F;
        this.xd = xd;
        this.yd = yd;
        this.zd = zd;
        this.quadSize *= 1F;
        this.lifetime = 16;
        this.setSpriteFromAge(spriteSet);

        this.rCol = 1f;
        this.gCol = 1f;
        this.bCol = 1f;
    }

    @Override
    public void tick() {
        super.tick();
        fadeOut();
    }

    private  void fadeOut() {
        this.alpha = (-(1/(float)lifetime) * age + 1);
    }

    @NotNull
    @Override
    protected Layer getLayer() {
        return Layer.TRANSLUCENT;
    }

    public static class Provider implements net.minecraft.client.particle.ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public Provider(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle createParticle(@NotNull SimpleParticleType particleType, @NotNull ClientLevel level, double x, double y, double z, double dx, double dy, double dz, RandomSource random) {
            return new RemovingModifierParticle(level, x, y, z, this.spriteSet, random, dx, dy, dz);
        }
    }
}
