package de.artemis.laboratoryblocks;

import de.artemis.laboratoryblocks.client.ModKeyBindings;
import de.artemis.laboratoryblocks.common.particle.ApplyingGlowstoneParticle;
import de.artemis.laboratoryblocks.common.particle.ApplyingRedstoneParticle;
import de.artemis.laboratoryblocks.common.particle.RemovingModifierParticle;
import de.artemis.laboratoryblocks.common.registry.ModParticles;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

@Mod(value = LaboratoryBlocks.MOD_ID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = LaboratoryBlocks.MOD_ID, value = Dist.CLIENT)
public class LaboratoryBlocksClient {

    @SubscribeEvent
    public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticles.APPLYING_GLOWSTONE_PARTICLE.get(), ApplyingGlowstoneParticle.Provider::new);
        event.registerSpriteSet(ModParticles.APPLYING_REDSTONE_PARTICLE.get(), ApplyingRedstoneParticle.Provider::new);
        event.registerSpriteSet(ModParticles.REMOVING_MODIFIER_PARTICLE.get(), RemovingModifierParticle.Provider::new);
    }

    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(ModKeyBindings.REMOVE_REDSTONE_CONFIGURATION_TOOL_ACTION);
        event.register(ModKeyBindings.REMOVE_GLOWSTONE_CONFIGURATION_TOOL_ACTION);
        event.register(ModKeyBindings.SHOW_INFORMATION);
    }
}