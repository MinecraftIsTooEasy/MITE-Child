package net.wensc.mitemod.child.mixin.cilent;

import net.minecraft.EntityPlayer;
import net.minecraft.AbstractClientPlayer;
import net.minecraft.RenderPlayer;

import org.lwjgl.opengl.GL11;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderPlayer.class)
public class RenderPlayerMixin {

    @Inject(method = "renderSpecials", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glPushMatrix()V", ordinal = 2, shift = At.Shift.AFTER))
    public void injectRenderPlayer(AbstractClientPlayer par1AbstractClientPlayer, float par2, CallbackInfo ci)
    {
        if(par1AbstractClientPlayer.isChild())
        {
            GL11.glScalef(0.5f, 0.5f, 0.5f);
            GL11.glTranslatef(0f, EntityPlayer.y_offset_on_client_and_eye_height_on_server, 0f);
        }
    }

    @Inject(method = "renderSpecials", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glTranslatef(FFF)V", ordinal = 4, shift = At.Shift.AFTER))
    public void redirectRenderPlayer(AbstractClientPlayer par1AbstractClientPlayer, float par2, CallbackInfo ci)
    {
        if(par1AbstractClientPlayer.isChild())
        {
            float var8x = 2F;
            GL11.glScalef(1.0F / var8x , 1.0F / var8x, 1.0F / var8x);
            GL11.glTranslatef(6F / 16F, 10F / 16F, 2F / 16F);
        }
    }

}