package eu.damek.dao;

import eu.damek.entity.Customer;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 * Project: caci_test
 * For:
 * Created by damekjan on 01/08/2017.
 */
public class CustomerDAO extends MainDAO<Customer> {

    /**
     * find or create customer by name
     *
     * @param customer name
     * @return Customer
     */
    public Customer getCutomerByNameOrCrearte(String customer) {
        try {
            TypedQuery<Customer> query = getEm().createNamedQuery("Customer.findByName", Customer.class);
            query.setParameter("name", customer);
            return query.getSingleResult();
        } catch (NoResultException e) {
            Customer c = new Customer();
            c.setName(customer);
            persist(c);
            return c;
        }
    }
}
