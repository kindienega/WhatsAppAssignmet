package com.whatsapp.assignment.service;

import com.whatsapp.assignment.dto.request.MessageRequestDto;
import com.whatsapp.assignment.dto.response.MessageResponseDto;
import com.whatsapp.assignment.modes.Message;
import com.whatsapp.assignment.modes.WhatsAppUserRegistration;
import com.whatsapp.assignment.repository.MessageRepository;
import com.whatsapp.assignment.repository.WhatsAppUserRegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final WhatsAppUserRegistrationRepository userRegistrationRepository;
    private final CloudinaryService cloudinaryService;
    public Message sendMessage(String senderEmail, String receiverEmail, String content) {
        Optional<WhatsAppUserRegistration> sender = userRegistrationRepository.findByEmail(senderEmail);
        Optional<WhatsAppUserRegistration> receiver = userRegistrationRepository.findByEmail(receiverEmail);

        if (sender.isEmpty() || receiver.isEmpty()) {
            throw new IllegalArgumentException("Both sender and receiver must be registered users.");
        }

        Message message = new Message();
        message.setSender(senderEmail);
        message.setReceiver(receiverEmail);
        message.setMessage(content);;
        return messageRepository.save(message);
    }
    public Message getMessage(Long id) {
        return messageRepository.findById(id).orElse(null);
    }

    public MessageResponseDto sendMessageWithFile(MultipartFile file ,MessageRequestDto messageRequestDto){
        Optional<WhatsAppUserRegistration> sender = userRegistrationRepository.findByEmail(messageRequestDto.getSender());
        Optional<WhatsAppUserRegistration> receiver = userRegistrationRepository.findByEmail(messageRequestDto.getReceiver());

        if (sender.isEmpty() || receiver.isEmpty()) {
            throw new IllegalArgumentException("Both sender and receiver must be registered users.");
        }
        String fileUrl = cloudinaryService.uploadFile(file);

        Message message = new Message();
        message.setSender(messageRequestDto.getSender());
        message.setReceiver(messageRequestDto.getReceiver());
        message.setMessage(messageRequestDto.getMessage());
        message.setImageUrl(fileUrl);
        message = messageRepository.save(message);

        MessageResponseDto response = new MessageResponseDto();
        response.setMessage(message.getMessage());
        response.setSenderEmail(message.getSender());
        response.setReceiverEmail(message.getReceiver());
        response.setImageUrl(message.getImageUrl());
        return response;
    }
    public Message messageReplay(String senderEmail, String receiverEmail, String content) {
        List<String> allowedEmojis = Arrays.asList("thumbup", "love", "crying", "surprised");

        if (!allowedEmojis.contains(content) && !content.matches("[a-zA-Z0-9 ]+")) {
        throw new IllegalArgumentException("Content must be either a text message or one of the allowed emojis.");
          }
        Optional<WhatsAppUserRegistration> sender = userRegistrationRepository.findByEmail(senderEmail);
        Optional<WhatsAppUserRegistration> receiver = userRegistrationRepository.findByEmail(receiverEmail);

        if (sender.isEmpty() || receiver.isEmpty()) {
            throw new IllegalArgumentException("Both sender and receiver must be registered users.");
        }

        Message message = new Message();
        message.setSender(senderEmail);
        message.setReceiver(receiverEmail);
        message.setMessage(content);;
        return messageRepository.save(message);
    }


}
