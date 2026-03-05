package net.wensc.mitemod.child.mixin;

import net.minecraft.GameSettings;
import net.minecraft.KeyBinding;
import net.wensc.mitemod.child.client.input.ChildKeyBindings;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;

/** Appends custom key bindings to GameSettings so they persist in options.txt. */
@Mixin(GameSettings.class)
public abstract class GameSettingsMixin {

    @Shadow public KeyBinding[] keyBindings;

    @Inject(method = "initKeybindings", at = @At("RETURN"))
    private void child$injectCustomKeys(CallbackInfo ci) {
        if (!ChildKeyBindings.markRegistered()) return;

        ChildKeyBindings.TOGGLE_CHILD = new KeyBinding("key.child", Keyboard.KEY_V);

        KeyBinding[] custom   = { ChildKeyBindings.TOGGLE_CHILD };
        KeyBinding[] expanded = Arrays.copyOf(keyBindings, keyBindings.length + custom.length);
        System.arraycopy(custom, 0, expanded, keyBindings.length, custom.length);
        keyBindings = expanded;
    }

}