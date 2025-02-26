package ir.rabity;

import com.google.common.base.Throwables;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

public class RabityConfig {
    private static File CONFIG_FILE;
    private static final String HEADER = "This is the main configuration file for RabbitSpigot.\n"
            + "As you can see, there's tons to configure. Some options may impact gameplay";

    public static YamlConfiguration config;

    public static void init(File configFile) {
        CONFIG_FILE = configFile;
        config = new YamlConfiguration();
        try {
            if (CONFIG_FILE != null && CONFIG_FILE.getParentFile() != null) {
                CONFIG_FILE.getParentFile().mkdirs(); // Ensure parent directories exist
            }

            if (!CONFIG_FILE.exists()) {
                CONFIG_FILE.createNewFile(); // Create the file
            }
//k
            config.load(CONFIG_FILE);
        } catch (InvalidConfigurationException e) {
            Bukkit.getLogger().log(Level.SEVERE, "Could not load rabbitspigot.yml, please correct your syntax errors", e);
            throw Throwables.propagate(e);
        } catch (IOException e) {
            throw new RuntimeException("Failed to create or load configuration file", e);
        }
        config.options().header(HEADER);
//dfdf
//sdf
        set("logging", "INFO");

        config.addDefault("knockback.friction", 2.0);
        config.addDefault("knockback.horizontal", 0.9064);
        config.addDefault("knockback.vertical", 0.25648);
        config.addDefault("knockback.verticalLimit", 4.0);
        config.addDefault("knockback.extraHorizontal", 0.5);
        config.addDefault("knockback.extraVertical", 0.1);

//        config.addDefault("commands.plugins.disabled", false);
//        config.addDefault("commands.plugins.only-for-op", true);
//
//        config.options().copyDefaults(true);
        try {
            config.save(configFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



        /*===================================================*/





    }

    public static YamlConfiguration getConfig() {
        return config;
    }

    public static double getKnockbackValue(String path) {
        return config.getDouble("knockback." + path);
    }

    private static void set(String path, Object val)
    {
        config.set( path, val );
    }

    private static void addDefault(String path, Object val) {
        config.addDefault(path, val);
    }

    private static boolean getBoolean(String path, boolean def)
    {
        config.addDefault( path, def );
        return config.getBoolean( path, config.getBoolean( path ) );
    }

    private static int getInt(String path, int def)
    {
        config.addDefault( path, def );
        return config.getInt( path, config.getInt( path ) );
    }

    private static <T> List getList(String path, T def)
    {
        config.addDefault( path, def );
        return (List<T>) config.getList( path, config.getList( path ) );
    }

    private static String getString(String path, String def)
    {
        config.addDefault( path, def );
        return config.getString( path, config.getString( path ) );
    }

    private static double getDouble(String path, double def)
    {
        config.addDefault( path, def );
        return config.getDouble( path, config.getDouble( path ) );
    }

}
