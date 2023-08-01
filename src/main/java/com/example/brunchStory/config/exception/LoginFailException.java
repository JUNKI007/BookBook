package com.example.brunchStory.config.exception;

public class LoginFailException extends RuntimeException{
    public LoginFailException(String message){
        super((message));
    }
}
