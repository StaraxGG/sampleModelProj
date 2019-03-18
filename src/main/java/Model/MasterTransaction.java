package Model;

import javax.persistence.EntityManager;

/**
 * An implementation of MasterTransaction
 * in samplemodelproject
 *
 * @author ytatar
 * @version 1.0
 * @since 2019-Feb-21
 */
@FunctionalInterface
public interface MasterTransaction<C> {

    C doInTransaction(EntityManager em);

}

