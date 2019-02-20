package com.inther.services.entity;

import com.inther.entities.Mark;
import com.inther.repositories.MarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MarkService
{
    private final MarkRepository markRepository;

    @Autowired
    public MarkService(MarkRepository markRepository)
    {
        this.markRepository = markRepository;
    }

    public List<Mark> fetchMarksByPresentationId(UUID presentationId) {
        return markRepository.findAllByPresentation_Id(presentationId);
    }

    public Optional<Mark> fetchUserMark(UUID userId, UUID presentationId) {
        return markRepository.findAllByPresentation_IdAndUser_Id(presentationId, userId);
    }
}