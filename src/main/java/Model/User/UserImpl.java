package Model.User;

import Model.Movie.Movie;
import Model.MovieList.MovieList;
import Model.MovieList.MovieListImpl;
import Tools.MessageTools;
import com.sun.istack.internal.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    @ManyToMany(fetch = FetchType.EAGER)
    private List<MovieListImpl> movieLists;

    @Transient
    final Logger logger = LoggerFactory.getLogger(UserImpl.class);

    /* ---------------------------------------- Constants ----------------------------------------------------------- */

    /* ---------------------------------------- Constructors -------------------------------------------------------- */

    /**
     * creates a new user object that is not yet persisted in the database
     * @param userName not null user name
     * @param password password not null and not empty!
     * @throws IllegalArgumentException
     */
    public UserImpl(String userName, String password) throws IllegalArgumentException {
        if (userName == null || userName.isEmpty())
            throw new IllegalArgumentException("");

        if (password == null || password.isEmpty())
            throw new IllegalArgumentException(String.format("The given password (%s) was invalid.",password));
        this.setUserName(userName);
        this.setPasswordHash(password);
        this.movieLists = new LinkedList<>();
    }

    protected UserImpl() {
    }

    /* ---------------------------------------- Methods ------------------------------------------------------------- */

    @Override
    public boolean addMovieList(MovieList movieList) {
        if (!(movieList instanceof MovieListImpl))
            return false;

        if (this.movieLists.contains(movieList)) {
            return false;
        }

        this.movieLists.add((MovieListImpl) movieList);
        return true;
    }

    @Override
    public boolean deleteMovieList(@NotNull MovieList movieList) {
        return this.movieLists.removeIf((userMovieList) -> userMovieList.equals(movieList));
    }

    @Override
    public List<? extends MovieList> findMovielistForMovie(Movie movie){
        // check for null value
        if (movie == null)
            return null;
        List<MovieListImpl> resultLists = new LinkedList<>();

        for (MovieList movieList : this.getMovieLists()){

            if (movieList.contains(movie))
                resultLists.add((MovieListImpl) movieList);

        }

        return resultLists;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof UserImpl))
            return false;
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

    private void setUserName(String userName){
        this.userName = userName;
    }

    private void setPasswordHash(String password){
        this.passwordHash = hash(password);
    }

    private void setMovieLists(List<MovieListImpl> movieLists) {
        this.movieLists = movieLists;
    }

}

