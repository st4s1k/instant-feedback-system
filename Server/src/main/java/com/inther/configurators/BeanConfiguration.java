package com.inther.configurators;

import com.inther.assets.filters.CorsFilter;
import com.inther.dto.PresentationDto;
import com.inther.entities.Presentation;
import com.inther.entities.User;
import com.inther.repositories.UserRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class BeanConfiguration
{
    private final UserRepository userRepository;

    public BeanConfiguration(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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

        Converter<String, User> emailToUserConverter = context ->
                userRepository.findUserByEmail(context.getSource()).orElse(null);

        modelMapper.addMappings(new PropertyMap<Presentation, PresentationDto>() {
            @Override
            protected void configure() {
                map(source.getUser().getEmail()).setEmail(null);
            }
        });

        modelMapper.addMappings(new PropertyMap<PresentationDto, Presentation>() {
            @Override
            protected void configure() {
                using(emailToUserConverter).map(source.getEmail()).setUser(null);
            }
        });
        return modelMapper;
    }
}