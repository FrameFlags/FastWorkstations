package itamio.iamfastworkstations;

import net.minecraftforge.fml.common.Mod;

/**
 * Fast Workstations — Itamio Port (Forge 1.20.1).
 *
 * <p>All behaviour lives in mixins; the mod constructor only loads the config.</p>
 */
@Mod(FastWorkstationsMod.MOD_ID)
public final class FastWorkstationsMod {

    public static final String MOD_ID = "itamiofastws";

    public FastWorkstationsMod() {
        Config.load();
    }
}
