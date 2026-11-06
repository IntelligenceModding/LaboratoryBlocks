package de.artemis.laboratoryblocks.common.datagen;

import com.google.gson.JsonParser;
import com.mojang.serialization.JsonOps;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.world.item.JukeboxSong;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;

/** Validates the added songs and expanded advancements with the 1.21.1 codecs. */
public record ModAdditionValidationProvider(CompletableFuture<HolderLookup.Provider> registries) implements DataProvider {
    @Override
    public CompletableFuture<?> run(CachedOutput output) {
        return registries.thenAccept(lookup -> {
            var ops = lookup.createSerializationContext(JsonOps.INSTANCE);
            for (String file : java.util.List.of("active_cooling.json", "air_exchange.json", "clean_finish.json", "cold_white_glow.json", "commissioning_complete.json", "control_loop.json", "display_online.json", "field_kit.json", "first_day_on_site.json", "full_spectrum.json", "laboratory_music.json", "precision_hardware.json", "root.json", "structural_integrity.json", "transparent_barrier.json", "wayfinding.json")) {
                String path = "/data/laboratoryblocks/advancement/" + file;
                try (var stream = ModAdditionValidationProvider.class.getResourceAsStream(path)) {
                    if (stream == null) throw new IllegalStateException("Missing advancement " + path);
                    Advancement.CODEC.parse(ops, JsonParser.parseReader(new InputStreamReader(stream, StandardCharsets.UTF_8))).getOrThrow();
                } catch (Exception e) { throw new IllegalStateException("Invalid advancement " + path, e); }
            }
            for (String name : java.util.List.of("exploration", "pondering_the_cosmos")) {
                String path = "/data/laboratoryblocks/jukebox_song/" + name + ".json";
                try (var stream = ModAdditionValidationProvider.class.getResourceAsStream(path)) {
                    if (stream == null) throw new IllegalStateException("Missing song " + path);
                    JukeboxSong.DIRECT_CODEC.parse(ops, JsonParser.parseReader(new InputStreamReader(stream, StandardCharsets.UTF_8))).getOrThrow();
                } catch (java.io.IOException e) { throw new IllegalStateException(e); }
            }
        });
    }
    @Override
    public String getName() { return "1.0 addition codec validation"; }
}
