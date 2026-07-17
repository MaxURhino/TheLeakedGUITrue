package net.maxrhino.tlgt.mixin.menus;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.maxrhino.tlgt.util.MixinFlags;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.CraftingMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(CraftingMenu.class)
public class MixinCraftingMenu {
    @WrapOperation(
            method = "<init>(ILnet/minecraft/world/entity/player/Inventory;Lnet/minecraft/world/inventory/ContainerLevelAccess;)V",
            at = @At(
                value = "INVOKE",
                target = "Lnet/minecraft/world/inventory/CraftingMenu;addStandardInventorySlots(Lnet/minecraft/world/Container;II)V"
            )
    )
    private void the_leaked_gui_true$moveInventorySlots(CraftingMenu instance, Container container, int left, int top, Operation<Void> original) {
        MixinFlags.IS_GENERIC_9x3.set(true);
        original.call(instance, container, left, top);
    }
}
