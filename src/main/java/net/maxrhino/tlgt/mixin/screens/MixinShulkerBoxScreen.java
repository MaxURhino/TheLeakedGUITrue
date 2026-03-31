package net.maxrhino.tlgt.mixin.screens;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import net.maxrhino.tlgt.interfaces.GuiGraphicsExtractorDuckInterface;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.ShulkerBoxScreen;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ShulkerBoxScreen.class)
public class MixinShulkerBoxScreen {
    @WrapOperation(method = "extractBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blit(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIFFIIII)V"))
    private void the_leaked_gui_true$changeBackgrondTexture(GuiGraphicsExtractor graphics, RenderPipeline renderPipeline, Identifier texture,
                                                            int x, int y, float u, float v, int width, int height, int textureWidth,
                                                            int textureHeight, Operation<Void> original) {
        GuiGraphicsExtractorDuckInterface mixined = (GuiGraphicsExtractorDuckInterface) graphics;

        mixined.the_leaked_gui_true$drawContainerBackground(x, y + (height - 90), width, 90);
        mixined.the_leaked_gui_true$drawContainerBackground(
                x, y,
                width, height - 90,
                "shulker_box"
        );
    }
}
