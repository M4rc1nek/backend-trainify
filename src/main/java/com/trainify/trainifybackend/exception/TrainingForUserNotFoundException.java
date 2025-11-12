package com.trainify.trainifybackend.exception;

public class TrainingForUserNotFoundException extends RuntimeException {
    public TrainingForUserNotFoundException(String message) {
        super(message);
    }
}
