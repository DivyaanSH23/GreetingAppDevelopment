package com.example.greeting.service;

import com.example.greeting.dto.AuthUserDTO;
import com.example.greeting.dto.LoginDTO;
import com.example.greeting.model.AuthUser;

public interface IAuthenticationService {
    AuthUser register(AuthUserDTO userDTO) throws Exception;

    String login(LoginDTO loginDTO);
}
