package net.maxrhino.tlgt.mixin.menus;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.maxrhino.tlgt.util.MixinFlags;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.ShulkerBoxMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ShulkerBoxMenu.class)
public class MixinShulkerBoxMenu {
    @ModifyConstant(
            method = "<init>(ILnet/minecraft/world/entity/player/Inventory;Lnet/minecraft/world/Container;)V",
            constant = @Constant(
                    intValue = 8,
                    ordinal = 0
            )
    )
    private int the_leaked_gui_true$moveShulkerBoxSlotsX(int original) {
        return -83;
    }

    @ModifyConstant(
            method = "<init>(ILnet/minecraft/world/entity/player/Inventory;Lnet/minecraft/world/Container;)V",
            constant = @Constant(
                    intValue = 18,
                    ordinal = 1
            )
    )
    private int the_leaked_gui_true$moveShulkerBoxSlotsY(int original) {
        return 62;
    }

    @WrapOperation(
            method = "<init>(ILnet/minecraft/world/entity/player/Inventory;Lnet/minecraft/world/Container;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/inventory/ShulkerBoxMenu;addStandardInventorySlots(Lnet/minecraft/world/Container;II)V"
            )
    )
    private void the_leaked_gui_true$moveInventorySlots(ShulkerBoxMenu instance, Container itemStacks, int x, int y, Operation<Void> original) {
        MixinFlags.IS_GENERIC_9x3.set(true);
        original.call(instance, itemStacks, 98, 49);
    }
}
