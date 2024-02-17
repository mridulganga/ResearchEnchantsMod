package dev.mg.researchenchants;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResearchEnchants implements ModInitializer {
	public static final String MOD_ID = "researchenchants";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Loaded {}", MOD_ID);
	}
}