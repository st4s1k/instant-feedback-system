package com.inther.services.entity;

import com.inther.entities.Mark;
import com.inther.repositories.MarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
                .findMarkByPresentationIdAndUserId(mark.getPresentationId(), mark.getUserId())
                .map(u -> Optional.<Mark>empty())
                .orElseGet(() -> Optional.of(markRepository.save(mark)));
    }
}