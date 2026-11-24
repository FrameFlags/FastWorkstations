package itamio.iamfastworkstations;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Simple file-based config (no GUI).
 *
 * <p>Reads config/itamiofastws.properties relative to the game directory.
 * System properties {@code itamiofastws.workstationTicks} and
 * {@code itamiofastws.hopperTicks} override the file. Values are clamped to
 * at least 1 tick, so the default/differentiator is one item per tick.</p>
 */
public final class Config {

    public static final String FILE_NAME = "itamiofastws.properties";

    private static int workstationTicks = 1;
    private static int hopperTicks = 1;

    private Config() {}

    public static void load() {
        Properties props = new Properties();
        File dir = new File("config");
        File file = new File(dir, FILE_NAME);
        if (file.isFile()) {
            try (FileInputStream in = new FileInputStream(file)) {
                props.load(in);
            } catch (IOException ignored) {
                // Fall back to defaults.
            }
        }

        workstationTicks = readInt(props, "workstationTicks", "itamiofastws.workstationTicks", 1);
        hopperTicks = readInt(props, "hopperTicks", "itamiofastws.hopperTicks", 1);

        if (!file.isFile()) {
            try {
                if (!dir.isDirectory()) {
                    dir.mkdirs();
                }
                props.setProperty("workstationTicks", Integer.toString(workstationTicks));
                props.setProperty("hopperTicks", Integer.toString(hopperTicks));
                try (FileOutputStream out = new FileOutputStream(file)) {
                    props.store(out, "Fast Workstations - Itamio Port (ticks per operation, min 1)");
                }
            } catch (IOException ignored) {
                // Config file is optional.
            }
        }
    }

    /** Ticks a furnace / blast furnace / smoker spends per smelted item. */
    public static int workstationTicks() {
        return Math.max(1, workstationTicks);
    }

    /** Ticks between hopper transfer attempts. */
    public static int hopperTicks() {
        return Math.max(1, hopperTicks);
    }

    private static int readInt(Properties props, String key, String sysProp, int fallback) {
        String raw = System.getProperty(sysProp);
        if (raw == null) {
            raw = props.getProperty(key);
        }
        if (raw == null) {
            return fallback;
        }
        try {
            return Math.max(1, Integer.parseInt(raw.trim()));
        } catch (NumberFormatException e) {
            return fallback;
        }
    }
}
