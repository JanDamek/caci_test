package eu.damek.dao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.logging.Logger;

/**
 * Project: caci_test
 * For:
 * Created by damekjan on 21/07/2017.
 *
 * @param <T> class
 */

@Stateless
public class MainDAO<T> {

    /**
     * injected {@link EntityManager} for "piksel" data source
     */
    @Inject
    private EntityManager em;
    /**
     * injected {@link Logger} for current class
     */
    @Inject
    private Logger logger;

    /**
     * getter for entity manager
     *
     * @return EntityManager
     */
    public EntityManager getEm() {
        return em;
    }

    /**
     * merge entity
     *
     * @param toMerge entity for merge
     * @return T of merged entity
     */
    public T merge(T toMerge) {
        return em.merge(toMerge);
    }

    /**
     * persist new entity
     *
     * @param toPersist entity for new persistence
     */
    public void persist(T toPersist) {
        em.persist(toPersist);
    }

}
