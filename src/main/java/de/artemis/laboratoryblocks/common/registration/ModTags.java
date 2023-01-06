package de.artemis.laboratoryblocks.common.registration;

import de.artemis.laboratoryblocks.LaboratoryBlocks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;

public class ModTags {

    public static class Item {
        public static final TagKey<net.minecraft.world.item.Item> STARCH_INGREDIENT = tag("starch_ingredient");

        private static TagKey<net.minecraft.world.item.Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(LaboratoryBlocks.MOD_ID, name));
        }

        private static TagKey<net.minecraft.world.item.Item> forgeTag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }
    }
}
