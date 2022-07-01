package com.project.web_test.obj;

import com.project.web_test.dao.AnimalRep;
import com.project.web_test.dao.UserRep;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Animal {
    @Id
    @GeneratedValue
    long id;
    private String name;
    private String sex;
    private String dateBorn;
    @ManyToOne
    private Users addedBy;
    //check is animal with name exists
    public static boolean IsUnique(String name, AnimalRep aniRep){
        return (aniRep.findAnimalByName(name)==null);
    }

    public Animal(String name, String sex, String dateBorn, Users addedBy) {
        this.name = name;
        this.dateBorn = dateBorn;
        this.sex = sex;
        this.addedBy = addedBy;
    }
}
