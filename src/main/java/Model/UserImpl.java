package Model;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

import static java.util.Objects.hash;

/**
 * An implementation of UserImpl
 * in samplemodelproject
 *
 * @author ytatar
 * @version 1.0
 * @since 2019-Feb-11
 */
public class UserImpl implements User {

    /* ---------------------------------------- Main ---------------------------------------------------------------- */

    /* ---------------------------------------- Attributes ---------------------------------------------------------- */

    private Long userID;

    private String userName;

    private Integer passwordHash;

    /* ---------------------------------------- Constants ----------------------------------------------------------- */

    /* ---------------------------------------- Constructors -------------------------------------------------------- */

    public UserImpl(String userName, String password){
        this.setUserName(userName);
        this.setPasswordHash(password);
    }

    /* ---------------------------------------- Methods ------------------------------------------------------------- */

    @Override
    public boolean addMovieList(MovieList movieList) {
        throw new NotImplementedException();
    }

    @Override
    public boolean deleteMovieList(Long id) {
        throw new NotImplementedException();
    }

    @Override
    public Long getUserId() {
        return null;
    }

    @Override
    public String getUserName() {
        return null;
    }

    @Override
    public List<MovieList> getMovieLists() {
        return null;
    }

    @Override
    public Integer getPasswordHash() {
        return null;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = hash(passwordHash);
    }

    /* ---------------------------------------- S/Getters ----------------------------------------------------------- */

}

