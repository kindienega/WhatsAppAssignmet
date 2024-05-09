package com.whatsapp.assignment.repository;

import com.whatsapp.assignment.modes.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

}
