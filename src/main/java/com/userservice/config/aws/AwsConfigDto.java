package com.userservice.config.aws;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "amazon")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AwsConfigDto {

    private String endpointUrl;
    private String accessKey;
    private String secretKey;
    private String bucketName;
    private String region;

}
