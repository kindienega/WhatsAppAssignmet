package com.whatsapp.assignment.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageResponseDto {
    private String senderEmail;
    private String receiverEmail;
    private String message;
    private String imageUrl;
}
