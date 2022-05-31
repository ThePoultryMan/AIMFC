package io.github.thepoultryman.aimfc;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArmorCombat implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("aimfc");

	@Override
	public void onInitialize(ModContainer mod) {
		LOGGER.info("AIMFC, Hiding your armor since 2022");
	}
}
