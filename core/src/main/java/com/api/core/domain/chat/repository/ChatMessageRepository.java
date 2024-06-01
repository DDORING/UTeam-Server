package com.api.core.domain.chat.repository;

import com.api.core.domain.chat.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    void deleteAllInBatchByChatRoomId(final Long id);

    List<ChatMessage> findAllByChatRoomId(final Long id);
}