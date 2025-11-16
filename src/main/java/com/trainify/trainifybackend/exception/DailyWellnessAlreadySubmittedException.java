package com.trainify.trainifybackend.exception;

public class DailyWellnessAlreadySubmittedException extends RuntimeException {
    public DailyWellnessAlreadySubmittedException(String message) {
        super(message);
    }
}
