package io.github.thepoultryman.aimfc.mixin;

import io.github.thepoultryman.aimfc.ArmorHidingHelper;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class PlayerDamageMixin {
	@Inject(method = "damage", at = @At("HEAD"))
	private void aimfc$unhideArmor(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
		ArmorHidingHelper.hideArmor(false, 0);
		ArmorHidingHelper.resetHideTime();
	}
}
