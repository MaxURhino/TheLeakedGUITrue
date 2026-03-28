package net.maxrhino.tlgt.util;

import net.maxrhino.tlgt.TheLeakedGUITrue;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;

public class ScreenUtils {
    public static void drawSlot(GuiGraphicsExtractor graphics, int x, int y, String type, boolean isOutput) {
        drawSlotWithCustomSize(graphics, x, y, 16, 16, type, isOutput);
    }

    public static void drawSlot(GuiGraphicsExtractor graphics, int x, int y, SlotPath type, boolean isOutput) {
        drawSlotWithCustomSize(graphics, x, y, 16, 16, type);
    }

    public static void drawSlot(GuiGraphicsExtractor graphics, int x, int y, String type) {
        drawSlot(graphics, x, y, type, false);
    }

    public static void drawSlot(GuiGraphicsExtractor graphics, int x, int y, SlotPath type) {
        drawSlot(graphics, x, y, type, false);
    }

    public static void drawSlot(GuiGraphicsExtractor graphics, int x, int y) {
        drawSlotWithCustomSize(graphics, x, y, 16, 16);
    }

    public static void drawSlotWithCustomSize(GuiGraphicsExtractor graphics, int x, int y, int width, int height, SlotPath type) {
        drawSlotWithCustomSize(graphics, x, y, width + 2, height + 2, type, width + 2, height + 2, 0, 0);
    }

    public static void drawSlotWithCustomSize(GuiGraphicsExtractor graphics, int x, int y, int width, int height, SlotPath type, int spriteWidth, int spriteHeight, int textureX, int textureY) {
        graphics.blitSprite(
                RenderPipelines.GUI_TEXTURED,
                TheLeakedGUITrue.id(type.path()),
                spriteWidth, spriteHeight,
                textureX, textureY,
                x - 1, y - 1,
                width, height
        );
    }

    public static void drawSlotWithCustomSize(GuiGraphicsExtractor graphics, int x, int y, int width, int height, String type, boolean isOutput) {
        drawSlotWithCustomSize(graphics, x, y, width, height, new SlotPath(type, true, isOutput));
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

    public record SlotPath(String path, boolean isNormalSlot, boolean isOutputSlot) {
        @Override
        public String path() {
            if (isNormalSlot) {
                return "hud/slots/new/" + (isOutputSlot ? "output_" : "item_") + path;
            }
            return path;
        }

        public SlotPath(String path, boolean isNormalSlot) {
            this(path, isNormalSlot, false);
        }
    }
}
