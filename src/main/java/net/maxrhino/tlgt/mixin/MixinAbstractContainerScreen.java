package net.maxrhino.tlgt.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.util.Optional;

@Mixin(AbstractContainerScreen.class)
public class MixinAbstractContainerScreen {
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

        if (str.equals(Component.translatable("container.chest"))) {
            instance.text(font, str, x + 1, y + 1, 0x7F3f3f3f, false);
            instance.text(font, str, x, y, 0xFFFFFFFF, false);
        } else {
            original.call(instance, font, str, x, y, color, dropShadow);
        }

        this.str = Optional.of(str);
    }

    @ModifyArgs(
            method = "extractLabels",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;text(Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/Component;IIIZ)V",
                    ordinal = 1
            )
    )
    private void the_leaked_gui_true$changeInventoryText(Args args) {
        args.set(3, (Integer)args.get(3) + 4);
        args.set(4, 0xFF898588);
    }
}
