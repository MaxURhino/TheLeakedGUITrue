package net.maxrhino.tlgt.mixin;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import net.maxrhino.tlgt.TheLeakedGUITrue;
import net.maxrhino.tlgt.util.ScreenUtils;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.ContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ContainerScreen.class)
public class MixinContainerScreen {
    @Unique
    private int yo;

    @Redirect(
            method = "extractBackground",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blit(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIFFIIII)V", ordinal = 0)
    )
    private void the_leaked_gui_true$removeFirstCall(GuiGraphicsExtractor instance, RenderPipeline renderPipeline, Identifier texture, int x, int y, float u, float v, int width, int height, int textureWidth, int textureHeight) {
        this.yo = y;
    }

    @Redirect(
            method = "extractBackground",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blit(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIFFIIII)V", ordinal = 1)
    )
    private void the_leaked_gui_true$drawGeneric54(GuiGraphicsExtractor graphics, RenderPipeline renderPipeline, Identifier texture,
                                                   int x, int y, float u, float v, int width, int height, int textureWidth, int textureHeight) {
        ContainerScreen instance = (ContainerScreen)(Object)this;

        int imageWidth = instance.imageWidth;
        int imageHeight = instance.imageHeight;

        graphics.blitSprite(
                RenderPipelines.GUI_TEXTURED,
                TheLeakedGUITrue.id("containers/default"),
                width, 94,
                0, 0,
                x + 90, (instance.height / 2) - 47,
                width, 94
        );

        graphics.blitSprite(
                RenderPipelines.GUI_TEXTURED,
                TheLeakedGUITrue.id("containers/chest_default"),
                width, imageHeight - 90,
                0, 0,
                x - 90, (instance.height / 2) - ((imageHeight - 90) / 2),
                width, imageHeight - 90
        );

        int cusX;
        int cusY;

        cusX = (instance.width / 2) + imageWidth + 2;
        cusY = (instance.height / 2) - 47;

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
                ScreenUtils.drawSlot(graphics, (cusX + 8) + (i * 18), (cusY + 18) + (j * 18), "chest_default");
            }
        }
    }
}
