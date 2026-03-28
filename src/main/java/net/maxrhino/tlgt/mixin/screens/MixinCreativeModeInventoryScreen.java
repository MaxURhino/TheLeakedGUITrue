package net.maxrhino.tlgt.mixin.screens;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import net.maxrhino.tlgt.TheLeakedGUITrue;
import net.maxrhino.tlgt.util.ScreenUtils;
import net.maxrhino.tlgt.util.screens.TestScreen;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.maxrhino.tlgt.util.ScreenUtils.*;

@Mixin(CreativeModeInventoryScreen.class)
public class MixinCreativeModeInventoryScreen {
    @WrapOperation(
            method = "extractBackground",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blit(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIFFIIII)V")
    )
    private void the_leaked_gui_true$onBlitBackground(GuiGraphicsExtractor instance, RenderPipeline renderPipeline, Identifier texture, int x, int y, float u, float v, int width, int height, int textureWidth, int textureHeight, Operation<Void> original) {
        switch (texture.getPath()) {
            case "textures/gui/container/creative_inventory/tab_items.png" ->
                    the_leaked_gui_true$drawTabItems(instance, x, y);
            case "textures/gui/container/creative_inventory/tab_item_search.png" ->
                    the_leaked_gui_true$drawTabItemSearch(instance, x, y);
            case "textures/gui/container/creative_inventory/tab_inventory.png" ->
                    the_leaked_gui_true$drawTabInventory(instance, x, y);
            default ->
                    original.call(instance, renderPipeline, texture, x, y, u, v, width, height, textureWidth, textureHeight);
        }
    }

    @Unique
    private void the_leaked_gui_true$drawTabItems(GuiGraphicsExtractor graphics, int x, int y) {
        ScreenUtils.drawContainerBackground(
                graphics,
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

        drawSlotWithCustomSize(
                graphics,
                x + 175, y + 18,
                12, 110
        );
    }

    @Unique
    private void the_leaked_gui_true$drawTabItemSearch(GuiGraphicsExtractor graphics, int x, int y) {
        the_leaked_gui_true$drawTabItems(graphics, x, y);

        drawSlotWithCustomSize(
                graphics,
                x + 81, y + 5,
                88, 10
        );
    }

    @Unique
    private void the_leaked_gui_true$drawTabInventory(GuiGraphicsExtractor graphics, int x, int y) {
        ScreenUtils.drawContainerBackground(
                graphics,
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
                TheLeakedGUITrue.id("containers/elements/creative_inventory/trash_bin"),
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

    @Inject(
            method = "keyPressed",
            at = @At("HEAD"),
            cancellable = true
    )
    private void the_leaked_gui_true$goToTestScreenOnPress(KeyEvent event, CallbackInfoReturnable<Boolean> cir) {
        KeyMapping keyMapping = Minecraft.getInstance().options.keyInventory;

        if (keyMapping.matches(event)) {
            Minecraft.getInstance().execute(() -> Minecraft.getInstance().setScreen(new TestScreen()));
            cir.setReturnValue(true);
        }
    }
}
