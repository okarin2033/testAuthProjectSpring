package com.project.web_test.dao;

import com.project.web_test.obj.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRep extends JpaRepository<Users, Long> {
    public Users findUserById(long id);
    public Users findUsersByName(String name);
}
