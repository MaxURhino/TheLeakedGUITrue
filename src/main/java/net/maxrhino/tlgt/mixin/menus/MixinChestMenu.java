package net.maxrhino.tlgt.mixin.menus;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.maxrhino.tlgt.interfaces.ChestMenuDuck;
import net.maxrhino.tlgt.mixin.accessors.AbstractContainerMenuAccessor;
import net.maxrhino.tlgt.util.MixinFlags;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChestMenu.class)
public class MixinChestMenu implements ChestMenuDuck {
    @Unique
    private DataSlot dataSlot;

    @Inject(
            method = "<init>(Lnet/minecraft/world/inventory/MenuType;ILnet/minecraft/world/entity/player/Inventory;Lnet/minecraft/world/Container;I)V",
            at = @At("TAIL")
    )
    private void the_leaked_gui_true$addDataSlotThing(MenuType menuType, int containerId, Inventory inventory, Container container, int rows, CallbackInfo ci) {
        AbstractContainerMenuAccessor accessor = (AbstractContainerMenuAccessor) this;
        dataSlot = DataSlot.standalone();
        dataSlot.set(-1);
        accessor.the_leaked_gui_true$addDataSlot(dataSlot);
        the_leaked_gui_true$getData(container);
    }

    @Unique
    private void the_leaked_gui_true$getData(Container container) {
        if (container instanceof ChestBlockEntity be) {
            BlockPos pos = be.getBlockPos();
            Level level = be.getLevel();
            if (level != null) {
                BlockState state = level.getBlockState(pos);
                if (state.is(BlockTags.COPPER_CHESTS)) {
                    if (state.is(Blocks.EXPOSED_COPPER_CHEST) || state.is(Blocks.WAXED_EXPOSED_COPPER_CHEST)) {
                        dataSlot.set(1);
                    } else if (state.is(Blocks.WEATHERED_COPPER_CHEST) || state.is(Blocks.WAXED_WEATHERED_COPPER_CHEST)) {
                        dataSlot.set(2);
                    } else if (state.is(Blocks.OXIDIZED_COPPER_CHEST) || state.is(Blocks.WAXED_OXIDIZED_COPPER_CHEST)) {
                        dataSlot.set(3);
                    } else {
                        dataSlot.set(0);
                    }
                }
            }
        }
    }

    @Override
    public DataSlot the_leaked_gui_true$getDataSlot() {
        return dataSlot;
    }

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
        MenuType<?> menuType = ((AbstractContainerMenuAccessor)this).the_leaked_gui_true$menuType();
        MixinFlags.IS_GENERIC_9x3.set(menuType == MenuType.GENERIC_9x3);
        int yLevel = menuType == MenuType.GENERIC_9x3 ? 49 : 76;
        original.call(instance, itemStacks, 98, yLevel);
    }
}
