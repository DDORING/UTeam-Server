package com.api.core.domain.chat.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatSaveRoomResponse {
    @Schema(description = "채팅방 ID")
    private Long id;
}
