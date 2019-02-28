package Model.User;

import Model.MovieList.MovieList;
import Model.MovieList.MovieListImpl;

import java.util.List;

/**
 * An implementation of User
 * in samplemodelproject
 *
 * @author ytatar
 * @version 1.0
 * @since 2019-Feb-11
 */
public interface User {

    /* ---------------------------------------- Attributes ---------------------------------------------------------- */
    /*
        Long userId;
        String username;
        Integer passwordhash;
        List<MovieList> movieLists
     */

    /* ---------------------------------------- Constants ----------------------------------------------------------- */

    /* ---------------------------------------- Methods ------------------------------------------------------------- */

    /**
     * adds a new list to this users movielists and adds this user as creator to the movieList
     *
     * @param movieList
     */
    boolean addMovieList(MovieList movieList);

    /**
     * goes through all movielists that this user has and deletes the one that is equal to the one that is passed as a
     * parameter here.
     * before that, it checks if the creator of this movielist is the same as this user, because only a creator can
     * delete his list
     * @param movieList
     * @return
     */
    boolean deleteMovieList(MovieList movieList);



    /* ---------------------------------------- S/Getters ----------------------------------------------------------- */

    /**
     * returns this users username
     * it should be ensured that this value is unique (e.g. email-adress) so we can use it as authentication method
     *
     * @return String
     */
    String getUsername();

    /**
     * returns the ids of all movieLists where this user is listed, either as creator or contributor
     *
     * @return List
     */
    List<MovieListImpl> getMovieLists();

    /**
     * returns the hash for this username that was stored in the database
     *
     * @return Integer hash
     */
    Integer getPasswordHash();


}

