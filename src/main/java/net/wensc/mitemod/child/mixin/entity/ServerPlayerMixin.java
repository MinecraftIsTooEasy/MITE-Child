package net.wensc.mitemod.child.mixin.entity;

import net.minecraft.EntityPlayer;
import net.minecraft.ServerPlayer;
import net.minecraft.World;

import net.wensc.mitemod.child.util.Constant;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin extends EntityPlayer {

    public ServerPlayerMixin(World par1World, String par2Str) {
        super(par1World, par2Str);
    }

    @Inject(method = "getEyeHeight", at = @At("RETURN"), cancellable = true)
    public void getEyeHeight(CallbackInfoReturnable<Float> cir) {
        if (this.isChild()) {
            cir.setReturnValue(cir.getReturnValue() - Constant.neakViewOffset);
        }
    }
}