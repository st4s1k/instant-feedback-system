package com.inther.repositories;

import com.inther.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer>
{
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "INSERT INTO user_roles(email, role) VALUES(:email, :role)")
    void addUserRole(@Param(value = "email") String email, @Param(value = "role") String role);
}