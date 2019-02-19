package Model;

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
public class MovieListImpl implements MovieList {

    /* ---------------------------------------- Main ---------------------------------------------------------------- */

    /* ---------------------------------------- Attributes ---------------------------------------------------------- */

    private LinkedList<MovieImpl> movieList;
    private Long movieListID;
    private String movieListName;
    private Long creatorUserID;
    //list of userIDs
    private List<Long> users;

    /* ---------------------------------------- Constants ----------------------------------------------------------- */

    /* ---------------------------------------- Constructors -------------------------------------------------------- */
    public MovieListImpl() {
        this.users = new LinkedList<Long>();
        this.movieList = new LinkedList<>();
    }

    public MovieListImpl(String movieListName, Long creatorUserID) {
        this.movieListName = movieListName;
        this.creatorUserID = creatorUserID;

        this.users = new LinkedList<Long>();
        this.users.add(creatorUserID);

        this.movieList = new LinkedList<>();
    }

    public MovieListImpl(String movieListName, Long creatorUserID, MovieImpl movie) {
        this.movieListName = movieListName;
        this.creatorUserID = creatorUserID;

        this.users = new LinkedList<Long>();
        this.users.add(creatorUserID);

        this.movieList = new LinkedList<>();
        this.movieList.add(movie);
    }


    /* ---------------------------------------- Methods ------------------------------------------------------------- */

    /**
     * Adds the received movie to the movieList if
     * it is not already on the list
     *
     * @param movie movie object
     * @return boolean success
     */
    @Override
    public boolean addMovie(MovieImpl movie) {
        if (this.movieList.contains(movie) == false) {
            this.movieList.add(movie);
            return true;
        }
        return false;
    }

    /**
     * Deletes the received movie out of the movieList if existing
     *
     * @param movie movie object
     * @return boolean success
     */
    @Override
    public boolean deleteMovie(MovieImpl movie) {
        if (this.movieList.contains(movie)) {
            this.movieList.remove(movie);
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
     * returns the movielist
     *
     * @return List<MovieImpl>
     */
    @Override
    public List<MovieImpl> getMovies() {
        return this.movieList;
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
    public List<Long> getUsers() {
        return this.users;
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
     * Adds a new user to the users list if the user
     * is not already on the list
     *
     * @param userID ID of the current user who want to use the list
     * @return boolean success
     */
    protected boolean setNewUser(Long userID) {
        if (this.users.contains(userID) == false) {
            this.users.add(userID);
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



