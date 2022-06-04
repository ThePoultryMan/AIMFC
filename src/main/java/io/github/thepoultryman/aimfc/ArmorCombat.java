package io.github.thepoultryman.aimfc;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.github.thepoultryman.aimfc.commands.AimfcCommand;
import io.github.thepoultryman.aimfc.commands.OverrideHiddenArmorCommands;
import io.github.thepoultryman.aimfc.commands.ReloadConfigCommand;
import io.github.thepoultryman.aimfc.config.AimfcConfig;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBind;
import net.minecraft.entity.EquipmentSlot;
import org.lwjgl.glfw.GLFW;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;
import org.quiltmc.qsl.command.api.client.ClientCommandRegistrationCallback;
import org.quiltmc.qsl.command.api.client.QuiltClientCommandSource;
import org.quiltmc.qsl.lifecycle.api.client.event.ClientTickEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.quiltmc.qsl.command.api.client.ClientCommandManager.literal;

public class ArmorCombat implements ClientModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("aimfc");

	private static final KeyBind TOGGLE_KEY = new KeyBind("key.aimfc.toggle_armor_hiding", GLFW.GLFW_KEY_H, "key.aimfc.category");
	public static AimfcConfig config = new AimfcConfig();

	@Override
	public void onInitializeClient(ModContainer mod) {
		LOGGER.info("AIMFC, Hiding your armor since 2022");

		KeyBindingHelper.registerKeyBinding(TOGGLE_KEY);

		ClientCommandRegistrationCallback.EVENT.register(((dispatcher) -> {
			LiteralArgumentBuilder<QuiltClientCommandSource> aimfcCommand = literal("aimfc").executes(new AimfcCommand());
			LiteralCommandNode<QuiltClientCommandSource> overrideCommand = literal("overridehiddenarmor")
					.then(literal("on").executes(new OverrideHiddenArmorCommands.TurnOn()))
					.then(literal("off").executes(new OverrideHiddenArmorCommands.TurnOff())).build();
			aimfcCommand.then(overrideCommand);

			LiteralCommandNode<QuiltClientCommandSource> configReloadCommand = literal("reloadconfig").executes(new ReloadConfigCommand()).build();
			aimfcCommand.then(configReloadCommand);

			dispatcher.getRoot().addChild(aimfcCommand.build());
		}));

		ArmorHidingHelper.SLOT_MAP.put(EquipmentSlot.HEAD, 0);
		ArmorHidingHelper.SLOT_MAP.put(EquipmentSlot.CHEST, 1);
		ArmorHidingHelper.SLOT_MAP.put(EquipmentSlot.LEGS, 2);
		ArmorHidingHelper.SLOT_MAP.put(EquipmentSlot.FEET, 3);

		config.loadConfig();

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
			if (client.player != null) {
				config.setOverrideHiddenArmor(!config.shouldOverrideHiddenArmor());
				ArmorHidingHelper.sendStatusChatMessage(client.player);
			} else {
				LOGGER.warn("Armor hiding cannot be disabled due to an unusual error.");
			}

		}
	}
}
