package de.artemis.laboratoryblocks.common.event;

import de.artemis.laboratoryblocks.LaboratoryBlocks;
import de.artemis.laboratoryblocks.client.particle.custom.ApplyingGlowstoneParticle;
import de.artemis.laboratoryblocks.client.particle.custom.RemovingGlowstoneParticle;
import de.artemis.laboratoryblocks.common.registration.ModParticles;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = LaboratoryBlocks.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents {

    @SubscribeEvent
    public static void registerParticleProvidersEvent(final ParticleFactoryRegisterEvent event) {
        Minecraft.getInstance().particleEngine.register(ModParticles.APPLYING_GLOWSTONE_PARTICLE.get(), ApplyingGlowstoneParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(ModParticles.REMOVING_GLOWSTONE_PARTICLE.get(), RemovingGlowstoneParticle.Provider::new);
    }

}
