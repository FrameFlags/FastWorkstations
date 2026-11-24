package itamio.iamfastworkstations.mixin;

import itamio.iamfastworkstations.Config;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Applies to furnaces, blast furnaces and smokers alike: they all extend
 * AbstractFurnaceBlockEntity and all run the static serverTick method.
 */
@Mixin(AbstractFurnaceBlockEntity.class)
public abstract class AbstractFurnaceBlockEntityMixin {

    @Shadow NonNullList<ItemStack> items;
    @Shadow private int litTimeRemaining;
    @Shadow int cookingTotalTime;

    @Shadow protected abstract int getBurnDuration(ServerLevel level, ItemStack stack);

    @Inject(method = "serverTick", at = @At("HEAD"))
    private static void fastWorkstations$configureCookTime(
            ServerLevel level, BlockPos pos, BlockState state, AbstractFurnaceBlockEntity furnace, CallbackInfo ci) {
        AbstractFurnaceBlockEntityMixin self = (AbstractFurnaceBlockEntityMixin) (Object) furnace;
        ItemStack input = self.items.get(0);
        ItemStack fuel = self.items.get(1);
        if (input.isEmpty() || (self.litTimeRemaining <= 0 && self.getBurnDuration(level, fuel) <= 0)) {
            return;
        }
        self.cookingTotalTime = Config.workstationTicks();
    }
}
