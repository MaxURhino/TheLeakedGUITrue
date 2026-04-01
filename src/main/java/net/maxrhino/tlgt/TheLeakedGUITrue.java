/*
 * To scare The Vegan Teacher away, I will add this:
 * 🥩🥩🥩🥩🥩🥩🥩🥩🥩
 * 🥩🥩🥩🥩🥩🥩🥩🥩🥩
 * 🥩🥩🥩🥩🥩🥩🥩🥩🥩
 */

package net.maxrhino.tlgt;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.maxrhino.tlgt.config.TheLeakedGUITrueConfig;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NonNull;
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
            Component.translatable("container.smoker"),
            Component.translatable("container.shulkerBox")
    );

    public static final TheLeakedGUITrue INSTANCE = new TheLeakedGUITrue();

    /**
     * The main function of the mod. For now, it only contains an information to the {@link TheLeakedGUITrue#LOGGER} to type {@code "Initializing The Leaked GUI True!!!"} in the log.
     */
    @Override
    public void onInitialize() {
        initializeConfig();
    }

    public void initializeConfig() {
        AutoConfig.register(TheLeakedGUITrueConfig.class, JanksonConfigSerializer::new);
    }

    /**
     * A simple function for the mod.
     * @param path the path of the resource in the resource folder.
     * @return {@link Identifier} of the mod.
     * @since 1.0.0
     */
    @Contract("_ -> new")
    public static @NonNull Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}
