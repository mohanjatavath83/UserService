package com.userservice.util;

import com.userservice.constants.StorageConstants;
import com.userservice.dto.media.MediaDto;
import com.userservice.exception.InvalidRequestException;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.Arrays;
import java.util.Date;


@Slf4j
public class StorageServiceUtil {

    public static String getFilePath(MediaDto mediaDto) {
        Date currentTime = new Date();
        String originalFilename = mediaDto.getFile().getOriginalFilename();

        if (!originalFilename.contains(".")) {
            String contentType = mediaDto.getFile().getContentType();
            String fileExtension = contentType.substring(contentType.lastIndexOf("/") + 1);
            originalFilename = originalFilename + "." + fileExtension;
        }
        StringBuilder builder = new StringBuilder(getFolder(mediaDto))
                .append(String.valueOf(currentTime.getTime()))
                .append("_")
                .append(originalFilename);

        return builder.toString();
    }

    public static String getFileMime(MediaDto mediaDto) {
        String originalFilename = mediaDto.getFile().getOriginalFilename();
        String fileExtension = "";
        if (originalFilename.contains(".")) {
            fileExtension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        } else {
            String contentType = mediaDto.getFile().getContentType();
            fileExtension = contentType.substring(contentType.lastIndexOf("/") + 1);

        }

        mediaDto.setMime(fileExtension);

        return fileExtension;
    }

    public static String getFolder(MediaDto mediaDto) {
        StringBuilder builder = new StringBuilder(File.separator);
        builder.append(mediaDto.getUploadedBy()).append(File.separator);
        return builder.toString();
    }


}
