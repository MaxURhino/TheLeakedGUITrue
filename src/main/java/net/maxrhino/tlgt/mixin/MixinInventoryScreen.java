package net.maxrhino.tlgt.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import net.maxrhino.tlgt.TheLeakedGUITrue;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(InventoryScreen.class)
public class MixinInventoryScreen {
    @WrapOperation(
            method = "extractBackground",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blit(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIFFIIII)V")
    )
    private void the_leaked_gui_true$changeBackgroundRenderingMethod(GuiGraphicsExtractor graphics, RenderPipeline renderPipeline, Identifier texture, int x, int y, float u, float v, int width, int height, int textureWidth, int textureHeight, Operation<Void> original) {
        /*
        graphics.blit(
                RenderPipelines.GUI_TEXTURED,
                TheLeakedGUITrue.id("textures/gui/containers/default.png"),
                x,
                y,
                0, 0,
                176, 166,
                176, 166
        );
         */

        for (int i = 0; i < 9; i++) {
            drawSlot(graphics, x + 8 + (i * 18), y + 142);
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 3; j++) {
                drawSlot(graphics, x + 8 + (i * 18), y + 120 - (j * 18));
            }
        }

        for (int i = 0; i < 4; i++) {
            drawSlot(graphics, x + 8, y + 8 + (i * 18));
        }

        graphics.blit(
                RenderPipelines.GUI_TEXTURED,
                TheLeakedGUITrue.id("textures/gui/containers/elements/character_background.png"),
                x + 25, y + 7,
                0, 0,
                51, 72,
                51, 72
        );

        drawSlot(graphics, x + 77, y + 62);

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                drawSlot(graphics, x + 98 + (i * 18), y + 18 + (j * 18));
            }
        }

        drawSlot(graphics, x + 154, y + 28);

        graphics.blit(
                RenderPipelines.GUI_TEXTURED,
                TheLeakedGUITrue.id("textures/gui/containers/elements/recipe_arrow.png"),
                x + 135, y + 29,
                0, 0,
                16, 13,
                16, 13
        );
    }

    @Unique
    private void drawSlot(GuiGraphicsExtractor graphics, int x, int y) {
        graphics.blit(
                RenderPipelines.GUI_TEXTURED,
                Identifier.withDefaultNamespace("textures/gui/sprites/container/slot.png"),
                x - 1,
                y - 1,
                0, 0,
                18, 18,
                18, 18
        );
    }
}
