package de.artemis.laboratoryblocks.common.registry;

import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

public record RegistrySupplier<T>(ResourceLocation getId, T get) implements Supplier<T> {
}
