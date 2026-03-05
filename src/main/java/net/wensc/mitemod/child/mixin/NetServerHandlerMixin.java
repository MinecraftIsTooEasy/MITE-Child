package net.wensc.mitemod.child.mixin;

import net.minecraft.Connection;
import net.minecraft.PlayerConnection;
import net.minecraft.ServerPlayer;

import net.wensc.mitemod.child.api.IChildPlayer;
import net.wensc.mitemod.child.api.IConnection;
import net.wensc.mitemod.child.network.PacketToggleChild;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(PlayerConnection.class)
public abstract class NetServerHandlerMixin extends Connection implements IConnection {

    @Shadow public ServerPlayer playerEntity;

    @Override
    @SuppressWarnings("ConstantConditions")
    public void child_handleToggleChild(PacketToggleChild packetToggleChild) {
        ((IChildPlayer)(Object) playerEntity).child_toggleChild();
    }
}