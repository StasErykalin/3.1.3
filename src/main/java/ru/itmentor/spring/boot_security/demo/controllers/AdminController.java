package ru.itmentor.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.models.Person;
import ru.itmentor.spring.boot_security.demo.util.PersonValidator;
import ru.itmentor.spring.boot_security.demo.services.PersonService;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final PersonValidator personValidator;
    private final PersonService personService;
    @Autowired
    public AdminController(PersonValidator personValidator, PersonService personService) {
        this.personValidator = personValidator;
        this.personService = personService;
    }

    @GetMapping("/adminpanel")
    public String getAll(Model model){
        model.addAttribute("person", personService.getAll());
        return "admin/adminpanel";
    }

    @GetMapping("/reg")
    public String addNewPerson(@ModelAttribute("person") Person person){
        return "admin/reg";
    }

    @PostMapping("/reg")
    public String performAddition(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        personValidator.validate(person, bindingResult);
        if(bindingResult.hasErrors())
            return "/admin/reg";
        personService.addNewPerson(person);
        return "admin/adminpanel";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("person", personService.fingById(id));
        return "admin/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") Person user, @PathVariable("id") int id){
        personService.update(user);
        return "redirect:admin/adminpanel";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        personService.delete(id);
        return "redirect:admin/adminpanel";

    }


}
