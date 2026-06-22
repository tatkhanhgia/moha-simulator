package com.example.hdld.application.usecase;

import com.example.hdld.application.dto.request.LoginRequest;
import com.example.hdld.application.dto.response.LoginResponse;
import com.example.hdld.application.port.PasswordEncoderPort;
import com.example.hdld.application.port.TokenPort;
import com.example.hdld.domain.entity.User;
import com.example.hdld.domain.exception.UnauthorizedException;
import com.example.hdld.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthenticateUseCaseTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoderPort passwordEncoderPort;
    @Mock
    private TokenPort tokenPort;

    private AuthenticateUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new AuthenticateUseCase(userRepository, passwordEncoderPort, tokenPort);
    }

    @Test
    void execute_shouldReturnTokenWhenCredentialsValid() {
        LoginRequest request = new LoginRequest("testuser", "password123");
        User user = new User(1L, "testuser", "hashedPassword", "ROLE_USER");

        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));
        when(passwordEncoderPort.matches("password123", "hashedPassword")).thenReturn(true);
        when(tokenPort.generateToken("testuser")).thenReturn("jwt-token-123");

        LoginResponse response = useCase.execute(request);

        assertThat(response.getToken()).isEqualTo("jwt-token-123");
        assertThat(response.getExpiresIn()).isEqualTo(86400L);
    }

    @Test
    void execute_shouldThrowUnauthorizedWhenUserNotFound() {
        LoginRequest request = new LoginRequest("unknown", "password123");
        when(userRepository.findByUsername("unknown")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> useCase.execute(request))
                .isInstanceOf(UnauthorizedException.class)
                .hasMessageContaining("Invalid username or password");
    }

    @Test
    void execute_shouldThrowUnauthorizedWhenPasswordInvalid() {
        LoginRequest request = new LoginRequest("testuser", "wrongpassword");
        User user = new User(1L, "testuser", "hashedPassword", "ROLE_USER");

        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));
        when(passwordEncoderPort.matches("wrongpassword", "hashedPassword")).thenReturn(false);

        assertThatThrownBy(() -> useCase.execute(request))
                .isInstanceOf(UnauthorizedException.class)
                .hasMessageContaining("Invalid username or password");
    }
}
