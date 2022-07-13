package com.rashidvakhitov.pp_3_1_4_fetchapi.controller;

import com.rashidvakhitov.pp_3_1_4_fetchapi.model.User;
import com.rashidvakhitov.pp_3_1_4_fetchapi.service.RoleService;
import com.rashidvakhitov.pp_3_1_4_fetchapi.service.UserService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("/api/v1")
@Tag(name = "Контроллеры")
public class RestAdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public RestAdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/principal")
//    @Operation(description = "Получение авторизованного юзера")
    @Hidden
    public User getPrincipal(Principal principal) {
        return userService.findUserByName(principal.getName());
    }

    @GetMapping(value = "/users")
    @Operation(description = "Получение всех юзеров")
    public List<User> getAllUsers() {
        return userService.findAll();
    }


    @PostMapping(value = "/add")
    @Operation(description = "Добавление юзера")
    public List<User> saveUser(@RequestBody User user) {
        user.setRoles(roleService.getSetRoles(user.getRoles()));
        userService.saveUser(user);
        return userService.findAll();
    }

    @GetMapping(value = "/{id}")
    @Operation(description = "Получение юзера по Id")
    public User getUser (@PathVariable ("id") Long id) {
        return userService.findById(id);
    }

    @PutMapping(value = "/")
    @Operation(description = "Обновление юзера")
    public List<User> updateUser(@RequestBody User user) {
        user.setRoles(roleService.getSetRoles(user.getRoles()));
        userService.saveUser(user);
        return userService.findAll();
    }

    @DeleteMapping(value = "/{id}")
    @Operation(description = "Удаление юзера")
    public List<User> deleteUser(@PathVariable ("id") Long id) {
        userService.deleteById(id);
        return userService.findAll();
    }
}
