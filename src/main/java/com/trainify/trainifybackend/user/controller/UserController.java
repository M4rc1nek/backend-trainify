package com.trainify.trainifybackend.user.controller;


import com.trainify.trainifybackend.user.dto.UserRegisterLoginDTO;
import com.trainify.trainifybackend.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;


    @PostMapping("/register")
    public ResponseEntity<UserRegisterLoginDTO> registerUserEndpoint(@RequestBody @Valid UserRegisterLoginDTO userRegisterLoginDTO) {
        return ResponseEntity.ok(userService.registerUser(userRegisterLoginDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<UserRegisterLoginDTO> loginUserEndpoint(@RequestBody @Valid UserRegisterLoginDTO userLogin) {
        return ResponseEntity.ok(userService.loginUser(userLogin.email(), userLogin.password()));
    }
}

