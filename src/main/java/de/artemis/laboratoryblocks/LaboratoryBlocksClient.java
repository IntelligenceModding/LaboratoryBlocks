package de.artemis.laboratoryblocks;

import de.artemis.laboratoryblocks.common.particle.ApplyingGlowstoneParticle;
import de.artemis.laboratoryblocks.common.particle.RemovingModifierParticle;
import de.artemis.laboratoryblocks.common.registry.ModParticles;
import de.artemis.laboratoryblocks.common.registry.ModBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;

public class LaboratoryBlocksClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ParticleFactoryRegistry.getInstance().register(ModParticles.APPLYING_GLOWSTONE_PARTICLE.get(), ApplyingGlowstoneParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.REMOVING_MODIFIER_PARTICLE.get(), RemovingModifierParticle.Provider::new);

        BlockRenderLayerMap.putBlocks(ChunkSectionLayer.CUTOUT,
                ModBlocks.LABORATORY_GLASS.get(),
                ModBlocks.GLOWING_LABORATORY_GLASS.get(),
                ModBlocks.LABORATORY_DOOR.get(),
                ModBlocks.MESH_LABORATORY_DOOR.get(),
                ModBlocks.GLASS_LABORATORY_DOOR.get(),
                ModBlocks.LABORATORY_TRAPDOOR.get(),
                ModBlocks.MESH_LABORATORY_TRAPDOOR.get(),
                ModBlocks.GLASS_LABORATORY_TRAPDOOR.get()
        );
    }
}
