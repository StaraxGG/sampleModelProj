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
    final static Logger logger = LoggerFactory.getLogger(MovieListImpl.class);

    /* ---------------------------------------- Constants ----------------------------------------------------------- */

    /**
     * this method creates a new movielist with a given user as creator user
     *
     * @param movieListName
     * @param creatorUserName
     * @throws UserNotFoundException when the user could not be found
     */
    public MovieListImpl(String movieListName, String creatorUserName) throws UserNotFoundException {
        this(movieListName, creatorUserName, null);
        logger.debug("New MovieListImpl Object with name " + movieListName + " by user " + creatorUserName);
    }

    /**
     * this method creates a new movielist with a given user as creator user
     *
     * @param movieListName
     * @param creatorUserName
     * @param movie           movie to add to the watchlist directly
     * @throws UserNotFoundException when the user could not be found
     */
    public MovieListImpl(String movieListName, String creatorUserName, MovieImpl movie) throws UserNotFoundException {
        // run default constructor for init shit
        this();
        logger.debug("New MovieListImpl Object with name " + movieListName + " by user " + creatorUserName
                + " with movie " + movie.getId());

        // init attributes
        this.movieListName = movieListName;

        // work with the user
        User user = UserModel.getInstance().findById(creatorUserName);
        if (user == null)
            throw new UserNotFoundException();

        this.creatorUserName = user.getUsername();
        this.addUserByName(user.getUsername());

        // add the movie
        if (movie != null)
            this.movies.add(movie);
    }


    /* ---------------------------------------- Constructors -------------------------------------------------------- */
    protected MovieListImpl() {
        this.users = new HashSet<>();
        this.movies = new HashSet<>();
    }

    /**
     * adds a new user to the users list of this movielist
     *
     * @param username username of the user that should already exist in the database
     * @return true if the user was added, false if he was already on the list
     * @throws UserNotFoundException when the user could not be found
     * @deprecated use {@link #addUser(User)} instead
     */
    public boolean addUserByName(String username) throws UserNotFoundException {
        logger.debug("(Deprecated) add movielist " + this.getId() + " to user " + username);
        User tmpUser = UserModel.getInstance().findById(username);

        // check if this user exists
        if (tmpUser == null)
            throw new UserNotFoundException("The given User was not found");

        // check if this user is NOT in the list
        if (this.users.contains(tmpUser)) {

            // user was already in the list
            return false;
        }

        // add the user
        this.users.add((UserImpl) tmpUser);
        return true;


    }

    /* ---------------------------------------- Methods ------------------------------------------------------------- */

    @Override
    public int hashCode() {
        return Objects.hash(this.creatorUserName, this.users, this.movies, this.movieListName);
    }

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
        logger.debug("Adding Movie " + movie.toString() + "to MovieList " + this.getId());
        MovieModel movieModel = MovieModel.getInstance();
        MovieImpl tmpMovie;

        if (!(movie instanceof MovieImpl) || this.movies.contains(movie)) {
            logger.debug("Movie " + movie.getId() + " either not instance of MovieImpl or List already " +
                    "contains movie " + movie.getId());
            return false;
        }

        // search the database for this movie
        logger.debug("Attempting to find movie " + movie.getId() + "in Tmdb Database (TmdBMovieId: " +
                movie.getTmdbId() + ")");
        tmpMovie = movieModel.findByTmdbId(movie.getTmdbId());

        // if this movie is already in the database, then add THAT to this MovieList
        if (tmpMovie != null) {
            logger.debug("tmpMovie" + movie.getId() + " is already in database, " +
                    "will be added to THIS movie" +
                    "list " + this.getId());
            this.movies.add(tmpMovie);

            // also: make the bidirectional connection
            logger.debug("Making bidirectional connections");
            logger.debug("Adding tmpMovie" + tmpMovie.getId() + "to this movie list " + this.getId());
            tmpMovie.addMovieList(this);
            logger.debug("Updating MovieModel with movie " + tmpMovie.getId());
            movieModel.update(tmpMovie);
        } else {
            // otherwise add THIS movie to this list and persist it in the database
            logger.debug("Movie " + movie.getId() + " is NOT already in database, so it will be" +
                    "added to this " + this.getId() + " list and persisted in database");
            logger.debug("Adding movie " + movie.getId() +
                    " to movies of this " + this.getId() + " list");
            this.movies.add((MovieImpl) movie);
            logger.debug("Add this " + this.getId() + "to movie " + movie.getId());
            movie.addMovieList(this);
            logger.debug("Persisting movie " + movie.getId() + " to the database");
            movieModel.persist((MovieImpl) movie);
        }

        return true;
    }

    @Override
    public boolean addMovies(List<Movie> movies) {
        logger.debug("Adding movies from list " + movies.toString() + " to this " + this.getId() + "list");
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
        logger.debug("Adding user " + user.getUsername() + " to this list " + this.getId());
        // null check
        if (user == null) {
            logger.warn("Give user " + user.getUsername() + " was null");
            return false;
        }
        // verify if this user exists
        logger.debug("Verifying that user " + user.getUsername() + " exists.");
        User userTmp = UserModel.getInstance().findById(user.getUsername());
        if (userTmp == null) {
            logger.error("User " + user.getUsername() + " was not found in UserModel");
            throw new UserNotFoundException();
        }
        // give this user the movie list
        logger.debug("Add this list " + this.getId() + " to user " + user.getUsername());
        user.addMovieList(this);

        // add this user to the movieList
        logger.debug("Adding user " + user.getUsername() + " to this list " + this.getId());
        return this.users.add((UserImpl) user);
    }

    @Override
    public boolean hasUser(User user) {
        logger.debug("Checking if user " + user.getUsername() + " is in this list.");
        if (!(user instanceof UserImpl))
            return false;
        return this.users.contains(user);
    }

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



    /* ---------------------------------------- S/Getters ----------------------------------------------------------- */

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
        logger.debug("Check if movie " + movie.getId() + " is contained in this list " + this.getId());
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



