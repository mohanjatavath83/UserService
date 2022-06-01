package com.userservice.service.user.impl;

import com.userservice.model.user.UserModel;
import com.userservice.repository.UserRepository;
import com.userservice.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserModel getUserByUid(String uid) {

        UserModel user = userRepository.findById(uid).orElseThrow(() -> new UsernameNotFoundException("user not found with uid ".concat(uid)));

        return user;
    }

    @Override
    public UserModel saveUser(UserModel user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);

        return user;
    }
}
