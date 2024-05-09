package com.whatsapp.assignment.dto.response;

import lombok.Data;

@Data
public class RegistrationResponseDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
