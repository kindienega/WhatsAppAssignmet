package com.whatsapp.assignment.repository;

import com.whatsapp.assignment.modes.ChatRoomForGroupChat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoomForGroupChat, Long>{
}
