package com.inther.repositories;

import com.inther.entities.implementation.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String>
{
    Optional<UserEntity> findUserEntityByEmail(String email);

    @Modifying
    @Transactional
    void deleteUserEntityByEmail(String email);
}