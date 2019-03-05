package Model;

import Tools.ConfigTools;
import info.movito.themoviedbapi.TmdbApi;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

/**
 * An implementation of MasterModel
 * in samplemodelproject
 *
 * @author ytatar
 * @version 1.0
 * @since 2019-Feb-10
 */
public abstract class MasterModel<T extends Serializable, C> {

    /* ---------------------------------------- Main ---------------------------------------------------------------- */

    /* ---------------------------------------- Attributes ---------------------------------------------------------- */

    /**
     * holds the class that will be persisted
     */
    protected Class<C> entityClass;
    /**
     * the language parameter that can be passed along every query
     */
    protected static String tmdbLang;
    @PersistenceContext
    protected static EntityManagerFactory ENTITY_MANAGER_FACTORY =
            Persistence.createEntityManagerFactory("sample-persistence-unit");
    private static TmdbApi tmdbApi = null;


    /* ---------------------------------------- Constants ----------------------------------------------------------- */

    /* ---------------------------------------- Constructors -------------------------------------------------------- */
    public MasterModel() {

        // load the class name for this entity
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        //noinspection unchecked
        this.entityClass = (Class<C>) genericSuperclass.getActualTypeArguments()[1];

        // load the language
        tmdbLang = ConfigTools.getVal("tmdb.language");
    }


    /* ---------------------------------------- Methods ------------------------------------------------------------- */

    /**
     * returns an instance of the tmdb api
     *
     * @return {@link TmdbApi}
     */
    protected TmdbApi getTmdbApi() {

        if (tmdbApi == null) {
            tmdbApi = new TmdbApi(ConfigTools.getVal("tmdb.api_key"));
        }

        return tmdbApi;
    }

    /* ---------------------------------------- CRUD - Methods ------------------------------------------------------ */

    /**
     * persists the given entity in the database
     *
     * @param entity
     * @return boolean true if successful
     */
    public boolean persist(C entity) {
        return doInTransaction((em) -> em.persist(entity));
    }

    /**
     * removes the given entity from the database
     *
     * @param entity
     * @return true if successful
     */
    public boolean remove(C entity) {
        return doInTransaction((em) -> em.remove(em.contains(entity) ? entity : em.merge(entity)));
    }

    /**
     * finds the given entity in the database with a given id
     *
     * @param id T
     * @return C
     */
    public C findById(T id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        C result = null;

        try {

            // start the transaction
            transaction.begin();

            result = em.find(this.entityClass, id);

            // commit the thing
            transaction.commit();

        } catch (PersistenceException pe) {
            System.err.println(pe.getMessage());
            if (transaction != null && transaction.isActive())
                transaction.rollback();

        } finally {
            em.close();
        }

        return result;
    }


    /**
     * runs a given operation (implementation of {@link MasterTransaction} as Functional Interface) in a new transaction
     * with rollback-Managament and stuff
     *
     * @param masterTransaction {@link MasterTransaction} implementation
     * @return boolean returns true if transaction was successful
     */
    private boolean doInTransaction(MasterTransaction masterTransaction) {

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        try {

            // start the transaction
            transaction.begin();

            // do the thing
            masterTransaction.doInTransaction(em);

            // commit the thing
            transaction.commit();

        } catch (PersistenceException pe) {
            System.err.println(pe.getMessage());
            if (transaction != null && transaction.isActive())
                transaction.rollback();

            return false;

        } finally {
            em.close();
        }

        return true;

    }

    /**
     * this method should be called at the end of the application to cleanup everything
     */
    public static void exit() {

        // close the EMF which closes the database connection pool
        ENTITY_MANAGER_FACTORY.close();
    }


    /* ---------------------------------------- S/Getters ----------------------------------------------------------- */

}


