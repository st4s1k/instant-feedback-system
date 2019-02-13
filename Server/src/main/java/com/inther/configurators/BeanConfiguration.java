package com.inther.configurators;

import com.inther.assets.filters.CorsFilter;
import com.inther.dto.PresentationDto;
import com.inther.entities.Participant;
import com.inther.entities.Presentation;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class BeanConfiguration
{
    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public HttpHeaders getHttpHeaders()
    {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }

    @Bean
    public CorsFilter getCorsFilter()
    {
        return new CorsFilter();
    }

    @Bean
    public ModelMapper getModelMapper()
    {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }
}