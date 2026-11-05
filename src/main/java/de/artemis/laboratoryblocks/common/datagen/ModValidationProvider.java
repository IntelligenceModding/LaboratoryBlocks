package de.artemis.laboratoryblocks.common.datagen;

import com.google.gson.JsonParser;
import com.mojang.serialization.JsonOps;
import de.artemis.laboratoryblocks.common.registry.ModBlockPalettes;
import de.artemis.laboratoryblocks.common.registry.ModBlocks;
import de.artemis.laboratoryblocks.common.util.LaboratoryWoodSwapUtil;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.JukeboxSong;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.world.level.block.Block;
import org.jspecify.annotations.NonNull;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/** Runs registry-aware regression checks whenever data is generated. */
public final class ModValidationProvider implements DataProvider {
    private final CompletableFuture<HolderLookup.Provider> registries;

    public ModValidationProvider(CompletableFuture<HolderLookup.Provider> registries) {
        this.registries = registries;
    }

    @Override
    public @NonNull CompletableFuture<?> run(@NonNull CachedOutput output) {
        return registries.thenAccept(lookup -> {
            verifyPaletteCoverage();
            verifyWoodSwaps();
            var ops = lookup.createSerializationContext(JsonOps.INSTANCE);
            for (String track : List.of("exploration", "pondering_the_cosmos")) {
                String path = "/data/laboratoryblocks/jukebox_song/" + track + ".json";
                try (var stream = ModValidationProvider.class.getResourceAsStream(path)) {
                    require(stream != null, "Missing jukebox song " + path);
                    var song = JukeboxSong.DIRECT_CODEC.parse(ops,
                            JsonParser.parseReader(new InputStreamReader(stream, StandardCharsets.UTF_8))).getOrThrow();
                    require(song.soundEvent().value().location().equals(
                            Identifier.fromNamespaceAndPath("laboratoryblocks", "music_disc." + track)),
                            "Wrong sound event for " + track);
                } catch (java.io.IOException e) {
                    throw new IllegalStateException("Cannot validate song " + path, e);
                }
            }
            for (String prefix : List.of("", "glowing_")) {
                String oldId = "laboratoryblocks:" + prefix + "gray_laboratory_pillar";
                Identifier newId = Identifier.fromNamespaceAndPath("laboratoryblocks", prefix + "dark_laboratory_pillar");
                var expected = BuiltInRegistries.BLOCK.getValue(newId);
                require(expected != null, "Missing renamed pillar " + newId);
                var state = BlockState.CODEC.parse(ops, JsonParser.parseString("{\"id\":\"" + oldId + "\"}"))
                        .getOrThrow();
                require(state.is(expected), "Legacy pillar block state failed to migrate: " + oldId);
                // Datagen does not initialize item components, so validate the saved item's ID codec directly.
                var item = BuiltInRegistries.ITEM.byNameCodec().parse(ops, new com.google.gson.JsonPrimitive(oldId))
                        .getOrThrow();
                require(item == expected.asItem(), "Legacy pillar item ID failed to migrate: " + oldId);
                require(expected == (prefix.isEmpty() ? ModBlocks.DARK_LABORATORY_PILLAR.get()
                        : ModBlocks.GLOWING_DARK_LABORATORY_PILLAR.get()), "Legacy alias targets wrong pillar");
            }
            for (String name : List.of("root", "first_day_on_site", "precision_hardware", "field_kit",
                    "structural_integrity", "clean_finish", "transparent_barrier", "cold_white_glow",
                    "display_online", "air_exchange", "active_cooling", "control_loop", "wayfinding",
                    "full_spectrum", "commissioning_complete", "laboratory_music")) {
                String path = "/data/laboratoryblocks/advancement/" + name + ".json";
                try (var stream = ModValidationProvider.class.getResourceAsStream(path)) {
                    require(stream != null, "Missing advancement " + path);
                    var json = JsonParser.parseReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
                    Advancement.CODEC.parse(ops, json).getOrThrow(message ->
                            new IllegalStateException("Invalid advancement " + name + ": " + message));
                } catch (java.io.IOException e) {
                    throw new IllegalStateException("Cannot validate " + path, e);
                }
            }
        });
    }

    private static void verifyPaletteCoverage() {
        var blocks = new HashSet<Block>();
        for (var pair : ModBlockPalettes.BLOCK_ORDER) {
            require(blocks.add(pair.original().get()), "Duplicate original block in palette");
            if (pair.dark() != null) {
                require(blocks.add(pair.dark().get()), "Duplicate dark block in palette");
                require(pair.original().get().getClass() == pair.dark().get().getClass(), "Variant changes block behavior");
                require(pair.original().get().getStateDefinition().getProperties().equals(
                        pair.dark().get().getStateDefinition().getProperties()), "Variant changes block properties");
            }
        }
        require(blocks.size() == ModBlocks.BLOCKS.getEntries().size(), "Palette does not cover every registered block");
    }

    private static void verifyWoodSwaps() {
        for (var from : ModDatagenEntries.WOOD_FAMILIES) {
            for (var to : ModDatagenEntries.WOOD_FAMILIES) {
                boolean samePalette = ModBlockPalettes.isDark(from.floorPair().base().get()) ==
                        ModBlockPalettes.isDark(to.floorPair().base().get());
                if (!samePalette) {
                    continue;
                }
                var sources = List.of(from.floorPair().base(), from.floorPair().glowing(),
                        from.tilePair().base(), from.tilePair().glowing());
                var targets = List.of(to.floorPair().base(), to.floorPair().glowing(),
                        to.tilePair().base(), to.tilePair().glowing());
                for (int i = 0; i < sources.size(); i++) {
                    var swap = LaboratoryWoodSwapUtil.getSwap(sources.get(i).get(), to.planks().asItem());
                    if (from == to) {
                        require(swap == null, "Swapping to the same wood must consume nothing");
                    } else {
                        require(swap != null && swap.targetBlock() == targets.get(i).get(),
                                "Wood swap loses palette, shape, glowstone, or species");
                        require(swap.returnedPlanks() == from.planks().asItem(),
                                "Wood swap returns the wrong planks");
                    }
                }
            }
        }
    }

    private static void require(boolean condition, String message) {
        if (!condition) {
            throw new IllegalStateException(message);
        }
    }

    @Override
    public @NonNull String getName() {
        return "Laboratory block integration checks";
    }
}
