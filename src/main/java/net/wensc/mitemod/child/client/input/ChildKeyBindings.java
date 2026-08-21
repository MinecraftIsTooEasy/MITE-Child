package net.wensc.mitemod.child.client.input;

import net.minecraft.KeyBinding;

/**
 * Custom key binding for child-mode toggle, appended to GameSettings
 */
public final class ChildKeyBindings {

    public static KeyBinding TOGGLE_CHILD;

    private static boolean registered;

    private ChildKeyBindings() {}

    /** Returns true only the first time; used to guard the GameSettings array expansion. */
    public static boolean markRegistered() {
        if (registered) return false;
        registered = true;
        return true;
    }
}