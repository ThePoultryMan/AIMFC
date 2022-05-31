package io.github.thepoultryman.aimfc;

public class ArmorHidingHelper {
	private static boolean hideArmor = true;
	private static Integer hideTime = 0;

	public static void hideArmor(boolean hide) {
		hideArmor = hide;
	}

	public static boolean shouldHideArmor() {
		return hideArmor;
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
