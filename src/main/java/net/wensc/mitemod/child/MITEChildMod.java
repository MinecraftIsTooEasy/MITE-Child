package net.wensc.mitemod.child;

import net.fabricmc.api.ModInitializer;
import net.xiaoyu233.fml.ModResourceManager;
import net.xiaoyu233.fml.reload.event.MITEEvents;

@SuppressWarnings("UnstableApiUsage")
public class MITEChildMod implements ModInitializer {

    public void onInitialize() {
        ModResourceManager.addResourcePackDomain("child");
        MITEEvents.MITE_EVENT_BUS.register(new ChildEvents());
    }
}