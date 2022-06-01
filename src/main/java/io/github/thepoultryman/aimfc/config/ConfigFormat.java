package io.github.thepoultryman.aimfc.config;

public class ConfigFormat {
	private boolean overrideHiddenArmor = false;
	private int armorVisibilityDelay = 20;
	private final AlwaysVisibleParts alwaysVisible = new AlwaysVisibleParts();
	private final OtherPieces hideOtherPieces = new OtherPieces();

	public void setOverrideHiddenArmor(boolean override) {
		this.overrideHiddenArmor = override;
	}

	public boolean shouldOverrideHiddenArmor() {
		return this.overrideHiddenArmor;
	}

	public void setArmorVisibilityDelay(int delay) {
		this.armorVisibilityDelay = delay;
	}

	public int getArmorVisibilityDelay() {
		return this.armorVisibilityDelay;
	}

	public boolean getAlwaysVisiblePart(int slot) {
		return switch (slot) {
			case 1 -> this.alwaysVisible.chest;
			case 2 -> this.alwaysVisible.legs;
			case 3 -> this.alwaysVisible.feet;
			default -> this.alwaysVisible.head;
		};
	}

	public OtherPieces getOtherPieces() {
		return this.hideOtherPieces;
	}

	private static class AlwaysVisibleParts {
		boolean head = false;
		boolean chest = false;
		boolean legs = false;
		boolean feet = false;
	}

	public static class OtherPieces {
		boolean elytra = false;

		public boolean shouldHideElytra() {
			return this.elytra;
		}
	}
}
