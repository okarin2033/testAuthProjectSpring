package com.project.web_test.controller;

import com.project.web_test.customerror.AuthError;
import com.project.web_test.customerror.BannedError;
import com.project.web_test.dao.UserRep;
import com.project.web_test.dto.UsersDto;
import com.project.web_test.obj.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class UserController {


    @Autowired
    UserRep userRep;

    @GetMapping("/user")
    public List<Users> getUsers(){
        return userRep.findAll();
    }

    @PostMapping("user/new")
    public Users newUser(@RequestBody UsersDto user, HttpServletRequest request){
        if (!Users.IsUnique(user.getName(), userRep))
            return null;
        Users newUser = new Users(
                user.getName(),
                user.getPassword());
        userRep.save(newUser);
        request.getSession().setAttribute("Access", true);
        request.getSession().setAttribute("User", newUser);
        return newUser;
    }

    @PostMapping("user/login")
    @ResponseStatus(code = HttpStatus.OK, reason = "Login success")
    public void login(@RequestBody UsersDto usersDto, HttpServletRequest request){
        String name= usersDto.getName();
        String password= usersDto.getPassword();
    //    System.out.println(name+" "+password);
        if(!userRep.findUsersByName(name).isBanned()) {
            if (userRep.findUsersByName(name).getPassword().equals(password)) {
            //    System.out.println("MATCH~<3");
                request.getSession().setAttribute("Access", true);
                request.getSession().setAttribute("User", userRep.findUsersByName(name));
                Users user = userRep.findUsersByName(name);
                user.setTryCount(0);
                userRep.save(user);
            } else {
            //    System.out.println("Wrong auth error - "+ name);
                Users temp = userRep.findUsersByName(name);
                temp.setTryCount(temp.getTryCount()+1);
                if (temp.getTryCount()==10)
                    temp.setBanned(true);
                userRep.save(temp);
                throw new AuthError(); //auth error
            }
        } else throw new BannedError(); //error too many tries

    }
    @PostMapping("user/logout")
    @ResponseStatus(code = HttpStatus.OK, reason = "Logout success")
    public void logout(HttpServletRequest request){
        request.getSession().setAttribute("Access", false);
        request.getSession().setAttribute("User", null);
    }

    @GetMapping("user/status")
    public Object status(HttpServletRequest request){
        return request.getSession().getAttribute("Access");
    }

    @GetMapping("user/name")
    public Object checkName(String name){
        return Users.IsUnique(name, userRep);
    }
}
