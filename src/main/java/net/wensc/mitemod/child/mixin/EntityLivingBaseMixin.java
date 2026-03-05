package net.wensc.mitemod.child.mixin;

import net.minecraft.EntityLivingBase;
import net.minecraft.EntityPlayer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntityLivingBase.class)
public class EntityLivingBaseMixin {

    @Redirect(method = "onDeathUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityLivingBase;isChild()Z"))
    public boolean isChild(EntityLivingBase entityLivingBase)
    {
        if(entityLivingBase instanceof EntityPlayer)
        {
            return false;
        }

        return entityLivingBase.isChild();
    }
}