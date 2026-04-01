package net.maxrhino.tlgt.mixin.menus;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.maxrhino.tlgt.interfaces.ShulkerBoxMenuDuck;
import net.maxrhino.tlgt.mixin.accessors.AbstractContainerMenuAccessor;
import net.maxrhino.tlgt.util.MixinFlags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.ShulkerBoxMenu;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ShulkerBoxMenu.class)
public class MixinShulkerBoxMenu implements ShulkerBoxMenuDuck {
    @Shadow
    @Final
    private Container container;

    @Unique
    private DataSlot dataSlot;

    @Inject(
            method = "<init>(ILnet/minecraft/world/entity/player/Inventory;Lnet/minecraft/world/Container;)V",
            at = @At("TAIL")
    )
    private void the_leaked_gui_true$addDyeColorDataSlot(int containerId, Inventory inventory, Container container, CallbackInfo ci) {
        AbstractContainerMenuAccessor accessor = (AbstractContainerMenuAccessor) this;
        dataSlot = DataSlot.standalone();
        dataSlot.set(-1);
        accessor.the_leaked_gui_true$addDataSlot(dataSlot);
        the_leaked_gui_true$getDyeColor();
    }

    @Override
    public DataSlot the_leaked_gui_true$getDataSlot() {
        return dataSlot;
    }

    @Override
    public void the_leaked_gui_true$setDataSlot(int data) {
        dataSlot.set(data);
    }

    @Unique
    public DyeColor the_leaked_gui_true$getDyeColor() {
        if (this.container instanceof ShulkerBoxBlockEntity be) {
            BlockPos pos = be.getBlockPos();
            Level level =  be.getLevel();

            if (level != null) {
                Block block = level.getBlockState(pos).getBlock();
                if (block instanceof ShulkerBoxBlock shulkerBox) {
                    dataSlot.set(shulkerBox.getColor() != null ? shulkerBox.getColor().getId() : -1);
                    return shulkerBox.getColor();
                }
            }
        }
        return null;
    }

    @ModifyConstant(
            method = "<init>(ILnet/minecraft/world/entity/player/Inventory;Lnet/minecraft/world/Container;)V",
            constant = @Constant(
                    intValue = 8,
                    ordinal = 0
            )
    )
    private int the_leaked_gui_true$moveShulkerBoxSlotsX(int original) {
        return -82;
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
