package ru.pobochnaya.springcourse.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.pobochnaya.springcourse.models.Person;

import java.util.List;

@Component
@Transactional
public class PersonDAO {

    @Autowired
    private SessionFactory sessionFactory;

    //@Transactional
    public List<Person> index() {
        Session session = sessionFactory.getCurrentSession();

        List<Person> fromPeople = session.createQuery("select p from Person p", Person.class).getResultList();
        return fromPeople;
    }

    @Transactional(readOnly = true)
    public Person show(int id) {
        Session session = sessionFactory.getCurrentSession();

        return session.get(Person.class, id);
        //Person person = session.createQuery("select p from Person p where p.id = :id", Person.class).setParameter("id", id).getSingleResultOrNull();
        //return person; //people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }


    public void save(Person person) {
        Session session = sessionFactory.getCurrentSession();

        session.persist(person);
    }

    public void update(int id, Person person) {
        Session session = sessionFactory.getCurrentSession();

        Person getPerson = session.createQuery("select p from Person p where p.id = :id", Person.class).setParameter("id", id).getSingleResult();

        if(person.getName() != null) getPerson.setName(person.getName());
        if(person.getAge() != null) getPerson.setAge(person.getAge());

        session.update(getPerson);
    }

    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();

        Person getPerson = session.createQuery("select p from Person p where p.id = :id", Person.class).setParameter("id", id).getSingleResult();

        session.delete(getPerson);
        //people.removeIf(person -> person.getId() == id);
    }
}
