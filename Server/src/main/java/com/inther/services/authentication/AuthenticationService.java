package com.inther.services.authentication;

import com.inther.dto.UserDto;
import com.inther.entities.User;

import java.util.Optional;

public interface AuthenticationService {

    /**
     * Logs in with the given {@code username} and {@code password}.
     *
     * @param email
     * @param password
     * @return an {@link Optional} of a userEmail when login succeeds
     */
    Optional<UserDto> login(String email, String password);

    /**
     * Finds a userEmail by its dao-key.
     *
     * @param token userEmail dao key
     * @return
     */
    Optional<User> findByToken(String token);

    /**
     * Logs out the given input {@code userEmail}.
     *
     * @param user the userEmail to logout
     */
    void logout(User user);
}

