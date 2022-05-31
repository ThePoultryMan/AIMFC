package io.github.thepoultryman.aimfc;

import net.minecraft.client.MinecraftClient;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.lifecycle.api.client.event.ClientTickEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArmorCombat implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("aimfc");

	int i = 0;

	@Override
	public void onInitialize(ModContainer mod) {
		LOGGER.info("AIMFC, Hiding your armor since 2022");

		ClientTickEvents.START.register(this::decrementHideTime);
	}

	private void decrementHideTime(MinecraftClient client) {
		if (!client.isPaused()) {
			ArmorHidingHelper.decrementHideTime();
			if (!ArmorHidingHelper.shouldHideArmor() && ArmorHidingHelper.getHideTime() == 0) {
				ArmorHidingHelper.hideArmor(true);
			}
		}
	}
}
