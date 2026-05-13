package de.artemis.laboratoryblocks.common.registry;

import de.artemis.laboratoryblocks.LaboratoryBlocks;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, LaboratoryBlocks.MOD_ID);

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> APPLYING_GLOWSTONE_PARTICLE =
            PARTICLE_TYPES.register("applying_glowstone_particle", () -> new SimpleParticleType(true));

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> APPLYING_REDSTONE_PARTICLE =
            PARTICLE_TYPES.register("applying_redstone_particle", () -> new SimpleParticleType(true));

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> REMOVING_MODIFIER_PARTICLE =
            PARTICLE_TYPES.register("removing_modifier_particle", () -> new SimpleParticleType(true));

    public static void register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }
}