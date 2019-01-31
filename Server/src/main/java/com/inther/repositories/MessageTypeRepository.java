package com.inther.repositories;

import com.inther.entities.implementation.MessageTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface MessageTypeRepository extends JpaRepository<MessageTypeEntity, Integer>
{

}