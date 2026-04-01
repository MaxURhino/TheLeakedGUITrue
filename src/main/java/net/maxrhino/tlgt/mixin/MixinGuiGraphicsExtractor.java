package net.maxrhino.tlgt.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.maxrhino.tlgt.TheLeakedGUITrue;
import net.maxrhino.tlgt.interfaces.ScreenUtils;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FontDescription;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiGraphicsExtractor.class)
public class MixinGuiGraphicsExtractor implements ScreenUtils {
    @Inject(
            method = "itemCount",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;text(Lnet/minecraft/client/gui/Font;Ljava/lang/String;IIIZ)V")
    )
    private void the_leaked_gui_true$drawHotbarCorner(Font font, ItemStack itemStack, int x, int y, String countText, CallbackInfo ci) {
        GuiGraphicsExtractor instance = (GuiGraphicsExtractor) (Object) this;

        int plusX = 10;
        int plusY = 10;

        instance.blit(
                RenderPipelines.GUI_TEXTURED,
                TheLeakedGUITrue.id("textures/gui/sprites/hud/hotbar_corner.png"),
                x + plusX, y + plusY,
                0, 0,
                6, 6,
                6, 6
        );
    }

    @WrapOperation(
            method = "itemCount",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;text(Lnet/minecraft/client/gui/Font;Ljava/lang/String;IIIZ)V")
    )
    private void the_leaked_gui_true$redirectItemCountText(GuiGraphicsExtractor instance, Font font, String str, int x, int y, int color, boolean dropShadow, Operation<Void> original) {
        Identifier fiveFont = TheLeakedGUITrue.id("five");
        MutableComponent styled = Component.literal(str)
                .withStyle(s -> s.withFont(new FontDescription.Resource(fiveFont)));
        int minusX = -1;
        float minusY = -0.5f;

        instance.pose().pushMatrix();

        instance.pose().translate(x - minusX, y - minusY);
        instance.pose().scale(7f/8f);

        if (str.length() > 1) {
            instance.pose().scale(5f/8f, 1);
            instance.pose().translate(-(minusX * 10), 0);
        }

        instance.text(font, styled, 0, 0, 0xFF686868, false);

        instance.pose().popMatrix();
    }

    // Custom methods

    @Unique
    public void the_leaked_gui_true$drawSlot(int x, int y, String type, boolean isOutput) {
        the_leaked_gui_true$drawSlotWithCustomSize(x, y, 16, 16, type, isOutput);
    }

    @Unique
    public void the_leaked_gui_true$drawSlot(int x, int y, String type) {
        the_leaked_gui_true$drawSlot(x, y, type, false);
    }

    @Override
    public void the_leaked_gui_true$drawSlot(int x, int y, ContainerTypes type) {
        the_leaked_gui_true$drawSlot(x, y, type.getName());
    }

    @Unique
    public void the_leaked_gui_true$drawSlot(int x, int y, SlotPath type) {
        the_leaked_gui_true$drawSlotWithCustomSize(x, y, 16, 16, type);
    }

    @Unique
    public void the_leaked_gui_true$drawSlot(int x, int y) {
        the_leaked_gui_true$drawSlotWithCustomSize(x, y, 16, 16);
    }

    @Unique
    public void the_leaked_gui_true$drawSlotWithCustomSize(int x, int y, int width, int height, SlotPath type) {
        the_leaked_gui_true$drawSlotWithCustomSize(x, y, width + 2, height + 2, type, width + 2, height + 2, 0, 0);
    }

    @Unique
    public void the_leaked_gui_true$drawSlotWithCustomSize(int x, int y, int width, int height, SlotPath type, int spriteWidth, int spriteHeight, int textureX, int textureY) {
        ((GuiGraphicsExtractor)(Object)this).blitSprite(
                RenderPipelines.GUI_TEXTURED,
                TheLeakedGUITrue.id(type.path()),
                spriteWidth, spriteHeight,
                textureX, textureY,
                x - 1, y - 1,
                width, height
        );
    }

    @Unique
    public void the_leaked_gui_true$drawSlotWithCustomSize(int x, int y, int width, int height, String type, boolean isOutput) {
        the_leaked_gui_true$drawSlotWithCustomSize(x, y, width, height, new SlotPath(type, true, isOutput));
    }

    @Unique
    public void the_leaked_gui_true$drawSlotWithCustomSize(int x, int y, int width, int height) {
        the_leaked_gui_true$drawSlotWithCustomSize(x, y, width, height, "default", false);
    }

    @Unique
    public void the_leaked_gui_true$drawContainerBackground(int x, int y, int width, int height, String container) {
        ((GuiGraphicsExtractor)(Object)this).blitSprite(
                RenderPipelines.GUI_TEXTURED,
                TheLeakedGUITrue.id("containers/" + container),
                width, height,
                0, 0,
                x, y,
                width, height
        );
    }

    @Unique
    public void the_leaked_gui_true$drawContainerBackground(int x, int y, int width, int height, ContainerTypes container) {
        the_leaked_gui_true$drawContainerBackground(x, y, width, height, container.getName());
    }

    @Unique
    public void the_leaked_gui_true$drawContainerBackground(int x, int y, int width, int height) {
        the_leaked_gui_true$drawContainerBackground(x, y, width, height, ContainerTypes.DEFAULT);
    }
}
