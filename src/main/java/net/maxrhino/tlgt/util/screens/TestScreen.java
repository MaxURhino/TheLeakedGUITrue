package net.maxrhino.tlgt.util.screens;

import net.maxrhino.tlgt.TheLeakedGUITrue;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;

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
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractBackground(graphics, mouseX, mouseY, a);

        graphics.blit(
                RenderPipelines.GUI_TEXTURED,
                TheLeakedGUITrue.id("textures/gui/containers/barrel.png"),
                (this.width - this.imageWidth) / 2,
                (this.height - this.imageHeight) / 2,
                0, 0,
                this.imageWidth, this.imageHeight,
                this.imageWidth, this.imageHeight
        );
    }
}
