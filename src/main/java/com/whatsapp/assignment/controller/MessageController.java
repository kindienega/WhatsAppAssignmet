package com.whatsapp.assignment.controller;

import com.whatsapp.assignment.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/whatsapp/message")
public class MessageController {

    private final MessageService messageService;
    @PostMapping("/send")
    public void sendMessage(String senderEmail, String receiverEmail, String content) {
        messageService.sendMessage(senderEmail, receiverEmail, content);
    }
}
