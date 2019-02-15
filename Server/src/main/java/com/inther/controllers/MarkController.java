package com.inther.controllers;

import com.inther.assets.validators.RequestDataValidator;
import com.inther.services.mappers.MarkMapper;
import com.inther.dto.MarkDto;
import com.inther.entities.Mark;
import com.inther.repositories.MarkRepository;
import com.inther.repositories.PresentationRepository;
import com.inther.repositories.UserRepository;
import com.inther.services.entity.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins="*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/marks")
public class MarkController
{
    private final MarkService markService;
    private final MarkMapper markMapper;
    private final HttpHeaders httpHeaders;
    private final PresentationRepository presentationRepository;
    private final UserRepository userRepository;
    private final MarkRepository markRepository;

    @PostMapping
    public ResponseEntity<?> addMark(
            @Validated(value = {RequestDataValidator.AddMark.class})
            @RequestBody MarkDto markDto)
    {
        Mark mark = markMapper.toEntity(markDto);

        if (!presentationRepository.findPresentationById(mark.getPresentation().getId()).isPresent()) {
            return new ResponseEntity<>("No such presentation!", httpHeaders, HttpStatus.BAD_REQUEST);
        } else if (!userRepository.findUserById(mark.getUser().getId()).isPresent()){
            return new ResponseEntity<>("No such user!", httpHeaders, HttpStatus.BAD_REQUEST);
        } else if (markRepository.findMarkByPresentation_IdAndUser_Id(
                mark.getPresentation().getId(),
                mark.getUser().getId()).isPresent()) {
            return new ResponseEntity<>("User has already rated this presentationId!",
                    httpHeaders, HttpStatus.CONFLICT);
        } else {
            markService.newMark(mark);
            return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
        }
    }

    @GetMapping(params = "presentationId")
    public ResponseEntity<?> getMarksByPresentation(
            @RequestParam(value = "presentationId") String id)
    {
        if (presentationRepository.findPresentationById(UUID.fromString(id)).isPresent()) {
            List<Mark> markList = markService.fetchMarksByPresentationId(UUID.fromString(id));
            return new ResponseEntity<>(markList, httpHeaders, markList.isEmpty()
                    ? HttpStatus.NO_CONTENT
                    : HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Presentation not found!", httpHeaders, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(params = "userId")
    public ResponseEntity<?> getUserMark(
            @RequestParam(value = "userId") String id)
    {
        if (userRepository.findUserById(UUID.fromString(id)).isPresent()) {
            Optional<Mark> mark = markService.fetchUserMark(UUID.fromString(id));
            return !mark.isPresent()
                    ? new ResponseEntity<>(httpHeaders, HttpStatus.NO_CONTENT)
                    : new ResponseEntity<>(markMapper.toDto(mark.get()), httpHeaders, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found!", httpHeaders, HttpStatus.NOT_FOUND);
        }
    }

    @Autowired
    public MarkController(MarkService markService,
                          MarkMapper markMapper,
                          HttpHeaders httpHeaders,
                          PresentationRepository presentationRepository,
                          UserRepository userRepository,
                          MarkRepository markRepository)
    {
        this.markService = markService;
        this.markMapper = markMapper;
        this.httpHeaders = httpHeaders;
        this.presentationRepository = presentationRepository;
        this.userRepository = userRepository;
        this.markRepository = markRepository;
    }
}