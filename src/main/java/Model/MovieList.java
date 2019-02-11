package Model;


=======
import java.util.List;

/**
 * An implementation of MovieList
 * in sample-model-project
 *
 * @author chris
 * @version 1.0
 * @since 2019-Feb-10
 */
>>>>>>> 9de783a7eada996aaa08d4d3a51d6da25600159a
public interface MovieList {

    /* ---------------------------------------- Attributes ---------------------------------------------------------- */

    /* ---------------------------------------- Constants ----------------------------------------------------------- */

    /* ---------------------------------------- Methods ------------------------------------------------------------- */

<<<<<<< HEAD

    /* ---------------------------------------- S/Getters ----------------------------------------------------------- */

}
=======
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

}


>>>>>>> 9de783a7eada996aaa08d4d3a51d6da25600159a
