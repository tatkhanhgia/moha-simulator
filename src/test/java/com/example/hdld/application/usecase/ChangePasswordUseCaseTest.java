package com.example.hdld.application.usecase;

import com.example.hdld.application.dto.request.ChangePasswordRequest;
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
class ChangePasswordUseCaseTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoderPort passwordEncoderPort;
    @Mock
    private TokenPort tokenPort;

    private ChangePasswordUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new ChangePasswordUseCase(userRepository, passwordEncoderPort, tokenPort);
    }

    @Test
    void execute_shouldChangePasswordWhenOldPasswordValid() {
        String username = "testuser";
        User user = new User(1L, username, "oldHash", "ROLE_USER");
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        when(passwordEncoderPort.matches("oldpass", "oldHash")).thenReturn(true);
        when(passwordEncoderPort.encode("newpass")).thenReturn("newHash");
        when(tokenPort.generateToken(username)).thenReturn("new-token-123");

        ChangePasswordRequest request = new ChangePasswordRequest("oldpass", "newpass");
        String token = useCase.execute(username, request);

        assertThat(token).isEqualTo("new-token-123");
        assertThat(user.getPasswordHash()).isEqualTo("newHash");
    }

    @Test
    void execute_shouldThrowUnauthorizedWhenUserNotFound() {
        when(userRepository.findByUsername("unknown")).thenReturn(Optional.empty());

        ChangePasswordRequest request = new ChangePasswordRequest("oldpass", "newpass");
        assertThatThrownBy(() -> useCase.execute("unknown", request))
                .isInstanceOf(UnauthorizedException.class)
                .hasMessageContaining("User not found");
    }

    @Test
    void execute_shouldThrowUnauthorizedWhenOldPasswordInvalid() {
        String username = "testuser";
        User user = new User(1L, username, "oldHash", "ROLE_USER");
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        when(passwordEncoderPort.matches("wrongpass", "oldHash")).thenReturn(false);

        ChangePasswordRequest request = new ChangePasswordRequest("wrongpass", "newpass");
        assertThatThrownBy(() -> useCase.execute(username, request))
                .isInstanceOf(UnauthorizedException.class)
                .hasMessageContaining("Old password is incorrect");
    }
}
