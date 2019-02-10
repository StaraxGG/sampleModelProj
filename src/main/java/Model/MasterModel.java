package Model;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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

    @PersistenceContext
    EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("sample-persistence-unit");

        protected Class<C> entityClass;


    /* ---------------------------------------- Constants ----------------------------------------------------------- */



    /* ---------------------------------------- Constructors -------------------------------------------------------- */
    public MasterModel() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        //noinspection unchecked
        this.entityClass = (Class<C>) genericSuperclass.getActualTypeArguments()[1];
    }


    /* ---------------------------------------- Methods ------------------------------------------------------------- */

    public static boolean internetConnectionEstablished(){
        throw new NotImplementedException();
    }

    /* ---------------------------------------- CRUD - Methods ------------------------------------------------------ */

    /**
     * persists the given entity in the database
     * @param entity
     */
    public void persist(C entity) {
        doInTransaction((em) -> em.persist(entity));
    }

    /**
     * removes the given entity from the database
     * @param entity
     */
    public void remove(C entity) {
        doInTransaction((em) -> em.remove(entity));
    }

    /**
     * finds the given entity in the database with a given id
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
            transaction.rollback();

        } finally {
            em.flush();
        }

        return result;
    }


    /**
     * runs a given operation (implementation of {@link MasterTransaction} as Functional Interface) in a new transaction
     * with rollback-Managament and stuff
     * @param masterTransaction {@link MasterTransaction} implementation
     */
    protected void doInTransaction(MasterTransaction masterTransaction) {

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
            transaction.rollback();

        } finally {
            em.flush();
        }

    }


    /* ---------------------------------------- S/Getters ----------------------------------------------------------- */

}

@FunctionalInterface
interface MasterTransaction {

    void doInTransaction(EntityManager em);

}


