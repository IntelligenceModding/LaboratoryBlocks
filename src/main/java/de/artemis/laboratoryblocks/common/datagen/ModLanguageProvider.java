package de.artemis.laboratoryblocks.common.datagen;

import de.artemis.laboratoryblocks.common.registry.ModItems;
import de.artemis.laboratoryblocks.common.registry.RegistrySupplier;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.TrapDoorBlock;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ModLanguageProvider extends FabricLanguageProvider {
    public ModLanguageProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator, "en_us");
    }

    @Override
    public void generateTranslations(TranslationBuilder builder) {
        builder.add("itemGroup.laboratoryblocks.laboratory_blocks_creative_tab", "Artemis' Laboratory Blocks");
        builder.add("tooltip.laboratoryblocks.configuration_tool.remove_glowstone", "Right-click glowing blocks to remove Glowstone Particles.");
        builder.add("modmenu.nameTranslation.laboratoryblocks", "Artemis' Laboratory Blocks");
        builder.add("modmenu.descriptionTranslation.laboratoryblocks", "Upgrade your builds with modern, futuristic laboratory blocks inspired by the classic laboratory style.");
        builder.add("laboratoryblocks.link.discord", "Discord");
        builder.add("laboratoryblocks.link.github", "GitHub");
        builder.add("laboratoryblocks.link.modrinth", "Modrinth");
        builder.add("laboratoryblocks.link.youtube", "YouTube");

        ModDatagenEntries.ALL_PAIRS.forEach(pair -> {
            builder.add(pair.base().get(), humanize(pair.base().getId().getPath()));
            builder.add(pair.glowing().get(), humanize(pair.glowing().getId().getPath()));
        });
        ModDatagenEntries.PILLAR_PAIRS.forEach(pair -> {
            builder.add(pair.base().get(), humanize(pair.base().getId().getPath()));
            builder.add(pair.glowing().get(), humanize(pair.glowing().getId().getPath()));
        });
        ModDatagenEntries.FAN_PAIRS.forEach(pair -> {
            builder.add(pair.base().get(), humanize(pair.base().getId().getPath()));
            builder.add(pair.glowing().get(), humanize(pair.glowing().getId().getPath()));
        });
        ModDatagenEntries.DOORS.forEach(door -> addDoorTranslation(builder, door));
        ModDatagenEntries.TRAPDOORS.forEach(trapdoor -> addTrapdoorTranslation(builder, trapdoor));

        Set<ResourceLocation> generatedBlockIds = ModDatagenEntries.ALL_PAIRS.stream()
                .flatMap(pair -> Stream.of(pair.base().getId(), pair.glowing().getId()))
                .collect(Collectors.toCollection(java.util.HashSet::new));
        generatedBlockIds.addAll(ModDatagenEntries.PILLAR_PAIRS.stream()
                .flatMap(pair -> Stream.of(pair.base().getId(), pair.glowing().getId()))
                .toList());
        generatedBlockIds.addAll(ModDatagenEntries.FAN_PAIRS.stream()
                .flatMap(pair -> Stream.of(pair.base().getId(), pair.glowing().getId()))
                .toList());
        generatedBlockIds.addAll(ModDatagenEntries.DOORS.stream().map(RegistrySupplier::getId).toList());
        generatedBlockIds.addAll(ModDatagenEntries.TRAPDOORS.stream().map(RegistrySupplier::getId).toList());
        generatedBlockIds = Set.copyOf(generatedBlockIds);

        for (var item : ModItems.ITEMS) {
            if (!generatedBlockIds.contains(item.getId())) {
                builder.add(item.get(), humanize(item.getId().getPath()));
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

    private static void addDoorTranslation(TranslationBuilder builder, RegistrySupplier<? extends DoorBlock> door) {
        builder.add(door.get(), humanize(door.getId().getPath()));
    }

    private static void addTrapdoorTranslation(TranslationBuilder builder, RegistrySupplier<? extends TrapDoorBlock> trapdoor) {
        builder.add(trapdoor.get(), humanize(trapdoor.getId().getPath()));
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
