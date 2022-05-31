package io.github.thepoultryman.aimfc;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBind;
import net.minecraft.entity.EquipmentSlot;
import org.lwjgl.glfw.GLFW;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;
import org.quiltmc.qsl.lifecycle.api.client.event.ClientTickEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArmorCombat implements ClientModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("aimfc");

	private static final KeyBind TOGGLE_KEY = new KeyBind("key.aimfc.toggle_armor_hiding", GLFW.GLFW_KEY_H, "key.aimfc.category");

	@Override
	public void onInitializeClient(ModContainer mod) {
		LOGGER.info("AIMFC, Hiding your armor since 2022");

		KeyBindingHelper.registerKeyBinding(TOGGLE_KEY);

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

		while (TOGGLE_KEY.wasPressed()) {
			ArmorHidingHelper.overrideArmorHiding(!ArmorHidingHelper.shouldOverrideArmorHiding());
		}
	}
}
