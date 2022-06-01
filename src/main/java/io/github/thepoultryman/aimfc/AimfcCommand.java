package io.github.thepoultryman.aimfc;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.TranslatableText;

public class AimfcCommand implements Command<ServerCommandSource> {
	@Override
	public int run(CommandContext<ServerCommandSource> context) {
		context.getSource().sendFeedback(new TranslatableText("command.aimfc.armor_hidden_" + !ArmorCombat.config.shouldOverrideHiddenArmor()), false);
		return 0;
	}
}
