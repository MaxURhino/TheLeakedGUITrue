package net.maxrhino.tlgt.util.screens;

import net.maxrhino.tlgt.TheLeakedGUITrue;
import net.maxrhino.tlgt.util.components.widgets.CloseButtonWidget;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import org.jspecify.annotations.NonNull;

import static net.maxrhino.tlgt.util.ScreenUtils.*;

public class TestScreen extends Screen {
    private int imageWidth;
    private int imageHeight;

    public TestScreen() {
        super(Component.translatable("container.creative"));
    }

    @Override
    protected void init() {
        super.init();

        this.imageWidth = 176;
        this.imageHeight = 166;

        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;

        this.addRenderableWidget(new CloseButtonWidget(x + imageWidth - 8, y + 4));
    }

    @Override
    public void extractBackground(@NonNull GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractBackground(graphics, mouseX, mouseY, a);

        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;

        graphics.blitSprite(
                RenderPipelines.GUI_TEXTURED,
                TheLeakedGUITrue.id("containers/default"),
                176, 166,
                0, 0,
                x, y,
                176, 166
        );

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

        graphics.blitSprite(
                RenderPipelines.GUI_TEXTURED,
                TheLeakedGUITrue.id("containers/elements/character_background"),
                51, 72,
                0, 0,
                x + 25, y + 7,
                51, 72
        );

        drawSlot(graphics, x + 77, y + 62);

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                drawSlot(graphics, x + 98 + (i * 18), y + 18 + (j * 18));
            }
        }

        drawSlot(graphics, x + 154, y + 28);

        graphics.blitSprite(
                RenderPipelines.GUI_TEXTURED,
                TheLeakedGUITrue.id("containers/elements/recipe_arrow"),
                16, 13,
                0, 0,
                x + 135, y + 29,
                16, 13
        );
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public boolean keyPressed(KeyEvent event) {
        KeyMapping keyMapping = this.minecraft.options.keyInventory;

        if (keyMapping.matches(event)) {
            onClose();
            return true;
        }

        return super.keyPressed(event);
    }
}
