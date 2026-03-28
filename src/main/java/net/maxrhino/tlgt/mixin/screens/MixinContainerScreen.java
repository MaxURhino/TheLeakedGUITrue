package net.maxrhino.tlgt.mixin.screens;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import net.maxrhino.tlgt.mixin.accessors.AbstractContainerScreenAccessor;
import net.maxrhino.tlgt.mixin.accessors.ScreenAccessor;
import net.maxrhino.tlgt.util.MixinFlags;
import net.maxrhino.tlgt.util.ScreenUtils;
import net.maxrhino.tlgt.util.components.widgets.CloseButtonWidget;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.ContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ContainerScreen.class)
public abstract class MixinContainerScreen extends MixinAbstractContainerScreen {

    @Redirect(
            method = "extractBackground",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blit(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIFFIIII)V", ordinal = 0)
    )
    private void the_leaked_gui_true$removeFirstCall(GuiGraphicsExtractor instance, RenderPipeline renderPipeline, Identifier texture, int x, int y, float u, float v, int width, int height, int textureWidth, int textureHeight) {
    }

    @Override
    protected void overrideInit(Operation<Void> original) {
        super.overrideInit(original);

        ContainerScreen instance = (ContainerScreen) (Object) this;

        int imageWidth = ((AbstractContainerScreenAccessor)this).imageWidth();

        int xo = (instance.width - imageWidth) / 2 + 90;
        int yo = (instance.height / 2) - 47;

        ((ScreenAccessor)this).the_leaked_gui_true$addRenderableWidget(new CloseButtonWidget(xo + imageWidth - 8, yo + 4));
    }

    @Redirect(
            method = "extractBackground",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blit(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIFFIIII)V", ordinal = 1)
    )
    private void the_leaked_gui_true$drawGeneric54(GuiGraphicsExtractor graphics, RenderPipeline renderPipeline, Identifier texture,
                                                   int x, int y, float u, float v, int width, int height, int textureWidth, int textureHeight) {
        ContainerScreen instance = (ContainerScreen)(Object)this;

        int imageWidth = ((AbstractContainerScreenAccessor)this).imageWidth();
        int imageHeight = ((AbstractContainerScreenAccessor)this).imageHeight();

        ScreenUtils.drawContainerBackground(
                graphics,
                x + 90, (instance.height / 2) - 47,
                width, 94
        );

        String textureThing = "chest_default";
        Component drawnContainer = MixinFlags.DRAWN_CONTAINER.get();
        if (drawnContainer.equals(Component.translatable("container.barrel"))) {
            textureThing = "barrel";
        } else if (drawnContainer.equals(Component.translatable("container.enderchest"))) {
            textureThing = "chest_ender";
        }

        ScreenUtils.drawContainerBackground(
                graphics,
                x - 90, (instance.height / 2) - ((imageHeight - 90) / 2),
                width, imageHeight - 90,
                textureThing
        );

        int cusX;
        int cusY;

        cusX = (instance.width / 2) + 2;
        cusY = (instance.height / 2) - 42;

        for (int i = 0; i < 9; i++) {
            ScreenUtils.drawSlot(graphics, cusX + 8 + (i * 18), cusY + 65);
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 3; j++) {
                ScreenUtils.drawSlot(graphics, (cusX + 8) + (i * 18), (cusY + 43) - (j * 18));
            }
        }

        cusX = (instance.width / 2) - imageWidth - 2;
        cusY = (instance.height / 2) - ((imageHeight - 90) / 2);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < instance.getMenu().getRowCount(); j++) {
                ScreenUtils.drawSlot(graphics, (cusX + 8) + (i * 18), (cusY + 18) + (j * 18), textureThing);
            }
        }
    }
}
