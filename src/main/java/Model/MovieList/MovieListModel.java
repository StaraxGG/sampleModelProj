package Model.MovieList;

import Model.MasterModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
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

    final Logger logger = LoggerFactory.getLogger(MovieListModel.class);

    /* ---------------------------------------- Constants ----------------------------------------------------------- */

    /* ---------------------------------------- Constructors -------------------------------------------------------- */

    private MovieListModel() {
        super();
    }

    /* ---------------------------------------- Methods ------------------------------------------------------------- */

    /**
     * returns THE instance of this MovieListModel
     *
     * @return MovieListModel
     */
    public static MovieListModel getInstance() {
        if (movieListModel == null) {
            movieListModel = new MovieListModel();
        }

        return movieListModel;
    }

    /**
     * returns a list of MovieLists
     * this method can be used to access multiple lists at the same time
     *
     * @param ids the ids of the movielists
     * @return List
     */
    public List<MovieList> findByIds(List<Long> ids) {
        logger.debug("Attempting to find MovieLists by IDs " + ids.toString());

        logger.debug("Opening entitiyManager");
        logger.error("EntitiyManager is never closed.");
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        return em.createQuery("SELECT m FROM MovieListImpl m WHERE m.movieListID in :ids", MovieList.class)
                .setParameter("ids", ids)
                .getResultList();

    }

    /* ---------------------------------------- S/Getters ----------------------------------------------------------- */

}

