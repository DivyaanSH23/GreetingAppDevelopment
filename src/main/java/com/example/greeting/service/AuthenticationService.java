package com.example.greeting.service;

import com.example.greeting.dto.AuthUserDTO;
import com.example.greeting.dto.LoginDTO;
import com.example.greeting.exception.UserException;
import com.example.greeting.model.AuthUser;
import com.example.greeting.repository.AuthUserRepository;
import com.example.greeting.util.EmailSenderService;
import com.example.greeting.util.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService implements IAuthenticationService {

    @Autowired
    private AuthUserRepository authUserRepository;

    @Autowired
    private JwtToken tokenUtil;

    @Autowired
    private EmailSenderService emailSenderService;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public AuthUser register(AuthUserDTO userDTO) throws Exception {
        AuthUser user = new AuthUser(userDTO);

        // ✅ Hashing password before saving to database
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        String token = tokenUtil.createToken(user.getUserId());
        authUserRepository.save(user);

        emailSenderService.sendEmail(user.getEmail(), "Registered in Greeting App",
                "Hii " + user.getFirstName() + ",\n\nYou have been successfully registered!\n\n"
                        + "User Id: " + user.getUserId() + "\n"
                        + "First Name: " + user.getFirstName() + "\n"
                        + "Last Name: " + user.getLastName() + "\n"
                        + "Email: " + user.getEmail() + "\n"
                        + "Token: " + token);

        return user;
    }

    @Override
    public String login(LoginDTO loginDTO) {
        Optional<AuthUser> user = Optional.ofNullable(authUserRepository.findByEmail(loginDTO.getEmail()));

        // ✅ Secure password matching using BCrypt
        if (user.isPresent() && passwordEncoder.matches(loginDTO.getPassword(), user.get().getPassword())) {
            emailSenderService.sendEmail(user.get().getEmail(), "Logged in Successfully!",
                    "Hii " + user.get().getFirstName() + ",\n\nYou have successfully logged in into Greeting App!");
            return "Congratulations!! You have logged in successfully!";
        } else {
            throw new UserException("Sorry! Email or Password is incorrect!");
        }
    }

    public String forgotPassword(String email, String newPassword) {
        Optional<AuthUser> userOptional = Optional.ofNullable(authUserRepository.findByEmail(email));
        if (userOptional.isEmpty()) {
            throw new UserException("Sorry! We cannot find the user email: " + email);
        }

        AuthUser user = userOptional.get();
        user.setPassword(passwordEncoder.encode(newPassword));  // Hash the new password
        authUserRepository.save(user);

        emailSenderService.sendEmail(user.getEmail(), "Password Changed",
                "Your password has been successfully changed.\n\nIf this wasn't you, please contact support immediately.");

        return "Password has been changed successfully!";
    }

    public String resetPassword(String email, String currentPassword, String newPassword) {
        Optional<AuthUser> userOptional = Optional.ofNullable(authUserRepository.findByEmail(email));
        if (userOptional.isEmpty()) {
            throw new UserException("User not found with email: " + email);
        }

        AuthUser user = userOptional.get();

        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            throw new UserException("Current password is incorrect!");
        }

        user.setPassword(passwordEncoder.encode(newPassword));  // Hash the new password
        authUserRepository.save(user);

        return "Password reset successfully!";
    }

}
