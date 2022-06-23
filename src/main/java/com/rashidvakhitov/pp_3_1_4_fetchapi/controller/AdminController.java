package com.rashidvakhitov.pp_3_1_4_fetchapi.controller;


import com.rashidvakhitov.pp_3_1_4_fetchapi.model.Role;
import com.rashidvakhitov.pp_3_1_4_fetchapi.model.User;
import com.rashidvakhitov.pp_3_1_4_fetchapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    //Создание пользователя с ролью ADMIN (Username = admin, Password = admin)
    @PostConstruct
    public void init() {
        Set<Role> roles = new HashSet<>();
        roles.add(new Role("ROLE_USER"));
        roles.add(new Role("ROLE_ADMIN"));
        User admin = new User("admin", "admin", (byte) 20, "admin@mail.com", "admin");
        admin.setRoles(roles);
        userService.saveUser(admin);
    }

    @GetMapping(value = "/")
    String getAllUsers() {
        return "users";
    }

}
