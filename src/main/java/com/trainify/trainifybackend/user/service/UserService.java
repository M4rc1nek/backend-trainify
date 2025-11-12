package com.trainify.trainifybackend.user.service;

import com.trainify.trainifybackend.exception.EmailAlreadyExistsException;
import com.trainify.trainifybackend.exception.UserNotFoundException;
import com.trainify.trainifybackend.exception.UsernameAlreadyExistsException;
import com.trainify.trainifybackend.exception.WrongPasswordException;
import com.trainify.trainifybackend.security.service.JwtService;
import com.trainify.trainifybackend.user.dto.UserRegisterLoginDTO;
import com.trainify.trainifybackend.user.model.User;
import com.trainify.trainifybackend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public UserRegisterLoginDTO registerUser(UserRegisterLoginDTO userRegisterLoginDTO) {

        if (userRepository.existsByUsername(userRegisterLoginDTO.username())) {
            throw new UsernameAlreadyExistsException("Nazwa użytkownika jest już zajęta");
        }

        if (userRepository.existsByEmail(userRegisterLoginDTO.email())) {
            throw new EmailAlreadyExistsException("Email jest już zajęty");
        }


        User user = User.builder()
                .username(userRegisterLoginDTO.username())
                .email(userRegisterLoginDTO.email())
                .password(passwordEncoder.encode(userRegisterLoginDTO.password())) // hashowanie hasła
                .createdAt(LocalDateTime.now())
                .build();
        User savedUser = userRepository.save(user);

        String jwtToken = jwtService.generateToken(
                new org.springframework.security.core.userdetails.User(
                        savedUser.getEmail(),
                        savedUser.getPassword(),
                        List.of()
                )
        );

        return new UserRegisterLoginDTO(user.getId(),savedUser.getUsername(), null, savedUser.getEmail(), jwtToken);

    }

    public UserRegisterLoginDTO loginUser(String email, String rawPassword) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, rawPassword)
            );
        } catch (AuthenticationException e) {
            throw new WrongPasswordException("Nieprawidłowy email lub hasło");
        }


        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Nie znaleziono użytkownika"));


        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                List.of()
        );
        String jwtToken = jwtService.generateToken(userDetails);

        return new UserRegisterLoginDTO(user.getId(),user.getUsername(), null, user.getEmail(), jwtToken);
    }


    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }


}
