package io.github.thepoultryman.aimfc.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import io.github.thepoultryman.aimfc.ArmorCombat;
import org.quiltmc.qsl.command.api.client.QuiltClientCommandSource;

public class ReloadConfigCommand implements Command<QuiltClientCommandSource> {
	@Override
	public int run(CommandContext<QuiltClientCommandSource> context) {
		ArmorCombat.config.loadConfig();
		return 1;
	}
}
