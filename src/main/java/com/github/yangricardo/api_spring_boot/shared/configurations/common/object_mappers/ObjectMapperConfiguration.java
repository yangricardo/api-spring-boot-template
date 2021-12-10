package com.github.yangricardo.api_spring_boot.shared.configurations.common.object_mappers;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfiguration {

  @Bean
  public ObjectMapper objectMapper() {
    return ObjectMapperBuilders.build();
  }

  @Bean ModelMapper modelMapper() {
    return ObjectMapperBuilders.buildModelMapper();
  }
}
