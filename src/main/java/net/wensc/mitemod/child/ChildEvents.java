package net.wensc.mitemod.child;

import com.google.common.eventbus.Subscribe;
import net.wensc.mitemod.child.network.PacketToggleChild;
import net.xiaoyu233.fml.reload.event.PacketRegisterEvent;

@SuppressWarnings("UnstableApiUsage")
public class ChildEvents {

    @Subscribe
    public void onPacketRegister(PacketRegisterEvent event){
        event.register(196, false, true, PacketToggleChild.class);
    }
}