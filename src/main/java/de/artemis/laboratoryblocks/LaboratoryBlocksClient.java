package de.artemis.laboratoryblocks;

import de.artemis.laboratoryblocks.common.particle.ApplyingGlowstoneParticle;
import de.artemis.laboratoryblocks.common.particle.RemovingModifierParticle;
import de.artemis.laboratoryblocks.common.registry.ModParticles;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = LaboratoryBlocks.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class LaboratoryBlocksClient {

    @SubscribeEvent
    public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
        event.register(ModParticles.APPLYING_GLOWSTONE_PARTICLE.get(), ApplyingGlowstoneParticle.Provider::new);
        event.register(ModParticles.REMOVING_MODIFIER_PARTICLE.get(), RemovingModifierParticle.Provider::new);
    }
}
