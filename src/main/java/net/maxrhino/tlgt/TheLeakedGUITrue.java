package net.maxrhino.tlgt;

import com.mojang.datafixers.util.Pair;
import net.fabricmc.api.ModInitializer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class TheLeakedGUITrue implements ModInitializer {
    public static final String MOD_ID = "the_leaked_gui_true";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final Map<Component, Pair<Integer, Boolean>> TEXT_COLORS = Map.ofEntries(
            Map.entry(Component.translatable("container.barrel"), Pair.of(0xFFFFFFFF, true))
    );

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing The Leaked GUI True!!!");
    }

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}
