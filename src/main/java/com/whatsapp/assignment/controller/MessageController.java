package com.whatsapp.assignment.controller;

import com.whatsapp.assignment.dto.request.MessageRequestDto;
import com.whatsapp.assignment.dto.response.MessageResponseDto;
import com.whatsapp.assignment.modes.Message;
import com.whatsapp.assignment.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/whatsapp/message")
public class MessageController {

    private final MessageService messageService;
    @PostMapping("/send")
    public Message sendMessage(String senderEmail, String receiverEmail, String content) {
        return messageService.sendMessage(senderEmail, receiverEmail, content);
    }
    @PostMapping("/sendMessageWithFile")
    public MessageResponseDto sendMessageWithFile(@RequestParam("file") MultipartFile file, @RequestBody MessageRequestDto messageRequestDto){
        return messageService.sendMessageWithFile(file, messageRequestDto);
    }
}
