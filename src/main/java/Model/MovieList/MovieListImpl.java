package Model.MovieList;

import Model.Movie.Movie;
import Model.Movie.MovieImpl;
import Model.Movie.MovieModel;
import Model.User.Exception.UserNotFoundException;
import Model.User.User;
import Model.User.UserImpl;
import Model.User.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * An implementation of MovieListImpl
 * in samplemodelproject
 *
 * @author CHW
 * @version 1.0
 * @since 2019-Feb-10
 */
@Entity
@Table(name = "movielist")
public class MovieListImpl implements MovieList {

    /* ---------------------------------------- Main ---------------------------------------------------------------- */

    /* ---------------------------------------- Attributes ---------------------------------------------------------- */


    @ManyToMany(targetEntity = MovieImpl.class, fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<MovieImpl> movies;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "movielist_id")
    private Long movieListID;

    @Column(name = "movielist_name")
    private String movieListName;

    @Column(name = "creator_user_name")
    private String creatorUserName;
    //list of userIDs

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<UserImpl> users;

    @Transient
    final Logger logger = LoggerFactory.getLogger(MovieListImpl.class);

    /* ---------------------------------------- Constants ----------------------------------------------------------- */

    /* ---------------------------------------- Constructors -------------------------------------------------------- */

    /**
     * this method creates a new movielist with a given user as creator user
     *
     * @param movieListName
     * @param creatorUserName
     * @throws UserNotFoundException when the user could not be found
     */
    public MovieListImpl(String movieListName, String creatorUserName) throws UserNotFoundException {
        this(movieListName, creatorUserName, null);
    }

    /**
     * this method creates a new movielist with a given user as creator user and the first movie
     *
     * @param movieListName
     * @param creatorUserName
     * @param movie           movie to add to the watchlist directly
     * @throws UserNotFoundException when the user could not be found
     */
    public MovieListImpl(String movieListName, String creatorUserName, MovieImpl movie) throws UserNotFoundException {
        // run default constructor for init shit
        this();

        // init attributes
        this.movieListName = movieListName;

        // check this user and work with this one afterwards
        UserImpl user = UserModel.getInstance().findById(creatorUserName);
        if (user == null)
            throw new UserNotFoundException();

        this.creatorUserName = user.getUsername();

        // add the user to the users list
        this.addUser(user);

        // add the movie
        if (movie != null)
            this.addMovie(movie);

        // add this movielist to this users lists
        user.addMovieList(this);
        UserModel.getInstance().update(user);
    }

    protected MovieListImpl() {
        this.users = new HashSet<>();
        this.movies = new HashSet<>();
    }

    /* ---------------------------------------- Methods ------------------------------------------------------------- */


    /**
     * Returns a hash value of the sequence of input values of the current object.
     *
     * @return int hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.creatorUserName, this.users, this.movies, this.movieListName);
    }


    /**
     * Compares the current MovieListImpl object to another object.
     * Returns true if they are equal, false if they are unequal.
     *
     * @param obj object
     * @return boolean success
     */
    @Override
    public boolean equals(Object obj) {

        // check if they are both MovieLists
        if (!(obj instanceof MovieList))
            return false;

        // cast to movielist
        MovieListImpl otherMovieList = (MovieListImpl) obj;

        // should have the same creator
        if (!this.getCreatorUserName().equals(((MovieListImpl) obj).getCreatorUserName()))
            return false;

        // should have the same name
        if (!this.getName().equals(((MovieListImpl)obj).getName()))
            return false;

        // should have the same users
        if (!this.getUsers().equals(otherMovieList.getUsers()))
            return false;

        // should have the same movies
        if (!this.getMovies().equals(otherMovieList.getMovies()))
            return false;

        return true;

    }

    /**
     * Adds the received movie to the movies if
     * it is not already on the list
     *
     * @param movie movie object
     * @return boolean success
     */
    @Override
    public boolean addMovie(Movie movie) {

        MovieModel movieModel = MovieModel.getInstance();
        MovieImpl tmpMovie;

        if (!(movie instanceof MovieImpl) || this.movies.contains(movie)) {
            return false;
        }

        // search the database for this movie
        tmpMovie = movieModel.findByTmdbId(movie.getTmdbId());

        // if this movie is already in the database, then add THAT to this MovieList
        if (tmpMovie != null) {
            this.movies.add(tmpMovie);

            // also: make the bidirectional connection
            tmpMovie.addMovieList(this);
            movieModel.update(tmpMovie);
        } else {
            // otherwise add THIS movie to this list and persist it in the database
            this.movies.add((MovieImpl) movie);
            movie.addMovieList(this);
            movieModel.persist((MovieImpl) movie);
        }

        return true;
    }

    @Override
    public boolean addMovies(List<Movie> movies) {
        if (movies == null)
            return false;

        for (Movie movie : movies)
            this.addMovie(movie);

        return true;
    }

    /**
     * Deletes the received movie out of the movies if existing
     *
     * @param movie movie object
     * @return boolean success
     */
    @Override
    public boolean deleteMovie(Movie movie) {
        if (movie instanceof MovieImpl && this.movies.contains(movie)) {
            this.movies.remove(movie);
            return true;
        }
        return false;
    }

    @Override
    public boolean addUser(User user) throws UserNotFoundException {
        // null check
        if (user == null)
            return false;

        // verify if this user exists
        User userTmp = UserModel.getInstance().findById(user.getUsername());
        if (userTmp == null)
            throw new UserNotFoundException();

        // give this user the movie list
        user.addMovieList(this);

        // add this user to the movieList
        return this.users.add((UserImpl) user);
    }

    @Override
    public boolean hasUser(User user) {
        if (!(user instanceof UserImpl))
            return false;
        return this.users.contains(user);
    }


    /* ---------------------------------------- S/Getters ----------------------------------------------------------- */

    /**
     * returns the id of this movielist
     *
     * @return Long
     */
    @Override
    public Long getId() {
        return this.movieListID;
    }

    /**
     * returns the Name of this movielist
     *
     * @return String
     */
    @Override
    public String getName() {
        return this.movieListName;
    }


    /**
     * Sets/changes the name of this movielist
     *
     * @param name name of movielist
     */
    public void setName(String name) {
        this.movieListName = name;
    }

    /**
     * returns the movielist
     *
     * @return List<MovieImpl>
     */
    @Override
    public Set<MovieImpl> getMovies() {
        return this.movies;
    }

    protected void setMovies(Set<MovieImpl> movies) {
        this.movies = movies;
    }

    /**
     * returns the ID of the creator of this list
     *
     * @return Long
     */
    @Override
    public String getCreatorUserName() {
        return this.creatorUserName;
    }

    /**
     * returns the userlist of this movielist
     *
     * @return List<Long>
     */
    @Override
    public Set<UserImpl> getUsers() {
        return this.users;
    }

    @Override
    public boolean contains(Movie movie) {
        // instaceof checks for null as well
        // i mean if this is not a movie, why check at all?
        if (!(movie instanceof MovieImpl))
            return false;
        return this.movies.contains(movie);
    }

    /**
     * Sets the movielist ID if not already set
     *
     * @param movieListID ID of this movielist
     */
    public void setMovieListID(Long movieListID) {
        if (this.movieListID == null) {
            this.movieListID = movieListID;
        }
    }


}



