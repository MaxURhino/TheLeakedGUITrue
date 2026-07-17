package net.maxrhino.tlgt.mixin.screens;

import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Screen.class)
public class MixinScreen {
    @Shadow
    public int height;
    @Shadow
    public int width;
}
