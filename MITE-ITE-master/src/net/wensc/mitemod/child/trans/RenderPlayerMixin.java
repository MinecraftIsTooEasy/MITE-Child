package net.wensc.mitemod.child.trans;

import net.minecraft.EntityLiving;
import net.minecraft.EntityPlayer;
import net.minecraft.beu;
import net.minecraft.bhj;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(bhj.class)
public class RenderPlayerMixin {

    @Inject(method = "c(Lnet/minecraft/EntityLiving;F)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/bhj;a(Lnet/minecraft/beu;F)V", shift = At.Shift.BEFORE))
    public void renderEquippedItems(EntityLiving par1EntityLivingBase, float par2, CallbackInfo ci){
        if (par1EntityLivingBase.isChild())
        {
            GL11.glScalef(0.5F, 0.5F, 0.5F);
            GL11.glTranslatef(0.0F, 1.5F, 0.0F);
        }
    }

}
