package com.userservice.facade.user;

import com.userservice.dto.user.TokenDto;
import com.userservice.dto.user.UserDto;
import com.userservice.dto.user.UserLoginDto;

public interface UserFacade {

    TokenDto getUserToken(UserLoginDto userLoginDto);

    UserDto saveUser(UserDto userDto);

    UserDto getUserByUid(String uid);
}
