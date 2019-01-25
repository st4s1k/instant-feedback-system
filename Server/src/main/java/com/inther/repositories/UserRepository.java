package com.inther.repositories;

import com.inther.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>
{
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "INSERT INTO users(email, password, enabled) VALUES(:email, :password, :enabled)")
    void addUser(@Param(value = "email") String email, @Param(value = "password") String password, @Param(value = "enabled") Integer enabled);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "DELETE FROM users WHERE email = :email ")
    void deleteUser(@Param(value = "email") String email);
}