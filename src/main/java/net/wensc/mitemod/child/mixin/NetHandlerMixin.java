package net.wensc.mitemod.child.mixin;

import net.minecraft.NetHandler;
import net.minecraft.Packet;

import net.wensc.mitemod.child.api.INetHandler;
import net.wensc.mitemod.child.network.PacketToggleChild;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(NetHandler.class)
public class NetHandlerMixin implements INetHandler {

    @Shadow public void unexpectedPacket(Packet par1Packet) {}

    @Override
    public void child_handleToggleChild(PacketToggleChild packetToggleChild) {
        this.unexpectedPacket(packetToggleChild);
    }
}