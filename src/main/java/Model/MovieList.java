package Model;


import java.util.List;

/**
 * An implementation of MovieList
 * in sample-model-project
 *
 * @author chris
 * @version 1.0
 * @since 2019-Feb-10
 */

public interface MovieList {

    /* ---------------------------------------- Attributes ---------------------------------------------------------- */

    /* ---------------------------------------- Constants ----------------------------------------------------------- */

    /* ---------------------------------------- Methods ------------------------------------------------------------- */


    /**
     * adds the movie to this list
     *
     * @param movie
     * @return boolean success
     */
    boolean addMovie(Movie movie);

    /**
     * deletes the movie from the list
     *
     * @param movie
     * @return boolean success
     */
    boolean deleteMovie(Movie movie);

    /* ---------------------------------------- S/Getters ----------------------------------------------------------- */

    /**
     * returns the id of this movielist
     *
     * @return Long
     */
    Long getId();

    /**
     * get the name for this movielist
     *
     * @return String
     */
    String getName();

    /**
     * returns the movies of this movielist
     *
     * @return List
     */
    List<Movie> getMovies();

    /**
     * returns the user id that created this movielist
     * @return Long id
     */
    Long getCreatorUserId();

    /**
     * returns the user ids that are contributors to this list
     * @return List
     */
    List<Long> getUsers();

}
