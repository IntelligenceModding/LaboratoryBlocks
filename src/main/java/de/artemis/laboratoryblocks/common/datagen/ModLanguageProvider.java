package de.artemis.laboratoryblocks.common.datagen;

import de.artemis.laboratoryblocks.LaboratoryBlocks;
import de.artemis.laboratoryblocks.common.item.ModItems;
import net.minecraft.data.PackOutput;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ModLanguageProvider extends net.neoforged.neoforge.common.data.LanguageProvider {
    public ModLanguageProvider(PackOutput output, String locale) {
        super(output, LaboratoryBlocks.MOD_ID, locale);
    }

    @Override
    protected void addTranslations() {
        add("itemGroup.laboratoryblocks", "Artemis' Laboratory Blocks");
        add("keybind.laboratoryblocks.category", "Artemis' Laboratory Blocks");

        add("tooltip.laboratoryblocks.configuration_tool", "Press %s to remove Glowstone Particles and %s Right Click to remove Redstone Particles.");
        add("tooltip.laboratoryblocks.configuration_tool_preview", "Hold %s for details.");

        add("keybind.laboratoryblocks.remove_redstone_configuration_tool_action", "Remove Redstone with Configuration Tool");
        add("keybind.laboratoryblocks.remove_glowstone_configuration_tool_action", "Remove Glowstone with Configuration Tool");
        add("keybind.laboratoryblocks.show_information", "Show information");

        ModDatagenEntries.ALL_PAIRS.forEach(pair -> {
            add(pair.base().get(), humanize(pair.base().getId().getPath()));
            add(pair.glowing().get(), humanize(pair.glowing().getId().getPath()));
        });
        ModDatagenEntries.PILLAR_PAIRS.forEach(pair -> {
            add(pair.base().get(), humanize(pair.base().getId().getPath()));
            add(pair.glowing().get(), humanize(pair.glowing().getId().getPath()));
        });
        ModDatagenEntries.FAN_PAIRS.forEach(pair -> {
            add(pair.base().get(), humanize(pair.base().getId().getPath()));
            add(pair.glowing().get(), humanize(pair.glowing().getId().getPath()));
        });
        ModDatagenEntries.DOORS.forEach(door -> add(door.get(), humanize(door.getId().getPath())));
        ModDatagenEntries.TRAPDOORS.forEach(trapdoor -> add(trapdoor.get(), humanize(trapdoor.getId().getPath())));

        Set<net.minecraft.resources.Identifier> generatedBlockIds = ModDatagenEntries.ALL_PAIRS.stream()
                .flatMap(pair -> Stream.of(pair.base().getId(), pair.glowing().getId()))
                .collect(Collectors.toCollection(java.util.HashSet::new));
        generatedBlockIds.addAll(ModDatagenEntries.PILLAR_PAIRS.stream()
                .flatMap(pair -> Stream.of(pair.base().getId(), pair.glowing().getId()))
                .toList());
        generatedBlockIds.addAll(ModDatagenEntries.FAN_PAIRS.stream()
                .flatMap(pair -> Stream.of(pair.base().getId(), pair.glowing().getId()))
                .toList());
        generatedBlockIds.addAll(ModDatagenEntries.DOORS.stream().map(door -> door.getId()).toList());
        generatedBlockIds.addAll(ModDatagenEntries.TRAPDOORS.stream().map(trapdoor -> trapdoor.getId()).toList());
        generatedBlockIds = Set.copyOf(generatedBlockIds);

        for (var item : ModItems.ITEMS.getEntries()) {
            if (!generatedBlockIds.contains(item.getId())) {
                add(item.get(), humanize(item.getId().getPath()));
            }
        }
    }

    private static String humanize(String path) {
        StringBuilder builder = new StringBuilder();
        String[] words = path.split("_");

        for (int i = 0; i < words.length; i++) {
            if (i > 0) {
                builder.append(' ');
            }
            builder.append(capitalizeWord(words[i]));
        }

        return builder.toString();
    }

    private static String capitalizeWord(String word) {
        StringBuilder builder = new StringBuilder();
        String[] parts = word.split("-");

        for (int i = 0; i < parts.length; i++) {
            if (i > 0) {
                builder.append('-');
            }

            if (parts[i].isEmpty()) {
                continue;
            }

            builder.append(Character.toUpperCase(parts[i].charAt(0)));
            if (parts[i].length() > 1) {
                builder.append(parts[i].substring(1));
            }
        }

        return builder.toString();
    }
}
