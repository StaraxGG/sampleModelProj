package Model;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

/**
 * An implementation of MovieModel
 * in samplemodelproject
 *
 * @author ytatar
 * @version 1.0
 * @since 2019-Feb-10
 */
public class MovieModel extends MasterModel<Long, Movie> {

    /* ---------------------------------------- Main ---------------------------------------------------------------- */

    /* ---------------------------------------- Attributes ---------------------------------------------------------- */

    /* ---------------------------------------- Constants ----------------------------------------------------------- */

    /* ---------------------------------------- Constructors -------------------------------------------------------- */

    /* ---------------------------------------- Methods ------------------------------------------------------------- */

    /**
     * add a movie to our local database of movies
     * @param movie
     * @return
     */
    boolean addMovie(Movie movie){
        throw new NotImplementedException();
    }

    /**
     * delete the given movie from our local database
     * @param id Long
     * @return boolean success
     */
    boolean deleteMovie(Long id){
        throw new NotImplementedException();
    }

    /* ---------------------------------------- S/Getters ----------------------------------------------------------- */

    /**
     * returns a movie with the specific id from our database
     * @param id
     * @return Movie
     */
    Movie getMovie(Long id){
        throw new NotImplementedException();
    }

    /**
     * returns a movie from the tmdb database
     * @param id
     * @return Movie
     */
    Movie getTmdbMovie(Integer id){
        throw new NotImplementedException();
    }

    /**
     * returns movies that match the  query
     * @param query
     * @return List
     */
    List<Movie> getMovies(String query){
        throw new NotImplementedException();
    }

    /**
     * returns movies that are similar to this one
     * @param movie
     * @return List
     */
    List<Movie> getSimilarMovies(Movie movie) {
        return null;
    }

}

