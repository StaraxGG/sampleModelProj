package Model.MovieList;

import Model.Movie.Movie;
import Model.Movie.MovieImpl;
import Model.User.UserImpl;
import Model.User.UserModel;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

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
    private List<MovieImpl> movies;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "movielist_id")
    private Long movieListID;

    @Column(name = "movielist_name")
    private String movieListName;

    @Column(name = "creator_user_name")
    private String creatorUserName;
    //list of userIDs

    @ManyToMany
    /*@JoinTable(
            name = "movielist_user",
            joinColumns = {@JoinColumn(name = "fk_movielist")},
            inverseJoinColumns = {@JoinColumn(name = "fk_user")}
    )*/
    private List<UserImpl> users;

    /* ---------------------------------------- Constants ----------------------------------------------------------- */

    /* ---------------------------------------- Constructors -------------------------------------------------------- */
    public MovieListImpl() {
        this.users = new LinkedList<>();
        this.movies = new LinkedList<>();
    }

    public MovieListImpl(String movieListName, String creatorUserName) {
        this.movieListName = movieListName;
        this.creatorUserName = creatorUserName;

        this.users = new LinkedList<>();
        this.users.add(UserModel.getInstance().findByUserName(creatorUserName));

        this.movies = new LinkedList<>();
    }

    public MovieListImpl(String movieListName, String creatorUserName, MovieImpl movie) {
        this.movieListName = movieListName;
        this.creatorUserName = creatorUserName;

        this.users = new LinkedList<>();
        this.users.add(UserModel.getInstance().findByUserName(creatorUserName));

        this.movies = new LinkedList<>();
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
        if (!this.movies.contains(movie)) {
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
    public List<MovieImpl> getMovies() {
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
    public List<UserImpl> getUsers() {
        return this.users;
    }

    /**
     * Adds a new user to the users list if the user
     * is not already on the list
     *
     * @param username ID of the current user who want to use the list
     * @return boolean success
     */
    public boolean addNewUser(String username) {

        UserImpl tmpUser = UserModel.getInstance().findByUserName(username);

        // check if this user exists
        if (tmpUser == null)
            return false;

        // check if this user is NOT in the list
        if (!this.users.contains(tmpUser)) {

            // add the user
            this.users.add(tmpUser);
            return true;
        }

        // user was already in the list
        return false;
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



