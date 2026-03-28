package net.maxrhino.tlgt.mixin.menus;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.maxrhino.tlgt.util.MixinFlags;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(AbstractFurnaceMenu.class)
public class MixinAbstractFurnaceMenu {
    @ModifyConstant(
            method = "<init>(Lnet/minecraft/world/inventory/MenuType;Lnet/minecraft/world/item/crafting/RecipeType;Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/world/inventory/RecipeBookType;ILnet/minecraft/world/entity/player/Inventory;Lnet/minecraft/world/Container;Lnet/minecraft/world/inventory/ContainerData;)V",
            constant = @Constant(
                    intValue = 56
            )
    )
    private int the_leaked_gui_true$moveFurnaceSlotsX(int original) {
        return -41;
    }

    @ModifyConstant(
            method = "<init>(Lnet/minecraft/world/inventory/MenuType;Lnet/minecraft/world/item/crafting/RecipeType;Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/world/inventory/RecipeBookType;ILnet/minecraft/world/entity/player/Inventory;Lnet/minecraft/world/Container;Lnet/minecraft/world/inventory/ContainerData;)V",
            constant = @Constant(
                    intValue = 17
            )
    )
    private int the_leaked_gui_true$moveFurnaceInputSlotY(int original) {
        return 57;
    }

    @ModifyConstant(
            method = "<init>(Lnet/minecraft/world/inventory/MenuType;Lnet/minecraft/world/item/crafting/RecipeType;Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/world/inventory/RecipeBookType;ILnet/minecraft/world/entity/player/Inventory;Lnet/minecraft/world/Container;Lnet/minecraft/world/inventory/ContainerData;)V",
            constant = @Constant(
                    intValue = 53
            )
    )
    private int the_leaked_gui_true$moveFurnaceFuelSlotY(int original) {
        return 87;
    }

    @ModifyConstant(
            method = "<init>(Lnet/minecraft/world/inventory/MenuType;Lnet/minecraft/world/item/crafting/RecipeType;Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/world/inventory/RecipeBookType;ILnet/minecraft/world/entity/player/Inventory;Lnet/minecraft/world/Container;Lnet/minecraft/world/inventory/ContainerData;)V",
            constant = @Constant(
                    intValue = 35
            )
    )
    private int the_leaked_gui_true$moveFurnaceResultSlotY(int original) {
        return 75;
    }

    @ModifyConstant(
            method = "<init>(Lnet/minecraft/world/inventory/MenuType;Lnet/minecraft/world/item/crafting/RecipeType;Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/world/inventory/RecipeBookType;ILnet/minecraft/world/entity/player/Inventory;Lnet/minecraft/world/Container;Lnet/minecraft/world/inventory/ContainerData;)V",
            constant = @Constant(
                    intValue = 116
            )
    )
    private int the_leaked_gui_true$moveFurnaceResultSlotX(int original) {
        return 25;
    }

    @WrapOperation(
            method = "<init>(Lnet/minecraft/world/inventory/MenuType;Lnet/minecraft/world/item/crafting/RecipeType;Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/world/inventory/RecipeBookType;ILnet/minecraft/world/entity/player/Inventory;Lnet/minecraft/world/Container;Lnet/minecraft/world/inventory/ContainerData;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/inventory/AbstractFurnaceMenu;addStandardInventorySlots(Lnet/minecraft/world/Container;II)V"
            )
    )
    private void the_leaked_gui_true$moveInventorySlots(AbstractFurnaceMenu instance, Container itemStacks, int x, int y, Operation<Void> original) {
        MixinFlags.IS_GENERIC_9x3.set(true);
        original.call(instance, itemStacks, 98, 49);
    }
}
