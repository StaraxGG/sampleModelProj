package Model.MovieList;


import Model.Movie.Movie;
import Model.Movie.MovieImpl;
import Model.User.UserImpl;

import java.util.List;
import java.util.Set;

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
     * @param movie movie to add
     * @return boolean success
     */
    boolean addMovie(MovieImpl movie);

    /**
     * deletes the movie from the list
     *
     * @param movie movie to remove
     * @return boolean success
     */
    boolean deleteMovie(MovieImpl movie);

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
     * returns the tmdbMovies of this movielist
     *
     * @return List
     */
    List<MovieImpl> getMovies();

    /**
     * returns the user id that created this movielist
     *
     * @return Long id
     */
    String getCreatorUserName();

    /**
     * returns the user ids that are contributors to this list
     *
     * @return List
     */
    Set<UserImpl> getUsers();

    /**
     * checks if this movieList contains the following moviee
     * @return
     */
    boolean contains(Movie movie);

}
