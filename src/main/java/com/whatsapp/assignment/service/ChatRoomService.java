package com.whatsapp.assignment.service;

import com.whatsapp.assignment.dto.request.ChatRoomRequestDto;
import com.whatsapp.assignment.dto.response.ChatRoomResponseDto;
import com.whatsapp.assignment.modes.ChatRoomForGroupChat;
import com.whatsapp.assignment.modes.WhatsAppUserRegistration;
import com.whatsapp.assignment.repository.ChatRoomRepository;
import com.whatsapp.assignment.repository.WhatsAppUserRegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        response.setId(chatRoom.getId());
        response.setGroupName(chatRoom.getGroupName());
        response.setGroupDescription(chatRoom.getGroupDescription());
        response.setCreatedBy(chatRoom.getCreatedBy().getEmail());
        response.setGroupMembers(chatRoom.getGroupMembers().stream().map(WhatsAppUserRegistration::getEmail).collect(Collectors.toList()));

        return response;
    }}
