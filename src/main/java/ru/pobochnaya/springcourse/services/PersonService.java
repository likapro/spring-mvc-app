package ru.pobochnaya.springcourse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pobochnaya.springcourse.models.Mood;
import ru.pobochnaya.springcourse.models.Person;
import ru.pobochnaya.springcourse.repositories.PersonRepository;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public List<Person> findAllOrderById() {
        return personRepository.findAll(Sort.by("id"));
    }

    public Person findById(int id) {
        // TODO: 25.04.2023 Почему не подгружаются товары в модель 
        return personRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Person person) {
        person.setCreateAt(new Date());
        person.setMood(Mood.NORMAL);
        personRepository.save(person);
    }

    public void deleteById(int id) {
        personRepository.deleteById(id);
    }

    public void update(int id, Person person) {
        person.setId(id);
        personRepository.save(person);
    }
}
