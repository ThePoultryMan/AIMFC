package io.github.thepoultryman.aimfc.mixin;

import io.github.thepoultryman.aimfc.ArmorHidingHelper;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ArmorFeatureRenderer.class)
public class ArmorRendererMixin<T extends LivingEntity, A extends BipedEntityModel<T>> {
	@Inject(method = "renderArmor", at = @At("HEAD"), cancellable = true)
	private void aimfc$cancelArmorRendering(MatrixStack matrices, VertexConsumerProvider vertexConsumers, T entity, EquipmentSlot armorSlot, int light, A model, CallbackInfo ci) {
		if (entity.isPlayer()) {
			if (ArmorHidingHelper.shouldHideArmor(ArmorHidingHelper.SLOT_MAP.get(armorSlot))) {
				ci.cancel();
			}
		}
	}
}
