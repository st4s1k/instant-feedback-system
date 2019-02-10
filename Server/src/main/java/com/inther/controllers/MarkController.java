package com.inther.controllers;

import com.inther.assets.validators.RequestDataValidator;
import com.inther.dto.MarkDto;
import com.inther.entities.Mark;
import com.inther.entities.Presentation;
import com.inther.repositories.PresentationRepository;
import com.inther.repositories.UserRepository;
import com.inther.services.MarkService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@CrossOrigin(origins="*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/marks")
public class MarkController
{
    private final MarkService markService;
    private final ModelMapper modelMapper;
    private final HttpHeaders httpHeaders;
    private final UserRepository userRepository;
    private final PresentationRepository presentationRepository;

    @Autowired
    public MarkController(MarkService markService,
                          ModelMapper modelMapper,
                          HttpHeaders httpHeaders,
                          PresentationRepository presentationRepository,
                          UserRepository userRepository)
    {
        this.markService = markService;
        this.modelMapper = modelMapper;
        this.httpHeaders = httpHeaders;
        this.presentationRepository = presentationRepository;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<?> addMark(
            @Validated(value = {RequestDataValidator.AddMark.class})
            @RequestBody MarkDto markDto)
    {
        return dtoToEntity(markDto).map(mark -> markService.addMarkAttempt(mark)
                        .map(newUser -> new ResponseEntity<>(httpHeaders, HttpStatus.CREATED))
                        .orElse(new ResponseEntity<>(httpHeaders, HttpStatus.CONFLICT)))
                .orElseGet(() -> new ResponseEntity<>(httpHeaders, HttpStatus.BAD_REQUEST));
    }

    // Possibly useless, going to make mark list arrive with presentation.
//    @GetMapping(value = "/{presentationId}")
//    public ResponseEntity<?> getPresentationMarks(@PathVariable String presentationId) {
//        List<MarkDto> markDtoList = markService
//                .getMarksByPresentationId(UUID.fromString(presentationId)).stream()
//                .map(this::entityToDto)
//                .filter(Optional::isEmpty)
//                .map(Optional::get)
//                .collect(Collectors.toList());
//
//        return markDtoList.isEmpty() ?
//                new ResponseEntity<>(httpHeaders, HttpStatus.NO_CONTENT) :
//                new ResponseEntity<>(markDtoList, httpHeaders, HttpStatus.OK);
//    }

    private Optional<Mark> dtoToEntity(MarkDto markDto) {
        return presentationRepository
                .findPresentationById(UUID.fromString(markDto.getPresentationId()))
                .flatMap(presentation -> userRepository.findUserById(UUID.fromString(markDto.getUserId()))
                .map(user -> modelMapper.map(markDto, Mark.class)
                        .setPresentation(presentation)
                        .setUser(user)
                        .setValue(markDto.getValue())));
    }

//    private Optional<MarkDto> entityToDto(Mark mark) {
//        // custom logic
//        return Optional.empty();
//    }

}