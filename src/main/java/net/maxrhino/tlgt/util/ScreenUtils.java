package net.maxrhino.tlgt.util;

import net.maxrhino.tlgt.TheLeakedGUITrue;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;

public class ScreenUtils {
    public static void drawSlot(GuiGraphicsExtractor graphics, int x, int y, String type, boolean isOutput) {
        drawSlotWithCustomSize(graphics, x, y, 16, 16, type, isOutput);
    }

    public static void drawSlot(GuiGraphicsExtractor graphics, int x, int y, String type) {
        drawSlot(graphics, x, y, type, false);
    }

    public static void drawSlot(GuiGraphicsExtractor graphics, int x, int y) {
        drawSlotWithCustomSize(graphics, x, y, 16, 16);
    }

    public static void drawSlotWithCustomSize(GuiGraphicsExtractor graphics, int x, int y, int width, int height, String type, boolean isOutput) {
        graphics.blitSprite(
                RenderPipelines.GUI_TEXTURED,
                TheLeakedGUITrue.id("hud/slots/new/" + (isOutput ? "output_" : "item_") + type),
                width + 2, height + 2,
                0, 0,
                x - 1, y - 1,
                width + 2, height + 2
        );
    }

    public static void drawSlotWithCustomSize(GuiGraphicsExtractor graphics, int x, int y, int width, int height) {
        drawSlotWithCustomSize(graphics, x, y, width, height, "default", false);
    }

    public static void drawContainerBackground(GuiGraphicsExtractor graphics, int x, int y, int width, int height, String container) {
        graphics.blitSprite(
                RenderPipelines.GUI_TEXTURED,
                TheLeakedGUITrue.id("containers/" + container),
                width, height,
                0, 0,
                x, y,
                width, height
        );
    }

    public static void drawContainerBackground(GuiGraphicsExtractor graphics, int x, int y, int width, int height) {
        drawContainerBackground(graphics, x, y, width, height, "default");
    }
}
