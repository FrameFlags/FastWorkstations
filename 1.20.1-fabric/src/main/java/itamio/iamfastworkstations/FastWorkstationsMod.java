package itamio.iamfastworkstations;

import net.fabricmc.api.ModInitializer;

/**
 * Fast Workstations — Itamio Port (Fabric 1.20.1).
 *
 * <p>All behaviour lives in mixins; the entrypoint only loads the config.</p>
 */
public final class FastWorkstationsMod implements ModInitializer {

    @Override
    public void onInitialize() {
        Config.load();
    }
}
