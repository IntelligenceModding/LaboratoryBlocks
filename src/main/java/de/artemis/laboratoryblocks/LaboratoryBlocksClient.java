package de.artemis.laboratoryblocks;

import de.artemis.laboratoryblocks.common.particle.ApplyingGlowstoneParticle;
import de.artemis.laboratoryblocks.common.particle.RemovingModifierParticle;
import de.artemis.laboratoryblocks.common.registry.ModParticles;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;

public class LaboratoryBlocksClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ParticleProviderRegistry.getInstance().register(ModParticles.APPLYING_GLOWSTONE_PARTICLE.get(), ApplyingGlowstoneParticle.Provider::new);
        ParticleProviderRegistry.getInstance().register(ModParticles.REMOVING_MODIFIER_PARTICLE.get(), RemovingModifierParticle.Provider::new);
    }
}
