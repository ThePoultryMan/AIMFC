package io.github.thepoultryman.aimfc.mixin;

import io.github.thepoultryman.aimfc.ArmorHidingHelper;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPlayerEntity.class)
public class PlayerDamageMixin {
	@Inject(method = "damage", at = @At("HEAD"))
	private void aimfc$unhideArmor(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
		if (source.isFromFalling()) {
			ArmorHidingHelper.hideArmor(false, 3);
		} else {
			ArmorHidingHelper.hideArmor(false);
		}
		ArmorHidingHelper.resetHideTime();
	}
}
