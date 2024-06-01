package com.api.core.domain.chat.dto.request;

import com.api.core.domain.chat.entity.ChatMessage;
import com.api.core.domain.chat.entity.ChatRoom;
import com.api.core.domain.chat.entity.SenderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageRequest {
    private String message;

    public ChatMessage toHumanMessageEntity(ChatRoom chatRoom) {
        return ChatMessage.builder()
                .message(this.message)
                .senderType(SenderType.HUMAN)
                .chatRoom(chatRoom)
                .build();
    }

    public ChatMessage toAIMessageEntity(ChatRoom chatRoom) {
        return ChatMessage.builder()
                .message(this.message)
                .senderType(SenderType.AI)
                .chatRoom(chatRoom)
                .build();
    }
}
