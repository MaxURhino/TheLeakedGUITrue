package net.maxrhino.tlgt.mixin.accessors;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(AbstractContainerScreen.class)
public interface AbstractContainerScreenAccessor {
    @Accessor("imageWidth") int imageWidth();
    @Accessor("imageHeight") int imageHeight();

    @Accessor("menu")
    <T extends AbstractContainerMenu> T menu();
}
