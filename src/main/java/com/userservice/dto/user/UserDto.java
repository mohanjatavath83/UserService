package com.userservice.dto.user;

import com.userservice.dto.media.MediaDto;
import com.userservice.model.user.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String uid;
    private String firstName;
    private String lastName;
    private String password;
    private String mobileNumber;
    private String email;
    private Set<Role> roles;
    private List<MediaDto> documents;
}
