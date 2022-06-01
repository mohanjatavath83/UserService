package com.userservice.facade.media.impl;

import com.userservice.dto.media.MediaDto;
import com.userservice.facade.media.MediaFacade;
import com.userservice.model.media.MediaModel;
import com.userservice.model.user.UserModel;
import com.userservice.service.storage.StorageService;
import com.userservice.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class MediaFacadeImpl implements MediaFacade {

    @Resource(name = "awsS3StorageService")
    private StorageService awsS3StorageService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    public MediaDto upload(MediaDto mediaDto) {
        UserModel user = userService.getUserByUid(mediaDto.getUploadedBy());
        MediaModel media = awsS3StorageService.uploadFile(mediaDto);
        user.getDocuments().add(media);
        userService.saveUser(user);
        mediaDto = modelMapper.map(media, MediaDto.class);

        return mediaDto;
    }
}
