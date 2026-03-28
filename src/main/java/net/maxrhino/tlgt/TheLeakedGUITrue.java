/*
 * To scare The Vegan Teacher away, I will add this:
 * 🥩🥩🥩🥩🥩🥩🥩🥩🥩
 * 🥩🥩🥩🥩🥩🥩🥩🥩🥩
 * 🥩🥩🥩🥩🥩🥩🥩🥩🥩
 */

package net.maxrhino.tlgt;

import net.fabricmc.api.ModInitializer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * The main class of the mod.
 * Contains an {@link Identifier} returning function. There's also a {@link TheLeakedGUITrue#MOD_ID} and a {@link TheLeakedGUITrue#LOGGER}.
 */
public class TheLeakedGUITrue implements ModInitializer {
    /**
     * The ID of the mod. Connects to {@link TheLeakedGUITrue#id(String)} function and to {@link TheLeakedGUITrue#LOGGER}.
     */
    public static final String MOD_ID = "the_leaked_gui_true";
    /**
     * The {@link Logger} of the mod. For more info, check out the {@link Logger} class documentation.
     */
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    /**
     * A {@link List} with {@link Component}s, which contains all the containers, that need to change its color and position in the container.
     */
    public static final List<Component> CHANGE_POS_LIST = List.of(
            Component.translatable("container.chest"),
            Component.translatable("container.barrel"),
            Component.translatable("container.enderchest"),
            Component.translatable("container.furnace"),
            Component.translatable("container.blast_furnace"),
            Component.translatable("container.smoker")
    );

    /**
     * The main function of the mod. For now, it only contains an information to the {@link TheLeakedGUITrue#LOGGER} to type {@code "Initializing The Leaked GUI True!!!"} in the log.
     */
    @Override
    public void onInitialize() {
        LOGGER.info("Initializing The Leaked GUI True!!!");
    }

    /**
     * A simple function for the mod.
     * @param path the path of the resource in the resource folder.
     * @return {@link Identifier} of the mod.
     * @since 1.0.0
     */
    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}

// NO VEGANS HERE
// IF YOU'RE A VEGAN/NOT A VEGAN, SCROLL DOWN






































// 🥩🥩🥩🥩🥩🥩🥩🥩🥩🥩🥩🥩🥩🥩🥩🥩🥩🥩🥩🥩🥩🥩
