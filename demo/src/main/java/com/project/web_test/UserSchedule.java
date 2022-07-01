package com.project.web_test;

import com.project.web_test.dao.UserRep;
import com.project.web_test.obj.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserSchedule {
    @Autowired
    UserRep userRep;
    @Scheduled(fixedDelay = 3600000) //1hour
    public void unbanUsers(){ //unban all users every 1 hour
        List<Users> users = userRep.findAll();
        for(Users user : users){
            user.setTryCount(0);
            user.setBanned(false);
            userRep.save(user);
        }
        System.out.println("All user unbanned");
    }
}
