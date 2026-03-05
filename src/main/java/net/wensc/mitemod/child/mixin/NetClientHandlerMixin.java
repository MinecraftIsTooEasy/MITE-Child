package net.wensc.mitemod.child.mixin;

import net.minecraft.Connection;
import net.minecraft.Minecraft;
import net.minecraft.NetClientHandler;

import net.wensc.mitemod.child.api.IConnection;
import net.wensc.mitemod.child.network.PacketToggleChild;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(NetClientHandler.class)
public abstract class NetClientHandlerMixin extends Connection implements IConnection {

    @Shadow private Minecraft h;

    @Override
    public void child_handleToggleChild(PacketToggleChild packetToggleChild) {
        this.unexpectedPacket(packetToggleChild);
    }

}