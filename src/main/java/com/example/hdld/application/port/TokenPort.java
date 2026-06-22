package com.example.hdld.application.port;

/**
 * Application port for token generation and validation.
 * Implemented by infrastructure security layer.
 */
public interface TokenPort {

    /**
     * Generates a token for the given username.
     */
    String generateToken(String username);

    /**
     * Validates a token string. Returns the username if valid, or null/throws if invalid.
     */
    String validateToken(String token);

    /**
     * Extracts the username from a token without full validation.
     */
    String extractUsername(String token);
}
