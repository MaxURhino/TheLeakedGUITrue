package net.maxrhino.tlgt.mixin.screens;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.gui.screens.inventory.AbstractRecipeBookScreen;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(AbstractRecipeBookScreen.class)
public class MixinAbstractRecipeBookScreen extends MixinAbstractContainerScreen {
    @WrapMethod(method = "hasClickedOutside")
    protected boolean the_leaked_gui_true$wrapHasClickedOutside(double mx, double my, int xo, int yo, Operation<Boolean> original) {
        return super.the_leaked_gui_true$overrideHasClickedOutside(mx, my, xo, yo, original);
    }
}
