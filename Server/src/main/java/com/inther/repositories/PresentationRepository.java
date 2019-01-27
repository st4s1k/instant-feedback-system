package com.inther.repositories;

import com.inther.entities.PresentationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PresentationRepository extends JpaRepository<PresentationEntity, Integer>
{

}