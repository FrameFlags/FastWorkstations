package itamio.iamfastworkstations.mixin;

import itamio.iamfastworkstations.Config;
import net.minecraft.tileentity.TileEntityFurnace;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Forge 1.12.2 changes the vanilla furnace: update() compares cookTime against
 * the totalCookTime field and getCookTime(ItemStack) is the lone method that
 * returns the vanilla literal 200. Replacing that constant (the 1.12.2
 * equivalent of modifying update()'s 200 in vanilla) plus clamping the live
 * total in update() also covers furnaces whose totalCookTime was read from NBT.
 */
@Mixin(TileEntityFurnace.class)
public abstract class TileEntityFurnaceMixin {

    @Shadow(remap = true) private int cookTime;
    @Shadow(remap = true) private int totalCookTime;

    @ModifyConstant(method = "getCookTime", constant = @Constant(intValue = 200), require = 1)
    private int fastWorkstations$replaceDefaultCookTime(int original) {
        return Config.workstationTicks();
    }

    @Inject(method = "update", at = @At("HEAD"))
    private void fastWorkstations$forceCookTime(CallbackInfo ci) {
        int ticks = Config.workstationTicks();
        this.totalCookTime = ticks;
        if (this.cookTime >= this.totalCookTime) {
            this.cookTime = this.totalCookTime - 1;
        }
    }
}
