package de.artemis.laboratoryblocks.mixin;

import de.artemis.laboratoryblocks.LaboratoryBlocks;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

/** Reads pre-1.0 pillar IDs without registering duplicate blocks or items. */
@Mixin(MappedRegistry.class)
abstract class LegacyPillarRegistryMixin<T> {
    @Shadow @Final private ResourceKey<? extends Registry<T>> key;

    @Unique
    private Identifier laboratoryblocks$remap(Identifier id) {
        if (id == null || (!key.equals(Registries.BLOCK) && !key.equals(Registries.ITEM))
                || !id.getNamespace().equals(LaboratoryBlocks.MOD_ID)) return id;
        return switch (id.getPath()) {
            case "gray_laboratory_pillar" -> Identifier.fromNamespaceAndPath(LaboratoryBlocks.MOD_ID, "dark_laboratory_pillar");
            case "glowing_gray_laboratory_pillar" -> Identifier.fromNamespaceAndPath(LaboratoryBlocks.MOD_ID, "glowing_dark_laboratory_pillar");
            default -> id;
        };
    }

    @ModifyVariable(method = {
            "get(Lnet/minecraft/resources/Identifier;)Ljava/util/Optional;",
            "getValue(Lnet/minecraft/resources/Identifier;)Ljava/lang/Object;",
            "containsKey(Lnet/minecraft/resources/Identifier;)Z"
    }, at = @At("HEAD"), argsOnly = true)
    private Identifier laboratoryblocks$legacyIdentifier(Identifier id) {
        return laboratoryblocks$remap(id);
    }

    @ModifyVariable(method = {
            "get(Lnet/minecraft/resources/ResourceKey;)Ljava/util/Optional;",
            "getValue(Lnet/minecraft/resources/ResourceKey;)Ljava/lang/Object;",
            "containsKey(Lnet/minecraft/resources/ResourceKey;)Z"
    }, at = @At("HEAD"), argsOnly = true)
    private ResourceKey<T> laboratoryblocks$legacyKey(ResourceKey<T> id) {
        if (id == null) return null;
        Identifier mapped = laboratoryblocks$remap(id.identifier());
        return mapped.equals(id.identifier()) ? id : ResourceKey.create(key, mapped);
    }
}
