package itamio.iamfastworkstations;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

/**
 * Fast Workstations — Itamio Port (Forge 1.12.2).
 *
 * <p>1.12.2 has no blast furnaces or smokers, so only furnaces and hoppers are
 * covered here. Mixins are registered with MixinBooter by
 * {@link FastWorkstationsLateMixinLoader}.</p>
 */
@Mod(modid = FastWorkstationsMod.MOD_ID, name = FastWorkstationsMod.MOD_NAME,
     version = FastWorkstationsMod.VERSION)
public class FastWorkstationsMod {

    public static final String MOD_ID = "itamiofastws";
    public static final String MOD_NAME = "Fast Workstations — Itamio Port";
    public static final String VERSION = "1.0.0";

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        Config.load();
    }
}
