package de.artemis.laboratoryblocks.common.registry;

import com.mojang.serialization.MapCodec;
import de.artemis.laboratoryblocks.LaboratoryBlocks;
import de.artemis.laboratoryblocks.common.loot.AddMusicDiscLootModifier;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class ModLootModifiers {
    private static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> MODIFIERS =
        DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, LaboratoryBlocks.MOD_ID);
    static { MODIFIERS.register("add_music_disc", () -> AddMusicDiscLootModifier.CODEC); }
    public static void register(IEventBus bus) { MODIFIERS.register(bus); }
    private ModLootModifiers() {}
}
