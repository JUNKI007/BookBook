package com.example.bookBook.email.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EmailMessage {

    // 이게 있어야하나?

    private String from;
    private String to;
    private String subject;
    private String message;
}
