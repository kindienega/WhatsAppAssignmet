package com.whatsapp.assignment.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChatRoomResponseDto {
    private Long id;
    private String groupName;
    private String groupDescription;
    private String createdBy;
    private List<String> groupMembers;
}