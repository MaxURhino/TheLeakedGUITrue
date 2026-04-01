package net.maxrhino.tlgt.client.datagen.providers;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class TheLeakedGUITrueLangProvider extends FabricLanguageProvider {
    public TheLeakedGUITrueLangProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(packOutput, registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider provider, TranslationBuilder translationBuilder) {
        translationBuilder.add("text.autoconfig.the_leaked_gui_true.title", "THE LEAKED GUI TRUE!!!");
        translationBuilder.add("text.autoconfig.the_leaked_gui_true.option.useOldSlotTextures", "Use old slot textures");
    }
}
