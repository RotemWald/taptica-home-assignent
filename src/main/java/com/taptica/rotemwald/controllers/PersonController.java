package com.taptica.rotemwald.controllers;

import com.taptica.rotemwald.HibernateConnector;
import com.taptica.rotemwald.entities.Person;
import com.taptica.rotemwald.exceptions.EmptyPersonDetailsException;
import com.taptica.rotemwald.exceptions.PersonDoesNotExistException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.web.bind.annotation.*;

/**
 * PersonController manages all person-based requests
 */
@RestController
public class PersonController {

    private SessionFactory sessionFactory = HibernateConnector.getInstance().getSessionFactory(); // Hibernate session factory

    /**
     * Get person route
     * @param id person's id
     * @return requested person object by id
     */
    @RequestMapping(value="/person/{id}", method=RequestMethod.GET)
    public Person getPerson(@PathVariable("id") int id) throws Exception {
        // Open hibernate session and begin configure transaction to work with
        Session sess = sessionFactory.openSession();
        Transaction tx = null;

        try {
            // Create new transaction, perform SELECT query and commit changes (if any)
            tx = sess.beginTransaction();
            Person person = sess.get(Person.class, id);
            if (person == null) // Person with requested id does not exist in the database
                throw new PersonDoesNotExistException("Person with requested id does not exist");
            tx.commit();

            return person;
        } catch (Exception e) {
            // Rollback changes (if any) and throw exception forward
            if (tx != null)
                tx.rollback();
            throw e;
        } finally {
            // Close hibernate session
            sess.close();
        }
    }

    /**
     * Save person route
     * @param person person object created from request details
     * @return id of new person
     */
    @RequestMapping(value="/person", method=RequestMethod.POST)
    public int savePerson(@RequestBody Person person) throws Exception {
        // Check if person details are empty, is so throw relevant exception
        if (person.getFirstName().isEmpty() && person.getLastName().isEmpty())
            throw new EmptyPersonDetailsException("Empty person details given");

        // Open hibernate session and begin configure transaction to work with
        Session sess = sessionFactory.openSession();
        Transaction tx = null;

        try {
            // Create new transaction, perform INSERT query and commit changes (if any)
            tx = sess.beginTransaction();
            sess.save(person);
            tx.commit();

            return person.getId();
        } catch (Exception e) {
            // Rollback changes (if any) and throw exception forward
            if (tx != null)
                tx.rollback();
            throw e;
        } finally {
            // Close hibernate session
            sess.close();
        }
    }
}
