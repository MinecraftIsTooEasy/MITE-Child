package net.wensc.mitemod.child.mixin;

import net.minecraft.*;
import net.wensc.mitemod.child.client.input.ChildKeyHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {

    @Inject(method = "k", at = @At("TAIL"))
    private void child$handleHotkeys(CallbackInfo ci) {
        ChildKeyHandler.onClientTick();
    }
}