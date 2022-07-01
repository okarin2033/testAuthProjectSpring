package com.project.web_test.obj;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.web_test.dao.UserRep;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Users {
    @Id
    @GeneratedValue
    long id;
    private String name;
    private String password;
    public Users(String name, String password) {
        this.name = name;
        this.password = password;
    }
//check is user with name exists
    public static boolean IsUnique(String name, UserRep userRep){
        return (userRep.findUsersByName(name)==null);
    }

    //@JsonIgnore
    private int tryCount=0;
 //   @JsonIgnore
    private boolean banned=false;

}
