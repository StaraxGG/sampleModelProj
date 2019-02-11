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

    //TODO: Christian Warken
    /*
    Implement all the methods below
     */

    /* ---------------------------------------- Main ---------------------------------------------------------------- */

    /* ---------------------------------------- Attributes ---------------------------------------------------------- */

    private LinkedList<MovieImpl> movieList;
    private Long movieListID;
    private String movieListName;
    private Long creatorUserID;
    private List<Long> users;

    /* ---------------------------------------- Constants ----------------------------------------------------------- */

    /* ---------------------------------------- Constructors -------------------------------------------------------- */
    public MovieListImpl() {
        //TODO CHW: woher kommt UUID und wird die hier gesetzt??
    }

    public MovieListImpl(String movieListName, Long creatorUserID) {
        //TODO CHW: woher kommt UUID und wird die hier gesetzt??
    }
    /* ---------------------------------------- Methods ------------------------------------------------------------- */

    @Override
    public boolean addMovie(Movie movie) {

        return false;
    }

    /**
     * Deletes the received movie out of the movieList if existing
     *
     * @return boolean success
     * @param movie movie object
     */
    @Override
    public boolean deleteMovie(Movie movie) {
        if(movieList.contains(movie)){
            movieList.remove(movie);
            return true;
        }

        return false;
    }

    @Override
    public Long getId() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public List<Movie> getMovies() {
        return null;
    }

    @Override
    public Long getCreatorUserId() {
        return null;
    }

    @Override
    public List<Long> getUsers() {
        return null;
    }

    @Override
    public void setName(String name) {

    }

    /* ---------------------------------------- S/Getters ----------------------------------------------------------- */

}

