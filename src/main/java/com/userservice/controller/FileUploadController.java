package com.userservice.controller;

import com.userservice.dto.generic.ResponseDto;
import com.userservice.dto.media.MediaDto;
import com.userservice.facade.media.MediaFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequestMapping("/uploads")
public class FileUploadController extends BaseController {


    @Autowired
    private MediaFacade mediaFacade;

    @PostMapping
    @ResponseBody
    @Secured("ROLE_EMPLOYEEGROUP")
    public ResponseEntity<ResponseDto> uploadFile(HttpServletRequest request, HttpServletResponse response, @RequestParam final MultipartFile file, @RequestParam final String type) {
        String employeeId = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("uploadFile by {}  ", employeeId);
        MediaDto mediaDto = new MediaDto(file, type, employeeId);
        response.setStatus(HttpStatus.OK.value());
        mediaDto = mediaFacade.upload(mediaDto);
        ResponseDto responseDto = new ResponseDto(Boolean.TRUE, "SUCCESS", HttpStatus.OK.value(), mediaDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

}
