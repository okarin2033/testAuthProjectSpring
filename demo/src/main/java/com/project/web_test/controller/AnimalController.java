package com.project.web_test.controller;

import com.project.web_test.customerror.AccessError;
import com.project.web_test.customerror.NotUniqueNameError;
import com.project.web_test.dao.AnimalRep;
import com.project.web_test.dao.UserRep;
import com.project.web_test.dto.AnimalDto;
import com.project.web_test.obj.Animal;
import com.project.web_test.obj.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class AnimalController {
    @Autowired
    AnimalRep animalRep;
    @Autowired
    UserRep userRep;

    @PostMapping("/animal/new")
    public Object newAnimal(@RequestBody AnimalDto animal, HttpServletRequest request) {
        if ((boolean) request.getSession().getAttribute("Access")) {
            Users user = (Users) request.getSession().getAttribute("User");
            if (!Animal.IsUnique(animal.getName(), animalRep))
                return null;
            Animal newAnimal = new Animal(animal.getName(), animal.getBorn(), animal.getSex(), user);
            animalRep.save(newAnimal);
            return newAnimal;
        } else throw new AccessError(); //auth error
    }

    @DeleteMapping("/animal/delete")
    @Transactional
    @ResponseStatus(code = HttpStatus.OK, reason = "Animal deleted successfully")
    public void deleteAnimal(String name, HttpServletRequest request) {
        if ((boolean) request.getSession().getAttribute("Access")) {
            animalRep.deleteByName(name);
        } else throw new AccessError(); //auth error
    }

    @PutMapping("/animal/upd/{id}")
    public Object updAnimal(@RequestBody AnimalDto animal, HttpServletRequest request, @PathVariable long id) {
        if ((boolean) request.getSession().getAttribute("Access")) {
            Animal red = animalRep.findAnimalByName(animal.getName());
            if (Animal.IsUnique(animal.getName(), animalRep)) {
                red.setName(animal.getName());
                red.setDateBorn(animal.getBorn());
                red.setSex(animal.getSex());
                animalRep.save(red);
                return red;
            } else throw new NotUniqueNameError(); //if name not unique
        } else throw new AccessError(); //auth error
    }


    @GetMapping("/animal")
    public Object getAnimalList(HttpServletRequest request){
        if ((boolean) request.getSession().getAttribute("Access")) {
            Users user = (Users) request.getSession().getAttribute("User");
            return animalRep.getAnimalsByAddedBy(user);
        }
        else throw new AccessError(); //auth error
    }
    @GetMapping("/animal/{id}")
    public Object getAnimal(@PathVariable long id, HttpServletRequest request){
        if ((boolean) request.getSession().getAttribute("Access")) {
            return animalRep.findById(id).get();
        }
        else throw new AccessError(); //auth error
    }

}
