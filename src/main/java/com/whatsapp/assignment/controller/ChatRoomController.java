package com.whatsapp.assignment.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.whatsapp.assignment.dto.request.ChatRoomRequestDto;
import com.whatsapp.assignment.dto.response.ChatRoomResponseDto;
import com.whatsapp.assignment.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/whatsapp/chatroom")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;
    private final Cloudinary cloudinary;
    @PostMapping("/create")
    public ChatRoomResponseDto createChatRoom(@RequestBody ChatRoomRequestDto chatRoomRequestDto) {
        return chatRoomService.createChatRoom(chatRoomRequestDto);
    }
    @PostMapping("/uploadImage")
    public String uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            return (String) uploadResult.get("url");
        } catch (IOException e) {
            throw new RuntimeException("Could not upload file. Please try again!", e);
        }
    }
    @GetMapping("/getAll")
    public List<ChatRoomResponseDto> getAllChatRooms() {
        return chatRoomService.getAllChatRooms();
    }

    @PostMapping("/{chatRoomId}/addUser/{userId}")
    public ChatRoomResponseDto addUserToChatRoom(@PathVariable Long chatRoomId, @PathVariable Long userId) {
         return chatRoomService.addUserToChatRoom(chatRoomId, userId);
    }

    @DeleteMapping("/{chatRoomId}/removeUser/{userId}")
    public ChatRoomResponseDto removeUserFromChatRoom(@PathVariable Long chatRoomId, @PathVariable Long userId) {
        return chatRoomService.removeUserFromChatRoom(chatRoomId, userId);
    }
    @PostMapping("/{chatRoomId}/chat/{userId}")
    public ChatRoomResponseDto chatOnRoom(@PathVariable Long chatRoomId, @PathVariable Long userId, @RequestParam(required = false) String message, @RequestParam(required = false) MultipartFile file) throws IOException {
        return chatRoomService.chatOnRoom(chatRoomId, userId, message, file);
    }

}

