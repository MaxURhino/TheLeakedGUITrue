package net.maxrhino.tlgt.mixin.menus;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.maxrhino.tlgt.util.MixinFlags;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.MenuType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ChestMenu.class)
public class MixinChestMenu {
    @ModifyConstant(
            method = "<init>(Lnet/minecraft/world/inventory/MenuType;ILnet/minecraft/world/entity/player/Inventory;Lnet/minecraft/world/Container;I)V",
            constant = @Constant(
                    intValue = 8,
                    ordinal = 0
            )
    )
    private int the_leaked_gui_true$moveChestSlotsX(int original) {
        return -82;
    }

    @ModifyConstant(
            method = "<init>(Lnet/minecraft/world/inventory/MenuType;ILnet/minecraft/world/entity/player/Inventory;Lnet/minecraft/world/Container;I)V",
            constant = @Constant(
                    intValue = 18,
                    ordinal = 1
            )
    )
    private int the_leaked_gui_true$moveChestSlotsY(int original) {
        return 63;
    }

    @WrapOperation(
            method = "<init>(Lnet/minecraft/world/inventory/MenuType;ILnet/minecraft/world/entity/player/Inventory;Lnet/minecraft/world/Container;I)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/inventory/ChestMenu;addStandardInventorySlots(Lnet/minecraft/world/Container;II)V"
            )
    )
    private void the_leaked_gui_true$moveInventorySlots(ChestMenu instance, Container itemStacks, int left, int top, Operation<Void> original) {
        MenuType<?> menuType = instance.menuType;
        MixinFlags.IS_GENERIC_9x3.set(menuType == MenuType.GENERIC_9x3);
        int yLevel = menuType == MenuType.GENERIC_9x3 ? 49 : 76;
        original.call(instance, itemStacks, 98, yLevel);
    }
}
