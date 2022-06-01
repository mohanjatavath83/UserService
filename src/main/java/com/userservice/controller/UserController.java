package com.userservice.controller;

import com.google.gson.Gson;
import com.userservice.dto.generic.ResponseDto;
import com.userservice.dto.user.TokenDto;
import com.userservice.dto.user.UserDto;
import com.userservice.dto.user.UserLoginDto;
import com.userservice.facade.user.UserFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController extends BaseController {

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private Gson gson;

    @PostMapping
    @ResponseBody
     @Secured("ROLE_ADMINGROUP")
    public ResponseEntity<ResponseDto> register(@RequestBody UserDto userDto, HttpServletRequest request, HttpServletResponse response) {
        log.info("user dto  = {} ", gson.toJson(userDto));
        userDto = userFacade.saveUser(userDto);
        ResponseDto<String> responseDto = new ResponseDto(Boolean.TRUE, "SUCCESS", HttpStatus.OK.value(), userDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping("/token")
    @ResponseBody
    public ResponseEntity<ResponseDto> getToken(HttpServletResponse response, @RequestBody UserLoginDto userLoginDto) {
        TokenDto tokenDto = userFacade.getUserToken(userLoginDto);
        ResponseDto<UserDto> responseDto = new ResponseDto(Boolean.TRUE, "SUCCESS", HttpStatus.OK.value(), tokenDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }


    //update passsword

    @GetMapping
    @ResponseBody
    @Secured({"ROLE_CUSTOMERGROUP"})
    public ResponseEntity<ResponseDto> getUserDetails(HttpServletRequest request, HttpServletResponse response) {
        String uid = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDto userDto = userFacade.getUserByUid(uid);
        ResponseDto<UserDto> responseDto = new ResponseDto(Boolean.TRUE, "SUCCESS", HttpStatus.OK.value(), userDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/{uid}")
    @ResponseBody
    @Secured({"ROLE_EMPLOYEEGROUP"})
    public ResponseEntity<ResponseDto> getUserByUid(HttpServletRequest request, HttpServletResponse response, @PathVariable String uid) {
        UserDto userDto = userFacade.getUserByUid(uid);
        ResponseDto<UserDto> responseDto = new ResponseDto(Boolean.TRUE, "SUCCESS", HttpStatus.OK.value(), userDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
