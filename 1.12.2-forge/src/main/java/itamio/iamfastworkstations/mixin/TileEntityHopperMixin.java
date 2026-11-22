package itamio.iamfastworkstations.mixin;

import itamio.iamfastworkstations.Config;
import net.minecraft.tileentity.TileEntityHopper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TileEntityHopper.class)
public abstract class TileEntityHopperMixin {

    @Inject(method = "setTransferCooldown", at = @At("HEAD"), cancellable = true)
    private void fastWorkstations$capCooldown(int ticks, CallbackInfo ci) {
        int cap = Config.hopperTicks();
        if (ticks > cap) {
            ((TileEntityHopper) (Object) this).setTransferCooldown(cap);
            ci.cancel();
        }
    }
}
