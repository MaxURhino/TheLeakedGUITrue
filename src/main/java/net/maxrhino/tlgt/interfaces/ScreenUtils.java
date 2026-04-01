package net.maxrhino.tlgt.interfaces;

import me.shedaniel.autoconfig.AutoConfig;
import net.maxrhino.tlgt.TheLeakedGUITrue;
import net.maxrhino.tlgt.config.TheLeakedGUITrueConfig;
import net.minecraft.world.inventory.Slot;

public interface ScreenUtils {
    void the_leaked_gui_true$drawSlot(int x, int y, String type, boolean isOutput);
    void the_leaked_gui_true$drawSlot(int x, int y, String type);
    void the_leaked_gui_true$drawSlot(int x, int y, ContainerTypes type);
    void the_leaked_gui_true$drawSlot(int x, int y, SlotPath type);
    void the_leaked_gui_true$drawSlot(int x, int y);
    void the_leaked_gui_true$drawSlotWithCustomSize(int x, int y, int width, int height, SlotPath type);
    void the_leaked_gui_true$drawSlotWithCustomSize(int x, int y, int width, int height, SlotPath type, int spriteWidth, int spriteHeight, int textureX, int textureY);
    void the_leaked_gui_true$drawSlotWithCustomSize(int x, int y, int width, int height, String type, boolean isOutput);
    void the_leaked_gui_true$drawSlotWithCustomSize(int x, int y, int width, int height);
    void the_leaked_gui_true$drawContainerBackground(int x, int y, int width, int height, String container);
    void the_leaked_gui_true$drawContainerBackground(int x, int y, int width, int height, ContainerTypes container);
    void the_leaked_gui_true$drawContainerBackground(int x, int y, int width, int height);

    record SlotPath(String path, boolean isNormalSlot, boolean isOutputSlot) {
        public SlotPath(ContainerTypes path, boolean isNormalSlot, boolean isOutputSlot) {
            this(path.getName(), isNormalSlot, isOutputSlot);
        }

        @Override
        public String path() {
            if (isNormalSlot) {
                try {
                    TheLeakedGUITrueConfig config = AutoConfig.getConfigHolder(TheLeakedGUITrueConfig.class).getConfig();
                    if (config.isUseOldSlotTextures()) {
                        return "hud/slots/" + path;
                    }
                } catch (Exception e) {
                    TheLeakedGUITrue.INSTANCE.onInitialize();
                    TheLeakedGUITrueConfig config = AutoConfig.getConfigHolder(TheLeakedGUITrueConfig.class).getConfig();
                    if (config.isUseOldSlotTextures()) {
                        return "hud/slots/" + path;
                    }
                }
                return "hud/slots/new/" + (isOutputSlot ? "output_" : "item_") + path;
            }
            return path;
        }

        public SlotPath(String path, boolean isNormalSlot) {
            this(path, isNormalSlot, false);
        }
    }

    enum ContainerTypes {
        BARREL("barrel"),
        CHEST_DEFAULT("chest_default"),
        CHEST_ENDER("chest_ender"),
        DEFAULT("default"),
        DEFAULT_DARK("default_dark"),
        FURNACE("furnace"),
        SHULKER_BOX("shulker_box"),;

        private final String name;
        ContainerTypes(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
