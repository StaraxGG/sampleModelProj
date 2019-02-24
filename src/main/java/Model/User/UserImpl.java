package Model.User;

import Model.MovieList.MovieList;
import Model.MovieList.MovieListImpl;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

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
    @Column(name = "user_name")
    private String userName;

    @Column(name = "password_hash")
    private Integer passwordHash;

    @ManyToMany
    private List<MovieListImpl> movieLists;

    /* ---------------------------------------- Constants ----------------------------------------------------------- */

    /* ---------------------------------------- Constructors -------------------------------------------------------- */

    public UserImpl(String userName, String password) {
        this.setUserName(userName);
        this.setPasswordHash(password);
        this.movieLists = new LinkedList<>();
    }

    protected UserImpl() {
    }

    /* ---------------------------------------- Methods ------------------------------------------------------------- */

    @Override
    public boolean addMovieList(MovieList movieList) {
        if (this.movieLists.contains((MovieListImpl) movieList)){
            return false;
        }

        this.movieLists.add((MovieListImpl) movieList);
        return true;
    }

    @Override
    public boolean deleteMovieList(Long id) {
        this.movieLists.removeIf((movieList) -> movieList.getId().equals(id));
        return true;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public List<MovieListImpl> getMovieLists() {
        return this.movieLists;
    }

    @Override
    public Integer getPasswordHash() {
        return this.passwordHash;
    }

    @Override
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = hash(passwordHash);
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setMovieLists(List<MovieListImpl> movieLists) {
        this.movieLists = movieLists;
    }

    @Override
    public boolean equals(Object obj) {
        UserImpl otherUser = (UserImpl) obj;

        // check if name and password hash are the same
        return this.getUsername().equals(otherUser.getUsername())
                && (this.getPasswordHash().equals(otherUser.getPasswordHash()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.userName, this.passwordHash);
    }

    /* ---------------------------------------- S/Getters ----------------------------------------------------------- */

}

