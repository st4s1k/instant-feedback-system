package com.inther.repositories;

import com.inther.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, String>
{
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserById(UUID id);

    @Modifying
    @Transactional
    void deleteUserByEmail(String email);

    @Modifying
    @Transactional
    void deleteUserById(UUID id);
}