package io.github.thepoultryman.aimfc.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import io.github.thepoultryman.aimfc.ArmorCombat;
import net.minecraft.text.TranslatableText;
import org.quiltmc.qsl.command.api.client.QuiltClientCommandSource;

public class OverrideHiddenArmorCommands {
	public static class TurnOn implements Command<QuiltClientCommandSource> {
		@Override
		public int run(CommandContext<QuiltClientCommandSource> context) {
			ArmorCombat.config.setOverrideHiddenArmor(true);
			context.getSource().sendFeedback(new TranslatableText("message.aimfc.hiding_" + false));
			return 1;
		}
	}

	public static class TurnOff implements Command<QuiltClientCommandSource> {
		@Override
		public int run(CommandContext<QuiltClientCommandSource> context) {
			ArmorCombat.config.setOverrideHiddenArmor(false);
			context.getSource().sendFeedback(new TranslatableText("message.aimfc.hiding_" + true));
			return 1;
		}
	}
}
