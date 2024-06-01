package com.api.core.domain.chat.dto.response;

import com.api.core.domain.chat.entity.SenderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageResponse {
    private String message;
    private SenderType senderType;
    private String name;
    private String imageUrl;
    private Long id;
}
