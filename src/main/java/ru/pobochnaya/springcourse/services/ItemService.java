package ru.pobochnaya.springcourse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pobochnaya.springcourse.models.Item;
import ru.pobochnaya.springcourse.models.Person;
import ru.pobochnaya.springcourse.repositories.ItemRepo;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepo itemRepo;

    public List<Item> findByPerson(Person person) {
        return itemRepo.findByPerson(person);
    }
}
