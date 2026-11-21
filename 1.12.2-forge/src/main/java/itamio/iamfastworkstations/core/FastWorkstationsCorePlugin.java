package itamio.iamfastworkstations.core;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import zone.rong.mixinbooter.IEarlyMixinLoader;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * FML core plugin that registers this mod's mixin config with MixinBooter.
 *
 * <p>Forge 1.12.2 has no native Mixin support. MixinBooter discovers core
 * plugins implementing {@link IEarlyMixinLoader} and queues the configs they
 * return before Minecraft classes load. The jar manifest declares this class
 * as {@code FMLCorePlugin}; Mixin itself is NOT shaded into this jar.</p>
 */
@IFMLLoadingPlugin.Name("itamiofastws")
@IFMLLoadingPlugin.MCVersion("1.12.2")
@IFMLLoadingPlugin.SortingIndex(1001)
public class FastWorkstationsCorePlugin implements IFMLLoadingPlugin, IEarlyMixinLoader {

    @Override
    public String[] getASMTransformerClass() {
        return new String[0];
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {
        // No setup required.
    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }

    @Override
    public List<String> getMixinConfigs() {
        return Collections.singletonList("itamiofastws.mixins.json");
    }
}
