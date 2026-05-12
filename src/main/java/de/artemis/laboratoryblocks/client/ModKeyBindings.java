package de.artemis.laboratoryblocks.client;

import com.mojang.blaze3d.platform.InputConstants;
import de.artemis.laboratoryblocks.LaboratoryBlocks;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import net.neoforged.neoforge.client.settings.KeyModifier;
import org.lwjgl.glfw.GLFW;

@SuppressWarnings("deprecation")
public class ModKeyBindings {
    private static final KeyMapping.Category CATEGORY = KeyMapping.Category.register(Identifier.fromNamespaceAndPath(LaboratoryBlocks.MOD_ID, "category"));

    public static final KeyMapping REMOVE_GLOWSTONE_CONFIGURATION_TOOL_ACTION = new KeyMapping("keybind." + LaboratoryBlocks.MOD_ID + ".remove_glowstone_configuration_tool_action", KeyConflictContext.UNIVERSAL, KeyModifier.NONE, InputConstants.Type.KEYSYM.getOrCreate(GLFW.GLFW_KEY_LEFT_CONTROL), CATEGORY);
    public static final KeyMapping REMOVE_REDSTONE_CONFIGURATION_TOOL_ACTION = new KeyMapping("keybind." + LaboratoryBlocks.MOD_ID + ".remove_redstone_configuration_tool_action", KeyConflictContext.UNIVERSAL, KeyModifier.NONE, InputConstants.Type.KEYSYM.getOrCreate(GLFW.GLFW_KEY_LEFT_ALT), CATEGORY);
    public static final KeyMapping SHOW_INFORMATION = new KeyMapping("keybind." + LaboratoryBlocks.MOD_ID + ".show_information", KeyConflictContext.UNIVERSAL, KeyModifier.NONE, InputConstants.Type.KEYSYM.getOrCreate(GLFW.GLFW_KEY_LEFT_CONTROL), CATEGORY);

}