package com.project.web_test.dao;

import com.project.web_test.obj.Animal;
import com.project.web_test.obj.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimalRep extends JpaRepository<Animal, Long> {
    public Animal findAnimalByName(String name);
    public List<Animal> getAnimalsByAddedBy(Users user);
    public void deleteByName(String name);
}