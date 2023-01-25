package com.example.spring_boot.Exception;

public class DataNotFound extends java.lang.Exception {

    private String message;
    public DataNotFound(String message) {
        super(message);
    }
}
