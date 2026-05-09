package com.Digi_Med.Hospital.Management.serviceImpl;

import com.Digi_Med.Hospital.Management.dtos.LoginRequest;
import com.Digi_Med.Hospital.Management.dtos.LoginResponse;
import com.Digi_Med.Hospital.Management.models.User;
import com.Digi_Med.Hospital.Management.repository.UserRepository;
import com.Digi_Med.Hospital.Management.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElse(null);

        if (user == null) {
            return new LoginResponse(false, "User not found", null, null, null, null);
        }

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return new LoginResponse(false, "Invalid password", null, null, null, null);
        }

        if (!user.isEnabled()) {
            return new LoginResponse(false, "Account is disabled", null, null, null, null);
        }

        return new LoginResponse(
                true,
                "Login successful",
                user.getEmail(),
                user.getFullName(),
                user.getRole().toString(),
                user.getId()
        );
    }
}
