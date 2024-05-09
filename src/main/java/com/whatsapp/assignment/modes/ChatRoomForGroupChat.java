package com.whatsapp.assignment.modes;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
@Entity
public class ChatRoomForGroupChat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String groupName;
    private String groupDescription;
    @OneToOne
    private WhatsAppUserRegistration createdBy;
    @OneToMany
    private List<WhatsAppUserRegistration> groupMembers;
    @OneToMany
    private List<ChatMessage> chatMessage;
}
