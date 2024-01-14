package de.artemis.laboratoryblocks.common.registration;

import com.mojang.blaze3d.platform.InputConstants;
import de.artemis.laboratoryblocks.LaboratoryBlocks;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import org.lwjgl.glfw.GLFW;

public class ModKeyBindings {

    public static final KeyMapping ALTERNATIVE_CONFIGURATION_TOOL_ACTION = new KeyMapping("keybind." + LaboratoryBlocks.MOD_ID + ".alternative_configuration_tool_action", KeyConflictContext.UNIVERSAL, KeyModifier.NONE, InputConstants.Type.KEYSYM.getOrCreate(GLFW.GLFW_KEY_LEFT_CONTROL), "keybind." + LaboratoryBlocks.MOD_ID + ".category");

}
