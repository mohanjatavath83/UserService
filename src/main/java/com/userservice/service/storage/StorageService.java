package com.userservice.service.storage;

import com.userservice.dto.media.MediaDto;
import com.userservice.model.media.MediaModel;

public interface StorageService {

    MediaModel uploadFile(MediaDto mediaDto);

    byte[] downloadFile(String fileName);

    String deleteFile(String fileName);
}
