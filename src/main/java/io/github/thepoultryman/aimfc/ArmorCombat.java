package io.github.thepoultryman.aimfc;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EquipmentSlot;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.lifecycle.api.client.event.ClientTickEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArmorCombat implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("aimfc");

	@Override
	public void onInitialize(ModContainer mod) {
		LOGGER.info("AIMFC, Hiding your armor since 2022");

		ArmorHidingHelper.SLOT_MAP.put(EquipmentSlot.HEAD, 0);
		ArmorHidingHelper.SLOT_MAP.put(EquipmentSlot.CHEST, 1);
		ArmorHidingHelper.SLOT_MAP.put(EquipmentSlot.LEGS, 2);
		ArmorHidingHelper.SLOT_MAP.put(EquipmentSlot.FEET, 3);

		ClientTickEvents.START.register(this::decrementHideTime);
	}

	private void decrementHideTime(MinecraftClient client) {
		if (!client.isPaused()) {
			ArmorHidingHelper.decrementHideTime();
			for (int i = 0; i < 4; i++) {
				if (!ArmorHidingHelper.shouldHideArmor(i) && ArmorHidingHelper.getHideTime() == 0) {
					ArmorHidingHelper.hideArmor(true, i);
				}
			}
		}
	}
}
