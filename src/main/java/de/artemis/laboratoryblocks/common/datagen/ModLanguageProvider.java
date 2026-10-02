package de.artemis.laboratoryblocks.common.datagen;

import de.artemis.laboratoryblocks.LaboratoryBlocks;
import de.artemis.laboratoryblocks.common.registry.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ModLanguageProvider extends net.minecraftforge.common.data.LanguageProvider {
    public ModLanguageProvider(DataGenerator generator, String locale) {
        super(generator, LaboratoryBlocks.MOD_ID, locale);
    }

    @Override
    protected void addTranslations() {
        add("itemGroup.laboratoryblocks", "Artemis' Laboratory Blocks");
        add("tooltip.laboratoryblocks.configuration_tool.remove_glowstone", "Right-click glowing blocks to remove Glowstone Particles.");

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
        ModDatagenEntries.DOORS.forEach(this::addDoorTranslation);
        ModDatagenEntries.TRAPDOORS.forEach(this::addTrapdoorTranslation);

        Set<ResourceLocation> generatedBlockIds = ModDatagenEntries.ALL_PAIRS.stream()
                .flatMap(pair -> Stream.of(pair.base().getId(), pair.glowing().getId()))
                .collect(Collectors.toCollection(java.util.HashSet::new));
        generatedBlockIds.addAll(ModDatagenEntries.PILLAR_PAIRS.stream()
                .flatMap(pair -> Stream.of(pair.base().getId(), pair.glowing().getId()))
                .toList());
        generatedBlockIds.addAll(ModDatagenEntries.FAN_PAIRS.stream()
                .flatMap(pair -> Stream.of(pair.base().getId(), pair.glowing().getId()))
                .toList());
        generatedBlockIds.addAll(ModDatagenEntries.DOORS.stream().map(RegistryObject::getId).toList());
        generatedBlockIds.addAll(ModDatagenEntries.TRAPDOORS.stream().map(RegistryObject::getId).toList());
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

    private void addDoorTranslation(RegistryObject<? extends DoorBlock> door) {
        add(door.get(), humanize(door.getId().getPath()));
    }

    private void addTrapdoorTranslation(RegistryObject<? extends TrapDoorBlock> trapdoor) {
        add(trapdoor.get(), humanize(trapdoor.getId().getPath()));
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
