package com.academy.football_system.exception;

public class InvalidCsvFormatException extends RuntimeException {

    public InvalidCsvFormatException(String message){
        super(message);
    }
}
