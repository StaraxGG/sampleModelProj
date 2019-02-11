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

    /* ---------------------------------------- Attributes ---------------------------------------------------------- */

    private User currentUser;

    /* ---------------------------------------- Constants ----------------------------------------------------------- */

    /* ---------------------------------------- Constructors -------------------------------------------------------- */

    /* ---------------------------------------- Methods ------------------------------------------------------------- */

    /**
     * checks if this users credentials are correct for them to login
     * then returns a valid user object or null
     * if the user vas valid he will be logged in as the current user automatically
     * @param username
     * @param password
     * @return User
     */
    public User checkUserCredentials(String username, String password){
        throw new NotImplementedException();
    }

    /* ---------------------------------------- S/Getters ----------------------------------------------------------- */

    /**
     * returns the currentUser that is logged in or otherwise null
     * @return User
     */
    public User getCurrentUser(){
        return this.currentUser;
    }

    /**
     * sets the current user
     * @param user
     */
    private void setCurrentUser(User user){
        this.currentUser = user;
    }

}

