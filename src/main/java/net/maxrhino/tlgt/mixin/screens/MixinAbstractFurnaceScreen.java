package net.maxrhino.tlgt.mixin.screens;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import net.maxrhino.tlgt.TheLeakedGUITrue;
import net.maxrhino.tlgt.mixin.accessors.AbstractContainerScreenAccessor;
import net.maxrhino.tlgt.mixin.accessors.ScreenAccessor;
import net.maxrhino.tlgt.util.ScreenUtils;
import net.maxrhino.tlgt.util.components.widgets.CloseButtonWidget;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.navigation.ScreenPosition;
import net.minecraft.client.gui.screens.inventory.AbstractFurnaceScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractFurnaceScreen.class)
public class MixinAbstractFurnaceScreen {
    @Unique
    private int customX, customY;

    @Inject(method = "init", at = @At("TAIL"))
    private void the_leaked_gui_true$addExitButton(CallbackInfo ci) {
        AbstractFurnaceScreen<?> instance = (AbstractFurnaceScreen<?>) (Object) this;

        int imageWidth = ((AbstractContainerScreenAccessor)this).imageWidth();
        int imageHeight = ((AbstractContainerScreenAccessor)this).imageHeight();

        int xo = (instance.width - imageWidth) / 2 + 90;
        customX = (instance.width / 2) - (imageWidth / 2);
        int yo = (instance.height / 2) - 47;
        customY = (instance.height / 2) - (imageHeight / 2);

        ((ScreenAccessor)this).the_leaked_gui_true$addRenderableWidget(new CloseButtonWidget(xo + imageWidth - 8, yo + 4));
    }

    @WrapOperation(
            method = "extractBackground",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blit(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIFFIIII)V",
                    ordinal = 0
            )
    )
    private void the_leaked_gui_true$drawFurnaceBackground(GuiGraphicsExtractor graphics, RenderPipeline renderPipeline, Identifier texture,
                                                           int x, int y, float u, float v, int width, int height, int textureWidth, int textureHeight,
                                                           Operation<Void> original) {
        AbstractFurnaceScreen<?> instance = (AbstractFurnaceScreen<?>) (Object) this;

        int cusX;
        int cusY;

        // Furnace

        int xo = x - 90;
        int yo = (instance.height / 2) - ((height - 90) / 2);

        ScreenUtils.drawContainerBackground(
                graphics,
                xo, yo,
                width, height - 90,
                "furnace"
        );

        ScreenUtils.drawSlot(
                graphics,
                x - 41,
                y + 57,
                new ScreenUtils.SlotPath("furnace", true, false)
        );

        graphics.pose().pushMatrix();

        graphics.pose().translate(-0.5f, -0.5f);

        ScreenUtils.drawSlotWithCustomSize(
                graphics,
                x - 41,
                y + 87,
                17, 17,
                new ScreenUtils.SlotPath("containers/elements/furnace/fuel_slot_flame_bg", false)
        );

        graphics.pose().popMatrix();

        ScreenUtils.drawSlotWithCustomSize(
                graphics,
                x + 23,
                y + 73,
                20, 20,
                new ScreenUtils.SlotPath("furnace", true, true)
        );

        // Inventory

        xo = x + 90;
        yo = (instance.height / 2) - 47;

        ScreenUtils.drawContainerBackground(
                graphics,
                xo, yo,
                width, 94
        );

        cusX = (instance.width / 2) + 2;
        cusY = (instance.height / 2) - 41;

        for (int i = 0; i < 9; i++) {
            ScreenUtils.drawSlot(graphics, cusX + 8 + (i * 18), cusY + 65);
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 3; j++) {
                ScreenUtils.drawSlot(graphics, (cusX + 8) + (i * 18), (cusY + 43) - (j * 18));
            }
        }

        graphics.blitSprite(
                RenderPipelines.GUI_TEXTURED,
                TheLeakedGUITrue.id("containers/elements/furnace/burn_progress_empty"),
                x - 11,
                y + 75,
                16, 16
        );
    }

    @WrapOperation(
            method = "extractBackground",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIIIIIII)V",
                    ordinal = 0
            )
    )
    private void the_leaked_gui_true$changeLitIndicator(GuiGraphicsExtractor graphics, RenderPipeline renderPipeline, Identifier location,
                                                        int spriteWidth, int spriteHeight, int textureX, int textureY, int x, int y,
                                                        int width, int height, Operation<Void> original) {
        graphics.pose().pushMatrix();

        graphics.pose().translate(-0.5f, -0.5f);

        int indicatorHeight = Math.round(height / (14f/19f));
        int indicatorTextureY = 19 - indicatorHeight;

        ScreenUtils.drawSlotWithCustomSize(
                graphics,
                customX - 41,
                customY + 87 + indicatorTextureY,
                19, indicatorHeight,
                new ScreenUtils.SlotPath("containers/elements/furnace/fuel_slot_flame_fill", false),
                19, 19,
                0, indicatorTextureY
        );

        graphics.pose().popMatrix();
    }

    @Inject(
            method = "getRecipeBookButtonPosition",
            at = @At(
                    "HEAD"
            ),
            cancellable = true
    )
    private void the_leaked_gui_true$getRecipeBookButtonPosition(CallbackInfoReturnable<ScreenPosition> cir) {
        AbstractFurnaceScreen<?> instance = (AbstractFurnaceScreen<?>) (Object) this;

        int yo = (instance.height / 2) - ((((AbstractContainerScreenAccessor)this).imageHeight() - 90) / 2);

        cir.setReturnValue(new ScreenPosition(-200, yo + 24));
    }

    @WrapOperation(
            method = "extractBackground",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIIIIIII)V",
                    ordinal = 1
            )
    )
    private void the_leaked_gui_true$changeBurnSprite(GuiGraphicsExtractor graphics, RenderPipeline renderPipeline, Identifier location,
                                                      int spriteWidth, int spriteHeight, int textureX, int textureY, int x, int y,
                                                      int width, int height, Operation<Void> original) {
        int burnProgressWidth = Mth.ceil(((AbstractFurnaceMenu)(((AbstractContainerScreenAccessor)this).menu())).getBurnProgress() * 5.0F);
        original.call(graphics, renderPipeline, TheLeakedGUITrue.id("containers/elements/furnace/burn_progress"), 5, spriteHeight, textureX, textureY, x - 84, y + 41, burnProgressWidth, height);
    }
}
