package net.maxrhino.tlgt.mixin;

import net.maxrhino.tlgt.util.screens.TestScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreativeModeInventoryScreen.class)
public class MixinCreativeModeInventoryScreen {
    @Inject(method = "init", at = @At("HEAD"))
    private void the_leaked_gui_true$testCustomScreen(CallbackInfo ci) {
        Minecraft.getInstance().execute(() -> Minecraft.getInstance().setScreen(new TestScreen()));
    }
}
