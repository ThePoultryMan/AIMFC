package io.github.thepoultryman.aimfc.mixin.hiddenpieces;

import io.github.thepoultryman.aimfc.ArmorCombat;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.ElytraFeatureRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ElytraFeatureRenderer.class)
public class ElytraRendererMixin {
	@Inject(method = "render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/entity/Entity;FFFFFF)V",
			at = @At("HEAD"), cancellable = true)
	private void aimfc$hideElytra(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, Entity entity,
								  float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch, CallbackInfo ci) {
		if (ArmorCombat.config.getElytraConfig().shouldHide()) ci.cancel();
	}
}
