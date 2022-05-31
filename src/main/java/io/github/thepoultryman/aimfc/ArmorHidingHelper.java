package io.github.thepoultryman.aimfc;

import net.minecraft.entity.EquipmentSlot;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArmorHidingHelper {
	public static final Map<EquipmentSlot, Integer> SLOT_MAP = new HashMap<>();

	private static boolean overrideArmorHiding = false;
	private static final boolean[] hideArmor = new boolean[] {true, true, true, true};
	private static Integer hideTime = 0;

	public static void overrideArmorHiding(boolean override) {
		overrideArmorHiding = override;
	}

	public static boolean shouldOverrideArmorHiding() {
		return overrideArmorHiding;
	}

	public static void hideArmor(boolean hide) {
		Arrays.fill(hideArmor, hide);
	}

	public static void hideArmor(boolean hide, int slot) {
		hideArmor[slot] = hide;
	}

	public static boolean shouldHideArmor(int slot) {
		if (overrideArmorHiding) {
			return false;
		} else {
			return hideArmor[slot];
		}
	}

	public static void resetHideTime() {
		hideTime = 10 * 20;
	}

	public static void decrementHideTime() {
		if (hideTime >= 1)
			hideTime -= 1;
	}

	public static Integer getHideTime() {
		return hideTime;
	}
}
