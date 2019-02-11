package Model;

/**
 * An implementation of MovieListModel
 * in samplemodelproject
 *
 * @author ytatar
 * @version 1.0
 * @since 2019-Feb-10
 */
public class MovieListModel extends MasterModel<Long, Model.MovieList> {

    /* ---------------------------------------- Main ---------------------------------------------------------------- */

    /* ---------------------------------------- Attributes ---------------------------------------------------------- */

    /* ---------------------------------------- Constants ----------------------------------------------------------- */

    /* ---------------------------------------- Constructors -------------------------------------------------------- */

    public MovieListModel() {
        super();
    }

    /* ---------------------------------------- Methods ------------------------------------------------------------- */

    /**
     * add a new movielist to the database
     *
     * @param movieList MovieList
     */
    public void addMovieList(Model.MovieList movieList) {
        doInTransaction((em) -> {
            em.persist(movieList);
        });
    }

    /**
     * delete a specific movielist from the database
     *
     * @param id Long
     */
    public void deleteMovieList(Long id) {
        doInTransaction((em -> {
            MovieList movieList = em.find(MovieList.class, id);
            em.remove(movieList);
        }));
    }

    /**
     * updates the given entity in the database
     *
     * @param movieList MovieList
     */
    public void updateMovieList(MovieList movieList) {
        doInTransaction((em) -> {
            em.merge(movieList);
        });
    }

    public MovieList getMovieList(Long id) {
        return this.findById(id);
    }

    /* ---------------------------------------- S/Getters ----------------------------------------------------------- */

}

