package com.whatsapp.assignment.mapping;

import com.whatsapp.assignment.dto.request.RegistrationRequestDto;
import com.whatsapp.assignment.dto.response.RegistrationResponseDto;
import com.whatsapp.assignment.modes.WhatsAppUserRegistration;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MappingFunction {
    public WhatsAppUserRegistration convertRegistrationRequestToEntity(RegistrationRequestDto  registrationRequestDto) {
        WhatsAppUserRegistration whatsAppUserRegistration = new WhatsAppUserRegistration();
        whatsAppUserRegistration.setFirstName(registrationRequestDto.getFirstName());
        whatsAppUserRegistration.setLastName(registrationRequestDto.getLastName());
        whatsAppUserRegistration.setPhoneNumber(registrationRequestDto.getPhoneNumber());
        whatsAppUserRegistration.setEmail(registrationRequestDto.getEmail());
        whatsAppUserRegistration.setPassword(registrationRequestDto.getPassword());
        return whatsAppUserRegistration;
    }
    public RegistrationResponseDto convertEntityToRegistrationResponse(WhatsAppUserRegistration whatsAppUserRegistration) {
        RegistrationResponseDto response = new RegistrationResponseDto();
        response.setFirstName(whatsAppUserRegistration.getFirstName());
        response.setLastName(whatsAppUserRegistration.getLastName());
        response.setEmail(whatsAppUserRegistration.getEmail());
        response.setPhoneNumber(whatsAppUserRegistration.getPhoneNumber());
        return response;
    }
}
