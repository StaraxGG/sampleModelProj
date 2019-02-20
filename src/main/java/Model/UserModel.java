package Model;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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
public class UserModel extends MasterModel<String, User> {

    /* ---------------------------------------- Main ---------------------------------------------------------------- */

    public static void main(String[] args) {
    }

    /* ---------------------------------------- Attributes ---------------------------------------------------------- */

    private static User currentUser;
    private static UserModel userModel;

    /* ---------------------------------------- Constants ----------------------------------------------------------- */

    /* ---------------------------------------- Constructors -------------------------------------------------------- */

    private UserModel(){

    }

    /* ---------------------------------------- Methods ------------------------------------------------------------- */

    public static UserModel getInstance(){
        if (userModel == null)
            userModel = new UserModel();

        return userModel;
    }

    /**
     * checks this users credentials and logs him into the database if valid
     * then this user will be set as currentUser automatically
     * otherwise returns null
     * @param user User
     * @return User
     */
    public User login(User user){

        User tmpUser = super.findById(user.getUsername());

        if (tmpUser == null)
            return null;

        if (tmpUser.getPasswordHash().equals(user.getPasswordHash())) {
            setCurrentUser(user);
            return getCurrentUser();
        }

        else return null;
    }

    /**
     * checks if this username is already used at the database and return false if so.
     * otherwise registers this user in the database and logs him in automatically
     * @param user
     * @return
     */
    public boolean register(User user){

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
        this.persist(user);
        this.login(user);

        return true;
    }

    /* ---------------------------------------- S/Getters ----------------------------------------------------------- */

    /**
     * returns the currentUser that is logged in or otherwise null
     * @return User
     */
    public User getCurrentUser(){
        return currentUser;
    }

    /**
     * sets the current user
     * @param user
     */
    private static void setCurrentUser(User user){
        currentUser = user;
    }

    /**
     * returns a user object for the given id
     * the returned user is not logged in automatically
     * this method can be used to retrieve user information for displaying in MovieLists
     * @param id T
     * @return User
     */
    public User findById(Long id){

        // just pass that stuff along
        return userModel.findById(id);
    }

    /**
     * verifies that the given username is a valid mail adress
     * @param username
     * @return true if username mail adress
     */
    private static boolean verifyMailAdress(String username){

        final Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(username);
        return matcher.find();
    }
}

