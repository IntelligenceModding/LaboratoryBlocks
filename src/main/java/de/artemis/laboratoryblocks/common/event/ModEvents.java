package de.artemis.laboratoryblocks.common.event;

import de.artemis.laboratoryblocks.LaboratoryBlocks;
import de.artemis.laboratoryblocks.client.particle.custom.ApplyingGlowstoneParticle;
import de.artemis.laboratoryblocks.client.particle.custom.RemovingGlowstoneParticle;
import de.artemis.laboratoryblocks.common.registration.ModParticles;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = LaboratoryBlocks.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEvents {

    @SubscribeEvent
    public static void registerParticleProvidersEvent(final RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticles.APPLYING_GLOWSTONE_PARTICLE.get(), ApplyingGlowstoneParticle.Provider::new);
        event.registerSpriteSet(ModParticles.REMOVING_GLOWSTONE_PARTICLE.get(), RemovingGlowstoneParticle.Provider::new);
    }
}
