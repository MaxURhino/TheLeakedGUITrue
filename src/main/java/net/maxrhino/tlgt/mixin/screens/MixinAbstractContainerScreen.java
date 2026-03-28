package net.maxrhino.tlgt.mixin.screens;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.maxrhino.tlgt.TheLeakedGUITrue;
import net.maxrhino.tlgt.util.MixinFlags;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Optional;

@Mixin(AbstractContainerScreen.class)
public abstract class MixinAbstractContainerScreen {
    @Unique
    private Optional<Component> str;

    @WrapOperation(
            method = "extractLabels",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;text(Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/Component;IIIZ)V",
                    ordinal = 0
            )
    )
    private void the_leaked_gui_true$changeContainerText(GuiGraphicsExtractor instance, Font font, Component str, int x, int y, int color, boolean dropShadow, Operation<Void> original) {
        if (str.equals(Component.translatable("container.chestDouble"))) {
            str = Component.translatable("container.chest");
        }

        MixinFlags.DRAWN_CONTAINER.set(str);

        if (TheLeakedGUITrue.CHANGE_POS_LIST.contains(str)) {
            x -= 90;
            y += 46;
            instance.text(font, str, x + 1, y + 1, 0x7F3f3f3f, false);
            instance.text(font, str, x, y, 0xFFFFFFFF, false);
        } else {
            original.call(instance, font, str, x, y, color, dropShadow);
        }

        this.str = Optional.of(str);
    }

    @WrapOperation(
            method = "extractLabels",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;text(Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/Component;IIIZ)V",
                    ordinal = 1
            )
    )
    private void the_leaked_gui_true$changeInventoryText(GuiGraphicsExtractor instance, Font font, Component str, int x, int y, int color, boolean dropShadow, Operation<Void> original) {
        if (this.str.isPresent() && TheLeakedGUITrue.CHANGE_POS_LIST.contains(this.str.get())) {
            x += 90;
            y = 67;
            if (MixinFlags.IS_GENERIC_9x3.get()) {
                y -= 27;
            }
        }

        original.call(instance, font, str, x, y, 0xFF898588, dropShadow);
    }

    @WrapMethod(
            method = "init()V"
    )
    protected void overrideInit(Operation<Void> original) {
        original.call();
    }
}
