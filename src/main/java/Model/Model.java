package Model;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

/**
 * An implementation of Model
 * in samplemodelproject
 *
 * @author ytatar
 * @version 1.0
 * @since 2019-Feb-10
 */
public interface Model<T extends Serializable, C> {

    @PersistenceContext
    EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("sample-persistence-unit");

    /* ---------------------------------------- Attributes ---------------------------------------------------------- */

    /* ---------------------------------------- Constants ----------------------------------------------------------- */

    /* ---------------------------------------- Methods ------------------------------------------------------------- */

    /**
     * persist the given entity to the database
     *
     * @param entity
     */
    void persist(C entity);

    /**
     * remove the given entity from the database
     *
     * @param entity
     */
    void remove(C entity);

    /**
     * find a specific entitiy by the given id
     *
     * @param id
     * @return
     */
    C findById(T id);

    /* ---------------------------------------- S/Getters ----------------------------------------------------------- */





}

