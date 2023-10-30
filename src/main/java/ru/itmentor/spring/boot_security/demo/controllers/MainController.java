package ru.itmentor.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itmentor.spring.boot_security.demo.services.PersonService;

@Controller
@RequestMapping("/")
public class MainController {

    private final PersonService personService;

    public MainController(PersonService personService) {
        this.personService = personService;
    }


    @GetMapping
    public String getAll(Model model){
        model.addAttribute("person", personService.getAll());
        return "/userlist";
    }

    @GetMapping("/{id}")
    public String showById(@PathVariable("id") int id, Model model){
        model.addAttribute("person", personService.fingById(id));
        return "user";
    }





}
