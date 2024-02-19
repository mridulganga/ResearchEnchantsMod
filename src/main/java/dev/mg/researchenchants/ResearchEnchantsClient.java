package dev.mg.researchenchants;

import dev.mg.researchenchants.screen.EnchantCreatorScreen;
import dev.mg.researchenchants.screen.ModScreenHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class ResearchEnchantsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HandledScreens.register(ModScreenHandlers.ENCHANT_CREATOR_SCREEN_HANDLER, EnchantCreatorScreen::new);
    }
}
