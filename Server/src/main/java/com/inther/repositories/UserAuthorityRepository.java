package com.inther.repositories;

import com.inther.domain.UserAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserAuthorityRepository extends JpaRepository<UserAuthority, Integer>
{
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "INSERT INTO user_authorities(email, authority) VALUES(:email, :authority)")
    void addUserAuthority(@Param(value = "email") String email, @Param(value = "authority") String authority);
}