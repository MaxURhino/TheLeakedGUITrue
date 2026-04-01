package net.maxrhino.tlgt.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "the_leaked_gui_true")
public class TheLeakedGUITrueConfig implements ConfigData {
    @ConfigEntry.Gui.Tooltip
    boolean useOldSlotTextures = false;

    public boolean isUseOldSlotTextures() {
        return useOldSlotTextures;
    }
}
