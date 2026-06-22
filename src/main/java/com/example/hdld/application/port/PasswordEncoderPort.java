package com.example.hdld.application.port;

/**
 * Application port for password encoding and matching.
 * Implemented by infrastructure security layer.
 */
public interface PasswordEncoderPort {

    /**
     * Encodes a raw password string.
     */
    String encode(String rawPassword);

    /**
     * Checks whether raw password matches encoded password.
     */
    boolean matches(String rawPassword, String encodedPassword);
}
