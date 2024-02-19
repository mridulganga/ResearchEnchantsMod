package dev.mg.researchenchants.screen;

import dev.mg.researchenchants.ResearchEnchants;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {
    public static final ScreenHandlerType<EnchantCreatorScreenHandler> ENCHANT_CREATOR_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(ResearchEnchants.MOD_ID, "enchant_creator"),
                    new ExtendedScreenHandlerType<>(EnchantCreatorScreenHandler::new));

    public static void registerScreenHandlers() {
        ResearchEnchants.LOGGER.info("Registering Screen Handlers for " + ResearchEnchants.MOD_ID);
    }
}