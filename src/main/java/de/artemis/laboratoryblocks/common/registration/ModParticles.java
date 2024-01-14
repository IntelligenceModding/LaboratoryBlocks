package de.artemis.laboratoryblocks.common.registration;

import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.RegistryObject;

import static de.artemis.laboratoryblocks.common.registration.Registration.PARTICLE_TYPES;

public class ModParticles {

    public static final RegistryObject<SimpleParticleType> APPLYING_GLOWSTONE_PARTICLE =
            PARTICLE_TYPES.register("applying_glowstone_particle", () -> new SimpleParticleType(true));

    public static final RegistryObject<SimpleParticleType> APPLYING_REDSTONE_PARTICLE =
            PARTICLE_TYPES.register("applying_redstone_particle", () -> new SimpleParticleType(true));

    public static final RegistryObject<SimpleParticleType> REMOVING_MODIFIER_PARTICLE =
            PARTICLE_TYPES.register("removing_modifier_particle", () -> new SimpleParticleType(true));

    public static void register() {
    }
}
