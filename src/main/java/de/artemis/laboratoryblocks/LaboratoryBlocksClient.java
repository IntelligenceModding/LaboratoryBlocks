package de.artemis.laboratoryblocks;

import de.artemis.laboratoryblocks.common.particle.ApplyingGlowstoneParticle;
import de.artemis.laboratoryblocks.common.particle.RemovingModifierParticle;
import de.artemis.laboratoryblocks.common.registry.ModBlocks;
import de.artemis.laboratoryblocks.common.registry.ModParticles;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = LaboratoryBlocks.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class LaboratoryBlocksClient {

    @SubscribeEvent
    public static void registerParticleFactories(ParticleFactoryRegisterEvent event) {
        Minecraft.getInstance().particleEngine.register(ModParticles.APPLYING_GLOWSTONE_PARTICLE.get(), ApplyingGlowstoneParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(ModParticles.REMOVING_MODIFIER_PARTICLE.get(), RemovingModifierParticle.Provider::new);
    }

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            RenderTypeLookup.setRenderLayer(ModBlocks.LABORATORY_DOOR.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.MESH_LABORATORY_DOOR.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.GLASS_LABORATORY_DOOR.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.LABORATORY_TRAPDOOR.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.MESH_LABORATORY_TRAPDOOR.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.GLASS_LABORATORY_TRAPDOOR.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.LABORATORY_GLASS.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.GLOWING_LABORATORY_GLASS.get(), RenderType.cutout());
        });
    }
}
