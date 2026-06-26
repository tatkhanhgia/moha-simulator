package com.example.hdld.application.usecase;

import com.example.hdld.application.dto.request.LoginRequest;
import com.example.hdld.application.dto.response.LoginResponse;
import com.example.hdld.application.port.PasswordEncoderPort;
import com.example.hdld.application.port.TokenPort;
import com.example.hdld.domain.entity.User;
import com.example.hdld.domain.exception.UnauthorizedException;
import com.example.hdld.domain.repository.UserRepository;

/**
 * Authenticates a user and generates a JWT token.
 */
public class AuthenticateUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoderPort passwordEncoderPort;
    private final TokenPort tokenPort;

    public AuthenticateUseCase(UserRepository userRepository,
                               PasswordEncoderPort passwordEncoderPort,
                               TokenPort tokenPort) {
        this.userRepository = userRepository;
        this.passwordEncoderPort = passwordEncoderPort;
        this.tokenPort = tokenPort;
    }

    public LoginResponse execute(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UnauthorizedException("Invalid username or password"));

        if (!passwordEncoderPort.matches(request.getPassword(), user.getPasswordHash())) {
            throw new UnauthorizedException("Invalid username or password");
        }

        String token = tokenPort.generateToken(user.getUsername());
        return new LoginResponse(token);
    }
}
