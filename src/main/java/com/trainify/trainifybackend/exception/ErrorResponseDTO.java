package com.trainify.trainifybackend.exception;

public record ErrorResponseDTO(
        int status,      // kod HTTP (np. 400, 404, 500)
        String error,    // krótki opis błędu (np. "Bad Request")
        String message,  // szczegółowy komunikat (np. "Email jest już zajęty")
        String path,     // endpoint który wywołał bląd np ("/register")
        long timestamp   // czas w milisekundach od 01.01.1970 (Unix epoch), kiedy wyjątek wystąpił.,
        // Przykład: 1734500000000 → ułatwia debugowanie i śledzenie w logach
) {

    // Konstruktor ułatwiający ustawienie timestamp automatycznie
    public ErrorResponseDTO(int status, String error, String message, String path){
        this(status,error,message,path, System.currentTimeMillis());
    }
}
