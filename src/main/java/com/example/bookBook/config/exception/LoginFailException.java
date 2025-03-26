package com.example.bookBook.config.exception;

public class LoginFailException extends RuntimeException{
    public LoginFailException(String message){
        super((message));
    }
}
