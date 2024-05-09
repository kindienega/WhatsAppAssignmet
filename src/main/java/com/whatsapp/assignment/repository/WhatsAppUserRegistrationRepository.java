package com.whatsapp.assignment.repository;

import com.whatsapp.assignment.modes.WhatsAppUserRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WhatsAppUserRegistrationRepository extends JpaRepository<WhatsAppUserRegistration, Long>{
    Optional<WhatsAppUserRegistration> findByEmail(String email);
    Optional<WhatsAppUserRegistration> findByEmailAndPassword(String email, String password);

}
