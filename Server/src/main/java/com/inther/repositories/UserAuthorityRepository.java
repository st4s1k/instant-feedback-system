package com.inther.repositories;

import com.inther.entities.UserAuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserAuthorityRepository extends JpaRepository<UserAuthorityEntity, UUID>
{
    Optional<List<UserAuthorityEntity>> findUserAuthorityEntityByEmail(String email);

    Optional<UserAuthorityEntity> findUserAuthorityEntityByEmailAndAuthority(String email, String authority);
    Optional<UserAuthorityEntity> findUserAuthorityEntityById(UUID id);

    @Transactional
    void deleteUserAuthorityEntityById(UUID authorityId);
}