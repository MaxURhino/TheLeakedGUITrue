package net.maxrhino.tlgt.mixin.screens;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import net.maxrhino.tlgt.mixin.accessors.AbstractContainerScreenAccessor;
import net.maxrhino.tlgt.mixin.accessors.ScreenAccessor;
import net.maxrhino.tlgt.util.ScreenUtils;
import net.maxrhino.tlgt.util.components.widgets.CloseButtonWidget;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractFurnaceScreen;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractFurnaceScreen.class)
public class MixinAbstractFurnaceScreen {
    @Inject(method = "init", at = @At("TAIL"))
    private void the_leaked_gui_true$addExitButton(CallbackInfo ci) {
        AbstractFurnaceScreen<?> instance = (AbstractFurnaceScreen<?>) (Object) this;

        int imageWidth = ((AbstractContainerScreenAccessor)this).imageWidth();

        int xo = (instance.width - imageWidth) / 2 + 90;
        int yo = (instance.height / 2) - 47;

        ((ScreenAccessor)this).the_leaked_gui_true$addRenderableWidget(new CloseButtonWidget(xo + imageWidth - 8, yo + 4));
    }

    @WrapOperation(
            method = "extractBackground",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blit(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIFFIIII)V",
                    ordinal = 0
            )
    )
    private void the_leaked_gui_true$drawFurnaceBackground(GuiGraphicsExtractor graphics, RenderPipeline renderPipeline, Identifier texture,
                                                           int x, int y, float u, float v, int width, int height, int textureWidth, int textureHeight,
                                                           Operation<Void> original) {
        AbstractFurnaceScreen<?> instance = (AbstractFurnaceScreen<?>) (Object) this;

        int cusX;
        int cusY;

        // Furnace

        int xo = x - 90;
        int yo = (instance.height / 2) - ((height - 90) / 2);

        ScreenUtils.drawContainerBackground(
                graphics,
                xo, yo,
                width, height - 90,
                "furnace"
        );

        // Inventory

        xo = x + 90;
        yo = (instance.height / 2) - 47;

        ScreenUtils.drawContainerBackground(
                graphics,
                xo, yo,
                width, 94
        );

        cusX = (instance.width / 2) + 2;
        cusY = (instance.height / 2) - 41;

        for (int i = 0; i < 9; i++) {
            ScreenUtils.drawSlot(graphics, cusX + 8 + (i * 18), cusY + 65);
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 3; j++) {
                ScreenUtils.drawSlot(graphics, (cusX + 8) + (i * 18), (cusY + 43) - (j * 18));
            }
        }
    }

    @WrapOperation(
            method = "extractBackground",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIIIIIII)V",
                    ordinal = 0
            )
    )
    private void the_leaked_gui_true$changeLitIndicator(GuiGraphicsExtractor graphics, RenderPipeline renderPipeline, Identifier location,
                                                        int spriteWidth, int spriteHeight, int textureX, int textureY, int x, int y,
                                                        int width, int height, Operation<Void> original) {

    }
}
