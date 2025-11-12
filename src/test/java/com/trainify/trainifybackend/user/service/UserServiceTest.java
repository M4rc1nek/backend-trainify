package com.trainify.trainifybackend.user.service;


import com.trainify.trainifybackend.exception.EmailAlreadyExistsException;
import com.trainify.trainifybackend.exception.UserNotFoundException;
import com.trainify.trainifybackend.exception.UsernameAlreadyExistsException;
import com.trainify.trainifybackend.exception.WrongPasswordException;
import com.trainify.trainifybackend.user.dto.UserRegisterLoginDTO;
import com.trainify.trainifybackend.user.model.User;
import com.trainify.trainifybackend.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService; //InjectMocks dlatego ze testujesz prawdziwe zachowanie UserService

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;


    @Test
    void shouldRegisterUserSuccessfully() {

        // GIVEN
        UserRegisterLoginDTO userRegisterLoginDTO = new UserRegisterLoginDTO("Marcin", "haslo123", "supermail@example.com");

        when(userRepository.existsByUsername("Marcin")).thenReturn(false);
        when(userRepository.existsByEmail("supermail@example.com")).thenReturn(false);


        when(passwordEncoder.encode("haslo123")).thenReturn("hashedPassword");

        //Powyzej symulujesz czy uzytkownik "Marcin: z mailem "supermail@example.com" nie istnieje w bazie, symulujesz czy haslo zostało zahashowane


        User savedUser = User.builder()
                .id(1L)
                .username("Marcin")
                .email("supermail@example.com")
                .password("hashedPassword")
                .build();


        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        //WHEN
        UserRegisterLoginDTO result = userService.registerUser(userRegisterLoginDTO);


        //THEN
        assertEquals("Marcin", result.username());
        assertEquals("supermail@example.com", result.email());
        assertNull(result.password());

    }


    @Test
    void shouldThrowWhenUsernameAlreadyExists() {


        //GIVEN
        UserRegisterLoginDTO userRegisterLoginDTO = new UserRegisterLoginDTO("Marcin", null, null);

        when(userRepository.existsByUsername("Marcin")).thenReturn(true);

        //WHEN & THEN
        assertThrows(UsernameAlreadyExistsException.class, () -> userService.registerUser(userRegisterLoginDTO));


    }

    @Test
    void shouldThrowWhenEmailAlreadyExists() {

        //GIVEN
        UserRegisterLoginDTO userRegisterLoginDTO = new UserRegisterLoginDTO(null, null, "example@gmail.com");

        when(userRepository.existsByEmail("example@gmail.com")).thenReturn(true);

        assertThrows(EmailAlreadyExistsException.class, () -> userService.registerUser(userRegisterLoginDTO));

    }

    @Test
    void shouldLoginSuccessfully() {
        //GIVEN
        User user = User.builder()
                .id(1L)
                .username("Marcin")
                .email("example@gmail.com")
                .password("hashedPassword")
                .build();

        when(userRepository.findByEmail("example@gmail.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("haslo123", "hashedPassword")).thenReturn(true);


        UserRegisterLoginDTO result = userService.loginUser("example@gmail.com", "haslo123");


        assertEquals("example@gmail.com", result.email());
        assertNull(result.password());


    }

    @Test
    void shouldThrowWhenUserNotFound() {


        when(userRepository.findByEmail("example@gmail.com")).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.loginUser("example@gmail.com", "haslo123"));

    }


    @Test
    void shouldThrowWhenWrongPassword() {

        User user = User.builder()
                .id(1L)
                .username("Marcin")
                .email("example@gmail.com")
                .password("hashedPassword")
                .build();

        when(userRepository.findByEmail("example@gmail.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("wrongPassword", "hashedPassword")).thenReturn(false);


        assertThrows(WrongPasswordException.class, () -> userService.loginUser("example@gmail.com", "wrongPassword"));


    }



}
