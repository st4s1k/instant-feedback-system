package com.inther.repositories;

import com.inther.entities.implementation.UserAuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserAuthorityRepository extends JpaRepository<UserAuthorityEntity, Integer>
{
    Optional<UserAuthorityEntity> findUserAuthorityEntityByEmailAndAuthority(String email, String authority);
    Optional<UserAuthorityEntity> findUserAuthorityEntityById(Integer id);

    Optional<List<UserAuthorityEntity>> findUserAuthorityEntityByEmail(String email);

    @Modifying
    @Transactional
    void deleteUserAuthorityEntityById(Integer authorityId);
}