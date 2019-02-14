package com.inther.controllers;

import com.inther.assets.validators.RequestDataValidator;
import com.inther.dto.MarkDto;
import com.inther.entities.Mark;
import com.inther.repositories.PresentationRepository;
import com.inther.repositories.UserRepository;
import com.inther.services.entity.MarkService;
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

@CrossOrigin(origins="*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/marks")
public class MarkController
{
    private final MarkService markService;
    private final ModelMapper modelMapper;
    private final HttpHeaders httpHeaders;
    private final PresentationRepository presentationRepository;
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<?> addMark(
            @Validated(value = {RequestDataValidator.AddMark.class})
            @RequestBody MarkDto markDto)
    {
        // Ugh.. big clump of methods. Source of untraceable trouble. Should fix it someday...)
        Mark mark = modelMapper.map(markDto, Mark.class);
        return presentationRepository.findPresentationById(mark.getPresentation().getId())
                .map(presentation -> userRepository.findUserById(mark.getUser().getId()))
                .filter(Optional::isPresent).map(Optional::get)
                .map(user -> markService.newMark(mark)
                        .map(newMark -> new ResponseEntity<>("User " + user.getEmail() +
                                " successfully rated presentationId, ID: " + mark.getPresentationId(),
                                httpHeaders, HttpStatus.CREATED))
                        .orElse(new ResponseEntity<>("User has already rated this presentationId!",
                                httpHeaders, HttpStatus.CONFLICT)))
                .orElseGet(() -> new ResponseEntity<>("No such presentationId OR userEmail.",
                        httpHeaders, HttpStatus.BAD_REQUEST));
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
            return mark.isEmpty()
                    ? new ResponseEntity<>(mark, httpHeaders, HttpStatus.OK)
                    : new ResponseEntity<>(httpHeaders, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("User not found!", httpHeaders, HttpStatus.NOT_FOUND);
        }
    }

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
}