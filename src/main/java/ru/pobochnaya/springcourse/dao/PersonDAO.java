package ru.pobochnaya.springcourse.dao;

import org.springframework.stereotype.Component;
import ru.pobochnaya.springcourse.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {


    public List<Person> index() {
        List<Person> people = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            String sql = "select * from Person";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Person person = new Person();
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setEmail(resultSet.getString("email"));
                person.setAge(resultSet.getInt("age"));

                people.add(person);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return people;
    }

    public Person show(int id) {
        return null;//people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save(Person person) {
        //people.add(person);
    }

    public void update(int id, Person person) {
        Person person1 = show(id);
        person1.setName(person.getName());
    }

    public void delete(int id) {
        //people.removeIf(person -> person.getId() == id);
    }
}
