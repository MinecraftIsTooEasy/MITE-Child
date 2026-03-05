package net.wensc.mitemod.child.mixin;

import net.minecraft.*;
import net.wensc.mitemod.child.api.IChildPlayer;
import net.wensc.mitemod.child.util.Constant;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityPlayer.class)
public abstract class EntityPlayerMixin extends EntityLivingBase implements IChildPlayer {

    @Unique
    int child_watcher4;

    @Shadow public void setSizeProne() {}
    @Shadow public void setSizeNormal(){}

    @Unique
    public void child_setSizeChild()
    {
        this.setSize(0.3F, 0.9F);
    }

    public EntityPlayerMixin(World par1World) {
        super(par1World);
    }

    @Inject(method = "onLivingUpdate", at = @At("HEAD"))
    public void onLivingUpdate(CallbackInfo ci)
    {
        if (!this.worldObj.isRemote)
        {
            if(this.isChild())
            {
                this.setSizeProne();
            }
        }
        if(this.isChild())
        {
            this.jumpMovementFactor *= 0.5F;
        }
    }

    public boolean isChild() {
        return this.dataWatcher.getWatchableObjectByte(child_watcher4) == 1;
    }

    @Inject(method = "getEyePosY", at = @At("RETURN"), cancellable = true)
    public void getEyePosY(CallbackInfoReturnable<Double> cir)
    {
        if(this.isChild())
        {
            cir.setReturnValue(cir.getReturnValueD() - Constant.neakViewOffset);
            cir.cancel();
        }
    }

    @Inject(method = "entityInit", at = @At("RETURN"))
    public void injectDataWatcher(CallbackInfo ci) {
        this.child_watcher4 = this.dataWatcher.addObject(this.dataWatcher.getNextAvailableId(), (byte) 0);
    }

    @Inject(method = "readEntityFromNBT", at = @At("RETURN"))
    public void injectReadEntityFromNBT(NBTTagCompound par1NBTTagCompound, CallbackInfo ci)
    {
        if(par1NBTTagCompound.hasKey("isChild"))
        {
            this.dataWatcher.updateObject(child_watcher4, par1NBTTagCompound.getByte("isChild"));
            if(this.isChild())
            {
                child_setSizeChild();
            }
            else
            {
                setSizeNormal();
            }
        }
    }

    @Inject(method = "writeEntityToNBT", at = @At("RETURN"))
    public void injectWriteEntityToNBT(NBTTagCompound par1NBTTagCompound, CallbackInfo ci) {
        par1NBTTagCompound.setByte("isChild", this.dataWatcher.getWatchableObjectByte(child_watcher4));
    }

    @Override
    public void child_toggleChild()
    {
        byte var3 = this.dataWatcher.getWatchableObjectByte(child_watcher4);
        if(var3 == 1)
        {
            this.dataWatcher.updateObject(child_watcher4, (byte) 0);
            this.setSizeNormal();
        }
        else
        {
            if(this.ridingEntity == null)
            {
                this.dataWatcher.updateObject(child_watcher4, (byte) 1);
                this.child_setSizeChild();
            }
        }
    }
}