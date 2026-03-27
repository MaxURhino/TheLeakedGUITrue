package net.maxrhino.tlgt.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.maxrhino.tlgt.TheLeakedGUITrue;
import net.maxrhino.tlgt.util.MixinFlags;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FontDescription;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiGraphicsExtractor.class)
public class MixinGuiGraphicsExtractor {
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
}
