package com.whatsapp.assignment.controller;

import com.whatsapp.assignment.dto.request.RegistrationRequestDto;
import com.whatsapp.assignment.dto.response.RegistrationResponseDto;
import com.whatsapp.assignment.modes.WhatsAppUserRegistration;
import com.whatsapp.assignment.service.WhatsAppAssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/whatsapp")
public class WhatsAppAssignmentController {
    private final WhatsAppAssignmentService whatsAppAssignmentService;

    @PostMapping("/register")
    public RegistrationResponseDto registerUser(@RequestBody RegistrationRequestDto registrationRequestDto) {
        return whatsAppAssignmentService.registerUser(registrationRequestDto);
    }
    @PostMapping("/login")
    public RegistrationResponseDto findUserByEmailAndPassword(@RequestParam String email, @RequestParam String password) {
        return whatsAppAssignmentService.findUserByEmailAndPassword(email, password);
    }
    @GetMapping("/email")
    public RegistrationResponseDto findUserByEmail(@RequestParam String email) {
        return whatsAppAssignmentService.findUserByEmail(email);
    }
    @PutMapping("/profile/update/{id}")
    public RegistrationResponseDto updateProfile(@PathVariable Long id, @RequestBody RegistrationRequestDto registrationRequestDto) {
        return whatsAppAssignmentService.updateProfile(id, registrationRequestDto);
    }
    @DeleteMapping("/profile/delete/{id}")
    public void deleteProfile(@PathVariable Long id) {
        whatsAppAssignmentService.deleteProfile(id);
    }
    @GetMapping("/profile/{id}")
    public RegistrationResponseDto getProfile(@PathVariable Long id) {
        return whatsAppAssignmentService.getProfile(id);
    }
    @GetMapping("/profile/all")
    public List<WhatsAppUserRegistration> getAllProfiles() {
        return whatsAppAssignmentService.getAllProfiles();
    }


}
