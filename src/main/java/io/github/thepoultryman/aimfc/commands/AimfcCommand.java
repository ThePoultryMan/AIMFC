package io.github.thepoultryman.aimfc.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import io.github.thepoultryman.aimfc.ArmorCombat;
import net.minecraft.text.TranslatableText;
import org.quiltmc.qsl.command.api.client.QuiltClientCommandSource;

public class AimfcCommand implements Command<QuiltClientCommandSource> {
	@Override
	public int run(CommandContext<QuiltClientCommandSource> context) {
		context.getSource().sendFeedback(new TranslatableText("command.aimfc.armor_hidden_" + !ArmorCombat.config.shouldOverrideHiddenArmor()));
		return 0;
	}
}
