package net.maxrhino.tlgt.util.components.widgets;

import com.mojang.blaze3d.platform.cursor.CursorTypes;
import net.maxrhino.tlgt.TheLeakedGUITrue;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;

public class CloseButtonWidget extends AbstractWidget {
    public CloseButtonWidget(int x, int y) {
        super(x, y, 5, 5, Component.empty());
    }

    @Override
    protected void extractWidgetRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
        graphics.blitSprite(
                RenderPipelines.GUI_TEXTURED,
                TheLeakedGUITrue.id("containers/elements/close_button"),
                width, height,
                0, 0,
                getX(), getY(),
                width, height
        );

        if (isHovered()) {
            graphics.requestCursor(CursorTypes.POINTING_HAND);
        }
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput output) {
    }

    @Override
    public void onClick(final MouseButtonEvent event, final boolean doubleClick) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.screen != null) {
            minecraft.execute(() -> minecraft.screen.onClose());
        }
    }
}
