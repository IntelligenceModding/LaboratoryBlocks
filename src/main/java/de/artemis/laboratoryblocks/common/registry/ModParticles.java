package de.artemis.laboratoryblocks.common.registry;

import de.artemis.laboratoryblocks.LaboratoryBlocks;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

public class ModParticles {
    public static final RegistrySupplier<SimpleParticleType> APPLYING_GLOWSTONE_PARTICLE =
            register("applying_glowstone_particle");

    public static final RegistrySupplier<SimpleParticleType> REMOVING_MODIFIER_PARTICLE =
            register("removing_modifier_particle");

    public static void register() {
    }

    private static RegistrySupplier<SimpleParticleType> register(String name) {
        Identifier id = Identifier.fromNamespaceAndPath(LaboratoryBlocks.MOD_ID, name);
        SimpleParticleType particleType = Registry.register(BuiltInRegistries.PARTICLE_TYPE, id, FabricParticleTypes.simple(true));
        return new RegistrySupplier<>(id, particleType);
    }
}
