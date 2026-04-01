package net.maxrhino.tlgt.compat.modmenu;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.autoconfig.AutoConfigClient;
import net.maxrhino.tlgt.config.TheLeakedGUITrueConfig;

public class TheLeakedGUITrueModMenuCompat implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> AutoConfigClient.getConfigScreen(TheLeakedGUITrueConfig.class, parent).get();
    }
}
