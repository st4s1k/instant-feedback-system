package com.inther.configurators;

import com.inther.assets.filters.CorsFilter;
import com.inther.dto.PresentationDto;
import com.inther.entities.Participant;
import com.inther.entities.Presentation;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
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
        Converter<List<String>, List<Participant>> emailListToParticipantListConverter = context ->
                context.getSource() == null
                        ? new ArrayList<>()
                        : context.getSource().stream().map(Participant::new).collect(Collectors.toList());

        Converter<List<Participant>, List<String>> participantListToEmailListemailListConverter = context ->
                context.getSource() == null
                        ? new ArrayList<>()
                        : context.getSource().stream().map(Participant::getEmail).collect(Collectors.toList());

        ModelMapper mm = new ModelMapper();

        mm.addMappings(new PropertyMap<PresentationDto, Presentation>() {
            @Override
            protected void configure() {
                using(emailListToParticipantListConverter).map(source.getParticipants()).setParticipants(null);
            }
        });

        mm.addMappings(new PropertyMap<Presentation, PresentationDto>() {
            @Override
            protected void configure() {
                using(participantListToEmailListemailListConverter).map(source.getParticipants()).setParticipants(null);
            }
        });

        return mm;
    }
}