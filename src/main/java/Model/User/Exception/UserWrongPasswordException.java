package Model.User.Exception;

/**
 * An implementation of UserWrongPasswordException
 * in samplemodelproject
 *
 * @author ytatar
 * @version 1.0
 * @since 2019-Mar-17
 */
public class UserWrongPasswordException extends Exception {

    /* ---------------------------------------- Main ---------------------------------------------------------------- */

    /* ---------------------------------------- Attributes ---------------------------------------------------------- */

    /* ---------------------------------------- Constants ----------------------------------------------------------- */

    /* ---------------------------------------- Constructors -------------------------------------------------------- */

    /**
     * this exception is thrown when the given password was wrong
     * @param msg
     */
    public UserWrongPasswordException(String msg){
        super(msg);
    }

    /* ---------------------------------------- Methods ------------------------------------------------------------- */

    /* ---------------------------------------- S/Getters ----------------------------------------------------------- */

}

