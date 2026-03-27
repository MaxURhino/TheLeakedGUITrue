package net.maxrhino.tlgt.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import net.maxrhino.tlgt.TheLeakedGUITrue;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderAttribute;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Objects;

import static net.maxrhino.tlgt.util.ScreenUtils.*;

@Mixin(CreativeModeInventoryScreen.class)
public class MixinCreativeModeInventoryScreen {
    @WrapOperation(
            method = "extractBackground",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blit(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIFFIIII)V")
    )
    private void the_leaked_gui_true$onBlitBackground(GuiGraphicsExtractor instance, RenderPipeline renderPipeline, Identifier texture, int x, int y, float u, float v, int width, int height, int textureWidth, int textureHeight, Operation<Void> original) {
        if (texture.getPath().equals("textures/gui/container/creative_inventory/tab_items.png")) {
            the_leaked_gui_true$drawTabItems(instance, x, y);
        } else if (texture.getPath().equals("textures/gui/container/creative_inventory/tab_item_search.png")) {
            the_leaked_gui_true$drawTabItemSearch(instance, x, y);
        } else if (texture.getPath().equals("textures/gui/container/creative_inventory/tab_inventory.png")) {
            the_leaked_gui_true$drawTabInventory(instance, x, y);
        } else {
            original.call(instance, renderPipeline, texture, x, y, u, v, width, height, textureWidth, textureHeight);
        }
    }

    @Unique
    private void the_leaked_gui_true$drawTabItems(GuiGraphicsExtractor graphics, int x, int y) {
        graphics.blitSprite(
                RenderPipelines.GUI_TEXTURED,
                TheLeakedGUITrue.id("containers/default"),
                195, 136,
                0, 0,
                x, y,
                195, 136
        );

        for (int i = 0; i < 9; i++) {
            drawSlot(graphics, x + 9 + (i * 18), y + 112);
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                drawSlot(graphics, x + 9 + (i * 18), y + 18 + (j * 18));
            }
        }

        graphics.blitSprite(
                RenderPipelines.GUI_TEXTURED,
                TheLeakedGUITrue.id("containers/elements/scroller_background"),
                14, 112,
                0, 0,
                x + 174, y + 17,
                14, 112
        );
    }

    @Unique
    private void the_leaked_gui_true$drawTabItemSearch(GuiGraphicsExtractor graphics, int x, int y) {
        the_leaked_gui_true$drawTabItems(graphics, x, y);

        graphics.blitSprite(
                RenderPipelines.GUI_TEXTURED,
                TheLeakedGUITrue.id("containers/elements/creative_searchbar"),
                90, 12,
                0, 0,
                x + 80, y + 4,
                90, 12
        );
    }

    @Unique
    private void the_leaked_gui_true$drawTabInventory(GuiGraphicsExtractor graphics, int x, int y) {
        graphics.blitSprite(
                RenderPipelines.GUI_TEXTURED,
                TheLeakedGUITrue.id("containers/default"),
                195, 136,
                0, 0,
                x, y,
                195, 136
        );

        for (int i = 0; i < 9; i++) {
            drawSlot(graphics, x + 9 + (i * 18), y + 112);
        }

        drawSlot(graphics, x + 173, y + 112);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 3; j++) {
                drawSlot(graphics, x + 9 + (i * 18), y + 54 + (j * 18));
            }
        }

        graphics.blitSprite(
                RenderPipelines.GUI_TEXTURED,
                TheLeakedGUITrue.id("containers/elements/creative_trash_bin"),
                9, 13,
                0, 0,
                x + 177, y + 114,
                9, 13
        );

        graphics.blitSprite(
                RenderPipelines.GUI_TEXTURED,
                TheLeakedGUITrue.id("containers/elements/character_background"),
                34, 45,
                0, 0,
                x + 72, y + 5,
                34, 45
        );

        drawSlot(graphics, x + 108, y + 6);
        drawSlot(graphics, x + 108, y + 33);
        drawSlot(graphics, x + 54, y + 6);
        drawSlot(graphics, x + 54, y + 33);
        drawSlot(graphics, x + 35, y + 20);
    }
}
