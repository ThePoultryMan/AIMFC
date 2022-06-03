package io.github.thepoultryman.aimfc.config;

import com.electronwill.nightconfig.core.file.FileConfig;
import org.quiltmc.loader.api.QuiltLoader;

public class TomlConfig {
	private final FileConfig config;

	// Config Values
	private boolean overrideHiddenArmor;

	public TomlConfig() {
		this.config = FileConfig.builder(QuiltLoader.getConfigDir() + "/aimfc.toml").defaultResource("/aimfc.toml").autosave().build();
	}

	public void loadConfig() {
		this.config.load();

		// Set config values
		this.overrideHiddenArmor = this.config.getOrElse("override_hidden_armor", false);
	}

	public boolean shouldOverrideHiddenArmor() {
		return this.overrideHiddenArmor;
	}
}
