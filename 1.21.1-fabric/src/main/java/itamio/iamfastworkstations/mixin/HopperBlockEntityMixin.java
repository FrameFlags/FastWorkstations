package itamio.iamfastworkstations.mixin;

import itamio.iamfastworkstations.Config;
import net.minecraft.world.level.block.entity.HopperBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HopperBlockEntity.class)
public abstract class HopperBlockEntityMixin {

    @Shadow private int cooldownTime;

    @Inject(method = "setCooldown", at = @At("HEAD"), cancellable = true)
    private void fastWorkstations$capCooldown(int ticks, CallbackInfo ci) {
        int cap = Config.hopperTicks();
        if (ticks > cap) {
            this.cooldownTime = cap;
            ci.cancel();
        }
    }
}
