package net.maxrhino.tlgt.interfaces;

import me.shedaniel.autoconfig.AutoConfig;
import net.maxrhino.tlgt.TheLeakedGUITrue;
import net.maxrhino.tlgt.config.TheLeakedGUITrueConfig;
import net.minecraft.world.item.DyeColor;
import org.jetbrains.annotations.Nullable;

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
                    TheLeakedGUITrue.INSTANCE.initializeConfig();
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

    class ContainerTypes {
        public static final ContainerTypes BARREL = new ContainerTypes("barrel");
        public static final ContainerTypes CHEST_DEFAULT = new ContainerTypes("chest_default");
        public static final ContainerTypes CHEST_ENDER = new ContainerTypes("chest_ender");
        public static final ContainerTypes DEFAULT = new ContainerTypes("default");
        public static final ContainerTypes DEFAULT_DARK = new ContainerTypes("default_dark");
        public static final ContainerTypes FURNACE = new ContainerTypes("furnace");
        public static final ContainerTypes BLAST_FURNACE = new ContainerTypes("blast_furnace");
        public static final ContainerTypes SMOKER = new ContainerTypes("smoker");
        public static final ContainerTypes SHULKER_BOX = new ContainerTypes("shulker_box");

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

        public static ContainerTypes createWithType(String path, String type) {
            return new ContainerTypes(path + "_" + type);
        }

        public static ContainerTypes createWithColor(String path, DyeColor color) {
            return createWithType(path, color.getName());
        }

        public static ContainerTypes createWithColor(ContainerTypes path, @Nullable DyeColor color) {
            String stringedColor = "default";
            if (color != null) {
                stringedColor = color.getName();
            }
            return createWithType(path.getName(), stringedColor);
        }
    }
}
