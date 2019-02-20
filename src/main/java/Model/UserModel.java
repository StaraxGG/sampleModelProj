package Model;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * An implementation of UserModel
 * in samplemodelproject
 *
 * @author ytatar
 * @version 1.0
 * @since 2019-Feb-11
 */
public class UserModel extends MasterModel<Long, User> {

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
    public static User login(User user){
        throw new NotImplementedException();
    }

    /**
     * checks if this username is already used at the database and return false if so.
     * otherwise registers this user in the database and logs him in automatically
     * @param user
     * @return
     */
    public static boolean register(User user){
        throw new NotImplementedException();
    }

    /* ---------------------------------------- S/Getters ----------------------------------------------------------- */

    /**
     * returns the currentUser that is logged in or otherwise null
     * @return User
     */
    public static User getCurrentUser(){
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

}

