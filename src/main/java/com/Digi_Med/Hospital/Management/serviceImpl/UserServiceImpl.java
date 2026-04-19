package com.Digi_Med.Hospital.Management.serviceImpl;


import com.Digi_Med.Hospital.Management.dtos.LoginRequest;
import com.Digi_Med.Hospital.Management.dtos.LoginResponse;
import com.Digi_Med.Hospital.Management.models.User;
import com.Digi_Med.Hospital.Management.repository.UserRepository;
import com.Digi_Med.Hospital.Management.services.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        // Find user by email and password (without encryption for now)
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElse(null);

        if (user == null) {
            return new LoginResponse(false, "User not found", null, null, null);
        }

        // Check password (simple comparison - will enhance later)
        if (!user.getPassword().equals(loginRequest.getPassword())) {
            return new LoginResponse(false, "Invalid password", null, null, null);
        }

        // Login successful
        return new LoginResponse(
                true,
                "Login successful",
                user.getEmail(),
                user.getFullName(),
                user.getRole().toString()
        );
    }
}