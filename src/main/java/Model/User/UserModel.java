package Model.User;

import Model.MasterModel;
import Model.User.Exception.UserNotFoundException;
import Model.User.Exception.UserWrongPasswordException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * An implementation of UserModel
 * in samplemodelproject
 *
 * @author ytatar
 * @version 1.0
 * @since 2019-Feb-11
 */
public class UserModel extends MasterModel<String, UserImpl> {

    /* ---------------------------------------- Main ---------------------------------------------------------------- */

    private static User currentUser;

    /* ---------------------------------------- Attributes ---------------------------------------------------------- */
    private static UserModel userModel;


    private UserModel() {
        super();
    }

    /* ---------------------------------------- Constants ----------------------------------------------------------- */

    /* ---------------------------------------- Constructors -------------------------------------------------------- */

    /* ---------------------------------------- Methods ------------------------------------------------------------- */

    public static UserModel getInstance() {
        if (userModel == null)
            userModel = new UserModel();

        return userModel;
    }

    /**
     * verifies that the given username is a valid mail adress
     *
     * @param username
     * @return true if username mail adress
     */
    private static boolean verifyMailAdress(String username) {

        final Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(username);
        return matcher.find();
    }

    /**
     * checks this users credentials and logs him in, into the application if valid
     *
     * @param user @NotNull
     * @return
     * @throws UserNotFoundException      when the given user could not be found by his username
     * @throws IllegalArgumentException   when the given user object was null
     * @throws UserWrongPasswordException when the password of this user was wrong
     */
    public User login(User user) throws UserNotFoundException, UserWrongPasswordException {

        if (user == null)
            throw new IllegalArgumentException("The given User object was null.");

        User tmpUser = super.findById(user.getUsername());

        // check if he is already in the database
        if (tmpUser == null)
            throw new UserNotFoundException(String.format("The user with the name %s was not found in the database", user.getUsername()));

        // check if he has the right credentials
        if (tmpUser.getPasswordHash().equals(user.getPasswordHash())) {
            setCurrentUser(user);
            return getCurrentUser();
        } else
            throw new UserWrongPasswordException(String.format("The password for the user (%s) was wrong.", user.getUsername()));
    }

    /**
     * logs the current user out
     */
    public void logout() {
        currentUser = null;
    }

    /* ---------------------------------------- S/Getters ----------------------------------------------------------- */

    /**
     * checks if this username is already used at the database and return false if so.
     * otherwise registers this user in the database and logs him in automatically
     *
     * @param user
     * @return
     */
    public boolean register(User user) {

        // check if the user address is valid
        if (!verifyMailAdress(user.getUsername()))
            return false;

        // try to retrieve this user
        User tmpUser = super.findById(user.getUsername());

        // check if the user exists
        if (tmpUser != null) {
            // this user exists
            return false;
        }

        // otherwise create a new user
        this.persist((UserImpl) user);
        try {
            this.login(user);
        } catch (UserNotFoundException | UserWrongPasswordException e) {
            // add logging here
        }

        return true;
    }

    /**
     * returns the currentUser that is logged in or otherwise null
     *
     * @return User
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * sets the current user
     *
     * @param user
     */
    private static void setCurrentUser(User user) {
        currentUser = user;
    }

}

