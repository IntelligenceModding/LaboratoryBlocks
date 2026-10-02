package de.artemis.laboratoryblocks.common.registry;

import de.artemis.laboratoryblocks.LaboratoryBlocks;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.RegistryObject;

public class ModParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, LaboratoryBlocks.MOD_ID);

    public static final RegistryObject<BasicParticleType> APPLYING_GLOWSTONE_PARTICLE =
            PARTICLE_TYPES.register("applying_glowstone_particle", () -> new BasicParticleType(true));

    public static final RegistryObject<BasicParticleType> REMOVING_MODIFIER_PARTICLE =
            PARTICLE_TYPES.register("removing_modifier_particle", () -> new BasicParticleType(true));

    public static void register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }
}
