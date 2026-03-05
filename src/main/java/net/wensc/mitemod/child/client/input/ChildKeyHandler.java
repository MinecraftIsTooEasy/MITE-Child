package net.wensc.mitemod.child.client.input;

import net.minecraft.Minecraft;
import net.wensc.mitemod.child.api.IChildPlayer;
import net.wensc.mitemod.child.network.PacketToggleChild;
import org.lwjgl.input.Keyboard;

public class ChildKeyHandler {

    private static boolean f3WasDown      = false;
    private static int     f3ReleaseDelay = 0;

    public static void onClientTick() {
        Minecraft mc = Minecraft.w();

        if (mc.h == null || mc.n != null) {
            f3WasDown      = Keyboard.isKeyDown(Keyboard.KEY_F3);
            f3ReleaseDelay = 0;
            return;
        }

        boolean f3Down = Keyboard.isKeyDown(Keyboard.KEY_F3);

        if (f3Down) {
            f3WasDown      = true;
            f3ReleaseDelay = 2;
            clearChildKeys();
            return;
        }

        if (f3WasDown) {
            if (f3ReleaseDelay > 0) {
                f3ReleaseDelay--;
                clearChildKeys();
                if (f3ReleaseDelay == 0) f3WasDown = false;
                return;
            }
            f3WasDown = false;
        }

        if (ChildKeyBindings.TOGGLE_CHILD.c()) {
            //noinspection ConstantConditions
            ((IChildPlayer)(Object) mc.h).child_toggleChild();
            mc.h.sendPacket(new PacketToggleChild());
        }
    }

    private static void clearChildKeys() {
        ChildKeyBindings.TOGGLE_CHILD.e   = false;
        ChildKeyBindings.TOGGLE_CHILD.f = 0;
    }
}