package io.github.thepoultryman.aimfc.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.thepoultryman.aimfc.ArmorCombat;
import org.quiltmc.loader.api.QuiltLoader;

import java.io.*;

public class ArmorHidingConfig {
	public static final String CONFIG_LOCATION = QuiltLoader.getConfigDir() + "/aimfc.json";
	private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

	public static ConfigFormat hidingConfig;

	public static ConfigFormat loadConfig() {
		if (!new File(CONFIG_LOCATION).exists()) {
			FileWriter writer;
			try {
				writer = new FileWriter(CONFIG_LOCATION);
				hidingConfig = new ConfigFormat();

				gson.toJson(hidingConfig, writer);

				try {
					writer.flush();
					writer.close();
				} catch (IOException e) {
					ArmorCombat.LOGGER.warn("There was an issue closing the file writer, so it has remained open.");
				}

				return hidingConfig;
			} catch (IOException e) {
				ArmorCombat.LOGGER.warn("There was an issue while creating the config file.", e);
			}
		} else {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(CONFIG_LOCATION));
				return gson.fromJson(reader, ConfigFormat.class);
			} catch (FileNotFoundException e) {
				ArmorCombat.LOGGER.warn("There was an error reading your config file.", e);
			}
		}

		ArmorCombat.LOGGER.error("There has been a severe issue during config creation/reading. Unexpected behavior or crashes may follow.");
		return null;
	}

	public static void saveConfigChanges(ConfigFormat updatedConfig) {
		if (new File(CONFIG_LOCATION).exists()) {
			FileWriter writer;
			try {
				writer = new FileWriter(CONFIG_LOCATION);

				gson.toJson(updatedConfig, writer);

				try {
					writer.flush();
					writer.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		} else {
			ArmorCombat.LOGGER.warn("A config file did not exist, so a new one is being created.");
			ArmorCombat.config = loadConfig();
		}
	}

	public static void reloadConfig() {
		if (new File(CONFIG_LOCATION).exists()) {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(CONFIG_LOCATION));
				ArmorCombat.config = gson.fromJson(reader, ConfigFormat.class);
			} catch (FileNotFoundException e) {
				ArmorCombat.LOGGER.warn("There was an error reading your config file.", e);
			}
		} else {
			ArmorCombat.config = loadConfig();
		}
	}
}
