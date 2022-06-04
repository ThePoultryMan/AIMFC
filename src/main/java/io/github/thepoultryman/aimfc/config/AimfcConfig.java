package io.github.thepoultryman.aimfc.config;

import com.electronwill.nightconfig.core.file.FileConfig;
import org.quiltmc.loader.api.QuiltLoader;

public class AimfcConfig {
	private final FileConfig config;

	// Config Values
	private boolean overrideHiddenArmor;
	private int armorVisibilityDelay;
	private final boolean[] alwaysVisibleParts = new boolean[4];
	private Elytra elytraConfig;

	public AimfcConfig() {
		this.config = FileConfig.builder(QuiltLoader.getConfigDir() + "/aimfc.toml").defaultResource("/aimfc.toml").autosave().build();
	}

	public void loadConfig() {
		this.config.load();

		// Set config values
		this.overrideHiddenArmor = this.config.getOrElse("override_hidden_armor", false);
		this.armorVisibilityDelay = this.config.getOrElse("armor_visibility_delay", 20);
		this.alwaysVisibleParts[0] = this.config.getOrElse("always_visible_parts.head", false);
		this.alwaysVisibleParts[1] = this.config.getOrElse("always_visible_parts.chest", false);
		this.alwaysVisibleParts[2] = this.config.getOrElse("always_visible_parts.legs", false);
		this.alwaysVisibleParts[3] = this.config.getOrElse("always_visible_parts.feet", false);

		this.elytraConfig = new Elytra(this.config.getOrElse("hide_other_pieces.elytra.hide", false),
				this.config.getOrElse("hide_other_pieces.elytra.dynamic_reveal", true));
	}

	public void setOverrideHiddenArmor(boolean override) {
		this.config.set("override_hidden_armor", override);
		this.overrideHiddenArmor = override;
	}

	public boolean shouldOverrideHiddenArmor() {
		return this.overrideHiddenArmor;
	}

	public int getArmorVisibilityDelay() {
		return this.armorVisibilityDelay;
	}

	public boolean getAlwaysVisiblePart(int slot) {
		return this.alwaysVisibleParts[slot];
	}

	public Elytra getElytraConfig() {
		return this.elytraConfig;
	}

	public static class Elytra {
		boolean hide;
		boolean dynamicReveal;

		public Elytra(boolean hide, boolean dynamicReveal) {
			this.hide = hide;
			this.dynamicReveal = dynamicReveal;
		}

		public boolean shouldHide() {
			return this.hide;
		}

		public boolean usingDynamicReveal() {
			return this.dynamicReveal;
		}
	}
}
