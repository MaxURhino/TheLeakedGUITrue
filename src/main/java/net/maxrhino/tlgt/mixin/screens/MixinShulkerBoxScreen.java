package net.maxrhino.tlgt.mixin.screens;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import net.maxrhino.tlgt.interfaces.ScreenUtils;
import net.maxrhino.tlgt.interfaces.ShulkerBoxMenuDuck;
import net.maxrhino.tlgt.mixin.accessors.AbstractContainerScreenAccessor;
import net.maxrhino.tlgt.mixin.accessors.ScreenAccessor;
import net.maxrhino.tlgt.util.components.widgets.CloseButtonWidget;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.ShulkerBoxScreen;
import net.minecraft.resources.Identifier;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.DyeColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ShulkerBoxScreen.class)
public class MixinShulkerBoxScreen extends MixinAbstractContainerScreen {
    @Override
    protected void overrideInit(Operation<Void> original) {
        original.call();

        ShulkerBoxScreen instance = (ShulkerBoxScreen)(Object)this;

        int imageWidth = ((AbstractContainerScreenAccessor)this).imageWidth();

        int xo = (instance.width - imageWidth) / 2 + 90;
        int yo = (instance.height / 2) - 47;

        ((ScreenAccessor) this).the_leaked_gui_true$addRenderableWidget(new CloseButtonWidget(xo + imageWidth - 8, yo + 4));
    }

    @Override
    protected boolean the_leaked_gui_true$overrideHasClickedOutside(double mx, double my, int xo, int yo, Operation<Boolean> original) {
        return super.the_leaked_gui_true$overrideHasClickedOutside(mx, my, xo, yo, original);
    }

    @WrapOperation(method = "extractBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blit(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIFFIIII)V"))
    private void the_leaked_gui_true$changeBackgrondTexture(GuiGraphicsExtractor graphics, RenderPipeline renderPipeline, Identifier texture,
                                                            int x, int y, float u, float v, int width, int height, int textureWidth,
                                                            int textureHeight, Operation<Void> original) {
        ScreenUtils mixined = (ScreenUtils) graphics;

        ShulkerBoxScreen instance = (ShulkerBoxScreen) (Object) this;

        int xo;
        int yo;

        xo = x + 90;
        yo = (instance.height / 2) - 47;

        mixined.the_leaked_gui_true$drawContainerBackground(
                xo, yo,
                width, 94
        );

        int cusX = (instance.width / 2) + 2;
        int cusY = (instance.height / 2) - 41;

        for (int i = 0; i < 9; i++) {
            mixined.the_leaked_gui_true$drawSlot(cusX + 8 + (i * 18), cusY + 65);
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 3; j++) {
                mixined.the_leaked_gui_true$drawSlot((cusX + 8) + (i * 18), (cusY + 43) - (j * 18));
            }
        }


        xo = x - 90;
        yo = (instance.height / 2) - ((height - 90) / 2);

        AbstractContainerMenu menu = ((AbstractContainerScreenAccessor)this).menu();

        ShulkerBoxMenuDuck duck = (ShulkerBoxMenuDuck) menu;

        int id = duck.the_leaked_gui_true$getDataSlot().get();
        DyeColor dyeColor;
        if (id > -1) {
            dyeColor = DyeColor.byId(id);
        } else {
            dyeColor = null;
        }

        ScreenUtils.ContainerTypes containerType = ScreenUtils.ContainerTypes
                .createWithColor(
                        ScreenUtils.ContainerTypes.SHULKER_BOX,
                        dyeColor
                );

        mixined.the_leaked_gui_true$drawContainerBackground(
                xo, yo,
                width, height - 90,
                containerType
        );

        for (int yc = 0; yc < 3; yc++) {
            for (int xc = 0; xc < 9; xc++) {
                mixined.the_leaked_gui_true$drawSlot(xo + xc * 18 + 8, yo + yc * 18 + 17, containerType);
            }
        }
    }
}
