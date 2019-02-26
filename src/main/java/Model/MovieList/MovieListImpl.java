package Model.MovieList;

import Model.Movie.Movie;
import Model.Movie.MovieImpl;
import Model.User.Exception.UserNotFoundException;
import Model.User.User;
import Model.User.UserImpl;
import Model.User.UserModel;

import javax.persistence.*;
import java.util.*;

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



    @OneToMany(targetEntity = MovieImpl.class)
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

    /* ---------------------------------------- Constants ----------------------------------------------------------- */

    /* ---------------------------------------- Constructors -------------------------------------------------------- */
    public MovieListImpl() {
        this.users = new HashSet<>();
        this.movies = new HashSet<>();
    }

    /**
     * this method creates a new movielist with a given user as creator user
     * @param movieListName
     * @param creatorUserName
     * @throws UserNotFoundException when the user could not be found
     */
    public MovieListImpl(String movieListName, String creatorUserName) throws UserNotFoundException {
        this(movieListName, creatorUserName, null);
    }


    /**
     * this method creates a new movielist with a given user as creator user
     * @param movieListName
     * @param creatorUserName
     * @param movie movie to add to the watchlist directly
     * @throws UserNotFoundException when the user could not be found
     */
    public MovieListImpl(String movieListName, String creatorUserName, MovieImpl movie) throws UserNotFoundException {
        // init objects
        this.movieListName = movieListName;
        this.users = new HashSet<>();
        this.movies = new HashSet<>();

        // work with the user
        User user = UserModel.getInstance().findById(creatorUserName);
        if (user == null)
            throw new UserNotFoundException();

        this.creatorUserName = user.getUsername();
        this.addNewUser(user.getUsername());

        // add the movie
        if (movie != null)
            this.movies.add(movie);
    }


    /* ---------------------------------------- Methods ------------------------------------------------------------- */

    /**
     * Adds the received movie to the movies if
     * it is not already on the list
     *
     * @param movie movie object
     * @return boolean success
     */
    @Override
    public boolean addMovie(MovieImpl movie) {
        if (movie != null && !this.movies.contains(movie)) {
            this.movies.add(movie);
            return true;
        }

        return false;
    }

    /**
     * Deletes the received movie out of the movies if existing
     *
     * @param movie movie object
     * @return boolean success
     */
    @Override
    public boolean deleteMovie(MovieImpl movie) {
        if (this.movies.contains(movie)) {
            this.movies.remove(movie);
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(Movie movie) {
        return this.movies.contains(movie);
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

    @Override
    public int hashCode() {
        return Objects.hash(this.creatorUserName, this.users, this.movies, this.movieListName);
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

    /**
     * adds a new user to the users list of this movielist
     * @param username username of the user that should already exist in the database
     * @return true if the user was added, false if he was already on the list
     * @throws UserNotFoundException when the user could not be found
     */
    public boolean addNewUser(String username) throws UserNotFoundException {

        User tmpUser = UserModel.getInstance().findById(username);

        // check if this user exists
        if (tmpUser == null)
            throw new UserNotFoundException();

        // check if this user is NOT in the list
        if (this.users.contains(tmpUser)) {

            // user was already in the list
            return false;
        }

        // add the user
        this.users.add((UserImpl)tmpUser);
        return true;


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



