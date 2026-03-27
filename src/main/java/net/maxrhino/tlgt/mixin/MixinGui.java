package net.maxrhino.tlgt.mixin;

import net.maxrhino.tlgt.util.MixinFlags;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class MixinGui {


    @Inject(method = "extractSlot", at = @At("HEAD"))
    private void the_leaked_gui_true$onExtractSlotEnter(GuiGraphicsExtractor graphics, int x, int y, DeltaTracker deltaTracker, Player player, ItemStack itemStack, int seed, CallbackInfo ci) {
        MixinFlags.IS_IN_EXTRACT_SLOT.set(true);
    }

    @Inject(method = "extractSlot", at = @At("RETURN"))
    private void the_leaked_gui_true$onExtractSlotExit(GuiGraphicsExtractor graphics, int x, int y, DeltaTracker deltaTracker, Player player, ItemStack itemStack, int seed, CallbackInfo ci) {
        MixinFlags.IS_IN_EXTRACT_SLOT.set(false);
    }

    @ModifyConstant(
            method = "extractItemHotbar",
            constant = @Constant(
                    intValue = 182
            )
    )
    private int the_leaked_gui_true$changeHotbarWidth(int original) {
        return 184;
    }

    @ModifyConstant(
            method = "extractItemHotbar",
            constant = @Constant(
                    intValue = 91
            )
    )
    private int the_leaked_gui_true$changeHotbarHalfWidth(int original) {
        return 92;
    }

    @ModifyConstant(
            method = "extractItemHotbar",
            constant = @Constant(
                    intValue = 22,
                    ordinal = 1
            )
    )
    private int the_leaked_gui_true$changeHotbarHeight(int original) {
        return 24;
    }

    @ModifyConstant(
            method = "extractItemHotbar",
            constant = @Constant(
                    intValue = 22
            )
    )
    private int the_leaked_gui_true$raiseHotbar(int original) {
        return 29;
    }

    @ModifyConstant(
            method = "extractItemHotbar",
            constant = @Constant(
                    intValue = 23,
                    ordinal = 1
            )
    )
    private int the_leaked_gui_true$raiseHotbarAgain(int original) {
        return 28;
    }

    @ModifyConstant(
            method = "extractItemHotbar",
            constant = @Constant(
                    intValue = 23,
                    ordinal = 2
            )
    )
    private int the_leaked_gui_true$raiseHotbarOnceAgain(int original) {
        return 28;
    }

    @ModifyConstant(
            method = "extractItemHotbar",
            constant = @Constant(
                    intValue = 1,
                    ordinal = 0
            )
    )
    private int the_leaked_gui_true$changeHotbarXPosition(int original) {
        return 0;
    }

    @ModifyConstant(
            method = "extractItemHotbar",
            constant = @Constant(
                    intValue = 3
            )
    )
    private int the_leaked_gui_true$raiseHotbarThings(int original) {
        return 10;
    }

    @ModifyConstant(
            method = "extractItemHotbar",
            constant = @Constant(
                    intValue = 23,
                    ordinal = 0
            )
    )
    private int the_leaked_gui_true$changeSelectorHeight(int original) {
        return 24;
    }
}
