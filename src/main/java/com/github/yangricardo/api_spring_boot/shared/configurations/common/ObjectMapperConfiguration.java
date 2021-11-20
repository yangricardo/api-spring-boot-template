package com.github.yangricardo.api_spring_boot.shared.configurations.common;

import java.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfiguration {
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper jsonMapper = new ObjectMapper();
        jsonMapper.findAndRegisterModules();
        jsonMapper.registerModule(new JavaTimeModule());
        jsonMapper.setDateFormat(new SimpleDateFormat(StdDateFormat.DATE_FORMAT_STR_ISO8601));
        return jsonMapper;
    }
}
