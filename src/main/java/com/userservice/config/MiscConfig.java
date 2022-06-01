package com.userservice.config;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MiscConfig {


    @Bean
    public Gson getGsonBuilder() {
        return new Gson();
    }

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}
