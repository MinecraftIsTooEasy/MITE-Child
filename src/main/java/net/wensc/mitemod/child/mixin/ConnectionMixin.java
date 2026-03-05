package net.wensc.mitemod.child.mixin;

import net.minecraft.Connection;
import net.minecraft.Packet;

import net.wensc.mitemod.child.api.IConnection;
import net.wensc.mitemod.child.network.PacketToggleChild;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Connection.class)
public class ConnectionMixin implements IConnection {

    @Shadow public void unexpectedPacket(Packet par1Packet) {}

    @Override
    public void child_handleToggleChild(PacketToggleChild packetToggleChild) {
        this.unexpectedPacket(packetToggleChild);
    }
}