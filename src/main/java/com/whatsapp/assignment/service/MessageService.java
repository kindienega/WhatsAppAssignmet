package com.whatsapp.assignment.service;

import com.whatsapp.assignment.modes.Message;
import com.whatsapp.assignment.modes.WhatsAppUserRegistration;
import com.whatsapp.assignment.repository.MessageRepository;
import com.whatsapp.assignment.repository.WhatsAppUserRegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final WhatsAppUserRegistrationRepository userRegistrationRepository;
    public Message sendMessage(String senderEmail, String receiverEmail, String content) {
        Optional<WhatsAppUserRegistration> sender = userRegistrationRepository.findByEmail(senderEmail);
        Optional<WhatsAppUserRegistration> receiver = userRegistrationRepository.findByEmail(receiverEmail);

        if (sender.isEmpty() || receiver.isEmpty()) {
            throw new IllegalArgumentException("Both sender and receiver must be registered users.");
        }

        Message message = new Message();
        message.setSender(senderEmail);
        message.setReceiver(receiverEmail);
        message.setMessage(content);
        return messageRepository.save(message);
    }
    public Message getMessage(Long id) {
        return messageRepository.findById(id).orElse(null);
    }


}
