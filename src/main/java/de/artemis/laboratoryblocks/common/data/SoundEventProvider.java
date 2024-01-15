package de.artemis.laboratoryblocks.common.data;

import de.artemis.laboratoryblocks.LaboratoryBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinition;
import net.minecraftforge.common.data.SoundDefinitionsProvider;

public class SoundEventProvider extends SoundDefinitionsProvider {
    protected SoundEventProvider(PackOutput packOutput, ExistingFileHelper helper) {
        super(packOutput, LaboratoryBlocks.MOD_ID, helper);
    }

    @Override
    public void registerSounds() {
        add("laboratory_block_break", definition().with(SoundDefinition.Sound.sound(new ResourceLocation(LaboratoryBlocks.MOD_ID, "laboratory_block_break"), SoundDefinition.SoundType.SOUND)).subtitle("Laboratory Block Break"));
        add("laboratory_block_fall", definition().with(SoundDefinition.Sound.sound(new ResourceLocation(LaboratoryBlocks.MOD_ID, "laboratory_block_fall"), SoundDefinition.SoundType.SOUND)).subtitle("Laboratory Block Fall"));
        add("laboratory_block_hit", definition().with(SoundDefinition.Sound.sound(new ResourceLocation(LaboratoryBlocks.MOD_ID, "laboratory_block_hit"), SoundDefinition.SoundType.SOUND)).subtitle("Laboratory Block Hit"));
        add("laboratory_block_place", definition().with(SoundDefinition.Sound.sound(new ResourceLocation(LaboratoryBlocks.MOD_ID, "laboratory_block_place"), SoundDefinition.SoundType.SOUND)).subtitle("Laboratory Block Place"));
        add("laboratory_block_step", definition().with(SoundDefinition.Sound.sound(new ResourceLocation(LaboratoryBlocks.MOD_ID, "laboratory_block_step"), SoundDefinition.SoundType.SOUND)).subtitle("Laboratory Block Step"));
    }
}
