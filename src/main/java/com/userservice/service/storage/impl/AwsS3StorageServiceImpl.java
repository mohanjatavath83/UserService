package com.userservice.service.storage.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.userservice.config.aws.AwsConfigDto;
import com.userservice.dto.media.MediaDto;
import com.userservice.model.media.MediaModel;
import com.userservice.service.storage.StorageService;
import com.userservice.util.StorageServiceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

@Slf4j
@Service("awsS3StorageService")
public class AwsS3StorageServiceImpl implements StorageService {


    @Autowired
    private AwsConfigDto configDto;

    @Autowired
    private AmazonS3 s3Client;


    @Override
    public MediaModel uploadFile(MediaDto mediaDto) {

        File fileObj = convertMultiPartFileToFile(mediaDto.getFile());
        String filePath = StorageServiceUtil.getFilePath(mediaDto);
        MediaModel media = new MediaModel();
        media.setFileName(mediaDto.getFileName());
        media.setUrl(filePath);
        media.setMime(StorageServiceUtil.getFileMime(mediaDto));
        StringBuilder code = new StringBuilder(mediaDto.getUploadedBy()).append("_").append(String.valueOf(new Date().getTime()));
        media.setCode(code.toString());
        s3Client.putObject(new PutObjectRequest(configDto.getBucketName(), filePath, fileObj));
        fileObj.delete();

        return media;
    }


    @Override
    public byte[] downloadFile(String filePath) {
        S3Object s3Object = s3Client.getObject(configDto.getBucketName(), filePath);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            byte[] content = IOUtils.toByteArray(inputStream);
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public String deleteFile(String fileName) {
        s3Client.deleteObject(configDto.getBucketName(), fileName);
        return fileName + " removed ...";
    }


    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            log.error("Error converting multipartFile to file", e);
        }
        return convertedFile;
    }

}
