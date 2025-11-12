package com.trainify.trainifybackend.security;

import com.trainify.trainifybackend.security.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {  // OncePerRequestFilter — zapewnia, że filtr wykona się tylko raz na każde żądanie HTTP.

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain // Zbiór filtrów, przez które ma przejść żądanie
    ) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization"); // Pobranie nagłówka "Authorization", to tutaj frontend przesyła token JWT
        final String jwt;
        final String userEmail;

        // 1. Sprawdzenie, czy nagłówek istnieje i zaczyna się od "Bearer "
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response); // Przekazanie do kolejnego filtra
            return;
        }

        jwt = authHeader.substring(7); // Obcina pierwsze 7 znaków ("Bearer ") i zostawia sam token JWT. Przykład:"Bearer abc.def.ghi" → "abc.def.ghi"
        userEmail = jwtService.extractUsername(jwt); // Używa mojego JwtService zeby wyciagnac email uzytkownika (Bo w tokenie zapisuje jego adres email)

        // 2. Warunek sprawdzajacy czy token faktycznie zawiera email i czy nikt jeszcze nie jest zalogowany
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // Wczytanie szczegółów użytkownika (UserDetails) z bazy na podstawie emaila użytkownika
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);


            // 3. Sprawdza czy token JWT jest ważny i prawidłowy
            //Jezeli token jest wazny i prawidlowy, tworzymy obiekt uwierzytelnienia ktory mowi "ten uzytkownik jest zalogowany"
            if (jwtService.isTokenValid(jwt, userDetails)) {

                // Tworzenie obiektu uwierzytelnienia Spring Security
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null, // JWT nie przechowuje hasła, więc null
                        userDetails.getAuthorities() // Uprawnienia (role)
                );

                // Dodanie szczegółów żądania do tokena (np. IP, sesja)
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                // 4. Ustawia w globalnym kontekście Spring Security, że ten użytkownik jest zalogowany (NAJWAŻNIEJSZA LINIA)
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response); // Przekazanie do kolejnego filtra/kontrolera
    }
}