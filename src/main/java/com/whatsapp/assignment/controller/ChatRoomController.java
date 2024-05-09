package com.whatsapp.assignment.controller;

import com.whatsapp.assignment.dto.request.ChatRoomRequestDto;
import com.whatsapp.assignment.dto.response.ChatRoomResponseDto;
import com.whatsapp.assignment.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/whatsapp/chatroom")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;
    @PostMapping("/create")
    public ChatRoomResponseDto createChatRoom(@RequestBody ChatRoomRequestDto chatRoomRequestDto) {
        return chatRoomService.createChatRoom(chatRoomRequestDto);
    }
}
