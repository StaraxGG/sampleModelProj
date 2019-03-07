package Tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.util.Objects;
import java.util.Properties;

/**
 * An implementation of MessageTools
 * in Prog2_Ueb14
 *
 * @author ytatar
 * @version 1.0
 * @since 2018-Apr-16
 */
public class MessageTools {

    private static Properties p;

    /* ---------------------------------------- Main ---------------------------------------------------------------- */


    static {

        p = new Properties();

        try (FileReader f = new FileReader(Objects.requireNonNull(MessageLocationMarker.getMessagePropertiesLocation("de")))) {

            p.load(f);

        } catch (Exception e) {
            final Logger logger = LoggerFactory.getLogger(MessageTools.class);
            logger.error(e.getLocalizedMessage(),e);
        }

    }



    /* ---------------------------------------- Attributes ---------------------------------------------------------- */



    /* ---------------------------------------- Constants ----------------------------------------------------------- */



    /* ---------------------------------------- Constructors -------------------------------------------------------- */



    /* ---------------------------------------- Methods ------------------------------------------------------------- */

    public static String getMsg(String key) {
        return p.getProperty(key, key + " NOT FOUND." + "Called By: "
                + Thread.currentThread().getStackTrace()[2]);
    }

    /* ---------------------------------------- S/Getters ----------------------------------------------------------- */

}
