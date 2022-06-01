package com.userservice.service.user.impl;


import com.userservice.model.user.Role;
import com.userservice.model.user.UserModel;
import com.userservice.service.user.UserService;
import com.userservice.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;


@Service(value = "userDetailsService")
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserModel userModel = userService.getUserByUid(username);
        List<GrantedAuthority> grantedAuthorities = getRoles(userModel.getRoles());
        User user = new User(userModel.getUid(), userModel.getPassword(), grantedAuthorities);

        return user;
    }

    List<GrantedAuthority> getRoles(Set<Role> roles) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        if (Objects.nonNull(roles) && roles.size() > 0) {
            roles.stream().forEach(role -> grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleGroup())));
        }

        return grantedAuthorities;
    }

}
