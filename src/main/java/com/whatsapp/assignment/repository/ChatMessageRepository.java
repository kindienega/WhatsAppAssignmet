package com.whatsapp.assignment.repository;

import com.whatsapp.assignment.modes.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
}
