package net.maxrhino.tlgt.mixin.accessors;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(AbstractContainerMenu.class)
public interface AbstractContainerMenuAccessor {
    @Invoker("getCarried")
    ItemStack the_leaked_gui_true$getCarried();

    @Accessor("menuType")
    MenuType<?> the_leaked_gui_true$menuType();

    @Invoker("addDataSlot")
    DataSlot the_leaked_gui_true$addDataSlot(DataSlot dataSlot);
}
