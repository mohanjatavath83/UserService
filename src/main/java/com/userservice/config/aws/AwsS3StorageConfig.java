package com.userservice.config.aws;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class AwsS3StorageConfig {


    @Autowired
    private AwsConfigDto configDto;

    @Bean
    public AmazonS3 s3Client() {

        log.info("######################################################################");
        log.info("access key = " + configDto.getAccessKey());
        log.info("secrete key = " + configDto.getSecretKey());
        log.info("######################################################################");
        AWSCredentials credentials = new BasicAWSCredentials(configDto.getAccessKey(), configDto.getSecretKey());
        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(configDto.getRegion())
                .build();
    }
}
