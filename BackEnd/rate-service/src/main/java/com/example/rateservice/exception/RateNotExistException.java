package com.example.rateservice.exception;

public class RateNotExistException extends IllegalArgumentException {
    public RateNotExistException(String msg) {
            super(msg);
        }
    }
