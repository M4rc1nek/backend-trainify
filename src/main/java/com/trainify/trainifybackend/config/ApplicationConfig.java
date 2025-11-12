package com.trainify.trainifybackend.config;

import com.trainify.trainifybackend.exception.UserNotFoundException;
import com.trainify.trainifybackend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final UserRepository userRepository; // Wstrzyknięcie repozytorium użytkowników

    //Wczytywanie  użytkownika i zwracanie uzytkownika z zahashowanym haslem i rolami
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByEmail(username) // Spring Security użyje "username", ale u nas to jest email
                .map(user -> org.springframework.security.core.userdetails.User.builder() // Mapowanie (zamiana) naszej klasy User na UserDetails
                        .username(user.getEmail())
                        .password(user.getPassword())
                        // W prostym projekcie nie używamy ról, ale jeśli chcesz, dodaj np. .roles("USER")
                        .build())
                .orElseThrow(() -> new UserNotFoundException("Nie znaleziono użytkownika"));
    }

    // 2. Provider uwierzytelniania, pobiera haslo od uzytkownika i prosi UserDetailsService o załadaowanie zahashowanego hasła z bazy
    // A nastepnie prosi PasswordEncoder o sprawdzenie czy te dwa hasla do siebie pasuja (cos typu .matches)
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService()); // Ustawienie serwisu do ładowania użytkownika
        authProvider.setPasswordEncoder(passwordEncoder()); // Ustawienie kodera hasła
        return authProvider;
    }

    // 3. Manager uwierzytelniania (Kieruje
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception { // Uwierzytelnia uzytkownikow (np. sprawdzenie loginu, hasla)
        return config.getAuthenticationManager(); // zwraca w pełni skonfigurowany obiekt AuthenticationManager
    }

    // 4. Koder hasła
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}