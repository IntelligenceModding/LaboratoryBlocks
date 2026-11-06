package de.artemis.laboratoryblocks.common.registry;
import de.artemis.laboratoryblocks.LaboratoryBlocks;
import de.artemis.laboratoryblocks.common.loot.AddMusicDiscLootModifier;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ModLootModifiers {
    private static final DeferredRegister<GlobalLootModifierSerializer<?>> MODIFIERS =
        DeferredRegister.create(ForgeRegistries.Keys.LOOT_MODIFIER_SERIALIZERS, LaboratoryBlocks.MOD_ID);
    public static final RegistryObject<AddMusicDiscLootModifier.Serializer> ADD_MUSIC_DISC =
        MODIFIERS.register("add_music_disc", AddMusicDiscLootModifier.Serializer::new);
    public static void register(IEventBus bus) { MODIFIERS.register(bus); }
    private ModLootModifiers() {}
}
