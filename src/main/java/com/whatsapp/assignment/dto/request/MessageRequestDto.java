package com.whatsapp.assignment.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageRequestDto {
    private String sender;
    private String receiver;
    private String message;
}
