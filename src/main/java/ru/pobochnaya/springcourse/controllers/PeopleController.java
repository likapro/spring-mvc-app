package ru.pobochnaya.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.pobochnaya.springcourse.dao.PersonDAO;
import ru.pobochnaya.springcourse.models.Person;
import ru.pobochnaya.springcourse.repositories.ItemRepo;
import ru.pobochnaya.springcourse.services.PersonService;

@Controller
@RequestMapping("/people")
public class PeopleController {
    @Autowired
    private PersonDAO personDAO;
    @Autowired
    private PersonService personService;
    @Autowired
    private ItemRepo itemRepo;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", personService.findAllOrderById());
        //personDAO.testNPlus1();
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personService.findById(id));
        model.addAttribute("items", itemRepo.findByPerson(personService.findById(id)));
        return "people/person";
    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("person", new Person());
        return "people/new";
    }

    @PostMapping
    public String create(@ModelAttribute("person") Person person) {
        personService.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personService.findById(id));
        return "people/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("person") Person person, @PathVariable("id") int id) {
        personService.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personService.deleteById(id);
        return "redirect:/people";
    }
}
