package com.example.hdld.application.usecase;

import com.example.hdld.application.dto.request.ChangePasswordRequest;
import com.example.hdld.application.port.PasswordEncoderPort;
import com.example.hdld.domain.entity.User;
import com.example.hdld.domain.exception.UnauthorizedException;
import com.example.hdld.domain.exception.ValidationException;
import com.example.hdld.domain.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Changes a user's password after verifying the old password.
 */
public class ChangePasswordUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoderPort passwordEncoderPort;

    public ChangePasswordUseCase(UserRepository userRepository,
                                 PasswordEncoderPort passwordEncoderPort) {
        this.userRepository = userRepository;
        this.passwordEncoderPort = passwordEncoderPort;
    }

    @Transactional
    public void execute(String username, ChangePasswordRequest request) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UnauthorizedException("User not found"));

        if (!passwordEncoderPort.matches(request.getOldPassword(), user.getPasswordHash())) {
            throw new UnauthorizedException("Old password is incorrect");
        }

        String newHash = passwordEncoderPort.encode(request.getNewPassword());
        user.setPasswordHash(newHash);
        userRepository.save(user);
    }
}
