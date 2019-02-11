package Tools;

import java.io.File;

/**
 * An implementation of MessageLocationMarker
 * in Prog2_Codebase
 *
 * @author takezo (jkrieger)
 * @version 1.0
 * @since 2018-May-08
 */
public abstract class MessageLocationMarker {

    /* ---------------------------------------- Main ---------------------------------------------------------------- */

    /* ---------------------------------------- Attributes ---------------------------------------------------------- */

    /* ---------------------------------------- Constants ----------------------------------------------------------- */

    /* ---------------------------------------- Constructors -------------------------------------------------------- */

    /**
     * default constructor for marker interface
     */
    private MessageLocationMarker() {

    }

    /* ---------------------------------------- Methods ------------------------------------------------------------- */

    /**
     * retrieves the location of this class
     *
     * @return String
     */
    public static String getLocation() {
        return MessageLocationMarker.class.getProtectionDomain().getCodeSource().getLocation().toString() + "Tools/";
    }

    /**
     * Returns location of the specified message_<local>.properties file in String format.
     *
     * @param local
     * @return String
     */
    public static String getMessagePropertiesLocation(String local) {
        String dir = MessageLocationMarker.getLocation().replace("file:", "");
        String correctLocal = local.toLowerCase();


        File directory = new File(dir);
        if (directory.isDirectory()) {

            for (File file : directory.listFiles()) {
                if (file.isFile() && file.getName().equals("message_" + correctLocal + ".properties")) {
                    return dir + "message_" + correctLocal + ".properties";
                }
            }

        }
        return null;
    }

    /* ---------------------------------------- S/Getters ----------------------------------------------------------- */

}
