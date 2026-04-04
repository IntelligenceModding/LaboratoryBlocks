package de.artemis.laboratoryblocks.common.util;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class ModBlockSetTypes {

    public static final BlockSetType LABORATORY = new BlockSetType(
            "laboratory",
            true,
            true,
            true,
            BlockSetType.PressurePlateSensitivity.EVERYTHING,
            ModSoundTypes.LABORATORY_BLOCK,
            SoundEvents.IRON_DOOR_CLOSE,
            SoundEvents.IRON_DOOR_OPEN,
            SoundEvents.IRON_TRAPDOOR_CLOSE,
            SoundEvents.IRON_TRAPDOOR_OPEN,
            SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF,
            SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON,
            SoundEvents.STONE_BUTTON_CLICK_OFF,
            SoundEvents.STONE_BUTTON_CLICK_ON);
}
