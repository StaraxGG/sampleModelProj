package Model.MovieList;


import Model.Movie.Movie;
import Model.Movie.MovieImpl;
import Model.User.Exception.UserNotFoundException;
import Model.User.User;
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
    boolean addMovie(Movie movie);

    /**
     * adds all given movies to this movieList
     * @param movies - not null, but can be empty
     * @return boolean success
     */
    boolean addMovies(List<Movie> movies);

    /**
     * deletes the movie from the list
     *
     * @param movie movie to remove
     * @return boolean success
     */
    boolean deleteMovie(Movie movie);

    /**
     * adds a user to this movieList and adds this movieList to the users movielists
     * notice, that the here given user will NOT be the creator user, although that should not be able to happen during
     * application workflow
     * @param user
     * @return true if the user (1) exists (2) was not in this movieList before (3) and is now added
     */
    boolean addUser(User user) throws UserNotFoundException;

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
    Set<MovieImpl> getMovies();

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
     * checks if this movieList contains the following movie
     * if the movie is null or not a MovieListImpl object then false will be returned
     * @return
     */
    boolean contains(Movie movie);

}
