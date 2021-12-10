package com.github.yangricardo.api_spring_boot.shared.configurations.common.object_mappers;

import java.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.modelmapper.ModelMapper;

public class ObjectMapperBuilders {
  public static ObjectMapper build() {
    ObjectMapper jsonMapper = new ObjectMapper();
    jsonMapper.findAndRegisterModules();
    jsonMapper.registerModule(new JavaTimeModule());
    jsonMapper.setDateFormat(new SimpleDateFormat(StdDateFormat.DATE_FORMAT_STR_ISO8601));
    return jsonMapper;
  }

  public static ModelMapper buildModelMapper() {
    ModelMapper modelMapper = new ModelMapper();
    return modelMapper;
  }
}
