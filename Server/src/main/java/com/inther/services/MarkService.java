package com.inther.services;

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

    public Optional<Mark> addMarkAttempt(Mark mark)
    {
        return markRepository
                .findMarkByPresentation_IdAndUser_Id(mark.getPresentation().getId(), mark.getUser().getId())
                .map(u -> Optional.<Mark>empty())
                .orElseGet(() -> Optional.of(markRepository.save(mark)));
    }

    public List<Mark> getMarksByPresentationId(UUID presentationId) {
        return markRepository.findMarksByPresentation_Id(presentationId);
    }
}