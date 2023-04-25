package ru.pobochnaya.springcourse.dao;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.pobochnaya.springcourse.models.Person;

import java.util.List;

@Component
@Transactional
public class PersonDAO {

    @Autowired
    private EntityManager manager;

    public void testNPlus1() {
        Session session = manager.unwrap(Session.class);

        String hql = "select p from Person p left join fetch p.items order by p.id";
        List<Person> personList = session.createQuery(hql, Person.class).getResultList();

        for(Person p : personList) {
            System.out.println(p);
        }
    }
}
