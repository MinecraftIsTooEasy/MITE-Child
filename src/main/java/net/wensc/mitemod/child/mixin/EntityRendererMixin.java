package net.wensc.mitemod.child.mixin;

import net.minecraft.EntityRenderer;
import net.minecraft.Minecraft;

import net.wensc.mitemod.child.util.Constant;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(EntityRenderer.class)
public class EntityRendererMixin {

    @Shadow private Minecraft mc;

    @ModifyVariable(method = "orientCamera", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityClientPlayerMP;isSneaking()Z"), name = "var3")
    private float modifyFloatVariable(float var3)
    {
        if(this.mc.thePlayer.isChild())
        {
            var3 -= -Constant.neakViewOffset;
            return var3;
        }

        return var3;
    }
}