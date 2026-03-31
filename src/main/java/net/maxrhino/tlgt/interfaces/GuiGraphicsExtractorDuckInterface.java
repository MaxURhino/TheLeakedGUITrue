package net.maxrhino.tlgt.interfaces;

public interface GuiGraphicsExtractorDuckInterface {
    void the_leaked_gui_true$drawSlot(int x, int y, String type, boolean isOutput);
    void the_leaked_gui_true$drawSlot(int x, int y, String type);
    void the_leaked_gui_true$drawSlot(int x, int y, SlotPath type);
    void the_leaked_gui_true$drawSlot(int x, int y);
    void the_leaked_gui_true$drawSlotWithCustomSize(int x, int y, int width, int height, SlotPath type);
    void the_leaked_gui_true$drawSlotWithCustomSize(int x, int y, int width, int height, SlotPath type, int spriteWidth, int spriteHeight, int textureX, int textureY);
    void the_leaked_gui_true$drawSlotWithCustomSize(int x, int y, int width, int height, String type, boolean isOutput);
    void the_leaked_gui_true$drawSlotWithCustomSize(int x, int y, int width, int height);
    void the_leaked_gui_true$drawContainerBackground(int x, int y, int width, int height, String container);
    void the_leaked_gui_true$drawContainerBackground(int x, int y, int width, int height);

    record SlotPath(String path, boolean isNormalSlot, boolean isOutputSlot) {
        @Override
        public String path() {
            if (isNormalSlot) {
                return "hud/slots/new/" + (isOutputSlot ? "output_" : "item_") + path;
            }
            return path;
        }

        public SlotPath(String path, boolean isNormalSlot) {
            this(path, isNormalSlot, false);
        }
    }
}
