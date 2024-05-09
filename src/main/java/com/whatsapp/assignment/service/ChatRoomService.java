package com.whatsapp.assignment.service;

import com.whatsapp.assignment.dto.request.ChatRoomRequestDto;
import com.whatsapp.assignment.dto.response.ChatRoomResponseDto;
import com.whatsapp.assignment.modes.ChatMessage;
import com.whatsapp.assignment.modes.ChatRoomForGroupChat;
import com.whatsapp.assignment.modes.WhatsAppUserRegistration;
import com.whatsapp.assignment.repository.ChatRoomRepository;
import com.whatsapp.assignment.repository.WhatsAppUserRegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final WhatsAppUserRegistrationRepository userRegistrationRepository;

    public ChatRoomResponseDto createChatRoom(ChatRoomRequestDto chatRoomRequestDto) {
        WhatsAppUserRegistration createdBy = userRegistrationRepository.findById(chatRoomRequestDto.getCreatedById())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        List<WhatsAppUserRegistration> groupMembers = chatRoomRequestDto.getGroupMemberIds().stream()
                .map(id -> userRegistrationRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found")))
                .collect(Collectors.toList());

        ChatRoomForGroupChat chatRoom = new ChatRoomForGroupChat();
        chatRoom.setGroupName(chatRoomRequestDto.getGroupName());
        chatRoom.setGroupDescription(chatRoomRequestDto.getGroupDescription());
        chatRoom.setCreatedBy(createdBy);
        chatRoom.setGroupMembers(groupMembers);
        chatRoom = chatRoomRepository.save(chatRoom);

        ChatRoomResponseDto response = new ChatRoomResponseDto();
        response.setGroupName(chatRoom.getGroupName());
        response.setGroupDescription(chatRoom.getGroupDescription());
        response.setCreatedBy(chatRoom.getCreatedBy().getEmail());
        response.setGroupMembers(chatRoom.getGroupMembers().stream().map(WhatsAppUserRegistration::getEmail).collect(Collectors.toList()));

        return response;
    }
    public List<ChatRoomResponseDto> getAllChatRooms() {
    return chatRoomRepository.findAll().stream()
            .map(chatRoom -> {
                ChatRoomResponseDto response = new ChatRoomResponseDto();
                response.setGroupName(chatRoom.getGroupName());
                response.setGroupDescription(chatRoom.getGroupDescription());
                response.setCreatedBy(chatRoom.getCreatedBy().getEmail());
                response.setGroupMembers(chatRoom.getGroupMembers().stream().map(WhatsAppUserRegistration::getEmail).collect(Collectors.toList()));
                return response;
            })
            .collect(Collectors.toList());
    }

    public ChatRoomResponseDto addUserToChatRoom(Long chatRoomId, Long userId) {
        ChatRoomForGroupChat chatRoom = chatRoomRepository.findById(chatRoomId)
                .orElseThrow(() -> new IllegalArgumentException("Chat room not found"));
        WhatsAppUserRegistration user = userRegistrationRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        chatRoom.getGroupMembers().add(user);

        chatRoom = chatRoomRepository.save(chatRoom);
        ChatRoomResponseDto response = new ChatRoomResponseDto();
        response.setGroupName(chatRoom.getGroupName());
        response.setGroupDescription(chatRoom.getGroupDescription());
        response.setCreatedBy(chatRoom.getCreatedBy().getEmail());
        response.setGroupMembers(chatRoom.getGroupMembers().stream().map(WhatsAppUserRegistration::getEmail).collect(Collectors.toList()));
        return response;
    }
    public ChatRoomResponseDto removeUserFromChatRoom(Long chatRoomId, Long userId) {
    ChatRoomForGroupChat chatRoom = chatRoomRepository.findById(chatRoomId)
            .orElseThrow(() -> new IllegalArgumentException("Chat room not found"));

    WhatsAppUserRegistration user = userRegistrationRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));

    chatRoom.getGroupMembers().remove(user);

    chatRoom = chatRoomRepository.save(chatRoom);

    ChatRoomResponseDto response = new ChatRoomResponseDto();
    response.setGroupName(chatRoom.getGroupName());
    response.setGroupDescription(chatRoom.getGroupDescription());
    response.setCreatedBy(chatRoom.getCreatedBy().getEmail());
    response.setGroupMembers(chatRoom.getGroupMembers().stream().map(WhatsAppUserRegistration::getEmail).collect(Collectors.toList()));
    return response;
   }

   public ChatRoomResponseDto chatOnRoom(Long chatRoomId, Long userId, String message, MultipartFile file) throws IOException {
    ChatRoomForGroupChat chatRoom = chatRoomRepository.findById(chatRoomId)
            .orElseThrow(() -> new IllegalArgumentException("Chat room not found"));

    WhatsAppUserRegistration user = userRegistrationRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));

    if (!chatRoom.getGroupMembers().contains(user)) {
        throw new IllegalArgumentException("User must be in the chat room to send a message or file");
    }

    ChatMessage chatMessage = new ChatMessage();
    chatMessage.setSender(user);
    chatMessage.setMessage(message);

    if (file != null) {
        if (file.getSize() > 10 * 1024 * 1024) {
            throw new IllegalArgumentException("File size must be less than 10MB");
        }
        chatMessage.setFile(file.getBytes());
    }
    chatRoom.getChatMessage().add(chatMessage);
    chatRoom = chatRoomRepository.save(chatRoom);
    ChatRoomResponseDto response = new ChatRoomResponseDto();
    response.setGroupName(chatRoom.getGroupName());
    response.setGroupDescription(chatRoom.getGroupDescription());
    response.setCreatedBy(chatRoom.getCreatedBy().getEmail());
    response.setGroupMembers(chatRoom.getGroupMembers().stream().map(WhatsAppUserRegistration::getEmail).collect(Collectors.toList()));

    return response;
}



}
