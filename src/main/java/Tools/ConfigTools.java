package Tools;

import java.io.FileReader;
import java.util.Objects;
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

    /* ---------------------------------------- Main ---------------------------------------------------------------- */

    static {

        p = new Properties();

        try (FileReader f = new FileReader("Tools/configuration.properties")) {

            p.load(f);

        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
        }

    }

    /* ---------------------------------------- Constants ----------------------------------------------------------- */

    /* ---------------------------------------- Constructors -------------------------------------------------------- */

    /* ---------------------------------------- Methods ------------------------------------------------------------- */

    /* ---------------------------------------- S/Getters ----------------------------------------------------------- */

    public static String getVal(String key) {
        return p.getProperty(key, key + " NOT FOUND." + "Called By: "
                + Thread.currentThread().getStackTrace()[2]);
    }

}

