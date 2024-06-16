package tobinio.usefulsavedhotbars.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import tobinio.usefulsavedhotbars.mixin.KeyboardAccessor;

public class UsefulSavedHotbarsClient implements ClientModInitializer {
    /**
     * Runs the mod initializer on the client environment.
     */

    public static KeyBinding LoadHotbarsKeyBindingF3;
    public static KeyBinding SaveHotbarsKeyBindingF3;

    public static KeyBinding LoadHotbarsKeyBinding;
    public static KeyBinding SaveHotbarsKeyBinding;

    @Override
    public void onInitializeClient() {
        LoadHotbarsKeyBindingF3 = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.usefulsavedhotbars.loadf3", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "category.usefulsavedhotbars.savedhotbar"));
        SaveHotbarsKeyBindingF3 = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.usefulsavedhotbars.savef3", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "category.usefulsavedhotbars.savedhotbar"));

        LoadHotbarsKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.usefulsavedhotbars.load", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_F6, "category.usefulsavedhotbars.savedhotbar"));
        SaveHotbarsKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.usefulsavedhotbars.save", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_F7, "category.usefulsavedhotbars.savedhotbar"));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (LoadHotbarsKeyBinding.wasPressed()) {
                setSavedHotbarScreen((KeyboardAccessor) client.keyboard, SavedHotbarScreen.Type.LOAD);
            }

            if (SaveHotbarsKeyBinding.wasPressed()) {
                setSavedHotbarScreen((KeyboardAccessor) client.keyboard, SavedHotbarScreen.Type.SAVE);
            }
        });
    }

    public static void setSavedHotbarScreen(KeyboardAccessor keyboardAccessor, SavedHotbarScreen.Type save) {
        MinecraftClient client = keyboardAccessor.getClient();

        if (client.player != null && client.player.isCreative()) {
            client.setScreen(new SavedHotbarScreen(client, save));
        } else {
            keyboardAccessor.invokeDebugLog("debug.gamemodes.error");
        }
    }
}
