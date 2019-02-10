package Model;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
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
public abstract class MasterModel<T extends Serializable, C> implements Model<T, C> {

    /* ---------------------------------------- Main ---------------------------------------------------------------- */



    /* ---------------------------------------- Attributes ---------------------------------------------------------- */

    protected EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();

    protected EntityTransaction transaction = null;

    protected Class<C> entityClass;


    /* ---------------------------------------- Constants ----------------------------------------------------------- */



    /* ---------------------------------------- Constructors -------------------------------------------------------- */
    public MasterModel() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        //noinspection unchecked
        this.entityClass = (Class<C>) genericSuperclass.getActualTypeArguments()[1];
    }


    /* ---------------------------------------- Methods ------------------------------------------------------------- */

    public void persist(C entity) {
        doInTransaction(() -> entityManager.persist(entity));
    }

    public void remove(C entity) {
        doInTransaction(() -> entityManager.remove(entity));
    }

    public C findById(T id) {

        entityManager.find(this.entityClass, id);

        return null;
    }


    protected void doInTransaction(MasterTransaction masterTransaction) {

        transaction = entityManager.getTransaction();
        transaction.begin();

        masterTransaction.doInTransaction();

        transaction.commit();
        entityManager.close();

    }


    /* ---------------------------------------- S/Getters ----------------------------------------------------------- */

}

@FunctionalInterface
interface MasterTransaction {

    void doInTransaction();

}

