package Model.MovieList;

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

    @Column(name = "creator_user_id")
    private Long creatorUserID;
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

    public MovieListImpl(String movieListName, Long creatorUserID) {
        this.movieListName = movieListName;
        this.creatorUserID = creatorUserID;

        this.users = new LinkedList<>();
        this.users.add(UserModel.getInstance().findById(creatorUserID));

        this.movies = new LinkedList<>();
    }

    public MovieListImpl(String movieListName, Long creatorUserID, MovieImpl movie) {
        this.movieListName = movieListName;
        this.creatorUserID = creatorUserID;

        this.users = new LinkedList<>();
        this.users.add(UserModel.getInstance().findById(creatorUserID));

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
    protected void setName(String name) {
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
    public Long getCreatorUserId() {
        return this.creatorUserID;
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
     * @param userID ID of the current user who want to use the list
     * @return boolean success
     */
    public boolean setNewUser(Long userID) {

        UserImpl tmpUser = UserModel.getInstance().findById(userID);

        if (!this.users.contains(tmpUser)) {
            this.users.add(tmpUser);
            return true;
        }
        return false;
    }

    /**
     * Sets the movielist ID if not already set
     *
     * @param movieListID ID of this movielist
     */
    protected void setMovieListID(Long movieListID) {
        if (this.movieListID == null) {
            this.movieListID = movieListID;
        }
    }

}



