package com.userservice.service.user;

import com.userservice.model.user.UserModel;

public interface UserService {

    UserModel getUserByUid(String uid);
    UserModel saveUser(UserModel user);
}
