package net.wensc.mitemod.child.api;

import net.wensc.mitemod.child.network.PacketToggleChild;

public interface IConnection {

    void child_handleToggleChild(PacketToggleChild packetToggleChild);
}