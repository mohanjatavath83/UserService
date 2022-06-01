package com.userservice.facade.user.impl;

import com.userservice.dto.user.TokenDto;
import com.userservice.dto.user.UserDto;
import com.userservice.dto.user.UserLoginDto;
import com.userservice.facade.user.UserFacade;
import com.userservice.model.user.UserModel;
import com.userservice.service.user.UserService;
import com.userservice.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class UserFacadeImpl implements UserFacade {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;


    @Override
    public UserDto saveUser(UserDto userDto) {
        UserModel user = modelMapper.map(userDto, UserModel.class);
        user = userService.saveUser(user);
        userDto = modelMapper.map(user, UserDto.class);

        return userDto;
    }

    @Override
    public UserDto getUserByUid(String uid) {
        UserModel user = userService.getUserByUid(uid);
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        UserDto userDto = modelMapper.map(user, UserDto.class);

        return userDto;
    }


    @Override
    public TokenDto getUserToken(UserLoginDto loginDto) {

        TokenDto tokenDto = new TokenDto();
        try {
            log.info("token requested for user {} ", loginDto.getUserName());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUserName(), loginDto.getPassword()));
        } catch (DisabledException ex) {
            ex.printStackTrace();
            throw new DisabledException("USER_DISABLED");
        } catch (BadCredentialsException ex) {
            ex.printStackTrace();
            throw new BadCredentialsException("INVALID_CREDENTIALS");
        }

        String token = jwtTokenUtil.generateToken(loginDto.getUserName());
        long expiresIn = jwtTokenUtil.getExpiryDate(token).getTime();
        tokenDto.setAccessToken(token);
        tokenDto.setExpiresIn(expiresIn);
        tokenDto.setTokenType("JWT");
        return tokenDto;
    }


}
