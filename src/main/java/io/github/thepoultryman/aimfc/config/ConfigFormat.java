package io.github.thepoultryman.aimfc.config;

public class ConfigFormat {
	private boolean overrideHiddenArmor = false;

	public void setOverrideHiddenArmor(boolean override) {
		this.overrideHiddenArmor = override;
	}

	public boolean shouldOverrideHiddenArmor() {
		return this.overrideHiddenArmor;
	}
}
