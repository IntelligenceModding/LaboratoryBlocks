package de.artemis.laboratoryblocks.common.registry;

import net.minecraft.resources.Identifier;

import java.util.function.Supplier;

public record RegistrySupplier<T>(Identifier getId, T get) implements Supplier<T> {
}
