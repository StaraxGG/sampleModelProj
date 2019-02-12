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
        users = new LinkedList<Long>();
        movieList = new LinkedList<>();
    }

    public MovieListImpl(String movieListName, Long creatorUserID) {
        this.movieListName = movieListName;
        this.creatorUserID = creatorUserID;

        users = new LinkedList<Long>();
        users.add(creatorUserID);

        movieList = new LinkedList<>();
    }

    public MovieListImpl(String movieListName, Long creatorUserID, MovieImpl movie) {
        this.movieListName = movieListName;
        this.creatorUserID = creatorUserID;

        users = new LinkedList<Long>();
        users.add(creatorUserID);

        movieList = new LinkedList<>();
        movieList.add(movie);
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
        if (movieList.contains(movie) == false) {
            movieList.add(movie);
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
        if (movieList.contains(movie)) {
            movieList.remove(movie);
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
        return movieListID;
    }

    /**
     * returns the Name of this movielist
     *
     * @return String
     */
    @Override
    public String getName() {
        return movieListName;
    }

    /**
     * returns the movielist
     *
     * @return List<MovieImpl>
     */
    @Override
    public List<MovieImpl> getMovies() {
        return movieList;
    }

    /**
     * returns the ID of the creator of this list
     *
     * @return Long
     */
    @Override
    public Long getCreatorUserId() {
        return creatorUserID;
    }

    /**
     * returns the userlist of this movielist
     *
     * @return List<Long>
     */
    @Override
    public List<Long> getUsers() {
        return users;
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
        if (users.contains(userID) == false) {
            users.add(userID);
            return true;
        }
        return false;
    }

}

