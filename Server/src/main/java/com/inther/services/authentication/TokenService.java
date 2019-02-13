package com.inther.services.authentication;

import java.util.Map;

/**
 * Creates and validates credentials.
 */
public interface TokenService {

    String generate(Map<String, String> attributes);

    /**
     * Checks the validity of the given credentials.
     *
     * @param token
     * @return attributes if verified
     */
    Map<String, String> untrusted(String token);

    /**
     * Checks the validity of the given credentials.
     *
     * @param token
     * @return attributes if verified
     */
    Map<String, String> verify(String token);
}