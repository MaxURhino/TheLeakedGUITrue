package net.maxrhino.tlgt.util;

import net.maxrhino.tlgt.TheLeakedGUITrue;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;

public class ScreenUtils {
    public static void drawSlot(GuiGraphicsExtractor graphics, int x, int y) {
        drawSlotWithCustomSize(graphics, x, y, 16, 16);
    }

    public static void drawSlotWithCustomSize(GuiGraphicsExtractor graphics, int x, int y, int width, int height) {
        graphics.blitSprite(
                RenderPipelines.GUI_TEXTURED,
                TheLeakedGUITrue.id("hud/slot"),
                width + 2, width + 2,
                0, 0,
                x - 1, y - 1,
                width + 2, height + 2
        );
    }
}
