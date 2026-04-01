package net.maxrhino.tlgt.util;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.DyeColor;

public class MixinFlags {
    public static final ThreadLocal<Boolean> IS_IN_EXTRACT_SLOT = ThreadLocal.withInitial(() -> false);
    public static final ThreadLocal<Component> DRAWN_CONTAINER = ThreadLocal.withInitial(Component::empty);
    public static final ThreadLocal<Boolean> IS_GENERIC_9x3 = ThreadLocal.withInitial(() -> false);
}
