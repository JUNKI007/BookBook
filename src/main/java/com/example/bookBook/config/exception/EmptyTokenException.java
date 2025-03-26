package com.example.bookBook.config.exception;

public class EmptyTokenException extends RuntimeException{
    public  EmptyTokenException(String message){
        super((message));
    }
}
