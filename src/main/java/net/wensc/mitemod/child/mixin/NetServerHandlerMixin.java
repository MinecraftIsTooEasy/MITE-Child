package net.wensc.mitemod.child.mixin;

import net.minecraft.NetHandler;
import net.minecraft.NetServerHandler;
import net.minecraft.ServerPlayer;

import net.wensc.mitemod.child.api.IChildPlayer;
import net.wensc.mitemod.child.api.INetHandler;
import net.wensc.mitemod.child.network.PacketToggleChild;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(NetServerHandler.class)
public abstract class NetServerHandlerMixin extends NetHandler implements INetHandler {

    @Shadow public ServerPlayer playerEntity;

    @Override
    @SuppressWarnings("ConstantConditions")
    public void child_handleToggleChild(PacketToggleChild packetToggleChild) {
        ((IChildPlayer)(Object) playerEntity).child_toggleChild();
    }
}