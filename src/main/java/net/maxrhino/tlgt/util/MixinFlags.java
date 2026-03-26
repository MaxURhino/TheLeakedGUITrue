package net.maxrhino.tlgt.util;

public class MixinFlags {
    public static final ThreadLocal<Boolean> IS_IN_EXTRACT_SLOT = ThreadLocal.withInitial(() -> false);
}
