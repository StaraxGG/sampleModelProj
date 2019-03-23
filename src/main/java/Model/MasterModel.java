package Model;

import Tools.ConfigTools;
import info.movito.themoviedbapi.TmdbApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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



    final static Logger logger = LoggerFactory.getLogger(MasterModel.class);


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
     * persists the given entity in to the database and returns it afterwards for later use
     * @param entity
     * @return
     */
    public C persist(C entity) {
        logger.debug("Function persist was called with argument " + entity.toString());
        logger.debug("EntityManager created");
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        logger.debug("Querying transaction from EntitiyManager");
        EntityTransaction transaction = em.getTransaction();

        C result = null;

        try {

            // start the transaction
            logger.debug("Starting transaction");
            transaction.begin();

            logger.debug("Trying to persist entity " + entity.toString());
            em.persist(entity);

            // commit the thing
            logger.debug("Trying to commit transaction " + transaction.toString());
            transaction.commit();

            // find it again
            result = entity;

        } catch (PersistenceException pe) {
            logger.error(pe.getMessage(), pe);
            if (transaction != null && transaction.isActive()) {
                logger.warn("Transaction will be back");
                transaction.rollback();
            }else{
                logger.error("Transaction could not be rollbacked!!! Transaction: "
                        + transaction.toString());
            }

        } finally {
            logger.debug("Closing EntitiyManager");
            em.close();
        }

        return result;
    }

    /**
     * removes the given entity from the database
     *
     * @param entity
     * @return true if successful
     */
    public boolean remove(C entity) {
        logger.debug("Function remove was called with argument " + entity.toString());

        logger.debug("Creating EntityManager");
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        logger.debug("Querying transaction from EntityManager");
        EntityTransaction transaction = em.getTransaction();

        try {

            // start the transaction
            logger.debug("Starting Transaction");
            transaction.begin();

            logger.debug("Trying to remove entity " + entity.toString());
            em.remove(em.contains(entity) ? entity : em.merge(entity));

            // commit the thing
            logger.debug("Trying to commit transaction " + transaction.toString());
            transaction.commit();


        } catch (PersistenceException pe) {
            logger.error(pe.getMessage(), pe);
            if (transaction != null && transaction.isActive()) {
                logger.warn("Transaction will be rolled back");
                transaction.rollback();
            }else{
                logger.error("Transaction could not be rollbacked!!! " +
                        "Transaction: " + transaction.toString());
            }

            return false;

        } finally {
            logger.debug("Closing EntityManager");
            em.close();
        }

        return true;
    }

    /**
     * updates the given entitiy in the database. if the entity does not exist, it will be created. please do not
     * use this method over {@link #persist(Object)}
     * @param entity
     * @return C the updated object
     */
    public C update(C entity) {
        logger.debug("Update was called with argument " + entity.toString());
        return doInTransaction((em) -> em.merge(entity));
    }

    /**
     * finds the given entity in the database with a given id
     *
     * @param id T
     * @return C
     */
    public C findById(T id) {
        logger.debug("findById was called with argument " + id.toString());
        logger.debug("Creting EntityManager");
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        logger.debug("Querying transaction from EntityManager");
        EntityTransaction transaction = em.getTransaction();

        C result = null;

        try {

            // start the transaction
            logger.debug("Starting Transaction");
            transaction.begin();

            logger.debug("Querying EntityManager for entityClass " + this.entityClass.getSimpleName()
                    + " with id " + id.toString());
            result = em.find(this.entityClass, id);

            // commit the thing
            logger.debug("Commiting Transaction " + transaction.toString());
            transaction.commit();

        } catch (PersistenceException pe) {
            logger.error(pe.getMessage(), pe);
            if (transaction != null && transaction.isActive()) {
                logger.debug("Transaction will be rolled back");
                transaction.rollback();
            }else{
                logger.error("Transaction could not be rolled back!!! " +
                        "Transaction: " + transaction.toString());
            }

        } finally {
            logger.debug("Closing EntityManager");
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
    private C doInTransaction(MasterTransaction<C> masterTransaction) {
        logger.debug("Function doInTransaction was called");

        logger.debug("Starting Transaction");
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        logger.debug("Querying transaction from EntityManager");
        EntityTransaction transaction = em.getTransaction();

        C result = null;

        try {

            // start the transaction
            logger.debug("Starting Transaction");
            transaction.begin();

            // do the thing
            logger.debug("Trying to do masterTransaction.doInTransaction");
            result = masterTransaction.doInTransaction(em);

            // commit the thing
            logger.debug("Commiting Transaction " + transaction.toString());
            transaction.commit();

        } catch (PersistenceException pe) {
            logger.error(pe.getMessage(), pe);
            if (transaction != null && transaction.isActive()) {
                logger.debug("Transaction will be rolled back");
                transaction.rollback();
            }else{
            logger.error("Transaction could not be rolled back!!! " +
                    "Transaction: " + transaction.toString());
            }

        } finally {
            logger.debug("Closing EntityManager");
            em.close();
        }

        return result;

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


