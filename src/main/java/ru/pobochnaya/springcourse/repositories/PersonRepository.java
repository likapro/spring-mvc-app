package ru.pobochnaya.springcourse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pobochnaya.springcourse.models.Person;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

}
