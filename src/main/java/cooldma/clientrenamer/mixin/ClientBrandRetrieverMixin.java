package cooldma.clientrenamer.mixin;

import cooldma.clientrenamer.ClientRenamer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import net.minecraft.client.ClientBrandRetriever;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({ClientBrandRetriever.class})
public abstract class ClientBrandRetrieverMixin {

	@Inject(at = @At("HEAD"), method = "getClientModName", cancellable = true, remap = false)
	private static void getClientModName(CallbackInfoReturnable<String> callback) {
		if (ClientRenamer.isEnabled())
			callback.setReturnValue(ClientRenamer.getClientBrand());
	}
}