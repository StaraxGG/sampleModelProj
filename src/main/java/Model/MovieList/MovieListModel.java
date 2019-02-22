package Model.MovieList;

import Model.MasterModel;
import Model.Movie.Movie;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

/**
 * An implementation of MovieListModel
 * in samplemodelproject
 *
 * @author ytatar
 * @version 1.0
 * @since 2019-Feb-10
 */
public class MovieListModel extends MasterModel<Long, MovieListImpl> {

    /* ---------------------------------------- Main ---------------------------------------------------------------- */

    /* ---------------------------------------- Attributes ---------------------------------------------------------- */
    private static MovieListModel movieListModel = null;

    /* ---------------------------------------- Constants ----------------------------------------------------------- */

    /* ---------------------------------------- Constructors -------------------------------------------------------- */

    private MovieListModel() {
        super();
    }

    /* ---------------------------------------- Methods ------------------------------------------------------------- */

    /**
     * returns THE instance of this MovieListModel
     * @return MovieListModel
     */
    public static MovieListModel getInstance(){
        if (movieListModel == null){
            movieListModel = new MovieListModel();
        }

        return movieListModel;
    }

    /**
     * returns the movieList with this specific id
     *
     * @param id
     * @return MovieList
     */
    public MovieList getMovieList(Long id) {
        return this.findById(id);
    }

    /**
     * @param ids
     * @return
     */
    public List<MovieList> getMovieLists(List<Long> ids) {
        throw new NotImplementedException();
    }

    /* ---------------------------------------- S/Getters ----------------------------------------------------------- */

}

