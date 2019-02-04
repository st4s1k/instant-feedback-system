package com.inther.configurators;

import com.inther.assets.filters.CorsFilter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
    public ModelMapper getModelMapper()
    {
        return new ModelMapper();
    }

    @Bean
    public CorsFilter getCorsFilter()
    {
        return new CorsFilter();
    }
}