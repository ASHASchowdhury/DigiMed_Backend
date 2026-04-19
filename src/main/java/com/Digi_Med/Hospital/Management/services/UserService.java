package com.Digi_Med.Hospital.Management.services;

import com.Digi_Med.Hospital.Management.dtos.LoginRequest;
import com.Digi_Med.Hospital.Management.dtos.LoginResponse;

public interface UserService {
    LoginResponse login(LoginRequest loginRequest);
}