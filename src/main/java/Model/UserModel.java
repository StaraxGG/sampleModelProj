package Model;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;
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
     * checks this users credentials and logs him into the database if valid
     * then this user will be set as currentUser automatically
     * otherwise returns null
     *
     * @param user User
     * @return User
     */
    public User login(User user) {

        User tmpUser = super.findById(user.getUsername());

        if (tmpUser == null)
            return null;

        if (tmpUser.getPasswordHash().equals(user.getPasswordHash())) {
            setCurrentUser(user);
            return getCurrentUser();
        } else return null;
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
        this.persist(user);
        this.login(user);

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

    /**
     * returns a user object for the given id
     * the returned user is not logged in automatically
     * this method can be used to retrieve user information for displaying in MovieLists
     *
     * @param id T
     * @return User
     */
    public UserImpl findById(Long id) {

        // just pass that stuff along
        return userModel.findById(id);
    }

    /**
     * this method returns all movielists where this movie is stored in already
     * therefore you can show the user if he has already saved a movie and in which lists
     * the movies are compared by their tmdpId / equals-method
     * @param movie
     * @return
     */
    List<MovieListImpl> findMovielistForMovie(Movie movie){
        throw new NotImplementedException();
    }
}

