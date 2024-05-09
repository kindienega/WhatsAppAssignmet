package com.whatsapp.assignment.service;

import com.whatsapp.assignment.dto.request.RegistrationRequestDto;
import com.whatsapp.assignment.dto.response.RegistrationResponseDto;
import com.whatsapp.assignment.mapping.MappingFunction;
import com.whatsapp.assignment.modes.WhatsAppUserRegistration;
import com.whatsapp.assignment.repository.WhatsAppUserRegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class WhatsAppAssignmentService {

    private final MappingFunction mappingFunction;
    private final WhatsAppUserRegistrationRepository whatsAppUserRegistrationRepository;

    public RegistrationResponseDto registerUser(RegistrationRequestDto registrationRequestDto) {
        WhatsAppUserRegistration whatsAppUserRegistration = mappingFunction.convertRegistrationRequestToEntity(registrationRequestDto);
        whatsAppUserRegistration = whatsAppUserRegistrationRepository.save(whatsAppUserRegistration);
        return mappingFunction.convertEntityToRegistrationResponse(whatsAppUserRegistration);
    }

    public RegistrationResponseDto updateProfile(Long id, RegistrationRequestDto registrationRequestDto) {
        Optional<WhatsAppUserRegistration> optionalUser = whatsAppUserRegistrationRepository.findById(id);
        if (optionalUser.isPresent()) {
            WhatsAppUserRegistration whatsAppUserRegistration = getWhatsAppUserRegistration(registrationRequestDto, optionalUser);
            whatsAppUserRegistration = whatsAppUserRegistrationRepository.save(whatsAppUserRegistration);
            return mappingFunction.convertEntityToRegistrationResponse(whatsAppUserRegistration);
        }
        return null;
    }

    private WhatsAppUserRegistration getWhatsAppUserRegistration(RegistrationRequestDto registrationRequestDto,
                                                                 Optional<WhatsAppUserRegistration> optionalUser) {
        WhatsAppUserRegistration whatsAppUserRegistration = optionalUser.get();
        whatsAppUserRegistration.setFirstName(registrationRequestDto.getFirstName());
        whatsAppUserRegistration.setLastName(registrationRequestDto.getLastName());
        whatsAppUserRegistration.setPhoneNumber(registrationRequestDto.getPhoneNumber());
        whatsAppUserRegistration.setEmail(registrationRequestDto.getEmail());
        whatsAppUserRegistration.setPassword(registrationRequestDto.getPassword());
        return whatsAppUserRegistration;
    }

    public RegistrationResponseDto getProfile(Long id) {
        Optional<WhatsAppUserRegistration> optionalUser = whatsAppUserRegistrationRepository.findById(id);
        return optionalUser.map(mappingFunction::convertEntityToRegistrationResponse).orElse(null);
    }

    public void deleteProfile(Long id) {
        whatsAppUserRegistrationRepository.deleteById(id);
    }
    public RegistrationResponseDto findUserByEmailAndPassword(String email, String password) {
        Optional<WhatsAppUserRegistration> optionalUser = whatsAppUserRegistrationRepository.findByEmailAndPassword(email, password);
        return optionalUser.map(mappingFunction::convertEntityToRegistrationResponse).orElse(null);
    }
    public RegistrationResponseDto findUserByEmail(String email) {
        Optional<WhatsAppUserRegistration> optionalUser = whatsAppUserRegistrationRepository.findByEmail(email);
        return optionalUser.map(mappingFunction::convertEntityToRegistrationResponse).orElse(null);
    }
    public List<WhatsAppUserRegistration> getAllProfiles() {
        return whatsAppUserRegistrationRepository.findAll();
    }
}
