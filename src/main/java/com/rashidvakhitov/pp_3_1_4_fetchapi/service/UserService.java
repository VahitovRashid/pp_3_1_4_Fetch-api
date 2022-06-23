package com.rashidvakhitov.pp_3_1_4_fetchapi.service;


import com.rashidvakhitov.pp_3_1_4_fetchapi.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> findAll();

    User findById(Long id);

    User saveUser(User user);

    void deleteById(Long id);

    User findUserByName(String name);

    User updateUser(User user, Long id);
}
