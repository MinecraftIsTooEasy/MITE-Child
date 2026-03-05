package net.wensc.mitemod.child.mixin;

import net.minecraft.NetClientHandler;
import net.minecraft.NetHandler;

import net.wensc.mitemod.child.api.INetHandler;
import net.wensc.mitemod.child.network.PacketToggleChild;

import org.spongepowered.asm.mixin.Mixin;

@Mixin(NetClientHandler.class)
public abstract class NetClientHandlerMixin extends NetHandler implements INetHandler {

    @Override
    public void child_handleToggleChild(PacketToggleChild packetToggleChild) {
        this.unexpectedPacket(packetToggleChild);
    }

}