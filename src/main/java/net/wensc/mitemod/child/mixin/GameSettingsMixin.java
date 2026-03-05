package net.wensc.mitemod.child.mixin;

import net.minecraft.ats;
import net.minecraft.aul;

import net.wensc.mitemod.child.client.input.ChildKeyBindings;

import org.lwjgl.input.Keyboard;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;

/** Appends custom key bindings to GameSettings so they persist in options.txt. */
@Mixin(aul.class)
public class GameSettingsMixin {

    @Shadow public ats[] W;

    @Shadow public ats S;

    @Inject(method = "initKeybindings", at = @At("RETURN"))
    private void child$injectCustomKeys(CallbackInfo ci) {
        if (!ChildKeyBindings.markRegistered()) return;

        ChildKeyBindings.TOGGLE_CHILD = new ats("key.child", Keyboard.KEY_V);

        ats[] custom   = { ChildKeyBindings.TOGGLE_CHILD };
        ats[] expanded = Arrays.copyOf(W, W.length + custom.length);
        System.arraycopy(custom, 0, expanded, W.length, custom.length);
        W = expanded;
    }

}