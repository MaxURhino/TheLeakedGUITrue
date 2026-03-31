package net.maxrhino.tlgt.mixin.screens;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import net.maxrhino.tlgt.TheLeakedGUITrue;
import net.maxrhino.tlgt.interfaces.GuiGraphicsExtractorDuckInterface;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(InventoryScreen.class)
public class MixinInventoryScreen {
    @WrapOperation(
            method = "extractBackground",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blit(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIFFIIII)V")
    )
    private void the_leaked_gui_true$changeBackgroundRenderingMethod(GuiGraphicsExtractor graphics, RenderPipeline renderPipeline, Identifier texture, int x, int y, float u, float v, int width, int height, int textureWidth, int textureHeight, Operation<Void> original) {
        GuiGraphicsExtractorDuckInterface mixined = (GuiGraphicsExtractorDuckInterface) graphics;

        mixined.the_leaked_gui_true$drawContainerBackground(
                x, y,
                176, 166
        );

        for (int i = 0; i < 9; i++) {
            mixined.the_leaked_gui_true$drawSlot(x + 8 + (i * 18), y + 142);
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 3; j++) {
                mixined.the_leaked_gui_true$drawSlot(x + 8 + (i * 18), y + 120 - (j * 18));
            }
        }

        for (int i = 0; i < 4; i++) {
            mixined.the_leaked_gui_true$drawSlot(x + 8, y + 8 + (i * 18));
        }

        graphics.blitSprite(
                RenderPipelines.GUI_TEXTURED,
                TheLeakedGUITrue.id("containers/elements/character_background"),
                51, 72,
                0, 0,
                x + 25, y + 7,
                51, 72
        );

        mixined.the_leaked_gui_true$drawSlot(x + 77, y + 62);

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                mixined.the_leaked_gui_true$drawSlot(x + 98 + (i * 18), y + 18 + (j * 18));
            }
        }

        mixined.the_leaked_gui_true$drawSlot(x + 154, y + 28);

        graphics.blitSprite(
                RenderPipelines.GUI_TEXTURED,
                TheLeakedGUITrue.id("containers/elements/recipe_arrow"),
                16, 13,
                0, 0,
                x + 135, y + 29,
                16, 13
        );
    }
}
