package Model;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.persistence.*;
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
@Entity
@Table(name = "user")
public class UserImpl implements User {

    /* ---------------------------------------- Main ---------------------------------------------------------------- */

    /* ---------------------------------------- Attributes ---------------------------------------------------------- */

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userID;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password_hash")
    private Integer passwordHash;

    /* ---------------------------------------- Constants ----------------------------------------------------------- */

    /* ---------------------------------------- Constructors -------------------------------------------------------- */

    public UserImpl(String userName, String password) {
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
    public String getUsername() {
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

    @Override
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = hash(passwordHash);
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    /* ---------------------------------------- S/Getters ----------------------------------------------------------- */

}

