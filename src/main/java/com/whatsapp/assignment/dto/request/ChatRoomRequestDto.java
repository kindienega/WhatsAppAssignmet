package com.whatsapp.assignment.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChatRoomRequestDto {
    private String groupName;
    private String groupDescription;
    private Long createdById;
    private List<Long> groupMemberIds;
}