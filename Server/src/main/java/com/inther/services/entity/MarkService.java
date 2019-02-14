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

    public Optional<Mark> newMark(Mark mark)
    {
        return markRepository
                .findMarkByPresentation_IdAndUser_Id(mark.getPresentation().getId(), mark.getUser().getId())
                .map(u -> Optional.<Mark>empty())
                .orElseGet(() -> Optional.of(markRepository.save(mark)));
    }

    public List<Mark> fetchMarksByPresentationId(UUID presentationId) {
        return markRepository.findMarksByPresentation_Id(presentationId);
    }

    public Optional<Mark> fetchUserMark(UUID userId) {
        return markRepository.findMarkByUser_Id(userId);
    }
}