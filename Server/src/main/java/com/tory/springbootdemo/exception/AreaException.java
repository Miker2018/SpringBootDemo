package com.tory.springbootdemo.exception;

public class AreaException extends Exception{
    private String message;

    public AreaException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
