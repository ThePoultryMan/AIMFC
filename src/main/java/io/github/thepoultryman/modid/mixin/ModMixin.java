package io.github.thepoultryman.modid.mixin;

import io.github.thepoultryman.modid.ModClass;
import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class ModMixin {
	@Inject(method = "init", at = @At("TAIL"))
	public void onInit(CallbackInfo ci) {
		ModClass.LOGGER.info("This line is printed by an example mod mixin!");
	}
}
