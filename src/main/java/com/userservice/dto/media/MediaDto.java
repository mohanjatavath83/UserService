package com.userservice.dto.media;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MediaDto implements Serializable {
    private String code;
    private String fileName;
    private String mediaType;
    private MultipartFile file;
    private byte[] fileBytes;
    private String url;
    private String mime;
    private String uploadedBy;
    private String folderPath;


    public MediaDto(MultipartFile file, String mediaType, String uploadedBy) {
        this.file = file;
        this.mediaType = mediaType;
        this.uploadedBy = uploadedBy;
    }
}
