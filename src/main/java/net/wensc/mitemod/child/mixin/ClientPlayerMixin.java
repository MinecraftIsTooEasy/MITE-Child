package net.wensc.mitemod.child.mixin;

import net.minecraft.ClientPlayer;

import net.wensc.mitemod.child.api.IClientPlayer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ClientPlayer.class)
public class ClientPlayerMixin implements IClientPlayer {

    @Shadow
    protected boolean pushOutOfBlocks(double par1, double par3, double par5) {
        return false;
    }

    @Override
    public boolean child_pushOutOfBlocksPublic(double par1, double par3, double par5){
        return pushOutOfBlocks(par1, par3, par5);
    }

    @Redirect(method = "onLivingUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/ClientPlayer;pushOutOfBlocks(DDD)Z"))
    public boolean pushOutOfBlocks(ClientPlayer instance, double x, double y, double z)
    {
        if (instance.isChild())
        {
            return false;
        }
        else
        {
            return ((IClientPlayer) instance).child_pushOutOfBlocksPublic(x, y, z);
        }
    }

}