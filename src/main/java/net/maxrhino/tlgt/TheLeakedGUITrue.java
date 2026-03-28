package net.maxrhino.tlgt;

import net.fabricmc.api.ModInitializer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TheLeakedGUITrue implements ModInitializer {
    public static final String MOD_ID = "the_leaked_gui_true";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final List<Component> CHANGE_POS_LIST = List.of(
            Component.translatable("container.chest"),
            Component.translatable("container.barrel"),
            Component.translatable("container.enderchest"),
            Component.translatable("container.furnace"),
            Component.translatable("container.blast_furnace"),
            Component.translatable("container.smoker")
    );

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing The Leaked GUI True!!!");
    }

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}
