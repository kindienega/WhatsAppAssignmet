package com.whatsapp.assignment.modes;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private WhatsAppUserRegistration sender;
    private String message;
    private byte[] file;
    private byte[] image;
    private byte[] video;
    private byte[] audio;
    private byte[] document;

}
