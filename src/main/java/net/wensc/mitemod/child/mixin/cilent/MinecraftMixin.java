package net.wensc.mitemod.child.mixin.cilent;

import net.minecraft.Minecraft;
import net.wensc.mitemod.child.client.input.ChildKeyHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Polls custom child hotkeys each client tick. */
@Mixin(Minecraft.class)
public abstract class MinecraftMixin {

    @Inject(method = "runTick", at = @At("TAIL"))
    private void child$handleHotkeys(CallbackInfo ci) {
        ChildKeyHandler.onClientTick();
    }
}