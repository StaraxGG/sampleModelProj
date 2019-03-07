package Tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

/**
 * An implementation of ConfigTools
 * in samplemodelproject
 *
 * @author ytatar
 * @version 1.0
 * @since 2019-Feb-10
 */
public class ConfigTools {

    /* ---------------------------------------- Attributes ---------------------------------------------------------- */
    private static Properties p;
    private static String configFilePath = "src/main/resources/configuration.properties";

    final Logger logger = LoggerFactory.getLogger(ConfigTools.class);

    /* ---------------------------------------- Main ---------------------------------------------------------------- */

    static {

        p = new Properties();

        try (FileReader f = new FileReader(configFilePath)) {

            p.load(f);

        } catch (Exception e) {
            final Logger logger = LoggerFactory.getLogger(ConfigTools.class);
            logger.error(e.getLocalizedMessage());
        }

    }

    public static void main(String[] args) {
        String lang = ConfigTools.getVal("lang");

        ConfigTools.setVal("lang", "en");
        String lang2 = ConfigTools.getVal("lang");

        ConfigTools.restoreDefault();
        ConfigTools.setVal("test", "true");
        ConfigTools.setVal("test", "false");
        System.exit(0);

    }

    /* ---------------------------------------- Constants ----------------------------------------------------------- */

    /* ---------------------------------------- Constructors -------------------------------------------------------- */

    /* ---------------------------------------- Methods ------------------------------------------------------------- */

    /* ---------------------------------------- S/Getters ----------------------------------------------------------- */

    /**
     * returns the value for this key
     * if a new value was set then this will be returned, otherwise the default value will be searched
     *
     * @param key
     * @return value
     */
    public static String getVal(String key) {
        String val = "";
        if ((val = p.getProperty(String.format("%s(changed)", key))) != null) {
            return val;
        } else if ((val = p.getProperty(key)) != null) {
            return val;
        } else {
            return String.format("Key %s not found. Called By: %s", key, Thread.currentThread().getStackTrace()[2]);
        }
    }

    /**
     * sets a new value to the properties
     * if the value existed it will be modified and a :changed tag will be appended
     *
     * @param key
     * @param value
     */
    public static void setVal(String key, String value) {
        if (p.containsKey(key)) {
            p.setProperty(String.format("%s(changed)", key), value);
        } else {
            p.setProperty(key, value);
        }

        // store the changes
        storeProperties();
    }

    /**
     * restores the default settings
     */
    public static void restoreDefault() {
        for (String key : p.stringPropertyNames()) {
            if (key.endsWith("(changed)")) {
                p.remove(key);
            }
        }

        // store the changes
        storeProperties();
    }

    /**
     * stores the properties file to the disk
     */
    private static void storeProperties() {

        try {
            p.store(new FileWriter(configFilePath), "Configuration File, Saved at: " + new Date().toString());
        } catch (IOException ioe) {
            final Logger logger = LoggerFactory.getLogger(ConfigTools.class);
            logger.error(ioe.getMessage(),ioe);
        }

    }

}

/*class ConfigLocationMarker {

    protected static String getLocation(){
        System.out.println("Class path: " + ConfigLocationMarker.class.getProtectionDomain().getCodeSource().getLocation().toString());
        return ConfigLocationMarker.class.getProtectionDomain().getCodeSource().getLocation().toString() + "Tools/";
    }

}*/

