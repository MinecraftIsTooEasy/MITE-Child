package net.wensc.mitemod.child;

import net.wensc.mitemod.child.mixin.MixinPackageMarker;

import net.xiaoyu233.fml.AbstractMod;

import net.xiaoyu233.fml.classloading.Mod;
import net.xiaoyu233.fml.config.InjectionConfig;
import net.xiaoyu233.fml.reload.event.MITEEvents;

import org.spongepowered.asm.mixin.MixinEnvironment;

import javax.annotation.Nonnull;

@Mod
public class MITEChildMod extends AbstractMod {

    public MITEChildMod() {}

    public void preInit() {}

    @Nonnull @Override
    public InjectionConfig getInjectionConfig() {
        return InjectionConfig.Builder.of("child", MixinPackageMarker.class.getPackage(), MixinEnvironment.Phase.INIT).setRequired().build();
    }

    public void postInit() {
        super.postInit();
        MITEEvents.MITE_EVENT_BUS.register(new ChildEvents());
    }


    public String modId() {
        return "child";
    }

    public int modVerNum() {
        return 100;
    }

    public String modVerStr() {
        return "1.0.0";
    }
}