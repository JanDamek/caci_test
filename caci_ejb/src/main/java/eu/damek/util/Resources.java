package eu.damek.util;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.logging.Logger;

/**
 * Project: piksel
 * For:
 * Created by damekjan on 20/07/2017.
 */
public class Resources {

    /**
     * Produce EntityManager for unit name "caci". Is used for @Inject annotation and is binded with in memory DB
     */
    @Produces
    @PersistenceContext(unitName = "caci")
    private EntityManager em;

    /**
     * Produce the logger for each class where are Injected
     *
     * @param injectionPoint point for place to inject the logger
     * @return Logger for the injected class
     */
    @Produces
    public Logger produceLog(final InjectionPoint injectionPoint) {
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass()
                .getName());
    }
}
