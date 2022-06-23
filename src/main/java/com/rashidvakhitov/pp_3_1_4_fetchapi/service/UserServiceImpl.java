package com.rashidvakhitov.pp_3_1_4_fetchapi.service;


import com.rashidvakhitov.pp_3_1_4_fetchapi.model.User;
import com.rashidvakhitov.pp_3_1_4_fetchapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findUserByName(String name) {
        return userRepository.getUser(name);
    }

    @Override
    public User updateUser(User user, Long id) {
        User updtuser = findById(id);
        updtuser.setName(user.getName());
        updtuser.setLastName(user.getLastName());
        updtuser.setAge(user.getAge());
        updtuser.setEmail(user.getEmail());
        updtuser.setPassword(user.getPassword());
        updtuser.setRoles(user.getRoles());
        return updtuser;
    }

}
