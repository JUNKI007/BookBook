package com.example.brunchStory.config.exception;

public class EmptyTokenException extends RuntimeException{
    public  EmptyTokenException(String message){
        super((message));
    }
}
