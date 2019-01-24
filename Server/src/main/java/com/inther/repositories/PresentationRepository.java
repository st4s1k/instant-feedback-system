package com.inther.repositories;


import com.inther.domain.Presentation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PresentationRepository extends JpaRepository<Presentation, Integer>
{
    //Optional<Presentation> findByUsername(Integer userName);

    void deleteByPresentationId(Integer presentationId);


}
