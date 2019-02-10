package Model;

import info.movito.themoviedbapi.model.MovieList;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * An implementation of MovieListModel
 * in samplemodelproject
 *
 * @author ytatar
 * @version 1.0
 * @since 2019-Feb-10
 */
public class MovieListModel extends MasterModel<Long, MovieList> {

    /* ---------------------------------------- Main ---------------------------------------------------------------- */

    /* ---------------------------------------- Attributes ---------------------------------------------------------- */

    /* ---------------------------------------- Constants ----------------------------------------------------------- */

    /* ---------------------------------------- Constructors -------------------------------------------------------- */

    public MovieListModel(){
        super();
    }

    /* ---------------------------------------- Methods ------------------------------------------------------------- */

    /**
     * add a new movielist to the database
     * @param movieList MovieList
     */
    public void addMovieList(Model.MovieList movieList){
        throw new NotImplementedException();
    }

    /**
     * delete a specific movielist from the database
     * @param id Long
     */
    public void deleteMovieList(Long id){
        throw new NotImplementedException();
    }

    /**
     * updates the given entity in the database
     * @param movieList MovieList
     */
    public void updateMovieList(Model.MovieList movieList){
        doInTransaction((em) -> {
            em.merge(movieList);
        });
    }

    /* ---------------------------------------- S/Getters ----------------------------------------------------------- */

}

