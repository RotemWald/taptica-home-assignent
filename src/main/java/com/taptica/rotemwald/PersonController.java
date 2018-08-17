package com.taptica.rotemwald;

import org.hibernate.Session;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonController {

    private Session session = HibernateConnector.getInstance().getSessionFactory().getCurrentSession();

    @RequestMapping(value="/person/{id}", method=RequestMethod.GET)
    public Person getPerson(@PathVariable("id") int id) {
        session.beginTransaction();
        Person person = session.get(Person.class, id);
        session.getTransaction().commit();

        return person;
    }

    @RequestMapping(value="/person", method=RequestMethod.POST)
    public int savePerson(@RequestBody Person person) {
        session.beginTransaction();
        session.save(person);
        session.getTransaction().commit();

        return person.getId();
    }
}
